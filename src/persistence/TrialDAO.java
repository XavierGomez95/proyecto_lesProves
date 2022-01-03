package persistence;

import business.trial.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrialDAO {
    private static Gson gson = new Gson();
    private List<Trial> trials = new ArrayList<>();

    private final String jsonArticle = "json_files/publicArticle.json";
    private final String jsonMaster = "json_files/studyMaster.json";
    private final String jsonPHD = "json_files/phdDefense.json";
    private final String jsonBudget = "json_files/budgetRequest.json";

    private final String csvArticle = "csv_files/publicArticle.csv";
    private final String csvMaster = "csv_files/studyMaster.csv";
    private final String csvPHD = "csv_files/phdDefense.csv";
    private final String csvBudget = "csv_files/budgetRequest.csv";

    // JSON
    public void writeJson(List<Trial> trials) {
        for (Trial t : trials) {
            if (t instanceof PublicArticle) {
                writeTrialJson(jsonArticle, t);
            } else if (t instanceof StudyMaster) {
                writeTrialJson(jsonMaster, t);
            } else if (t instanceof PhDefense) {
                writeTrialJson(jsonPHD, t);
            } else if (t instanceof BudgedRequest) {
                writeTrialJson(jsonBudget, t);
            }
        }
    }

    private void writeTrialJson(String path, Trial t) {
        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        try {
            OutputStream os = new FileOutputStream(path);
            os.write(gsonBuild.toJson(t).getBytes());
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Trial> readJson() {
        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonArticle), PublicArticle[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonMaster), StudyMaster[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonPHD), PhDefense[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonBudget), BudgedRequest[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return trials;
    }


    // CSV
    public void writeCsv() {

    }

    /*  public List<Trial> readCsv() {
          try {
              BufferedReader bufferedReaderPublicArticle = new BufferedReader(new FileReader("csv_files/publicArticle.csv"));
              BufferedReader bufferedReaderStudyMaster = new BufferedReader(new FileReader("csv_files/studyMaster.csv"));
              BufferedReader bufferedReaderPhDefense = new BufferedReader(new FileReader("csv_files/phdDefense.csv"));
              BufferedReader bufferedReaderBudgetRequest = new BufferedReader(new FileReader("csv_files/budgetRequest.csv"));

              trialsList.add(readFiles(bufferedReaderPublicArticle).toString());
              trialsList.add(readFiles(bufferedReaderStudyMaster).toString());
              trialsList.add(readFiles(bufferedReaderPhDefense).toString());
              trialsList.add(readFiles(bufferedReaderBudgetRequest).toString());
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }

          return trialsList;
      }
     */

    public List<Trial> readCsv() {
        Scanner scanFile = null;
        List<Trial> trials = new ArrayList<>();
        try {
            File f = new File(csvArticle);
            scanFile = new Scanner(f);
            while (scanFile.hasNextLine()) {
                trials.add(PublicArticle.fromLine(scanFile.nextLine()));
            }
            scanFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanFile != null) {
                scanFile.close();
            }
        }
        return trials;
    }
/*
    public ArrayList<String> readFiles(BufferedReader bufferedReader) {
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

 */
}
