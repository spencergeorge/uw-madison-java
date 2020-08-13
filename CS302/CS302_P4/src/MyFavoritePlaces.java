import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
//import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyFavoritePlaces 
{
	private PlaceList placeList = new PlaceList();
	private Scanner consoleScanner = new Scanner(System.in);
	private final String DATA_DIRECTORY;
	private static final String FILE_EXTENSION = ".mfp";
	private final String WELCOME_BANNER;
	private final String EXIT_BANNER;
	private final String HORIZONTAL_REFERENCE = "--------------------------";
	private final String NO_PLACES_MESSAGE;
	private final String MENU_NO_PLACES;
	private final String MENU_PLACES;
	
	public MyFavoritePlaces(String dataDirectory)
	{
		this.DATA_DIRECTORY = dataDirectory;
		
		int calendarYear = new GregorianCalendar().get(Calendar.YEAR);

		WELCOME_BANNER = "My Favorite Places " + calendarYear;
		EXIT_BANNER = "Thank you for using My Favorite Places " + calendarYear;
		NO_PLACES_MESSAGE = "No places loaded";
		MENU_NO_PLACES = "A)dd R)read Q)uit : ";
		MENU_PLACES = "A)dd S)how E)dit D)elete C)urrent R)ead W)rite Q)uit : ";
	}
	
	private String askName(boolean rename)
	{
		if(rename == false)
			System.out.print("Enter the name: ");
		else
			System.out.print("Enter the new name: ");
		
		return consoleScanner.nextLine();
	}
	
	private String askAddress(boolean readdress)
	{
		if(readdress == false)
			System.out.print("Enter the address: ");
		else
			System.out.print("Enter the new address: ");
		
		return consoleScanner.nextLine();
	}
	
	private void printError(String error)
	{
		System.out.println(error);
		System.out.print("Press enter to continue.");
		consoleScanner.nextLine();
	}
	
	private String [] findAddress(String address) 
	{
		String jsonResponse;
		String [] addressArray = new String[3];
		
		try 
		{
			jsonResponse = Geocoding.find(address);
			GResponse gResponse = GeocodeResponse.parse(jsonResponse);
			if(gResponse.hasAddress())
			{
				addressArray[0] = gResponse.getFormattedAddress();
				addressArray[1] = String.valueOf(gResponse.getLatitude());
				addressArray[2] = String.valueOf(gResponse.getLongitude());
				
				return addressArray;
			}
			
			printError("Place not found using address: " + address);
			
			return null;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void add()
	{
		String name = askName(false);
		String address = askAddress(false);
		
		String [] addressFound = findAddress(address);
		if(addressFound != null)
		{
			Place place;
			try 
			{
				place = new Place(name, addressFound[0],
						addressFound[1], addressFound[2]);
				
				placeList.add(place);
			} 
			catch (UnsupportedEncodingException e) 
			{
				printError("Encoding address with UTF8 failed. "
						+ "URL will be null");
			}
		}
	}
	
	public void show()
	{
		System.out.print("Enter number of place to Show: ");
		int index = consoleScanner.nextInt() - 1;
		consoleScanner.nextLine(); // Clear buffer from nextInt
		
		System.out.println(placeList.get(index).toString());
		
		try 
		{
			Geocoding.openBrowser(placeList.get(index).getUrl());
		}
		catch (IOException | URISyntaxException e) 
		{
			printError("Cannot open browser.");
		}
		finally
		{
			System.out.print("Press Enter to continue.");
			consoleScanner.nextLine();	
		}
	}
	
	public void edit()
	{
		System.out.print("Enter number of place to Edit: ");
		int index = consoleScanner.nextInt() - 1;
		consoleScanner.nextLine();
		
		System.out.println("Current name: " + placeList.get(index).getName());
		placeList.get(index).setName(askName(true));
		
		System.out.println("Current address: " 
				+ placeList.get(index).getAddress());
		try 
		{
			placeList.get(index).setAddress(askAddress(true));
		}
		catch (UnsupportedEncodingException e) 
		{
			printError("Encoding address with UTF8 failed. URL will be null");
		}
	}
	
	public void delete()
	{
		System.out.print("Enter number of place to Delete: ");
		placeList.remove(consoleScanner.nextInt() - 1);
		consoleScanner.nextLine();	
		
		System.out.print("Press Enter to continue.");
		consoleScanner.nextLine();	
	}
	
	public void current()
	{
		System.out.print("Enter number of place to be Current place: ");
		Place.currentPlace = placeList.get(consoleScanner.nextInt() - 1);
		consoleScanner.nextLine();
		
		System.out.println(Place.currentPlace.getName() 
				+ " set as Current place.");
		
		System.out.print("Press Enter to continue.");
		consoleScanner.nextLine();
	}
	
	public void read() 
	{
		System.out.println("Available Files: ");
		
		File folder = new File(DATA_DIRECTORY);
		for (File file: folder.listFiles()) 
		{
		  if (file.getName().endsWith(FILE_EXTENSION)) 
		  {
			  System.out.println("\t" + file.getName());
		  } 
		}
		
		System.out.print("Enter filename: ");
		String fileName = consoleScanner.nextLine();
		
		Scanner fileScanner;
		String [] lineArray;
		
		try 
		{
			fileScanner = new Scanner(new File(DATA_DIRECTORY 
					+ "/" + fileName));
						
			while(fileScanner.hasNextLine())
			{
				lineArray = fileScanner.nextLine().split(";");
				
				if(placeList.size() == 0)
				{
					Place place;
					try 
					{
						place = new Place(lineArray[0], lineArray[1],
								lineArray[2], lineArray[3]);
						
						placeList.add(place);
					}
					catch (UnsupportedEncodingException e) 
					{
						printError("Encoding address with UTF8 failed. "
								+ "URL will be null");
					}
				}
				else
				{
					if(placeList.exists(lineArray[0], lineArray[1]))
					{
						System.out.println("Place " + lineArray[0] 
								+ " already in list.");
					} 
					else
					{
						Place place;
						try 
						{
							place = new Place(lineArray[0], lineArray[1],
									lineArray[2], lineArray[3]);
							
							placeList.add(place);
						}
						catch (UnsupportedEncodingException e) 
						{
							printError("Encoding address with UTF8 failed. "
									+ "URL will be null");
						}
					}
				}
			}
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("Unable to read from file: " + fileName);
		}
	}
	
	public void write()
	{
		System.out.println("Current Files: ");

		File folder = new File(DATA_DIRECTORY);
		for (File file: folder.listFiles()) 
		{
		  if (file.getName().endsWith(FILE_EXTENSION)) 
		    System.out.println("\t" + file.getName());
		}
		
		System.out.print("Enter filename: ");
		String fileName = consoleScanner.nextLine();
		
		try(PrintWriter output = new PrintWriter(DATA_DIRECTORY 
				+ "/" + fileName);)
		{
			for(int i = 0; i < placeList.size(); i++)
			{
				output.println(placeList.get(i).toLine());
			}
		} 
		catch(FileNotFoundException e) 
		{
			System.out.println("Unable to write to file: " + fileName); 
		} 
	}
	
	public void interactive()
	{
		boolean quit = false;
		
		do
		{
			System.out.println("\n" + WELCOME_BANNER);
			System.out.println(HORIZONTAL_REFERENCE);
			
			if(placeList.size() == 0)
			{
				System.out.println(NO_PLACES_MESSAGE);
				System.out.println(HORIZONTAL_REFERENCE);
				System.out.print(MENU_NO_PLACES);
			}
			else if(Place.currentPlace != null)
			{
				placeList.sort();
				
				System.out.println("distance from " 
						+ Place.currentPlace.getName());
				
				for(int i = 0; i < placeList.size(); i++)
				{
					System.out.print( i + 1 + ") " 
							+ placeList.get(i).getName()
							+ " ("); 
					System.out.printf("%.2f miles)\n", 
							placeList.get(i).getDistance());
				}
				
				System.out.println(HORIZONTAL_REFERENCE);
				System.out.print(MENU_PLACES);
			}
			else
			{
				placeList.sort();
				for(int i = 0; i < placeList.size(); i++)
				{
					System.out.println( i + 1 + ") " 
							+ placeList.get(i).getName());
				}
				
				System.out.println(HORIZONTAL_REFERENCE);
				System.out.print(MENU_PLACES);
			}
			
			String userInput = consoleScanner.nextLine();
			if(userInput.length() == 1)
			{	
				char menuChoice = userInput.toUpperCase().charAt(0);
				
				if(placeList.size() == 0)
				{
					switch(menuChoice)
					{
						case 'A': add();
							break;
						case 'R': read();
							break;
						case 'Q': 
							quit = true;
							System.out.println(EXIT_BANNER + "!");
							break;
						default: break;
					}
				}
				else
				{
					switch(menuChoice)
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
							System.out.println(EXIT_BANNER + "!");
							break;
						default: break;
					}
				}
			}
		}while(quit == false);
				
		consoleScanner.close();
	}
	
	public static void main(String[] args) 
	{
		String dataDirectory = "data";
		if(args.length > 0)
			dataDirectory = args[0];
		
		/*
		File folder = new File(dataDirectory);
		if(!folder.exists())
		{
			System.out.println("Data directory: '" + folder.getAbsolutePath() 
					+ "' does not exist.");
			System.exit(1);
		}*/
		
		MyFavoritePlaces myFavoritePlaces 
			= new MyFavoritePlaces(dataDirectory);
		
		myFavoritePlaces.interactive();
	}
}
