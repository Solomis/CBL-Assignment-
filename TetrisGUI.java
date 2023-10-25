import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javax.swing.*;
import javax.sound.sampled.*;


public class TetrisGUI extends JFrame implements ActionListener {
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
    
    
    JLabel holdLabel;
    JLabel titleLabel;
    JLabel playLabel;
    JLabel nextLabel;
    JLabel scoreLabel;
    JLabel levelLabel;
    JLabel linesLabel;
    JLabel gamePausedLabel;
    JLabel highScoreLabel;
    JLabel highScore1Label;
    JLabel highScore2Label;
    JLabel highScore3Label;
    JLabel highScore4Label;
    JLabel highScore5Label;

   
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

    
    public TetrisGUI() {
        
        frame = new JFrame("Tetris");
        gamePanel = new JPanel();
        nextPanel = new JPanel();
        holdPanel = new JPanel();
        scorePanel = new JPanel();
        offsetPanel = new JPanel();
        startPanel = new JPanel();
        pausePanel = new JPanel();
        tintPanel = new JPanel();
        howtoplayPanel = new JPanel();
        highscorePanel = new JPanel();

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

        gamePausedLabel = new JLabel("GAME PAUSED",SwingConstants.CENTER);
        highScoreLabel = new JLabel("HIGH SCORES",SwingConstants.CENTER);
        holdLabel = new JLabel("HOLD",SwingConstants.CENTER);
        scoreLabel = new JLabel("SCORE",SwingConstants.CENTER);
        nextLabel = new JLabel("NEXT",SwingConstants.CENTER);
        linesLabel = new JLabel("LINES",SwingConstants.CENTER);
        levelLabel = new JLabel("LEVEL",SwingConstants.CENTER);
        highScore1Label = new JLabel("",SwingConstants.CENTER);
        highScore2Label = new JLabel("",SwingConstants.CENTER);
        highScore3Label = new JLabel("",SwingConstants.CENTER);
        highScore4Label = new JLabel("",SwingConstants.CENTER);
        highScore5Label = new JLabel("",SwingConstants.CENTER);

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
            //Path currentDirectoryPath = FileSystems.getDefault().getPath("");
	        //String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
            //String path = currentDirectoryName + iconPath;
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
        
        public void setuplButton() {
            howtoplayPanel.add(lButton);
            lButton.setContentAreaFilled(false);
            lButton.setBounds(250, 700, 50, 50);     
            lButton.setBorderPainted(false);  
            lButton.setIcon(setupIcon("C:\\\\Programming Course\\\\game\\\\Images\\\\leftarrow.png", lButton));

        }

        public void setuprButton() {
            howtoplayPanel.add(rButton);
            rButton.setContentAreaFilled(false);
            rButton.setBounds(550, 700, 50, 50);  
            rButton.setBorderPainted(false);     
            rButton.setIcon(setupIcon("C:\\\\Programming Course\\\\game\\\\Images\\\\rightarrow.png", rButton));

        }

        public void setupdoneButton() {
            howtoplayPanel.add(doneButton);
            doneButton.setContentAreaFilled(false);
            doneButton.setBounds(350, 700, 100, 50); 
            doneButton.setBorderPainted(false);
            doneButton.setIcon(setupIcon("C:\\\\Programming Course\\\\game\\\\Images\\\\backarrow.png", doneButton));           
        }

        public void setupPlayButton() {
            startPanel.add(playButton);
            playButton.setContentAreaFilled(false);
            playButton.setBounds(50, 200, 200, 50);       
            playButton.setBorderPainted(false);
            playButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\play.png", playButton));
        }
        
        public void setupLevelButton() {
            startPanel.add(levelButton);
            levelButton.setContentAreaFilled(false);        
            levelButton.setBounds(77, 265,150, 50); 
            levelButton.setBorderPainted(false);
            levelButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\level 1.png", levelButton));
            levelButton.setFocusPainted(false);
        }

        public void setupQuitButton() {
            pausePanel.add(quitButton);
            quitButton.setContentAreaFilled(false);
            quitButton.setBounds(50, 300,100, 50);
            quitButton.setBorderPainted(false);
            quitButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\quit.png", quitButton));
        }

        public void setupQuitButton2() {

            startPanel.add(quitButton2);
            quitButton2.setContentAreaFilled(false);
            quitButton2.setBounds(195, 540,50, 50);
            quitButton2.setBorderPainted(false);
            quitButton2.setOpaque(false);
            quitButton2.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\quit2.png", quitButton2));
        }
        
        public void setuphowtoplayButton() {
            pausePanel.add(howtoplayButton);
            howtoplayButton.setContentAreaFilled(false);
            howtoplayButton.setBounds(50, 300,100, 50);
            howtoplayButton.setBorderPainted(false);
            howtoplayButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\howtoplay.png", howtoplayButton));
        }

        public void setuphowtoplayButton2() {
            startPanel.add(howtoplayButton2);
            howtoplayButton2.setContentAreaFilled(false);
            howtoplayButton2.setBounds(75, 540,50, 50);
            howtoplayButton2.setBorderPainted(false);
            howtoplayButton2.setOpaque(false);
            howtoplayButton2.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\howtoplay2.png", howtoplayButton2));
        }


        public void setupResumeButton() {
            pausePanel.add(resumeButton);
            resumeButton.setContentAreaFilled(false);
            resumeButton.setBounds(50, 300,100, 50);
            resumeButton.setBorderPainted(false);
            resumeButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\resume.png", resumeButton));
        }

        public void setupPauseButton() {
            frame.add(pauseButton);  
            pauseButton.setContentAreaFilled(false);                  
            pauseButton.setBounds(765, 500,70, 70); 
            pauseButton.setBorderPainted(false);
            pauseButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\pause.png", pauseButton));            
        }

        public void setupMuteButton() {
            frame.add(muteButton);
            muteButton.setContentAreaFilled(false);
            muteButton.setBounds(665, 500,70, 70);
            muteButton.setBorderPainted(false);
            muteButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\unmute.jpg", muteButton));
           
        }
       
        public void setuphowtoplayPanel() {

            frame.add(howtoplayPanel);
            howtoplayPanel.setSize(800, 600);
            howtoplayPanel.setLocation(43, 40);
            //setupBackground("C:\\Programming Course\\game\\Images\\control.png", howtoplayPanel);
            howtoplayPanel.setLayout(null);

            setuplButton();       
            lButton.setBounds(200,700,50,50);     
            setupdoneButton();
            doneButton.setBounds(350, 700, 100, 50);
            setuprButton();
            rButton.setBounds(500,700,50,50);
           
        }
        Color tintColor = new Color(0,0,0,200);
        
        public void setupTintPanel() {
            frame.add(tintPanel);
            tintPanel.setSize(900, 700);
            tintPanel.setBackground(tintColor);
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

            player player1 = new player("Player 1:",0);
            player player2 = new player("Player 2:",0);
            player player3 = new player("Player 3:",0);
            player player4 = new player("Player 4:",0);
            player player5 = new player("Player 5:",0);


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

        public Font font() {
            try {
                File fontFile = new File("C:\\Programming Course\\game\\SummerPixel22Regular-jE0W7.ttf");
                Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
                Font sizedFont = font.deriveFont(39f);
                return sizedFont;
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                return null;
            }
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
            resumeButton.setBounds(80, 80,100, 50);
            setuphowtoplayButton();
            howtoplayButton.setBounds(80, 180,100, 50);
            setupQuitButton();
            quitButton.setBounds(80, 280,100, 50);

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
            setuphowtoplayButton2();

            JLabel lblNewLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\logo.png");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(350,175, Image.SCALE_DEFAULT);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
            lblNewLabel.setLocation(-24, 10);
            lblNewLabel.setSize(350, 175);
            startPanel.add(lblNewLabel);
        }
        

        public void setupGamePanel() {
            frame.add(game); 
            game.setSize(300,600);
            game.setVisible(false);
            game.setLocation(300,50); 
         }
        

        public void setupNextPanel() {

            frame.add(nextPanel);
            nextPanel.setSize(200, 300);
            nextPanel.setLocation(650, 100);
            nextPanel.setBackground(trans);

            JLabel lblNewLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\backgroundnext.jpg");
            Image scaledImage = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
            nextPanel.add(lblNewLabel);

            nextLabel.setFont(font());
            nextLabel.setBounds(30, 10, 140, 50);  
            nextLabel.setForeground(Color.white);  
            nextLabel.setBackground(tintColor);
            nextLabel.setOpaque(true);
            lblNewLabel.add(nextLabel);
         
        }

        public void setupHoldPanel() {

            frame.add(holdPanel);
            holdPanel.setSize(200, 200);
            holdPanel.setLocation(50,100 );
            holdPanel.setBackground(trans);

            JLabel lblNewLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\backgroundhold.jpg"); 
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
            holdPanel.add(lblNewLabel);

            holdLabel.setFont(font());
            holdLabel.setBounds(30, 10, 140, 50);  
            holdLabel.setForeground(Color.white);
            holdLabel.setBackground(tintColor);
            holdLabel.setOpaque(true);      
            lblNewLabel.add(holdLabel);

        }

        public void setupScorePanel() {

            frame.add(scorePanel);
            scorePanel.setSize(200, 200);
            scorePanel.setLocation(50, 400);
            scorePanel.setBackground(trans);

            JLabel lblNewLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\backgroundscore.jpg");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
            scorePanel.add(lblNewLabel);

            int score = 0;
            scoreLabel.setFont(font().deriveFont(35f));
            scoreLabel.setBounds(10, 10, 180, 50);  
            scoreLabel.setForeground(Color.white); 
            scoreLabel.setBackground(tintColor);
            scoreLabel.setOpaque(true);     
            lblNewLabel.add(scoreLabel);
            scoreLabel.setText("SCORE: "+ score);

            int level = 0;
            levelLabel.setFont(font().deriveFont(35f));
            levelLabel.setBounds(10, 70, 180, 50);  
            levelLabel.setForeground(Color.white); 
            levelLabel.setBackground(tintColor);
            levelLabel.setOpaque(true);     
            lblNewLabel.add(levelLabel);
            levelLabel.setText("LEVEL: "+ level);

            int lines = 0;
            linesLabel.setFont(font().deriveFont(35f));
            linesLabel.setBounds(10, 130, 180, 50);  
            linesLabel.setForeground(Color.white); 
            linesLabel.setBackground(tintColor);
            linesLabel.setOpaque(true);     
            lblNewLabel.add(linesLabel);
            linesLabel.setText("LINES: "+ lines);

        }

        public void setupOffsetPanel() {

            frame.add(offsetPanel);
            offsetPanel.setSize(0,0);
            offsetPanel.setLocation(0, 0);
            offsetPanel.setBackground(trans);

            JLabel lblNewLabel = new JLabel();
            ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\backroundframe.jpg");
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(900,700, Image.SCALE_DEFAULT);
            lblNewLabel.setIcon(new ImageIcon(scaledImage));
            offsetPanel.add(lblNewLabel);

        }


    void setupGui() {
        frame.setSize(900, 700);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (screenSize.width - frame.getWidth()) / 2;
        int centerY = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(centerX, centerY);

        ImageIcon imageIcon = new ImageIcon("C:\\Programming Course\\game\\Images\\favicon.png");
        frame.setIconImage(imageIcon.getImage());

        setupLevelButton();
        setupPauseButton();
        setupMuteButton();

        setuphowtoplayPanel();
        setupPausePanel();
        setupTintPanel();
        setupStartPanel();
        setupGamePanel();
        setupNextPanel();
        setupHoldPanel();
        setupScorePanel();
        
        setupOffsetPanel();
        
        
        
        pauseButton.setVisible(false);
        muteButton.setVisible(false);
        pausePanel.setVisible(false);
        tintPanel.setVisible(false);
        howtoplayPanel.setVisible(false);
       
        
        playButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("gay");
                game.setVisible(true);       
                game.startGame(); 
                frame.revalidate();
                game.requestFocus();
                startPanel.setVisible(false);
                pauseButton.setVisible(true);
                muteButton.setVisible(true);
                playSound("C:\\Programming Course\\game\\Original Tetris theme Tetris Soundtrack.wav");
                muteButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\unmute.png", muteButton));
            }
        });

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
                startPanel.setVisible(false);      
            }
        });

        doneButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                howtoplayPanel.setVisible(false); 
                pausePanel.setVisible(true);  
                pauseButton.setVisible(true);    
                muteButton.setVisible(true);   
            }
        });

        lButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupBackground("C:\\Programming Course\\game\\Images\\control.png",howtoplayPanel); 
            }
        });

        rButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupBackground("C:\\Programming Course\\game\\Images\\gameplayinfo.png",howtoplayPanel);        
            }
        });

        boolean[] mute = { false };
        muteButton.addActionListener (new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent e) {               
                if (mute[0]) {                   
                    clip.start();
                    mute[0] = false;
                    muteButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\unmute.png", muteButton));
                } else {         
                    clip.stop();
                    mute[0] = true;   
                    muteButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\mute.png", muteButton));                 
                }                
            }
        });

        pauseButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tintPanel.setVisible(true);
                pausePanel.setVisible(true);                
                clip.stop();             
            }
        });

        resumeButton.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausePanel.setVisible(false); 
                tintPanel.setVisible(false); 
                if (mute[0]==false) {
                    clip.start();              
                }             
            }
        });

        final int[] x = { 1 };

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                                
                if (x[0] == 1) {
                    x[0] = x[0] + 4;
                } else if (x[0] == 25) {
                    x[0] = 1;
                } else {
                    x[0] = x[0] + 5;
                }      
                levelButton.setIcon(setupIcon("C:\\Programming Course\\game\\Images\\level " + x[0] + ".png", levelButton));         
                }
                
        });
        
        frame.getContentPane().setBackground( Color.WHITE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TetrisGUI().setupGui();
    }
    
}