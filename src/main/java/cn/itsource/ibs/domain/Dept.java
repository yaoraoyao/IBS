package cn.itsource.ibs.domain;

import javax.persistence.*;
/**
 * (Dept)实体类
 *
 * @author 吴昌勇
 * @since 2019-08-16 14:30:17
 */
@Entity
@Table(name="dept")
public class Dept extends BaseDomain{
 
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}