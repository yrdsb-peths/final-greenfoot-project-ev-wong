/**
 * Write a description of class Dealer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dealer  
{
    private Deck deck;
    
    public Dealer() {
        deck = new Deck();
    }
    
    public Card dealCard() {
        return deck.dealCard();
    }
    
    public void dealToPlayer(Player player) {
        player.addCard(dealCard());
    }
}
