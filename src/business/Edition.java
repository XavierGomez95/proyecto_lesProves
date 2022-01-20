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
     * @return a list withe information of an Edition.
     */
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add("Year: " + year);
        list.add("Players: " + numPlayers);
        list.add("Trials:");

        return list;
    }

    /**
     * @return a String with the year and other information.
     */
    public String getYear() {
        return "The trials " + this.year;
    }

    /**
     * @param editionsYear represents a year
     * @return Returns if the year received is the same as the year of the current Edition.
     */
    public boolean isYearCoincident(int editionsYear) {
        return editionsYear == this.year;
    }

    /**
     * @return a list with the name of the Trials.
     */
    public ArrayList<String> getNameTrials() {
        return nameTrials;
    }

    /**
     * Getter
     *
     * @return the number of players.
     */
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
