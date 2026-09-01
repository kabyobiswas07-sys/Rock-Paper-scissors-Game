package ui;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class UserInterface extends JFrame {

    private Controller controller;

    
    private ImageIcon rockIcon;
    private ImageIcon paperIcon;
    private ImageIcon scissorsIcon;
    private ImageIcon unknownIcon;  

    
    private RoundedButton rockButton;
    private RoundedButton paperButton;
    private RoundedButton scissorsButton;
    private JButton       resetButton;

    
    private JLabel statusLabel;          
    private JLabel playerIconLabel;      
    private JLabel computerIconLabel;    
    private JLabel playerNameLabel;     
    private JLabel computerNameLabel;    

    public UserInterface() {
        controller = new Controller();

        setTitle("Rock Paper Scissors");
        setSize(560, 460);
        setMinimumSize(new Dimension(460, 380));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loadIcons();
        initComponents();
        setVisible(true);
    }

    

    private void loadIcons() {
        rockIcon     = loadIcon("/images/rock.png",     64);
        paperIcon    = loadIcon("/images/paper.png",    64);
        scissorsIcon = loadIcon("/images/scissors.png", 64);
        unknownIcon  = makeQuestionMarkIcon();         
    }

  
    private ImageIcon makeQuestionMarkIcon() {
        int size = 64;
        java.awt.image.BufferedImage img =
            new java.awt.image.BufferedImage(size, size,
                java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(200, 200, 210));
        g2.fillOval(0, 0, size, size);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString("?", (size - fm.stringWidth("?")) / 2,
                           (size - fm.getHeight()) / 2 + fm.getAscent());
        g2.dispose();
        return new ImageIcon(img);
    }

    

    private void initComponents() {

        
        statusLabel = new JLabel("Choose Rock, Paper, or Scissors!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(12, 10, 8, 10));
        add(statusLabel, BorderLayout.NORTH);

        
        JPanel vsPanel = buildVsPanel();
        add(vsPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = buildBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

   
    private JPanel buildVsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panel.setOpaque(false);

        
        JPanel playerPanel = new JPanel(new BorderLayout(0, 6));
        playerPanel.setOpaque(false);
        playerIconLabel = new JLabel(unknownIcon, SwingConstants.CENTER);
        playerNameLabel = new JLabel("You", SwingConstants.CENTER);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerNameLabel.setForeground(new Color(40, 80, 160));
        playerPanel.add(playerIconLabel,  BorderLayout.CENTER);
        playerPanel.add(playerNameLabel,  BorderLayout.SOUTH);

       
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 26));
        vsLabel.setForeground(new Color(180, 60, 60));

        
        JPanel computerPanel = new JPanel(new BorderLayout(0, 6));
        computerPanel.setOpaque(false);
        computerIconLabel = new JLabel(unknownIcon, SwingConstants.CENTER);
        computerNameLabel = new JLabel("Computer", SwingConstants.CENTER);
        computerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        computerNameLabel.setForeground(new Color(160, 40, 40));
        computerPanel.add(computerIconLabel,  BorderLayout.CENTER);
        computerPanel.add(computerNameLabel,  BorderLayout.SOUTH);

        panel.add(playerPanel);
        panel.add(vsLabel);
        panel.add(computerPanel);

        return panel;
    }

   
    private JPanel buildBottomPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 16, 20));
        wrapper.setOpaque(false);

      
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        buttonPanel.setOpaque(false);

        rockButton     = new RoundedButton("Rock");
        paperButton    = new RoundedButton("Paper");
        scissorsButton = new RoundedButton("Scissors");

        rockButton.setIcon(loadIcon("/images/rock.png",     40));
        paperButton.setIcon(loadIcon("/images/paper.png",   40));
        scissorsButton.setIcon(loadIcon("/images/scissors.png", 40));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 13));
        resetButton.setFocusPainted(false);
        JPanel resetWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        resetWrapper.setOpaque(false);
        resetWrapper.add(resetButton);

        wrapper.add(buttonPanel,  BorderLayout.CENTER);
        wrapper.add(resetWrapper, BorderLayout.SOUTH);

      
        rockButton.addActionListener(e     -> handleChoice("Rock"));
        paperButton.addActionListener(e    -> handleChoice("Paper"));
        scissorsButton.addActionListener(e -> handleChoice("Scissors"));

        resetButton.addActionListener(e -> {
            statusLabel.setText(controller.handleReset());
            playerIconLabel.setIcon(unknownIcon);
            computerIconLabel.setIcon(unknownIcon);
            playerNameLabel.setText("You");
            computerNameLabel.setText("Computer");
        });

        return wrapper;
    }

    
    private void handleChoice(String choice) {
        
        String message = controller.handlePlayerChoice(choice);
        statusLabel.setText(message);

        
        playerIconLabel.setIcon(getIconFor(choice));
        playerNameLabel.setText("You: " + choice);

        
        String compChoice = controller.getComputerChoice();
        computerIconLabel.setIcon(getIconFor(compChoice));
        computerNameLabel.setText("CPU: " + compChoice);
    }

    
    private ImageIcon getIconFor(String choice) {
        switch (choice) {
            case "Rock":     return rockIcon;
            case "Paper":    return paperIcon;
            case "Scissors": return scissorsIcon;
            default:         return unknownIcon;
        }
    }

 
    private ImageIcon loadIcon(String path, int size) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.out.println("Icon not found: " + path);
            return null;
        }
        Image scaled = new ImageIcon(url).getImage()
                           .getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
