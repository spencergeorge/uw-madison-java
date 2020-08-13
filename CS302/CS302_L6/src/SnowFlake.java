import processing.core.*;
import java.util.Random;
public class SnowFlake 
{
	private int x;
    private int y;
    private int size;
    private Random random = new Random();
    
    public SnowFlake( int x, int y, int size ) 
    {
        this.x = x ;
        this.y = y ;
        this.size = size ;
    }
    
	public void draw(processing.core.PApplet applet) 
	{
		 // DRAW A WHITE SNOW FLAKE  
        applet.stroke(255);
        applet.fill(255);
        applet.ellipse(x-1,y-1,size,size); // small filled in circle
        applet.line(x-size-1-1, y-size-1-1, x+size+1-1, y+size+1-1);  // \
        applet.line(x-size-1-1, y-1,        x+size+1-1, y-1);         // -
        applet.line(x-1,        y-size-1-1, x-1,        y+size+1-1);  // |
        applet.line(x-size-2-1, y+size+2-1, x+size+2-1, y-size-2-1);  // /
        
        move(random.nextInt(1 + 2) - 1, 1);
        
        if(this.y >= 300)
        {
        	x = (int)(Math.random()*applet.width);
        	y = 0;
        }
        
    }
	
	public void move(int dx, int dy)
	{
		x = x + dx;
		y = y + dy;
	}
}
