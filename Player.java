import java.util.List;
import java.util.ArrayList;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player  
{
    private List<Card> hand;
    
    public Player() {
        hand = new ArrayList<Card>();
    }
    
    public void addCard(Card card) {
        hand.add(card);
    }
    
    public List<Card> getHand() {
        return hand;
    }
    
    public boolean isHuman() {
        return false;
    }
}
