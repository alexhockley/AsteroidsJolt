package AsteroidsJolt;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Projectile is a specialized Entity that is shot from the MainCharacter
 * @author Alex Hockley
 */ 
public class Projectile extends Entity{
    /** The shape for the projectile*/
    Shape bullet;
    /**Length of the projectile */
    protected float length;
    /**angle the projectile is traveling relative to 0 (up)*/
    protected float angle; //angle relative to zero (up)
        
    /**Maximum x value (not used) */
    protected float maxX;
    /**Maximum y value (not used) */
    protected float maxY; //both maxes to get from game board
    
    
    /**
     * Creates a projectile with set values
     * @param sX Starting x position
     * @param sY Starting y position
     * @param mX max x position
     * @param mY max y position
     * @param pAngle projectile angle
     */
    public Projectile(float sX, float sY, float mX, float mY, float pAngle)
    {
        float endX, endY; //ending positions of the shape
        length = 25; //size
        angle = pAngle;
        endX = sX + (length*(float)(Math.sin(angle)));
        endY = sY + (-length*(float)(Math.cos(angle)));
        
        //System.out.println(sX);
        
        
        
        name = "Bullet";
        
        
        bullet = new Line(sX,sY,endX,endY);
        speed = 10;
        this.maxX = mX;
        this.maxY = mY;
        controls = null; // no controls
        
    }
    
    /**
     * Translates the projectile to its new position and updates the x and y trackers
     */
    public void move(){
             
        this.bullet = this.bullet.transform(Transform.createTranslateTransform((float)(speed*Math.sin(angle)),(float)(speed*Math.cos(angle)*-1)));
        this.posx = this.posx+(float)(speed*Math.sin(angle));
        this.posy = this.posy+(float)(-speed*Math.cos(angle));
         
    }
}
