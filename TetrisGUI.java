import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.sound.sampled.*;


public class TetrisGUI extends JFrame implements KeyListener, ActionListener {
    TetrisGame game;

    JFrame frame;

    JPanel gamePanel;
    JPanel nextPanel;
    JPanel holdPanel;
    JPanel scorePanel;
    JPanel offsetPanel;
    JPanel startPanel;
    JPanel pausePanel;
    JPanel tintPanel;
    JPanel howtoplayPanel;
    JPanel highscorePanel;
    JPanel gameendPanel;
    JPanel newhighscorPanel;
    JPanel highscorePanel2;
    
    JLabel nextPanelLabel;
    JLabel holdPanelLabel;
    JLabel holdLabel;
    JLabel titleLabel;
    JLabel playLabel;
    JLabel nextLabel;
    JLabel gamePausedLabel;
    JLabel highScoreLabel;
    JLabel highScore1Label;
    JLabel highScore2Label;
    JLabel highScore3Label;
    JLabel highScore4Label;
    JLabel highScore5Label;
    JLabel gameoverLabel;
    JLabel newhighscorLabel;
    JLabel enterusernamLabel;
    JLabel newscoreLabel;
    JLabel howtoLabel;
    JLabel highScoreLabel2;
    JLabel highScore1Label2;
    JLabel highScore2Label2;
    JLabel highScore3Label2;
    JLabel highScore4Label2;
    JLabel highScore5Label2;

    public JTextField usernamTextField;

    JButton lButton;
    JButton rButton;
    JButton doneButton;
    JButton howtoplayButton;
    JButton muteButton;
    JButton levelButton;
    JButton resumeButton;
    JButton howButton;
    JButton quitButton;
    JButton pauseButton;
    JButton playButton;
    JButton quitButton2;
    JButton howtoplayButton2;
    JButton replayButton;
    JButton themeButton;
    JButton okButton;

    
    public TetrisGUI() {
        
        frame = new JFrame();
        
        gamePanel = new JPanel(new BorderLayout());
        nextPanel = new JPanel();
        holdPanel = new JPanel();
        scorePanel = new JPanel();
        offsetPanel = new JPanel();
        startPanel = new JPanel();
        pausePanel = new JPanel();
        tintPanel = new JPanel();
        howtoplayPanel = new JPanel();
        highscorePanel = new JPanel();
        gameendPanel = new JPanel();
        newhighscorPanel = new JPanel();
        highscorePanel2 = new JPanel();

        game = new TetrisGame();
        
        lButton = new JButton();
        rButton = new JButton();
        doneButton = new JButton();
        howtoplayButton = new JButton();
        muteButton = new JButton();
        levelButton = new JButton();
        resumeButton = new JButton();
        howButton = new JButton();
        quitButton= new JButton();
        pauseButton = new JButton();
        playButton = new JButton();
        quitButton2 = new JButton();
        howtoplayButton2 = new JButton();
        replayButton = new JButton();
        themeButton = new JButton();
        okButton = new JButton();

        gamePausedLabel = new JLabel("GAME PAUSED",SwingConstants.CENTER);
        highScoreLabel = new JLabel("HIGH SCORES",SwingConstants.CENTER);
        highScoreLabel2 = new JLabel("HIGH SCORES",SwingConstants.CENTER);
        holdLabel = new JLabel("HOLD",SwingConstants.CENTER);
        nextLabel = new JLabel("NEXT",SwingConstants.CENTER);  
        highScore1Label = new JLabel("",SwingConstants.CENTER);
        highScore2Label = new JLabel("",SwingConstants.CENTER);
        highScore3Label = new JLabel("",SwingConstants.CENTER);
        highScore4Label = new JLabel("",SwingConstants.CENTER);
        highScore5Label = new JLabel("",SwingConstants.CENTER);
        highScore1Label2 = new JLabel("",SwingConstants.CENTER);
        highScore2Label2 = new JLabel("",SwingConstants.CENTER);
        highScore3Label2 = new JLabel("",SwingConstants.CENTER);
        highScore4Label2 = new JLabel("",SwingConstants.CENTER);
        highScore5Label2 = new JLabel("",SwingConstants.CENTER);
        gameoverLabel = new JLabel("GAME OVER",SwingConstants.CENTER);
        newhighscorLabel = new JLabel("NEW HIGH SCORE",SwingConstants.CENTER);
        newscoreLabel = new JLabel("",SwingConstants.CENTER);
        enterusernamLabel = new JLabel("ENTER YOUR USERNAME:",SwingConstants.CENTER);
        howtoLabel = new JLabel();
        holdPanelLabel = new JLabel();
        nextPanelLabel = new JLabel();

        usernamTextField = new JTextField(20);

        addKeyListener(this);
        setFocusable(true);
        requestFocus();

    }

