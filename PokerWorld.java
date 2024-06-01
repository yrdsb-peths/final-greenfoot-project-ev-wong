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
    private House house; //House
    private List<Bot> bots;  // List to hold all bots
    private List<Card> allCards;  // List to hold all community cards
    private int currentPlayerIndex;  // Index of the current player
    private int playerChips;  // Chips for the player
    private int currentBet;  // Current bet amount
    private int pot;  // Total pot amount
    private int bb; //big blind
    private int sb; //small blind
    public int humanBetAmount; //human bet
    private boolean roundInProgress; // Tracker
    private String smallBlindPlayer;
    private String bigBlindPlayer;
    
    // Labels to display bot actions
    Label botOneAction = new Label (".", 10);
    Label botTwoAction = new Label("", 10);
    Label botThreeAction = new Label("", 10);
    Label botFourAction = new Label("", 10);
    Label botFiveAction = new Label("", 10);
    Label botSixAction = new Label("", 10);
    Label botSevenAction = new Label("", 10);
    Label smallBlindLabel = new Label("Small Blind: ", 20);
    Label bigBlindLabel = new Label("Big Blind: ", 20);
    Label humanBetLabel = new Label("Your bet: " + humanBetAmount, 30);

    
    /**
     * Constructor for objects of class PokerWorld.
     */
    public PokerWorld()
    {    
        super(600, 400, 1); 
        prepare();  // Initialize the world
        dealer = new Dealer();  // Create a dealer
        player = new Player(); // Create a player
        house = new House(); // Create house
        bots = new ArrayList<Bot>();  // Initialize list for bots
        allCards = new ArrayList<Card>();  // Initialize list for community cards
        players = new ArrayList<>();  // Initialize list for all players
        sb = 0;
        bb = 1;        
        roundInProgress = false;
        
        currentPlayerIndex = 0;  // Start with the first player
        playerChips = 1000;  // Set initial chips for the player
        currentBet = 0;  // Initialize current bet
        pot = 0;  // Initialize pot
        
        players.add(new HumanPlayer());  // Add human player to the list
        addBots(7);  // Add bots to the game
        
        addObject(smallBlindLabel, 80, 340);
        addObject(bigBlindLabel, 73, 370);
        addObject(humanBetLabel, 475, 360);
    }
    
    // Act method for game logic
    public void act() {
        if (roundInProgress == false) {
            startRound();
        }
    }
    
    private void startRound() {
        roundInProgress = true;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        assignBlinds();
        deductBlindChips();
        dealPersonalCards();
        bettingRound();
        dealTableCardOne();
        bettingRound();
        dealTableCardTwo();
        bettingRound();
        dealTableCardThree();
        bettingRound();
        calculateWinner();
        rotatePositions();  
    }
    
    public void bettingRound() {
        /*
        currentBet = 20;
        int lastToActIndex = (currentPlayerIndex + bb + players.size() - 1) % players.size();
        
        boolean roundCompleted = false;
        
        while(!roundCompleted) {
            roundCompleted = true;
            
            for (int i = 0; i < players.size(); i++) {
                Player currentPlayer = players.get((currentPlayerIndex + i) % players.size());
                
                if(currentPlayer.isInRound() && currentPlayer.getChips() > 0) {
                    PlayerAction action = new PlayerAction(action, currentBet);                 
                    switch (action.getType()) {
                        case FOLD:
                            currentPlayer.setInRound(false);
                            break;
                        case CALL:
                            int callAmount = currentBet - currentPlayer.getCurrentBet();
                            currentPlayer.setChips(currentPlayer.getChips() - callAmount);
                            currentPlayer.setCurrentBet(currentBet);
                            pot += callAmount;
                            break;
                        case RAISE:
                            int raiseAmount = action.getAmount();
                            currentBet += raiseAmount;
                            currentPlayer.setChips(currentPlayer.getChips() - (currentBet - currentPlayer.getCurrentBet()));
                            currentPlayer.setCurrentBet(currentBet);
                            pot += currentBet - currentPlayer.getCurrentBet();
                            lastToActIndex = (currentPlayerIndex + i + players.size() - 1) % players.size();
                            roundCompleted = false;
                            break;
                        case CHECK:
                            // No chips are deducted for checking
                            break;
                    }
                }
                
                if (i == lastToActIndex) {
                    roundCompleted = true;
                    break;
                }
            }
        }
        for (Player player : players) {
            player.setCurrentBet(0);
        }
        */
    }
    
    public void dealTableCardOne() {
        dealCardToHouse(house, 200, 160);
    }
    
    public void dealTableCardTwo() {
        dealCardToHouse(house, 250, 160);
    }
    
    public void dealTableCardThree() {
        dealCardToHouse(house, 300, 160);
        dealCardToHouse(house, 350, 160);
        dealCardToHouse(house, 400, 160);
    }
    
    public void calculateWinner() {
        Player bestPlayer = null;
        int bestHandValue = -1;
        
        for (Player player : players) {
            List<Card> combinedHand = new ArrayList<>(player.getHand());
            combinedHand.addAll(house.getTableCards());
            int handValue = HandEvaluator.evaluateHand(combinedHand);
            
            if (handValue > bestHandValue) {
                bestHandValue = handValue;
                bestPlayer = player;
            }
        }
    
        if (bestPlayer != null) {
            bestPlayer.setChips(bestPlayer.getChips() + pot);
            pot = 0;
            
        }
    }

    
    public void assignBlinds() {
        //Reset blinds
       for (Player player : players) {
           player.setSmallBlind(false);
           player.setBigBlind(false);
       }
       
       //Assign small blind
       Player smallBlindPlayer = players.get((currentPlayerIndex + sb) % players.size());
       smallBlindPlayer.setSmallBlind(true);
       
       //Assign big blind
       Player bigBlindPlayer = players.get((currentPlayerIndex + bb) % players.size());
       bigBlindPlayer.setBigBlind(true);
       
       //Get player name
       this.smallBlindPlayer = smallBlindPlayer.getName();
       this.bigBlindPlayer = bigBlindPlayer.getName();
       // Update labels
       smallBlindLabel.setValue("Small Blind: " + this.smallBlindPlayer);
       bigBlindLabel.setValue("Big Blind: " + this.bigBlindPlayer);
    }
    
    private void rotatePositions() {
        sb += 1;
        bb += 1;
    }
    
    private void deductBlindChips() {
        for(Player player : players) {
            if (player.isSmallBlind()) {
                player.setChips(player.getChips() - 10);
                pot +=  10;
            } else if (player.isBigBlind()) {
                player.setChips(player.getChips() - 20);
                pot += 20;
            }
        }
    }

    private void humanBet() {
        if(Greenfoot.isKeyDown("left")) {
            humanBetAmount += 10;
        }
        if(Greenfoot.isKeyDown("right")) {
            humanBetAmount -= 10;
        }
    }
    
    public void humanTurn() {
        while(!Greenfoot.isKeyDown("enter")) {
            humanBet();
            humanBetLabel.setValue("Your Bet: " + humanBetAmount);
        }
    }
    
    // Add bots to the game
    private void addBots(int numberOfBots) {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot bot = new Bot("Bot" + i);
            bots.add(new Bot("Bot" + i));  // Add bot to the bot list
            players.add(new Bot("Bot" + i));  // Add bot to the players list
            bot.setChips(1000);
        }
    }
    
    // Deal personal cards to players
    private void dealPersonalCards() {
        dealCardToPlayer(player, 275, 350);  // Deal a card to the player
        dealCardToPlayer(player, 325, 350);  // Deal another card to the player
        for (Bot bot : bots) {
            dealToBot(bot);  // Deal cards to bots
            dealToBot(bot);  // Deal second card to bots
        }
    }
    
    // Deal a card to a player
    private void dealCardToPlayer(Player player, int x, int y) {
        Card card = dealer.dealCard();  // Deal a card from the deck
        player.addCard(card);  // Add the card to the player's hand
        CardDisplay cardDisplay = new CardDisplay(card);  // Create a card display object
        addObject(cardDisplay, x, y);  // Add the card display to the world
    }
    
    // Deal a card to house
    private void dealCardToHouse(House house, int x, int y) {
        Card card = dealer.dealCard();
        house.addTableCard(card);
        CardDisplay cardDisplay = new CardDisplay(card);
        addObject(cardDisplay, x, y);
    }
    
    private void dealToBot(Bot bot) {
        Card card = dealer.dealCard();
        bot.addCard(card);
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
}
