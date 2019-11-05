package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.query.EmployeeQuery;
import cn.itsource.ibs.repository.IEmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class _05_IBaseRepositoryTest extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception{
        System.out.println(employeeRepository);
        System.out.println(employeeRepository.getClass());
    }
    @Test
    public void testFindPageByQuery() throws Exception{
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("admin");
        employeeQuery.setEmail("2");
        employeeQuery.setOrderType("desc");
        //findPageByQuery方法需要一个BaseQuery参数，但是我们传进去的是它的子类对象，子类重写了方法之后就形成多态
        Page<Employee> page = employeeRepository.findPageByQuery(employeeQuery);
        page.forEach(e -> System.out.println(e));
    }


}
