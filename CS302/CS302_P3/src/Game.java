///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Program 3 Pants on Fire
// Files:            PantsOnFire.jar, Hero.java, Water.java, Pant.java,
//					 Fireball.java, Fire.java
// Semester:         CS302 Spring 2016
//
//Author:           Spencer George
//
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Game class is responsible for managing all of the objects in your game.
 * The Application creates a new Game object for each level, and then calls
 * that Game object's update() method repeatedly until it returns either
 * "ADVANCE", or "QUIT". 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Game 
{
	/** Our controllable character, a fire-fighter  */ 
	private Hero hero;
	
	/** Hero's weapon of choice a stream of water stream */
	private Water[] water;
	
	/** Pants, the victim to be saved */
	private ArrayList<Pant> pants = new ArrayList<>();
	
	/** Weapon of the fire object */
	private ArrayList<Fireball> fireballs = new ArrayList<>();
	
	/** The agmes villain */
	private ArrayList<Fire> fires = new ArrayList<>();
	
	/** This instance of a random generator passed to the game constructor */
	private Random randGen;
	
	/** Number of fires and pants created for a random level (not random) */ 
	private final int NUMBER_OF_PANTS;
	private final int NUMBER_OF_FIRES;
	
	/**
	 * This constructor initializes a new Game object, so that the Application
	 * can begin calling this game's update() method to advance game play.
	 * In the process of this initialization, all of the objects in the current
	 * level should be instantiated and initialized to their beginning states.
	 * 
	 * @param level - is a string that either contains the word "RANDOM", or
	 * the contents of a custom level that should be loaded and played.
	 * 
	 * @param randGen - is a Random number generator that should be used to set
	 * the initial positions for the 6 Fire objects and 20 Pant objects within
	 * a random level.
	 */
	public Game(String level, Random randGen) 
	{
		this.randGen = randGen;
		
		water = new Water[8];
		
		NUMBER_OF_PANTS = 20;
		NUMBER_OF_FIRES = 6;
		
		if(level.equals("RANDOM"))
			createRandomLevel();
		else
			loadLevel(level);
		
	}

	/**
	 * The Application calls this method repeatedly to update all of the
	 * objects within your game, and to enforce all of the rules of your game.
	 * 
	 * @param time - is the time in milliseconds that has elapsed since the
	 * last time this method was called. This can be used to control the speed
	 * that objects are moving within your game.
	 * 
	 * @return When this method returns "QUIT" the game will end after a short
	 * 3 second pause and message indicating that the player has lost. 
	 * When this method returns "ADVANCE", a short pause and win message will
	 * be followed by the creation of a new game which replaces this one.
	 * When this method returns anything else (including "CONTINUE"), 
	 * the Application will simply continue to call this update() 
	 * method as usual.
	 */
	public String update(int time)
	{
		/**
		 *  Store a single instance when these objects are created or not but
		 *  supporting methods in pant and fire. Otherwise instances that are
		 *  created and returned upon a method call would be lost.
		 */
		Fire tempFire;
		Fireball tempFireball;
		
		hero.update(time, water);
		
		// Update all water objects
		for(int i = 0; i < water.length; i++)
		{
			// Water can be null if the space or mouse have not been held long
			// enough or a water object collides with a fireball and is removed
			if(water[i] != null) 
				water[i] = water[i].update(time);
		}
		
		// Update pant objects, test if they have collided with a fireball
		for(int i = 0; i < pants.size(); i++)
		{
			pants.get(i).update(time);
			
			tempFire = pants.get(i).handleFireballCollisions(fireballs);
			// If a fire object has been created (fireball collided with pant)
			if(tempFire != null)
				fires.add(tempFire);
			
			if(pants.get(i).shouldRemove() == true)
				pants.remove(i);
				
		}
		
		// Update all fire objects, test if collided with water
		for(int i = 0; i < fires.size(); i++)
		{
			tempFireball = fires.get(i).update(time); 
			// If a fireball object has been created (fireball timer expired)
			if(tempFireball != null)
				fireballs.add(tempFireball);
			
			// Test water fire collisions
			fires.get(i).handleWaterCollisions(water);
			
			if(fires.get(i).shouldRemove() == true)
				fires.remove(i);
		}
				
		// Update all fireball objects, test if collided with water
		for(int i = 0; i < fireballs.size(); i++)
		{
			if(fireballs.get(i) != null)
			{
				fireballs.get(i).update(time);
				fireballs.get(i).handleWaterCollisions(water);
				
				if(fireballs.get(i).shouldRemove() == true)
					fireballs.remove(i);
			}
		}
		
		// Determine game status. Hero has died, pants are now fire, no fire
		if(hero.handleFireballCollisions(fireballs) || pants.size() == 0)
			return "QUIT";
		else if(fires.size() == 0)
			return "ADVANCE";
				
		return "CONTINUE";
	}
	
	/**
	 * This method returns a string of text that will be displayed in the upper
	 * left hand corner of the screen. Ultimately this should express the
	 * number of unburned pants remaining in the level. However, this may 
	 * also be useful for displaying messages that help you debug your game.
	 * 
	 * @return a string of text to be displayed in the upper-left hand corner
	 * of the screen by the Application.
	 */
	public String getHUDMessage() 
	{ 
		return "Pants Left: " + pants.size() + "\nFires Left: " + fires.size(); 
	}
	
	/**
	 * This method creates a random level consisting of a single Hero centered
	 * in the middle of the screen, along with 6 randomly positioned fires, 
	 * and 20 randomly positioned pants.
	 */
	public void createRandomLevel() 
	{
		/**
		 * Creates hero in the center of the window. 
		 * Sets the control type randomly( between ints 1,2,3)
		 */
		hero = new Hero((float)(Engine.getWidth() / 2),
				(float)(Engine.getHeight() / 2), 
				randGen.nextInt(3) + 1);
		
		// Create and store pant objects based on NUMBER_OF_PANTS
		int pantsCount = 0;
		while(pantsCount < NUMBER_OF_PANTS)
		{
			pants.add(new Pant(randGen.nextFloat() * Engine.getWidth(), 
					randGen.nextFloat() 
							* Engine.getHeight(), randGen));
			pantsCount++;
		}
		
		// Create and store fire objects based on NUMBER_OF_PANTS
		int firesCount = 0;
		while(firesCount < NUMBER_OF_FIRES)
		{
			fires.add(new Fire(randGen.nextFloat() * Engine.getWidth(), 
					randGen.nextFloat() 
							* Engine.getHeight(), randGen));
			firesCount++;
		}
	}
	
	/**
	 * This method initializes the current game according to the descriptions
	 * included within the level parameter as described below.
	 * 
	 * @param level - is a string containing the contents of a custom level
	 * file, which can be read using a new Scanner(level). Try looking through
	 * a few of the provided level files to see how they are formatted. 
	 * The first line is always the "ControlType: #" where # is either
	 * 1, 2, or 3. Subsequent lines describe an object TYPE, along with an
	 * X and Y position that are formatted as: "TYPE @ X, Y". This method 
	 * should instantiate and initialize a new object for each such line.
	 */
	public void loadLevel(String level) 
	{
		Scanner scnr = new Scanner(level);

		// Stores objectType (class) to be created
		String scnrObjectType;

		// Stores the objects X coordinate as String because it will include
		// a comma that is read by the scanner with the next() method.
		String scnrObjectXString;

		// Substring of scnrObjectXString that is only a float of the x coord.
		float scnrObjectX;
		
		// Objects Y coordinate
		float scnrObjectY;
		
		scnr.next();	// First line is always "controlType: [int]"
						// Skip "controlType: " and store

		// Read the control type "ControlType:"
		int controlType = scnr.nextInt();
		
		
		/**
		 * After the controlType has been stored, the following lines contain
		 * information for creating Hero, Fire, and Pant objects.
		 * 
		 * This will read each line and create the objects as given.
		 */
		while(scnr.hasNext() == true)
		{
			// Read object type
			scnrObjectType = scnr.next();
			
			// Skip @ symbol
			scnr.next(); 
			
			// Read X coordinate as string because it contains a comma
			scnrObjectXString = scnr.next();
			// Remove the comma and convert to float
			scnrObjectX = Float.parseFloat(scnrObjectXString.substring(0, 
						scnrObjectXString.length() -1));
			
			// Read the Y coordinate
			scnrObjectY = scnr.nextFloat();

			// Based on the object type create the object at X, Y 
			switch(scnrObjectType)
			{
				case "HERO":
					hero = new Hero(scnrObjectX, scnrObjectY, controlType);
					break;
				case "FIRE":
					fires.add(new Fire(scnrObjectX,scnrObjectY, randGen));
					break;
				case "PANT":
					pants.add(new Pant(scnrObjectX,scnrObjectY, randGen));
					break;
			}
		}
		
		scnr.close();
	}

	/**
	 * This method creates and runs a new Game and Engine together. Any command
	 * line arguments are used to choose custom levels that should be played in
	 * a particular order. It is also possible to seed the Random number 
	 * generator passed into this Game's constructor by passing a positive 
	 * integer as string first element within this args array.
	 * 
	 * @param args - is the sequence of custom levels to play, with an optional
	 * first element containing a seed for a random number generator.
	 */
	public static void main(String[] args)
	{
		Application.startEngineAndGame(args);		
	}
}

