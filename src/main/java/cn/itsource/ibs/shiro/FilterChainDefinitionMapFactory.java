package cn.itsource.ibs.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

public class FilterChainDefinitionMapFactory {

    public Map<String,String> buildFilterChainDefinitionMap(){
        //LinkedHashMap有序的
        Map<String,String> map = new LinkedHashMap<>();
        //静态资源放行【css、js、图片】
        map.put("/favicon.ico", "anon");
        map.put("*.js","anon");
        map.put("*.css","anon");
        map.put("/css/**","anon");
        map.put("/js/**","anon");
        map.put("/easyui/**","anon");
        map.put("/img/**","anon");
        //登录表单提交地址必须放开，不需要登录就可以直接访问
        map.put("/login.do", "anon");
        map.put("/*.jar", "anon");
        //配置访问/s/emp.jsp这个资源需要拥有哪些权限
        map.put("/s/emp.jsp", "perms[employee:*]");
        //除了上面的之外的其他所有资源都必须登录后才能访问
        map.put("/**", "authc");
        return map;
    }

}
