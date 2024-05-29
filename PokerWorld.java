import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PokerWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PokerWorld extends World
{

    /**
     * Constructor for objects of class PokerWorld.
     * 
     */
    public PokerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        CardBack cardBack = new CardBack();
        addObject(cardBack,270,363);
        CardBack cardBack2 = new CardBack();
        addObject(cardBack2,330,363);
        CardBack cardBack3 = new CardBack();
        addObject(cardBack3,300,160);
        CardBack cardBack4 = new CardBack();
        addObject(cardBack4,350,160);
        CardBack cardBack5 = new CardBack();
        addObject(cardBack5,250,160);
        CardBack cardBack6 = new CardBack();
        addObject(cardBack6,200,160);
        CardBack cardBack7 = new CardBack();
        addObject(cardBack7,400,160);
    }
}
