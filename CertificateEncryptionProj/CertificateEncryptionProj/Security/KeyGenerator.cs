using System;
using System.Security.Cryptography;

namespace CertificateEncryptionProj.Security
{
    // Generates a random key and IV for AES encryption
    public class KeyGenerator
    {
        public (string Key, string IV) GenerateKeyAndIV()
        {
            byte[] key = new byte[32]; // 256-bit key
            byte[] iv = new byte[16];  // 128-bit IV

            RandomNumberGenerator.Fill(key);
            RandomNumberGenerator.Fill(iv);

            return (Convert.ToBase64String(key), Convert.ToBase64String(iv));
        }
    }
}
