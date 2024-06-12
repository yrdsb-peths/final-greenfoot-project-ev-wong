import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HumanPlayer class represents a human player in the poker game.
 */

public class HumanPlayer extends Player  
{   
    private String name;  // Name of the bot
    private int chips;  // Chips for the bot
    
    public HumanPlayer(String name) {
        super();  // Call the superclass constructor to initialize the hand
        this.name = "human";  // Set the bot's name
        this.chips = 1000;  // Initialize chips (this seems to be missing initialization)
    }
    
    // Method to check if the player is human (overrides superclass method)
    public boolean isHuman() {
        return true; 
    }    
    
    @Override
    public String toString(){
        return name;
    }
}