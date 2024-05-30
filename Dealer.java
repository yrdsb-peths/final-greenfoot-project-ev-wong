/**
 * Dealer class represents a dealer in the poker game.
 */
public class Dealer  
{
    private Deck deck;  // Reference to the deck
    
    // Constructor to create a dealer with a deck
    public Dealer() {
        deck = new Deck();  // Create a new deck for the dealer
    }
    
    // Method to deal a card from the deck
    public Card dealCard() {
        return deck.dealCard();  // Delegate to the deck to deal a card
    }
    
    // Method to deal a card to a player
    public void dealToPlayer(Player player) {
        player.addCard(dealCard());  // Deal a card to the player
    }
}
