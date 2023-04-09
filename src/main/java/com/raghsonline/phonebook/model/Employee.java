package com.raghsonline.phonebook.model;

public class Employee 
{
	
	private String name;
	private int id;
	
	public Employee()
	{
		System.out.println("Employee() instantiated");
		this.name = "Raghavan";
		this.id =  2351;
	}
	
	public Employee(String name, int id)
	{
		System.out.println("Employee(name, id) instantiated");
		System.out.println("Params : name - " + name + ", id - " + id);
		
		this.name = name;
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + "]";
	}
}
