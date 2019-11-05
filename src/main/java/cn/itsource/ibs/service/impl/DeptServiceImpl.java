package cn.itsource.ibs.service.impl;

import cn.itsource.ibs.domain.Dept;
import cn.itsource.ibs.repository.IDeptRepository;
import cn.itsource.ibs.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Dept)表业务逻辑层实现类
 *
 * @author 吴昌勇
 * @since 2019-08-16 14:59:15
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept,Long> implements IDeptService {

    @Autowired
    private IDeptRepository deptRepository;

    @Override
    @Transactional
    public void delete(String ids) {
        deptRepository.delete(ids);
    }
}