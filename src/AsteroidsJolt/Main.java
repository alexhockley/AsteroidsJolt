package AsteroidsJolt;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
        
/**
 * This class handles all of the actual gameplay elements, updating and drawing of the game
 * @author Alex Hockley
 */
public class Main extends BasicGame{
    
    /** Collection of asteroids */
    ArrayList<Asteroid> asteroids;
    /** Main character entity */
    MainCharacter character;
    
    /** Game container which holds the game */
    AppGameContainer appgc;
    
    /** Player's score */
    float score = 0;
    
    /** Number of asteroids to create */
    int numAsteroids;
    
    /**
     * Creates a game container and initializes variables used to create the game
     * @param gamename Name of the game
     * @param startAsteroids Number of asteroids to start the game with
     */
    public Main(String gamename, int startAsteroids)
    {
	super(gamename);
        asteroids = new ArrayList();
        numAsteroids = startAsteroids;
        
    }

    /**
     * Initializes the playing field and all starting objects in the game
     * @param gc Game container the game takes place in, passed by default.
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        for(int i=0; i<numAsteroids;i++)
        {
            asteroids.add(new Asteroid( gc.getWidth(),gc.getHeight()));
        }
       character = new MainCharacter("MainCharacter", .1f, gc.getWidth()/2, gc.getHeight()/2,new Controls( Input.KEY_LEFT,Input.KEY_RIGHT,Input.KEY_UP,Input.KEY_DOWN, Input.KEY_SPACE), gc.getWidth(),gc.getHeight());

    }

    /**
     * Updates the entities, score and associated trackers
     * @param gc Game container passed by default
     * @param i Unused
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        character.move(gc.getInput());//moves the character
        updateAsteroids(gc); //updates the asteroids
        
        
        try{
            checkCollision(gc);//checks if the main character collided with an asteroid
        }catch(CollisionException e)
        {
              JOptionPane.showMessageDialog(null,"Score is: " + score);
              gc.exit();
        } catch (BulletCollideException ex) { //asteroid was shot
           score = score + 100;
        }
        score = score + (0.1f*numAsteroids); //update score each frame
    }

    /**
     * Draws the objects to the game screen
     * @param gc Game container passed by default
     * @param g Graphics passed by default
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.draw(character.shape);
        
        for(Asteroid a : asteroids)
        {
            drawAsteroid(gc,a);
        }
        for(Projectile proj : character.projectiles)
            drawProjectiles(gc,proj);
        
        //g.drawString("Score: " + (int)score, 0, 50); //broke somehow when main menu was added
        
    }
    
    /**
     * Draws the asteroid to the game container
     * @param gc Game container containing the game (lol)
     * @param toUpdate Asteroid to be drawn
     */
    public void drawAsteroid(GameContainer gc, Asteroid toUpdate)
    {
        gc.getGraphics().draw(toUpdate.shape);
        
    }
    
    /**
     * Draws a projectile to the game screen
     * @param gc Game container containing the current game
     * @param toUpdate Projectile to be drawn
     */
    public void drawProjectiles(GameContainer gc, Projectile toUpdate)
    {
        gc.getGraphics().draw(toUpdate.bullet);
    }
    
    /**
     * Draws all the asteroids to the game screen
     * @param gc Game container containing the game
     */
    public void updateAsteroids(GameContainer gc)
    {
        Asteroid a;
        for(int i = 0; i < asteroids.size(); i++) // moves each asteroid
        {
            a = asteroids.get(i);
            a.move();
        }
        if(asteroids.isEmpty()) //no asteroids left
        {
            numAsteroids = numAsteroids * 2; //double num of asteroids to be created
            for(int i=0; i<numAsteroids;i++)
            { 
                asteroids.add(new Asteroid( gc.getWidth(),gc.getHeight())); //generate new asteroids
            }
        }
    }
    
    /**
     * Checks if the main character or projectile collided with an asteroid
     * @param gc Game container containing the game
     * @throws CollisionException Main character collided
     * @throws BulletCollideException Projectile collided
     */
    public void checkCollision(GameContainer gc) throws CollisionException, BulletCollideException
    {
        for(Asteroid a : asteroids) //loops through asteroids
        {
            if(character.shape.intersects(a.shape)) //did collide with player?
                throw new CollisionException("You Collided with an Asteroid");
            
            for(Projectile p : character.projectiles) //loops through active projectiles
            {
                if(p.bullet.intersects(a.shape)) //did collide with projectile?
                {
                    character.projectiles.remove(p);//gets rid of projectile
                    asteroids.remove(a); //gets rid of asteroid
                    if(a.diameter > 15) //if asteroid is big enough
                    {
                        asteroids.add(a.split()); //split in two
                        asteroids.add(a.split());
                    }                   
                    throw new BulletCollideException("You shot an asteroid");
                }
            }
        }
    }
    
}
