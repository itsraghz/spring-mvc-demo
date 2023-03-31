package com.raghsonline.phonebook.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.raghsonline.phonebook.model.UserLogin;
import com.raghsonline.phonebook.repository.LoginDAO;

@Repository
public class LoginDAOImpl implements LoginDAO<UserLogin>
{
	Logger logger = Logger.getLogger(ContactDAO.class);
	
	/**
	 * <p>
	 * An injectable dependency of the <tt>JdbcTemplate</tt>, 
	 * will be <tt>@Autowired</tt> by the Spring Container.
	 * </p>
	 */
	private JdbcTemplate jdbcTemplate;
	
	public LoginDAOImpl(JdbcTemplate jdbcTemplate) 
	{
		super();
		logger.info("LoginDAOImpl() instantiated, jdbcTemplate=" + jdbcTemplate);
		this.setJdbcTemplate(jdbcTemplate);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public long create(UserLogin l) 
	{
		logger.info("create(Contact) invoked, contact :" + l);
		
		String sql = "INSERT INTO Login"
				+ "(UserName, Password) "
				+ "VALUES (?, ?)";
		
		long generatedKey = 0;
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int rowsAffected = jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] {"Id"});
				
				ps.setString(1, l.getUserName());
				ps.setString(2, l.getPassword());
				return ps;
			}, keyHolder);
		
		logger.info("rowsAffected : " + rowsAffected);
		
		generatedKey = keyHolder.getKey().longValue();
		
		logger.info("generatedKey :: " + generatedKey);
		
		return generatedKey;
	}
	
	private UserLogin loginRowMapper(ResultSet rs) throws SQLException 
	{
		UserLogin pl = new UserLogin();
		
		pl.setId(rs.getInt("Id"));
		pl.setUserName(rs.getString("UserName"));
		pl.setPassword(rs.getString("Password"));
		
		
		return pl;
	}
	
	@Override
	public Optional<UserLogin> verifyUser(String userName, String password) 
	{
		logger.info("verifyUser() - Invoked");
		 
		String sql = "SELECT * FROM Login WHERE UserName = ? AND Password = ?";
		
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {userName,password}, 
				(rs, rowNum) -> Optional.of(loginRowMapper(rs)));
		}catch(EmptyResultDataAccessException empDataAccessException) {
			logger.info("No matching records available in the database for the userName and password provided - " + userName + password);
			return Optional.empty();
		}
		
	/*	UserLogin login = jdbcTemplate.query(sql, new Object[] {userName,password}, rs -> {
			rs.next();
			return loginRowMapper(rs);
		});
		
		logger.info("login User :: " + login);
		
		Optional<UserLogin> userLogin = null!=login? Optional.of(login) : Optional.empty();
		
		return userLogin; */
	}

	@Override
	public Optional<UserLogin> getByUserName(String userName) 
	{
		logger.info("verifyUser() - Invoked");
		 
		String sql = "SELECT * FROM LOGIN WHERE UserName = ?";
		
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] {userName}, 
				(rs, rowNum) -> Optional.of(loginRowMapper(rs)));
		}catch(EmptyResultDataAccessException empDataAccessException) {
			logger.info("No matching records available in the database for the userName - " + userName);
			return Optional.empty();
		}
	}
}
