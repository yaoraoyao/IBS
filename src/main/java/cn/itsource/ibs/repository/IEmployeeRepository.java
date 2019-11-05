package cn.itsource.ibs.repository;

import cn.itsource.ibs.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 我们要使用SpringDataJPA，就只需要自定义一个接口继承JpaRepository接口即可
 *  两个泛型：
 *      T  domain实体类名称
 *      ID domain实体类主键字段的类型
 */
public interface IEmployeeRepository extends IBaseRepository<Employee,Long> {


//    /**
//     * SpringDataJPA支持通过方法名称来指定查询条件，方法名称就必须遵守以下规范：
//     *  1）方法名称必须以findBy或者findAllBy开头【findBy表示查询一行，findAllBy查询多行】
//     *  2）findBy或者findAllBy后面必须紧跟domain实体类的属性名称
//     *  3）domain实体类的属性名称后面紧跟以下单词
//     *      Equals          =
//     *      NotEquals       !=
//     *      LessThan        <
//     *      LessThanEqual   <=
//     *      GreaterThan     >
//     *      GreaterThanEqual >=
//     *      In              in
//     *      NotIn           not in
//     *      Like            like
//     *      NotLike         not like
//     *      BetweenAnd      between...and...
//     *      NotBetweenAnd   not between...and...
//     *      IsNull          is null
//     *      IsNotNull       is not null
//     *  4）方法参数列表一定要与第3）点中的内容匹配【有几个条件就需要几个参数】
//     *  5）如果省略第3）点中的单词不写，则默认是Equals
//     *  缺陷：
//     *      1）方法名称太长，不灵活
//     * @param username
//     * @return
//     */
//    List<Employee> findAllByUsernameLike(String username);
//
    Employee findByUsername(String username);
//
//    List<Employee> findAllByUsernameLikeAndEmailLikeAndAge(String username, String email, Integer age);
//
//    /**
//     * 推荐使用@Query注解方式实现条件查询
//     * @param username
//     * @return
//     */
//    @Query("select e from Employee e where e.username like ?1")
//    List<Employee> findAllByUsername(String username);
//
//    @Query("select e from Employee e where e.username like ?1 and e.email like ?2 and e.age=?3")
//    List<Employee> findAllByUsernameAndEmailAndAge(String username, String email, Integer age);
//
//    /**
//     * nativeQuery 为true的时候表示value值是普通SQL语句，默认是false
//     * @param username
//     * @param email
//     * @param age
//     * @return
//     */
//    @Query(value = "select e.* from employee e where e.username like ?1 and e.email like ?2 and e.age=?3",nativeQuery = true)
//    List<Employee> findAllByUsernameAndEmailAndAge2(String username, String email, Integer age);


}
