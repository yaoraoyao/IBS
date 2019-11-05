package cn.itsource.ibs.query;

import cn.itsource.ibs.domain.Role;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
/**
 * (Role)高级查询实体类
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:03
 */
public class RoleQuery extends BaseQuery<Role> {
    
    private String name; 
    
    @Override
    public Specification getSpecification() {
        return Specifications.<Role>and()
                //第一个参数是布尔值：如果为true的时候才去拼接当前条件，为false就直接跳过
                .like(StringUtils.isNotBlank(name),"name", "%"+name+"%")
                //如果要做其他高级查询条件拼接，就在这里继续写...
                .build();
    }

    @Override
    public Pageable getPageable() {
        Pageable pageable = new PageRequest(getJpaPageNo(), getPageSize(), getSort());
        return pageable;
    }

    @Override
    public Sort getSort() {
        Sort.Direction direction = Sort.Direction.ASC;
        if(getOrderType().equalsIgnoreCase("desc")){
            direction = Sort.Direction.DESC;
        }
        Sort sort = new Sort(new Sort.Order(direction, getOrderProperty()));
        return sort;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}