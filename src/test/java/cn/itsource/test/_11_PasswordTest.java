package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.service.IEmployeeService;
import cn.itsource.ibs.utils.MD5Util;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class _11_PasswordTest extends BaseTest {

    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void test() throws Exception{
        //查询员工表所有数据【全都是持久状态的】
        List<Employee> list = employeeService.findByJpql("select e from Employee e");

        for (Employee employee : list) {
            //将密码设置为用户名一样的 但是要加密
            employee.setPassword(MD5Util.getMD5Password(employee.getUsername()));
            employeeService.save(employee);
        }
    }

}
