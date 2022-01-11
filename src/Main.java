import business.trial.PublicArticle;
import business.trial.Trial;
import persistence.TrialDAO;
import presentation.Controller;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        TrialDAO trialDAO = new TrialDAO();

        for (Trial t : trialDAO.readCsv()) {
            t = (PublicArticle) t;
            System.out.println(t.getInfo());
        }
        Controller controller = new Controller(menu, trialDAO);

        controller.run();
    }
}
