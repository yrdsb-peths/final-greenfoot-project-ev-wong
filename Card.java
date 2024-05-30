import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    private String suit;
    private int rank;
    
    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
