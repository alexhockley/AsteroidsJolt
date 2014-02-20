package AsteroidsJolt;

/**
 * Simple exception to be thrown when the player collides with an asteroid
 * @author Alex
 */
public class CollisionException extends Exception{
    
    public CollisionException(String s)
    {
        super("A Collision has occurred: " + s);
    }
}
