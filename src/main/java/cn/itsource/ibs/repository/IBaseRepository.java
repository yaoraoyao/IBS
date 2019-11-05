package cn.itsource.ibs.repository;

import cn.itsource.ibs.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @NoRepositoryBean表示不生成动态代理类，也不创建bean对象
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 根据Query拿到分页对象(分页)
     * @param baseQuery
     * @return
     */
    Page<T> findPageByQuery(BaseQuery baseQuery);

    /**
     * 根据Query拿到对应的所有数据(不分页)
     * @param baseQuery
     * @return
     */
    List<T> findByQuery(BaseQuery baseQuery);

    /**
     * 根据jpql与对应的参数拿到数据
     * @param jpql
     * @param values
     * @return
     */
    List<T> findByJpql(String jpql,Object... values);

    /**
     * 一次性删除多个数据
     * @param ids
     */
    void delete(String ids);
}
