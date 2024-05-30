import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The main world where the poker game takes place.
 */
public class PokerWorld extends World
{
    private List<Player> players;  // List to hold all players
    private Dealer dealer;  // Dealer object
    private Player player;  // Human player
    private List<Bot> bots;  // List to hold all bots
    private List<Card> allCards;  // List to hold all community cards
    private int currentPlayerIndex;  // Index of the current player
    private int playerChips;  // Chips for the player
    private int currentBet;  // Current bet amount
    private int pot;  // Total pot amount
    
    // Labels to display bot actions
    Label botOneAction = new Label (".", 10);
    Label botTwoAction = new Label("", 10);
    Label botThreeAction = new Label("", 10);
    Label botFourAction = new Label("", 10);
    Label botFiveAction = new Label("", 10);
    Label botSixAction = new Label("", 10);
    Label botSevenAction = new Label("", 10);
    
    /**
     * Constructor for objects of class PokerWorld.
     */
    public PokerWorld()
    {    
        super(600, 400, 1); 
        prepare();  // Initialize the world
        dealer = new Dealer();  // Create a dealer
        player = new Player();  // Create a human player
        bots = new ArrayList<Bot>();  // Initialize list for bots
        allCards = new ArrayList<Card>();  // Initialize list for community cards
        players = new ArrayList<>();  // Initialize list for all players
        
        currentPlayerIndex = 0;  // Start with the first player
        playerChips = 1000;  // Set initial chips for the player
        currentBet = 0;  // Initialize current bet
        pot = 0;  // Initialize pot
        
        players.add(new HumanPlayer());  // Add human player to the list
        addBots(7);  // Add bots to the game
        
        dealPersonalCards();
        dealAllCards();
    }
    
    // Add bots to the game
    private void addBots(int numberOfBots) {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot bot = new Bot("Bot " + i);  // Create a new bot
            bots.add(bot);  // Add bot to the list
            players.add(new Bot("Bot" + i));  // Add bot to the players list
        }
    }
    
    // Deal personal cards to players
    private void dealPersonalCards() {
        dealCardToPlayer(player, 275, 350);  // Deal a card to the player
        dealCardToPlayer(player, 325, 350);  // Deal another card to the player
        for (Bot bot : bots) {
            dealer.dealToPlayer(bot);  // Deal cards to bots
            dealer.dealToPlayer(bot);  // Deal second card to bots
        }
    }
    
    // Deal a card to a player at specified coordinates
    private void dealCardToPlayer(Player player, int x, int y) {
        Card card = dealer.dealCard();  // Deal a card from the deck
        player.addCard(card);  // Add the card to the player's hand
        CardDisplay cardDisplay = new CardDisplay(card);  // Create a card display object
        addObject(cardDisplay, x, y);  // Add the card display to the world
    }
    
    // Deal community cards
    private void dealAllCards() {
        for (int i= 0; i < 5; i++) {
            allCards.add(dealer.dealCard());  // Deal a card to the community cards
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        // Add card backs to the world
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
    
    private void resetRound() {
        currentBet = 0;
        pot = 0;
        allCards.clear();
    }
    
    // Act method for game logic
    public void act() {
    
    }
}
