package cn.itsource.ibs.service.impl;

import cn.itsource.ibs.domain.Permission;
import cn.itsource.ibs.repository.IPermissionRepository;
import cn.itsource.ibs.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (Permission)表业务逻辑层实现类
 *
 * @author 吴昌勇
 * @since 2019-08-19 14:02:06
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    @Transactional
    public void delete(String ids) {
        permissionRepository.delete(ids);
    }
}