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
    
    // Getter for the bot's name
    public String getName() {
        return name;  // Return the bot's name
    }
    
    // Method to handle the bot's turn
    public void botTurn(List<Card> allCards, int currentBet, int pot) {
        List<Card> combinedHand = new ArrayList<>(getHand());  // Combine bot's hand and community cards
        combinedHand.addAll(allCards);  // Add community cards to the bot's hand
        int handStrength = HandEvaluator.evaluateHand(combinedHand);  // Evaluate hand strength
        
        // Simplified logic for bot's actions based on hand strength
        if (handStrength <= 2) {
            fold();  // Fold on weak hands
        } else if (handStrength <= 4) {
            if (currentBet > 0) {
                call(currentBet);  // Call if there's a bet
            } else {
                check();  // Check if no bet
            }
        } else {
            raise(currentBet, pot);  // Raise on strong hands
        }
    }
    
    // Method to handle bot checking
    private void check() {
        // Implement check logic here if needed
    }
    
    // Method to handle bot calling
    private void call(int currentBet) {
        chips -= currentBet;  // Deduct chips for calling
    }
    
    // Method to handle bot raising
    private void raise(int currentBet, int pot) {
        int raiseAmount = (int) (pot * 0.5);  // Example raise amount (adjust as needed)
        chips -= (currentBet + raiseAmount);  // Deduct chips for raising
    }
    
    // Method to handle bot folding
    private void fold() {
        // Implement fold logic here if needed
    }
    
    
    public class HandEvaluator {
        public static int evaluateHand(List<Card> hand) {
            hand.sort((card1, card2) -> card1.getRank() - card2.getRank());
    
            boolean isFlush = hand.stream().allMatch(card -> card.getSuit().equals(hand.get(0).getSuit()));
            boolean isStraight = true;
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i + 1).getRank() != hand.get(i).getRank() + 1) {
                    isStraight = false;
                    break;
                }
            }
    
            if (isFlush && isStraight) return 9; // Straight flush
            if (isFlush) return 6; // Flush
            if (isStraight) return 5; // Straight
    
            int[] rankCounts = new int[14]; // Index 0 unused, ranks 1-13
            for (Card card : hand) {
                rankCounts[card.getRank()]++;
            }
    
            int pairs = 0, threes = 0, fours = 0;
            for (int count : rankCounts) {
                if (count == 2) pairs++;
                if (count == 3) threes++;
                if (count == 4) fours++;
            }
    
            if (fours > 0) return 8; // Four of a kind
            if (threes > 0 && pairs > 0) return 7; // Full house
            if (threes > 0) return 4; // Three of a kind
            if (pairs > 1) return 3; // Two pairs
            if (pairs > 0) return 2; // Pair
    
            return 1; // High card
        }
    }
}
