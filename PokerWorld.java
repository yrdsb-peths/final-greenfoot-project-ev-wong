import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    private int currentBet;  // Current bet amount
    private int pot;  // Total pot amount
    private int bb; //big blind
    private int sb; //small blind
    private int randomNum; //random number
    public int humanBetAmount; //human bet
    private int humanChips;
    private boolean roundInProgress; // Tracker
    private boolean waitForHumanInput;
    private boolean musicPlaying;
    private String smallBlindPlayer;
    private String bigBlindPlayer;
    private HumanPlayer human;
    
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
    Label humanBetLabel = new Label("Your chips: " + humanChips, 20);
    Label currentBetLabel = new Label("Current bet : " + currentBet, 20);
    Label potLabel = new Label("Pot : " + pot, 20);
    Label winnerLabel = new Label("", 30);
    
    //Sound effects
    GreenfootSound shuffleSound = new GreenfootSound("cardShuffle.mp3");
    GreenfootSound music = new GreenfootSound("bgmusic.mp3");
    GreenfootSound dealSound = new GreenfootSound("cardPlace1.mp3");
    GreenfootSound chipSound = new GreenfootSound("chipsStack1.mp3");
    GreenfootSound slideSound = new GreenfootSound("cardSlide1.mp3");

    
    /**
     * Constructor for objects of class PokerWorld.
     */
    public PokerWorld()
    {    
        super(600, 400, 1); 
        player = new Player(); // Create a player
        bots = new ArrayList<Bot>();  // Initialize list for bots
        players = new ArrayList<>();  // Initialize list for all players
        sb = 0;
        bb = 1;        
        
        waitForHumanInput = false; 
        roundInProgress = false;
        musicPlaying = false;
        
        currentPlayerIndex = 0;  // Start with the first player
        currentBet = 0;  // Initialize current bet
        pot = 0;  // Initialize pot
        
        dealer = new Dealer();
        human = new HumanPlayer("Human");
        human.setChips(1000);
        players.add(human);  // Add human player to the list
        addBots(7);  // Add bots to the game
        
        addObject(smallBlindLabel, 80, 340);
        addObject(bigBlindLabel, 73, 370);
        addObject(humanBetLabel, 506, 340);
        addObject(currentBetLabel, 500, 360);
        addObject(potLabel, 470, 380);
        addObject(winnerLabel, 300, 100);
        
        updateHumanBetLabel();
    }
    
    // Act method for game logic
    public void act() {
        if (musicPlaying == false) {
            music.play();
            musicPlaying = true;
        }
        resetDeckAndHands();
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        assignBlinds();
        deductBlindChips();
        shuffleSound.play();
        putInRound();
        dealPersonalCards();
        Greenfoot.delay(50);
        bettingRound();
        dealTableCardOne();
        Greenfoot.delay(50);
        bettingRound();
        dealTableCardTwo();
        Greenfoot.delay(50);
        bettingRound();
        dealTableCardThree();
        Greenfoot.delay(50);
        bettingRound();
        calculateWinner();
        Greenfoot.delay(200);
    }
    
    private void resetDeckAndHands() {
        dealer.resetDeck(); // Reinitialize the dealer to reset the deck
        house = new House(); // Create house
        allCards = new ArrayList<Card>();  // Initialize list for community cards
        for (Player player : players) {
            player.clearHand(); // Clear the hand of each player
            player.setInRound(true); // Reset the in-round status of each player
        }
        house.clearTableCards(); // Clear the community cards from the table
        allCards.clear(); // Clear the allCards list if used
        currentBet = 0;
        pot = 0;
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
        updateHumanBetLabel();
        updateCurrentBetLabel();
        updatePotLabel();
        updateWinnerLabel();
    }
    
    public void putInRound() {
        for (Player player : players) {
            player.setInRound(true);
        }
    }

    
    public void bettingRound() {
        currentBet = 0;
        waitForHumanInput = true;
        
        int roundPlayerIndex = 0;
        
        while (roundPlayerIndex < players.size()) {
            Player currentPlayer = players.get(roundPlayerIndex);
            if (currentPlayer.isInRound()){
                if (currentPlayer.isHuman()) {
                    humanTurn();
                } else {
                    botTurn((Bot) currentPlayer); // Cast to Bot type
                }
                
                if (currentPlayer.isInRound()) {
                    roundPlayerIndex++;
                }
            } else {
                roundPlayerIndex++;
            }
        }
        
        updateHumanBetLabel();
        updateCurrentBetLabel();
        updatePotLabel();
    }
    
    
    private void humanTurn() {
        while (waitForHumanInput) {    
            if (Greenfoot.isKeyDown("c")) {
                human.call();
                pot += currentBet;
                human.setChips(human.getChips() - currentBet);
                chipSound.play();
                waitForHumanInput = false;
            } else if (Greenfoot.isKeyDown("r")) {
                human.raise();
                currentBet += 20;
                pot += currentBet;
                human.setChips(human.getChips() - currentBet);
                chipSound.play();
                waitForHumanInput = false;
            } else if (Greenfoot.isKeyDown("f")) {
                human.fold();
                slideSound.play();
                waitForHumanInput = false;
            }
            
            if (human.getChips() <= 0) {
                players.remove(human);
                break;
            }
        }
    }
    
    // Method to handle the bot's turn
    private void botTurn(Bot bot) {        
        Random rand = new Random();
        randomNum = rand.nextInt(4);
        
        // Simplified logic for bot's actions based on hand strength
        if (randomNum == 0) {
            bot.fold();  // Fold on weak hands
        } else if (randomNum == 1) {
            bot.call();
            pot += currentBet;
        } else if (randomNum == 2){
            bot.raise();
            currentBet += 20;
            pot += currentBet;
        }
        
        if (bot.getChips() <= 0) {
            players.remove(bot);
        }
    }
    
    private void playerTurn(Player player) {
        if (player.isHuman()) {
            while (waitForHumanInput == true) {
                humanTurn();
                Greenfoot.delay(100);
            }
        } else {
            botTurn((Bot) player); // Cast to Bot type
        }
    }
    
    public void dealTableCardOne() {
        dealSound.play();
        dealCardToHouse(house, 200, 160);
    }
    
    public void dealTableCardTwo() {
        dealSound.play();
        dealCardToHouse(house, 250, 160);
    }
    
    public void dealTableCardThree() {
        dealSound.play();
        dealCardToHouse(house, 300, 160);
        dealCardToHouse(house, 350, 160);
        dealCardToHouse(house, 400, 160);
    }
    
    private void calculateWinner() {
        Player winner = null;
        int bestHandValue = -1;
    
        for (Player player : players) {
            if (player.isInRound()) {  // Check if player is still in the round
                List<Card> combinedHand = new ArrayList<>(player.getHand());
                combinedHand.addAll(house.getTableCards());
    
                int handValue = HandEvaluator.evaluateHand(combinedHand);
    
                if (handValue > bestHandValue) {
                    bestHandValue = handValue;
                    winner = player;
                }
            }
        }
    
        if (winner != null) {
            winner.setChips(winner.getChips() + pot); // Add the pot to the winner's chips
            pot = 0; // Reset the pot
        }
        
        winnerLabel.setValue(winner + " Wins!");
        updateHumanBetLabel();
        updateCurrentBetLabel();
        updatePotLabel();
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
       
       if (this.smallBlindPlayer == null) {
           smallBlindLabel.setValue("Small Blind: Player");
       } else {
           smallBlindLabel.setValue("Small Blind: " + this.smallBlindPlayer);
       }
       
       if (this.bigBlindPlayer  == null) {
           bigBlindLabel.setValue("Big Blind: Player");
       } else {
           bigBlindLabel.setValue("Big Blind: " + this.bigBlindPlayer);
       }
       
       if (sb == 8) {
           sb = 0;
       }
       
       if (bb == 8) {
           bb = 0;
       }
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
    
    // Add bots to the game
    private void addBots(int numberOfBots) {
        for (int i = 1; i <= numberOfBots; i++) {
            Bot bot = new Bot("Bot" + i);
            bot.setChips(1000);
            bots.add(bot);  // Add bot to the bot list
            players.add(bot);  // Add bot to the players list
        }
    }
    
    // Deal personal cards to players
    private void dealPersonalCards() {
        dealCardToPlayer(human, 275, 350);  // Deal a card to the player
        dealCardToPlayer(human, 325, 350);  // Deal another card to the player
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
    
    private void updateHumanBetLabel() {
        humanBetLabel.setValue("Your chips: " + human.getChips());
    }
    
    private void updateCurrentBetLabel() {
        currentBetLabel.setValue("Current bet: " + currentBet);
    }
    
    private void updatePotLabel() {
        potLabel.setValue("Pot: " + pot);
    }
    
    private void updateWinnerLabel() {
        winnerLabel.setValue("");
    }
}
