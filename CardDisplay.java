import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CardDisplay class represents the visual display of a card.
 */
public class CardDisplay extends Actor
{
    private Card card;  // Reference to the card object
    private String cardImage;  // File name of the card image
    
    // Constructor to create a CardDisplay with a given card
    public CardDisplay(Card card) {
        this.card = card;
        // Generate the file name for the card image based on suit and rank
        String imageName = "card_" + card.getSuit() + "_" + card.getRankName() + ".png";
        setImage(imageName);  // Set the image of the CardDisplay
    }
    
    // Getter for the card object
    public Card getCard() {
        return card;
    }
}