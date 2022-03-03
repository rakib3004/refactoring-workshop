package workshop.TriviaGameModule;


public class ScienceQuestionPatternMatcher implements QuestionPatternMatcher{
    @Override
    public boolean match(int number) {
        return number == 1 || number == 5 || number == 9;
    }

    @Override
    public String generateResponse() {
        return "Science";
    }
}