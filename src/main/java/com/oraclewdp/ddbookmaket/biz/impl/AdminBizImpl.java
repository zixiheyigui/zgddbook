package com.oraclewdp.ddbookmaket.biz.impl;

import com.oraclewdp.ddbookmaket.biz.AdminBiz;
import com.oraclewdp.ddbookmaket.dao.AdminDao;
import com.oraclewdp.ddbookmaket.dao.impl.AdminDaoJdbcImpl;
import com.oraclewdp.ddbookmaket.model.Admin;

public class AdminBizImpl implements AdminBiz {

    @Override
    public boolean findNameAdmin(Admin admin) {
        AdminDao adminDao=new AdminDaoJdbcImpl();

        return adminDao.find(admin);
    }
}
