package workshop;

public class FizzBuzzPatternMatcher implements PatternMatcher {
    @Override
    public boolean matches(int number) {
        // TODO Auto-generated method stub
        return number % 7 == 0;
    }

    @Override
    public String generateResponse() {
        // TODO Auto-generated method stub
        return "FizzBuzz";
    }

}
