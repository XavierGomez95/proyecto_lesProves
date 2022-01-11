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

    private final String jsonArticlePath = "json_files/publicArticle.json";
    private final String jsonMasterPath = "json_files/studyMaster.json";
    private final String jsonPHDPath = "json_files/phdDefense.json";
    private final String jsonBudgetPath = "json_files/budgetRequest.json";

    private final String csvArticlePath = "csv_files/publicArticle.csv";
    private final String csvMasterPath = "csv_files/studyMaster.csv";
    private final String csvPHDPath = "csv_files/phdDefense.csv";
    private final String csvBudgetPath = "csv_files/budgetRequest.csv";

    // JSON
    public void writeJson(List<Trial> jsonTrials) {
        detectInstance(jsonTrials, "Json");
    }

    private void writeTrialJson(Trial t) {
        Gson gsonBuild = new GsonBuilder().setPrettyPrinting().create();
        try {
            if (t instanceof PublicArticle publicArticle) {
                OutputStream os = new FileOutputStream(jsonArticlePath);
                os.write(gsonBuild.toJson(publicArticle).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof StudyMaster studyMaster) {
                OutputStream os = new FileOutputStream(jsonMasterPath);
                os.write(gsonBuild.toJson(studyMaster).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof PhDefense phDefense) {
                OutputStream os = new FileOutputStream(jsonPHDPath);
                os.write(gsonBuild.toJson(phDefense).getBytes());
                os.flush();
                os.close();
            } else if (t instanceof BudgedRequest budgedRequest) {
                OutputStream os = new FileOutputStream(jsonBudgetPath);
                os.write(gsonBuild.toJson(budgedRequest).getBytes());
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Trial> readJson() {
        List<Trial> trials = new ArrayList<>();
        try {
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonArticlePath), PublicArticle[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonMasterPath), StudyMaster[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonPHDPath), PhDefense[].class)));
            trials.addAll(Arrays.asList(gson.fromJson(new FileReader(jsonBudgetPath), BudgedRequest[].class)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return trials;
    }


    // CSV
    public void writeCsv(List<Trial> csvfTrials) {
        detectInstance(csvfTrials, "Csv");
    }

    private void writeTrialCsv(Trial t) {
        try {
            if (t instanceof PublicArticle publicArticle) { // Casteo el t para usar las funcionas abstractas para el write
                File file = new File(csvArticlePath);
                FileWriter outputCsvFile = new FileWriter(file);
                outputCsvFile.write(publicArticle.getInfo());
                outputCsvFile.close();
            } else if (t instanceof StudyMaster studyMaster) { // Casteo el t para usar las funcionas abstractas para el write
                File file = new File(csvMasterPath);
                FileWriter outputCsvFile = new FileWriter(file);
                outputCsvFile.write(studyMaster.getInfo());
                outputCsvFile.close();
            } else if (t instanceof PhDefense phDefense) { // Casteo el t para usar las funcionas abstractas para el write
                File file = new File(csvPHDPath);
                FileWriter outputCsvFile = new FileWriter(file);
                outputCsvFile.write(phDefense.getInfo());
                outputCsvFile.close();
            } else if (t instanceof BudgedRequest budgedRequest) { // Casteo el t para usar las funcionas abstractas para el write
                File file = new File(csvBudgetPath);
                FileWriter outputCsvFile = new FileWriter(file);
                outputCsvFile.write(budgedRequest.getInfo());
                outputCsvFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Trial> readCsv() { // Corregir esto
        Scanner scanFile = null;
        List<Trial> trials = new ArrayList<>();
        try {

            scanFile = new Scanner(new File(csvArticlePath));
            if (!isDirectoryEmpty(new File(csvArticlePath))) readLines(scanFile, trials);

            scanFile = new Scanner(new File(csvMasterPath));
            if (!isDirectoryEmpty(new File(csvMasterPath))) readLines(scanFile, trials);

            scanFile = new Scanner(new File(csvPHDPath));
            if (!isDirectoryEmpty(new File(csvPHDPath))) readLines(scanFile, trials);

            scanFile = new Scanner(new File(csvBudgetPath));
            if (!isDirectoryEmpty(new File(csvBudgetPath))) readLines(scanFile, trials);

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

    private boolean isDirectoryEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<Trial> readLines(Scanner scanFile, List<Trial> trials) {
        /*
        while (scanFile.hasNextLine()) {
            trials.add(PublicArticle.fromLine(scanFile.nextLine()));
        }
        */
        return trials;
    }


    private void detectInstance(List<Trial> trials, String format) {
        for (Trial t : trials) {
            switch (format) {
                case "Json" -> writeTrialJson(t);
                case "Csv" -> writeTrialCsv(t);
            }
        }
    }
}