import java.util.List;
import java.util.ArrayList;

/**
 * House class represents the cards on the table in the poker game.
 */

public class House  
{
    private List<Card> table;  // List to hold the player's hand

    public House() {
        table = new ArrayList<Card>();
    }
    
    public void addTableCard(Card card) {
        table.add(card);  // Add the card to the hand
    }
    
    public List<Card> getTableCards() {
        return table;
    }
}
