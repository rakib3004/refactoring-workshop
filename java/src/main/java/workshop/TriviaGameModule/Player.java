package workshop.TriviaGameModule;

public class Player {
    private final String name;
    private int place = 0;
    private int coinInPurse = 0;
    private boolean isInPenaltyBox = false;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPlace() {
        return this.place;
    }


    public int getCoinInPurse() {
        return coinInPurse;
    }

    public boolean isInPenaltyBox() {
        return isInPenaltyBox;
    }

    public void setInPenaltyBox(boolean inPenaltyBox) {
        isInPenaltyBox = inPenaltyBox;
    }

    public void move(int roll) {
        place = (place + roll) % 12;
    }

    public void increaseCoin() {
        coinInPurse++;
    }
}