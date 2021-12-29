package persistence;

import business.Player;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LesProvesDAO {
    private static Gson gson = new Gson();

    private List<Player[]> playersList = new ArrayList<>();

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

    public List<Player[]> readJson () {

        return playersList;
    }

    public void writeCsv () {

    }

    public List<Player[]> readCsv () {

        return playersList;
    }
}
