import javax.swing.*;
import java.awt.*;

public class RocketPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int rocketY = 300; // Initial Y position of the rocket

    public RocketPanel() {
        setBackground(Color.BLACK);
    }

    public void setRocketPosition(double altitude) {
        this.rocketY = getHeight() - (int) (altitude / 10000 * getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect((getWidth() / 2) - 10, rocketY, 20, 50);
    }
}
