package persistence.trialdao;

import business.trial.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvTrialDAO implements TrialDAO {

    private final String csvArticlePath = "csv_files/publicArticle.csv";
    private final String csvMasterPath = "csv_files/studyMaster.csv";
    private final String csvPHDPath = "csv_files/phdDefense.csv";
    private final String csvBudgetPath = "csv_files/budgetRequest.csv";

    @Override
    public List<Trial> readAll() {//podemos hacer que el readTrials() sea el readAll pero asi parece mas limpio?
        return readTrials();
    }

    /**
     * @param trials
     */
    @Override
    public void writeAll(List<Trial> trials) {
        //primero vaciamos los 4 files
        try {
            new FileWriter(csvArticlePath).close();
            new FileWriter(csvMasterPath).close();
            new FileWriter(csvPHDPath).close();
            new FileWriter(csvBudgetPath).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!trials.isEmpty()) {
            for (Trial t : trials) {
                writeTrial(t);
            }
        }
    }

    private void writeTrial(Trial t) {
        if (t instanceof PublicArticle) {
            writeLine(csvArticlePath, t);
        } else if (t instanceof StudyMaster) {
            writeLine(csvMasterPath, t);
        } else if (t instanceof PhDefense) {
            writeLine(csvPHDPath, t);
        } else if (t instanceof BudgedRequest) {
            writeLine(csvBudgetPath, t);
        }
    }

    private void checkDirectory(File file) {
        File directory = new File("csv_files");
        directory.mkdir();
        if (!directory.exists()) directory.mkdirs();
        else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private List<Trial> readTrials() {
        Scanner scanFile = null;
        List<Trial> trials = new ArrayList<>();

        File articleFile = new File(csvArticlePath);
        try {
            if (articleFile.exists()) {
                scanFile = new Scanner(articleFile);
                if (!isDirectoryEmpty(articleFile)) {
                    while (scanFile.hasNextLine()) {
                        trials.add(PublicArticle.fromLine(scanFile.nextLine()));
                    }
                }
            }

            File masterFile = new File(csvMasterPath);
            scanFile = new Scanner(masterFile);
            if (masterFile.exists()) {
                if (!isDirectoryEmpty(masterFile)) {
                    while (scanFile.hasNextLine()) {
                        trials.add(StudyMaster.fromLine(scanFile.nextLine()));
                    }
                }
            }

            File phdFile = new File(csvPHDPath);
            scanFile = new Scanner(phdFile);
            if (phdFile.exists()) {
                if (!isDirectoryEmpty(phdFile)) {
                    while (scanFile.hasNextLine()) {
                        trials.add(PhDefense.fromLine(scanFile.nextLine()));
                    }
                }
            }

            File budgetFile = new File(csvBudgetPath);
            scanFile = new Scanner(budgetFile);
            if (budgetFile.exists()) {
                if (!isDirectoryEmpty(budgetFile)) {
                    while (scanFile.hasNextLine()) {
                        trials.add(BudgedRequest.fromLine(scanFile.nextLine()));
                    }
                }
            }
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

    private void writeLine(String path, Trial t) {
        try {
            File file = new File(path);
            checkDirectory(file);
            FileWriter writer = new FileWriter(file, true);

            writer.append(t.getInfo());
            writer.append(System.lineSeparator());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}