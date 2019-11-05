package cn.itsource.ibs.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (Role)实体类
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:03
 */
@Entity
@Table(name="role")
public class Role extends BaseDomain{
 
    private String name;
    private String sn;

    //一个角色有多个权限 千万不要使用级联保存角色与权限之间的关系数据 使用List不要使用Set，因为Set集合没有索引
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="role_permission",
        joinColumns = {@JoinColumn(name="role_id")},
        inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private List<Permission> permissions = new ArrayList<>();

    //一个角色拥有多个用户
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private List<Employee> employees = new ArrayList<Employee>();

    @Override
    public String toString() {
        return "Role{" +
                "id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

}