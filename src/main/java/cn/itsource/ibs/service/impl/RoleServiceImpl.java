package cn.itsource.ibs.service.impl;

import cn.itsource.ibs.domain.Role;
import cn.itsource.ibs.repository.IRoleRepository;
import cn.itsource.ibs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Role)表业务逻辑层实现类
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:03
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long> implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    @Transactional
    public void delete(String ids) {
        roleRepository.delete(ids);
    }
}