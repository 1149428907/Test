/**   
 * Copyright © 2017 上海华禽网络科技有限公司. All rights reserved.
 * 
 * @Title: MyBatisConfiguration.java 
 * @Prject: mht-userSoa
 * @Package: com.jingsha 
 * @Description: TODO
 * @author: 张天德 
 * @date: 2017年4月5日 上午10:49:10 
 * @version: V1.0   
 */
package com.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @ClassName: MyBatisConfiguration
 * @Description: TODO
 * @author: 张天德
 * @date: 2017年4月5日 上午10:49:10
 */
@Configuration
@MapperScan("com.jingsha.dao")
@EnableTransactionManagement
public class MyBatisConfiguration {
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Bean(destroyMethod = "close")
	@Primary
	public DataSource primaryDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setPoolName("springHikariCP");
		hikariDataSource.setDriverClassName(driverClassName);
		hikariDataSource.setJdbcUrl(url);
		hikariDataSource.setUsername(username);
		hikariDataSource.setPassword(password);
		return hikariDataSource;
	}

	@Bean
	public DataSourceTransactionManager makeDataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource);
		manager.setDefaultTimeout(30);
		return manager;
	}

	@Bean
	public SqlSessionFactory makeSqlSessionFactoryBean(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

		sessionFactory.setDataSource(dataSource);

		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("offsetAsPageNum", "false");
		properties.setProperty("rowBoundsWithCount", "false");
		properties.setProperty("pageSizeZero", "true");

		properties.setProperty("reasonable", "false");
		properties.setProperty("supportMethodsArguments", "false");
		properties.setProperty("returnPageInfo", "none");
		pageHelper.setProperties(properties);
		sessionFactory.setPlugins(new Interceptor[] { pageHelper });

		// 添加xml目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/*/*.xml"));
		
		sessionFactory.setTypeAliasesPackage("com.test.page");
		

		return sessionFactory.getObject();
	}

	@Bean(name = "transactionInterceptor")
	public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		// 事物管理器
		transactionInterceptor.setTransactionManager(platformTransactionManager);
		Properties transactionAttributes = new Properties();

		// 新增
		transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
		transactionAttributes.setProperty("add*", "PROPAGATION_REQUIRED,-Throwable");
		transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED,-Throwable");
		// 修改
		transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
		transactionAttributes.setProperty("edit*", "PROPAGATION_REQUIRED,-Throwable");
		// 删除
		transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
		transactionAttributes.setProperty("del*", "PROPAGATION_REQUIRED,-Throwable");
		// 查询
		transactionAttributes.setProperty("select*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		transactionAttributes.setProperty("list*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,-Throwable,readOnly");
		transactionInterceptor.setTransactionAttributes(transactionAttributes);
		return transactionInterceptor;
	}

	// 代理到ServiceImpl的Bean
	@Bean
	public BeanNameAutoProxyCreator transactionAutoProxy() {
		BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
		transactionAutoProxy.setProxyTargetClass(true);
		transactionAutoProxy.setBeanNames("*Service");
		transactionAutoProxy.setInterceptorNames("transactionInterceptor");
		return transactionAutoProxy;
	}
}
