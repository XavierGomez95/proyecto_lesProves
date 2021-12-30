package persistence;

import business.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LesProvesDAO {
    private static Gson gson = new Gson();
    private List<String> trialsList = new ArrayList<>();

    private Trial[] publicArticle = new PublicArticle[]{};
    private Trial[] studyMaster = new StudyMaster[]{};
    private Trial[] phDefense = new PhDefense[]{};
    private Trial[] budgedRequest = new BudgedRequest[]{};



    // JSON
    public void writeJson () {

    }

    public List<String> readJson () {
        try {
            trialsList.add(Arrays.toString(gson.fromJson(new FileReader("json_files/publicArticle.json"), PublicArticle[].class)));
            trialsList.add(Arrays.toString(gson.fromJson(new FileReader("json_files/studyMaster.json"), StudyMaster[].class)));
            trialsList.add(Arrays.toString(gson.fromJson(new FileReader("json_files/phdDefense.json"), PhDefense[].class)));
            trialsList.add(Arrays.toString(gson.fromJson(new FileReader("json_files/budgetRequest.json"), BudgedRequest[].class)));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return trialsList;
    }



    // CSV
    public void writeCsv () {

    }

    public List<String> readCsv () {
        try {
            BufferedReader bufferedReaderPublicAticle = new BufferedReader(new FileReader("csv_files/publicArticle.csv"));
            BufferedReader bufferedReaderStudyMaster = new BufferedReader(new FileReader("csv_files/studyMaster.csv"));
            BufferedReader bufferedReaderPhDefense = new BufferedReader(new FileReader("csv_files/phdDefense.csv"));
            BufferedReader bufferedReaderBudgetRequest = new BufferedReader(new FileReader("csv_files/budgetRequest.csv"));

            trialsList.add(readfiles(bufferedReaderPublicAticle).toString());
            trialsList.add(readfiles(bufferedReaderStudyMaster).toString());
            trialsList.add(readfiles(bufferedReaderPhDefense).toString());
            trialsList.add(readfiles(bufferedReaderBudgetRequest).toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return trialsList;
    }

    public ArrayList<String> readfiles (BufferedReader bufferedReader) {
        ArrayList<String> trials = new ArrayList<>();

        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                trials.add(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trials;
    }
}
