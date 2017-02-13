package no.uqtran.animalpoker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class GameTest {

    private final Game validGame1;
    private final Game validGame2;
    private final Game validGame3;
    private final Game validGame4;

    public GameTest() throws Exception {
        validGame1 = new Game(
                new String[]{"Unicorn", "Consultant", "Ada Lovelace", "Grace Hopper"},
                new int[]{
                        10, 1, 28, 6,
                        24, 2, 27, 20,
                        38, 3, 26, 7,
                        52, 14, 25, 21

                });
        validGame2 = new Game(new String[]{"Steve Jobs", "Bill Gates"}, new int[]{
                1, 24,
                2, 27,
                3, 26,
                14, 25
        });
        validGame3 = new Game(new String[]{"Bill Gates", "Steve Jobs"}, new int[]{
                24, 1,
                27, 2,
                26, 3,
                25, 14
        });
        validGame4 = new Game(new String[]{"Johan", "Lars", "Gustaf"}, new int[]{
                2, 1, 7,
                3, 5, 8,
                4, 6, 9,
                56, 14, 11
        });
    }

    @Test
    public void invalidPlayers_nullOrEmpty() throws Exception {
        Exception expectedException = new Exception("You need to specify the animals as an array of animal names");
        assertGameThrow(expectedException, () -> new Game(null, null));
        assertGameThrow(expectedException, () -> new Game(new String[]{}, null));
    }

    @Test
    public void invalidCards_nullOrEmpty() throws Exception {
        Exception expectedException = new Exception("You need to specify the cards as an array of integers");
        String[] players = {"a", "b"};
        assertGameThrow(expectedException, () -> new Game(players, null));
        assertGameThrow(expectedException, () -> new Game(players, new int[]{}));
    }

    @Test
    public void tooFewCards() throws Exception {
        Exception expectedException = new Exception("There was not enough cards for all players");
        assertGameThrow(expectedException, () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void tooManyCards() throws Exception {
        Exception expectedException = new Exception("There was too many cards for the amount of players");
        assertGameThrow(expectedException, () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void duplicates() throws Exception {
        Exception expectedException = new Exception("The same card can't be dealt more than once");
        assertGameThrow(expectedException,
                () -> new Game(new String[]{"a", "b"}, new int[]{1, 2, 3, 4, 5, 2, 7, 8}));
    }

    @Test
    public void getPlayers_incorrectInput() throws Exception {
        Exception exception = new Exception("Not a valid player");
        assertGameThrow(exception, () -> validGame1.getPlayer(0));
        assertGameThrow(exception, () -> validGame1.getPlayer(5));
    }

    @Test
    public void getPlayers_correctPlayer() throws Exception {
        assertEquals("Unicorn", validGame1.getPlayer(1).getName());
        assertEquals("Consultant", validGame1.getPlayer(2).getName());
        assertEquals("Ada Lovelace", validGame1.getPlayer(3).getName());
        assertEquals("Grace Hopper", validGame1.getPlayer(4).getName());
    }


    /*
     * BEFORE
     */
    /*@Test
    public void getWinners() throws Exception {
        assertEquals("Unicorn won with All Different", validGame1.getWinner());

        assertEquals("Johan won with High Card", validGame4.getWinner());
    }*/

    /*
     * AFTER
     * I believe Johan won with straight didn't he? :S
     */

    @Test
    public void getWinners() throws Exception {
        assertEquals("Unicorn won with All Different", validGame1.getWinner());

        assertEquals("Johan won with Straight", validGame4.getWinner());
    }

    /*
    Handle tail as 15 in an high straight and 1 in an lower straight
     */
    @Test
    public void getWinners_tail() throws Exception {
//        assertEquals("Bill Gates won with Straight", validGame2.getWinner());
        assertEquals("Bill Gates won with Straight", validGame3.getWinner());
    }

    private interface GameFunction {
        void test() throws Exception;
    }

    private void assertGameThrow(Exception ex, GameFunction gameFunction) throws Exception {
        String message = "Exception not expected. Expected result was:" + ex.getMessage();
        boolean exceptionIsThrown = false;
        try {
            gameFunction.test();
        } catch (Exception e) {
            exceptionIsThrown = true;
            if (e.getMessage() == null || !e.getMessage().equals(ex.getMessage())) {
                throw new Exception(message);
            }
        }
        if (!exceptionIsThrown) {
            throw new Exception(message);
        }
    }
}