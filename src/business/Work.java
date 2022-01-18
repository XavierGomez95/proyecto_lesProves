package business;

import business.player.Player;
import business.trial.*;

import javax.print.attribute.standard.MediaSize;

public class Work implements Runnable {
    private Player player;
    private Trial trial;
    private String info;
    //private static int sumPlayersPi = 0;


    public Work(Player player, Trial trial) {
        this.player = player;
        this.trial = trial;
        info = "";
    }

    @Override
    public void run() {
        if (trial instanceof BudgedRequest b) {
            //Hacer X cosa
        }

        else if (trial instanceof PhDefense ph) {
            //if (player.piSum() > ph.calculateFormula()); // TEMPORAL
        }

        else if (trial instanceof PublicArticle p) {
            info = player.getName() + "is submitting..."; // falta el tipo de estudiante

            boolean articlePublished = p.isArticlePublished();
            if (articlePublished) info += " Accepted! PI count: ";
            else {

                boolean reviewAccepted = p.isReviewAccepted(false);
                if (reviewAccepted) info += " Revisions...";

                else {
                    if (p.isRejected(false)) info = player.getName() + "is submitting..." + " Rejected. PI count: ";
                    else info += " Accepted PI count: ";
                }

            }
        }

        else if (trial instanceof StudyMaster s) {
            // Hacer X cosa
        }
//instance of phdefense
    }
    public void stopWork(){

    }
}
