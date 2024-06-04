import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MainMenu extends JFrame {
    private static final int WIDTH = 450;
    private static final int HEIGHT = 350;
    private static final Color backgroundColor = new Color(144, 238, 144);
    private static ImageIcon icon = new ImageIcon("img\\icon.png");
    public MainMenu(){
        super("Main Menu");
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY);
        setIconImage(icon.getImage());

        JLabel menuImageLabel = new JLabel(new ImageIcon("img\\Menu.jpg"));
        add(menuImageLabel, BorderLayout.CENTER);

        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new FlowLayout());
        menuButtonPanel.setBackground(Color.DARK_GRAY);

        JButton newGameButton = new JButton("New Game");
        JButton modesButton = new JButton("Modes");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        JPanel buttonFirstRow = new JPanel();
        buttonFirstRow.setLayout(new FlowLayout());
        buttonFirstRow.add(newGameButton);
        buttonFirstRow.add(modesButton);
        buttonFirstRow.add(helpButton);
        buttonFirstRow.add(exitButton);
        buttonFirstRow.setBackground(Color.BLACK);

        menuButtonPanel.add(buttonFirstRow);

        add(menuButtonPanel, BorderLayout.SOUTH);

        // buttons' Action Listeners
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HangmanGUI game = new HangmanGUI();
                game.setVisible(true);
                setVisible(false);
            }
        });

        modesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame modesFrame = new JFrame("Modes");
                modesFrame.setSize(WIDTH, HEIGHT);
                modesFrame.setLocationRelativeTo(MainMenu.this);
                modesFrame.setLayout(new GridLayout(2,1));
                modesFrame.getContentPane().setBackground(backgroundColor);

                JLabel titleLabel = new JLabel("Modes", SwingConstants.CENTER);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
                titleLabel.setForeground(Color.red);
                modesFrame.add(titleLabel);

                JPanel modeSelectionPanel = new JPanel();
                modeSelectionPanel.setBackground(backgroundColor);
                modeSelectionPanel.setLayout(new FlowLayout());
                modeSelectionPanel.setPreferredSize(new Dimension(WIDTH/4, HEIGHT/3));
                JButton themesButton = new JButton("By Themes");

                themesButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modeSelectionPanel.removeAll();

                        String[] themes = {"animals", "fruits and vegetables", "colors", "sports",
                                "countries", "capital cities", "random"};

                        JLabel themeLabel = new JLabel("Select a Theme:");
                        themeLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        themeLabel.setForeground(Color.BLACK);
                        modeSelectionPanel.add(themeLabel);

                        JPanel firstRow = new JPanel(new FlowLayout());
                        firstRow.setBackground(Color.orange);
                        JPanel secondRow = new JPanel(new FlowLayout());
                        secondRow.setBackground(Color.BLUE);

                        for (String theme : themes) {
                            JButton themeButton = new JButton(theme);
                            themeButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    JButton clickedButton = (JButton) e.getSource();
                                    String buttonText = clickedButton.getText();
                                    HangmanGUI hangman = new HangmanGUI("words\\"+ buttonText + ".txt");
                                    hangman.setVisible(true);
                                    modesFrame.dispose();
                                }
                            });

                            if (firstRow.getComponentCount() < 4) {
                                firstRow.add(themeButton);
                            } else {
                                secondRow.add(themeButton);
                            }
                        }

                        modeSelectionPanel.add(firstRow);
                        modeSelectionPanel.add(secondRow);

                        modeSelectionPanel.revalidate();
                        modeSelectionPanel.repaint();
                    }
                });
                modeSelectionPanel.add(themesButton);


                modesFrame.add(modeSelectionPanel);
                modesFrame.setVisible(true);
                setVisible(false);

                modesFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.setVisible(true);
                    }
                });
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame helpFrame = new JFrame("Help");
                helpFrame.setSize(765, 465);
                helpFrame.setLocationRelativeTo(MainMenu.this);
                JTextArea helpTextArea = new JTextArea("Welcome to Hangman!\n" +
                        "\n" +
                        "Objective:\n" +
                        "Guess the word by suggesting letters. You have a limited number of incorrect guesses before the game ends.\n" +
                        "\n" +
                        "How to Play:\n" +
                        "1. At the start of the game, a word is chosen at random, and its length is displayed as blank spaces.\n" +
                        "2. Guess a letter by clicking on the corresponding button.\n" +
                        "3. If the letter is correct, it will fill in the blanks in the word.\n" +
                        "4. If the letter is incorrect, a part of the hangman will be drawn.\n" +
                        "5. Keep guessing letters until you solve the word or make too many incorrect guesses.\n" +
                        "6. You win if you correctly guess the word before the hangman is fully drawn.\n" +
                        "7. You lose if the hangman is fully drawn before you guess the word.\n" +
                        "\n" +
                        "Controls:\n" +
                        "- Click on the letter buttons to guess a letter.\n" +
                        "- Use the \"New Game\" button to start a new game.\n" +
                        "- Use the \"Scores\" button to view high scores.\n" +
                        "- Use the \"Exit\" button to quit the game.\n" +
                        "\n" +
                        "Have fun playing Hangman!");
                helpTextArea.setEditable(false);
                helpTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

                JScrollPane scrollPane = new JScrollPane(helpTextArea);
                helpFrame.add(scrollPane, BorderLayout.CENTER);
                helpFrame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame checkBoxFrame = new JFrame("Closing Hangman");
                checkBoxFrame.setSize(400,150);
                checkBoxFrame.setLocationRelativeTo(MainMenu.this);
                checkBoxFrame.setLayout(new GridLayout(2,1));
                checkBoxFrame.setResizable(false);

                JLabel closingLabel = new JLabel("Are you sure?", SwingConstants.CENTER);
                closingLabel.setFont(new Font("Arial", Font.BOLD, 16));
                closingLabel.setForeground(Color.BLACK);
                checkBoxFrame.add(closingLabel);

                JPanel buttonGroup = new JPanel(new FlowLayout());
                JButton continueButton = new JButton("Continue");
                continueButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                buttonGroup.add(continueButton);

                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkBoxFrame.dispose();
                    }
                });
                buttonGroup.add(cancelButton);

                checkBoxFrame.add(buttonGroup);
                checkBoxFrame.setVisible(true);
            }
        });
    }
}

