import javax.swing.*;

import java.awt.*;
import java.sql.SQLException;

public class StartPanel extends JPanel {
    private MainFrame frame;
    private Game game;

    private JLabel label1 = new JLabel("Bine ați venit!");
    private JLabel label2 = new JLabel("Introduceți numele de utilizator:");
    private JTextField field = new JTextField();
    private StyledButton start = new StyledButton("Start quiz", MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);

    public StartPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(null);

        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        label1.setBounds(180, 50, 340, 60);
        add(label1);

        label2.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        label2.setBounds(120, 180, 500, 50);
        label2.setForeground(new Color(255, 255, 255));
        add(label2);

        field.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 40));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setBounds(100, 260, 500, 70);
        add(field);

        start.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        start.setBounds(230, 400, 240, 70);

        start.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isRollover() || model.isPressed())
                start.setColors(MainFrame.color3, MainFrame.color4, Color.WHITE);
            else
                start.setColors(MainFrame.color1, MainFrame.color2, MainFrame.buttonTextColor);
        });

        start.addActionListener(e -> {
            if (field.getText().equals(""))
                JOptionPane.showMessageDialog(new JFrame(), "Numele de utilizator nu poate fi gol!", "Atenție!", JOptionPane.ERROR_MESSAGE);
            else {
                game = new Game(field.getText());
                try {
                    new QuestionFromDatabase(game).setGameQuestion();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.remove(frame.getStartPanel());
                frame.setQuestionPanel(new QuestionPanel(frame, game));
                frame.add(frame.getQuestionPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
        add(start);
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
