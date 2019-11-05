package cn.itsource.ibs.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {

    private static final String ALGORITHMNAME = "MD5";      //加密方式
    private static final String SALT = "cn.itsource";               //盐值
    private static final Integer HASHITERATIONS = 10;       //加密次数

    public static String getMD5Password(String password){
        SimpleHash simpleHash = new SimpleHash(ALGORITHMNAME, password, SALT, HASHITERATIONS);
        return simpleHash.toString();
    }

}
