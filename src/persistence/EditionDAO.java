package persistence;

import business.Edition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditionDAO {
    private final String csvEditionPath = "csv_files/edition.csv";
    private final String jsonEditionPath = "json_files/edition.json";
    private File file = new File(csvEditionPath);

    /**
     * @param editions
     */
    public void writeCsv(List<Edition> editions) {
        try {
            new FileWriter(csvEditionPath).close();//para borrar content
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Edition e : editions) {
            writeEditionCsv(e);
        }


    }

    private void writeEditionCsv(Edition edition) {
        try {
            checkFile();
            FileWriter writer = new FileWriter(file, true);

            writer.append(edition.getInfo());
            writer.append(System.lineSeparator());

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void checkFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private List<Edition> readTrials() {
        Scanner scanFile = null;
        List<Edition> editions = new ArrayList<>();
        try {

            scanFile = new Scanner(file);
            if (!isDirectoryEmpty(file)) {
                while (scanFile.hasNextLine()) {
                    editions.add(Edition.fromLine(scanFile.nextLine()));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanFile != null) {
                scanFile.close();
            }
        }
        return editions;
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
}