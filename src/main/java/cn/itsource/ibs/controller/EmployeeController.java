package cn.itsource.ibs.controller;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.query.EmployeeQuery;
import cn.itsource.ibs.service.IEmployeeService;
import cn.itsource.ibs.utils.MyPage;
import cn.itsource.ibs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/index.do")
    public String index(){
        return "employee/employee";
    }

    @ResponseBody
    @RequestMapping("/page.do")
    public MyPage<Employee> page(EmployeeQuery employeeQuery){
        return employeeService.findAll(employeeQuery);
    }

    @ResponseBody
    @RequestMapping("/upload.do")
    public String upload(MultipartFile headImage, HttpServletRequest request) throws IOException {
        String fileName = headImage.getOriginalFilename();
        /**
         * 1.文件名称不能重复
         * 2.保存路径应该在当前项目的webapp目录下
         */
        //获取文件名称后缀
        String ext = fileName.substring(fileName.lastIndexOf("."));
        //使用随机数+时间毫秒   或者UUID 来作为文件名称
        String newName = UUID.randomUUID().toString();
        //现在是开发阶段，文件路径就写成项目路径下，若是要发布上线，就需要改为tomcat的webapps目录路径
        String realPath = request.getServletContext().getRealPath("/img/head/");
        File out = new File(realPath + newName + ext);
        //将请求中的文件二进制数据写入到服务器端的磁盘文件中
        headImage.transferTo(out);
        return "/img/head/" + newName + ext;
    }

    @ResponseBody
    @RequestMapping("/save.do")
    public Result save(Employee employee){
        Result result = null;
        try {
            if(employee.getId() != null && employee.getId() > 0)
                result = new Result(200,"员工编辑成功！");
            else
                result = new Result(200,"员工新增成功！");
            /**
             * employeeService.save(employee);  新增或者修改
             *  如果是新增【id为null】，新增完成之后该对象变成持久状态【与数据库表中的数据保持一致】，保存成功之后id就不为null了
             */
            employeeService.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500,"操作失败：" + e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/findOne.do")
    public Employee findOne(Long id){
        return employeeService.findOne(id);
    }
    @ResponseBody
    @RequestMapping("/delete.do")
    public Result delete(String ids){
        try {
            employeeService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }



}
