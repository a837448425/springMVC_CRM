package com.kgc.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.SneakyThrows;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @Author: luo
 * @date: 2024/3/8 18:09
 * @Description:
 * @Version: 1.0
 */
@Configuration
@ComponentScan(value = "com.kgc")
@EnableTransactionManagement
@MapperScan("com.kgc.mapper")
@PropertySource("classpath:db.properties")
public class SpringConfig {

    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("${mysql.driver}");
        ds.setUrl("${mysql.url}");
        ds.setUsername("${mysql.username}");
        ds.setPassword("${mysql.password}");

        ds.setMinIdle(30);
        ds.setMaxActive(50);
        ds.setMaxWait(3000);
        ds.setValidationQuery("select 1");

        return ds;
    }

    @Bean
    @SneakyThrows
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration ctf=new org.apache.ibatis.session.Configuration();
        ctf.setMapUnderscoreToCamelCase(true);//开启驼峰命名
        ctf.setLazyLoadingEnabled(true);
        ctf.setAggressiveLazyLoading(false);
        ctf.setLogImpl(Log4j2Impl.class);
        ctf.getTypeAliasRegistry().registerAliases("{com.kgc.pojo,com.kgc.entity}");

        bean.setConfiguration(ctf);

        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/kgc/mapper/*.xml"));

        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
