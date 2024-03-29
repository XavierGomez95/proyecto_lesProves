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

    /**
     * @return a number corresponding to the quartile that has the test.
     */
    public int rewardPi() {
        int pi = 0;
        switch (quartile) {
            case "Q1" -> pi = 4;
            case "Q2" -> pi = 3;
            case "Q3" -> pi = 2;
            case "Q4" -> pi = 1;
        }
        return pi;
    }

    /**
     * @return a number corresponding to the quartile that has the test.
     */
    public int penaltyPi() {
        int pi = 0;
        switch (quartile) {
            case "Q1" -> pi = 5;
            case "Q2" -> pi = 4;
            case "Q3" -> pi = 3;
            case "Q4" -> pi = 2;
        }
        return pi;
    }

    /**
     * @return whether the article has been published or not.
     */
    public boolean isArticlePublished() {
        return (new Random().nextInt(101) <= acceptanceProbability);
    }

    /**
     * @return whether the review has been accepted or not.
     */
    public boolean isReviewAccepted() {
        return (new Random().nextInt(101) <= revisionProbability);
    }

    /**
     * @return whether the article has been rejected or not.
     */
    public boolean isArticleRejected() {
        return (new Random().nextInt(101) < rejectionProbability);
    }

    /**
     * @return a String representing the trial type.
     */
    @Override
    public String getType() {
        return " (Paper publication)";
    }

    /**
     * @return a String with all the information of a public article ready to be displayed on the screen.
     */
    @Override
    public String listInfo() {
        return "Trial: " + name + " (Paper publication)" + System.lineSeparator() +
                "Journal: " + magazineName + " (" + quartile + ")" + System.lineSeparator() +
                "Chances: " + acceptanceProbability + "% acceptance, " + revisionProbability +
                "% revision, " + rejectionProbability + "% rejection" + System.lineSeparator();
    }

    /**
     * @return a String with all the information of a public article ready to be persisted.
     */
    @Override
    public String getInfo() {
        return super.getInfo() + "," + magazineName + "," + quartile + "," +
                acceptanceProbability + "," + revisionProbability + "," +
                rejectionProbability;
    }

    /**
     * From a String it receives, it separates the information into variables and creates a new instance of the
     * class itself.
     *
     * @param line String with information about the public article.
     * @return an instance of the PublicArticle class.
     */
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
