///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Water.java
// Semester:         CS302 Spring 2016
//
// Author:           Spencer George
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This Water class represents a splash of Water that is sprayed by the Hero to
 * extinguish Fireballs and Fires, as they attempt to save the Pants. 
 *
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author Spencer George
 */
public class Water
{
	/** Holds the reference to the graphic of this instance of Water */
	private Graphic graphic;
	
	/** How fast the object appears to move, multiply with time */
	private float speed;
	
	/** Counter of distanceTraveled from creation in pixels as float */
	private float distanceTraveled;
	
	/** Limit for water objects before they are destroyed in pixels as float */
	private final float MAX_DISTANCE_TRAVELED;
	
	/**
	 * This constructor initializes a new instance of Water at the specified
	 * location and facing a specific movement direction. This Water should 
	 * move with a speed of 0.7 pixels per millisecond.
	 * 
	 * @param x - the x-coordinate of this new Water's position
	 * @param y - the y-coordinate of this new Water's position
	 * @param direction - the angle (in Radians) from 0 to 2pi that this new 
	 * Water should be both oriented and moving according to.
	 */
	public Water(float x, float y, float direction)
	{
		// Create a graphic instance of this object
		graphic = new Graphic();
		graphic.setX(x);
		graphic.setY(y);		
		graphic.setDirection(direction);
		graphic.setType("WATER");
		
		MAX_DISTANCE_TRAVELED = 200.0F;
		
		speed = 0.7F;
		
		distanceTraveled = 0.0F;
		
	}
	
	/**
	 * This method is called repeatedly by the Game to draw and move the 
	 * current Water. After this Water has moved a total of 200 pixels or 
	 * further, it's graphic should be destroyed and this method should 
	 * return null instead of a reference to this Water.
	 * 
	 * @param time - is the amount of time in milliseconds that has elapsed 
	 * since the last time this update was called.
	 * 
	 * @return a reference to this water for as long as this water has traveled
	 * less than 200 pixels. It should then return null, after this water has
	 * traveled this far.
	 */
	public Water update(int time)
	{
		// The future positions are pre-calculated for ease of reading,
		// and to only consume the processor for this calculation once
		float futureX = graphic.getX() 
				+ (graphic.getDirectionX() 
				* speed * (float)time);
		float futureY = graphic.getY()
				+ (graphic.getDirectionY() 
				* speed * (float)time);
		
		distanceTraveled += (float) Math.sqrt(
				(futureX - graphic.getX())
					* (futureX - graphic.getX()) 
				+ (futureY - graphic.getY())
					* (futureY - graphic.getY()));
		
		// Only set and draw water if it is less than the distance limit
		if(distanceTraveled <= MAX_DISTANCE_TRAVELED)
		{
			graphic.setX(futureX);
			graphic.setY(futureY); 
			graphic.draw();	
			return this;
		}
			
		return null;
	} //END update()
	
	/**
	 * This is a simple accessor for this object's Graphic, which may be used 
	 * by other objects to check for collisions.
	 * 
	 * @return a reference to this Water's Graphic object.
	 */
	public Graphic getGraphic()
	{
		return this.graphic;
	}
	
	

}
