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
        inRound = true;
        currentBet = 0;
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
        return false;  
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
        if (chips >= 20) {
            chips -= 20;
            currentBet += 20;
        }
    }
    
    public void call() {
        chips -= currentBet;
    }
    
    public void check() {
        // Implement check logic here if needed
    }
}
