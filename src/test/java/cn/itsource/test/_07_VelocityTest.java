package cn.itsource.test;

import cn.itsource.ibs.query.DeptQuery;
import cn.itsource.ibs.service.IDeptService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _07_VelocityTest extends BaseTest{

    @Test
    public void test() throws Exception{
        //Velocity上下文，就是一个引擎【内部有一个Map集合】
        VelocityContext context = new VelocityContext();
        //获取模板文件[单元测试：模板文件应该放在当前项目的根目录下，当然可以创建子目录来存放]
        Template template = Velocity.getTemplate("template/hello.vm", "UTF-8");
        //传递数据
        // 【key必须是字符串，而且必须与模板文件中的${}之内的变量名称一致，否则就直接显示${...}，
        // 但是可以加上感叹号$!{..}当找不到数据的时候就不显示任何东西】
        context.put("name", "伍涛");

        //保存一个数组数据
        String[] citys = {"重庆","成都","北京"};
        context.put("citys", citys);
        //保存一个集合数据
        List<String> names = Arrays.asList(new String[]{"张三丰","渣渣辉","伍涛","王晨燕"});
        context.put("names", names);
        //保存一个Map集合数据
        Map<String,Object> map = new HashMap<>();
        map.put("aaa", "重庆市大竹林");
        map.put("bbb", 123);
        map.put("ccc", 3.45F);
        context.put("map", map);


        //将模板文件与数据合并,合并之后输出到指定地方
        StringWriter stringWriter = new StringWriter();
        template.merge(context,stringWriter);

        System.out.println(stringWriter);
    }


    @Autowired
    private IDeptService deptService;
    @Test
    public void testEasyCode() throws Exception{
        System.out.println(deptService);
        System.out.println(deptService.getClass());
        DeptQuery deptQuery = new DeptQuery();
        deptService.find(deptQuery).forEach(d -> System.out.println(d));
    }


}
