package cn.itsource.ibs.service.impl;


import cn.itsource.ibs.query.BaseQuery;
import cn.itsource.ibs.repository.IBaseRepository;
import cn.itsource.ibs.service.IBaseService;
import cn.itsource.ibs.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Transactional
 *  写在类上面表示全局配置，当前类中的所有方法都使用这种方式来进行事务管理
 *  写在方法上面表示局部配置，局部配置会覆盖全局配置
 *  readOnly 表示是否只读，默认false【表示读写】
 *  propagation 表示事务传播机制
 *      Propagation.SUPPORTS[推荐]
 *          该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
 *      Propagation.REQUIRED[推荐]
 *          业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
 *      Propagation.REQUIRES_NEW
 *          不管是否存在事务，该方法总会为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
 *      Propagation.NEVER
 *          该方法绝对不能在事务范围内执行。如果在就抛异常。只有该方法没有关联到任何事务，才正常执行。
 * @param <T>
 * @param <ID>
 */
@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
public class BaseServiceImpl<T,ID extends Serializable> implements IBaseService<T, ID> {

    @Autowired
    private IBaseRepository<T,ID> baseRepository;

    @Override
    @Transactional
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        baseRepository.delete(id);
    }



    @Override
    public T findOne(ID id) {
        return (T)baseRepository.findOne(id);
    }

    @Override
    public MyPage<T> findAll(BaseQuery baseQuery) {
        Page<T> page = baseRepository.findPageByQuery(baseQuery);
        return new MyPage<T>(baseQuery.getPageNo(),baseQuery.getPageSize(),page.getTotalElements(),page.getContent());
    }

    /**
     * 查询所有数据[有可能有查询条件] 不分页
     * @param baseQuery
     * @return
     */
    @Override
    public List<T> find(BaseQuery baseQuery) {
        return baseRepository.findByQuery(baseQuery);
    }

    /**
     * 根据jpql与对应的参数拿到数据
     * @param jpql
     * @param values
     * @return
     */
    public List<T> findByJpql(String jpql,Object... values){
        return baseRepository.findByJpql(jpql, values);
    }
}
