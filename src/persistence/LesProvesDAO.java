package persistence;

import business.*;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LesProvesDAO {
    private final static int ARTICLE = 1;
    private final static int MASTER = 2;
    private final static int PHD = 3;
    private final static int BUDGET = 4;
    private static Gson gson = new Gson();
    private List<Trial[]> trialsList = new ArrayList<>();

    private File publicArticleCsvFile = new File("csv_files/publicArticle.csv");
    private File studyMasterCsvFile = new File("csv_files/studyMaster.csv");
    private File phdDefenseCsvFile = new File("csv_files/phdDefense.csv");
    private File budgetRequestCsvFile = new File("csv_files/budgetRequest.csv");

    private File publicArticleJsonFile = new File("json_files/publicArticle.json");
    private File studyMasterJsonFile = new File("json_files/studyMaster.json");
    private File phdDefenseJsonFile = new File("json_files/phdDefense.json");
    private File budgetRequestJsonFile = new File("json_files/budgetRequest.json");

    public void writeJson () {

    }

    public List<Trial[]> readJson () {
        try {
            FileReader fileReader1 = new FileReader(publicArticleJsonFile);
            FileReader fileReader2 = new FileReader(studyMasterJsonFile);
            FileReader fileReader3 = new FileReader(phdDefenseJsonFile);
            FileReader fileReader4 = new FileReader(budgetRequestJsonFile);

            trialsList.add(gson.fromJson(fileReader1, PublicArticle[].class));
            trialsList.add(gson.fromJson(fileReader2, StudyMaster[].class));
            trialsList.add(gson.fromJson(fileReader3, PhDefense[].class));
            trialsList.add(gson.fromJson(fileReader4, BudgedRequest[].class));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return trialsList;
    }

    public void writeCsv () {

    }

    public List<Trial[]> readCsv () {

        return trialsList;
    }
}
