import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;
import java.util.Random;

PImage backgroundImage;
PImage[] playerCards;
Game game;

void setup() {
  size(1280,720);
  reset();
}

void reset() {
  int[] deck = new int[56];
  for(int i = 0; i < deck.length; i++) {
    deck[i] = i+1;
  }
  shuffleArray(deck);
  String[] playerList = new String[]{"Spiller1", "Spiller2", "Spiller3", "Spiller4"};
  int[] cardList = new int[16];
  for(int i = 0; i < cardList.length; i++) {
    cardList[i] = deck[i];
  }
  try{
    game = new Game(playerList, cardList);
    playerCards = new PImage[cardList.length];
    int index = 0;
    for(int i = 0; i < playerList.length; i++) {
      List<Card> playerHand = game.getPlayer(i+1).getHand();
      for(Card card : playerHand) {
        playerCards[index++] = loadImage("img/" + card.getId() + ".png");
      }
    }
  } catch(Exception e) {
    
  }
}

void keyPressed() {
  if(key == ' ') {
    reset();
  }
}

void draw() {
  background(255);
  fill(51,102,0);
  String winner = game.getWinner();
  if(winner.startsWith("Spiller1")) {
    rect(410, 520, 480, 1000);
  }
  image(playerCards[0], 430, 550, 100, 146);
  image(playerCards[1], 540, 550, 100, 146);
  image(playerCards[2], 650, 550, 100, 146);
  image(playerCards[3], 760, 550, 100, 146);
  if(winner.startsWith("Spiller2")) {
    rect(0, 40, 130, 620);
  }
  image(playerCards[4], 10, 50, 100, 146);
  image(playerCards[5], 10, 200, 100, 146);
  image(playerCards[6], 10, 350, 100, 146);
  image(playerCards[7], 10, 500, 100, 146);
  if(winner.startsWith("Spiller3")) {
    rect(400, 0, 490, 170);
  }
  image(playerCards[8], 430, 0, 100, 146);
  image(playerCards[9], 540, 0, 100, 146);
  image(playerCards[10], 650, 0, 100, 146);
  image(playerCards[11], 760, 0, 100, 146);
  if(winner.startsWith("Spiller4")) {
    rect(1140, 40, 550, 620);
  }
  image(playerCards[12], 1165, 50, 100, 146);
  image(playerCards[13], 1165, 200, 100, 146);
  image(playerCards[14], 1165, 350, 100, 146);
  image(playerCards[15], 1165, 500, 100, 146);
  text(winner, 30,30);
}

void shuffleArray(int[] array) {
    int index, temp;
    Random random = new Random();
    for (int i = array.length - 1; i > 0; i--)
    {
        index = random.nextInt(i + 1);
        temp = array[index];
        array[index] = array[i];
        array[i] = temp;
    }
}

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


public class CardUtil extends DeckUtil{

    public int calculateCardValue(int cardId) {
        int cardValue = 0;
        if(isTail(cardId)) {
            cardValue = 15;
        } else {
            cardValue = (cardId % SUIT_RANGE) + SUIT_START;
        }
        return cardValue;
    }

    public int calculateCardSuitIndex(int cardId) {
        int cardSuitIndex = 0;
        if(isTail(cardId)) {
            cardSuitIndex = (cardId / SUIT_RANGE) - 1;
        } else {
            cardSuitIndex = (cardId / SUIT_RANGE);
        }
        return cardSuitIndex;
    }

    public boolean isTail(int cardId) {
        return cardId % SUIT_RANGE == 0;
    }

    public String generateCardName(int cardId, Suit cardSuit) {
        String cardName = "";
        if(isTail(cardId)) {
            cardName = "Tail of " + cardSuit;
        } else {
            int cardNumber = calculateCardValue(cardId);
            cardName = cardNumber + " of " + cardSuit;
        }
        return cardName;
    }
}

public class DeckUtil {
    public static final int SUIT_RANGE = 14;
    public static final int SUIT_START = 1;
}

public class GameUtil {
    public static final int HAND_SIZE = 4;

