package persistence;

import business.trial.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonTrialDAO implements TrialDAO {
    private static Gson gson = new Gson();

    private final String jsonArticlePath = "json_files/publicArticle.json";
    private final String jsonMasterPath = "json_files/studyMaster.json";
    private final String jsonPHDPath = "json_files/phdDefense.json";
    private final String jsonBudgetPath = "json_files/budgetRequest.json";

    @Override
    public List<Trial> readAll() {//podemos hacer que el readTrials() sea el readAll pero asi parece mas limpio?
        return readTrials();
    }

    @Override
    public void writeAll(List<Trial> Trials) {
        for (Trial t : Trials) {
            writeTrial(t);
        }
    }

    private void writeTrial(Trial t) {
        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        try {
            if (t instanceof PublicArticle publicArticle) {
                checkDirectory(jsonArticlePath);
                OutputStream os = new FileOutputStream(jsonArticlePath);
                os.write(gsonBuild.toJson(publicArticle).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof StudyMaster studyMaster) {
                checkDirectory(jsonMasterPath);
                OutputStream os = new FileOutputStream(jsonMasterPath);
                os.write(gsonBuild.toJson(studyMaster).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof PhDefense phDefense) {
                checkDirectory(jsonPHDPath);
                OutputStream os = new FileOutputStream(jsonPHDPath);
                os.write(gsonBuild.toJson(phDefense).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof BudgedRequest budgedRequest) {
                checkDirectory(jsonBudgetPath);
                OutputStream os = new FileOutputStream(jsonBudgetPath);
                os.write(gsonBuild.toJson(budgedRequest).getBytes());
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lo que esta comentado no va aqui, pero sirve para escribir los "[]" dentro de los JSONs.
    // https://docs.oracle.com/javaee/7/api/javax/json/JsonArray.html
    // private void checkDirectoryJson (String path) throws CustomMessageException {
    private void checkDirectory(String path) {
        //JsonObject jsonObject = new JsonObject();
        //JsonArray jsonArray = new JsonArray();
        File directory = new File("json_files");
        directory.mkdir();
        File file = new File(path);
        if (!directory.exists()) directory.mkdirs();
        else if (!file.exists()) {
            try {
                file.createNewFile();
                /* NO HACE FALTA ESCRIBIR [] pq antes se comprueba que trials es not empty por lo tanto se escribe si o si
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.append("{\"data\":[]}");
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //throw new CustomMessageException("Error trying to write" + System.lineSeparator());
                }

                 */
                //Files.write(Paths.get(path), jsonObject.toString().getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
                //throw new CustomMessageException("Error trying to open" + System.lineSeparator());
            }
        }
    }

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
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonBudgetPath), BudgedRequest[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return trials;
    }
}