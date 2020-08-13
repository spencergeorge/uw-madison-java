///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Fire.java
// Semester:         CS302 Spring 2016
//
// Author:           Spencer George 
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Random;

/**
 * This class represents a fire that is burning, which ejects a fireball in a 
 * random direction every 3-6 seconds. This fire can slowly be extinguished 
 * through repeated collisions with water. 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Fire 
{
	/** Holds the reference to the graphic of this instance of Fire */
	private Graphic graphic;
	
	/** An instance of the randGen passed in to the Game constructor */
	private Random randGen;
	
	/** A fire's life unit. Decrements one when a single water collides */
	private int heat;
	
	/**
	 *  true if object is being displayed and can interact with game objects
	 *  false if has collided with a water and should be removed
	 */
	private boolean isAlive;
	 
	/** Upper and lower Bounds between fireball instances spewed from a fire */
	private final int FIRE_TIME_HIGH;	
	private final int FIRE_TIME_LOW;

	/** Countdown between fireballs created, set randomly between bounds */
	private int fireballCountdown;
	
	/**
	 * This constructor initializes a new instance of Fire at the appropriate
	 * location and with the appropriate amount of heat. The Random number 
	 * generator should be used both to determine how much time remains before
	 * the next Fireball is propelled, and in which random direction it is shot.
	 * 
	 * @param x - the x-coordinate of this new Fire's position
	 * @param y - the y-coordinate of this new Fire's position
	 * @param randGen - a Random number generator to determine when and in 
	 * which direction new Fireballs are created and launched.
	 */
	public Fire(float x, float y, Random randGen) 
	{
		// Create a graphic instance of this object
		graphic = new Graphic();
		graphic.setX(x);
		graphic.setY(y);
		graphic.setType("FIRE");
		
		// Set between 3-6 seconds
		FIRE_TIME_HIGH = 5999;
		FIRE_TIME_LOW = 3000;
		
		isAlive = true;
		
		this.randGen = randGen;
		
		// Start with a life of 40
		heat = 40;
		
		/**
		 *  The initial fireball countdown timer is set here.
		 *  Otherwise if the fire remains alive for more than one fireball
		 *  object fireballCountdown is reset in the update() method.
		 */
		fireballCountdown = this.randGen.nextInt((FIRE_TIME_HIGH + 1) 
				- FIRE_TIME_LOW) + FIRE_TIME_LOW;
		
	}
	
	/**
	 * This method is called repeatedly by the Game to draw and sporadically
	 * launch a new Fireball in a random direction.
	 * 
	 * @param time - is the amount of time in milliseconds that has elapsed 
	 * since the last time this update was called.
	 * 
	 * @return null unless a new Fireball was just created and launched. 
	 * In that case, a reference to that new Fireball is returned instead.
	 */
	public Fireball update(int time) 
	{
		/** 
		 * If heat is less than 1, the fire has lost its
		 * flame so to speak therefore it is destroyed and 
		 * removed from the screen.  
		 */
		if(heat < 1)
		{
			graphic.destroy();
			isAlive = false;
			return null;
		}
		
		graphic.draw();

		fireballCountdown -= time;	
		
		
		/**
		 * When fireballCountdown is <= 0
		 * reset the fireballCountdown to a random value
		 * between FIRE_TIME_LOW and FIRE_TIME_HIGH and return
		 * a new Fireball object
		 */
		if(fireballCountdown <= 0)
		{
			fireballCountdown = randGen.nextInt((FIRE_TIME_HIGH + 1) 
					- FIRE_TIME_LOW) + FIRE_TIME_LOW;
			
			return new Fireball(graphic.getX(), graphic.getY(),
					randGen.nextFloat() * (float)(2.0 * Math.PI));
			
		}
		
		return null;
	}	
	
	/**
	 * This is a simple accessor for this object's Graphic, 
	 * which may be used by other objects to check for collisions.
	 * 
	 * @return a reference to this Fire's Graphic object.
	 */
	public Graphic getGraphic()
	{
		return graphic;
	}
	
	/**
	 * This method detects and handles collisions between any active (!= null)
	 * Water objects, and the current Fire. When a collision is found, 
	 * the colliding water should be removed, and this Fire's heat should be
	 * decremented by 1. If this Fire's heat reaches 0, then it's graphic 
	 * should be destroyed, it should no longer eject Fireballs, and its 
	 * shouldRemove() method should start returning true.
	 * 
	 * @param water - is the Array of water objects that have been launched 
	 * by the Hero (ignore any null references within this array).
	 */
	public void handleWaterCollisions(Water[] water) 
	{
		for(int i = 0; i < water.length; i++)
		{
			if(water[i] != null)
			{
				if(graphic.isCollidingWith(water[i].getGraphic()))
				{
					heat--; 
					
					water[i].getGraphic().destroy();
					water[i] = null;
				}
			}
		}
	}
	
	/**
	 * This method should return false until this Fire's heat drops down to 0. 
	 * After that it should begin to return true.
	 * 
	 * @return true when this Fire's heat is greater than zero, otherwise false
	 */
	public boolean shouldRemove()
	{
		if(isAlive == false)
			return true;
		
		return false;
	}
}
