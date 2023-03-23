package com.raghsonline.phonebook.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//Added for the Test class to run, without the Controller since it would miss the 'registration-servlet.xml'
//as the XML file being declared under the /WEB-INF folder, will take effect only with the Web Version.
@ComponentScan("com.raghsonline")
public class AppConfig 
{
	
	@Bean
	public DataSource dataSource()
	{
		//For the time being, to execute the 1st level of Test Case!
		return new DriverManagerDataSource();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(dataSource());
	}
}
