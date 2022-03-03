package workshop.TriviaGameModule;

public class SportsQuestionPatternMatcher implements QuestionPatternMatcher{
    @Override
    public boolean match(int number) {
        return number == 2 || number == 6 || number == 10;
    }

    @Override
    public String generateResponse() {
        return "Sports";
    }
}