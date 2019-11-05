package cn.itsource.test;

import cn.itsource.ibs.shiro.JpaRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class _09_JpaRealmTest {

    @Test
    public void test() throws Exception{

        //自己创建一个DefaultSecurityManager对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //创建自定义JpaRealm的对象
        JpaRealm jpaRealm = new JpaRealm();

        //设置加密规则
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");        //设置加密方式
        matcher.setHashIterations(10);              //设置加密次数
        jpaRealm.setCredentialsMatcher(matcher);    //将当前加密规则设置给自定义JpaRealm的对象

        //将自定义JpaRealm的对象设置成DefaultSecurityManager对象的属性
        securityManager.setRealm(jpaRealm);

        //将SecurityManager对象放入Shiro的上下文中[Shiro的Session]
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户【Shiro认为只要执行上面几行代码，就认为你是一个用户，不管你是否登录】
        Subject currentUser = SecurityUtils.getSubject();

        //判断当前用户是否已经登录
        System.out.println("用户是否已登录：" + currentUser.isAuthenticated());


        try {
            if(!currentUser.isAuthenticated()){
                //UsernamePasswordToken表示令牌，就包含用户名和密码【从前端表单提交过来的用户名和密码】
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin","admin");
                //登录
                currentUser.login(usernamePasswordToken);
            }
        } catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("登录失败：用户名错误！");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("登录失败：密码错误！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }


        //判断当前用户是否已经登录
        System.out.println("用户是否已登录：" + currentUser.isAuthenticated());

        //判断当前登录用户使用拥有*权限
        System.out.println("判断当前登录用户使用拥有*权限：" + currentUser.isPermitted("*"));
        //判断当前登录用户使用拥有employee:*权限
        System.out.println("判断当前登录用户使用拥有employee:*权限：" + currentUser.isPermitted("employee:*"));

    }

}
