package cn.itsource.ibs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee extends BaseDomain{

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String headImage;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    private Department department;

    //一个员工可能属于多个角色
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="employee_role",
        joinColumns = {@JoinColumn(name="employee_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")})
    @JsonIgnore //只是转化为JSON字符串的时候忽略当前字段
    private List<Role> roles = new ArrayList<Role>();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + this.getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", headImage='" + headImage + '\'' +
                ", age=" + age +
                '}';
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
