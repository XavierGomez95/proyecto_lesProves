package persistence.executionDAO;

import business.Edition;
import business.Execution;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvExecutionDAO implements ExecutionDAO {
    private final String csvExecutionPath = "csv_files/execution.csv";
    private File file = new File(csvExecutionPath);

    @Override
    public List<Execution> readAll() {
        return readExecutions();
    }

    @Override
    public void writeAll(List<Execution> executions) {
        try {
            new FileWriter(csvExecutionPath).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Execution execution : executions) {
            writeExecution(execution);
        }
    }

    private void writeExecution(Execution execution) {
        try {
            checkFile();
            FileWriter writer = new FileWriter(file, true);

            writer.append(execution.getInfo());
            writer.append(System.lineSeparator());

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private List<Execution> readExecutions() {
        Scanner scanFile = null;
        List<Execution> executions = new ArrayList<>();
        try {

            scanFile = new Scanner(file);
            if (!isDirectoryEmpty(file)) {
                while (scanFile.hasNextLine()) {
                    executions.add(Execution.fromLine(scanFile.nextLine()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanFile != null) {
                scanFile.close();
            }
        }
        return executions;
    }


    private boolean isDirectoryEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void checkFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void deleteContent() {
        try {
            new FileWriter(file).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
