package cn.itsource.test;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.repository.IEmployeeRepository;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class _03_Jpa_Spec_Wenhao_Test extends BaseTest {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception{
        /**
         * 文浩将这段代码封装了，封装成一个工具类调用静态方法方式【链式操作】，大大简化了生成Specification对象【高级查询条件】的代码
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
         */
        /**
         * 文浩将这段代码封装了，封装成一个工具类调用静态方法方式【链式操作】，大大简化了生成Specification对象【高级查询条件】的代码
         * eq
         * neq
         * lt
         * le
         * gt
         * ge
         * like
         * notLike
         * in
         * notIn
         * between
         * notBetween
         */
        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", "%admin%")
                .like("email","%2%")
                .build();
        List<Employee> list = employeeRepository.findAll(specification);
        list.forEach(e -> System.out.println(e));
    }




}
