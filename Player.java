import java.util.List;
import java.util.ArrayList;

/**
 * Player class represents a player in the poker game.
 */
public class Player  
{
    private List<Card> hand;  // List to hold the player's hand
    
    // Constructor to initialize the player's hand
    public Player() {
        hand = new ArrayList<Card>();  // Initialize the hand as an empty list
    }
    
    // Method to add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);  // Add the card to the hand
    }
    
    // Getter for the player's hand
    public List<Card> getHand() {
        return hand;  // Return the list representing the player's hand
    }
    
    // Method to check if the player is human (to be overridden by subclasses)
    public boolean isHuman() {
        return false;  // Default implementation assumes non-human player
    }
}
