import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Card class represents a playing card.
 */
public class Card extends Actor
{
    private String suit;  // Suit of the card (e.g., spades, hearts)
    private int rank;  // Rank of the card (1-13)

    // Constructor to create a card with given suit and rank
    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    // Getters for suit and rank
    public String getSuit() {
        return suit;
    }
    
    public int getRank() {
        return rank;
    }
    
    // Get the name of the rank (e.g., "A" for Ace, "K" for King)
    public String getRankName() {
        switch (rank) {
            case 1: return "A";
            case 2: return "02";
            case 3: return "03";
            case 4: return "04";
            case 5: return "05";
            case 6: return "06";
            case 7: return "07";
            case 8: return "08";
            case 9: return "09";
            case 10: return "10";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return Integer.toString(rank);
        }
    }

    // Act method
    public void act()
    {
        // Add your action code here if needed
    }
}
