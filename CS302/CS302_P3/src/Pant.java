///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Pant.java
// Semester:         CS302 Spring 2016
//
// Author:           Spencer George
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a pair of Pants that the Hero must protect from
 * burning. Whenever a Pant collides with a Fireball, that Pant will be
 * replaced by a Fire. 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Pant 
{
	/** Holds the reference to the graphic of this instance of Pant */
	private Graphic graphic;
	
	/** An instance of the randGen passed in to the Game constructor */
	private Random randGen;
	
	/**
	 *  true if object is being displayed and can interact with game objects
	 *  false if has collided with a fireball and should be removed
	 */
	private boolean isAlive;
 
	/**
	 * This constructor initializes a new instance of Pant at the appropriate
	 * location. The Random number is only used to create a new Fire, after
	 * this pant is hit by a Fireball.
	 * 
	 * @param x - the x-coordinate of this new Pant's position
	 * @param y - the y-coordinate of this new Pant's position
	 * @param randGen - a Random number generator to pass onto any Fire that is
	 * created as a result of this Pant being hit by a Fireball.
	 */
	public Pant(float x, float y, Random randGen) 
	{
		// Create a graphic instance of this object
		graphic = new Graphic();
		graphic.setX(x);
		graphic.setY(y);
		graphic.setType("PANT");
		
		isAlive = true;
		
		this.randGen = randGen;
		
	}
	
	/**
	 * This method is simply responsible for draing the current Pant to the
	 * screen.
	 * 
	 * @param time - is the amount of time in milliseconds that has elapsed 
	 * since the last time this update was called.
	 */
	public void update(int time) 
	{
		graphic.draw();
	}
	
	/**
	 * This is a simple accessor for this object's Graphic, which may be used
	 * by other objects to check for collisions.
	 * 
	 * @return a reference to this Pant's Graphic object.
	 */
	public Graphic getGraphic()
	{
		return this.graphic;
	}
	
	/**
	 * This method detects an handles collisions between any active Fireball,
	 * and the current Pant. When a collision is found, the colliding Fireball
	 * should be removed from the game (by calling its destroy() method), and
	 * the current Pant should also be removed from the game (by destroying it
	 * graphic, and also ensuring that its shouldRemove() method returns true).
	 * 
	 * @param fireballs - the ArrayList of Fireballs that should be checked 
	 * against the current Pant's position for collisions.
	 * 
	 * @return null unless a new Fire was just created in the place of this
	 * Pant. In that case, a reference to that new Fire is returned instead.
	 */
	public Fire handleFireballCollisions(ArrayList<Fireball> fireballs) 
	{
		for(int i = 0; i < fireballs.size(); i++)
		{
			if(fireballs.get(i) != null)
			{
				if(graphic.isCollidingWith(fireballs.get(i).getGraphic()))
				{
					graphic.destroy();
					isAlive = false;
					fireballs.get(i).destroy();
					
					return new Fire(graphic.getX(), graphic.getY(), randGen);
				}
			}
		}
		
		return null;
		
	}
	
	/**
	 * This method communicates to the Game whether this Pant is still in use
	 * vs. done being used and ready to be removed from the Game's ArrayList
	 * of Pants.
	 * 
	 * @return true when this Pant has been hit by a Fireball, otherwise false.
	 */
	public boolean shouldRemove()
	{
		if(isAlive == false)
			return true;
		
		return false;
				
	}

}