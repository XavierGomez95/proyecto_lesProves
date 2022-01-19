package business;

import business.player.Player;
import business.trial.*;

public class Work implements Runnable {
    private Player player;
    private Trial trial;
    private String info;


    public Work(Player player, Trial trial) {
        this.player = player;
        this.trial = trial;
        info = "";
    }

    @Override
    public void run() {
        if (trial instanceof PhDefense phd) {
            info = infoConcatName();
            if (player.successfulPhD(phd.calculateFormula())) info = phdPassed();
            else info = phdNotPassed();
        }

        else if (trial instanceof PublicArticle p) {
            info = infoConcatName();
            info += "is submitting...";

            boolean articlePublished = p.isArticlePublished();
            if (articlePublished) info = acceptedArticle(p);
            else {
                boolean reviewAccepted = p.isReviewAccepted(false);
                if (reviewAccepted) info += " Revisions...";

                else {
                    if (p.isRejected(false)) info = rejectedArticle(p);
                    else info = acceptedArticle(p);
                }
            }
        }

        else if (trial instanceof StudyMaster s) {
            int creditsPassed = s.approvedCredits();
            info = infoConcatName();
            info += " passed " + creditsPassed + "/" + s.getNumCredits() + " ECTS.";

            if (s.isMasterPassed(creditsPassed)) info = masterPassed();
            else info = masterNotPassed();
        }
//instance of phdefense
    }

    private String masterNotPassed() {
        player.subtractPoints(3);
        info += " Sorry... PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    private String masterPassed() {
        if (player.isTypeEngineer()) player.upGrade();
        else player.addPoints(3);
        info += " Congrats! PI count: " + player.getPi();
        return info;
    }

    private String phdNotPassed() {
        player.subtractPoints(5);
        info += " was unsuccessful. Sorry... PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    private String phdPassed() {
        if (player.isTypeEngineer()) player.upGrade();
        else player.addPoints(5);
        info += " was successful. Congrats! PI count: " + player.getPi();
        return info;
    }

    private String infoConcatName() {
        if (player.isTypeMaster()) info = player.getType() + " " + player.getName();
        else if (player.isTypeDoctor()) info = player.getName() + ", PhD. ";
        else if (player.isTypeEngineer()) info = player.getName();
        return info;
    }

    private String rejectedArticle(PublicArticle p) {
        int pi = p.penaltyPi();
        player.subtractPoints(pi);
        info += player.getName() + "is submitting..." + " Rejected. PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    private String acceptedArticle(PublicArticle p) {
        int pi = p.rewardPi();
        player.addPoints(pi);
        if (player.isUpgradeable()) player.upGrade();
        info += " Accepted! PI count: " + player.getPi();
        return info;
    }

    public void stopWork(){

    }
}
