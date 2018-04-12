package com.jcgroup.lidian.spring.mapper;

import com.jcgroup.lidian.spring.pojo.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-09
 * Time: 18:53
 */
@Repository
public interface UserDao {
    Long insert(UserBean userBean);

    UserBean getByUid(String uid);

    Long deleteByUid(String uid);
}
