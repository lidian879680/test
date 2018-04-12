package com.jcgroup.lidian.spring.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-10
 * Time: 17:17
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.jcgroup.lidian.spring")
@PropertySource("classpath:jdbc.properties")
public class WebConfig extends WebMvcConfigurerAdapter {
    //这里面没做任何修改，因为。。。毕竟前后端分离，感觉没有需要设置的了
}
