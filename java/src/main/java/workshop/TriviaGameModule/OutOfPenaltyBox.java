package workshop.TriviaGameModule;

import workshop.TriviaGame;

public class OutOfPenaltyBox implements PenaltyBox {
    public boolean checkPenaltyBox(int rollValue){
            return rollValue%2!=0;
    }
    TriviaGame triviaGame;
    public boolean isGettingOutOfPenaltyBox(Player currentPlayerObj){
        
        triviaGame.announce(currentPlayerObj.getName() + " is getting out of the penalty box");
        currentPlayerObj.move(roll);
        triviaGame.announce(currentPlayerObj.getName()
                + "'s new location is "
                + currentPlayerObj.getPlace());
                triviaGame.getCurrentQuestion();
                triviaGame.announce("The category is " + currentQuestion.getQuestion_type());
                triviaGame.announce(currentQuestion.getQuestion());


        return true;
    }
}
