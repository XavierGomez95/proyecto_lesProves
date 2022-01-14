package persistence.executionDAO;

import business.Execution;

import java.util.List;

public interface ExecutionDAO {
    List<Execution> readAll();

    void writeAll(List<Execution> executions);

}
