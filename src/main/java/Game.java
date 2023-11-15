import java.util.ArrayList;
import java.util.List;

public class Game {
    private String username;
    private String questionAnswer;
    private String idQuestion;
    private String question;
    private List<String> idAnswers;
    private List<String> answers;
    private int questionNo;
    private float score;

    public Game(String username) {
        this.username = username;
        this.questionAnswer = "";
        idAnswers = new ArrayList<>();
        answers = new ArrayList<>();
        questionNo = 1;
        score = -1;
    }

    public String getUsername() {
        return username;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getIdAnswers() {
        return idAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public float getScore() {
        return score;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setIdAnswers(List<String> idAnswers) {
        this.idAnswers = idAnswers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }
}
