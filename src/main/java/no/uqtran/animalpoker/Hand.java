package no.uqtran.animalpoker;

import no.uqtran.animalpoker.lambda.HandLambda;

import java.util.List;

/**
 * Created by Uy Tran on 24.09.2016.
 * Hand score is calculated by ordinal + 1.
 * We may play with n size hand.
 * STRAIGHT could be even more generalized by using a ring list.
 * An implementation with a ring list would have the TAIL cards as both tail and head. With a ring list implementation,
 * we may even have n size SUIT_RANGE
 *
 * Thanks to Java 8 lambda function we are able to generalize the hands. If someone were to add another hand, they
 * would only need to add the new hand in this enum and provide a lambda-methods to it.
 */

import static no.uqtran.animalpoker.util.CardUtil.isTail;

public enum Hand {
    HIGH_CARD("High Card",(List<Card> playerHand)->{
        return true;
    }),
    TWO_PAIR("Two Pair", (List<Card> playerHand)->{
        int pairCount = 0;
        Card previousCard = null;
        for(Card currentCard : playerHand) {
            if(previousCard != null) {
                if(previousCard.getValue() == currentCard.getValue()) {
                    pairCount++;
                }
            }
            previousCard = currentCard;
        }

        boolean isTwoPair = pairCount ==  2;
        return isTwoPair;
    }),
    STRAIGHT("Straight", (List<Card> playerHand)->{
        boolean isStraight = true;
        Card previousCard = null;
        for(Card currentCard : playerHand) {
            if(previousCard != null) {
                int previousCardValue = previousCard.getValue();
                int currentCardValue = currentCard.getValue();
                int currentCardId = currentCard.getId();
                int previousCardId = previousCard.getId();

                if((previousCardValue != currentCardValue+1 && (!isTail(previousCardId) && currentCardValue != 4))
                        || (isTail(previousCardId) && isTail(currentCardId))) {
                    isStraight = false;
                    break;
                }
            }
            previousCard = currentCard;
        }

        return isStraight;
    }),
    ALL_DIFFERENT("All Different", (List<Card> playerHand)->{
        boolean isAllDifferent = true;
        int index = 0;
        first:for(Card card : playerHand) {
            index++;
            for(int i = index; i < playerHand.size(); i++) {
                if(card.getCardSuit().ordinal() == playerHand.get(i).getCardSuit().ordinal()) {
                    isAllDifferent = false;
                    break first;
                }
            }
        }

        return isAllDifferent;
    });

    private HandLambda callOnLambda;
    private String handName;

    Hand(String handName, HandLambda callOnLambda) {
        this.handName = handName;
        this.callOnLambda = callOnLambda;
    }

    public HandLambda getCallOnLambda() {
        return callOnLambda;
    }

    public String getHandName() {
        return handName;
    }
}
