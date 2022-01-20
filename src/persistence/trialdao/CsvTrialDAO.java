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

    /**
     * @return list of {@link Trial} read on CSV.
     */
    @Override
    public List<Trial> readAll() {//podemos hacer que el readTrials() sea el readAll pero asi parece mas limpio?
        return readTrials();
    }

    /**
     * First it erases the 4 files then writes trials in file
     *
     * @param trials updated Trial list
     */
    @Override
    public void writeAll(List<Trial> trials) {
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

    /**
     * @param t trial we want to write in csv file, then {@link #writeLine(String pathTrialType, Trial)}
     */
    private void writeTrial(Trial t) {
        if (t instanceof PublicArticle) {
            writeLine(csvArticlePath, t);
        } else if (t instanceof StudyMaster) {
            writeLine(csvMasterPath, t);
        } else if (t instanceof PhDefense) {
            writeLine(csvPHDPath, t);
        } else if (t instanceof BudgetRequest) {
            writeLine(csvBudgetPath, t);
        }
    }
    /**
     * checks if the file and directory exist, otherwise it creates them.
     */
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

    /**
     * Reads the 4 files of trials csv
     *
     * @return list of all {@link Trial} saved in csv file.
     */
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
                        trials.add(BudgetRequest.fromLine(scanFile.nextLine()));
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

    /**
     * @param file we want to check if it is empty.
     * @return true == empty file.
     */
    private boolean isDirectoryEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * {@link #checkDirectory(File)} and  {@link Trial#getInfo()} to write it in csv file.
     *
     * @param path to know the type of trial and where save it.
     * @param t    to {@link Trial#getInfo()}
     */
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