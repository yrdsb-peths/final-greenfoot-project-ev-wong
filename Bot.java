import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
    private int randomNum;
    
    // Constructor to create a bot with a name
    public Bot(String name) {
        super();  // Call the superclass constructor to initialize the hand
        this.name = name;  // Set the bot's name
        this.chips = 1000;  // Initialize chips (this seems to be missing initialization)
    }
    
    // Getter for the bot's name
    public String getName() {
        return name;  // Return the bot's name
    }
    
    // Method to handle the bot's turn
    public void botTurn(List<Card> allCards, int currentBet, int pot) {
        List<Card> combinedHand = new ArrayList<>(getHand());  // Combine bot's hand and community cards
        combinedHand.addAll(allCards);  // Add community cards to the bot's hand
        int handStrength = HandEvaluator.evaluateHand(combinedHand);  // Evaluate hand strength
        
        Random rand = new Random();
        randomNum = rand.nextInt(4);
        
        // Simplified logic for bot's actions based on hand strength
        if (randomNum == 0) {
            fold();  // Fold on weak hands
        } else if (randomNum == 1) {
            check();
        } else if (randomNum == 2){
            call();
        } else if (randomNum == 3) {
            raise();
        }
    }
    
    // Method to handle bot raising
    private void raise(int currentBet, int pot) {
        int raiseAmount = (int) (pot * 0.5);  // Example raise amount (adjust as needed)
        chips -= (currentBet + raiseAmount);  // Deduct chips for raising
    }
    

}
