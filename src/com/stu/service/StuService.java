package com.stu.service;

import com.stu.dao.StuDao;
import com.stu.model.StuModel;

/**
 *学生表的业务层
 */

public class StuService {

    StuDao stuDao = new StuDao();

    //判断是否登陆成功
    public int checkLogin(String account,String pass) {
        //调用dao层去查询数据库
        //返回0表示账户登陆失败,返回1表示登陆成功,返回2表示账户被冻结
        StuModel stu = stuDao.getStu(account);
        if (!stu.getStu_password().equals(pass)) {
            if (stu.getStu_status()<=3)
                stu.setStu_status(stu.getStu_status()+1);
            else return 2;
            stuDao.updateStu(stu);
        }
        if (stu.getStu_password().equals(pass)) {
            if (stu.getStu_status()<=3) {
                stu.setStu_status(0);
                stuDao.updateStu(stu);
                return 1;
            }
            else return 2;
        }
        return 0;
    }
}
