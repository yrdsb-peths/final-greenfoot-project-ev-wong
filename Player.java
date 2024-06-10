import java.util.List;
import java.util.ArrayList;

/**
 * Player class represents a player in the poker game.
 */
public class Player  
{
    private List<Card> hand;  // List to hold the player's hand
    private boolean isSmallBlind;
    private boolean isBigBlind;
    private boolean inRound;
    private int currentBet;
    private int chips;
    private String name;
    
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
    
    public void setChips(int chips) {
        this.chips = chips;
    }
    
    public int getChips() {
        return chips;
    }
    
    public String getName() {
        return name;
    }
    
    // Method to check if the player is human (to be overridden by subclasses)
    public boolean isHuman() {
        return false;  // Default implementation assumes non-human player
    }
    
    public boolean isSmallBlind() {
        return isSmallBlind;
    }
    
    public boolean isBigBlind() {
        return isBigBlind;
    }
    
    public void setSmallBlind(boolean isSmallBlind) {
        this.isSmallBlind = isSmallBlind;
    }
    
    public void setBigBlind(boolean isBigBlind) {
        this.isBigBlind = isBigBlind;
    }
    
    public boolean isInRound() {
        return inRound;
    }
    
    public void setInRound(boolean inRound) {
        this.inRound = inRound;
    }
    
    public int getCurrentBet() {
        return currentBet;
    }
    
    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
    
    public void bet(int amount) {
        if (amount <= chips) {
            chips -= amount;
            currentBet += amount;
        }
    }
    
    public void fold() {
        inRound = false;
    }
    
    public void raise() {
        chips -= 20;
    }
    
    public void call() {
        chips -= currentBet;
    }
    
    public void check() {
        // Implement check logic here if needed
    }
    
}
