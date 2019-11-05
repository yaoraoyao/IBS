package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.repository.IEmployeeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class _02_JpaSpecificationExecutorTest extends BaseTest{

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * 单条件高级查询
     * @throws Exception
     */
    @Test
    public void testFindByUserNameLike() throws Exception{
        //Specification表示一个规范或者标准【高级查询的条件】
        Specification<Employee> specification = new Specification<Employee>(){
            /**
             * 非官方理解:
             * 查询的时候就需要给一个标准（规范） -》 根据规范（这个规范我们可以先简单理解为查询的条件）进行查询
             *      CriteriaQuery：
             *      CriteriaBuilder：
             * @param root 查询哪个表（定位到表和字段-> 用于拿到表中的字段）
             *            可以查询和操作的实体的根
             *             Root接口：代表Criteria查询的根对象，Criteria查询的查询根定义了实体类型，能为将来导航获得想要的结果，它与SQL查询中的FROM子句类似
             *             Root<Employee> 相当于 from Employee
             *             Root<Product> 相当于  from Product
             * @param criteriaQuery  查询哪些字段，排序是什么(主要是把多个查询的条件连系起来)
             * @param criteriaBuilder 字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
             *                      主要判断关系（和这个字段是相等，大于，小于，like等）
             * @return Predicate（Expression）：单独每一条查询条件的详细描述 整个 where xxx=xx and yyy=yy ...
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                /**
                 * 拼接条件
                 *  like
                 *  notLike
                 *  equal       =
                 *  notEqual    !=
                 *  between
                 *  notBetween
                 *  in
                 *  notIn
                 *  lt      lessthan        <
                 *  le      lessthanequal   <=
                 *  gt      greaterthan     >
                 *  ge      greaterthanequal >=
                 */
                Predicate predicate = criteriaBuilder.like(usernameAttr, "%admin10%");// username like '%admin10%'
                return predicate;
            }
        };
        List<Employee> list = employeeRepository.findAll(specification);
        list.forEach(e -> System.out.println(e));
    }

    /**
     * 多条件高级查询
     * @throws Exception
     */
    @Test
    public void testFindByUserNameAndEmail() throws Exception{
        //Specification表示一个规范或者标准【高级查询的条件】
        Specification<Employee> specification = new Specification<Employee>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(usernameAttr, "%admin%");// username like '%admin10%'
                //获取字段
                Path emailAttr = root.get("email");
                Predicate predicate2 = criteriaBuilder.like(emailAttr, "%2%");// email like '%admin10%'
                //将多个条件用AND连接起来
                criteriaQuery = criteriaQuery.where(predicate1,predicate2);
                return criteriaQuery.getRestriction();
            }
        };
        List<Employee> list = employeeRepository.findAll(specification);
        list.forEach(e -> System.out.println(e));
    }

    /**
     * 高级查询+分页
     * @throws Exception
     */
    @Test
    public void testFindByUserNameAndEmailByPage() throws Exception{
        Specification<Employee> specification = new Specification<Employee>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(usernameAttr, "%admin%");// username like '%admin10%'
                //获取字段
                Path emailAttr = root.get("email");
                Predicate predicate2 = criteriaBuilder.like(emailAttr, "%2%");// email like '%admin10%'
                //将多个条件用AND连接起来
                criteriaQuery = criteriaQuery.where(predicate1,predicate2);
                return criteriaQuery.getRestriction();
            }
        };

        Pageable pageable = new PageRequest(0,10);
        Page<Employee> page = employeeRepository.findAll(specification, pageable);
        page.forEach(e -> System.out.println(e));
    }
    /**
     * 高级查询+排序
     * @throws Exception
     */
    @Test
    public void testFindByUserNameAndEmailWithOrder() throws Exception{
        Specification<Employee> specification = new Specification<Employee>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(usernameAttr, "%admin%");// username like '%admin10%'
                //获取字段
                Path emailAttr = root.get("email");
                Predicate predicate2 = criteriaBuilder.like(emailAttr, "%2%");// email like '%admin10%'
                //将多个条件用AND连接起来
                criteriaQuery = criteriaQuery.where(predicate1,predicate2);
                return criteriaQuery.getRestriction();
            }
        };
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"age"));
        List<Employee> list = employeeRepository.findAll(specification, sort);
        list.forEach(e -> System.out.println(e));
    }
    /**
     * 高级查询+分页+排序
     * @throws Exception
     */
    @Test
    public void testFindByUserNameAndEmailByPageWithOrder() throws Exception{
        Specification<Employee> specification = new Specification<Employee>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(usernameAttr, "%admin%");// username like '%admin10%'
                //获取字段
                Path emailAttr = root.get("email");
                Predicate predicate2 = criteriaBuilder.like(emailAttr, "%2%");// email like '%admin10%'
                //将多个条件用AND连接起来
                criteriaQuery = criteriaQuery.where(predicate1,predicate2);
                return criteriaQuery.getRestriction();
            }
        };
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        Pageable pageable = new PageRequest(0,10,sort);
        Page<Employee> page = employeeRepository.findAll(specification, pageable);
        page.forEach(e -> System.out.println(e));
    }
    /**
     * 高级查询+查询数量
     * @throws Exception
     */
    @Test
    public void testFindCountByUserNameAndEmail() throws Exception{
        Specification<Employee> specification = new Specification<Employee>(){
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取字段
                Path usernameAttr = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(usernameAttr, "%admin%");// username like '%admin10%'
                //获取字段
                Path emailAttr = root.get("email");
                Predicate predicate2 = criteriaBuilder.like(emailAttr, "%2%");// email like '%admin10%'
                //将多个条件用AND连接起来
                criteriaQuery = criteriaQuery.where(predicate1,predicate2);
                return criteriaQuery.getRestriction();
            }
        };
        long count = employeeRepository.count(specification);
        System.out.println(count);
    }


}
