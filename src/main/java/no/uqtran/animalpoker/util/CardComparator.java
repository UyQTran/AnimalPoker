package no.uqtran.animalpoker.util;

import no.uqtran.animalpoker.Card;

import java.util.Comparator;

/**
 * Created by Uy Tran on 24.09.2016.
 */
public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        int compareValue = card2.getValue() - card1.getValue();

        if(compareValue == 0) {
            compareValue = card2.getCardSuit().ordinal() - card1.getCardSuit().ordinal();
        }
        return compareValue;
    }
}
