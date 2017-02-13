package no.uqtran.animalpoker.util;

import no.uqtran.animalpoker.Suit;

/**
 * Created by Uy Tran on 24.09.2016.
 * I could have implemented the court and number values in an enum class just like the suits, but I chose not to
 * because tail is the only "court" card.
 * I have implemented these methods in a way so that they may be called from anywhere and be used for other purposes
 * like debugging for Processing.
 */

public class CardUtil extends DeckUtil{

    public static int calculateCardValue(int cardId) {
        int cardValue = 0;
        if(isTail(cardId)) {
            cardValue = 15;
        } else {
            cardValue = (cardId % SUIT_RANGE) + SUIT_START;
        }
        return cardValue;
    }

    public static int calculateCardSuitIndex(int cardId) {
        int cardSuitIndex = 0;
        if(isTail(cardId)) {
            cardSuitIndex = (cardId / SUIT_RANGE) - 1;
        } else {
            cardSuitIndex = (cardId / SUIT_RANGE);
        }
        return cardSuitIndex;
    }

    public static boolean isTail(int cardId) {
        return cardId % SUIT_RANGE == 0;
    }

    public static String generateCardName(int cardId, Suit cardSuit) {
        String cardName = "";
        if(isTail(cardId)) {
            cardName = "Tail of " + cardSuit;
        } else {
            int cardNumber = calculateCardValue(cardId);
            cardName = cardNumber + " of " + cardSuit;
        }
        return cardName;
    }
}
