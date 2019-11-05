package cn.itsource.ibs.service.impl;

import cn.itsource.ibs.domain.Department;
import cn.itsource.ibs.repository.IDepartmentRepository;
import cn.itsource.ibs.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    @Transactional
    public void delete(String ids) {
        departmentRepository.delete(ids);
    }
}
