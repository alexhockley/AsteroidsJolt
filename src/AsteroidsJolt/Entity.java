package AsteroidsJolt;

import org.newdawn.slick.Input;

/**
 * Entity is the primary class for keeping track of a character or asteroid.
 * @author Alex Hockley
 */
public abstract class Entity {
    
    /** Name of the entity */
    protected String name;
    
    /**X position of the entity*/
    protected float posx;
    /**Y position of the entity*/
    protected float posy;
    
    /** Speed of the entity */
    protected float speed;
    
    /** Entity's controls */
    protected Controls controls;
    
    
    /** Sets the speed of the entity */
    public void setSpeed(float s)
    {
        this.speed = s;
    }
    
    /** Sets the x position tracker of the entity*/
    public void setX(float x)
    {
        this.posx = x;
    }
    
    /** Sets the Y position tracker of the entity*/
    public void setY(float y)
    {
        this.posy=y;
    }
    
    /**
     * Gets the Controls object from the entity
     * @return Controls of the entity
     */
    public Controls getControls()
    {
        return this.controls;
    }
    
    /**
     * Updates the Entity's position based on what key is pressed in the controls
     * @param direction Direction pressed on the keyboard
     */
    public void move(Input direction)
    {
        if(direction.isKeyDown(controls.MOVE_FORWARD_CONTROL))
            this.posy = this.posy-this.speed;
        else if (direction.isKeyDown(controls.MOVE_BACKWARD_CONTROL))
            this.posy = this.posy+this.speed;
        if(direction.isKeyDown(controls.MOVE_LEFT_CONTROL))
            this.posx = this.posx-this.speed;
        else if (direction.isKeyDown(controls.MOVE_RIGHT_CONTROL))
            this.posx = this.posx+this.speed;
    }
    
    
}
