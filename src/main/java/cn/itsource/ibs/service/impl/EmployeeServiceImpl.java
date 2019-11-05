package cn.itsource.ibs.service.impl;

import cn.itsource.ibs.domain.Employee;
import cn.itsource.ibs.repository.IEmployeeRepository;
import cn.itsource.ibs.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void delete(String ids) {
        employeeRepository.delete(ids);
    }


    /**
     * 通过用户名查询一个员工对象
     * @param username
     * @return
     */
    public Employee findOne(String username){
        return employeeRepository.findByUsername(username);
    }
}
