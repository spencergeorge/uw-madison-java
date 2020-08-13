///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Hero.java
// Semester:         CS302 Spring 2016
//
// Author:           Spencer George
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;

/**
 * This class represents the player's character which is a fire fighter who is
 * able to spray water that extinguishes Fires and Fireballs. They must save 
 * as many Pants from burning as possible, and without colliding with any 
 * Fireballs in the process. 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Hero 
{
	/** Holds the reference to the graphic of this instance of Hero */
	private Graphic graphic;
	
	/** How fast the object appears to move, multiply with time */
	private float speed;
	
	/** A private integer representing controlType, currently 1,2,3 */
	private int controlType;
	
	/** Counter for current mouse distance from hero */ 
	private double mouseDistance;
	
	/** 
	 * The minimum distance between the mouse and the Hero.
	 * If the distance is greater, the Hero will move to the position of
	 * the mouse until it is equal to or less than this value.
	 */
	private final double MIN_MOUSE_DISTANCE;
	
	/**
	 * This constructor initializes a new instance of Hero at the appropriate
	 * location and using the appropriate controlType. This Hero should move 
	 * with a speed of 0.12 pixels per millisecond.
	 * 
	 * @param x - the x-coordinate of this new Hero's position
	 * @param y - the y-coordinate of this new Hero's position
	 * @param controlType - specifies which control scheme should be used by
	 * this hero
	 */
	public Hero(float x, float y, int controlType)
	{
		// Create a graphic instance of this object
		graphic = new Graphic();
		graphic.setX(x);
		graphic.setY(y);
		graphic.setType("HERO");
		
		this.controlType = controlType;
		
		speed = 0.12F;	
		
		MIN_MOUSE_DISTANCE = 20.0;
				
		mouseDistance = 0;
	}
	
	/**
	 * This method is called repeated by the Game to draw and move (based on
	 * the current controlType) the Hero, as well as to spray new Water in the
	 * direction that this Hero is currently facing.
	 * 
	 * @param time - is the amount of time in milliseconds that has elapsed
	 * since the last time this update was called.
	 * 
	 * @param water - the array of Water that the Hero has sprayed in the past,
	 * and if there is an empty (null) element in this array, they can can add
	 * a new Water object to this array when the use is pressing the
	 * appropriate controls.
	 */
	public void update(int time, Water[] water)
	{
		/**
		 * Iterate through the water array and if the reference is null
		 * and the SPACE or MOUSE is being held, a water object is created
		 * and the reference is stored where the null water reference was.
		 * 
		 * The water object is created at the same location as the Hero
		 * and its direction is set as the direction of the Hero at the time
		 * the object is created.
		 */
		for(int i = 0; i < water.length; i++)
		{
			if(water[i] == null)
			{
				if(Engine.isKeyHeld("SPACE") || Engine.isKeyHeld("MOUSE"))
				{
					water[i] = new Water(graphic.getX(), graphic.getY(),
							graphic.getDirection());
					i = water.length;
				}
			}
		}
		
		/**
		 * On each update, match the control type 1,2,3 and modify 
		 * movement accordingly
		 * 
		 * 1: move and look with W (up) A (left) S (down) D (right) 
		 * 2: move with WASD, look with mouse
		 * 3: move and look with mouse
		 */
		switch(controlType) 
		{
			case 1: 
				// Move & look right
				if(Engine.isKeyHeld("D"))
				{
					graphic.setDirection(0);
					graphic.setX(graphic.getX() 
							+ (speed * (float)time));
				}
				// Move & look left
				else if(Engine.isKeyHeld("A"))
				{
					graphic.setDirection((float) Math.PI);
					graphic.setX(graphic.getX() 
							- (speed * (float)time));
				}
				// Move & look up
				else if(Engine.isKeyHeld("W"))
				{
					graphic.setDirection((float) (3 * Math.PI / 2));
					graphic.setY(graphic.getY() 
							- (speed * (float) time));
				}
				// Move & look down
				else if(Engine.isKeyHeld("S"))
				{
					graphic.setDirection((float) (Math.PI / 2));
					graphic.setY(graphic.getY() 
							+ (speed * (float)time));
				}
				break;
			
			case 2:
				// Set direction to look where mouse is
				graphic.setDirection(Engine.getMouseX(), Engine.getMouseY());

				// Move right
				if(Engine.isKeyHeld("D"))
				{
					graphic.setX(graphic.getX() 
							+ (speed * (float)time));
				}
				// Move left
				else if(Engine.isKeyHeld("A"))
				{
					graphic.setX(graphic.getX() 
							- (speed * (float)time));
				}
				// Move up
				else if(Engine.isKeyHeld("W"))
				{
					graphic.setY(graphic.getY() 
							- (speed * (float)time));
				}
				// Move down
				else if(Engine.isKeyHeld("S"))
				{
					graphic.setY(graphic.getY() 
							+ (speed * (float)time));
				}
				break;
				
			case 3:
				// Set direction of hero towards mouse
				graphic.setDirection(Engine.getMouseX(), 
						Engine.getMouseY());
				
				// Calculate distance between mouse and hero
				// distance = sqrt((Xsub2 - Xsub1)^2 + (Ysub2 - Ysub1)^2)
				mouseDistance = Math.sqrt(
						(Engine.getMouseX() - graphic.getX())
							* (Engine.getMouseX() - graphic.getX()) 
						+ (Engine.getMouseY() - graphic.getY())
							* (Engine.getMouseY() - graphic.getY()));
				
				
				/**
				 * If mouse is farther than MIN_MOUSE_DISTANCE away,
				 * set next position of Hero to advance in the direction
				 * of mouse. 
				 */
				if(mouseDistance >= MIN_MOUSE_DISTANCE)
				{
					graphic.setX(graphic.getX() 
							+ (graphic.getDirectionX() 
							* speed * (float)time));
					graphic.setY(graphic.getY()
							+ (graphic.getDirectionY() 
							* speed * (float)time)); 
				}
				break;
		}
		
		graphic.draw();
		
	} //END update()
	
	/**
	 * This is a simple accessor for this object's Graphic, which may be used
	 * by other objects to check for collisions.
	 * 
	 * @return a reference to this Hero's Graphic object.
	 */
	public Graphic getGraphic()
	{
		return this.graphic;
	}
	
	/**
	 * This method detects an handles collisions between any active Fireball
	 * objects, and the current Hero. When a collision is found, this method
	 * returns true to indicate that the player has lost the Game.
	 * 
	 * @param fireballs - the ArrayList of Fireballs that should be checked
	 * against the current Hero's position for collisions.
	 * 
	 * @return true when a Fireball collision is detected, otherwise false
	 */
	public boolean handleFireballCollisions(ArrayList<Fireball> fireballs)
	{
		for(int i = 0; i < fireballs.size(); i++)
		{
			if(graphic.isCollidingWith(fireballs.get(i).getGraphic()))
				return true;
		}
		
		return false;
		
	}

}
