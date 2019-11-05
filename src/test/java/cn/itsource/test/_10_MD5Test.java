package cn.itsource.test;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class _10_MD5Test {

    /**
     * 密码加密
     *  1）MD5 不可逆 只能加密不能解密  所有密码加密之后都是32位十六进制数字组成的字符串
     *  2）散列算法 SHA
     * @throws Exception
     */
    @Test
    public void test() throws Exception{

        /**
         * String algorithmName 加密方式：MD5、SHA
         * Object source        需要加密的密码
         * Object salt          盐值
         * int hashIterations   加密次数
         */
        //admin 盐值为cn.itsource 加密10次之后 6006eba3724471f7e1b7945b10ddb1d7
        //保存密码数据的时候的加密规则要与登录的时候使用的加密规则完全一致
        SimpleHash simpleHash = new SimpleHash("MD5", "admin", "cn.itsource", 10);
        System.out.println(simpleHash.toString());
    }

}
