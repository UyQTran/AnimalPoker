package no.uqtran.animalpoker;

import no.uqtran.animalpoker.util.TestUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest extends TestUtil {

    private Player unicorn = new Player("Unicorn", createListOfCards(14, 28, 17, 20));
    private Player consultant = new Player("Consultant", createListOfCards(56, 42, 28, 14));
    private Player lovelace = new Player("Ada Lovelace", createListOfCards(5, 4, 2, 3));
    private Player hopper = new Player("Grace Hopper", createListOfCards(11, 25, 13, 27));

    @Test
    public void player_toString() throws Exception {
        assertEquals("Unicorn has Tail of Horse, Tail of Pony, 7 of Horse, 4 of Horse",
                unicorn.toString());
        assertEquals("Consultant has Tail of Unicorn, Tail of Rhino, Tail of Horse, Tail of Pony",
                consultant.toString());
        assertEquals("Ada Lovelace has 6 of Pony, 5 of Pony, 4 of Pony, 3 of Pony",
                lovelace.toString());
        assertEquals("Grace Hopper has 14 of Horse, 14 of Pony, 12 of Horse, 12 of Pony",
                hopper.toString());
    }

    @Test
    public void getHand_correctSize() throws Exception {
        assertEquals(4, unicorn.getHand().size());
        assertEquals(4, consultant.getHand().size());
        assertEquals(4, lovelace.getHand().size());
        assertEquals(4, hopper.getHand().size());
    }

    /*
     * I had to add .toString to each of these since the .createListOfCards-method would create a whitespace at the end
     * while .getHand would not...
     */
    @Test
    public void getHand_shouldBeOrdered() throws Exception {
        assertEquals(createListOfCards(28, 14, 20, 17).toString(), unicorn.getHand().toString());
        assertEquals(createListOfCards(56, 42, 28, 14).toString(), consultant.getHand().toString());
        assertEquals(createListOfCards(5, 4, 3, 2).toString(), lovelace.getHand().toString());
        assertEquals(createListOfCards(27, 13, 25, 11).toString(), hopper.getHand().toString());
    }

    @Test
    public void getScore() throws Exception {
        assertEquals(1, unicorn.getScore());
        assertEquals(4, consultant.getScore());
        assertEquals(3, lovelace.getScore());
        assertEquals(2, hopper.getScore());
    }
}