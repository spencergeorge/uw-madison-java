import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyFavoritePlaces_original 
{
	private static final String WELCOME_BANNER = "My Favorite Places ";
	private static final String EXIT_BANNER = "Thank you for using My Favorite"
			+ " Places ";
	private static final String HORIZONTAL_REFERENCE 
			= "--------------------------";
	private static final String NO_PLACES_MESSAGE = "No places loaded";
	private static final String MENU_NO_PLACES = "A)dd R)read Q)uit : ";
	private static final String MENU_PLACES = "A)dd S)how E)dit D)elete "
			+ "C)urrent R)ead W)rite Q)uit : ";
	private static Scanner consoleScanner = new Scanner(System.in);
	private static Calendar calendar = new GregorianCalendar();
	private static PlaceList placeList = new PlaceList();
	private static int userInputInt;
	private static char userInputChar;
	private static String userInputString;
	private static String name;
	private static String address;
	private static String latitude;
	private static String longitude;
	private static String dataDir;
	private static String databaseExtension = ".mfp";
	
	
	public static String askName()
	{
		System.out.print("Enter the name: ");
		return consoleScanner.nextLine();
	}
	
	public static String askAddress()
	{
		System.out.print("Enter the address: ");
		return consoleScanner.nextLine();
	}
		
	public static void add()
	{
		//name = askName(); 
		//address = askAddress();
		placeList.add(askName(), askAddress());
	}
	
	public static void show()
	{
		System.out.print("Enter number of place to Show: ");
		userInputInt = consoleScanner.nextInt() - 1;
		System.out.println(placeList.get(userInputInt).getName());
		System.out.println(placeList.get(userInputInt).getAddress());
		System.out.println(placeList.get(userInputInt).getLatitude());
		System.out.println(placeList.get(userInputInt).getLongitude());
	}
	
	public static void edit()
	{
		System.out.print("Enter number of place to Edit: ");
		userInputInt = consoleScanner.nextInt() - 1;
		consoleScanner.nextLine(); 
		
		placeList.get(userInputInt).setName(askName());
		
		placeList.get(userInputInt).setName(askAddress());
	}
	
	public static void delete()
	{
		System.out.print("Enter number of place to Delete: ");
		//userInputInt = consoleScanner.nextInt() - 1;
		placeList.remove(consoleScanner.nextInt() - 1);
	}
	
	public static void current()
	{
		
	}
	
	public static void read()
	{
		System.out.println("Available Files: ");

		File folder = new File(dataDir);
		for ( File file : folder.listFiles()) 
		{
		  if ( file.getName().endsWith( databaseExtension)) 
		  {
			  System.out.println("\t" + file.getName());
		  } 
		}
		
		System.out.print("Enter filename: ");
		userInputString = consoleScanner.nextLine();
		
		Scanner fileScanner;
		String [] lineArray;
		
		try 
		{
			fileScanner = new Scanner(new File(dataDir 
					+ "/" + userInputString));
			
			while(fileScanner.hasNextLine())
			{
				lineArray = fileScanner.nextLine().split(";");
				
				if(placeList.size() == 0)
					placeList.add(lineArray[0], lineArray[1], 
							lineArray[2], lineArray[3]);
				else
				{
					if(placeList.exists(lineArray[0], lineArray[1]))
					{
						System.out.println("Place " + lineArray[0] 
								+ " already in list.");
					}
					else
						placeList.add(lineArray[0], lineArray[1],
								lineArray[2], lineArray[3]);
				}
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Unable to read from file: " + userInputString);
		}
	}
	
	public static void write() throws FileNotFoundException
	{
		System.out.println("Current Files: ");

		File folder = new File(dataDir);
		for (File file: folder.listFiles()) 
		{
		  if ( file.getName().endsWith( databaseExtension)) 
		  {
			  System.out.println("\t" + file.getName());
		  } 
		}
		
		System.out.print("Enter filename: ");
		
		try(PrintWriter output = new PrintWriter(dataDir 
				+ "/" + consoleScanner.nextLine());)
		{
			for(int i = 0; i < placeList.size(); i++)
			{
				output.println(placeList.get(i).getName() + ";" 
						+ placeList.get(i).getAddress() + ";"
						+ placeList.get(i).getLongitude() + ";"
						+ placeList.get(i).getLatitude() + ";");
			}
		} 
	}
	
	public static void interactive() throws FileNotFoundException
	{
		dataDir = "data";
		boolean quit = false;
		
		do
		{
			System.out.println("\n" + WELCOME_BANNER 
					+ calendar.get(Calendar.YEAR));
			System.out.println(HORIZONTAL_REFERENCE);
			
			if(placeList.size() == 0)
			{
				System.out.println(NO_PLACES_MESSAGE);
				System.out.println(HORIZONTAL_REFERENCE);
				System.out.print(MENU_NO_PLACES);
			}
			else
			{
				for(int i = 0; i < placeList.size(); i++)
				{
					System.out.println( i + 1 + ") " 
							+ placeList.get(i).getName());
				}
				
				System.out.println(HORIZONTAL_REFERENCE);
				System.out.print(MENU_PLACES);
			}
			
			userInputChar = consoleScanner.next().toUpperCase().charAt(0);
			consoleScanner.nextLine();
		
			switch(userInputChar)
			{
				case 'A': add();
					break;
				case 'S': show();
					break;
				case 'E': edit();
					break;
				case 'D': delete();
					break;
				case 'C': current();
					break;
				case 'R': read();
					break;
				case 'W': write();
					break;
				case 'Q': 
					quit = true;
					System.out.println(EXIT_BANNER 
							+ calendar.get(Calendar.YEAR) + "!");
					break;
				default:
					break;
			}
		}while(quit == false);
				
		consoleScanner.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		interactive();
	}
	
}

