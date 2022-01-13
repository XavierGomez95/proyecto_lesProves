package persistence.editiondao;

import business.Edition;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonEditionDAO implements EditionDAO {
    private static Gson gson = new Gson();
    private static Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();

    JsonArray arrayEdition = new JsonArray();
    private final String jsonEditionPath = "json_files/edition.json";

    @Override
    public List<Edition> readAll() {
        return readEditions();
    }

    @Override
    public void writeAll(List<Edition> editions) {
        try {
            new FileWriter(jsonEditionPath).append("[]").close();//para borrar content
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Edition edition : editions) {
            checkFile();
            JsonObject jsonObject = JsonParser.parseString(gson.toJson(edition)).getAsJsonObject();
            arrayEdition.add(jsonObject);

            try {
                OutputStream osArticle = new FileOutputStream(jsonEditionPath);
                osArticle.write(gsonBuild.toJson(arrayEdition).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private List<Edition> readEditions() {
        List<Edition> editions = new ArrayList<>();
        try {
            editions.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonEditionPath), Edition[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return editions;
    }

}
