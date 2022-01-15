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

    @Override
    public List<Execution> readAll() {
        return readExecutions();
    }

    @Override
    public void writeAll(List<Execution> executions) {
        deleteContent();
        writeArray(executions);
    }

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
            }
        }
    }

    private List<Execution> readExecutions() {
        List<Execution> executions = new ArrayList<>();
        File file = new File(jsonExecutionPath);
        if (file.exists()) {
            try {
                executions.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonExecutionPath), Execution[].class)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return executions;
    }

    private void deleteContent() {
        try {
            new FileWriter(jsonExecutionPath).append("[]").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    //throw new CustomMessageException("Error trying to write" + System.lineSeparator());
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                //throw new CustomMessageException("Error trying to open" + System.lineSeparator());
            }
        }
    }

}