    public void validateCardList(String[] playerList, int[] cardList) throws Exception {
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
    
    public int getHighestCard(List<Card> playerHand) {
        int highestCard = 0;
        for(Card card : playerHand) {
            if(card.getId() > highestCard) {
                highestCard = card.getId();
            }
        }
        return highestCard;
    }

    public Player compareHands(List<Player> winnerList) {
        Player winner = null;
        int highestCardOfAllPlayers = 0;
        for(Player player : winnerList) {
            int currentPlayerHighestCard = getHighestCard(player.getHand());
            if(winner == null || currentPlayerHighestCard > highestCardOfAllPlayers) {
                winner = player;
                highestCardOfAllPlayers = currentPlayerHighestCard;
            }
        }
        return winner;
    }
}

public class PlayerUtil {

    public int calculatePlayerScore(List<Card> playerHand) {
        int playerScore = 1;
        if(isAllDifferent(playerHand)) {
          playerScore = 4;
        } else if(isStraight(playerHand)) {
          playerScore = 3;
        }else if(isTwoPair(playerHand)) {
          playerScore = 2;
        }
        return playerScore;
    }
    
    public boolean isAllDifferent(List<Card> playerHand) {
        boolean isAllDifferent = true;
        int index = 0;
        first:for(Card card : playerHand) {
            index++;
            for(int i = index; i < playerHand.size(); i++) {
                if(card.getCardSuit().ordinal() == playerHand.get(i).getCardSuit().ordinal()) {
                    isAllDifferent = false;
                    break first;
                }
            }
        }

        return isAllDifferent;
    }
    
    public boolean isTail(int cardId) {
        return cardId % 14 == 0;
    }
    
    public boolean isStraight(List<Card> playerHand) {
        boolean isStraight = true;
        Card previousCard = null;
        for(Card currentCard : playerHand) {
            if(previousCard != null) {
                int previousCardValue = previousCard.getValue();
                int currentCardValue = currentCard.getValue();
                int currentCardId = currentCard.getId();
                int previousCardId = previousCard.getId();

                if((previousCardValue != currentCardValue+1 && (!isTail(previousCardId) && currentCardValue != 4))
                        || (isTail(previousCardId) && isTail(currentCardId))) {
                    isStraight = false;
                    break;
                }
            }
            previousCard = currentCard;
        }

        return isStraight;
    }
    
    public boolean isTwoPair(List<Card> playerHand) {
        int pairCount = 0;
        Card previousCard = null;
        for(Card currentCard : playerHand) {
            if(previousCard != null) {
                if(previousCard.getValue() == currentCard.getValue()) {
                    pairCount++;
                }
            }
            previousCard = currentCard;
        }

        boolean isTwoPair = pairCount ==  2;
        return isTwoPair;
    }
}


public enum Suit {
    PONY("Pony"),
    HORSE("Horse"),
    RHINO("Rhino"),
    UNICORN("Unicorn");

    private String suitName;

    private Suit(String suitName) {
        this.suitName = suitName;
    }

    @Override
    public String toString() {
        return suitName;
    }
}

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

public class Game extends GameUtil {
    private List<Player> playerList;


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
        List<Player> winnerList = new ArrayList<Player>();
        for(Player player : playerList) {
            if(winner == null){
                winner = player;
            } else if(winner.getScore() < player.getScore()) {
                winner = player;
                winnerList.clear();
                winnerList.add(winner);
            } else if(winner.getScore() == player.getScore()) {
                winnerList.add(player);
            }
        }

        if(winnerList.size() > 1) {
            winner = compareHands(winnerList);
        }

        return winner.getName() + " won with " + Hand.values()[winner.getScore()-1].getHandName();
    }
}

public class Card extends CardUtil{
    private int id;
    private int cardValue;
    private String cardName;
    private Suit cardSuit;

    public Card(int id) {
        this.id = id;
        initCardProps();
    }

    public void initCardProps() {
        cardValue = calculateCardValue(id);
        cardSuit = Suit.values()[calculateCardSuitIndex(id)];
        cardName = generateCardName(id, cardSuit);
    }

    public int getValue() {
        return cardValue;
    }

    @Override
    public String toString() {
        return cardName;
    }

    public int getId() {
        return id;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }
}

enum Hand {
    HIGH_CARD("High Card"),
    TWO_PAIR("Two Pair"),
    STRAIGHT("Straight"),
    ALL_DIFFERENT("All Different");

    private String handName;

    private Hand(String handName) {
        this.handName = handName;
    }

    public String getHandName() {
        return handName;
    }
}