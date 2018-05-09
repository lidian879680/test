package com.jcgroup.lidian.spring.controller;

import com.jcgroup.lidian.spring.pojo.UserBean;
import com.jcgroup.lidian.spring.pojo.req.UserIdReq;
import com.jcgroup.lidian.spring.pojo.req.UserReq;
import com.jcgroup.lidian.spring.pojo.vo.JsonResVo;
import com.jcgroup.lidian.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-10
 * Time: 9:20
 */
@RestController
@RequestMapping(value = "test/user", consumes = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonResVo addUser(@RequestBody UserReq userReq){

        UserBean userBean1 = new UserBean();
        userBean1.setBirthday(new Date(userReq.getBirthday()));
        userBean1.setNickName(userReq.getNickName());
        userBean1.setPwd(userReq.getPwd());
        userBean1.setSex(userReq.getSex());
        userBean1.setUid(userReq.getUid());

        String id = userService.addUser(userBean1);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);

        return JsonResVo.buildSuccess(result);
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public JsonResVo findUser(@RequestBody UserIdReq req){
        String uid = req.getUid();
        UserBean result = userService.getUser(uid);
        return JsonResVo.buildSuccess(result);
    }

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }
}
