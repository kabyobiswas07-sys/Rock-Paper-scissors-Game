package ui;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UserInterface extends JFrame {

    private Controller controller;

    private RoundedButton rockButton;
    private RoundedButton paperButton;
    private RoundedButton scissorsButton;
    private JButton       resetButton;

    private JLabel statusLabel;
    private JLabel choiceLabel;

    public UserInterface() {
        controller = new Controller();  

        setTitle("Rock Paper Scissors");
        setSize(520, 400);
        setMinimumSize(new Dimension(420, 320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        statusLabel = new JLabel("Choose Rock, Paper, or Scissors!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 4, 10));
        add(statusLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        buttonPanel.setOpaque(false);

        rockButton     = new RoundedButton("Rock");
        paperButton    = new RoundedButton("Paper");
        scissorsButton = new RoundedButton("Scissors");

        rockButton.setIcon(loadIcon("/images/rock.png"));
        paperButton.setIcon(loadIcon("/images/paper.png"));
        scissorsButton.setIcon(loadIcon("/images/scissors.png"));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 6));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(6, 20, 16, 20));
        bottomPanel.setOpaque(false);

        choiceLabel = new JLabel(" ", SwingConstants.CENTER);
        choiceLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        choiceLabel.setForeground(new Color(60, 90, 140));
        bottomPanel.add(choiceLabel, BorderLayout.CENTER);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 13));
        resetButton.setFocusPainted(false);
        JPanel resetWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        resetWrapper.setOpaque(false);
        resetWrapper.add(resetButton);
        bottomPanel.add(resetWrapper, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(e -> handleChoice("Rock"));
        paperButton.addActionListener(e -> handleChoice("Paper"));
        scissorsButton.addActionListener(e -> handleChoice("Scissors"));

        resetButton.addActionListener(e -> {
            String msg = controller.handleReset();
            statusLabel.setText(msg);
            choiceLabel.setText(" ");
        });
    }

    private void handleChoice(String choice) {
        String result = controller.handlePlayerChoice(choice);
        statusLabel.setText(result);
        choiceLabel.setText("Player selection recorded ✓");
    }
    private ImageIcon loadIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found: " + path);
            return null;
        }
        Image scaled = new ImageIcon(url).getImage()
                           .getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
