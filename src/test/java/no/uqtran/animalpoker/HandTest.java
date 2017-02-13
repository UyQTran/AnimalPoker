package no.uqtran.animalpoker;

import no.uqtran.animalpoker.util.TestUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class HandTest extends TestUtil{

    @Test
    public void getCallOnLambda_isHandCheck() throws Exception {
        assertTrue(Hand.ALL_DIFFERENT.getCallOnLambda().isHandCheck(createListOfCards(56,38,19,2)));
        assertFalse(Hand.ALL_DIFFERENT.getCallOnLambda().isHandCheck(createListOfCards(39,38,4,2)));

        assertTrue(Hand.STRAIGHT.getCallOnLambda().isHandCheck(createListOfCards(5,4,3,2)));
        assertFalse(Hand.STRAIGHT.getCallOnLambda().isHandCheck(createListOfCards(39,38,4,2)));
        assertTrue(Hand.STRAIGHT.getCallOnLambda().isHandCheck(createListOfCards(14,3,2,1)));
        assertFalse(Hand.STRAIGHT.getCallOnLambda().isHandCheck(createListOfCards(14,14,14,14)));

        assertTrue(Hand.TWO_PAIR.getCallOnLambda().isHandCheck(createListOfCards(18,4,16,2)));
        assertFalse(Hand.TWO_PAIR.getCallOnLambda().isHandCheck(createListOfCards(39,52,4,2)));
        assertFalse(Hand.TWO_PAIR.getCallOnLambda().isHandCheck(createListOfCards(18,4,5,2)));
    }


}