package no.uqtran.animalpoker;

import no.uqtran.animalpoker.util.GameUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game extends GameUtil {
    List<Player> playerList;


    public Game(String[] playerList, int[] cardList) throws Exception {
        validateCardList(playerList, cardList);
        initGameProps(playerList, cardList);
    }

    private void initGameProps(String[] playerList, int[] cardList) {
        this.playerList = new ArrayList<Player>();
        for(int i = 0; i < playerList.length; i++) {

            List<Card> playerHand = new ArrayList<Card>();
            for(int j = 0; j < cardList.length/playerList.length; j++) {
                int cardIndex = i + (j * playerList.length);
                Card card = new Card(cardList[cardIndex]);
                playerHand.add(card);
            }

            Player player = new Player(playerList[i], playerHand);
            this.playerList.add(player);
        }
    }

    public Player getPlayer(int id) throws Exception {
        if(id > playerList.size() || id < 1) {
            throw new Exception("Not a valid player");
        }
        return playerList.get(id-1);
    }

    public String getWinner() {
        Player winner = null;
        for(Player player : playerList) {
            if(winner == null){
                winner = player;
            } else if(winner.getScore() < player.getScore()) {
                winner = player;
            } else if(winner.getScore() == player.getScore()) {
                winner = compareHands(winner.getHand(), player.getHand()) < 0 ? player : winner;
            }
        }

        return winner.getName() + " won with " + Hand.values()[winner.getScore()-1].getHandName();
    }
}
