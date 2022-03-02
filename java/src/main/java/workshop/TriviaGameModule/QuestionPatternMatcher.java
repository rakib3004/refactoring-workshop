package workshop.TriviaGameModule;

public interface QuestionPatternMatcher {
    boolean match(int number);
    String generateResponse();
}