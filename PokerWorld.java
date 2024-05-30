import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class PokerWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PokerWorld extends World
{
    private Dealer dealer;
    private Player player;
    private List<Bot> bots;
    private List<Card> allCards;
    private int playerChips;
    private int dealerChips;
    
    public PokerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
        dealer = new Dealer();
        player = new Player();
        bots = new ArrayList<Bot>();
        allCards = new ArrayList<Card>();
        
        playerChips = 1000;
        dealerChips = 1000;
        
        addBots(6);
        
        dealPersonalCards();
        dealAllCards();
    }
    
    private void addBots(int numberOfBots) {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot bot = new Bot("Bot " + i);
            bots.add(bot);
        }
    }
    private void dealPersonalCards() {
        dealer.dealToPlayer(player);
        dealer.dealToPlayer(player);
    }
    
    private void dealAllCards() {
        for (int i= 0; i < 5; i++) {
            allCards.add(dealer.dealCard());
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        CardBack cardBack1 = new CardBack();
        addObject(cardBack1,300,160);
        CardBack cardBack2 = new CardBack();
        addObject(cardBack2,350,160);
        CardBack cardBack3 = new CardBack();
        addObject(cardBack3,250,160);
        CardBack cardBack4 = new CardBack();
        addObject(cardBack4,200,160);
        CardBack cardBack5 = new CardBack();
        addObject(cardBack5,400,160);
    }
}
