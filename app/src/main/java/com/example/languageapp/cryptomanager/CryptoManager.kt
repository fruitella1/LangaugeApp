package com.example.languageapp.cryptomanager

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

internal class CryptoManager {
    private val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE).apply {
        load(null)
    }

    private fun getDecryptionCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM, ANDROID_KEY_STORE).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .build()
            )
        }.generateKey()
    }

    fun encrypt(bytes: ByteArray): EncryptedData {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getKey())

        val encryptedBytes = cipher.doFinal(bytes)

        return EncryptedData(
            cipherText = encryptedBytes,
            iv = cipher.iv
        )
    }

    fun decrypt(data: EncryptedData): ByteArray {
        return getDecryptionCipherForIv(data.iv).doFinal(data.cipherText)
    }

    fun encryptString(value: String): EncryptedData {
        return encrypt(value.toByteArray(Charsets.UTF_8))
    }

    fun decryptToString(data: EncryptedData): String {
        return decrypt(data).toString(Charsets.UTF_8)
    }

    companion object {
        private const val KEY_ALIAS = "Secret"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }
}

data class EncryptedData(
    val cipherText: ByteArray,
    val iv: ByteArray
)
private const val ANDROID_KEY_STORE = "AndroidKeyStore"