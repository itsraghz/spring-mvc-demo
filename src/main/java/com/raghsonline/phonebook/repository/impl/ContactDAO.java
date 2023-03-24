package com.raghsonline.phonebook.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
		//dropTable();
		createTable();// (IF NOT EXISTS clause is added in the DDL Statement)
        getCount();
	}
	
	public void reset()
	{
        //truncateTable();
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
                    + "     ContactNo VARCHAR(10) UNIQUE NOT NULL,\n"
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
	public Optional<Contact> getByContactNo(String contactNo) 
	{
		logger.info("getByContactNo() invoked, contactNo=" + contactNo);
		
		Optional<Contact> optionalContact = Optional.empty();
		
		long count = getCount();
		logger.info("Count :: " + count);
		
		if(count <= 0)
		{
			logger.info("count is <= 0, does NOT make any sense to query the table further");
			return optionalContact;
		} 
		
		optionalContact = getByContactNoUsingPS(contactNo);
		//optionalContact = getByContactNoUsingStmt(contactNo);
		
		logger.info("OptionalContact :: " + optionalContact);
		
		return optionalContact;
	}
	
	public Optional<Contact> getByContactNoUsingStmt(String contactNo) 
	{
		logger.info("getByContactNoUsingSmt() invoked, contactNo=" + contactNo);
		
		/* Uses a Statement , appending the value to the Stmt directly */
		String sql = "SELECT * FROM CONTACT WHERE ContactNo = " + contactNo;
		
		Contact contact = null;

		/* 
		 * We can use the simplest version of queryForObject() method, 
		 * as there is NO need of binding any arguments
		 */
		//contact = jdbcTemplate.queryForObject(sql, Contact.class);
		
		contact = jdbcTemplate.query(sql, rs -> {
			logger.info("resultset : " + rs);
			
			if(null==rs) return null;
			
			rs.next();
			
			Contact c = new Contact();
			
			c.setFirstName(rs.getString("FirstName"));
			c.setLastName(rs.getString("LastName"));
			c.setDob(rs.getString("Dob"));
			c.setContactNo(rs.getString("ContactNo"));
			c.setEmail(rs.getString("Email"));
			c.setNotes(rs.getString("Notes"));
			c.setTag(rs.getString("Tag"));
			
			logger.info("contact object c about to be returned : " + c);
			
			return c;
		}); 
				
		logger.info("contact obj from jdbcTemplate : " + contact);
		
		return (null!=contact) ? Optional.of(contact) : Optional.empty();
	}
	
	public Optional<Contact> getByContactNoUsingPS(String contactNo) 
	{
		logger.info("getByContactNoUsingPS() invoked, contactNo=" + contactNo);
		
		/* Uses a PreparedStatement */
		String sql = "SELECT * FROM CONTACT WHERE ContactNo = ?";
		
		Contact contact = jdbcTemplate.queryForObject(sql,new Object[] {contactNo}, 
				(rs, rowNum) -> {
					return contactRowMapper(rs);
				} );
		
		logger.info("contact obj from jdbcTemplate : " + contact);
		
		return (null!=contact) ? Optional.of(contact) : Optional.empty();
	}

	private Contact contactRowMapper(ResultSet rs) throws SQLException 
	{
		Contact c = new Contact();
		
		c.setFirstName(rs.getString("FirstName"));
		c.setLastName(rs.getString("LastName"));
		c.setDob(rs.getString("Dob"));
		c.setContactNo(rs.getString("ContactNo"));
		c.setEmail(rs.getString("Email"));
		c.setNotes(rs.getString("Notes"));
		c.setTag(rs.getString("Tag"));
		
		return c;
	}

	@Override
	public Optional<Contact> getById(int id) 
	{
		logger.info("getById() invoked, id=" + id);
		
		String sql = "SELECT * FROM CONTACT WHERE ";
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

	@Override
	public List<Contact> getAll() 
	{
		logger.info("getAll() invoked");
		
		String sql = "SELECT * FROM CONTACT";
		
		List<Contact> contactList = new ArrayList<>();
		
		contactList =  jdbcTemplate.query(sql, (rs, rowNum) -> {
			return contactRowMapper(rs);
		});
		
		logger.info("contactList :: " + contactList);
		
		return contactList;
	}

}
