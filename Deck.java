import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck extends Actor
{
    private List<Card> cards;
    
    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = {"spades", "hearts", "clubs", "diamonds"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle();
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public Card dealCard() {
        return cards.remove(0);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
