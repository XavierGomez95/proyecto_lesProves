/*
 ************************************************************
 * @Purpose: Object-oriented programming project.
 * @Authors: Iris Querol Armero & Xavier Gomez de la Torre
 * @Creation date: 28-12-2021
 * @Last modification date: 21-01-2022
 ************************************************************
 */

import presentation.Controller;
import presentation.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Controller controller = new Controller(menu);

        controller.run();
    }
}
