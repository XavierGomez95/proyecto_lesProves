package business.trial;

import business.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PublicArticle extends Trial {
    private String magazineName;
    private String quartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;


    public PublicArticle(String magazineName, String quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability, String name) {
        super(name);
        this.quartile = quartile;
        this.magazineName = magazineName;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }

    @Override
    public List<String> listInfo() {
        List<String> list = new ArrayList<>();
        list.add("Trial: " + name + " (Paper publication)");
        list.add("Journal: " + magazineName + " (" + quartile + ")");
        list.add("Chances: " + acceptanceProbability + "% acceptance, " + revisionProbability + "% revision, " + rejectionProbability + "% rejection");

        return list;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "," + magazineName + "," + quartile + "," + acceptanceProbability + "," + revisionProbability + "," + rejectionProbability;
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String magazineName = line.split(",")[1];
        String quartile = line.split(",")[2];
        int acceptanceProbability = Integer.parseInt(line.split(",")[3]);
        int revisionProbability = Integer.parseInt(line.split(",")[4]);
        int rejectionProbability = Integer.parseInt(line.split(",")[5]);

        return new PublicArticle(magazineName, quartile, acceptanceProbability, revisionProbability, rejectionProbability, name);
    }


}
