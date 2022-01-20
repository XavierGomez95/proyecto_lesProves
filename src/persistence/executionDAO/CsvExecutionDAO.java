package persistence.executionDAO;

import business.Execution;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvExecutionDAO implements ExecutionDAO {
    private final String csvExecutionPath = "csv_files/execution.csv";
    private File file = new File(csvExecutionPath);

    /**
     * @return list of {@link Execution} read on csv.
     */
    @Override
    public List<Execution> readAll() {
        return readExecutions();
    }

    /**
     * writes executions in csv file
     *
     * @param executions updated Execution list
     */
    @Override
    public void writeAll(List<Execution> executions) {
        deleteContent();
        try {
            new FileWriter(csvExecutionPath).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Execution execution : executions) {
            writeExecution(execution);
        }
    }

    /**
     * {@link #checkFile()} and{@link business.Execution#getInfo()} to write it in csv file.
     *
     * @param execution we want to write.
     */
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

    /**
     * @return list of all {@link Execution} saved in csv file.
     */
    private List<Execution> readExecutions() {
        Scanner scanFile = null;
        List<Execution> executions = new ArrayList<>();
        try {
            if (file.exists()) {
                if (!isDirectoryEmpty(file)) {
                    scanFile = new Scanner(file);
                    while (scanFile.hasNextLine()) {
                        executions.add(Execution.fromLine(scanFile.nextLine()));
                    }
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

    /**
     * @param file we want to check if it is empty.
     * @return true == empty file.
     */
    private boolean isDirectoryEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * checks if the file exists, otherwise we create it.
     */
    private void checkFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * it erases the document to overwrite on it.
     */
    private void deleteContent() {
        try {
            new FileWriter(file).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
