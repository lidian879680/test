package com.jcgroup.lidian.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-08
 * Time: 9:53
 */
@Configuration
@MapperScan("com.jcgroup.lidian.spring.mapper")
public class DBConfig {

    @Value("${datasource.general-mysql.url}")
    private String url;

    @Value("${datasource.general-mysql.username}")
    private String userName;

    @Value("${datasource.general-mysql.password}")
    private String password;

    @Value("${datasource.general-mysql.driver-class-name}")
    private String driverClassName;

//    @Value("${datasource.general-mysql.time-between-eviction-runs-millis}")
//    private Long timeBetweenEvictionRunsMillis;
    /**
     * 这个配置主要用于定时检测链接是否有效，无效则关闭，定时多长根据timeBetweenEvictionRunsMillis
     */
//    @Value("${datasource.general-mysql.test-while-idle}")
//    private Boolean testWhileIdle;

    /**
     * 配置数据源
     * @return
     */
    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
//        dataSource.setTestWhileIdle(testWhileIdle);
//        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        return dataSource;
    }

    /**
     * 获取sqlsessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        String path = "classpath*:mybatis/*.xml";
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(path));
        return sqlSessionFactoryBean.getObject();
    }
}
