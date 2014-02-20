package AsteroidsJolt;

/**
 * Java class to encapsulate the Controls of an entity. Intended to be used with Slick2D Input class
 * @author Alex Hockley
 */
public class Controls {
    /** Control to move the entity left */
    protected final int MOVE_LEFT_CONTROL;
    /** Control to move the entity right */
    protected final int MOVE_RIGHT_CONTROL;
    /** Control to move the entity forward */
    protected final int MOVE_FORWARD_CONTROL;
    /** Control to move the entity backward */
    protected final int MOVE_BACKWARD_CONTROL;
    
    /** Control to shoot with the entity */
    protected final int SHOOT_CONTROL;
    
    /** Creates a Controls object with specified buttons for each action */
    public Controls(int left, int right, int up, int down, int shoot)
    {
        this.MOVE_LEFT_CONTROL=left;
        this.MOVE_RIGHT_CONTROL=right;
        this.MOVE_FORWARD_CONTROL=up;
        this.MOVE_BACKWARD_CONTROL=down;
        this.SHOOT_CONTROL = shoot;
    }
}
