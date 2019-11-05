package cn.itsource.ibs.controller;

import cn.itsource.ibs.domain.Dept;
import cn.itsource.ibs.query.DeptQuery;
import cn.itsource.ibs.service.IDeptService;
import cn.itsource.ibs.utils.MyPage;
import cn.itsource.ibs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Dept)表控制层
 *
 * @author 吴昌勇
 * @since 2019-08-16 15:17:47
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @RequestMapping("/index.do")
    public String index(){
        return "dept/dept";
    }

    @ResponseBody
    @RequestMapping("/page.do")
    public MyPage<Dept> page(DeptQuery deptQuery){
        return deptService.findAll(deptQuery);
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public Result save(Dept dept){
        Result result = null;
        try {
            if(dept.getId() != null && dept.getId() > 0)
                result = new Result(200,"编辑成功！");
            else
                result = new Result(200,"新增成功！");
            /**
             * deptService.save(dept);  新增或者修改
             *  如果是新增【id为null】，新增完成之后该对象变成持久状态【与数据库表中的数据保持一致】，保存成功之后id就不为null了
             */
            deptService.save(dept);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500,"操作失败：" + e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findOne.do")
    public Dept findOne(Long id){
        return deptService.findOne(id);
    }
    @ResponseBody
    @RequestMapping("/delete.do")
    public Result delete(String ids){
        try {
            deptService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}