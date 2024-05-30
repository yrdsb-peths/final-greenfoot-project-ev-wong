import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardDisplay extends Actor
{
    private Card card;
    private String cardImage;
    
    public CardDisplay(Card card) {
        this.card = card;
        String imageName = "card_" + card.getSuit() + "_" + card.getRankName() + ".png";
        setImage(imageName);
    }
    
    public Card getCard() {
        return card;
    }
}
