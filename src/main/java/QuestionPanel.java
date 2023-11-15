import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionPanel extends JPanel {
    private MainFrame frame;
    private Game game;

    private JTextArea question;
    private List<StyledButton> buttons = new ArrayList<>();
    private List<JTextArea> answers = new ArrayList<>();
    private StyledButton next = new StyledButton("Next", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);

    private List<Boolean> selected;
    private List<String> checkedAnswers;

    public QuestionPanel(MainFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
        selected = new ArrayList<>(Collections.nCopies(6, false));
        checkedAnswers = new ArrayList<>();
        init();
    }

    private void init() {
        setLayout(null);

        question = new JTextArea("    " + game.getQuestionNo() + ". " + game.getQuestion());
        question.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        question.setBounds(20, 20, 640, 120);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);
        question.setOpaque(false);
        add(question);

        buttons.add(new StyledButton("A", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));
        buttons.add(new StyledButton("B", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));
        buttons.add(new StyledButton("C", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));
        buttons.add(new StyledButton("D", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));
        buttons.add(new StyledButton("E", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));
        buttons.add(new StyledButton("F", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor));

        for (JButton button : buttons) {
            button.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        }
        buttons.get(0).setBounds(10, 160, 60, 70);
        buttons.get(1).setBounds(10, 240, 60, 70);
        buttons.get(2).setBounds(10, 320, 60, 70);
        buttons.get(3).setBounds(10, 400, 60, 70);
        buttons.get(4).setBounds(10, 480, 60, 70);
        buttons.get(5).setBounds(10, 560, 60, 70);

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            buttons.get(i).getModel().addChangeListener(e -> {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover() || (!model.isRollover() && selected.get(finalI)))
                    buttons.get(finalI).setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
                else
                    buttons.get(finalI).setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);
            });
        }

        for (int i = 0; i < 6; i++) {
            int finalI = i;
            buttons.get(i).addActionListener(e -> {
                if (!selected.get(finalI)) {
                    checkedAnswers.add(game.getIdAnswers().get(finalI));
                    buttons.get(finalI).setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
                } else {
                    checkedAnswers.remove(game.getIdAnswers().get(finalI));
                    buttons.get(finalI).setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);
                }
                selected.set(finalI, !selected.get(finalI));
            });
        }
        buttons.forEach(this::add);

        game.getAnswers().forEach(answer -> answers.add(new JTextArea(answer)));
        for (JTextArea answer : answers) {
            answer.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
            answer.setLineWrap(true);
            answer.setWrapStyleWord(true);
            answer.setEditable(false);
        }
        answers.get(0).setBounds(70, 160, 600, 70);
        answers.get(1).setBounds(70, 240, 600, 70);
        answers.get(2).setBounds(70, 320, 600, 70);
        answers.get(3).setBounds(70, 400, 600, 70);
        answers.get(4).setBounds(70, 480, 600, 70);
        answers.get(5).setBounds(70, 560, 600, 70);
        answers.forEach(this::add);

        if (game.getQuestionNo() == 10)
            next.setText("Done");

        next.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        next.setBounds(250, 650, 200, 70);
        next.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover() || model.isPressed())
                next.setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
            else
                next.setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);
        });
        next.addActionListener(e -> {
            if (checkedAnswers.size() == 0)
                JOptionPane.showMessageDialog(new JFrame(), "Trebuie să selectezi măcar o variantă de răspuns!", "Atenție!", JOptionPane.ERROR_MESSAGE);
            else {
                StringBuilder questionAnswer = new StringBuilder();
                for (String answer : checkedAnswers)
                    questionAnswer.append(",").append(answer);
                game.setQuestionAnswer(game.getIdQuestion() + questionAnswer);
                try {
                    new QuestionFromDatabase(game).setGameQuestion();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (game.getQuestionNo() == 10)
                    showFinalPanel();
                else nextQuestion();
            }
        });
        add(next);
    }

    private void nextQuestion() {
        game.setQuestionNo(game.getQuestionNo() + 1);
        frame.remove(frame.getQuestionPanel());
        frame.setQuestionPanel(new QuestionPanel(frame, game));
        frame.add(frame.getQuestionPanel());
        frame.revalidate();
        frame.repaint();
    }

    private void showFinalPanel() {
        frame.remove(frame.getQuestionPanel());
        frame.setFinalPanel(new FinalPanel(frame, game));
        frame.add(frame.getFinalPanel());
        frame.revalidate();
        frame.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gp1 = new GradientPaint(
                getWidth() / 2, 0, new Color(12, 204, 204),
                getWidth() / 2, getHeight() / 2, new Color(45, 127, 227));
        GradientPaint gp2 = new GradientPaint(
                getWidth() / 2, getHeight() / 2, new Color(45, 127, 227),
                getWidth() / 2, getHeight(), new Color(168, 25, 190));
        g2d.setPaint(gp1);
        g.fillRect(0, 0, getWidth(), getHeight() / 2);
        g2d.setPaint(gp2);
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight());
    }
}
