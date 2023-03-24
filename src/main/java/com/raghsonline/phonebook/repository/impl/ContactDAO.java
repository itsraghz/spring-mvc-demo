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
	
	public void cleanup()
	{
		dropTable();
		createTable();
        getCount();
	}
	
	public void reset()
	{
        truncateTable();
        getCount();
	}
	
	public void dropTable()
    {
        logger.info("dropTable() invoked");
        
        String sql = "DROP TABLE Contact";
        
        jdbcTemplate.execute(sql);
        
        logger.info("An attempt to drop the Table Contact was completed");
    }
	
	public void createTable()
    {
        logger.info("createTable() invoked");
        
        String sql = "CREATE TABLE IF NOT EXISTS Contact\n"
                    + "(\n"
                    + "     Id INT auto_increment primary key,\n"
                    + "     FirstName VARCHAR(30) NOT NULL,\n"
                    + "     LastName VARCHAR(30) NOT NULL,\n"
                    + "     DOB VARCHAR(20),\n"
                    + "     ContactNo VARCHAR(10) NOT NULL,\n"
                    + "     EMAIL VARCHAR(30),\n"
                    + "     NOTES VARCHAR(50),\n"
                    + "     TAG VARCHAR(50)\n"
                    + ")";
        
        jdbcTemplate.execute(sql);
        
        logger.info("An attempt to create the Table Contact was completed");
    }
	
	public void truncateTable()
    {
        logger.info("truncateTable() invoked");
        
        String sql = "TRUNCATE Contact";
        
        jdbcTemplate.execute(sql);
        
        logger.info("An attempt to truncate the Table Contact was completed");
    }
	
    public int getCount()
    {
        logger.info("getCount() invoked ");
        
        int count = 0;
        
        logger.info("getCount() invoked");
        
        String sql = "SELECT COUNT(*) from Contact";
        
        count = jdbcTemplate.queryForObject(sql, Integer.class);
        
        logger.info("Count : " + count);
        
        return count;
    }
	

	@Override
	public int create(Contact t) 
	{	
		logger.info("create(Contact) invoked, contact :" + t);
		
		String sql = "INSERT INTO Contact"
				+ "(FirstName, LastName, Dob, ContactNo, Email, Notes, Tag) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		int rowsAffected = jdbcTemplate.update(sql, 
				t.getFirstName(), t.getLastName(), t.getDob(), 
				t.getContactNo(), t.getEmail(), t.getNotes(), t.getTag());
		
		logger.info("rowsAffected : " + rowsAffected);
		
		return rowsAffected;
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
