package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.query.BaseQuery;
import cn.itsource.ibs.query.EmployeeQuery;
import cn.itsource.ibs.repository.IEmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

public class _04_BaseQueryTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void testFind() throws Exception{
        //这里我们是直接创建对象的，今后集成了SpringMVC之后直接在Controller类的方法参数中直接写即可
        EmployeeQuery employeeQuery = new EmployeeQuery();
//        employeeQuery.setUsername("admin");
//        employeeQuery.setEmail("2");
        List<Employee> list = employeeRepository.findAll(employeeQuery.getSpecification());
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void testFindAndPageAndSort() throws Exception{
        //这里我们是直接创建对象的，今后集成了SpringMVC之后直接在Controller类的方法参数中直接写即可
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("admin");
//        employeeQuery.setEmail("2");
        employeeQuery.setOrderType("desc");
        Page<Employee> page = employeeRepository.findAll(employeeQuery.getSpecification(), employeeQuery.getPageable());
        page.forEach(e -> System.out.println(e));
    }

}
