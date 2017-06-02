package com.desmond.codebase.security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//public class MD5 {
//
//    public static String calculate(String str) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("md5");
//            digest.update(str.getBytes());
//            return new BigInteger(1, digest.digest()).toString(16);
//        } catch (NoSuchAlgorithmException e) {
//            return "";
//        }
//    }
//
//}

/*
 * MD5 算法
*/
public class MD5 {

    // 全局数组
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    // 返回形式只为数字
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String calculate(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            return byteToString(md.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

    private static String moneyMd5(float money) {
        String hkey = "money_1st_" + money + "3tzlc_4gli_2job";

        return MD5.calculate(hkey);
    }

    public static void main(String[] args) {
        System.out.println(moneyMd5(10000) + "," + 10000);
        System.out.println(moneyMd5(9000) + "," + 9000);
        System.out.println(moneyMd5(3000) + "," + 3000);
        System.out.println(moneyMd5(4000) + "," + 4000);
        System.out.println(moneyMd5(7000) + "," + 7000);
    }
}
