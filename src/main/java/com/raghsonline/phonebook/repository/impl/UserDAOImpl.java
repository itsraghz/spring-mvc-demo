package com.raghsonline.phonebook.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.raghsonline.phonebook.model.User;

import com.raghsonline.phonebook.repository.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO<User> {
	
	Logger logger = Logger.getLogger(UserDAOImpl.class);
	

	/**
	 * <p>
	 * An injectable dependency of the <tt>JdbcTemplate</tt>, 
	 * will be <tt>@Autowired</tt> by the Spring Container.
	 * </p>
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param jdbcTemplate
	 */
	public UserDAOImpl(JdbcTemplate jdbcTemplate) 
	{
		super();
		logger.info("UserDAOImpl() instantiated, jdbcTemplate=" + jdbcTemplate);
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
    public long getCount()
    {
        logger.info("getCount() invoked ");
        
        long count = 0;
        
        logger.info("getCount() invoked");
        
        String sql = "SELECT COUNT(*) from USER";
        
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
		
		optionalUser = getUserUsingPS(userName, password);
	
		
		logger.info("optionalUser :: " + optionalUser);
		
		return optionalUser;
	}
	
	public Optional<User> getUserUsingPS(String userName,String password) 
	{
		logger.info("getUserUsingPS() invoked, userName=" + userName);
		
		/* Uses a PreparedStatement */
		String sql = "SELECT * FROM USER WHERE UserName = ? AND Password = ?";
		
	
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
