package com.stu.service;

import com.stu.dao.StuDao;

/**
 *学生表的业务层
 */

public class StuService {

    StuDao stuDao = new StuDao();

    //判断是否登陆成功
    public boolean checkLogin(String account,String pass) {
        //调用dao层去查询数据库
        if (stuDao.checkLogin(account,pass)!=null)
            return true;
        return false;
    }
}
