import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TitleScreen class for displaying the main menu of the game.
 */
public class TitleScreen extends World
{
    // Labels for title, subtitle, and instructions
    Label title = new Label("Ev's Poker Table", 83);
    Label subtitle = new Label("Classic Texas Hold Em'", 59);
    Label insOne = new Label("press <space> to begin", 40);
    Label insTwo = new Label("press <t> for tutorial", 40);
    
    GreenfootSound music = new GreenfootSound("bgmusic.mp3");

    /**
     * Constructor for objects of class TitleScreen.
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        // Add labels to the world
        addObject(title, 300, 90);
        addObject(subtitle, 300, 175);
        addObject(insOne, 300, 270);
        addObject(insTwo, 300, 320);
        prepare();  // Initialize the world
    }

    // Act method for user input
    public void act()
    {
        
        // Start the game if space is pressed
        if(Greenfoot.isKeyDown("space")) {
            PokerWorld gameWorld = new PokerWorld();
            Greenfoot.setWorld(gameWorld);
        }
        // Show tutorial if 't' is pressed
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
        // Additional preparation can be added here if needed
    }
}
