package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UserInterface extends JFrame {

    private RoundedButton rockButton;
    private RoundedButton paperButton;
    private RoundedButton scissorsButton;
    private JLabel statusLabel;

    public UserInterface() {
        setTitle("Rock Paper Scissors");
        setSize(500, 350);
        setMinimumSize(new Dimension(400, 280));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        statusLabel = new JLabel("Choose Rock, Paper, or Scissors!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        buttonPanel.setOpaque(false);

        rockButton = new RoundedButton("Rock");
        paperButton = new RoundedButton("Paper");
        scissorsButton = new RoundedButton("Scissors");

        rockButton.setIcon(loadIcon("/images/rock.png"));
        paperButton.setIcon(loadIcon("/images/paper.png"));
        scissorsButton.setIcon(loadIcon("/images/scissors.png"));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel, BorderLayout.CENTER);

        rockButton.addActionListener(e -> statusLabel.setText("You chose Rock"));
        paperButton.addActionListener(e -> statusLabel.setText("You chose Paper"));
        scissorsButton.addActionListener(e -> statusLabel.setText("You chose Scissors"));
    }


    private ImageIcon loadIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found on classpath: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image scaled = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
