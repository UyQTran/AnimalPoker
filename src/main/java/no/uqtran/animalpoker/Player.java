package no.uqtran.animalpoker;

import no.uqtran.animalpoker.util.CardComparator;
import no.uqtran.animalpoker.util.PlayerUtil;

import java.util.Arrays;
import java.util.List;

public class Player extends PlayerUtil {
    private String playerName;
    private List<Card> playerHand;
    private int playerScore;

    public Player(String playerName, List<Card> playerHand) {
        this.playerName = playerName;
        this.playerHand = playerHand;
        initPlayerProps();
    }

    private void initPlayerProps() {
        playerHand.sort(new CardComparator());
        this.playerScore = calculatePlayerScore(playerHand);
    }

    @Override
    public String toString() {
        String playerHandAsString = Arrays.toString(playerHand.toArray());
        String playerNameAndHand = playerName + " has " + playerHandAsString.substring(1, playerHandAsString.length()-1);
        return playerNameAndHand;
    }

    public int getScore() {
        return playerScore;
    }

    public List<Card> getHand() {
        return playerHand;
    }

    public String getName() {
        return playerName;
    }
}
