///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Fireball.java
// Semester:         CS302 Spring 2016
//
// Author:           Spencer George 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class represents a Fireball that is ejected from a burning fire. 
 * When a Fireball hits the Hero, they lose the game. When a Fireball hits 
 * a Pant, those Pants are replaced by a Fire. 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Fireball 
{
	/** Holds the reference to the graphic of this instance of Fireball */
	private Graphic graphic;
	
	/** How fast the object appears to move, multiply with time */
	private float speed;
	
	/**
	 *  true if object is being displayed and can interact with game objects
	 *  false if has collided with a water and should be removed 
	 */
	private boolean isAlive;
	
	/**
	 *  Distance traveled offscreen before a fireball is destroyed.
	 *  Set this as a positive float.
	 */
	private final float MAX_DISTANCE_OFFSCREEN;
	 
	/**
	 * This constructor initializes a new instance of Fireball at the specified
	 * location and facing a specific movement direction. This Fireball should
	 * move with a speed of 0.2 pixels per millisecond.
	 * 
	 * @param x - the x-coordinate of this new Fireball's position
	 * @param y - the y-coordinate of this new Fireball's position
	 * @param directionAngle - the angle (in Radians) from 0 to 2pi that this
	 * new Fireball should be both oriented and moving according to.
	 */
	public Fireball(float x, float y, float directionAngle)
	{
		// Create a graphic instance of this object
		graphic = new Graphic();
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(directionAngle);
		graphic.setType("FIREBALL");
		
		// MUST SET AS POSITIVE. Automatically converted to negative
		MAX_DISTANCE_OFFSCREEN = 100.0F;
		
		speed = 0.2F;
		
		isAlive = true;
	}
	
	/**
	 * This method is called repeatedly by the Game to draw and move the 
	 * current Fireball. When a fireball moves more than 100 pixels beyond any
	 * edge of the screen, it's graphic should be destroyed and it's 
	 * shouldRemove() method should return true instead of false.
	 * 
	 * @param time - is the amount of time in milliseconds that has elapsed
	 * since the last time this update was called.
	 */
	public void update(int time) 
	{
		// Set the next position to be drawn
		graphic.setX(graphic.getX() 
				+ (graphic.getDirectionX() 
				* speed * (float)time));
		graphic.setY(graphic.getY()
				+ (graphic.getDirectionY() 
				* speed * (float)time)); 
		
		graphic.draw();
		
		// If the graphic is off the screen MAX_DISTANCE_OFFSCREEN then destroy
		if(graphic.getX() <= -MAX_DISTANCE_OFFSCREEN 
				|| graphic.getX() >= (Engine.getWidth() 
						+ MAX_DISTANCE_OFFSCREEN)
				|| graphic.getY() <= -MAX_DISTANCE_OFFSCREEN
				|| graphic.getY() >= (Engine.getHeight()
						+ MAX_DISTANCE_OFFSCREEN))
		{
			this.destroy();
		}
		
	} //END update()
	
	/**
	 * This is a simple accessor for this object's Graphic, which may be used
	 * by other objects to check for collisions.
	 * 
	 * @return a reference to this Fireball's Graphic object.
	 */
	public Graphic getGraphic()
	{
		return graphic;
	}
	
	/**
	 * This method detects an handles collisions between any active (!= null)
	 * Water objects, and the current Fireball. When a collision is found,
	 * the colliding water should be removed (graphic destroyed and array
	 * reference set to null), and this fireball should also be removed
	 * from the game ( graphic destroyed, and shouldRemove() should begin
	 * returning true).
	 * 
	 * @param water - is the Array of water objects that have been launched by
	 * the Hero (ignore any null references within this array).
	 */
	public void handleWaterCollisions(Water[] water)
	{
		for(int i = 0; i < water.length; i++)
		{
			if(water[i] != null)
			{
				if(graphic.isCollidingWith(water[i].getGraphic()))
				{
					isAlive = false;
					graphic.destroy();
					
					water[i].getGraphic().destroy();
					water[i] = null;
				}
			}
		}
	}

	/**
	 * This helper method allows other classes (like Pant) to destroy a
	 * Fireball upon collision. This method should destroy the current
	 * Fireball's graphic, and ensure that shouldRemove() starts returning
	 * true instead of false.
	 */
	public void destroy() 
	{
		isAlive = false;
		graphic.destroy();
	}
	
	/**
	 * This method communicates to the Game whether this Fireball is still in
	 * use vs. done being used and ready to be removed from the Game's
	 * ArrayList of Fireballs.
	 * 
	 * @return true when this Fireball has either gone off the screen or
	 * collided with a Water or Pant object, and false otherwise.
	 */
	public boolean shouldRemove()
	{
		if(isAlive == false)
			return true;
		return false;
	}
}
