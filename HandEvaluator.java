import java.util.*;

/*
 * Hand evaluator to determine winner
 */
public class HandEvaluator {
    public static int evaluateHand(List<Card> hand) {
        Map<Integer, Integer> rankCounts = new HashMap<>();
        Map<String, Integer> suitCounts = new HashMap<>();
        List<Integer> ranks = new ArrayList<>();
        List<String> suits = new ArrayList<>();

        for (Card card : hand) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
            suitCounts.put(card.getSuit(), suitCounts.getOrDefault(card.getSuit(), 0) + 1);
            ranks.add(card.getRank());
            suits.add(card.getSuit());
        }

        Collections.sort(ranks);

        if (containsFlush(suitCounts)) {
            if (containsStraight(ranks)) {
                return 9;  // Straight Flush
            }
            return 6;  // Flush
        }

        if (containsFourOfAKind(rankCounts)) {
            return 8;  // Four of a Kind
        }

        if (containsFullHouse(rankCounts)) {
            return 7;  // Full House
        }

        if (containsStraight(ranks)) {
            return 5;  // Straight
        }

        if (containsThreeOfAKind(rankCounts)) {
            return 4;  // Three of a Kind
        }

        if (containsTwoPair(rankCounts)) {
            return 3;  // Two Pair
        }

        if (containsPair(rankCounts)) {
            return 2;  // Pair
        }

        return 1;  // High Card (No other hand found)
    }

    private static boolean containsFlush(Map<String, Integer> suitCounts) {
        return suitCounts.containsValue(5);  // 5 cards of the same suit
    }

    private static boolean containsFourOfAKind(Map<Integer, Integer> rankCounts) {
        return rankCounts.containsValue(4);  // Four cards of the same rank
    }

    private static boolean containsFullHouse(Map<Integer, Integer> rankCounts) {
        return rankCounts.containsValue(3) && rankCounts.containsValue(2);  // Three of a kind and a pair
    }

    private static boolean containsStraight(List<Integer> ranks) {
        for (int i = 0; i < ranks.size() - 1; i++) {
            if (ranks.get(i + 1) - ranks.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsThreeOfAKind(Map<Integer, Integer> rankCounts) {
        return rankCounts.containsValue(3);  // Three cards of the same rank
    }

    private static boolean containsTwoPair(Map<Integer, Integer> rankCounts) {
        int pairs = 0;
        for (int count : rankCounts.values()) {
            if (count == 2) {
                pairs++;
            }
        }
        return pairs == 2;  // Two pairs
    }

    private static boolean containsPair(Map<Integer, Integer> rankCounts) {
        return rankCounts.containsValue(2);  // Two cards of the same rank
    }
}
