import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private StartPanel startPanel;
    private QuestionPanel questionPanel;
    private FinalPanel finalPanel;

    final static Color color1 = new Color(120, 195, 241);
    final static Color color2 = new Color(191, 214, 255);
    final static Color color3 = new Color(81, 150, 255);
    final static Color color4 = new Color(168, 25, 190);
    final static Color buttonTextColor = new Color(54, 54, 54, 255);

    public MainFrame() {
        super("Quiz Game");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 800);
        setLocation(400, 10);
        setBackground(new Color(0, 0, 0));

        startPanel = new StartPanel(this);
        add(startPanel);
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public QuestionPanel getQuestionPanel() {
        return questionPanel;
    }

    public FinalPanel getFinalPanel() {
        return finalPanel;
    }

    public void setQuestionPanel(QuestionPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public void setFinalPanel(FinalPanel finalPanel) {
        this.finalPanel = finalPanel;
    }
}
