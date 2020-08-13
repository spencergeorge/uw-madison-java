//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Programming Assignment 2
// Files:            DblListNode.java,EmptyLoopException.java,Image.java
//					 ImageLoopEditor.java, LinkedLoop.java
//					 LinkedLoopIterator.java, LoopADT.java
// Semester:         CS 367 Fall 2016
//
// Author:           Spencer George
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The ImageLoopEditer class is responsible for a console or command file 
 * interface for managing a loop of images. It displays images or their 
 * properties and provides a menu of options to interact and modify the loop.
 * <p>
 * The class creates a new loop object, to hold a list of images, then calls
 * the execute method via interactive()(a console based interface) or via
 * commands provided by the command line argument.
 * 
 * @author Spencer George
 */
public class ImageLoopEditor 
{
	/** Single instance of scanner for all input */
	private static Scanner inputScanner; //= new Scanner(System.in);

	/** Loop of images as working loop list */
	private static LinkedLoop<Image> loop = new LinkedLoop<Image>();
	
	/** Image directory location */
	private static final String IMAGES_DIR = "images";
	
	/** Message displayed if list is empty */
	private static final String EMPTY_LIST_MESSAGE = "no images";
	
	/**
	 * A helper method for the class. printHelpMenu() prints a menu of options
	 * that a user can use to interact with the image loop. Each menu option
	 * corresponds to a separate method within the class.
	 */
	private static void printHelpMenu()
	{
		StringBuilder sb = new StringBuilder();

		// Line 1
		sb.append("s (save)");
		sb.append("\t");
		sb.append("l (load)");
		sb.append("\t");
		sb.append("d (display)");
		sb.append("\t\t");
		sb.append("p (picture)");
		sb.append("\n");
		
		// Line 2
		sb.append("f (forward)");
		sb.append("\t");
		sb.append("b (backward)");
		sb.append("\t");
		sb.append("j (jump)");
		sb.append("\t\t");
		sb.append("t (test)");
		sb.append("\n");
		
		// Line 3
		sb.append("r (remove)");
		sb.append("\t");
		sb.append("a (add after)");
		sb.append("\t");
		sb.append("i (insert before)");
		sb.append("\t");
		sb.append("r (retitle)");
		sb.append("\n");
		
		// Line 4
		sb.append("c (contains)");
		sb.append("\t");
		sb.append("u (update)");
		sb.append("\t");
		sb.append("x (exit)");
		sb.append("\n");
		
		System.out.print(sb);
	}
	
	/**
	 * A helper method for the class. displayContext() displays the image 
	 * (i.e, it's title, filename and duration) immediately before the current 
	 * image, the current image, and the image immediately after the current 
	 * image (one per line). 
	 * <p>
	 * The arrows "--> <--" are displayed to highlight the current image. 
	 * However there are two specials cases. If the loop contains only a single
	 * image and if the loop contains only two images. In both of these cases
	 * the current image is displayed on the first line.
	 */
	private static void displayContext()
	{
		if(loop.isEmpty())
		{
			return;
		}
		
		/*
		 *  Used to determine when to print the current image, its previous
		 *  and / or its next image.
		 */
		int loopSize;
		
		/*
		 *  Tests the loop size against the three cases of displaying context
		 *  and sets the loopSize variable accordingly.
		 */
		switch(loop.size())
		{
			case 1: loopSize = 1;
				break;
			case 2: loopSize = 2;
				break;
			default: loopSize = 3;
				break;
		}
		
		// Prints a single image during each iteration
		for(int i = 0; i < loopSize; i++)
		{
			/*
			 * Case where the total loop size is less than 3 and its the first
			 * iteration or the loop size is 3 (or greater) and is the second
			 * line.
			 * 
			 * Prints current image
			 */
			if((loopSize < 3 && i == 0) || (loopSize == 3 && i == 1))
			{
				try 
				{
					System.out.println("--> " + loop.getCurrent() + " <--");
				}
				catch (EmptyLoopException e) 
				{
					System.out.println("no images");
				}
			}
			/*
			 * Case where loop size is 3 (or greater) and is printing the
			 * first image that is the previous of current.
			 * 
			 * REGARDING THE NEXT TWO IF STATEMENT BLOCKS:
			 * 		Without using an iterator the getCurrent() method returns
			 * the data of the image and not the pointer to the node. 
			 * Therefore we cannot get the pointer to the next or previous 
			 * nodes and print it from the currentNode without advancing
			 * the loop in either direction. 
			 * 
			 * The original current is advanced to it's next() so we can 
			 * utilize the toString() method of the image object. This is 
			 * reversed at the end of the statement. 
			 */
			else if(loopSize == 3 && i == 0)
			{
				loop.next();
				try 
				{
					System.out.println("    " + loop.getCurrent());
				}
				catch (EmptyLoopException e) 
				{
					System.out.println("no images");
				}
				loop.previous();
			}
			else
			{
				loop.previous();
				try 
				{
					System.out.println("    " + loop.getCurrent());
				}
				catch (EmptyLoopException e) 
				{
					System.out.println("no images");
				}
				loop.next();
			}
		}
	}
	
