import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Dictionary{
    private ArrayList<String> words;
    public Dictionary(String fileName){
        this.words = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new File(fileName));
            while (sc.hasNext()) {
                words.add(sc.nextLine());
            }
        }catch (FileNotFoundException e){
            System.err.println("Error: file '" + fileName + "' not found.");
            System.exit(0);
        }
    }

    public Dictionary(){
        this("words\\random.txt");
    }

    public String generateWord(){
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
}