    Color trans = new Color(0,0,0,0);

    public void setupBackground(String backgroundPath, JComponent jComponent) {
        JLabel lblNewLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(backgroundPath);
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(jComponent.getWidth(),jComponent.getHeight(), Image.SCALE_DEFAULT);
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        jComponent.add(lblNewLabel); 
    }

    public ImageIcon setupIcon(String iconPath, JComponent jComponent) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        int buttonWidth = jComponent.getWidth();
        int buttonHeight = jComponent.getHeight();
        Image scaledImage = originalIcon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);           
        return scaledIcon;
    }

    private Clip clip;

    public void playSound(String soundName) {            
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    } 

    public Font font() {
        try {
            File fontFile = new File("C:/Programming Course/CBL-Assignment/font/SummerPixel22Regular-jE0W7.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font sizedFont = font.deriveFont(39f);
            return sizedFont;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public class player {
        private String username;
        private int highscore;

        public player(String username, int highscore){
            this.username = username;
            this.highscore = highscore;
        }

        public String getUsername () {
            return this.username;
        }

        public int getHighscore () {
            return this.highscore;
        }

        public void setUsername (String userName) {
            this.username = userName;
        }

        public void setHighscore (int highScore) {
            this.highscore = highScore;
        }
    }

    public player player1 = new player("",0);
    public player player2 = new player("",0);
    public player player3 = new player("",0);
    public player player4 = new player("",0);
    public player player5 = new player("",0);

    public void setupThemeButton(){
        startPanel.add(themeButton);
        themeButton.setContentAreaFilled(false);
        themeButton.setBounds(125, 540,50, 50);
        themeButton.setBorderPainted(false);
        themeButton.setBackground(Color.LIGHT_GRAY);
        themeButton.setOpaque(true);
        themeButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/dark.png", themeButton));

    }

    public void setupReplayButton(){
        gameendPanel.add(replayButton);
        replayButton.setContentAreaFilled(false);
        replayButton.setBounds(100, 325, 100, 50);     
        replayButton.setBorderPainted(false);  
        replayButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/replay.png", replayButton));
    }
    
    public void setuplButton() {
        howtoplayPanel.add(lButton);
        lButton.setContentAreaFilled(false);
        lButton.setBounds(250, 500, 100, 100);     
        lButton.setBorderPainted(false);  
        lButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/leftarrow.png", lButton));

    }

    public void setuprButton() {
        howtoplayPanel.add(rButton);
        rButton.setContentAreaFilled(false);
        rButton.setBounds(450, 500, 100, 100);  
        rButton.setBorderPainted(false);                
        rButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/rightarrow.png", rButton));

    }

    public void setupdoneButton() {
        howtoplayPanel.add(doneButton);
        doneButton.setContentAreaFilled(false);
        doneButton.setBounds(350, 500, 100, 100); 
        doneButton.setBorderPainted(false);
        doneButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/backarrow.png", doneButton));            
    }

    public void setupPlayButton() {
        startPanel.add(playButton);
        playButton.setContentAreaFilled(false);
        playButton.setBounds(50, 200, 200, 50);       
        playButton.setBorderPainted(false);
        playButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/play.png", playButton));
    }
    
    public void setupLevelButton() {
        startPanel.add(levelButton);
        levelButton.setContentAreaFilled(false);        
        levelButton.setBounds(77, 265,150, 50); 
        levelButton.setBorderPainted(false);
        levelButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/level_1.png", levelButton));
        levelButton.setFocusPainted(false);
    }

    public void setupQuitButton() {
        pausePanel.add(quitButton);
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(50, 300,150, 75);
        quitButton.setBorderPainted(false);
        quitButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/quit.png", quitButton));
    }

    public void setupQuitButton2() {
        startPanel.add(quitButton2);
        quitButton2.setContentAreaFilled(false);
        quitButton2.setBounds(200, 540,50, 50);
        quitButton2.setBorderPainted(false);
        quitButton2.setBackground(Color.LIGHT_GRAY);
        quitButton2.setOpaque(true);
        quitButton2.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/quit2.png", quitButton2));
    }
    
    public void setuphowtoplayButton() {
        pausePanel.add(howtoplayButton);
        howtoplayButton.setContentAreaFilled(false);
        howtoplayButton.setBounds(50, 300,150, 75);
        howtoplayButton.setBorderPainted(false);
        howtoplayButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/howtoplay.png", howtoplayButton));
    }

    public void setuphowtoplayButton2() {
        startPanel.add(howtoplayButton2);
        howtoplayButton2.setContentAreaFilled(false);
        howtoplayButton2.setBounds(50, 540,50, 50);
        howtoplayButton2.setBorderPainted(false);
        howtoplayButton2.setBackground(Color.LIGHT_GRAY);
        howtoplayButton2.setOpaque(true);
        howtoplayButton2.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/howtoplay2.png", howtoplayButton2));
    }


    public void setupResumeButton() {
        pausePanel.add(resumeButton);
        resumeButton.setContentAreaFilled(false);
        resumeButton.setBounds(50, 300,150, 75);
        resumeButton.setBorderPainted(false);
        resumeButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/resume.png", resumeButton));
    }

    public void setupPauseButton() {
        frame.add(pauseButton);  
        pauseButton.setContentAreaFilled(false);                  
        pauseButton.setBounds(765, 500,70, 70); 
        pauseButton.setBorderPainted(false);
        pauseButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/pause.png", pauseButton));              
    }

    public void setupMuteButton() {
        frame.add(muteButton);
        muteButton.setContentAreaFilled(false);
        muteButton.setBounds(665, 500,70, 70);
        muteButton.setBorderPainted(false);
        muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/unmute.png", muteButton));
        
    }

    public void setupNewHighScorePanel(){
        frame.add(newhighscorPanel);
        newhighscorPanel.setLayout(null);
        newhighscorPanel.setSize(400, 300);
        newhighscorPanel.setLocation(250,150);
        newhighscorPanel.setBackground(Color.darkGray);

        newhighscorLabel.setFont(font().deriveFont(40f));
        newhighscorLabel.setBounds(50, 20, 300, 75);
        newhighscorLabel.setForeground(Color.WHITE);
        newhighscorPanel.add(newhighscorLabel);

        newscoreLabel.setFont(font().deriveFont(40f));
        newscoreLabel.setForeground(Color.WHITE);
        newscoreLabel.setBounds(50,80,300,50);
        newhighscorPanel.add(newscoreLabel);

        enterusernamLabel.setFont(font().deriveFont(20f));
        enterusernamLabel.setForeground(Color.WHITE);
        enterusernamLabel.setBounds(100,130,200,50);
        newhighscorPanel.add(enterusernamLabel);

        usernamTextField.setBounds(100,180,200,50);
        usernamTextField.setFont(font().deriveFont(25f));
        usernamTextField.setForeground(Color.black);
        newhighscorPanel.add(usernamTextField);

        okButton.setBounds(175,240,50,50);
        okButton.setFont(font().deriveFont(20f));
        okButton.setForeground(Color.white);
        newhighscorPanel.add(okButton);
    }
    
    public void setupGameEndPanel() {
        frame.add(gameendPanel);
        gameendPanel.setLayout(null);
        gameendPanel.setSize(300, 400);
        gameendPanel.setLocation(300,150);
        gameendPanel.setBackground(Color.DARK_GRAY);
        gameoverLabel.setFont(font().deriveFont(40f));
        gameoverLabel.setBounds(50, 20, 200, 50);
        gameoverLabel.setForeground(Color.white);
        gameendPanel.add(gameoverLabel);

        setupHighScorePanel2();
        setupReplayButton();

    }

    public void setuphowtoplayPanel() {
        frame.add(howtoplayPanel);
        howtoplayPanel.setSize(800, 600);
        howtoplayPanel.setLocation(50, 40);
        howtoplayPanel.setBackground(new Color(38,38,38,255));
        howtoplayPanel.repaint();
        howtoplayPanel.setLayout(null);
        howtoLabel.setBounds(0,0,800,500);
        howtoLabel.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/control.png", howtoLabel));
        howtoplayPanel.add(howtoLabel);

        setuplButton();           
        setupdoneButton();
        setuprButton();
        
    }
    public Color tintColor = new Color(0,0,0,200);
    
    public void setupTintPanel() {
        frame.add(tintPanel);
        tintPanel.setSize(900, 700);
        tintPanel.setBackground(tintColor);
    }

    public void setupHighScorePanel2() { 
        gameendPanel.add(highscorePanel2);
        highscorePanel2.setSize(300, 250);
        highscorePanel2.setLocation(0, 60);
        highscorePanel2.setBackground(Color.darkGray);
        highscorePanel2.setLayout(null);
        highScoreLabel2.setFont(font());
        highScoreLabel2.setBounds(0, 10, 300, 50);  
        highScoreLabel2.setForeground(Color.white);      
        highscorePanel2.add(highScoreLabel2);

        highScore1Label2.setText(player1.getUsername()+" "+player1.getHighscore());
        highScore1Label2.setFont(font().deriveFont(25f));
        highScore1Label2.setBounds(0,60,300,50);
        highScore1Label2.setForeground(Color.white);
        highscorePanel2.add(highScore1Label2);

        highScore2Label2.setText(player2.getUsername()+" "+player2.getHighscore());
        highScore2Label2.setFont(font().deriveFont(25f));
        highScore2Label2.setBounds(0,90,300,50);
        highScore2Label2.setForeground(Color.white);
        highscorePanel2.add(highScore2Label2);

        highScore3Label2.setText(player3.getUsername()+" "+player3.getHighscore());
        highScore3Label2.setFont(font().deriveFont(25f));
        highScore3Label2.setBounds(0,120,300,50);
        highScore3Label2.setForeground(Color.white);
        highscorePanel2.add(highScore3Label2);

        highScore4Label2.setText(player4.getUsername()+" "+player4.getHighscore());
        highScore4Label2.setFont(font().deriveFont(25f));
        highScore4Label2.setBounds(0,150,300,50);
        highScore4Label2.setForeground(Color.white);
        highscorePanel2.add(highScore4Label2);

        highScore5Label2.setText(player5.getUsername()+" "+player5.getHighscore());
        highScore5Label2.setFont(font().deriveFont(25f));
        highScore5Label2.setBounds(0,180,300,50);
        highScore5Label2.setForeground(Color.white);
        highscorePanel2.add(highScore5Label2);

    }

    public void setupHighScorePanel() { 
        startPanel.add(highscorePanel);
        highscorePanel.setSize(250, 200);
        highscorePanel.setLocation(25, 330);
        highscorePanel.setBackground(Color.DARK_GRAY);
        highscorePanel.setLayout(null);
        highScoreLabel.setFont(font());
        highScoreLabel.setBounds(30, 5, 200, 50);  
        highScoreLabel.setForeground(Color.white);      
        highscorePanel.add(highScoreLabel);

        highScore1Label.setText(player1.getUsername()+" "+player1.getHighscore());
        highScore1Label.setFont(font().deriveFont(25f));
        highScore1Label.setBounds(30,40,200,50);
        highScore1Label.setForeground(Color.white);
        highscorePanel.add(highScore1Label);

        highScore2Label.setText(player2.getUsername()+" "+player2.getHighscore());
        highScore2Label.setFont(font().deriveFont(25f));
        highScore2Label.setBounds(30,70,200,50);
        highScore2Label.setForeground(Color.white);
        highscorePanel.add(highScore2Label);

        highScore3Label.setText(player3.getUsername()+" "+player3.getHighscore());
        highScore3Label.setFont(font().deriveFont(25f));
        highScore3Label.setBounds(30,100,200,50);
        highScore3Label.setForeground(Color.white);
        highscorePanel.add(highScore3Label);

        highScore4Label.setText(player4.getUsername()+" "+player4.getHighscore());
        highScore4Label.setFont(font().deriveFont(25f));
        highScore4Label.setBounds(30,130,200,50);
        highScore4Label.setForeground(Color.white);
        highscorePanel.add(highScore4Label);

        highScore5Label.setText(player5.getUsername()+" "+player5.getHighscore());
        highScore5Label.setFont(font().deriveFont(25f));
        highScore5Label.setBounds(30,160,200,50);
        highScore5Label.setForeground(Color.white);
        highscorePanel.add(highScore5Label);

    }

    public void setupPausePanel() {
        frame.add(pausePanel);
        pausePanel.setSize(250, 420);
        pausePanel.setLocation(325, 140);
        pausePanel.setBackground(Color.DARK_GRAY);
        pausePanel.setLayout(null); 
        gamePausedLabel.setFont(font());
        gamePausedLabel.setBounds(30, 25, 200, 50);  
        gamePausedLabel.setForeground(Color.white);      
        pausePanel.add(gamePausedLabel); 

        setupResumeButton();
        resumeButton.setBounds(53, 80,150, 75);
        setuphowtoplayButton();
        howtoplayButton.setBounds(50, 180,150, 75);
        setupQuitButton();
        quitButton.setBounds(55, 280,150, 75);
    }

    public void setupStartPanel() {
        Color startmenucolor = new Color(0,0,0,200);
        frame.add(startPanel);
        startPanel.setSize(300, 600);
        startPanel.setLocation(300, 50);
        startPanel.setBackground(startmenucolor);             
        startPanel.setLayout(null);    

        setupPlayButton();  
        setupLevelButton();
        setupHighScorePanel();

        setupQuitButton2();
        setupThemeButton();
        setuphowtoplayButton2();

        JLabel lblNewLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/logo.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(350,175, Image.SCALE_DEFAULT);
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        lblNewLabel.setLocation(-24, 10);
        lblNewLabel.setSize(350, 175);
        startPanel.add(lblNewLabel);
    }
    

    public void setupGamePanel() {
        frame.add(gamePanel); 
        gamePanel.setSize(300,600);
        gamePanel.setLocation(300,50); 
        }

    public void setupNextPanel() {

        frame.add(nextPanel);
        nextPanel.setSize(200, 200);
        nextPanel.setLocation(650, 100);
        nextPanel.setBackground(trans);
        nextPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/backgroundnext.jpg");
        Image scaledImage = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        nextPanelLabel.setIcon(new ImageIcon(scaledImage));
        nextPanelLabel.setLayout(null);
        nextPanelLabel.setBounds(0,0,200,200);
        nextPanel.add(nextPanelLabel);

        nextLabel.setFont(font());
        nextLabel.setBounds(30, 10, 140, 50);  
        nextLabel.setForeground(Color.white);  
        nextPanelLabel.add(nextLabel);
        
    }

    public void setupHoldPanel() {

        frame.add(holdPanel);
        holdPanel.setSize(200, 200);
        holdPanel.setLocation(50,100 );
        holdPanel.setBackground(trans);
        holdPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/backgroundhold.jpg"); 
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        holdPanelLabel.setIcon(new ImageIcon(scaledImage));
        holdPanelLabel.setBounds(0,0,200,200);
        holdPanel.add(holdPanelLabel);
        
        holdLabel.setFont(font());
        holdLabel.setBounds(30, 10, 140, 50);  
        holdLabel.setForeground(Color.white);      
        holdPanelLabel.add(holdLabel);
    }

    

    public void setupScorePanel() {

        frame.add(scorePanel);
        scorePanel.setSize(200, 200);
        scorePanel.setLocation(50, 400);
        scorePanel.setBackground(trans);

        JLabel lblNewLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/backgroundscore.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        scorePanel.add(lblNewLabel);

        game.scoreLabel.setFont(font().deriveFont(35f));
        game.scoreLabel.setBounds(10, 10, 180, 50);  
        game.scoreLabel.setForeground(Color.white);    
        lblNewLabel.add(game.scoreLabel);
        game.scoreLabel.setText("SCORE: 0");
        
        game.levelLabel.setFont(font().deriveFont(35f));
        game.levelLabel.setBounds(10, 70, 180, 50);  
        game.levelLabel.setForeground(Color.white);     
        lblNewLabel.add(game.levelLabel);
        game.levelLabel.setText("LEVEL: 1"  );

        game.linesLabel.setFont(font().deriveFont(35f));
        game.linesLabel.setBounds(10, 130, 180, 50);  
        game.linesLabel.setForeground(Color.white);    
        lblNewLabel.add(game.linesLabel);
        game.linesLabel.setText("LINES: 0");

    }

    public void setupOffsetPanel() {

        frame.add(offsetPanel);
        offsetPanel.setSize(0,0);
        offsetPanel.setLocation(0, 0);
        offsetPanel.setBackground(trans);

        JLabel lblNewLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/backgroundframe.jpg");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(900,700, Image.SCALE_DEFAULT);
        lblNewLabel.setIcon(new ImageIcon(scaledImage));
        offsetPanel.add(lblNewLabel);

    }
        
    private String username = "";
    private Timer timer; 

    void setupGui() {
        frame.setSize(900, 700);
        frame.setTitle("Tetris");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - frame.getWidth()) / 2;
        int centerY = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);

        ImageIcon imageIcon = new ImageIcon("C:/Programming Course/CBL-Assignment/images/favicon.png");
        frame.setIconImage(imageIcon.getImage());

        frame.setFocusable(true);
        frame.requestFocus();

        setupLevelButton();
        setupPauseButton();
        setupMuteButton();

        
        setupNewHighScorePanel();
        setupGameEndPanel();
        setuphowtoplayPanel();
        setupPausePanel();
        setupTintPanel();
        setupStartPanel();
        setupGamePanel();
        setupNextPanel();
        setupHoldPanel();
        setupScorePanel();
        
        setupOffsetPanel();

        newhighscorPanel.setVisible(false);
        gameendPanel.setVisible(false);
        gamePanel.setVisible(false);
        pauseButton.setVisible(false);
        muteButton.setVisible(false);
        pausePanel.setVisible(false);
        tintPanel.setVisible(false);
        howtoplayPanel.setVisible(false);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernamTextField.getText();
                newhighscorPanel.setVisible(false);
                gameendPanel.setVisible(true);
                timer.stop();
                if (game.score>player1.getHighscore()){
                            System.out.println("player1 entered");
                            player1.setHighscore(game.score);
                            player1.setUsername(username);
                            System.out.println("this is the username: "+username+game.score);
                        }else if (game.score>player2.getHighscore()){
                            System.out.println("player2 entered");
                            player2.setHighscore(game.score);
                            player2.setUsername(username);
                        }else if (game.score>player3.getHighscore()){
                            System.out.println("player3 entered");
                            player3.setHighscore(game.score);
                            player3.setUsername(username);
                        }else if (game.score>player4.getHighscore()){
                            System.out.println("player4 entered");
                            player4.setHighscore(game.score);
                            player4.setUsername(username);
                        }else if (game.score>player5.getHighscore()){
                            System.out.println("player5 entered");
                            player5.setHighscore(game.score);
                            player5.setUsername(username);
                        }
                        highScore1Label2.setText(player1.getUsername()+" "+player1.getHighscore());
                        highScore1Label2.setText(player2.getUsername()+" "+player2.getHighscore());
                        highScore1Label2.setText(player3.getUsername()+" "+player3.getHighscore());
                        highScore1Label2.setText(player4.getUsername()+" "+player4.getHighscore());
                        highScore1Label2.setText(player5.getUsername()+" "+player5.getHighscore());
                        highscorePanel2.repaint();
                        highScore1Label.setText(player1.getUsername()+" "+player1.getHighscore());
                        highScore1Label.setText(player2.getUsername()+" "+player2.getHighscore());
                        highScore1Label.setText(player3.getUsername()+" "+player3.getHighscore());
                        highScore1Label.setText(player4.getUsername()+" "+player4.getHighscore());
                        highScore1Label.setText(player5.getUsername()+" "+player5.getHighscore());
                        highscorePanel.repaint();
                        System.out.println(player1.getUsername()+player1.getHighscore());
                    }
        });

        Action customAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.gameEnded()) { 
                    playButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "none");
                    frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "none");
                    frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "none"); 
                    gamePanel.setVisible(false);
                    newscoreLabel.setText("" + game.score);
                    timer.stop();    
                    tintPanel.setVisible(true);
                    clip.stop();
                    muteButton.setVisible(false);
                    pauseButton.setVisible(false);
                    if (game.score>player5.getHighscore() || (username != null && !username.isEmpty())){
                        newhighscorPanel.setVisible(true);
                        newscoreLabel.setText("" + game.score);
                    }else{
                        gameendPanel.setVisible(true);
                    }                  
                }
                
            }
        };
        
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "customAction");

        ActionMap actionMap = frame.getRootPane().getActionMap();
        actionMap.put("customAction", customAction);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot r = new Robot();
                    r.keyPress(KeyEvent.VK_F6);
                    r.keyRelease(KeyEvent.VK_F6);
                } catch (AWTException exception) {
                    exception.printStackTrace();
                }
            }
        });

        timer.start();

        Action playAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playButton.doClick();            
            }
        };

        playButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "play");
        playButton.getActionMap().put("play", playAction);

        boolean[] mute = { false };
        replayButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                tintPanel.setVisible(false);
                gameendPanel.setVisible(false);
                playButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "play");
                frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "mute");
                frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
                game.scoreLabel.setText("SCORE: " + 0);
                game.linesLabel.setText("LINES: " + 0);
                game.score = 0;
                game.lines = 0;

                gamePanel.setVisible(true);
                gamePanel.add(game, BorderLayout.CENTER);
                gamePanel.revalidate();
                gamePanel.repaint();
                gamePanel.addKeyListener(game);
                game.startGame(); 
                game.requestFocus();
                muteButton.setVisible(true);
                pauseButton.setVisible(true);
                if (mute[0]==false) {
                    clip.start();              
                }
                timer.start();
                game.holdLabel.setBounds(70,60,60,120);
                holdPanelLabel.add(game.holdLabel);
                holdPanel.repaint();
                game.nextLabel.setBounds(30,60,60,120);
                nextPanelLabel.add(game.nextLabel);
                game.lastLabel.setBounds(110,60,60,120);
                nextPanelLabel.add(game.lastLabel);
                nextPanelLabel.repaint();
                usernamTextField.setText("");
                newhighscorPanel.repaint();

            }
        });
        
        playButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                startPanel.setVisible(false);
                pauseButton.setVisible(true);
                muteButton.setVisible(true);
                playSound("C:/Programming Course/CBL-Assignment/audio/Original_Tetris_theme_Tetris_Soundtrack.wav"); 
                muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/unmute.png", muteButton));
                

                gamePanel.setVisible(true);
                gamePanel.add(game, BorderLayout.CENTER);
                gamePanel.revalidate();
                gamePanel.repaint();
                gamePanel.addKeyListener(game);
                game.startGame(); 
                game.requestFocus();

                game.holdLabel.setBounds(40,70,120,120);
                holdPanelLabel.add(game.holdLabel);
                holdPanel.repaint();
                game.nextLabel.setBounds(30,60,60,120);
                nextPanelLabel.add(game.nextLabel);
                game.lastLabel.setBounds(110,60,60,120);
                nextPanelLabel.add(game.lastLabel);
                nextPanelLabel.repaint();
            }
        });
          
        Action quitAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                clip.stop();
            }
        };
        
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "quit");
        frame.getRootPane().getActionMap().put("quit", quitAction);

        quitButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();               
            }
        });

        quitButton2.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();               
            }
        });

        themeButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isDarkTheme){
                    game.isDarkTheme = true;
                    themeButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/dark.png", themeButton));
                    themeButton.repaint();
                }else{
                    game.isDarkTheme = false;
                    themeButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/light.png", themeButton));
                    themeButton.repaint();
                }               
            }
        });

        howtoplayButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howtoplayPanel.setVisible(true); 
                pausePanel.setVisible(false); 
                pauseButton.setVisible(false);  
                muteButton.setVisible(false);      
            }
        });

        howtoplayButton2.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howtoplayPanel.setVisible(true); 
                tintPanel.setVisible(true);
                startPanel.setVisible(false);
            }
        });

        doneButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isPaused) {
                    howtoplayPanel.setVisible(false); 
                    pausePanel.setVisible(true);  
                    pauseButton.setVisible(true);    
                    muteButton.setVisible(true); 
                }else{
                    howtoplayPanel.setVisible(false); 
                    tintPanel.setVisible(false);
                    startPanel.setVisible(true);
                }
            }
        });

        lButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                howtoLabel.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/control.png", howtoLabel));
            }
        });

        rButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howtoLabel.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/gameplayinfo.png", howtoLabel));       
            }
        });

        
        Action muteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mute[0]) {                   
                    clip.start();
                    mute[0] = false;
                    muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/unmute.png", muteButton));
                    game.requestFocus();
                } else {         
                    clip.stop();
                    mute[0] = true;   
                    muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/mute.png", muteButton));       
                    game.requestFocus();        
                } 
            }
        };

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0), "mute");
        frame.getRootPane().getActionMap().put("mute", muteAction);

        muteButton.addActionListener (new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent e) {               
                if (mute[0]) {                   
                    clip.start();
                    mute[0] = false;
                    muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/unmute.png", muteButton));
                    gamePanel.requestFocus();
                } else {         
                    clip.stop();
                    mute[0] = true;   
                    muteButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/mute.png", muteButton));           
                    gamePanel.requestFocus();  
                }                
            }
        });

        Action pauseAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isPaused) {
                    tintPanel.setVisible(true);
                    pausePanel.setVisible(true);                
                    clip.stop(); 
                    game.isPaused = true;
                    game.timer.stop();
                } else {
                    pausePanel.setVisible(false); 
                    tintPanel.setVisible(false); 
                    gamePanel.addKeyListener(game);
                    if (mute[0]==false) {
                        clip.start();              
                    }     
                    game.isPaused = false;
                    game.timer.start(); 
                }
                repaint();
            }
        };

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
        frame.getRootPane().getActionMap().put("pause", pauseAction);

        pauseButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tintPanel.setVisible(true);
                pausePanel.setVisible(true);                
                clip.stop(); 
                game.isPaused = true;
                game.timer.stop();
                game.requestFocus();
            }
        });

        resumeButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausePanel.setVisible(false); 
                tintPanel.setVisible(false); 
                gamePanel.addKeyListener(game);
                if (mute[0]==false) {
                    clip.start();              
                }     
                game.isPaused = false;
                game.timer.start(); 
                game.requestFocus();
            }
        });

        final int[] x = { 1 };
        game.startLevel = x[0];

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                                
                if (x[0] == 1) {
                    x[0] = x[0] + 4;
                    game.startLevel = x[0];
                    game.levelLabel.setText("LEVEL: " + x[0] );
                } else if (x[0] == 25) {
                    x[0] = 1;
                    game.startLevel = x[0];
                    game.levelLabel.setText("LEVEL: " + x[0] );
                } else {
                    x[0] = x[0] + 5;
                    game.startLevel = x[0];
                    game.levelLabel.setText("LEVEL: " + x[0] );
                }      
                levelButton.setIcon(setupIcon("C:/Programming Course/CBL-Assignment/images/level_" + x[0] + ".png", levelButton));       
                }
                
        });
        
        frame.getContentPane().setBackground( Color.WHITE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new TetrisGUI().setupGui();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}