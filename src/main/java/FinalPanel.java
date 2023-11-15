import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FinalPanel extends JPanel {
    private MainFrame frame;
    private Game game;

    private JLabel label = new JLabel("Punctajul obținut este:");
    private JLabel score = new JLabel();
    private JLabel message = new JLabel();
    private StyledButton exit = new StyledButton("Ieșire", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);
    private StyledButton tryAgain = new StyledButton("Test nou", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);

    public FinalPanel(MainFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
        init();
    }

    private void init() {
        setLayout(null);

        label.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        label.setBounds(50, 100, 550, 60);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        score.setText(String.valueOf(game.getScore()));
        score.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        score.setForeground(new Color(214, 203, 243));
        score.setBounds(250, 190, 200, 70);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        add(score);

        if (game.getScore() == 10)
            message.setText("Felicitări! Ești un geniu!");
        else if (game.getScore() >= 80 && game.getScore() < 100)
            message.setText("Te-ai descurcat foarte bine!");
        else if (game.getScore() >= 55 && game.getScore() < 80)
            message.setText("Te-ai descurcat bine!");
        else if (game.getScore() >= 50 && game.getScore() < 55)
            message.setText("Mai învață! Ai fost la limită.");
        else message.setText("Nu te descuraja! Mai învață!");
        message.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        message.setBounds(70, 320, 560, 50);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        add(message);

        exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        exit.setBounds(250, 430, 200, 60);
        exit.addActionListener(e -> {
            Database.closeConnection();
            System.exit(0);
        });
        exit.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover() || model.isPressed())
                exit.setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
            else
                exit.setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);

        });
        add(exit);

        tryAgain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        tryAgain.setBounds(225, 520, 250, 60);
        tryAgain.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover() || model.isPressed())
                tryAgain.setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
            else
                tryAgain.setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);

        });
        tryAgain.addActionListener(e -> {
            game = new Game(game.getUsername());
            try {
                new QuestionFromDatabase(game).setGameQuestion();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            frame.remove(frame.getFinalPanel());
            frame.setQuestionPanel(new QuestionPanel(frame, game));
            frame.add(frame.getQuestionPanel());
            frame.revalidate();
            frame.repaint();
        });
        add(tryAgain);
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
