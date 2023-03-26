package com.raghsonline.phonebook.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//Added for the Test class to run, without the Controller since it would miss the 'registration-servlet.xml'
//as the XML file being declared under the /WEB-INF folder, will take effect only with the Web Version.
@ComponentScan("com.raghsonline")
@PropertySource("classpath:/jdbc.properties")
public class AppConfig 
{
	Logger logger = Logger.getLogger(AppConfig.class);
	
	@Autowired
	Environment env;
	
	public AppConfig()
	{
		logger.info("AppConfig() instantiated");
		logger.info("Autowired Environment : " + env);
	}
	
	@Bean
	public DataSource dataSource()
	{
		logger.info("dataSource() Bean has been invoked");
		
		//For the time being, to execute the 1st level of Test Case!
		String url = "jdbc:mysql://localhost:3306/Phonebook";
		String username = "root";
		String password = "root";
		
		DataSource dataSource = new DriverManagerDataSource(url, username, password);
		logger.info("dataSource : " + dataSource);
		
		return dataSource; 
	}
	
	//@Bean
	public DataSource dataSource1()
	{
		logger.info("dataSource1() Bean has been invoked");
		
		//For the time being, to execute the 1st level of Test Case!
		String url = env.getProperty("jdbc.url");
		String username = env.getProperty("jdbc.username");
		String password = env.getProperty("jdbc.password");
		
		logger.info(
				String.format(
						"jdbc properties from property file : url=%s, username=%s, password=%s", 
						url, username, password));
		
		DataSource dataSource = new DriverManagerDataSource(url, username, password);
		logger.info("dataSource : " + dataSource);
		
		return dataSource; 
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		logger.info("jdbcTemplate() Bean has been invoked");
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		
		logger.info("jdbcTemplate : " + jdbcTemplate);
		
		return jdbcTemplate;
	}
}
