package com.jcgroup.lidian.spring.services.imp;

import com.jcgroup.lidian.spring.mapper.UserDao;
import com.jcgroup.lidian.spring.pojo.UserBean;
import com.jcgroup.lidian.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-10
 * Time: 10:24
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public String addUser(UserBean userBean) {
        //
        Long rows = userDao.insert(userBean);
        return rows.toString();
    }

    @Override
    public UserBean getUser(String uid) {
        return userDao.getByUid(uid);
    }
}
