import processing.core.*;
public class SnowStudent {

    // All SnowStudents have a name
    private String name; // the name displayed when drawn
    private float x; // the x-coord of center line of SnowStudent
    private float y; // the y-coord of the base of SnowStudent
    
    // Each part of the SnowStudent is a SnowBall of a given radius
    private SnowBall base, body, head;
    
    private float BASE_WIDTH = 30;
    private float BODY_WIDTH = 20;
    private float HEAD_WIDTH = 10;
    private float BASE_RADIUS = BASE_WIDTH / 2;
    private float BODY_RADIUS = BODY_WIDTH / 2;
    private float HEAD_RADIUS = HEAD_WIDTH / 2;

    /**
     * Construct a SnowStudent with the given name at the given position.  
     * The snow student will also be assigned the next name number.
     * 
     * @param name The SnowStudent's name
     * @param xCenter The position along the x-axis in pixels for 
     *  the center of the SnowStudent
     * @param yBase The position along the y-axis for the 
     *  bottom of SnowStudent
     */
    public SnowStudent(String name, float xCenter, float yBase ) {
        this.name = name;
        this.x = xCenter;
        this.y = yBase; // bottom of the base of the snowball
        this.base = new SnowBall(x,y-BASE_RADIUS,BASE_RADIUS); 
        // TODO: add code to initialize body SnowBall here
        
        
        this.body = new SnowBall(x,y-BASE_WIDTH-BODY_RADIUS,BODY_RADIUS);
        
        // TODO: add code to initialize head SnowBall here
        this.head = new SnowBall(x,y-BASE_WIDTH-BODY_WIDTH-HEAD_RADIUS,HEAD_RADIUS);
    }
    

    /** 
     * Returns the name of this SnowStudent.
     * @return The name of this SnowStudent
     */
    public String getName() { 
        return name; 
    }
    
    /** 
     * Returns the height of this SnowStudent in pixels.
     * height = height of base + height of body + height of head
     * 
     * @return height in pixels of this SnowStudent
     */
    public float getHeight() {
        return base.getHeight() + body.getHeight() + head.getHeight();
    }

    /** 
     * Draw this instance of a SnowStudent at the specified position.
     * @param applet The applet to draw this SnowStudent on
     */
    public void draw(processing.core.PApplet applet) {
        applet.stroke(255); // white
        
        // draw the SnowBall (base,body,head) of the SnowStudent
        base.draw(applet);
        body.draw(applet);
        head.draw(applet);
    }
    
    /**
     * Moves this SnowStudent by changing the position of the anchor point (x,y)
     * @param dx The change in x (+ moves right and - moves left)
     * @param dy The change in y (+ moves up and - moves down)
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // move the snow balls
        base.move(dx,dy);
    }

    /**
     * Returns the y-Coordinate of the base of the snowStudent's base snowball
     * @return y-coordinate at the base of the Snow Student
     */
    public float getBaseY() {
        return y+BASE_RADIUS;
    }

    /**
     * Used to help determine where left or right edge is.
     * @return Returns the radius of the base SnowBall of this SnowStudent 
     */
    public float getBaseRadius() {
        return base.getRadius();
    }
    
    /**
     * Returns the center of the snow student along x-axis.
     * @return Returns the position of the center of the SnowStudent along the x-axis 
     */
    public float getCenterX() {
        return base.getX();
    }

    /**
     * Change the name of this SnowStudent
     * @param name The new name of this SnowStudent
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * Return the right edge of this SnowStudent
     * so we can know when they crossed the finish line.
     * @return The x-coordinate of the right-most part of this SnowStudent
     */
    public float getRightEdge() {        
        // TODO calculate the x-coordinate of the right edge
        return -1;
    }
    
}
