import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

/** D.
 * 
 */
public class TetrisGame extends JPanel implements ActionListener, KeyListener {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int BLOCK_SIZE = 30;
    public int delay = 800; // Starts at 800, ends at 35.
    public int lines = 0;
    public int totalLines = 0;
    public int numFullLines = 0;
    public int startLevel = 1; // initially 1
    public int level;
    public int score = 0;
    public int easter = 0;
    public boolean isEaster = false;
    public boolean isDarkTheme = true;

    public int[][] board = new int[WIDTH][HEIGHT];
    public Timer timer;
    public boolean isFallingFinished = false;
    public boolean isStarted = false;
    public boolean isPaused = false;
    public int currentX = 0;
    public int currentY = 0;
    public Tetromino currentTetromino;
    public Tetromino nextTetromino;
    public Tetromino lastTetromino;
    public Tetromino holdTetromino;
    public Tetromino dummyTetromino;
    public boolean itemInHold;
    public boolean holdUsed;

    public JLabel scoreLabel;
    public JLabel levelLabel;
    public JLabel linesLabel;
    public JLabel holdLabel;
    public JLabel nextLabel;
    public JLabel lastLabel;

    /** Initializes game.
     * 
     */
    public TetrisGame() {
        initBoard();
        setFocusable(true);
        addKeyListener(this);
        scoreLabel = new JLabel();
        linesLabel = new JLabel();
        levelLabel = new JLabel();

    }

