package business;

import java.time.Year;
import java.util.List;

public class ExecutionManager {
    private List<Execution> executions;
    private Execution currentExecution;//la execution que estamos usando

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

}
