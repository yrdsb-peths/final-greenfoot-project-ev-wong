/**
* Manages the deck of cards and deals cards to players. 
*/
public class Dealer  
{
    private Deck deck;  // Deck of cards
    
    // Creates a new dealer with a fresh deck of cards.
    public Dealer() {
        deck = new Deck();  // Initialize deck
        deck.shuffle(); // Shuffles deck
    }
    
    // Deals a card from the deck to a player.
    public Card dealCard() {
        return deck.removeCard();  // Remove and return a card
    }
    
    // Deals a card to a player.
    public void dealToPlayer(Player player) {
        player.addCard(dealCard());  // Give a card to player
    }
}
