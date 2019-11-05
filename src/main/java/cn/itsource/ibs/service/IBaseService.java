package cn.itsource.ibs.service;


import cn.itsource.ibs.query.BaseQuery;
import cn.itsource.ibs.utils.MyPage;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, ID extends Serializable> {

    void save(T t);

    void delete(ID id);


    T findOne(ID id);

    MyPage<T> findAll(BaseQuery baseQuery);

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    List<T> find(BaseQuery baseQuery);

    /**
     * 根据jpql与对应的参数拿到数据
     * @param jpql
     * @param values
     * @return
     */
    List<T> findByJpql(String jpql,Object... values);

}
