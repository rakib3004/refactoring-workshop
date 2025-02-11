package workshop.TriviaGameModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TriviaGame {
    List<Player> playerList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();
    List<PenaltyBox> penaltyBoxs;
    List<QuestionPatternMatcher> questionPatternMatcherList;
    Question currentQuestion;
    Player currentPlayerObj;
    int currentPlayer = 0;

    public TriviaGame() {
        addQuestionType("Rock");
        addQuestionType("Sports");
        addQuestionType("Science");
        addQuestionType("Pop");
        questionPatternMatcherList = Arrays.asList(
                new PopQuestionPatternMatcher(),
                new ScienceQuestionPatternMatcher(),
                new SportsQuestionPatternMatcher(),
                new RockQuestionPatternMatcher()
        );
    }

    private void getCurrentQuestion() {
        for (Question question : questionList) {
            for (QuestionPatternMatcher questionPatternMatcher : questionPatternMatcherList) {
                if (questionPatternMatcher.match(currentPlayerObj.getPlace())) {
                    if (questionPatternMatcher.generateResponse().equals(question.getQuestion_type())) {
                        currentQuestion = question;
                        break;
                    }
                }
            }
        }
    }

    public void addQuestionType(String questionType) {
        questionList.add(new Question(questionType));
    }

    public void add(String playerName) {
        playerList.add(new Player(playerName));

        announce(playerName + " was added");
        announce("They are player number " + playerList.size());
    }

    public void roll(int roll) {
        boolean isGettingOutOfPenaltyBox=false;
        getCurrentPlayer();
        announce(currentPlayerObj.getName() + " is the current player");
        announce("They have rolled a " + roll);
       

        if (currentPlayerObj.isInPenaltyBox()) {

            for(PenaltyBox penaltyBox: penaltyBoxs){
                if(penaltyBox.checkPenaltyBox(roll)){
                    isGettingOutOfPenaltyBox=penaltyBox.isGettingOutOfPenaltyBox();
                }
            }

            if(isGettingOutOfPenaltyBox){
                announce(currentPlayerObj.getName() + " is getting out of the penalty box");
                currentPlayerObj.move(roll);
                announce(currentPlayerObj.getName()
                        + "'s new location is "
                        + currentPlayerObj.getPlace());
                getCurrentQuestion();
                announce("The category is " + currentQuestion.getQuestion_type());
                announce(currentQuestion.getQuestion());

            }

        } else {
            currentPlayerObj.move(roll);
            announce(currentPlayerObj.getName()
                    + "'s new location is "
                    + currentPlayerObj.getPlace());

            getCurrentQuestion();

            announce("The category is "
                    + currentQuestion.getQuestion_type());

            announce(currentQuestion.getQuestion());
        }
    }

    private void getCurrentPlayer() {
        currentPlayerObj = playerList.get(currentPlayer);
    }

    public boolean wasCorrectlyAnswered(boolean isGettingOutOfPenaltyBox) {
        getCurrentPlayer();
        boolean winner = false;
        if (currentPlayerObj.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                announce("Answer was correct!!!!");
                currentPlayerObj.increaseCoin();
                announce(currentPlayerObj.getName()
                        + " now has "
                        + currentPlayerObj.getCoinInPurse()
                        + " Gold Coins.");

                winner = didPlayerWin();
            } else {
                currentPlayer = (currentPlayer + 1) % playerList.size();
                return true;
            }
        } else {
            announce("Answer was correct!!!!");
            currentPlayerObj.increaseCoin();
            announce(playerList.get(currentPlayer).getName()
                    + " now has "
                    + currentPlayerObj.getCoinInPurse()
                    + " Gold Coins.");

            winner = didPlayerWin();
        }
        currentPlayer = (currentPlayer + 1) % playerList.size();
        return winner;
    }

    public void wrongAnswer() {
        getCurrentPlayer();

        announce("Question was incorrectly answered");
        announce(currentPlayerObj.getName() + " was sent to the penalty box");

        currentPlayerObj.setInPenaltyBox(true);

        currentPlayer = (currentPlayer + 1) % playerList.size();
    }

    private boolean didPlayerWin() {
        return !(currentPlayerObj.getCoinInPurse() == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}
