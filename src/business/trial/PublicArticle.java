package business.trial;

import java.util.Random;

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


    public boolean execute() {
        boolean done = false;
        Random rand = new Random();
        if (rand.nextInt(100) <= acceptanceProbability) {//comparar con rejection probability? 3 if?
            done = true;
        }
        return done;
    }

    @Override
    public String listInfo() {
        return new StringBuilder("Trial: ").append(name).append(" (Paper publication)").append(System.lineSeparator())
                .append("Journal: ").append(magazineName).append(" (").append(quartile).append(")").append(System.lineSeparator())
                .append("Chances: ").append(acceptanceProbability).append("% acceptance, ").append(revisionProbability)
                .append("% revision, ").append(rejectionProbability).append("% rejection").append(System.lineSeparator()).toString();
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(super.getInfo()).append(",").append(magazineName).append(",").append(quartile).append(",").append(acceptanceProbability).append(",").append(revisionProbability).append(",").append(rejectionProbability).toString();
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

    @Override
    public String getType() {
        return " (Paper publication)";
    }
}
