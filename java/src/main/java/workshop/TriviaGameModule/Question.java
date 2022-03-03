package workshop.TriviaGameModule;


import java.util.ArrayList;

public class Question {
    private ArrayList<String> question = new ArrayList<>();
    private String question_type;

    public Question(String question_type) {
        this.question_type = question_type;
        generateQuestion();
    }

    private void generateQuestion() {
        for (int i = 0; i < 50; i++) {
            question.add(this.question_type + " Question " + i);
        }
    }

    public String getQuestion_type() {
        return question_type;
    }

    String getQuestion() {
        return question.remove(0);
    }
}