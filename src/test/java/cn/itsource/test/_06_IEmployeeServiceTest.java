package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.service.IEmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class _06_IEmployeeServiceTest extends BaseTest {

    @Autowired
    private IEmployeeService employeeService;


    @Test
    public void testFindOne() throws Exception{
        System.out.println(employeeService);
        System.out.println(employeeService.getClass());
        Employee employee = employeeService.findOne(274L);
        System.out.println(employee);
    }

}
