package no.uqtran.animalpoker;


import no.uqtran.animalpoker.util.CardUtil;


public class Card extends CardUtil {
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
