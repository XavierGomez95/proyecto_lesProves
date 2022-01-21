package persistence.editiondao;

import business.Edition;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonEditionDAO implements EditionDAO {
    private final String jsonEditionPath = "json_files/edition.json";
    private Gson gson = new Gson();

    /**
     * @return list of {@link Edition} read on json.
     */
    @Override
    public List<Edition> readAll() {
        return readEditions();
    }

    /**
     * writes Editions in json file
     *
     * @param editions updated Edition list
     */
    @Override
    public void writeAll(List<Edition> editions) {
        deleteContent();
        writeArray(editions);
    }

    /**
     * it reads the json file.
     *
     * @return the list of {@link Edition} read from file.
     */
    private List<Edition> readEditions() {
        List<Edition> editions = new ArrayList<>();
        File file = new File(jsonEditionPath);

        if (file.exists()) {
            try {
                editions.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonEditionPath), Edition[].class)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return editions;
    }

    /**
     * it adds in arrayEdition the json object of each {@link Edition}.
     *
     * @param editions we want to write.
     */
    private void writeArray(List<Edition> editions) {
        JsonArray arrayEdition = new JsonArray();

        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        if (!editions.isEmpty()) {
            for (Edition edition : editions) {
                checkFile();
                JsonObject jsonObject = JsonParser.parseString(gson.toJson(edition)).getAsJsonObject();
                arrayEdition.add(jsonObject);
            }
            try {
                OutputStream osArticle = new FileOutputStream(jsonEditionPath);
                osArticle.write(gsonBuild.toJson(arrayEdition).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * it erases the document to overwrite on it.
     */
    private void deleteContent() {
        try {
            new FileWriter(jsonEditionPath).append("[]").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if the file exists, otherwise we create it.
     */
    private void checkFile() {
        File file = new File(jsonEditionPath);
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
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
