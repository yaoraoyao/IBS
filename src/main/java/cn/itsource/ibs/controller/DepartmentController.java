package cn.itsource.ibs.controller;

import cn.itsource.ibs.domain.Department;
import cn.itsource.ibs.query.DepartmentQuery;
import cn.itsource.ibs.service.IDepartmentService;
import cn.itsource.ibs.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/index.do")
    public String index(){
        return "department/department";
    }

    @ResponseBody
    @RequestMapping("/page.do")
    public MyPage<Department> page(DepartmentQuery departmentQuery){
        return departmentService.findAll(departmentQuery);
    }


    @ResponseBody
    @RequestMapping("/list.do")
    public List<Department> list(DepartmentQuery departmentQuery){
        List<Department> list = departmentService.find(departmentQuery);
        list.add(0, new Department(-1L,"请选择部门"));
        return list;
    }

}
