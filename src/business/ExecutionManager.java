package business;

import business.trial.Trial;

import java.time.Year;
import java.util.List;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution;//la execution que estamos usando
    private Edition currentEdition; //necesario para saber los trials?

    public ExecutionManager(List<Execution> executions) {
        this.executions = executions;
    }

    public boolean checkCurrentYear() {
        boolean exists = false;
        for (Execution execution : executions) {
            if (execution.getYear() == Year.now().getValue()) {
                currentExecution = execution;
                exists = true;
            }
        }
        if (!exists) {
            currentExecution = new Execution(Year.now().getValue());
            executions.add(currentExecution);
        }

        return exists;
    }


    public void createPlayers(List<String> names) {
        currentExecution.createPlayers(names);
    }

    public void start(List<String> players, Trial t) {
        //llama a x threads dependiendo de los players , falta saber como averigua que player es cada uno para puntuar
        for (int i = 0; i < players.size(); i++) {
            System.out.println(i + "num players:" + players.size());
            Thread thread = new Thread();
            //lista.add(thread);
            thread.start();


        }

    }

}
