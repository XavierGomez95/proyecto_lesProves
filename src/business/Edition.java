package business;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Edition {
    private int year;
    private int numPlayers;
    private int numTrials;
    private ArrayList<String>  nameTrials;
    private ArrayList<String>  namePlayers;

    public Edition(int year, int numPlayers, int numTrials, ArrayList<String> nameTrials) {
        this.year = year;
        this.numPlayers = numPlayers;
        this.numTrials = numTrials;
        this.nameTrials = nameTrials;
    }

    public List<String> getNameTrials (List<String> list ) {
        list.add("Year: " + year);
        list.add("Players: " + numPlayers);
        list.add("Trials:");
        for (int i = 0; i < numTrials; i++) {
            list.add("\t" + (i + 1) + "- " + nameTrials.get(i));
        }
        return list;
    }

    public String getEditionsInfo() {
        return "The trials "+ this.year;
    }

    public boolean isYearCoincident(int editionsYear) {
        return editionsYear == this.year;
    }
}
