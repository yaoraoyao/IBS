package cn.itsource.ibs.repository;

import cn.itsource.ibs.query.BaseQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T,ID> implements IBaseRepository<T,ID> {

    private final EntityManager entityManager;

    private Class<T> domainClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.domainClass = domainClass;
    }

    @Override
    public Page<T> findPageByQuery(BaseQuery baseQuery) {
        return this.findAll(baseQuery.getSpecification(), baseQuery.getPageable());
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return this.findAll(baseQuery.getSpecification());
    }

    @Override
    public List<T> findByJpql(String jpql, Object... values) {
        Query query = entityManager.createQuery(jpql);
        //设置参数
        if(values != null && values.length > 0){
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i+1, values[i]);
            }
        }
        return query.getResultList();
    }

    @Override
    public void delete(String ids) {
        for (String id : ids.split(",")) {
            this.delete((ID)Long.valueOf(id));
        }
    }
}
