package cn.itsource.ibs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Permission)实体类
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:06
 */
@Entity
@Table(name="permission")
public class Permission extends BaseDomain{
 
    private String name;
    private String url;
    private String descs;
    private String sn;
    private Long menu_id;

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + this.getId() + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", descs='" + descs + '\'' +
                ", sn='" + sn + '\'' +
                ", menu_id=" + menu_id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
    
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }
}