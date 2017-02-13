package no.uqtran.animalpoker;

/**
 * Created by Uy Tran on 24.09.2016.
 */
public enum Suit {
    PONY("Pony"),
    HORSE("Horse"),
    RHINO("Rhino"),
    UNICORN("Unicorn");

    private String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    @Override
    public String toString() {
        return suitName;
    }
}
