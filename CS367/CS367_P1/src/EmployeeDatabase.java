//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 1
// Files:            EmployeDatabase.java,InteractiveDBTester.java,Employee.java
// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides the ability to manage multiple employee objects and mostly acts as
 * a wrapper class for an ArrayList. 
 * 
 * @author Spencer
 *
 */
public class EmployeeDatabase 
{
	/** Master list of all employee objects */
	private ArrayList<Employee> empArrayList;
	
	/**
	 * Constructs new EmployeeDatabase by creating new arrayList.
	 */
	public EmployeeDatabase()
	{
		empArrayList = new ArrayList<>();
	}
	
	/**
	 * Add an employee with the given username e to the end of the database. 
	 * If an employee with username e is already in the database, just return.
	 * 
	 * @param e The name of the employee
	 */
	public void addEmployee(String e)
	{
		if(e == null)
			throw new java.lang.IllegalArgumentException();
		
		if(!this.containsEmployee(e))
			empArrayList.add(new Employee(e));
	}
	
	/**
	 * Add the given destination d to the wish list for employee e in the 
	 * database. If employee e is not in the database throw a 
	 * java.lang.IllegalArgumentException. If d is already in the wish list for
	 * employee e, just return.
	 *  
	 * @param e The name of the employee to add destination to
	 * @param d The destination
	 */
	public void addDestination(String e, String d)
	{
		if(e == null || d == null)
			throw new java.lang.IllegalArgumentException();
		
		Employee tempEmp;	
		List<String> tempEmpWishList;
		Iterator<Employee> empIterator = empArrayList.iterator();
		while (empIterator.hasNext()) 
		{
			tempEmp = empIterator.next();
			
			if(e.equals(tempEmp.getUsername()))
			{
				tempEmpWishList = tempEmp.getWishlist();
				if(!tempEmpWishList.contains(d))
					tempEmpWishList.add(d);
				
				return;
			}
		}
		
		throw new java.lang.IllegalArgumentException();
	}
	
	/**
	 * Return true if and only if employee e is in the database.
	 * 
	 * @param e The name of the employee
	 * @return boolean; true if employee is in database, false if not.
	 */
	public boolean containsEmployee(String e)
	{
		if(e == null)
			throw new java.lang.IllegalArgumentException();
		
		for(Employee emp: empArrayList)
		{
			if(emp.getUsername().equals(e))
				return true;
		}

		return false;
	}
	
	/**
	 * Return true if and only if destination d appears in at least one 
	 * employee's wish list in the database.
	 * 
	 * @param d The destination
	 * @return boolean; true if destination(d) is in a wish list of any employee
	 */
	public boolean containsDestination(String d)
	{
		if(d == null)
			throw new java.lang.IllegalArgumentException(); 
		
		for(Employee emp: empArrayList)
		{
			if(emp.getWishlist().contains(d))
				return true;
		}

		return false;
	}
	
	/**
	 * Returns true if and only if destination d is in the wish list for 
	 * employee e. If employee e is not in the database, return false.
	 * 
	 * @param e The name of the employee 
	 * @param d The destination
	 * @return boolean; true if employee(e) contains the destination(d), false
	 * otherwise.
	 */
	boolean hasDestination(String e, String d)
	{
		if(e == null || d == null)
			throw new java.lang.IllegalArgumentException();
		
		if(!this.containsEmployee(e))
			return false;
		
		boolean tempBool = false;
		
		for(Employee emp: empArrayList)
		{
			if(emp.getUsername().equals(e))
				tempBool = emp.getWishlist().contains(d);
		}
		
		return tempBool;
	}
	
	/**
	 * Return the list of employees who have destination d in their wish list. 
	 * If destination d is not in the database, return a null list.
	 * 
	 * @param d The destination
	 * @return
	 */
	public List<String> getEmployees(String d)
	{
		if(d == null)
			throw new java.lang.IllegalArgumentException();
		
		List<String> tempEmpList = new ArrayList<String>();
		
		for(Employee emp: empArrayList)
		{
			if(emp.getWishlist().contains(d))
				tempEmpList.add(emp.getUsername());
		}
		
		return tempEmpList;
	}
	
	/**
	 * Return the wish list for the employee e. 
	 * If an employee e is not in the database, return null.
	 * 
	 * @param e The employee.
	 * @return A list of destinations for an employee.
	 */
	public List<String> getDestinations(String e)
	{
		if(e == null)
			throw new java.lang.IllegalArgumentException();
		
		for(Employee emp: empArrayList)
		{
			if(e.equals(emp.getUsername()))
				return emp.getWishlist();
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return Return an Iterator over the Employee objects in the database. 
	 * The employees should be returned in the order they were added to the 
	 * database (resulting from the order in which they are in the text file).
	 */
	public Iterator<Employee> iterator()
	{
		return empArrayList.iterator();
	}
	
	/**
	 * Remove employee e from the database. If employee e is not in the 
	 * database, return false; otherwise (i.e., the removal is successful)
	 * return true.
	 * 
	 * @param e The employee to be removed
	 * @return boolean; true if successfully removed, false if not.
	 */
	public boolean removeEmployee(String e)
	{
		if(e == null)
			throw new java.lang.IllegalArgumentException();
		
		if(this.containsEmployee(e))
			return false;
		
		for(int i = 0; i < empArrayList.size(); i++)
		{
			if(e.equals(empArrayList.get(i).getUsername()))
			{
				empArrayList.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Remove destination d from the database, i.e., remove destination d 
	 * from every wish list in which it appears. If destination d is not in 
	 * the database, return false; otherwise (i.e., the removal is successful)
	 * return true.
	 * 
	 * @param d The destination
	 * @return boolean; true if successfully removed, false if not
	 */
	public boolean removeDestination(String d)
	{
		if(d == null)
			throw new java.lang.IllegalArgumentException();
		
		boolean wasRemoved = false;
		for(Employee e: empArrayList)
		{
			if(e.getWishlist().contains(d))
			{
				List<String> eWishList = e.getWishlist();
				eWishList.remove(d);
				wasRemoved = true;
			}
		}
		
		return wasRemoved;
	}
	
	/**
	 *
	 * @return 	Return the number of employees in this database.
	 */
	public int size()
	{
		return empArrayList.size();
	}
	
	/**
	 * Overrides toString to print employee database as each employees name
	 * on a line.
	 * 
	 * @return string of employees names in the database on seperate lines.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(Employee e: empArrayList)
		{
			sb.append(e.getUsername());
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