	/**
	 * Saves all the image objects in the loop to a text file.  
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'S' or 's' is entered by the user or read from a file. 
	 * <p>
	 * Format expected(without brackets): [filename] [duration] [title]
	 * 
	 * @param filename The filename used to create and write to a file.
	 */
	public static void save(String filename)
	{
		if(loop.isEmpty())
		{
			System.out.println("no images to save");
		}
		else
		{
			File outFile = new File(filename);
			if (outFile.exists()) 
			{
				System.out.println(
						"warning: file already exists, will be overwritten");
			}
			
			try(PrintWriter output = new PrintWriter(filename);)
			{
				Iterator<Image> loopIterator = loop.iterator();

				/*
				 *  The Image Object's toString() cannot be used so a 
				 *  string builder is created.
				 */
				while(loopIterator.hasNext())
				{
					Image tempImage = loopIterator.next();
					StringBuilder sb = new StringBuilder();
					sb.append(tempImage.getFile());
					sb.append(" ");
					sb.append(tempImage.getDuration());
					sb.append(" ");
					sb.append(tempImage.getTitle());
					output.println(sb);
				}
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("unable to save");
			}
		}
	}
	
	/**
	 * Loads the loop database from an existing file of image properties.
	 * This method will confirm that the filename presented for each image
	 * does exist or it will not be added to the loop.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'L' or 'l' is entered by the user or read from a file. 
	 * <p>
	 * Format expected(without brackets): [filename] [duration] [title]
	 * 
	 * @param filename The filename of the file contain images to load.
	 */
	public static void load(String filename)
	{
		File inFile = new File(filename);

		if (!inFile.exists() || !inFile.canRead()) 
		{
		    System.out.println("unable to load");
		}
		else
		{
			try(Scanner fileScanner = new Scanner(inFile)) 
			{
				String [] imageAsLine = new String[3];

				while(fileScanner.hasNextLine())
				{
					/*
					 * Line format: title [duration,filename]
					 * Indexes correspond as follows
					 * 0: filename
					 * 1: duration
					 * 2: title
					 */
					
					// Filename
					imageAsLine[0] = fileScanner.next();
					// Duration
					imageAsLine[1] = fileScanner.next();
					// Title
					imageAsLine[2] = 
							fileScanner.nextLine().replaceAll("\"", "").trim();

					File imageFile = 
							new File(IMAGES_DIR + "/" + imageAsLine[0]);
					
					if(!imageFile.exists())
					{
						System.out.println("Warning: " + imageAsLine[0] + 
								" is not in images folder");
					}
					else
					{
						loop.add(new Image(imageAsLine[0],
								imageAsLine[2],
								Integer.parseInt(imageAsLine[1])));
					}
				}
				
				// Set first image added to be current
				loop.previous();

			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("unable to load");
			}
		}
	}
	
	/**
	 * Loops through all image nodes in the loop and displays them on a line.
	 * Formatted: title [filename,duration]
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'D' or 'd' is entered by the user or read from a file.
	 */
	public static void display()
	{
		if(loop.isEmpty())
		{
			System.out.println(EMPTY_LIST_MESSAGE);
		}
		else
		{
			Iterator<Image> loopIterator = loop.iterator();
			
			while(loopIterator.hasNext())
			{
				System.out.println(loopIterator.next());
			}
		}
	}
	
