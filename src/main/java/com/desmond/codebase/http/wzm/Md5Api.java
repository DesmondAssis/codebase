package com.desmond.codebase.http.wzm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Api {
    public static String calculate(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(str.getBytes());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
