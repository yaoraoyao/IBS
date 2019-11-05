package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.repository.IEmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


public class _01_JpaRespositoryTest extends BaseTest{

    @Autowired
    private IEmployeeRepository employeeRepository;


    @Test
    public void test() throws Exception{
        //org.springframework.data.jpa.repository.support.SimpleJpaRepository@512d4583
        System.out.println(employeeRepository);
        //class com.sun.proxy.$Proxy26
        System.out.println(employeeRepository.getClass());
    }

    @Test
    public void testFindAll() throws Exception{
        List<Employee> list = employeeRepository.findAll();
        list.forEach(e -> System.out.println(e));
    }

    @Test
    public void testInsert() throws Exception{
        Employee employee = new Employee();
        employee.setAge(22);
        employee.setEmail("dadsasdds@qq.com");
        employee.setHeadImage("aaaaaa.png");
        employee.setUsername("admin100");
        employee.setPassword("123");
        employeeRepository.save(employee);
    }
    @Test
    public void testUpdate() throws Exception{
        Employee employee = employeeRepository.findOne(273L);
        employee.setUsername("admin102");
        employeeRepository.save(employee);
    }
    @Test
    public void testDelete() throws Exception{
        employeeRepository.delete(272L);
    }

    /**
     * 测试SpringDataJPA的分页查询
     * @throws Exception
     */
    @Test
    public void testFindAllByPage() throws Exception{
        /**
         * int page 表示当前页码，SpringDataJPA中的当前页码从0开始的
         * int size 表示每页显示多少行数据
         */
        Pageable pageable = new PageRequest(13,10);
        Page<Employee> page = employeeRepository.findAll(pageable);
        /**
         * SpringDataJPA的分页查询返回的Page对象，千万注意不是我们自己写的那个Page类的对象
         *
         */
        System.out.println(page.getTotalElements());        //总行数
        System.out.println(page.getTotalPages());           //总页数
        System.out.println(page.getNumber());               //当前页码 从0开始的
        System.out.println(page.getNumberOfElements());     //当前页有多少行数据
        System.out.println(page.getSize());                 //每页显示多少行数据
        System.out.println(page.getContent());              //获取当前页的数据集合
    }

    /**
     * 测试SpringDataJPA的排序查询
     * @throws Exception
     */
    @Test
    public void testFindAllOrderBy() throws Exception{
//        //单列排序：Order类是Sort类中内部类
//        //  Direction是Sort类中的内部枚举，表示升序或者降序
//        //  property参数表示排序的domain实体类对象的属性名称
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
//        Sort sort = new Sort(order);

        //多列排序
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "age");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = new Sort(order1,order2);
        List<Employee> list = employeeRepository.findAll(sort);
        list.forEach(e -> System.out.println(e));
    }

    /**
     * 测试SpringDataJPA的分页+排序查询
     * @throws Exception
     */
    @Test
    public void testFindAllByPageAndOrderBy() throws Exception{
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "age");
        Sort sort = new Sort(order);
        /**
         * int page 表示当前页码，SpringDataJPA中的当前页码从0开始的
         * int size 表示每页显示多少行数据
         * Sort sort 表示排序
         */
        Pageable pageable = new PageRequest(0,10,sort);
        Page<Employee> page = employeeRepository.findAll(pageable);
        page.forEach(e -> System.out.println(e));
    }

//    /**
//     * 测试SpringDataJPA简单条件查询
//     * @throws Exception
//     */
//    @Test
//    public void testFindAllByUsernameLike() throws Exception{
//        List<Employee> list = employeeRepository.findAllByUsernameLike("%admin10%");
//        list.forEach(e -> System.out.println(e));
//    }
//
//    /**
//     * 测试SpringDataJPA多个简单条件查询
//     * @throws Exception
//     */
//    @Test
//    public void testFindAllByUsernameLikeAndEmailLikeAndAge() throws Exception{
//        List<Employee> list = employeeRepository.findAllByUsernameLikeAndEmailLikeAndAge("%admin%", "%2%", 25);
//        list.forEach(e -> System.out.println(e));
//    }
//
//    @Test
//    public void testFindAllByUsername() throws Exception{
//        List<Employee> list = employeeRepository.findAllByUsername("%admin10%");
//        list.forEach(e -> System.out.println(e));
//    }


}
