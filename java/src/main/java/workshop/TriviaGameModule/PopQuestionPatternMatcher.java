package workshop.TriviaGameModule;

public class PopQuestionPatternMatcher implements QuestionPatternMatcher {
    @Override
    public boolean match(int number) {
        return number == 0 || number == 4 || number == 8;
    }

    @Override
    public String generateResponse() {
        return "Pop";
    }
}
