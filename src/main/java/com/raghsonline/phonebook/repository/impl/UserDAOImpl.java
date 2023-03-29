package com.raghsonline.phonebook.repository.impl;

import com.raghsonline.phonebook.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.raghsonline.phonebook.repository.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO<User>
{
	Logger logger = Logger.getLogger(UserDAO.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
     
	public  UserDAOImpl(JdbcTemplate jdbcTemplate) 
	{
		super();
		logger.info("ContactDAO() instantiated, jdbcTemplate=" + jdbcTemplate);
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
    public long getCount()
    {
        logger.info("getCount() invoked ");
        
        long count = 0;
        
        logger.info("getCount() invoked");
        
        String sql = "SELECT COUNT(*) from Contact";
        
        count = jdbcTemplate.queryForObject(sql, Long.class);
        
        logger.info("Count : " + count);
        
        return count;
    }
		
	@Override
	public Optional<User> verifyUser(String userName,String password) {

		logger.info("verifyUser() invoked, userName=" + userName);

		Optional<User> optionalUser = Optional.empty();

		long count = getCount();
		logger.info("Count :: " + count);

		if(count <= 0)
		{
			logger.info("count is <= 0, does NOT make any sense to query the table further");
			return optionalUser;
		} 

		optionalUser = getUserByUsernameAndPassword(userName, password);


		logger.info("optionalUser :: " + optionalUser);

		return optionalUser;
	}
	
	public Optional<User> getUserByUsernameAndPassword(String userName,String password) 
	{
		logger.info("getUserByUsernameAndPassword() invoked UserName" + userName);
		
	 String sql = "SELECT * FROM USER WHERE USERNAME= ? AND PASSWORD= ?";
	
	 try {
			return jdbcTemplate.queryForObject(sql, new Object[] {userName,password}, 
				(rs, rowNum) -> Optional.of(userRowMapper(rs)));
		}catch(EmptyResultDataAccessException empDataAccessException) {
			logger.info("No matching records available in the database for the userName and password provided - " + userName);
			return Optional.empty();
		}
		}	
	
	 private User userRowMapper(ResultSet rs) throws SQLException 
		{
			User u = new User();

			u.setId(rs.getInt("Id"));
			u.setUserName(rs.getString("UserName"));
			u.setPassword(rs.getString("Password"));

			logger.info("User obtained from database is " +u);
			return u;
		}
	}


