package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RoundedButton extends JButton {

    private int cornerRadius = 30;

   
    private float scale = 1.0f;
    private Timer animTimer;

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 15));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(new Color(50, 60, 80));

      
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stopAnimation();
                scale = 0.93f;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               
                animTimer = new Timer(16, null);
                animTimer.addActionListener(evt -> {
                    scale += (1.0f - scale) * 0.25f;
                    if (Math.abs(scale - 1.0f) < 0.005f) {
                        scale = 1.0f;
                        stopAnimation();
                    }
                    repaint();
                });
                animTimer.start();
            }
        });
    }

    private void stopAnimation() {
        if (animTimer != null && animTimer.isRunning()) {
            animTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

       
        if (scale != 1.0f) {
            float tx = w * (1 - scale) / 2f;
            float ty = h * (1 - scale) / 2f;
            g2.translate(tx, ty);
            g2.scale(scale, scale);
        }

       
        g2.setColor(new Color(0, 0, 0, 25));
        g2.fillRoundRect(3, 5, w - 4, h - 4, cornerRadius, cornerRadius);

       
        Color top, bottom;
        if (getModel().isPressed()) {
            top    = new Color(160, 185, 220);
            bottom = new Color(130, 160, 200);
        } else if (getModel().isRollover()) {
            top    = new Color(220, 232, 250);
            bottom = new Color(195, 212, 240);
        } else {
            top    = new Color(240, 245, 255);
            bottom = new Color(215, 228, 248);
        }

        GradientPaint gradient = new GradientPaint(0, 0, top, 0, h, bottom);
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, w - 2, h - 3, cornerRadius, cornerRadius);

      
        g2.setColor(new Color(160, 185, 215));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawRoundRect(0, 0, w - 2, h - 3, cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}
