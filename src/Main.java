import persistence.LesProvesDAO;
import presentation.Controller;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        LesProvesDAO lesProvesDAO = new LesProvesDAO();
        Controller controller = new Controller(menu, lesProvesDAO);

        controller.run();
    }
}
