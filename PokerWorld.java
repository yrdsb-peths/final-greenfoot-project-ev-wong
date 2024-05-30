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
    private int currentPlayerIndex;
    private List<Player> players;
    private Dealer dealer;
    private Player player;
    private List<Bot> bots;
    private List<Card> allCards;
    private int playerChips;
    private int currentBet;
    private int pot;
    
    Label botOneAction = new Label (".", 10);
    Label botTwoAction = new Label("", 10);
    Label botThreeAction = new Label("", 10);
    Label botFourAction = new Label("", 10);
    Label botFiveAction = new Label("", 10);
    Label botSixAction = new Label("", 10);
    Label botSevenAction = new Label("", 10);
    
    public PokerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
        dealer = new Dealer();
        player = new Player();
        bots = new ArrayList<Bot>();
        allCards = new ArrayList<Card>();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        
        playerChips = 1000;
        currentBet = 0;
        pot = 0;
        
        players.add(new HumanPlayer());
        addBots(7);
        
        dealPersonalCards();
        dealAllCards();
    }
    
    private void addBots(int numberOfBots) {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot bot = new Bot("Bot " + i);
            bots.add(bot);
            players.add(new Bot("Bot" + i));
        }
    }
    
    private void dealPersonalCards() {
        dealCardToPlayer(player, 275, 350);
        dealCardToPlayer(player, 325, 350);
        for (Bot bot : bots) {
            dealer.dealToPlayer(bot);
            dealer.dealToPlayer(bot);
        }
    }
    
    private void dealCardToPlayer(Player player, int x, int y) {
        Card card = dealer.dealCard();
        player.addCard(card);
        CardDisplay cardDisplay = new CardDisplay(card);
        addObject(cardDisplay, x, y);
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
    
    public void act() {
        if (players != null && currentPlayerIndex >= 0 && currentPlayerIndex < players.size()) {
            Player currentPlayer = players.get(currentPlayerIndex);
            if (currentPlayer.isHuman()) {
                ((HumanPlayer) currentPlayer).humanTurn();
            } else {
                ((Bot) currentPlayer).botTurn(allCards, currentBet, pot);
            }
        }
    }
}
