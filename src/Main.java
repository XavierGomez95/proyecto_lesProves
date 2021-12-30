import persistence.TrialDAO;
import presentation.Controller;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        TrialDAO trialDAO = new TrialDAO();
        Controller controller = new Controller(menu, trialDAO);

        controller.run();
    }
}
