package business.trial;

import business.player.Player;

import java.util.List;

public class PublicArticle extends Trial {
    private String magazineName;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;


    public PublicArticle(String name, List<Player> players, String magazineName, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        super(name, players);
        this.magazineName = magazineName;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }

    public static Trial fromLine(String line) {
        String name = line.split(",")[0];
        String magazineName = line.split(",")[1];
        int acceptanceProbability = Integer.parseInt(line.split(",")[2]);
        int revisionProbability = Integer.parseInt(line.split(",")[3]);
        int rejectionProbability = Integer.parseInt(line.split(",")[4]);
        String players = line.substring(line.indexOf("["), line.indexOf("]"));
        List<Player> playerList = Player.fromLine(players);
        return new PublicArticle(name, playerList, magazineName, acceptanceProbability, revisionProbability, rejectionProbability);
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", " + magazineName + ", " + acceptanceProbability;
    }
}
