package cn.itsource.ibs.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department extends BaseDomain{

  private String name;

  public Department() {
  }

  public Department(Long id, String name) {
    this.setId(id);
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
