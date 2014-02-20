package AsteroidsJolt;

/**
 * Simple exception to be thrown when a bullet collides with an asteroid.
 * @author Alex Hockley
 */
public class BulletCollideException extends Exception{
    public BulletCollideException(String s)
    {
        super("Something has been shot: " + s);
    }
}
