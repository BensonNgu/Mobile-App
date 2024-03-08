package com.example.emailverification;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    public String getMD5HashString(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] MessageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(byte b : MessageDigest){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("MD5 Algorithms not found", e);
        }
    }

    public bool containUsername(String password, String username){
        if(password.toUpperCase().contains(username.toUpperCase()))return true;
        
        return false;
    }

    public bool containUppercase(String password){
        
    }
}
