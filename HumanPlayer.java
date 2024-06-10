import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HumanPlayer class represents a human player in the poker game.
 */

public class HumanPlayer extends Player  
{   
    // Method to check if the player is human (overrides superclass method)
    public boolean isHuman() {
        return true; 
    }    
}