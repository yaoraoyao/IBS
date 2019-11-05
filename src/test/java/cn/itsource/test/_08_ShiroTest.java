package cn.itsource.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class _08_ShiroTest {

    @Test
    public void test01() throws Exception{
        //通过加载ini文件来创建一个Factory工厂对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //通过工厂创建一个SecurityManager对象【Shiro的核心对象，Shiro的所有操作都要基于这个对象】
        SecurityManager securityManager = factory.getInstance();

        //将SecurityManager对象放入Shiro的上下文中[Shiro的Session]
        SecurityUtils.setSecurityManager(securityManager);

        //获取当前用户【Shiro认为只要执行上面几行代码，就认为你是一个用户，不管你是否登录】
        Subject currentUser = SecurityUtils.getSubject();

        //判断当前用户是否已经登录
        System.out.println("用户是否已登录：" + currentUser.isAuthenticated());


        try {
            if(!currentUser.isAuthenticated()){
                //UsernamePasswordToken表示令牌，就包含用户名和密码【从前端表单提交过来的用户名和密码】
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("guest","guest");
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

//        //判断当前用户是否已经登录
//        System.out.println("用户是否已登录：" + currentUser.isAuthenticated());
//
//        //判断当前登录用户是否属于admin角色
//        System.out.println("判断当前登录用户是否属于admin角色：" + currentUser.hasRole("admin"));
//        //判断当前登录用户是否属于guest角色
//        System.out.println("判断当前登录用户是否属于guest角色：" + currentUser.hasRole("guest"));
//
//        //判断当前登录用户使用拥有*权限
//        System.out.println("判断当前登录用户使用拥有*权限：" + currentUser.isPermitted("*"));
//        //判断当前登录用户使用拥有employee:*权限
//        System.out.println("判断当前登录用户使用拥有employee:*权限：" + currentUser.isPermitted("employee:*"));

        //退出登录
        if(currentUser.isAuthenticated()){
            currentUser.logout();
        }

    }

}
