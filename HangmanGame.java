import java.util.ArrayList;
public class HangmanGame{
    private String secretWord;
    private String currentGuess;
    private int attemptsLeft;
    private ArrayList<Character> guessedLetters;
    private boolean gameOver;

    public HangmanGame(){
        secretWord = new Dictionary().generateWord();
        currentGuess = "";
        for (int i = 0; i < secretWord.length(); i++)
            currentGuess+="-";

        attemptsLeft = 6;
        guessedLetters = new ArrayList<>();
        gameOver = false;
    }

    public HangmanGame(String filePath){
        /*this();
        secretWord = new Dictionary(filePath).generateWord();*/

        secretWord = new Dictionary(filePath).generateWord();
        currentGuess = "";
        for (int i = 0; i < secretWord.length(); i++)
            currentGuess+="-";

        attemptsLeft = 6;
        guessedLetters = new ArrayList<>();
        gameOver = false;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void makeGuess(char guess){
        guess = Character.toLowerCase(guess);

        if (!Character.isLetter(guess)){
            throw new IllegalArgumentException("Invalid guess. Enter a letter!");

        }
        if (guessedLetters.contains(guess)){
            throw new IllegalArgumentException("You already guessed this letter. Enter another one!");
        }

        guessedLetters.add(guess);
        boolean found = false;
        for (int i = 0; i < secretWord.length(); i++){
            if (Character.toLowerCase(guess) == Character.toLowerCase(secretWord.charAt(i))){
                currentGuess = currentGuess.substring(0, i) + secretWord.charAt(i) + currentGuess.substring(i + 1);
                found = true;
            }
        }
        //
        if (!found){
            attemptsLeft--;
            if (attemptsLeft <= 0) {
                gameOver = true;
                System.out.println("Out of attempts! The word was " + secretWord);
            }
        }
        if (currentGuess.equals(secretWord)){
            gameOver = true;
            System.out.println("Congratulations! You correctly guessed the word: " + secretWord);
        }
    }

    public int getAttemptsLeft(){
        return attemptsLeft;
    }

    public String getCurrentGuess(){
        return currentGuess;
    }

    public String getSecretWord(){
        return secretWord;
    }

    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
