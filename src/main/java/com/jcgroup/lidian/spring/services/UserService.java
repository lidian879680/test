package com.jcgroup.lidian.spring.services;

import com.jcgroup.lidian.spring.pojo.UserBean;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-10
 * Time: 10:21
 */
public interface UserService {
    String addUser(UserBean userBean);

    UserBean getUser(String uid);
}
