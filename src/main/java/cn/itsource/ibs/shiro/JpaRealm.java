package cn.itsource.ibs.shiro;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.service.IEmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class JpaRealm extends AuthorizingRealm {

    @Autowired
    private IEmployeeService employeeService;

    public JpaRealm(){
        System.out.println("JpaRealm创建对象了。。。。。。。。。。。。。。。。。。。。。。。。。。。");
    }

    @Override
    public String getName() {
        return "jpaRealm";
    }

    /**
     * 身份认证
     *  1）将前端表单提交过来的用户名和密码取出来
     *  2）通过端表单提交过来的用户名去查询数据库【查询到、没查询到】
     *  3）如果没查询到就直接返回null，当doGetAuthenticationInfo方法返回null的时候，就一定抛出UnknownAccountException异常
     *  4）如果第2）步查询到了数据，就创建一个SimpleAuthenticationInfo对象，然后返回
     *      第一个参数是通过用户名从数据库中查询出来的用户对象【Employee】
     *      第二个参数是前端表单提交过来的密码
     *      第三个参数是当前自定义Realm的名称
     *      只要doGetAuthenticationInfo方法返回一个不为null的AuthenticationInfo对象，Shiro内部就会自动去匹配密码，如果密码不匹配
     *      就抛出IncorrectCredentialsException异常
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //类型强转
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        //获取用户名【从前端表单中提交过来的用户名】
        String username = usernamePasswordToken.getUsername();
        //通过这个用户去查询数据库
        Employee employee = employeeService.findOne(username);
        if(null == employee){
            return null;
        }
        /**
         * 创建AuthenticationInfo对象
         * Object principal     主体对象【从数据库中查询到的Employee】
         * Object credentials   令牌、密码【从数据库中查询到的Employee对象身上的密码】
         * ByteSource credentialsSalt 盐值
         * String realmName     当前自定义realm的名称
         */
        ByteSource byteSource = new SimpleByteSource("cn.itsource");
        //ByteSource.Util.bytes("cn.itsource");
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(employee,employee.getPassword(),byteSource,getName());
        return authenticationInfo;
    }

    /**
     * 模拟一下：通过用户名去查询数据库，得到一行员工数据
     *  明天就要将这里改成真正的查询数据库
     * @param username
     * @return
     */
    private Employee loadEmployeeByUsername(String username) {
        if("admin".equals(username)){
            //这里模拟是从数据库中查询到的数据
            Employee employee = new Employee();
            employee.setUsername("admin");
            //admin 盐值为cn.itsource 加密10次之后 6006eba3724471f7e1b7945b10ddb1d7
            employee.setPassword("6006eba3724471f7e1b7945b10ddb1d7");
            return employee;
        }
        return null;
    }


    /**
     * 权限授权【一定是发生在身份认证成功之后】
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份认证成功之后的主体对象
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //获取用户id
        Long id = employee.getId();

        //通过用户id去查询该用户拥有哪些权限
        Set<String> permissions = this.loadPermissionsByUserId(id);
        //创建一个SimpleAuthorizationInfo对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //将通过用户id查询到的他拥有的那些权限授权给当前用户
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 模拟通过当前登录用户的id去查询它拥有哪些权限
     * @param id
     * @return
     */
    private Set<String> loadPermissionsByUserId(Long id) {
        Set<String> permissions = new HashSet<>();
        permissions.add("department:*");
        return permissions;
    }

}
