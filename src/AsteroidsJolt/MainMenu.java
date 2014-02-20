package AsteroidsJolt;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

/**
 * This is the Main Menu for the AsteroidsJolt game where the player will select the Difficulty
 * @author Alex Hockley
 */
public class MainMenu extends BasicGame implements MouseListener{

    /** Container to hold the game when it starts */
    public AppGameContainer appgc;
    
    /** Title of the game */
    String titleStr;
    String playStr;
    String exitStr;
    
    /** Are we on title screen? */
    boolean title = true;
    
    /**
     * Creates the MainMenu container
     * @param s Name of the the container
     */
    public MainMenu(String s)
    {
        super(s);
    }
    
    /**
     * Initializes the screen
     * @param gc Game container we are using
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        titleStr = "Asteroids JOLT";
        playStr = "Play";
        exitStr = "Exit";
        
        gc.getInput().addMouseListener(this);
    }

    /**
     * Nothing to update
     * @param gc
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
    }

    /**
     * Draws the menu to the container
     * @param gc Game container we are using
     * @param g Graphics of the container we are using
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        if(title){
        g.drawString(titleStr, gc.getWidth()/2-50, 0);
        g.drawString(playStr, gc.getWidth()/2, gc.getHeight()/2.5f);
        g.drawString(exitStr, gc.getWidth()/2, gc.getHeight()/2);
        }
        else{
            g.drawString("Easy", gc.getWidth()/2, gc.getHeight()/2.5f);
            g.drawString("Medium", gc.getWidth()/2, gc.getHeight()/2);
            g.drawString("Hard", gc.getWidth()/2, gc.getHeight()/1.5f);
                        
        }
        
        g.drawString("Arrow Keys to move, Space to shoot", 0, gc.getHeight()-30);
    }
    
    /**
     * Starst the game
     * @param num Number of asteroids to start with
     */
    public void play(int num)
    {
        try{
            appgc.destroy(); //destroys title screen
            appgc = new AppGameContainer(new Main("Astroids JOLT",num));
            appgc.setTargetFrameRate(30);
            appgc.setShowFPS(false);
            appgc.setDisplayMode(800, 600, false);
            appgc.start();
        }
        catch(SlickException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * Listener for clicking
     * @param button Mouse button pressed
     * @param x X position clicked on
     * @param y y position clicked on
     * @param clickCount number of times clicked
     */
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
        if(button == 0 && title)
        {
            if( x > appgc.getWidth()/2 && x < appgc.getWidth()/2 + 50 && y > appgc.getHeight()/2.5f && y < appgc.getHeight()/2.5f + 20)
            {
                title = false;
            }
        }
        else if(button == 0 && !title)
        {
            if( x > appgc.getWidth()/2 && x < appgc.getWidth()/2 + 50 && y > appgc.getHeight()/2.5f && y < appgc.getHeight()/2.5f + 20)
            {
                play(2);
            }
            if(x > appgc.getWidth()/2 && x < appgc.getWidth()/2 + 50 && y > appgc.getHeight()/2f && y < appgc.getHeight()/2f + 20)
            {
                play(7);
            }
            if(x > appgc.getWidth()/2 && x < appgc.getWidth()/2 + 50 && y > appgc.getHeight()/1.5f && y < appgc.getHeight()/1.5f + 20)
            {
                play(15);
            }


        }
    }
    
    public static void main (String [] args)
    {
        AppGameContainer appgc1;
        MainMenu game = new MainMenu("Asteroids JOLT");
        try{
            appgc1 = new AppGameContainer(game);
            appgc1.setTargetFrameRate(30);
            appgc1.setShowFPS(false);
            appgc1.setDisplayMode(800, 600, false);
            game.appgc = appgc1;
            game.appgc.start();
        }
        catch(SlickException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
