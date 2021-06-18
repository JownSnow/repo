package com.changgou.system.service;

import com.changgou.goods.pojo.Admin;

public interface AdminService {

    /**
     * 登录验证密码
     * @param admin
     * @return
     */
    boolean login(Admin admin);


    //添加
    public void add(Admin admin);
}
