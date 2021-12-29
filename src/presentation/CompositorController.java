package presentation;

import business.CompositorManager;
import business.ConductorManager;

public class CompositorController extends Controller{
    CompositorManager compositorM;

    public CompositorController(Menu menu) {
        super(menu);
        this.compositorM = new CompositorManager();
    }

    public void run () {

    }

    private void addPlayers () {

    }


}
