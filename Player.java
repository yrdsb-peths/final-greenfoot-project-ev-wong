import java.util.List;
import java.util.ArrayList;

/**
 * Player class represents a player in the poker game.
 */
public class Player {
    private List<Card> hand;  
    private boolean isSmallBlind;
    private boolean isBigBlind;
    private boolean inRound;
    private int currentBet;
    private int chips;
    private String name;
    
    public Player() {
        hand = new ArrayList<Card>();  
        chips = 1000;
        currentBet = 0;
        inRound = true;
    }
    
    public void addCard(Card card) {
        hand.add(card);  
    }
    
    public List<Card> getHand() {
        return hand;  
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
    
    public boolean isHuman() {
        return this.isHuman();  
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
        return this.inRound;
    }
    
    public void setInRound(boolean inRound) {
        this.inRound = inRound;
    }
    
    public int getCurrentBet() {
        return currentBet;
    }
    
    public void clearHand() {
        hand.clear(); // Assuming 'hand' is the list of cards in the player's hand
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
        this.inRound = false;
    }
    
    public void raise() {
        currentBet += 20;
        this.chips -= currentBet;
    }
    
    public void call() {
        this.chips -= currentBet;
    }
    
    public void check() {
        // Implement check logic here if needed
    }
}
