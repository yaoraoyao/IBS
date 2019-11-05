package cn.itsource.ibs.controller;

import cn.itsource.ibs.domain.Permission;
import cn.itsource.ibs.query.PermissionQuery;
import cn.itsource.ibs.service.IPermissionService;
import cn.itsource.ibs.utils.MyPage;
import cn.itsource.ibs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Permission)表控制层
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:06
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/index.do")
    public String index(){
        return "permission/permission";
    }

    @ResponseBody
    @RequestMapping("/page.do")
    public MyPage<Permission> page(PermissionQuery permissionQuery){
        return permissionService.findAll(permissionQuery);
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public Result save(Permission permission){
        Result result = null;
        try {
            if(permission.getId() != null && permission.getId() > 0)
                result = new Result(200,"编辑成功！");
            else
                result = new Result(200,"新增成功！");
            /**
             * permissionService.save(permission);  新增或者修改
             *  如果是新增【id为null】，新增完成之后该对象变成持久状态【与数据库表中的数据保持一致】，保存成功之后id就不为null了
             */
            permissionService.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500,"操作失败：" + e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findOne.do")
    public Permission findOne(Long id){
        return permissionService.findOne(id);
    }
    @ResponseBody
    @RequestMapping("/delete.do")
    public Result delete(String ids){
        try {
            permissionService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }
}