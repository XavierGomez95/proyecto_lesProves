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

    /**
     * Method that represents the operation followed by each thread. Depending on the type of trial on which
     * each player depends, the run() method does one thing or another.
     */
    @Override
    public void run() {
        if (trial instanceof PhDefense phd) {
            info = infoConcatName();
            if (player.successfulPhD(phd.calculateFormula())) info = phdPassed();
            else info = phdNotPassed();
        } else if (trial instanceof PublicArticle p) {
            info = infoConcatName();
            info += " is submitting...";

            if (p.isArticlePublished()) info = acceptedArticle(p);
            else {

                info += " Revisions...";
                if (p.isReviewAccepted()) {

                    if (p.isArticleRejected()) info = rejectedArticle(p);
                    else info = acceptedArticle(p);

                } else {
                    info = rejectedArticle(p);
                }

            }
        } else if (trial instanceof StudyMaster s) {
            int creditsPassed = s.approvedCredits();
            info = infoConcatName();
            info += " passed " + creditsPassed + "/" + s.getNumCredits() + " ECTS.";

            if (s.isMasterPassed(creditsPassed)) info = masterPassed();
            else info = masterNotPassed();
        }
    }

    /**
     * @return A String with information about the execution of trials (Master not passed).
     */
    private String masterNotPassed() {
        player.subtractPoints(3);
        info += " Sorry... PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    /**
     * @return A String with information about the execution of trials (Master passed).
     */
    private String masterPassed() {
        if (player.isTypeEngineer()) {
            player.addPoints(5);
            info += " Congrats! PI count: " + player.getPi();
            player.upGrade();
        } else {
            player.addPoints(3);
            info += " Congrats! PI count: " + player.getPi();
            if (player.isUpgradeable()) player.upGrade(); // REVISAR ESTO
        }
        return info;
    }

    /**
     * @return A String with information about the execution of trials (PhD Defense unsuccessfully done).
     */
    private String phdNotPassed() {
        player.subtractPoints(5);
        info += " was unsuccessful. Sorry... PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    /**
     * @return A String with information about the execution of trials (PhD Defense successfully done).
     */
    private String phdPassed() {
        if (player.isTypeEngineer()) {
            player.addPoints(5);
            info += " was successful. Congrats! PI count: " + player.getPi();
            player.upGrade();
        } else {
            player.addPoints(5);
            info += " was successful. Congrats! PI count: " + player.getPi();
            if (player.isUpgradeable()) player.upGrade();
        }
        return info;
    }

    /**
     * @return A String with the name depending on the type of studies the player has.
     */
    private String infoConcatName() {
        if (player.isTypeMaster()) info = "\t" + player.getType() + " " + player.getName();
        else if (player.isTypeDoctor()) info = "\t" + player.getName() + ", PhD.";
        else if (player.isTypeEngineer()) info = "\t" + player.getName();
        return info;
    }

    /**
     * @param p PublicArticle class object.
     * @return A String with information about the execution of trials (rejection of the article).
     */
    private String rejectedArticle(PublicArticle p) {
        int pi = p.penaltyPi();
        player.subtractPoints(pi);
        //info += player.getName() + " is submitting..." + " Rejected. PI count: " + player.getPi();
        info += " Revisions... Rejected. PI count: " + player.getPi();
        if (player.isKillable()) {
            player.killPlayer();
            info += " - Disqualified!";
        }
        return info;
    }

    /**
     * @param p PublicArticle class object.
     * @return A String with information about the execution of trials (acceptance of the article).
     */
    private String acceptedArticle(PublicArticle p) {
        int pi = p.rewardPi();
        player.addPoints(pi);
        info += " Accepted! PI count: " + player.getPi();
        if (player.isUpgradeable()) player.upGrade();
        return info;
    }

    /**
     * @return information of the thread.
     */
    public String getInfo() {
        return info;
    }
}
