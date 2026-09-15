package ui;

import controller.Controller;
import model.DataModel;
import utils.SoundPlayer;
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
    private JLabel vsLabel;

   
    private Timer flashTimer;

    public UserInterface() {
        controller = new Controller();

        setTitle("Rock Paper Scissors  |  Wins: 0  Losses: 0  Draws: 0");
        setSize(580, 600);
        setMinimumSize(new Dimension(480, 520));
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
        g2.setColor(new Color(200, 210, 225));
        g2.fillOval(0, 0, size, size);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 32));
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString("?",
            (size - fm.stringWidth("?")) / 2,
            (size - fm.getHeight()) / 2 + fm.getAscent());
        g2.dispose();
        return new ImageIcon(img);
    }

   

    private void initComponents() {

     
        JPanel background = new GradientPanel();
        background.setLayout(new BorderLayout(0, 0));
        setContentPane(background);

    
        background.add(buildScorePanel(),  BorderLayout.NORTH);

      
        JPanel centerPanel = new JPanel(new BorderLayout(0, 4));
        centerPanel.setOpaque(false);
        centerPanel.add(buildStatusLabel(),  BorderLayout.NORTH);
        centerPanel.add(buildVsPanel(),      BorderLayout.CENTER);
        centerPanel.add(buildResultBanner(), BorderLayout.SOUTH);
        background.add(centerPanel, BorderLayout.CENTER);

      
        background.add(buildBottomPanel(), BorderLayout.SOUTH);
    }

  

   
  
    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(
                0, 0,            new Color(230, 238, 255),  
                0, getHeight(),  new Color(255, 255, 255)   
            );
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

 

    private JPanel buildScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 0, 0));
        panel.setBackground(new Color(30, 34, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        winLabel   = makeScoreLabel("Wins: 0",    new Color(80,  200, 120));
        loseLabel  = makeScoreLabel("Losses: 0",  new Color(220, 80,  80));
        drawLabel  = makeScoreLabel("Draws: 0",   new Color(180, 180, 190));
        roundLabel = makeScoreLabel("Rounds: 0",  new Color(140, 170, 220));

        panel.add(winLabel);
        panel.add(loseLabel);
        panel.add(drawLabel);
        panel.add(roundLabel);
        return panel;
    }

    private JLabel makeScoreLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(color);
        return label;
    }

    private void refreshScorePanel() {
        winLabel.setText("Wins: "    + controller.getWinCount());
        loseLabel.setText("Losses: " + controller.getLoseCount());
        drawLabel.setText("Draws: "  + controller.getDrawCount());
        roundLabel.setText("Rounds: "+ controller.getTotalRounds());


        setTitle("Rock Paper Scissors  |  Wins: " + controller.getWinCount()
               + "  Losses: " + controller.getLoseCount()
               + "  Draws: "  + controller.getDrawCount());
    }

  

    private JPanel buildStatusLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        statusLabel = new JLabel("Choose Rock, Paper, or Scissors!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
        statusLabel.setForeground(new Color(50, 60, 90));
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

    
        vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 28));
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

   

    private JPanel buildResultBanner() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        resultBanner = new JLabel(" ", SwingConstants.CENTER);
        resultBanner.setFont(new Font("Arial", Font.BOLD, 30));
        resultBanner.setVisible(false);
        panel.add(resultBanner);
        return panel;
    }

  

    private JPanel buildBottomPanel() {
        JPanel wrapper = new JPanel(new BorderLayout(0, 10));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 18, 20));
        wrapper.setOpaque(false);

      
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        buttonPanel.setOpaque(false);

        rockButton     = new RoundedButton("Rock");
        paperButton    = new RoundedButton("Paper");
        scissorsButton = new RoundedButton("Scissors");

        rockButton.setIcon(loadIcon("/images/rock.png",         42));
        paperButton.setIcon(loadIcon("/images/paper.png",       42));
        scissorsButton.setIcon(loadIcon("/images/scissors.png", 42));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

      
        JPanel resetPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        resetPanel.setOpaque(false);

        resetRoundButton = new JButton("New Round");
        styleResetButton(resetRoundButton, new Color(60, 90, 150));

        resetAllButton = new JButton("Reset Scores");
        styleResetButton(resetAllButton, new Color(160, 40, 40));

        resetPanel.add(resetRoundButton);
        resetPanel.add(resetAllButton);

        wrapper.add(buttonPanel,  BorderLayout.CENTER);
        wrapper.add(resetPanel,   BorderLayout.SOUTH);

       
        rockButton.addActionListener(e -> {
            SoundPlayer.playClick();
            handleChoice("Rock");
        });
        paperButton.addActionListener(e -> {
            SoundPlayer.playClick();
            handleChoice("Paper");
        });
        scissorsButton.addActionListener(e -> {
            SoundPlayer.playClick();
            handleChoice("Scissors");
        });

        resetRoundButton.addActionListener(e -> {
            SoundPlayer.playClick();
            handleNewRound();
        });

        resetAllButton.addActionListener(e -> {
            SoundPlayer.playClick();
            handleResetAll();
        });

        return wrapper;
    }

    
    private void styleResetButton(JButton btn, Color color) {
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setForeground(color);
        btn.setBackground(new Color(240, 243, 250));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 1, true),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

   

    private void handleChoice(String choice) {
        stopFlashAnimation();

        String message = controller.handlePlayerChoice(choice);
        statusLabel.setText(message);

      
        playerIconLabel.setIcon(getIconFor(choice));
        playerNameLabel.setText("You: " + choice);

        String compChoice = controller.getComputerChoice();
        computerIconLabel.setIcon(getIconFor(compChoice));
        computerNameLabel.setText("CPU: " + compChoice);

     
        String result = controller.getResult();
        showResultBannerAnimated(result);
        playSoundForResult(result);

      
        refreshScorePanel();
    }

    private void handleNewRound() {
        stopFlashAnimation();
        statusLabel.setText(controller.handleReset());
        playerIconLabel.setIcon(unknownIcon);
        computerIconLabel.setIcon(unknownIcon);
        playerNameLabel.setText("You");
        computerNameLabel.setText("Computer");
        resultBanner.setVisible(false);
        vsLabel.setForeground(new Color(180, 60, 60));
    }

    private void handleResetAll() {
        stopFlashAnimation();
        statusLabel.setText(controller.handleResetAll());
        playerIconLabel.setIcon(unknownIcon);
        computerIconLabel.setIcon(unknownIcon);
        playerNameLabel.setText("You");
        computerNameLabel.setText("Computer");
        resultBanner.setVisible(false);
        vsLabel.setForeground(new Color(180, 60, 60));
        refreshScorePanel();
    }

 
    private void showResultBannerAnimated(String result) {
       
        Color resultColor;
        switch (result) {
            case DataModel.WIN:
                resultBanner.setText("🎉  YOU WIN!  🎉");
                resultColor = new Color(0, 160, 70);
                break;
            case DataModel.LOSE:
                resultBanner.setText("😞  YOU LOSE  😞");
                resultColor = new Color(200, 30, 30);
                break;
            default:
                resultBanner.setText("🤝  DRAW  🤝");
                resultColor = new Color(100, 100, 130);
                break;
        }
        resultBanner.setForeground(resultColor);
        resultBanner.setVisible(true);

       
        vsLabel.setForeground(resultColor);

      
        final int[] flashCount = {0};
        final int maxFlashes   = 6;   

        flashTimer = new Timer(200, null);
        flashTimer.addActionListener(e -> {
            flashCount[0]++;
            resultBanner.setVisible(flashCount[0] % 2 == 0); 
            if (flashCount[0] >= maxFlashes) {
                flashTimer.stop();
                resultBanner.setVisible(true);              
            }
        });
        flashTimer.start();
    }

    private void stopFlashAnimation() {
        if (flashTimer != null && flashTimer.isRunning()) {
            flashTimer.stop();
        }
        resultBanner.setVisible(false);
    }



    private void playSoundForResult(String result) {
        switch (result) {
            case DataModel.WIN:  SoundPlayer.playWin();  break;
            case DataModel.LOSE: SoundPlayer.playLose(); break;
            default:             SoundPlayer.playDraw(); break;
        }
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
