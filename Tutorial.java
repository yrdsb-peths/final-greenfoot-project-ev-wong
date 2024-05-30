import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tutorial class for displaying the tutorial screen.
 */
public class Tutorial extends World
{
    // Labels for tutorial instructions
    Label one = new Label ("Tutorial", 90);
    Label two = new Label ("For more information on how to play", 30);  
    Label three = new Label ("Texas Hold Em' poker, refer to Google", 30);
    Label four = new Label ("Bet chips/raise using the <-- and --> arrows", 30);
    Label five = new Label ("Fold using <f>, Check/Call using <c>", 30);
    Label six = new Label ("Click <ESC> to return to main menu", 30);
    
    /**
     * Constructor for objects of class Tutorial.
     */
    public Tutorial()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        // Add labels to the world
        addObject(one, 300, 70);
        addObject(two, 300, 145);
        addObject(three, 300, 180);
        addObject(four, 300, 240);
        addObject(five, 300, 275);
        addObject(six, 300, 330);
    }
    
    // Act method for user input
    public void act() {
        // Return to main menu if ESC is pressed
        if(Greenfoot.isKeyDown("escape")) {
            TitleScreen gameWorld = new TitleScreen();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
