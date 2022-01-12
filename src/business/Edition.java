package business;

import java.util.ArrayList;


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

}
