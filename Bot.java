import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class Bot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bot extends Player 
{
    private String name;
    private int chips;
    
    public Bot(String name) {
        super();
        this.name = name;
        this.chips = chips;
    }
    
    public String getName() {
        return name;
    }
    
    public void botTurn(List<Card> allCards, int currentBet, int pot) {
        List<Card> combinedHand = new ArrayList<>(getHand());
        combinedHand.addAll(allCards);
        int handStrength = HandEvaluator.evaluateHand(combinedHand);

        // Simplified logic: Fold on weak hands, bet or call on strong hands
        if (handStrength <= 2) {
            fold();
        } else if (handStrength <= 4) {
            if (currentBet > 0) {
                call(currentBet);
            } else {
                check();
            }
        } else {
            raise(currentBet, pot);
        }
    }
    
    private void check() {
        
    }
    
    private void call(int currentBet) {
        chips -= currentBet;
    }
    
    private void raise(int currentBet, int pot) {
        int raiseAmount = (int) (pot * 05);
        chips -= (currentBet + raiseAmount);
    }
    
    private void fold() {
        
    }
    
    public int getChips() {
        return chips;
    }
    
    public class HandEvaluator {
        public static int evaluateHand(List<Card> hand) {
            hand.sort((card1, card2) -> card1.getRank() - card2.getRank());
    
            boolean isFlush = hand.stream().allMatch(card -> card.getSuit().equals(hand.get(0).getSuit()));
            boolean isStraight = true;
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i + 1).getRank() != hand.get(i).getRank() + 1) {
                    isStraight = false;
                    break;
                }
            }
    
            if (isFlush && isStraight) return 9; // Straight flush
            if (isFlush) return 6; // Flush
            if (isStraight) return 5; // Straight
    
            int[] rankCounts = new int[14]; // Index 0 unused, ranks 1-13
            for (Card card : hand) {
                rankCounts[card.getRank()]++;
            }
    
            int pairs = 0, threes = 0, fours = 0;
            for (int count : rankCounts) {
                if (count == 2) pairs++;
                if (count == 3) threes++;
                if (count == 4) fours++;
            }
    
            if (fours > 0) return 8; // Four of a kind
            if (threes > 0 && pairs > 0) return 7; // Full house
            if (threes > 0) return 4; // Three of a kind
            if (pairs > 1) return 3; // Two pairs
            if (pairs > 0) return 2; // Pair
    
            return 1; // High card
        }
}

}
