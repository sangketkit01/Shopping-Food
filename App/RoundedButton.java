package App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class RoundedButton extends JButton {
    private int radius;
    private Color borderColor, colorOver, colorExit;

    public RoundedButton(int radius) {
        this.radius = radius;
        setColor(borderColor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                borderColor = colorOver;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borderColor = colorExit;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                borderColor = colorOver;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                borderColor = colorExit;
                repaint();
            }
        });
    }

    public void setColor(Color color) {
        borderColor = color;
        repaint();
    }

    public void setColorOver(Color color) {
        colorOver = color;
    }

    public void setColorExit(Color color) {
        colorExit = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setBackground(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        int size = Math.max(super.getPreferredSize().width, super.getPreferredSize().height);
        return new Dimension(size, size);
    }
}
