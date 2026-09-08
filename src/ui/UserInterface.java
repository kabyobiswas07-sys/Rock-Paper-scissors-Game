package ui;

import controller.Controller;
import model.DataModel;
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

  
    private JButton resetRoundButton;  
    private JButton resetAllButton;     

    // Score labels ← Week 7
    private JLabel winLabel;
    private JLabel loseLabel;
    private JLabel drawLabel;
    private JLabel roundLabel;

   
    private JLabel statusLabel;
    private JLabel resultBanner;
    private JLabel playerIconLabel;
    private JLabel computerIconLabel;
    private JLabel playerNameLabel;
    private JLabel computerNameLabel;

    public UserInterface() {
        controller = new Controller();

        setTitle("Rock Paper Scissors");
        setSize(560, 580);
        setMinimumSize(new Dimension(460, 500));
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
        setLayout(new BorderLayout(0, 0));

        
        JPanel topSection = new JPanel(new BorderLayout());
        topSection.add(buildScorePanel(), BorderLayout.NORTH);   // ← NEW
        topSection.add(buildStatusLabel(), BorderLayout.SOUTH);
        add(topSection, BorderLayout.NORTH);

        
        JPanel centerPanel = new JPanel(new BorderLayout(0, 6));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        centerPanel.add(buildVsPanel(),      BorderLayout.CENTER);
        centerPanel.add(buildResultBanner(), BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

     
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }

    //  Score panel ( Week 7) 


    private JPanel buildScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 0, 0));
        panel.setBackground(new Color(40, 44, 52));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        winLabel   = makeScoreLabel("Wins: 0",    new Color(80, 200, 120));
        loseLabel  = makeScoreLabel("Losses: 0",  new Color(220, 80,  80));
        drawLabel  = makeScoreLabel("Draws: 0",   new Color(180, 180, 180));
        roundLabel = makeScoreLabel("Rounds: 0",  new Color(150, 180, 220));

        panel.add(winLabel);
        panel.add(loseLabel);
        panel.add(drawLabel);
        panel.add(roundLabel);

        return panel;
    }

   
    private JLabel makeScoreLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(color);
        return label;
    }

    
    private void refreshScorePanel() {
        winLabel.setText("Wins: "    + controller.getWinCount());
        loseLabel.setText("Losses: " + controller.getLoseCount());
        drawLabel.setText("Draws: "  + controller.getDrawCount());
        roundLabel.setText("Rounds: "+ controller.getTotalRounds());
    }

    

    private JPanel buildStatusLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        statusLabel = new JLabel("Choose Rock, Paper, or Scissors!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 4, 10));
        panel.add(statusLabel);
        return panel;
    }

  

    private JPanel buildVsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 30, 6, 30));
        panel.setOpaque(false);

        
        JPanel playerPanel = new JPanel(new BorderLayout(0, 6));
        playerPanel.setOpaque(false);
        playerIconLabel = new JLabel(unknownIcon, SwingConstants.CENTER);
        playerNameLabel = new JLabel("You", SwingConstants.CENTER);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        playerNameLabel.setForeground(new Color(40, 80, 160));
        playerPanel.add(playerIconLabel, BorderLayout.CENTER);
        playerPanel.add(playerNameLabel, BorderLayout.SOUTH);

     
        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 26));
        vsLabel.setForeground(new Color(180, 60, 60));

      
        JPanel computerPanel = new JPanel(new BorderLayout(0, 6));
        computerPanel.setOpaque(false);
        computerIconLabel = new JLabel(unknownIcon, SwingConstants.CENTER);
        computerNameLabel = new JLabel("Computer", SwingConstants.CENTER);
        computerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        computerNameLabel.setForeground(new Color(160, 40, 40));
        computerPanel.add(computerIconLabel, BorderLayout.CENTER);
        computerPanel.add(computerNameLabel, BorderLayout.SOUTH);

        panel.add(playerPanel);
        panel.add(vsLabel);
        panel.add(computerPanel);
        return panel;
    }

    // Result banner 

    private JPanel buildResultBanner() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        resultBanner = new JLabel(" ", SwingConstants.CENTER);
        resultBanner.setFont(new Font("Arial", Font.BOLD, 28));
        resultBanner.setVisible(false);
        panel.add(resultBanner);
        return panel;
    }

  

    private JPanel buildBottomPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 8));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 16, 20));
        wrapper.setOpaque(false);

        // Three choice buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        buttonPanel.setOpaque(false);

        rockButton     = new RoundedButton("Rock");
        paperButton    = new RoundedButton("Paper");
        scissorsButton = new RoundedButton("Scissors");

        rockButton.setIcon(loadIcon("/images/rock.png",         40));
        paperButton.setIcon(loadIcon("/images/paper.png",       40));
        scissorsButton.setIcon(loadIcon("/images/scissors.png", 40));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        // Two reset buttons side by side
        JPanel resetPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        resetPanel.setOpaque(false);

        resetRoundButton = new JButton("New Round");
        resetRoundButton.setFont(new Font("Arial", Font.PLAIN, 13));
        resetRoundButton.setFocusPainted(false);

        resetAllButton = new JButton("Reset Scores");          // ← NEW
        resetAllButton.setFont(new Font("Arial", Font.PLAIN, 13));
        resetAllButton.setFocusPainted(false);
        resetAllButton.setForeground(new Color(180, 40, 40));  

        resetPanel.add(resetRoundButton);
        resetPanel.add(resetAllButton);

        wrapper.add(buttonPanel,  BorderLayout.CENTER);
        wrapper.add(resetPanel,   BorderLayout.SOUTH);

     
        rockButton.addActionListener(e     -> handleChoice("Rock"));
        paperButton.addActionListener(e    -> handleChoice("Paper"));
        scissorsButton.addActionListener(e -> handleChoice("Scissors"));

      
        resetRoundButton.addActionListener(e -> {
            statusLabel.setText(controller.handleReset());
            playerIconLabel.setIcon(unknownIcon);
            computerIconLabel.setIcon(unknownIcon);
            playerNameLabel.setText("You");
            computerNameLabel.setText("Computer");
            resultBanner.setVisible(false);
        });


        resetAllButton.addActionListener(e -> {
            statusLabel.setText(controller.handleResetAll());
            playerIconLabel.setIcon(unknownIcon);
            computerIconLabel.setIcon(unknownIcon);
            playerNameLabel.setText("You");
            computerNameLabel.setText("Computer");
            resultBanner.setVisible(false);
            refreshScorePanel();                               // ← reset score display too
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

      
        showResultBanner(controller.getResult());

        // Update score panel ← NEW Week 7
        refreshScorePanel();
    }

    private void showResultBanner(String result) {
        switch (result) {
            case DataModel.WIN:
                resultBanner.setText("🎉  YOU WIN!  🎉");
                resultBanner.setForeground(new Color(0, 150, 60));
                break;
            case DataModel.LOSE:
                resultBanner.setText("😞  YOU LOSE  😞");
                resultBanner.setForeground(new Color(200, 30, 30));
                break;
            default:
                resultBanner.setText("🤝  DRAW  🤝");
                resultBanner.setForeground(new Color(100, 100, 120));
                break;
        }
        resultBanner.setVisible(true);
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
