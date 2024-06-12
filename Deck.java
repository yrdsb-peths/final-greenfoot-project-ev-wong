import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class represents a deck of playing cards.
 */
public class Deck extends Actor
{
    private List<Card> cards;  // List to hold the cards in the deck
    
    // Constructor to initialize the deck with a full set of cards
    public Deck() {
        cards = new ArrayList<>();  // Initialize the list
        cards.clear();
        String[] suits = {"spades", "hearts", "clubs", "diamonds"};  // Array of card suits
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(suit, rank));  // Add each card to the deck
            }
        }
        shuffle();  // Shuffle the deck
    }
    
    public Card removeCard() {
        return cards.remove(0);  // Remove and return the first card from the deck
    }
    
    // Method to shuffl e the deck
    public void shuffle() {
        Collections.shuffle(cards);  // Use Java's Collections.shuffle() method
    }

    // Act method
    public void act()
    {
        // Add your action code here if needed
    }
}
