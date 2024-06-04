public class Main {
    public static void main(String[] args) {
        if (args.length == 0){
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        }
        else{
            Console console = new Console();
            console.play();
        }
    }
}
