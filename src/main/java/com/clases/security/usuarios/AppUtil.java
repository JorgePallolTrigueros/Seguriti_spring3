package com.clases.security.usuarios;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class AppUtil {
    /**
     * Obtiene el hash de un archivo lo cual me sirve para verificar su caracteristica unica
     * @param hash
     * @return
     */
    public static String toHexString(byte[] hash) {

        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }



    public static String getFileChecksum(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] uploadBytes = file.getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(uploadBytes);
        String hashString = new BigInteger(1, digest).toString(16);
        return hashString;
    }
}
