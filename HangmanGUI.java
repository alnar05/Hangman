import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangmanGUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final Color backgroundColor = new Color(144, 238, 144);
    private HangmanGame game;
    private JLabel hangmanImageLabel;
    private JPanel guessedWordPanel;
    private JButton[] letterButtons;
    public String theme;
    private JLabel themeLabel;
    private static ImageIcon icon = new ImageIcon("img\\icon.png");

    public HangmanGUI() {
        super("Hangman Game");
        this.theme = "random";

        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);
        setIconImage(icon.getImage());

        game = new HangmanGame("words\\" + theme + ".txt");
        createUpperSection();
        createMiddleSection();
        createLowerSection();
        updateUI();
    }

    public HangmanGUI(String filePath) {
        super("Hangman Game");
        this.theme = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf(".txt"));

        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
//        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(backgroundColor);
        setIconImage(icon.getImage());

        game = new HangmanGame(filePath);
        createUpperSection();
        createMiddleSection();
        createLowerSection();
        updateUI();
    }

    private void createUpperSection() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        hangmanImageLabel = new JLabel();
        hangmanImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.setBackground(backgroundColor);
        updateHangmanImage();
        imagePanel.add(hangmanImageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton menuButton = new JButton("Menu");
        JButton newGameButton = new JButton("New Game");

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
                setVisible(false);
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game = new HangmanGame("words\\" + theme + ".txt");
                updateUI();

                for (JButton button : letterButtons) {
                    button.setEnabled(true);
                    button.setBackground(null);
                }
            }
        });
        JPanel groupButton = new JPanel(new GridLayout(2, 1));
        groupButton.add(newGameButton);
        groupButton.add(menuButton);

        buttonPanel.setBackground(backgroundColor);
        buttonPanel.add(groupButton, BorderLayout.NORTH);
        imagePanel.add(buttonPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.NORTH);
    }

    private void createMiddleSection() {
        guessedWordPanel = new JPanel();
        guessedWordPanel.setLayout(new FlowLayout());
        guessedWordPanel.setBackground(backgroundColor);
        updateGuessPanel(true);
        add(guessedWordPanel, BorderLayout.CENTER);
    }

    private void createLowerSection() {
        JPanel keyboardPanel = new JPanel(new GridLayout(4, 7));
        letterButtons = new JButton[26];
        char[] alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            char letter = alphabet[i];
            letterButtons[i] = new JButton(String.valueOf(letter));
            letterButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    game.makeGuess(letter);
                    updateUI();

                    clickedButton.setEnabled(false);
                    clickedButton.setBackground(Color.red);
                }
            });
            keyboardPanel.add(letterButtons[i]);

        }
        add(keyboardPanel, BorderLayout.SOUTH);
    }

    private void updateUI() {
        updateHangmanImage();
        if (!game.isGameOver()) {
            updateGuessPanel(true);
        } else {
            updateGuessPanel(false);
            disableLetterButtons();
            showGameOverMessage();
        }
    }

    private void updateGuessPanel(boolean ongoingGame) {
        guessedWordPanel.removeAll();
        String guessedWord = game.getCurrentGuess();
        String secretWord = game.getSecretWord();

        for (int i = 0; i < guessedWord.length(); i++) {
            char letter = guessedWord.charAt(i);
            char secretLetter = secretWord.charAt(i);

            JLabel letterLabel = new JLabel();
            if (Character.toLowerCase(letter) == Character.toLowerCase(secretLetter)) {
                letterLabel.setText(Character.toString(letter));
            } else if (letter == '-') {
                if (ongoingGame)
                    letterLabel.setText("-");
                else {
                    letterLabel.setText(Character.toString(secretLetter));
                    letterLabel.setForeground(Color.RED);
                }
            }

            letterLabel.setFont(new Font("Arial", Font.BOLD, 24));
            letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            letterLabel.setPreferredSize(new Dimension(50, 50));
            letterLabel.setOpaque(true);
            guessedWordPanel.add(letterLabel);
        }
        guessedWordPanel.revalidate();
        guessedWordPanel.repaint();
    }

    private void updateHangmanImage() {
        hangmanImageLabel.setIcon(new ImageIcon("img\\Hangman" + game.getAttemptsLeft() + ".gif"));
    }

    private void showGameOverMessage() {
        String message;
        if (game.getAttemptsLeft() <= 0) {
            message = "Out of attempts! The word was " + game.getSecretWord();
        } else {
            message = "Congratulations! The word was " + game.getCurrentGuess();
        }

        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(350, 150);
        gameOverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);

        JLabel gameOverLabel = new JLabel(message);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setVerticalAlignment(SwingConstants.CENTER);

        gameOverFrame.add(gameOverLabel);
        gameOverFrame.setVisible(true);
    }

    private void disableLetterButtons() {
        for (JButton button : letterButtons) {
            button.setEnabled(false);
        }
    }
}