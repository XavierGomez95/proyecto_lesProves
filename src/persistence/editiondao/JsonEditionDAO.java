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

    @Override
    public List<Edition> readAll() {
        return readEditions();
    }

    @Override
    public void writeAll(List<Edition> editions) {
        deleteContent();
        writeArray(editions);
    }


    private List<Edition> readEditions() {
        List<Edition> editions = new ArrayList<>();


        try {
            editions.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonEditionPath), Edition[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return editions;
    }

    private void writeArray(List<Edition> editions) {
        JsonArray arrayEdition = new JsonArray();

        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();

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

    private void deleteContent() {
        try {
            new FileWriter(jsonEditionPath).append("[]").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    //throw new CustomMessageException("Error trying to write" + System.lineSeparator());
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                //throw new CustomMessageException("Error trying to open" + System.lineSeparator());
            }
        }
    }
}
