package cn.itsource.ibs.service;

import cn.itsource.ibs.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee, Long>{
    //公共的方法全部抽取到IBaseService父接口中，这里呢可以扩展其他特殊业务功能方法
    /**
     * 一次性删除多个数据
     * @param ids
     */
    void delete(String ids);

    /**
     * 通过用户名查询一个员工对象
     * @param username
     * @return
     */
    Employee findOne(String username);
}
