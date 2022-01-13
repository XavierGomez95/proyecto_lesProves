import presentation.Controller;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Controller controller = new Controller(menu);

        controller.run();
    }
}
