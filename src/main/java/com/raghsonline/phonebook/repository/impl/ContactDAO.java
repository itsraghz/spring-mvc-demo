package com.raghsonline.phonebook.repository.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.repository.DAO;

@Repository
public class ContactDAO implements DAO<Contact> 
{
	Logger logger = Logger.getLogger(ContactDAO.class);
	
	/**
	 * <p>
	 * An injectable dependency of the <tt>JdbcTemplate</tt>, 
	 * will be <tt>@Autowired</tt> by the Spring Container.
	 * </p>
	 */
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @param jdbcTemplate
	 */
	public ContactDAO(JdbcTemplate jdbcTemplate) 
	{
		super();
		logger.info("ContactDAO() instantiated, jdbcTemplate=" + jdbcTemplate);
		this.setJdbcTemplate(jdbcTemplate);
	}
	

	@Override
	public int create(Contact t) 
	{	
		logger.info("create(Contact) invoked, contact :" + t);
		
		//String sql = "INSERT INTO Contact....";
		//jdbcTemplate.execute("");
		
		return 0;
	}

	@Override
	public Optional<Contact> getById(int id) 
	{
		return null;
	}

	@Override
	public void update(Contact t) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Contact t) {
		// TODO Auto-generated method stub
		return false;
	}


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
