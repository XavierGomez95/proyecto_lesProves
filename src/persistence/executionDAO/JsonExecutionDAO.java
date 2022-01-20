package persistence.executionDAO;

import business.Execution;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonExecutionDAO implements ExecutionDAO {
    private final String jsonExecutionPath = "json_files/execution.json";
    private Gson gson = new Gson();

    /**
     * @return list of {@link Execution} read on json.
     */
    @Override
    public List<Execution> readAll() {
        return readExecutions();
    }

    /**
     * writes executions in json file
     *
     * @param executions updated Execution list
     */
    @Override
    public void writeAll(List<Execution> executions) {
        deleteContent();
        writeArray(executions);
    }

    /**
     * it adds in arrayExecution the json object of each {@link Execution}.
     *
     * @param executions we want to write.
     */
    private void writeArray(List<Execution> executions) {
        JsonArray arrayExecution = new JsonArray();

        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        if (!executions.isEmpty()) {
            for (Execution execution : executions) {
                checkFile();
                JsonObject jsonObject = JsonParser.parseString(gson.toJson(execution)).getAsJsonObject();
                arrayExecution.add(jsonObject);
            }
            try {
                OutputStream osArticle = new FileOutputStream(jsonExecutionPath);
                osArticle.write(gsonBuild.toJson(arrayExecution).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                //throw new IOException("Error trying to open" + System.lineSeparator());
            }
        }
    }

    /**
     * it reads the json file.
     *
     * @return the list of {@link Execution} read from file.
     */
    private List<Execution> readExecutions() {
        List<Execution> executions = new ArrayList<>();
        File file = new File(jsonExecutionPath);
        if (file.exists()) {
            try {
                executions.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonExecutionPath), Execution[].class)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //throw new FileNotFoundException("File not found" + System.lineSeparator());
            }
        }
        return executions;
    }

    /**
     * it erases the document to overwrite on it.
     */
    private void deleteContent() {
        try {
            new FileWriter(jsonExecutionPath).append("[]").close();
        } catch (IOException e) {
            //throw new IOException("Error trying to delete content" + System.lineSeparator());
            e.printStackTrace();
        }
    }

    /**
     * checks if the file exists, otherwise we create it.
     */
    private void checkFile() {
        File file = new File(jsonExecutionPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.append("[{}]");
                    writer.flush();
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                    //throw new WrongInputException("Error trying to write" + System.lineSeparator());
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                //throw new WrongInputException("Error trying to open" + System.lineSeparator());
            }
        }
    }

}
