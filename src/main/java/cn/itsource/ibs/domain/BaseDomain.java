package cn.itsource.ibs.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @MappedSuperclass专门用来抽取domain实体类的父类
 *  有这个注解就相当于子类就拥有这个类中的字段
 */
@MappedSuperclass
public class BaseDomain {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
