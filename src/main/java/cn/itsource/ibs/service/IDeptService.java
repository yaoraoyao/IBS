package cn.itsource.ibs.service;

import cn.itsource.ibs.domain.Dept;

/**
 * (Dept)表数据库访问层
 *
 * @author 吴昌勇
 * @since 2019-08-16 14:54:16
 */
public interface IDeptService extends IBaseService<Dept, Long>{
    //公共的方法全部抽取到IBaseService父接口中，这里呢可以扩展其他特殊业务功能方法
    /**
     * 一次性删除多个数据
     * @param ids
     */
    void delete(String ids);
}