	/**
	 * Displays the current image as a photograph, in a window with the image's
	 * title for the specified duration.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'P' or 'p' is entered by the user or read from a file.
	 */
	public static void picture()
	{
		try 
		{
			loop.getCurrent().displayImage();
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (EmptyLoopException e)
		{
			System.out.println(EMPTY_LIST_MESSAGE);
		}
	}
	
	/**
	 * Forward the current item to the next image in the loop and display the 
	 * current context.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'F' or 'f' is entered by the user or read from a file.
	 */
	public static void forward()
	{
		if(loop.isEmpty())
			System.out.println(EMPTY_LIST_MESSAGE);
		else
		{
			/*
			 *  Remember the loop is in reverse order where the right most
			 *  node is the first loaded. Next() points right, prev() points
			 *  left. Forward correlates to the next newest image (left).
			 */
			loop.previous();
			displayContext();
		}
	}
	
	/**
	 * Back the current item to the previous image in the loop and display the
	 * current context 
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'b' or 'b' is entered by the user or read from a file.
	 */
	public static void backward()
	{
		if(loop.isEmpty())
			System.out.println(EMPTY_LIST_MESSAGE);
		else
		{
			loop.next();
			displayContext();
		}
	}
	
	/**
	 * Sets the current image by advancing N(jump) number of nodes. If N < 0
	 * it jumps back N and if N > 0 forward N.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'j' or 'j' is entered by the user or read from a file.
	 * 
	 * @param jump The number of nodes to jump.
	 */
	public static void jump(String jump)
	{
		try
		{
			int jumpCount = Integer.parseInt(jump);
			
			if(loop.isEmpty())
				System.out.println(EMPTY_LIST_MESSAGE);
			else
			{
				for(int i = 0; i < Math.abs(jumpCount); i++)
				{
					if(jumpCount < 0)
						loop.next();
					else if(jumpCount > 0)
						loop.previous();
				}
				displayContext();
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("invalid command");
		}
	}
	
	/**
	 * Otherwise, test the loop, starting with the current image, by displaying 
	 * each image as a photograph in a window with the image's title for the 
	 * specified duration.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'T' or 't' is entered by the user or read from a file.
	 */
	public static void test()
	{
		if(loop.isEmpty())
			System.out.println(EMPTY_LIST_MESSAGE);
		else
		{
			Iterator<Image> loopIterator = loop.iterator();
			while(loopIterator.hasNext())
			{
				try 
				{
					loopIterator.next().displayImage();
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Removes the current image.  If the image loop becomes empty as a result 
	 * of the removal, display "no images".  Otherwise, make the image after 
	 * the removed image the current image and display the current context
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'R' or 'r' is entered by the user or read from a file.
	 */
	public static void remove()
	{
		try 
		{
			loop.removeCurrent();
			if(loop.size() == 0)
				System.out.println(EMPTY_LIST_MESSAGE);
			else
				displayContext();
		}
		catch (EmptyLoopException e)
		{
			System.out.println(EMPTY_LIST_MESSAGE);
		}
	}
	
	/**
	 * f the image loop is empty, add a new image with the given filename, 
	 * a null title, and a duration of 5 seconds to the loop and make it the 
	 * current image.  Otherwise, add the new image immediately after the 
	 * current image and make the new image the new current image.  
	 * In either case, display the current context 
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'A' or 'a' is entered by the user or read from a file.
	 * 
	 * @param filename The actual image's filename.
	 */
	public static void addAfter(String filename)
	{
		File imageFile = new File(IMAGES_DIR + "/" + filename);
		
		if(!imageFile.exists())
		{
			System.out.println("Warning: " + filename + 
					" is not in images folder");
		}
		else
		{
			loop.next();
			loop.add(new Image(filename, "", 5));
		}
		displayContext();
	}
	
	/**
	 * If the image loop is empty, add a new image with the given filename, 
	 * a null title, and a duration of 5 seconds to the loop and make it the 
	 * current image.  Otherwise, insert the new image immediately before the 
	 * current image and make new image the new current image.  
	 * In either case, display the current context 
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'I' or 'i' is entered by the user or read from a file.
	 * 
	 * @param filename The actual image's filename
	 */
	public static void insertBefore(String filename)
	{
		File imageFile = new File(IMAGES_DIR + "/" + filename);
		
		if(!imageFile.exists())
		{
			System.out.println("Warning: " + filename + 
					" is not in images folder");
		}
		else
		{
			loop.add(new Image(filename, "", 5));
		}
		displayContext();
	}
	
	/**
	 * Edits the title for current image to the given title 
	 * (which may be quoted) and display the current context
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'E' or 'e' is entered by the user or read from a file.
	 * 
	 * @param title
	 */
	public static void retitle(String title)
	{
		title = title.replaceAll("\"", "");
		
		try 
		{
			loop.getCurrent().setTitle(title);
			displayContext();
		}
		catch (EmptyLoopException e)
		{
			System.out.println(EMPTY_LIST_MESSAGE);
		}
	}
	
	/**
	 * Finds (by searching forward in the image loop) the first image whose 
	 * title contains the given string (which may be quoted).  If no image with
	 * a title containing string is found, display "not found"; otherwise, make
	 * that image the current image and display the current context. 
	 * Comparison is case-sensitive.
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'S' or 's' is entered by the user or read from a file.
	 * 
	 * @param searchString The string to be used to search against titles
	 */
	public static void contains(String searchString)
	{
		if(loop.isEmpty())
		{
			System.out.println(EMPTY_LIST_MESSAGE);
		}
		else
		{
			Iterator<Image> loopIterator = loop.iterator();
			Image tempImage;
			while(loopIterator.hasNext())
			{
				tempImage = loopIterator.next();
				if(tempImage.getTitle().equals(searchString))
				{
					displayContext();
					break;
				}
				else if(!loopIterator.hasNext())
					System.out.println("not found");
			}
		}
	}

	/**
	 * Update the duration for current image to the given time and display the
	 * current context. 
	 * <p>
	 * This method is called by user input in execute() when the character
	 * 'U' or 'u' is entered by the user or read from a file.
	 * 
	 * @param time The display duration in seconds.
	 */
	public static void update(String time)
	{
		try
		{
			int duration = Integer.parseInt(time);
			try 
			{
				loop.getCurrent().setDuration(duration);
				displayContext();
			}
			catch (EmptyLoopException e) 
			{
				System.out.println(EMPTY_LIST_MESSAGE);
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("invalid command");
		}
	}
	
	/**
	 * Central switch statement called from interactive() (user console based
	 * inputs) or from a command line argument file (loaded in main). This 
	 * method will call the subsequent methods for each process to manipulate
	 * the image loop.
	 * 
	 * @param menuChoice The users choice used to match a case and method
	 * @param remainder The data sent to the method if exists.
	 */
	public static void execute(char menuChoice, String remainder)
	{
		switch(menuChoice)
		{
			case '?': printHelpMenu();
				break;
				
			case 's': save(remainder);
				break;

			case 'l': load(remainder);
				break;
				
			case 'd': display();
				break;
				
			case 'p': picture();
				break;
				
			case 'f': forward();
				break;
				
			case 'b': backward();
				break;
				
			case 'j': jump(remainder);
				break;
				
			case 't': test();
				break;
			
			case 'r': remove();
				break;
			
			case 'a': addAfter(remainder);
				break;
				
			case 'i': insertBefore(remainder);
				break;
				
			case 'e': retitle(remainder);
				break;
				
			case 'c': contains(remainder);
				break;
			
			case 'u': update(remainder);
				break;
			
			case 'x': System.exit(2);
					break;
		}
	}
	
	/**
	 * This is the primary controlling method for user input from the console.
	 * It will loop continuously until the user enters the 'X' or 'x' 
	 * character at the menu prompt. The command letter is not case sensitive. 
	 */
	public static void interactive()
	{
		boolean quit = false;
		
		do
		{
			// Print command prompt
			System.out.print("enter command (? for help)> ");
			// Grab menu option
			String userInput = inputScanner.nextLine();

			// Grab the first character of the input. 
			char menuChoice = userInput.toLowerCase().charAt(0);
			String remainder = userInput.substring(1).trim();
			
			execute(menuChoice, remainder);
			
		}while(quit == false);
		
		inputScanner.close(); // Close user input scanner.
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Ensure only one argument is entered to test for filename.
		if(args.length == 1)
		{
			File inFile = new File(args[0]);

			/*
			 * Test file if it exists and can be read. Then load commands
			 * and send to the execute() method.
			 */
			try 
			{
				if (!inFile.exists() || !inFile.canRead()) 
				{
				    System.err.println("problem with input file!");
				    System.exit(1);
				}
				inputScanner = new Scanner(inFile);
			} 
			catch (FileNotFoundException e) 
			{
				System.err.println("problem with input file!");
				System.exit(1);
			}
			
			while(inputScanner.hasNextLine())
			{
				char menuChoice = inputScanner.next().toLowerCase().charAt(0);
				String remainder = inputScanner.nextLine().trim();
				
				execute(menuChoice, remainder);
			}
			
		}
		/*
		 *  Program can only be called with zero or one argument. Catches calls
		 *  with greater than one argument.
		 */
		else if(args.length > 1)
		{
			System.err.println("invalid command-line arguments");
		}
		else // Run in interactive mode prompting the user on the console.
		{
			inputScanner = new Scanner(System.in);
			interactive();
		}
	}
}
