package business.player;

import javax.print.Doc;

public class Master extends Engineer {

    public Master(String name, int pi) {
        super(name, pi);
    }

    @Override
    public void subtractPoints (int points) {
        this.pi -= (points / 2); // Redondeo hacia abajo automatico en caso de points impar.
                                 // Los estudiantes de master pierden la mitad de puntos.
    }

    @Override
    public void addPoints(int points) {
        this.pi += points;
    }

    @Override
    public void upGrade() {
        // Creo que ha de tener un return de tipo Master, del palo Master m = upGrade();
    }
}
