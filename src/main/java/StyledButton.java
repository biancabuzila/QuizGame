import javax.swing.*;
import java.awt.*;

public class StyledButton extends JButton {
    private Color color1, color2;
    private Color textColor;

    public StyledButton(String text, Color color1, Color color2, Color textColor) {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        this.textColor = textColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setContentAreaFilled(false);
        setForeground(textColor);
        Graphics2D graphics = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0, 0, color1,
                getWidth(), getHeight(), color2);
        graphics.setPaint(gp);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void setColors(Color color1, Color color2, Color textColor) {
        this.color1 = color1;
        this.color2 = color2;
        this.textColor = textColor;
        revalidate();
        repaint();
    }
}
