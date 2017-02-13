package no.uqtran.animalpoker.util;

import no.uqtran.animalpoker.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Uy Tran on 24.09.2016.
 */
public class GameUtil {
    public static final int HAND_SIZE = 4;

    public static void validateCardList(String[] playerList, int[] cardList) throws Exception {
        if(playerList == null || playerList.length == 0) {
            throw new Exception("You need to specify the animals as an array of animal names");
        }
        if(cardList == null || cardList.length == 0) {
            throw new Exception("You need to specify the cards as an array of integers");
        }

        Set<Integer> cardSet = new HashSet<Integer>();
        for(int i = 0; i < cardList.length; i++) {
            cardSet.add(cardList[i]);
        }
        if(cardList.length != cardSet.size()) {
            throw new Exception("The same card can't be dealt more than once");
        } else if(cardList.length*1.0/playerList.length*1.0 < HAND_SIZE) {
            throw new Exception("There was not enough cards for all players");
        } else if(cardList.length*1.0/playerList.length*1.0 > HAND_SIZE) {
            throw new Exception("There was too many cards for the amount of players");
        }
    }

    public static int compareHands(List<Card> hand1, List<Card> hand2) {
        return new CardComparator().compare(hand1.get(0), hand2.get(0));
    }
}
