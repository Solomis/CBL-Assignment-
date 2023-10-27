import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**d.
 * 
 */
public class TestGUInew {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("Game Launcher");

            // Create a button to start the game
            JButton startButton = new JButton("Start Game");

            // Create an instance of your GamePanel
            TetrisGame tetrisgame = new TetrisGame();
            //TetrisGUI tetrisGUI = new TetrisGUI();

            //tetrisGUI.setupGui();


            // Add an action listener to the button
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remove the start button
                    frame.getContentPane().remove(startButton);

                    // Add your GamePanel to the frame
                    frame.add(tetrisgame, BorderLayout.CENTER);

                    // Revalidate and repaint the frame
                    frame.revalidate();
                    frame.repaint();

                    // Start your game timer or logic here
                    //frame.addKeyListener(tetrisgame);
                    tetrisgame.startGame();


                    // Set focus to the game panel to capture key input
                    tetrisgame.requestFocus();
                }
            });

            // Set layout manager and add components to the frame
            frame.setLayout(new BorderLayout());
            frame.add(startButton, BorderLayout.NORTH);

            // Set frame properties
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(314, 638);
            frame.setVisible(true);
        });
    }
}
