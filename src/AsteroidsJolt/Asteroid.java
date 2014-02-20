package AsteroidsJolt;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Asteroid is a specific entity that is the primary "enemy" in AsteroidsJolt.
 * It flies around with a random trajectory until it is destroyed
 * @author Alex Hockley
 */
public class Asteroid extends Entity{
    
    /** Diameter of the asteroid */
    protected float diameter;
    
    /** Angle of the Asteroid's trajectory, relative to 0 (up)*/
    protected float angle = 0; 
    
    /** Speed the Asteroid is rotating. UNUSED */
    protected float rotateSpeed = .3f;
     
    /** Maximum X value for the Asteroid */
    protected float maxX;
    /** Maximum Y value for the Asteroid */
    protected float maxY;
    
    /** Shape object to draw and translate the asteroid */
    Shape shape;
    
    /**
     * Creates an asteroid with a specified maximum x and y.
     * Randomly generates a diameter between 25 and 50.
     * Randomly generates a speed between 0.5 and 4
     * @param x Maximum X value of the game space
     * @param y Maximum Y value of the game space
     */
    public Asteroid(float x, float y)
    {
        name = "Asteroid";
        diameter = 25+(float)Math.random()*((50-25)+1);
        calculateAngleAndStart();
        setupShape();
        speed = (float)Math.random()*(4-.5f)+.5f;
        this.maxX = x;
        this.maxY = y;
        controls = null;//no controls
        
    }
    
    /**
     * Creates the shape for the constructor.
     */
    final void setupShape()
    {
        shape = new Ellipse(posx,posy,diameter/2,diameter/2);
    }
    
    
    /**
     * Randomly chooses an edge of the screen space for the Asteroid to start on and sets it trajectory.
     * With the edge, it determines what direction the asteroid should be traveling
     */
    private void calculateAngleAndStart()
    {
        int edge = (int)Math.random()*3;
        switch(edge)
        {
            case(0): //top
                angle = (float)Math.random()*(260-100)+100;
                posx = (float)Math.random()*((maxX-100)-100)+100;
                posy=0;
                break;
            case(1): //left
                angle = (float)Math.random()*(170-10)+10;
                posy = (float)Math.random()*((maxY-100)-100)+100;
                posx=0;
                break;
            case(2)://bottom
                angle = (float)Math.random()*(440-280)+280;
                posx = (float)Math.random()*((maxX-100)-100)+100;
                posy=maxY;
                break;
            case(3)://right
                angle = (float)Math.random()*(350-190)+190;
                posy = (float)Math.random()*((maxY-100)-100)+100;
                posx=maxX;
                break;
        }
        
    }
    /**
     * Translates the Asteroid to its next position, updating any positional trackers and checking if it moves outside the screen space.
     */
    public void move(){
             
        this.shape = this.shape.transform(Transform.createTranslateTransform((float)(speed*Math.sin(angle)),(float)(speed*Math.cos(angle)*-1)));
        this.posx = this.posx+(float)(speed*Math.sin(angle));
        this.posy = this.posy+(float)(speed*Math.cos(angle)*-1);
         
         updateShape();
    }
    
    /**
     * Checks if the Asteroid is out of bounds and updates it if it is.
     */
    public void updateShape()
    {
        if(posx > maxX){
            posx = 0;
            this.shape.setX(-maxX);
        }
        else if(posx < 0)
        {
            posx = maxX;
            this.shape.setX(posx);
        }
        if(posy < 0)
        {
            posy = maxY;
            this.shape.setY(posy);
        }
        else if(posy > maxY)
        {
            posy = 0;
            this.shape.setY(-maxY);
        }
    }
    
    /**
     * Creates an asteroid fragment
     * @return A smaller version of the current asteroid
     */
    public Asteroid split()
    {
        Asteroid a = new Asteroid (this.maxX, this.maxY);
        a.posx = this.posx;
        a.posy = this.posy;
        a.diameter = this.diameter/2;
        a.setupShape();
        return a;
    }
    
}
