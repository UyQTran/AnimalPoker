package no.uqtran.animalpoker.util;


import no.uqtran.animalpoker.Card;
import no.uqtran.animalpoker.Hand;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Uy Tran on 24.09.2016.
 */

public class PlayerUtil {

    public static int calculatePlayerScore(List<Card> playerHand) {
        int playerScore = 0;
        Hand[] reversedHandList = Hand.values();
        Collections.reverse(Arrays.asList(reversedHandList));
        for(Hand hand : reversedHandList) {
            if(hand.getCallOnLambda().isHandCheck(playerHand)) {
                playerScore = hand.ordinal() + 1;
                break;
            }
        }
        return playerScore;
    }
}
