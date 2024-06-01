import java.util.List;

/**
 * Write a description of class HandEvaluator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
