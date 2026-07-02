package ui;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    private int cornerRadius = 25;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);   
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setVerticalTextPosition(SwingConstants.BOTTOM);  
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.PLAIN, 16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(new Color(180, 200, 230));
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(210, 225, 245));
        } else {
            g2.setColor(new Color(235, 240, 250));
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.setColor(new Color(150, 170, 200));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}
