package workshop.TriviaGameModule;

public class OutOfPenaltyBox {
    public boolean checkPenaltyBox(int rollValue){
            return rollValue%2!=0;
    }
    public boolean isGettingOutOfPenaltyBox(){
        return true;
    }
}
