package com.example.emailverification;

import java.nio.file.LinkPermission;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
    /*
    * Password rules
    * at least 8 characters
    * 1 uppercase letter
    * 1 lowercase letter
    * 1 number
    * 1 special character
    * */

    public int validatePassword(String password, String username){
        if (containUsername(password, username)) return 1;
        if (!containUppercase(password)) return 2;
        if (!containLowercase(password)) return 3;
        if (!containDigit(password)) return 4;
        if (!containSpecialCharacter(password)) return 5;
        return 0;
    }

    public boolean containUsername(String password, String username){
        return (password.toLowerCase().contains(username.toLowerCase()));
    }

    public boolean containDigit(String password){
        for(int i = 0; i < password.length(); i++){
            char ch = password.charAt(i);
            if(Character.isDigit(ch))return true;
        }
        return false;
    }

    public boolean containUppercase(String password){
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(Character.isUpperCase(ch))return true;
        }
        return false;
    }

    public boolean containLowercase(String password){
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(Character.isLowerCase(ch))return true;
        }
        return false;
    }

    public boolean containSpecialCharacter(String password){
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if(!Character.isLetterOrDigit(ch))return true;
        }
        return false;
    }
    /*
    * Password hashing
    * 1. password_hashed = hash(password)
    * 2. username_hashed = hash(username)
    * 3. finalPassword = hash(password_hashed + username_hashed)
    * */

    public String getHash(String password, String username){
        String passwordHashed = hash(password);
        String usernameHashed = hash(username);
        String finalPassword = hash(passwordHashed + usernameHashed);
        return finalPassword;
    }

    public String hash(String password){
        StringBuilder hashedPassword = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            for(byte b : messageDigest){
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("MD5 Algorithm not found", e);
        }
    }
}
