package AsteroidsJolt;

import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

/**
 * MainCharacter is a special entity to be controlled by a player.
 * @author Alex Hockley
 */
public class MainCharacter extends Entity{
    /** Width of the shape*/
    protected float width;
    /** Height of shape*/
    protected float height;
    /** Angle the shape is facing, relative to 0 (up)*/
    protected float angle = 0; 
    
    /** Speed the shape can rotate */
    protected float rotateSpeed = .2f;
    
    /** Current speed */
    protected float currSpeed = 0;
    /** Maximum speed */
    protected float maxSpeed = 5;
  
    /** Trajectory angle, starting at 0 (up) */
    protected float trajAngle = angle;
    
    /**Maximum x position */
    protected float maxX;
    /**Maximum y position*/
    protected float maxY; //both maxes to get from game board
    
    /** Main Characters shape */
    Polygon shape;
    
    /** Collection of Projectiles from this entity */
    ArrayList<Projectile> projectiles = new ArrayList();
    
    /**
     * Creates a MainCharacter with specified controls
     * @param c Controls object for the Character
     */
    public MainCharacter(Controls c)
    {
        this("Main Character", 5,0,0,c,1024,768);
    }
    
    
    /**
     * Creates a MainCharacter object with all field initialized
     * @param n Name of the Entity
     * @param speedArg Starting speed of the entity
     * @param x Starting x position of the Entity
     * @param y Starting y position of the Entity
     * @param c Controls for the Entity
     * @param maxX Maximum X value for the Entity
     * @param maxY Maximum Y Value for the Entity
     */
    public MainCharacter(String n, float speedArg, float x,float y ,Controls c, float maxX, float maxY)
    {
        this.name =n;
        this.speed = speedArg;
        this.posx = x;
        this.posy=y;
        this.controls=c;
        
        this.maxX = maxX;
        this.maxY = maxY;
        
        this.width = 10;
        this.height = 15;
        this.shape = new Polygon();
        setupShape();
        
    }
    
    /**
     * Sets up the MainCharacter's shape object to look like a triangle
     */
    final void setupShape()
    {
        
        shape.addPoint((width/2), 0);
        shape.addPoint(0,height);
        shape.addPoint(width,height);
        shape.setX(this.posx);
        shape.setY(this.posy);
    }
    
    /**
     * Moves the main character depending on the key pressed
     * @param direction Input received from the keyboard
     */
    @Override
    public void move(Input direction){
        
        if(direction.isKeyDown(controls.MOVE_FORWARD_CONTROL)){
            if(currSpeed + speed < maxSpeed){
                currSpeed = currSpeed + speed;
            }
            else
                currSpeed = maxSpeed;
            this.trajAngle = angle;
        }
        else if (direction.isKeyDown(controls.MOVE_BACKWARD_CONTROL))
        {
            if(currSpeed - (speed*2) > (-maxSpeed/2)){
                currSpeed = currSpeed - speed*2;
            }
            else
                currSpeed = -maxSpeed/2;
             this.trajAngle = angle;
        }
        if(direction.isKeyDown(controls.MOVE_LEFT_CONTROL)){
            this.shape = (Polygon)this.shape.transform(Transform.createRotateTransform(-rotateSpeed,shape.getCenterX(), shape.getCenterY()));
            this.angle=this.angle - rotateSpeed;
            
        }
        else if (direction.isKeyDown(controls.MOVE_RIGHT_CONTROL))
        {
            this.shape = (Polygon)this.shape.transform(Transform.createRotateTransform(rotateSpeed,shape.getCenterX(), shape.getCenterY()));
            this.angle = this.angle + rotateSpeed;
        }
        
         this.shape = (Polygon)this.shape.transform(Transform.createTranslateTransform((float)(currSpeed*Math.sin(trajAngle)),(float)(currSpeed*Math.cos(trajAngle)*-1)));
         this.posx = this.posx+(float)(currSpeed*Math.sin(trajAngle));
         this.posy = this.posy+(float)(currSpeed*Math.cos(trajAngle)*-1);
        //System.out.println(posy);
        if(direction.isKeyPressed(controls.SHOOT_CONTROL))
        {           
            projectiles.add(new Projectile(posx,posy,maxX,maxY,angle));
        }
         
        updateShape(); //update trackers
        updateProjectiles(); //update projectiles position
        
    }
    
    /**
     * Checks edges of the board for if the Entity is outside of it
     */
    private void updateShape()
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
     * Moves the projectiles tied to this MainCharacter
     */
    private void updateProjectiles()
    {
        for(Projectile proj : projectiles)
        {
            proj.move();
        }
    }
    
}
