//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 1
// Files:            EmployeDatabase.java,InteractiveDBTester.java,Employee.java
// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The InteractiveDBTester class is responsible for a console based user
 * interface for managing a list of employees and their wish lists of 
 * destinations. It displays a menu of options to interact and modify the list.
 * 
 * @author Spencer
 *
 */
public class InteractiveDBTester 
{
    public static void main(String[] args) 
    {
    	Iterator<Employee> empDBiterator;
    	
    	// Main Method Step 1
    	if(args.length != 1)
    	{
    		System.out.println(
    				"Please provide input file as command-line argument");
    		System.exit(1);
    	}
    	
    	// Main Method Steps 2 & 3
    	Scanner fileScanner;
    	String [] empAsLine;
    	EmployeeDatabase empDatabase = new EmployeeDatabase();
				
		try 
		{
			fileScanner = new Scanner(new File(args[0]));

			while(fileScanner.hasNextLine())
			{
				empAsLine = fileScanner.nextLine().toLowerCase().split(",");
				
				empDatabase.addEmployee(empAsLine[0]);
				
				for(int i = 1; i < empAsLine.length; i++)
				{
					empDatabase.addDestination(empAsLine[0], 
							empAsLine[i]);
				}
			}
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("Error: Cannot access input file" );
			System.exit(2);
		}
    	
    	
        Scanner stdin = new Scanner(System.in);  // for reading console input
        printOptions();
        boolean done = false;
        while (!done) 
        {
            System.out.print("Enter option ( dfhisqr ): ");
            String input = stdin.nextLine();
            input = input.toLowerCase();  // convert input to lower case

            // only do something if the user enters at least one character
            if (input.length() > 0) 
            {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) 
                {
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                }

                switch (choice) 
                {
                
                /*
                 * If destination is not in the database, display "destination 
                 * not found". Otherwise, discontinue destination (i.e., remove 
                 * the destination from all the wish lists in which it appears)
                 * and display "destination discontinued".
                 */
                case 'd':
                	if(empDatabase.containsDestination(remainder))
                	{
                		empDatabase.removeDestination(remainder);
                		System.out.println("destination removed");
                	}
                	else
                	{
                		System.out.println("destination not found");
                	}
                	
                    break;
                /*
                 * If employee is not in the database, display "employee not 
                 * found". Otherwise, find employee and display the employee 
                 * (on one line) in the format: 
                 * employee:destination1,destination2,destination3
                 */
                case 'f':
                	empDBiterator = empDatabase.iterator();
                	
                	while(empDBiterator.hasNext())
                	{
                		Employee tempEmp = empDBiterator.next();
	                	if(tempEmp.getUsername().equals(remainder))
	                	{
	                		StringBuilder sb = new StringBuilder();
	                		sb.append(remainder);
	                		sb.append(':');
	                		                		
	                		List<String> eDestinations = 
	                				empDatabase.getDestinations(remainder);
	                		
	                		for(int i = 0; i < eDestinations.size(); i++)
	                		{
	                			sb.append(eDestinations.get(i));
	                			
	                			if(i + 1 != eDestinations.size())
	            					sb.append(',');
	                		}
	                		System.out.println(sb);
	                		break;
	                	}
	                	else
	                	{
	                		if(!empDBiterator.hasNext())
	                			System.out.println("Employee not found");
	                	}
                	}	
                    break;
                /*
                 * Provide help by displaying the list of command options. 
                 * This command has already been implemented for you.
                 */
                case 'h': 
                    printOptions();
                    break;
                
                /*
                 * Display information about this database by doing the 
                 * following:
				 * 
				 *  1
				 *  Display on a line: "Employees: integer, Destinations: 
				 *  integer"
				 *  This is the number of employees followed by the total number
				 *  of unique destinations.
				 *
				 *  2
				 *  Display on a line: "# of destinations/employee: most 
				 *  integer, least integer, average decimal fraction"
				 *  where most is the largest number of destinations that any
				 *  employee has in their wish list, least is the fewest, and 
				 *  average is arithmetic mean number of destinations per 
				 *  employee rounded to the nearest tenth (e.g., 1.2 or 0.7).
				 *
				 *  3
				 *  Display on a line: "# of employees/destination: most 
				 *  integer, least integer, average decimal fraction"
				 *  where most is the largest number of employee wish lists in
				 *  which any destination appears, least is the fewest, and
				 *  average is arithmetic mean number of employees per 
				 *  destination rounded to the nearest tenth (e.g., 1.2 or 0.7).
				 *
				 *  4
				 *  Display on a line: 
				 *  "Most popular destination: destination(s) [integer]"
				 *  This is the destination that shows up in the greatest
				 *   number of wish lists followed by the number of wish lists containing that destination in square brackets. If there is a tie for most popular destination, display all those tying in the order they appear in the database separated by commas.
                 */
                case 'i':
                    // *** Add code to implement this option ***
                	empDBiterator = empDatabase.iterator();
                	List<String> uniqueDest = new ArrayList<String>();
                	List<String> nonUniqueDest = new ArrayList<String>();
    	        	List<String> tempDest;
                	
                	ArrayList<Integer> wishListLengths = 
                			new ArrayList<Integer>();
                	
                	// 2, 3 calculations
                	while(empDBiterator.hasNext())
                	{
                		Employee tempEmp = empDBiterator.next();
                		tempDest = tempEmp.getWishlist();
                		

                		// 2
                		wishListLengths.add(tempDest.size());
                		
                		for(String dest: tempDest)
                		{
                			if(!uniqueDest.contains(dest))
                				uniqueDest.add(dest);
                		}

                		// 3
                		nonUniqueDest.addAll(tempDest);

                	}
                	
                	// 1 Output
                	System.out.println("Employees: " + empDatabase.size() + 
                			" Destinations: " + uniqueDest.size());
                	
                	// 2 Output
                	Integer mostDest = Collections.max(wishListLengths);
                	Integer leastDest = Collections.min(wishListLengths);
                	
                	double destPerEmpMean = (double) nonUniqueDest.size() / 
                			(double) wishListLengths.size();
                	destPerEmpMean = (double) Math.round(destPerEmpMean * 10d) 
                			/ 10d;
                	
                	System.out.println("# of destination/Employee: most " +
                			mostDest + 
                			", least " + 
                			leastDest + 
                			", average " + 
                			destPerEmpMean);
                	
                	// 3 Output
                	HashMap<String, Integer> hm = new HashMap<String, Integer>();
                	for(String dest: nonUniqueDest)
                	{
                		if(hm.containsKey(dest))
            			{
                			hm.put(dest, hm.get(dest) + 1);
            			}
            			else
            			{
            				hm.put(dest, 1);
            			}
            		}
                	
                	Integer maxDest = 0;
                	Integer minDest = null;
                	Integer destSum = 0;
                	for (Integer list : hm.values() )
                	{
                		if(maxDest < list)
                			maxDest = list;
                		
                		if(minDest == null || minDest > list)
                			minDest = list;
                		
                		destSum += list;
                	}
                	
                	double empPerDestMean = (double) nonUniqueDest.size() / 
                			(double) uniqueDest.size();
                	empPerDestMean = (double) Math.round(empPerDestMean * 10d)
                			/ 10;
                	
                	System.out.println("# of Employees/destination: most " +
                			maxDest + 
                			", least " + 
                			minDest + 
                			", average " + 
                			empPerDestMean);
                	
                	//4 most popular
                	System.out.print("Most popular destination: ");
                	int tempI = 0;
                	
                	for (String dest : uniqueDest)
                	{
                		if(hm.get(dest).equals(maxDest))
                		{
                			if(tempI > 0)
                				System.out.print(',');
                			
                			System.out.print(dest);
                			tempI++;
            			}
            		}
                	
                	System.out.print(" [" + maxDest + "]\n");
                	
                    break;
                    
                /*
                 * 	If destination is not in the database, display 
                 * "destination not found". Otherwise, search for destination
                 *  and display the destination along with the employees who
                 *  have that destination in their wish list (on one line) in
                 *  the format:
				 *  destination:employee1,employee2,employee3
                 */
                case 's':
                    // *** Add code to implement this option ***
                	if(empDatabase.containsDestination(remainder))
                	{
                		StringBuilder sb = new StringBuilder();
                		sb.append(remainder + ":");
                		
                		List<String> destinationList = 
                				empDatabase.getEmployees(remainder);
                		
                		Iterator<String> destListIT = 
                				destinationList.iterator();
                		
                		while(destListIT.hasNext())
                		{
                			sb.append(destListIT.next());
                			if(destListIT.hasNext())
                				sb.append(',');
                		}
                		System.out.println(sb);
                	}
                	else
                		System.out.println("destination not found");
                	
                    break;

                case 'q':
                    done = true;
                    System.out.println("quit");
                    break;

                /*
                 * 	If employee is not in the database, display "employee
                 *  not found". Otherwise, remove employee and display 
                 *  "employee removed".
                 */
                case 'r':
                	if(empDatabase.containsEmployee(remainder))
                	{
                		if(empDatabase.removeEmployee(remainder))
                			System.out.println("Employee removed");
            		}
                	else
                		System.out.println("employee not found");
                    
                	break;

                default:  // ignore any unknown commands
                    break;
                }
            }
        }
        
        stdin.close();
    }

    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() 
    {
         System.out.println("d <destination> - discontinue the given <destination>");
         System.out.println("f <Employee> - find the given <Employee>");
         System.out.println("h - display this help menu");
         System.out.println("i - display information about this Employee database");
         System.out.println("s <destination> - search for the given <destination>");
         System.out.println("q - quit");
         System.out.println("r <Employee> - remove the given <Employee>");

    }
}