    /* Sets all grid blocks black.
     */
    public void initBoard() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                board[i][j] = 0;
            }
        }
    }

    /* Creates a new random tetromino and updates x and y values.
     */
    public void newTetromino() {
        currentTetromino = nextTetromino;
        //System.out.println("current: "
        //    + currentTetromino.getColorName(currentTetromino.getColor()));
        nextTetromino = lastTetromino;
        nextLabel.repaint();
        //System.out.println("next: " + nextTetromino.getColorName(nextTetromino.getColor()));
        lastTetromino = TetrominoFactory.getRandomTetromino();
        lastLabel.repaint();
        //System.out.println("last: " + lastTetromino.getColorName(lastTetromino.getColor()));
        currentX = WIDTH / 2 - 1;
        currentY = 0;
        holdUsed = false;
    }

    /* Given an 
     * 
     */
    public boolean isValidMove(int x, int y, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getXSize(); i++) {
            for (int j = 0; j < tetromino.getYSize(); j++) {
                if (tetromino.isBlock(i, j) && (x + i >= WIDTH 
                    || x + i < 0 || y + j >= HEIGHT || board[x + i][y + j] != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void dropTetromino() {
        while (currentY < HEIGHT - 1 && isValidMove(currentX, currentY + 1, currentTetromino)) {
            currentY++;
            score = score + 2;
        }
        pieceDropped();
    }

    public void pieceDropped() {
        for (int i = 0; i < currentTetromino.getXSize(); i++) {
            for (int j = 0; j < currentTetromino.getYSize(); j++) {
                if (currentTetromino.isBlock(i, j)) {
                    board[currentX + i][currentY + j] = currentTetromino.getColor();
                }
            }
        }
        isFallingFinished = true;
        numFullLines = 0;
        removeFullLines();
        updateLevel();
        timer.setDelay(delay);
        lines = 0;
        scoreLabel.setText("SCORE: " + score);
        linesLabel.setText("LINES: " + totalLines);
        levelLabel.setText("LEVEL: " + level);

    }

    /**D.
     * 
     */
    public void removeFullLines() {
        boolean isLineFull;
        for (int j = HEIGHT - 1; j >= 0; j--) {
            isLineFull = true;
            for (int i = 0; i < WIDTH; i++) {
                if (board[i][j] == 0) {
                    isLineFull = false;
                    break;
                }
            }
            if (isLineFull) {
                numFullLines++;
                for (int y = j; y > 0; y--) {
                    for (int x = 0; x < WIDTH; x++) {
                        board[x][y] = board[x][y - 1];
                    }
                }
                for (int x = 0; x < WIDTH; x++) {
                    board[x][0] = 0;
                }
                removeFullLines();
                if (numFullLines > 0 && numFullLines != 4) {
                    lines = lines + numFullLines;
                    totalLines += lines;
                    score = score + (100 + 200 * (numFullLines - 1)) * level;
                    numFullLines = 0;
                } else if (numFullLines == 4) {
                    lines = lines + numFullLines;
                    totalLines += lines;
                    score = score + 800 * level;
                    numFullLines = 0;
                }
                
            }
        }
    }

    public void updateLevel() {
        
        level = startLevel + (totalLines / 10);

        if (level < 10) {
            switch (level) {
                case 1:
                    delay = 800;
                    break; 
                case 2:
                    delay = 720;
                    break;
                case 3:
                    delay = 630;
                    break;
                case 4:
                    delay = 550;
                    break;
                case 5:
                    delay = 470;
                    break;
                case 6:
                    delay = 380;
                    break;
                case 7:
                    delay = 300;
                    break;
                case 8:
                    delay = 220;
                    break;
                case 9:
                    delay = 100;
                    break;
                default:
                    break;
            }
        } else if (level > 9 && level < 13) {
            delay = 80;
        } else if (level > 12 && level < 16) {
            delay = 70;
        } else if (level > 15 && level < 19) {
            delay = 50;
        } else if (level > 18 && level < 29) {
            delay = 40;
        } else {
            delay = 35;
        }
    }

    public void moveLeft() {
        if (isValidMove(currentX - 1, currentY, currentTetromino)) {
            currentX--;
        }
    }

    public void moveRight() {
        if (isValidMove(currentX + 1, currentY, currentTetromino)) {
            currentX++;
        }
    }

    public void rotate() {
        Tetromino rotatedTetromino = currentTetromino.rotate();
        if (isValidMove(currentX, currentY, rotatedTetromino)) {
            currentTetromino = rotatedTetromino;
        }
    }


    /**d. */
    public boolean gameEnded() {
        for (int i = 0; i < WIDTH; i++) {
            if (board[i][1] != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            if (!gameEnded()) {
                newTetromino();
            } else {
                timer.stop(); 
            }
            
        } else {
            moveDown();
        }
        repaint();
    }

    public void moveDown() {
        if (isValidMove(currentX, currentY + 1, currentTetromino)) {
            currentY++;
        } else {
            pieceDropped();
        }
    }

    public Color darkTheme(boolean b) {
        return b ? Color.GRAY : Color.WHITE;
    }

    public void drawSquare(Graphics g, int x, int y, int color) {
        Color[] colors = {darkTheme(isDarkTheme), Color.CYAN, Color.YELLOW,
            Color.MAGENTA, Color.ORANGE, Color.BLUE, Color.GREEN, Color.RED, Color.BLACK};
        Color blockColor = colors[color];

        g.setColor(blockColor);
        g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        g.setColor(blockColor.darker());
        g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }

    public void drawBoard(Graphics g) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                drawSquare(g, i, j, board[i][j]);   
            }
        }
        g.setColor(Color.RED);
        g.drawLine(0, BLOCK_SIZE, WIDTH * BLOCK_SIZE, BLOCK_SIZE);
    }

    public void drawEaster(Graphics g) {
        for (int i = 1; i < 4; i++) {
            if (board[i][2] == 0) {
                drawSquare(g, i, 2, 8);
            }
        }
        for (int i = 3; i < 7; i++) {
            if (board[2][i] == 0) {
                drawSquare(g, 2, i, 8);
            }
        }
        for (int i = 2; i < 7; i++) {
            if (board[4][i] == 0) {
                drawSquare(g, 4, i, 8);
            }
        }
        if (board[5][6] == 0) {
            drawSquare(g, 5, 6, 8);
        }
        for (int i = 2; i < 7; i++) {
            if (board[6][i] == 0) {
                drawSquare(g, 6, i, 8);
            }
        }
        for (int i = 7; i < 9; i++) {
            if (board[i][2] == 0) {
                drawSquare(g, i, 2, 8);
            }
        }
        for (int i = 4; i < 7; i = i + 2) {
            if (board[7][i] == 0) {
                drawSquare(g, 7, i, 8);
            }
        }
        if (board[8][6] == 0) {
            drawSquare(g, 8, 6, 8);
        }        
    }

    public void drawCurrentTetromino(Graphics g) {
        for (int i = 0; i < currentTetromino.getXSize(); i++) {
            for (int j = 0; j < currentTetromino.getYSize(); j++) {
                if (currentTetromino.isBlock(i, j)) {
                    drawSquare(g, currentX + i, currentY + j, currentTetromino.getColor());
                }
            }
        }
    }

    public void drawTetromino(Graphics g, Tetromino t) {
        for (int i = 0; i < t.getXSize(); i++) {
            for (int j = 0; j < t.getYSize(); j++) {
                if (t.isBlock(i, j)) {
                    drawSquare(g, i, j, t.getColor());
                }
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        if (isEaster) {
            drawEaster(g);
        }
        drawCurrentTetromino(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameEnded()) {
            int keyCode = e.getKeyCode();
            /* 
            if (keyCode == KeyEvent.VK_P) {
                isPaused = !isPaused;
                if (isPaused) {
                    timer.stop();
                } else {
                    timer.start();
                }
                repaint();
            }*/
            if (keyCode == KeyEvent.VK_T) {
                if (easter == 2) {
                    isEaster = !isEaster;
                    easter = 0;
                } else {
                    easter++;
                }            
            }
            if (!isStarted || isPaused) {
                return;
            }

            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown();
                    score++;
                    scoreLabel.setText("SCORE: " + score);
                    break;
                case KeyEvent.VK_UP:
                    rotate();
                    break;
                case KeyEvent.VK_SPACE:
                    dropTetromino();
                    break;
                case KeyEvent.VK_C:
                    if (!holdUsed) {
                        if (!itemInHold) {
                            holdTetromino = currentTetromino;
                            newTetromino();
                            itemInHold = true;
                            holdUsed = true;
                            holdLabel.repaint();
                        } else {
                            if (isValidMove(currentX, currentY, holdTetromino)) {
                                currentX = WIDTH / 2 - 1;
                                currentY = 0;
                                dummyTetromino = holdTetromino;
                                holdTetromino  = currentTetromino;
                                currentTetromino = dummyTetromino;
                                holdUsed = true;
                                holdLabel.repaint();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        
        TetrisGame tetrisGame = new TetrisGame();
        frame.add(tetrisGame);
        frame.setSize(WIDTH * BLOCK_SIZE + 14, HEIGHT * BLOCK_SIZE + 38);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(tetrisGame);
        tetrisGame.startGame();
    }

    /**d. */
    public void startGame() {
        isStarted = true;
        initBoard();
        nextTetromino = TetrominoFactory.getRandomTetromino();
        lastTetromino = TetrominoFactory.getRandomTetromino();
        holdLabel = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                if (itemInHold){
                    super.paintComponent(g);
                    drawTetromino(g, holdTetromino);
                }
            }
        };
        holdLabel.setSize(2 * BLOCK_SIZE, 4 * BLOCK_SIZE);
        nextLabel = new JLabel("NEXT") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTetromino(g, nextTetromino);
            }
        };
        nextLabel.setSize(2 * BLOCK_SIZE, 4 * BLOCK_SIZE);
        lastLabel = new JLabel("LAST") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTetromino(g, lastTetromino);
            }
        };
        lastLabel.setSize(2 * BLOCK_SIZE, 4 * BLOCK_SIZE);
        newTetromino();
        itemInHold = false;
        updateLevel();
        timer = new Timer(delay, this);
        timer.start();
    }
}

class Tetromino {
    private int[][] shape;
    private int color;

    /**d. */
    public Tetromino(int[][] shape, int color) {
        this.shape = shape;
        this.color = color;
    }

    public int getXSize() {
        return shape.length;
    }

    public int getYSize() {
        return shape[0].length;
    }

    public int getMax() {
        return Math.max(shape.length, shape[0].length);
    }

    public int getMin() {
        return Math.min(shape.length, shape[0].length);
    }

    /* returns if the square of the tetromino at position x, y is a block or not */
    public boolean isBlock(int x, int y) {
        return shape[x][y] != 0;
    }

    /**d. */
    public Tetromino rotate() {
        int[][] newShape = new int[getYSize()][getXSize()];

        if (getMax() == 2) {
            return new Tetromino(shape, color);
        }

        if (getMax() == 4) {
            if (shape.length == 4) {
                for (int i = 0; i < 4; i++) {
                    newShape[0][i] = shape[i][0];
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    newShape[i][0] = shape[0][i];
                }
            }
            return new Tetromino(newShape, color);
        }

        if (shape.length == 2) {
            newShape[0][0] = shape[0][2];
            newShape[0][1] = shape[1][2];
            newShape[1][0] = shape[0][1];
            newShape[1][1] = shape[1][1];
            newShape[2][0] = shape[0][0];
            newShape[2][1] = shape[1][0];
        } else {
            newShape[0][0] = shape[0][1];
            newShape[0][1] = shape[1][1];
            newShape[0][2] = shape[2][1];
            newShape[1][0] = shape[0][0];
            newShape[1][1] = shape[1][0];
            newShape[1][2] = shape[2][0];
        }
        return new Tetromino(newShape, color);
    }

    public int getColor() {
        return color;
    }

    /**d. */
    public String getColorName(int color) {
        String[] colorNames = {"Gray", "Cyan", 
            "Yellow", "Magenta", "Orange", "Blue", "Green", "Red"};
        return colorNames[color];
    }
}

class TetrominoFactory {
    private static final Tetromino[] TETROMINOS = {
        new Tetromino(new int[][]{{1, 1, 1, 1}}, 1), // I-shape (Cyan)
        new Tetromino(new int[][]{{1, 1}, {1, 1}}, 2), // O-shape (Yellow)
        new Tetromino(new int[][]{{1, 1, 1}, {0, 1, 0}}, 3), // T-shape (Magenta)
        new Tetromino(new int[][]{{1, 1, 1}, {0, 0, 1}}, 4), // L-shape (Orange)
        new Tetromino(new int[][]{{1, 1, 1}, {1, 0, 0}}, 5), // J-shape (Blue)
        new Tetromino(new int[][]{{1, 1, 0}, {0, 1, 1}}, 6), // S-shape (Green)
        new Tetromino(new int[][]{{0, 1, 1}, {1, 1, 0}}, 7)  // Z-shape (Red)
    };

    /**Gets a random tetromino.  */
    public static Tetromino getRandomTetromino() {
        Random random = new Random();
        int randomIndex = random.nextInt(TETROMINOS.length);
        return TETROMINOS[randomIndex];
    }
}