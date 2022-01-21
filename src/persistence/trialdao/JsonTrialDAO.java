package persistence.trialdao;

import business.trial.*;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTrialDAO implements TrialDAO {
    private static Gson gson = new Gson();
    private JsonArray arrayArticle = new JsonArray();
    private JsonArray arrayMaster = new JsonArray();
    private JsonArray arrayPHD = new JsonArray();
    private JsonArray arrayBudget = new JsonArray();

    private final String jsonArticlePath = "json_files/publicArticle.json";
    private final String jsonMasterPath = "json_files/studyMaster.json";
    private final String jsonPHDPath = "json_files/phdDefense.json";
    private final String jsonBudgetPath = "json_files/budgetRequest.json";

    /**
     * @return list of {@link Trial} read on JSON.
     */
    @Override
    public List<Trial> readAll() {//podemos hacer que el readTrials() sea el readAll pero asi parece mas limpio?
        return readTrials();
    }

    /**
     * writes trials in file {@link #writeArrays()}
     *
     * @param trials updated Trial list
     */
    @Override
    public void writeAll(List<Trial> trials) {
        try {
            new FileWriter(jsonArticlePath).append("[]").close();//para borrar content
            new FileWriter(jsonMasterPath).append("[]").close();//para borrar content
            new FileWriter(jsonPHDPath).append("[]").close();//para borrar content
            new FileWriter(jsonBudgetPath).append("[]").close();//para borrar content
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Trial t : trials) {
            addToArray(t);
        }
        writeArrays();
    }

    /**
     * it writes to each file all the information
     */
    private void writeArrays() {
        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        try {
            if (!arrayArticle.isEmpty()) {
                OutputStream osArticle = new FileOutputStream(jsonArticlePath);
                osArticle.write(gsonBuild.toJson(arrayArticle).getBytes());
            }
            if (!arrayMaster.isEmpty()) {
                OutputStream osMaster = new FileOutputStream(jsonMasterPath);
                osMaster.write(gsonBuild.toJson(arrayMaster).getBytes());
            }
            if (!arrayPHD.isEmpty()) {
                OutputStream osPhd = new FileOutputStream(jsonPHDPath);
                osPhd.write(gsonBuild.toJson(arrayPHD).getBytes());
            }
            if (!arrayBudget.isEmpty()) {
                OutputStream osBudget = new FileOutputStream(jsonBudgetPath);
                osBudget.write(gsonBuild.toJson(arrayBudget).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * it adds in each array of typeTrial the param we want to write.
     *
     * @param t trial we want to write.
     */
    private void addToArray(Trial t) {
        if (t instanceof PublicArticle) {
            checkDirectory(jsonArticlePath);
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(t)).getAsJsonObject();
            arrayArticle.add(jsonObject);
        } else if (t instanceof StudyMaster) {
            checkDirectory(jsonMasterPath);
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(t)).getAsJsonObject();
            arrayMaster.add(jsonObject);
        } else if (t instanceof PhDefense) {
            checkDirectory(jsonPHDPath);
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(t)).getAsJsonObject();
            arrayPHD.add(jsonObject);
        } else if (t instanceof BudgetRequest) {
            checkDirectory(jsonBudgetPath);
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(t)).getAsJsonObject();
            arrayBudget.add(jsonObject);
        }
    }


    /**
     * checks if the file and directory exist, otherwise it creates them.
     *
     * @param path of each json file.
     */
    private void checkDirectory(String path) {
        File directory = new File("json_files");
        directory.mkdir();
        File file = new File(path);
        if (!directory.exists()) directory.mkdirs();
        else if (!file.exists()) {

            try {
                file.createNewFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.append("[{}]");
                    writer.flush();
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * it reads the 4 json files.
     *
     * @return the list of {@link Trial} read from file.
     */
    private List<Trial> readTrials() {
        List<Trial> trials = new ArrayList<>();
        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonArticlePath), PublicArticle[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonMasterPath), StudyMaster[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonPHDPath), PhDefense[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonBudgetPath), BudgetRequest[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return trials;
    }
}