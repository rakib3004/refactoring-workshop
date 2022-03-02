package workshop.PlainTextToHTML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {

    List<SignChecker> signCheckers;

    public PlaintextToHtmlConverter(List<SignChecker> signCheckers){
        this.signCheckers = signCheckers;
    }
    

    public String toHtml() throws Exception {
        return basicHtmlEncode(read());
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get("sample.txt")));
    }

    private String basicHtmlEncode(String source) {
        
        List<String> htmlOutcomeResult = new ArrayList<>();
        List<String> convertedLine = new ArrayList<>();

        for(char signToSignatureConverter: source.toCharArray()){
            for(SignChecker signChecker: signCheckers){
                if(signChecker.matches(signToSignatureConverter)){
                    convertedLine.add(signChecker.addHtmlSign());
                }
                else if(signToSignatureConverter=='\n'){
                    addANewLine(convertedLine, htmlOutcomeResult);
                }
                else{
                    convertedLine.add(String.valueOf(signToSignatureConverter));
                }
            }
        }
        addANewLine(convertedLine, htmlOutcomeResult);
        String finalResult = String.join("<br />", htmlOutcomeResult);
        return finalResult;
    }

   
    //stringfy convertedLine array and push into result
    //reset convertedLine
    private void addANewLine(List<String> convertedLine, List<String> htmlOutcomeResult) {
        String line = String.join("", convertedLine);
        htmlOutcomeResult.add(line);
        convertedLine = new ArrayList<>();
    }

}
