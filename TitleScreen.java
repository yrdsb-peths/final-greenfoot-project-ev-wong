import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TitleScreen
 * 
 * @author Ev
 * @version May 29 2024
 */
public class TitleScreen extends World
{
    Label title = new Label("Ev's Poker Table", 83);
    Label subtitle = new Label("Classic Texas Hold Em'", 59);
    Label insOne = new Label("press <space> to begin", 40);
    Label insTwo = new Label("press <t> for tutorial", 40);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        addObject(title, 300, 90);
        addObject(subtitle, 300, 175);
        addObject(insOne, 300, 270);
        addObject(insTwo, 300, 320);
        prepare();
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("space")) {
            PokerWorld gameWorld = new PokerWorld();
            Greenfoot.setWorld(gameWorld);
        }
        
        if(Greenfoot.isKeyDown("t")) {
            Tutorial gameWorld = new Tutorial();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
