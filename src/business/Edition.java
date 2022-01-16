package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList<String> nameTrials;

    public Edition(int year, int numPlayers, int numTrials, ArrayList<String> nameTrials) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.nameTrials = nameTrials;
    }

    /**
     * @return
     */
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add(new StringBuilder("Year: ").append(year).toString());
        list.add(new StringBuilder("Players: ").append(numPlayers).toString());
        list.add("Trials:");

        return list;
    }

    public List<String> listTrials() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < numTrials; i++) {
            list.add(new StringBuilder("\t" + (i + 1)).append("- ").append(nameTrials.get(i)).toString());
        }
        return list;
    }

    /**
     * @return
     */
    public String getYear() {
        return new StringBuilder("The trials ").append(this.year).toString();
    }

    /**
     * @param editionsYear
     * @return
     */
    public boolean isYearCoincident(int editionsYear) {
        return editionsYear == this.year;
    }

    /**
     * @return
     */
    public ArrayList<String> getNameTrials() {
        return nameTrials;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @return String line with all data of Edition to save in CSV
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(year).append(",").append(numPlayers).append(",").append(numTrials).append(",[");
        for (int i = 0; i < nameTrials.size(); i++) {
            if (i == 0) {
                sb.append(nameTrials.get(i));
            } else {
                sb.append(",").append(nameTrials.get(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * It Creates an Edition from edition.csv information
     *
     * @param line receives a row from csv file
     * @return the object Edition to add it in the list.
     */
    public static Edition fromLine(String line) {
        int year = Integer.parseInt(line.split(",")[0]);
        int numPlayers = Integer.parseInt(line.split(",")[1]);
        int numTrials = Integer.parseInt(line.split(",")[2]);

        String s = line.substring(line.indexOf("["), line.indexOf("]")).substring(1);

        ArrayList<String> nameTrials = new ArrayList<>();
        Collections.addAll(nameTrials, (s.split(",")));
        return new Edition(year, numPlayers, numTrials, nameTrials);
    }
}
