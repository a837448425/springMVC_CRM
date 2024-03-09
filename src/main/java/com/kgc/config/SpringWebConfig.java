package com.kgc.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.swing.*;

/**
 * @Author: luo
 * @date: 2024/3/8 17:56
 * @Description:
 * @Version: 1.0
 */
@Configuration //配置类
@ComponentScan("com.kgc.controller") //扫描controller
@EnableWebMvc //启动SpringMVC
public class SpringWebConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("classpath:/WEB-INF/js");
    }

    @Bean
    //配置thymeleaf视图解析器
    public ViewResolver ViewResolver(ISpringTemplateEngine templateEngine){
        //创建
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        //设置模板引擎
        thymeleafViewResolver.setTemplateEngine(templateEngine);
        //设置编码
        thymeleafViewResolver.setCharacterEncoding("utf-8");
        //返回
        return thymeleafViewResolver;
    }

    @Bean
    //创建模板引擎
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
        //创建
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        //设置支持SpringEL表达式
        templateEngine.setEnableSpringELCompiler(true);
        //设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }

    @Bean
    //创建模板解析器
    public ITemplateResolver templateResolver(ApplicationContext applicationContext){
        //创建
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        //设置应用上下文
        templateResolver.setApplicationContext(applicationContext);
        //设置模板类型
        templateResolver.setTemplateMode(TemplateMode.HTML);
        //设置前缀
        templateResolver.setPrefix("/WEB-INF/");
        templateResolver.setSuffix(".html");
        //设置编码
        templateResolver.setCharacterEncoding("utf-8");
        //设置是否缓存
        templateResolver.setCacheable(false);
        //返回
        return templateResolver;
    }
}
