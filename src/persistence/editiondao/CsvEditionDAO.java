package persistence.editiondao;

import business.Edition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvEditionDAO implements EditionDAO {
    private final String csvEditionPath = "csv_files/edition.csv";
    private File file = new File(csvEditionPath);

    /**
     * @return list of {@link Edition} read on csv.
     */
    @Override
    public List<Edition> readAll() {
        return readEditions();
    }

    /**
     * writes Editions in csv file
     *
     * @param editions updated Edition list
     */
    @Override
    public void writeAll(List<Edition> editions) {
        deleteContent();
        if (!editions.isEmpty()) {
            for (Edition e : editions) {
                writeEdition(e);
            }
        }
    }

    /**
     * {@link #checkFile()} and{@link Edition#getInfo()} to write it in csv file.
     *
     * @param edition we want to write.
     */
    private void writeEdition(Edition edition) {
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

    /**
     * checks if the file exists, otherwise we create it.
     */
    private void checkFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @return list of all {@link Edition} saved in csv file.
     */
    private List<Edition> readEditions() {
        Scanner scanFile = null;
        List<Edition> editions = new ArrayList<>();
        try {
            if (file.exists()) {
                scanFile = new Scanner(file);
                if (!isDirectoryEmpty(file)) {
                    while (scanFile.hasNextLine()) {
                        editions.add(Edition.fromLine(scanFile.nextLine()));

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
        return editions;
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
     * it erases the document to overwrite on it.
     */
    private void deleteContent() {
        try {
            new FileWriter(file).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}