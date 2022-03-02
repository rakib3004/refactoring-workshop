package workshop.PlainTextToHTML;

public class AndSignChecker implements SignChecker {

    public boolean matches(char character) {

        return character=='&';
    }
    public String addHtmlSign(){
        return "&amp;";
    }
}
