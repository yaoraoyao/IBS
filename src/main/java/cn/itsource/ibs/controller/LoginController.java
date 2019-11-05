package cn.itsource.ibs.controller;

import cn.itsource.ibs.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/index.do")
    public String index(){
        return "index";
    }

    /**
     * method = RequestMethod.GET       指定请求方法是GET或者POST
     * method = RequestMethod.POST
     * @return
     */
    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public Result login(String username, String password){
        //获取当前用户【Shiro认为只要执行上面几行代码，就认为你是一个用户，不管你是否登录】
        Subject currentUser = SecurityUtils.getSubject();
        try {
            if(!currentUser.isAuthenticated()){
                //UsernamePasswordToken表示令牌，就包含用户名和密码【从前端表单提交过来的用户名和密码】
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
                //登录
                currentUser.login(usernamePasswordToken);
            }
        } catch (UnknownAccountException e){
            e.printStackTrace();
            //System.out.println("登录失败：用户名错误！");
//            session.setAttribute("errorMsg", "登录失败：用户名错误！");
            return new Result(500,"登录失败：用户名错误！");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            //System.out.println("登录失败：密码错误！");
//            session.setAttribute("errorMsg", "登录失败：密码错误！");
            return new Result(500,"登录失败：密码错误！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
//            session.setAttribute("errorMsg", "登录失败：系统繁忙！");
            return new Result(500,"登录失败：系统繁忙！");
        }
        return new Result(200,"登录成功！");
    }

    @RequestMapping("/logout.do")
    public String logout(){
        //获取当前用户【Shiro认为只要执行上面几行代码，就认为你是一个用户，不管你是否登录】
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }

}
