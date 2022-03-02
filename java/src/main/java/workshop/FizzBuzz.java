package workshop;

import java.util.List;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
	private static List<PatternMatcher> patternMatchers;
	private static PatternMatcher nullObjectPattern;
    public FizzBuzz(List<PatternMatcher> patternMatchers, PatternMatcher nullObjectPattern) {
		super();
		FizzBuzz.patternMatchers = patternMatchers;
		FizzBuzz.nullObjectPattern = nullObjectPattern;
	}

	public static String say(int number) {
        String strReturn = nullObjectPattern.generateResponse();
        
        for (PatternMatcher patternMatcher : patternMatchers) {
        	if (patternMatcher.matches(number)) strReturn = patternMatcher.generateResponse();
		}
          
        return strReturn;
    }
}
