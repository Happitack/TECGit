using System.Security.Cryptography;

namespace CertificateEncryptionProj.Security;
public class EncryptionService
{
    private readonly string _encryptionKey;
    private readonly string _encryptionIV; // Initialization Vector

    // Contructor to intialize key and IV
    public EncryptionService(string encryptionKey, string encryptionIV)
    {
        _encryptionKey = encryptionKey;
        _encryptionIV = encryptionIV;
    }

    // Method for encrypting a string
    public byte[] Encrypt(string plainText)
    {
        using (Aes aesAlg = Aes.Create()) // Creates an AES object
        {
            aesAlg.Key = Convert.FromBase64String(_encryptionKey); // Converts the key from Base64 to a byte array
            aesAlg.IV = Convert.FromBase64String(_encryptionIV); // Converts the IV from Base64 to a byte array

            ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV); // Creates an encryptor object

            // Creates a memory stream to write the encrypted data to
            using (MemoryStream msEncrypt = new MemoryStream())
            {
                using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                {
                    swEncrypt.Write(plainText); // Writes the plain text to the stream writer
                }

                return msEncrypt.ToArray(); // Returns the encrypted data as a byte array
            }
        }
    }

    // Method for decrypting a byte array
    public string Decrypt(byte[] cipherText)
    {
        using (Aes aesAlg = Aes.Create()) // Creates an AES object
        {
            aesAlg.Key = Convert.FromBase64String(_encryptionKey); // Converts the key from Base64 to a byte array
            aesAlg.IV = Convert.FromBase64String(_encryptionIV); // Converts the IV from Base64 to a byte array

            ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV); // Creates a decryptor object

            // Creates a memory stream to read the encrypted data from
            using (MemoryStream msDecrypt = new MemoryStream(cipherText))
            using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
            using (StreamReader srDecrypt = new StreamReader(csDecrypt))
            {
                return srDecrypt.ReadToEnd(); // Returns the decrypted data as a string
            }
        }
    }
}
