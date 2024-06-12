import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class Bot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * Bot class represents a computer-controlled player (bot) in the poker game.
 */
public class Bot extends Player 
{
    private String name;  // Name of the bot
    private int chips;  // Chips for the bot
    
    // Constructor to create a bot with a name
    public Bot(String name) {
        super();  // Call the superclass constructor to initialize the hand
        this.name = name;  // Set the bot's name
        this.chips = 1000;  // Initialize chips (this seems to be missing initialization)
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    public boolean isHuman() {
        return false;  
    }
    
    // Getter for the bot's name
    public String getName() {
        return name;  // Return the bot's name
    }
}
