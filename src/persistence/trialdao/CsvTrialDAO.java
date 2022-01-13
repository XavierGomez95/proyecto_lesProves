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
            new FileWriter(csvArticlePath).close();//para borrar content
            new FileWriter(csvMasterPath).close();//para borrar content
            new FileWriter(csvPHDPath).close();//para borrar content
            new FileWriter(csvBudgetPath).close();//para borrar content
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Trial t : trials) {
            writeTrial(t);
        }


    }

    private void writeTrial(Trial t) {
        if (t instanceof PublicArticle) {
            writeLine(csvArticlePath, t);
        } else if (t instanceof StudyMaster) { // Casteo el t para usar las funcionas abstractas para el write
            writeLine(csvMasterPath, t);
        } else if (t instanceof PhDefense phDefense) { // Casteo el t para usar las funcionas abstractas para el write
            writeLine(csvPHDPath, t);
        } else if (t instanceof BudgedRequest budgedRequest) { // Casteo el t para usar las funcionas abstractas para el write
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
        try {

            scanFile = new Scanner(new File(csvArticlePath));
            if (!isDirectoryEmpty(new File(csvArticlePath))) {
                while (scanFile.hasNextLine()) {
                    trials.add(PublicArticle.fromLine(scanFile.nextLine()));
                }
            }

            scanFile = new Scanner(new File(csvMasterPath));
            if (!isDirectoryEmpty(new File(csvMasterPath))) {
                while (scanFile.hasNextLine()) {
                    trials.add(StudyMaster.fromLine(scanFile.nextLine()));
                }
            }

            scanFile = new Scanner(new File(csvPHDPath));
            if (!isDirectoryEmpty(new File(csvPHDPath))) {
                while (scanFile.hasNextLine()) {
                    trials.add(PhDefense.fromLine(scanFile.nextLine()));
                }
            }

            scanFile = new Scanner(new File(csvBudgetPath));
            if (!isDirectoryEmpty(new File(csvBudgetPath))) {
                while (scanFile.hasNextLine()) {
                    trials.add(BudgedRequest.fromLine(scanFile.nextLine()));
                }
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

    private boolean isDirectoryEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private List<Trial> readLinesTrial(Scanner scanFile, List<Trial> trials) {
        while (scanFile.hasNextLine()) {

            trials.add(PublicArticle.fromLine(scanFile.nextLine()));//no sirve sino se tiene que comprobar el path y es mas lio

        }
        return trials;
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