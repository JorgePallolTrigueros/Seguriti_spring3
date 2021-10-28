package com.clases.security.usuarios.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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

    public static String currentDate(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }


    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Get Final cause of exception
     * @param t
     * @return Throwable
     */
    public static String getFinalCause(Throwable t){
        if(t.getCause()!=null){
            return getFinalCause(t.getCause());
        }else{
            return t.toString().replaceAll("(\\r|\\n|\\t)", " ");
        }
    }

    /**
     * Get Class name by depth level
     * @param level
     * @return Class Name
     */
    public static String getClassName(int level) {
        return Thread.currentThread().getStackTrace()[level].getClassName();
    }


    /**
     * Get Class name default depth level 2
     * @return Class Name
     */
    public static String getClassName() {
        return getClassName(2);
    }

    /**
     * Get Method name by depth level
     * @param level
     * @return Method Name
     */
    public static String getMethodName(int level) {
        return Thread.currentThread().getStackTrace()[level].getMethodName();
    }

    /**
     * Get Method name default depth level 2
     * @return Method Name
     */
    public static String getMethodName() {
        return getMethodName(2);
    }

    public static String getMethodWithClass(){
        String clazz = getClassName(3);
        clazz =  clazz.substring(clazz.lastIndexOf(".")+1);
        return clazz+"."+getMethodName(3);
    }

}
