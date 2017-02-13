package no.uqtran.animalpoker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

    @Test
    public void getValue() throws Exception {
        assertEquals(2, new Card(1).getValue());
        assertEquals(15, new Card(14).getValue());
        assertEquals(13, new Card(40).getValue());
        assertEquals(15, new Card(56).getValue());
    }

    @Test
    public void cardToString() throws Exception {
        assertEquals("3 of Pony", new Card(2).toString());
        assertEquals("6 of Horse", new Card(19).toString());
        assertEquals("11 of Rhino", new Card(38).toString());
        assertEquals("Tail of Unicorn", new Card(56).toString());
    }

}