package persistence.executionDAO;

import business.Execution;

import java.util.List;

public interface ExecutionDAO {
    /**
     * @return list of {@link Execution} read on file.
     */
    List<Execution> readAll();

    /**
     * writes executions in file
     *
     * @param executions updated Edition list
     */
    void writeAll(List<Execution> executions);

}
