package workshop.TriviaGameModule;


public class OutOfPenaltyBox implements PenaltyBox {
    public boolean checkPenaltyBox(int rollValue){
            return rollValue%2!=0;
    }
    TriviaGame triviaGame;
    public boolean isGettingOutOfPenaltyBox( ){
        
        return true;
    }
   
}
