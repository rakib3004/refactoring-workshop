package workshop.TriviaGameModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TriviaGame {
    private final List<Player> playerList = new ArrayList<>();
    private final List<Question> questionList = new ArrayList<>();
    private final List<QuestionPatternMatcher> questionPatternMatcherList;
    private Question currentQuestion;
    private Player currentPlayerObj;
    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        // only for testing purpose
        //this staffs will go where object will be created
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

    //its name will be addPlayer
    public void add(String playerName) {
        playerList.add(new Player(playerName));

        announce(playerName + " was added");
        announce("They are player number " + playerList.size());
    }

    public void roll(int roll) {
        getCurrentPlayer();
        announce(currentPlayerObj.getName() + " is the current player");
        announce("They have rolled a " + roll);

        if (currentPlayerObj.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                announce(currentPlayerObj.getName() + " is getting out of the penalty box");
                currentPlayerObj.move(roll);
                announce(currentPlayerObj.getName()
                        + "'s new location is "
                        + currentPlayerObj.getPlace());
                getCurrentQuestion();
                announce("The category is " + currentQuestion.getQuestion_type());
                announce(currentQuestion.getQuestion());
            } else {
                announce(currentPlayerObj.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
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

    public boolean wasCorrectlyAnswered() {
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
                // why its return true?
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
