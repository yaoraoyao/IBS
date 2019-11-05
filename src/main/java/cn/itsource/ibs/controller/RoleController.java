package cn.itsource.ibs.controller;

import cn.itsource.ibs.domain.Role;
import cn.itsource.ibs.query.RoleQuery;
import cn.itsource.ibs.service.IRoleService;
import cn.itsource.ibs.utils.MyPage;
import cn.itsource.ibs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Role)表控制层
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:03
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/index.do")
    public String index(){
        return "role/role";
    }

    @ResponseBody
    @RequestMapping("/page.do")
    public MyPage<Role> page(RoleQuery roleQuery){
        return roleService.findAll(roleQuery);
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public Result save(Role role){
        Result result = null;
        try {
            if(role.getId() != null && role.getId() > 0)
                result = new Result(200,"编辑成功！");
            else
                result = new Result(200,"新增成功！");
            /**
             * roleService.save(role);  新增或者修改
             *  如果是新增【id为null】，新增完成之后该对象变成持久状态【与数据库表中的数据保持一致】，保存成功之后id就不为null了
             */
            roleService.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500,"操作失败：" + e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findOne.do")
    public Role findOne(Long id){
        return roleService.findOne(id);
    }
    @ResponseBody
    @RequestMapping("/delete.do")
    public Result delete(String ids){
        try {
            roleService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}