import java.util.Scanner;
public class Console {
    private HangmanGame game;
    public Console(){
        game = new HangmanGame();
    }
    public static void display(int remainingAttempts){
        switch(remainingAttempts){
            case 6:
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("    (_) ");
                System.out.println("    /|\\");
                System.out.println("     |  ");
                System.out.println("    / \\");
                System.out.println("________");
                break;

            case 5:
                System.out.println("        ");
                System.out.println("        ");
                System.out.println("    (_) ");
                System.out.println("    /|\\");
                System.out.println("     |  ");
                System.out.println("    / \\");
                System.out.println("    MMM ");
                System.out.println("____MMM_");
                break;
            case 4:
                System.out.println("        ");
                System.out.println("|       ");
                System.out.println("|   (_) ");
                System.out.println("|   /|\\");
                System.out.println("|    |  ");
                System.out.println("|   / \\");
                System.out.println("|   MMM ");
                System.out.println("|___MMM_");
                break;
            case 3:
                System.out.println("        ");
                System.out.println("| /     ");
                System.out.println("|/  (_) ");
                System.out.println("|   /|\\");
                System.out.println("|    |  ");
                System.out.println("|   / \\");
                System.out.println("|   MMM ");
                System.out.println("|___MMM_");
                break;
            case 2:
                System.out.println("______  ");
                System.out.println("| /     ");
                System.out.println("|/  (_) ");
                System.out.println("|   /|\\");
                System.out.println("|    |  ");
                System.out.println("|   / \\");
                System.out.println("|   MMM ");
                System.out.println("|___MMM_");
                break;
            case 1:
                System.out.println("______  ");
                System.out.println("|/   |  ");
                System.out.println("|   (_) ");
                System.out.println("|   /|\\");
                System.out.println("|    |  ");
                System.out.println("|   / \\");
                System.out.println("|   MMM ");
                System.out.println("|___MMM_");
                break;
            case 0:
                System.out.println("______  ");
                System.out.println("|/   |  ");
                System.out.println("|   (_) ");
                System.out.println("|   /|\\");
                System.out.println("|    |  ");
                System.out.println("|   | | ");
                System.out.println("|       ");
                System.out.println("|_______");
                break;
            default:
                break;

        }
    }

    public void displayGuessedLetters() {
        System.out.print("Guessed Letters: ");
        for (Character letter : game.getGuessedLetters()) {
            System.out.print(letter + " ");
        }
        System.out.println();
    }
    public void play(){
        display(6);
        System.out.println();
        System.out.print("Enter a letter: ");
        Scanner sc = new Scanner(System.in);
        while(!game.isGameOver()){
            String charInString = sc.next();
            try {
                if (!(charInString.length() == 1)) {
                    throw new IllegalArgumentException("It must be a letter. Exactly 1 character!");
                }
                game.makeGuess(charInString.charAt(0));
            }catch(IllegalArgumentException e){
                System.err.println("Error: " + e.getMessage());
                System.out.println();
                continue;
            }
            if (game.getAttemptsLeft() > 0) {
                System.out.println("Current guess: " + game.getCurrentGuess());
                System.out.println("Used letters: " + game.getGuessedLetters());
                System.out.println(game.getAttemptsLeft() + " attempts left!");
            }
            display(game.getAttemptsLeft());
            System.out.println();
        }
    }
}
