package no.uqtran.animalpoker.lambda;

import no.uqtran.animalpoker.Card;

import java.util.List;

/**
 * Created by Uy Tran on 24.09.2016.
 */

public interface HandLambda {
    public boolean isHandCheck(List<Card> playerHand);
}
