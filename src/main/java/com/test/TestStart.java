package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.TagFactory;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import com.test.beetl.BeetlTagFactory;
import com.test.beetl.Pagination;


@ServletComponentScan
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class TestStart extends SpringBootServletInitializer {

    //extends SpringBootServletInitializer
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestStart.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TestStart.class, args);
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration(Pagination paginationTag) {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
        	// WebAppResourceLoader 配置root路径是关键
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource("classpath:/WEB-INF/html").getFile().getPath());
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //分页 标签库
        TagFactory tagFactory= new BeetlTagFactory(paginationTag);
        Map<String ,TagFactory> tags=new HashMap<String ,TagFactory>();
        tags.put("pagination", tagFactory);
        beetlGroupUtilConfiguration.setTagFactorys(tags);

        //读取配置文件信息
        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl/beetl.properties"));
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}