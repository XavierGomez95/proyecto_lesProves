package presentation;

import business.trial.Trial;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;

    /**
     * Global variable that stores a code that generates bold letters.
     */
    private static final String TEXT_BOLD = "\033[0;1m";
    /**
     * Global variable that stores a code that generates letters in red color.
     */
    private static final String COLOR_RED = "\u001B[31m";
    /**
     * Global variable that stores a code that stops the generation of letters in red color.
     */
    private static final String RESET_COLOR = "\u001B[0m";

    /**
     * Instantiates a new constructor
     */
    public Menu() {
        this.sc = new Scanner(System.in);
    }


    /**
     * This method is called to show the format menu options on the screen.
     */
    public void showFormatMenu() { // 4.1
        System.out.println(System.lineSeparator() +
                "I) People’s Front of Engineering (CSV)" + System.lineSeparator() +
                "II) Engineering People’s Front (JSON)" + System.lineSeparator());
    }

    /**
     * This method is called to show the role menu options on the screen.
     */
    public void showRoleMenu() { // 4.2
        System.out.println(System.lineSeparator() +
                "A) The Composer" + System.lineSeparator() +
                "B) This year’s Conductor" + System.lineSeparator());
    }

    /**
     * This method is called to show the manager menu options on the screen.
     */
    public void showManagerMenu() { // 4.3
        System.out.println(System.lineSeparator() +
                "1) Manage Trials" + System.lineSeparator() +
                "2) Manage Editions" + System.lineSeparator() +
                System.lineSeparator() +
                "3) Exit" + System.lineSeparator());
    }

    /**
     * This method is called to show the trial menu options on the screen.
     */
    public void showTrialsMenu() { // 4.3.1
        System.out.println(System.lineSeparator() +
                "a) Create Trial" + System.lineSeparator() +
                "b) List Trials" + System.lineSeparator() +
                "c) Delete Trial" + System.lineSeparator() +
                System.lineSeparator() +
                "e) Back" + System.lineSeparator());
    }

    /**
     * This method is called to show the editions menu options on the screen.
     */
    public void showEditionsMenu() { // 4.3.2
        System.out.println(System.lineSeparator() +
                "a) Create Edition" + System.lineSeparator() +
                "b) List Editions" + System.lineSeparator() +
                "c) Duplicate Edition" + System.lineSeparator() +
                "d) Delete Edition" + System.lineSeparator() +
                System.lineSeparator() +
                "e) Back" + System.lineSeparator());
    }

    /**
     *
     */
    public void showTrialTypesMenu() { // 4.3.1.1
        System.out.println("--- Trial types ---" + System.lineSeparator() +
                " 1) Paper publication" + System.lineSeparator() +
                " 2) Master studies" + System.lineSeparator() +
                " 3) Doctoral thesis defense" + System.lineSeparator() +
                " 4) Budget request" + System.lineSeparator());
    }

    /**
     * This method is called to show the delete trial menu on the screen.
     */
    public void showDeleteTrialMenu() {
        System.out.println();
    }


    /**
     * This method request information from the user and return the correct input value (integer data type).
     * {@link #errorInput(String)}.
     *
     * @param inputOption It is a String variable that we use to ask a user for information.
     * @return A value of primitive type integer entered by the user.
     */
    public int askInteger(String inputOption) {
        int number;

        while (true) {
            try {
                System.out.print(inputOption);
                number = sc.nextInt();
                return number;
            } catch (InputMismatchException e) {
                errorInput("Incorrect input! Enter a number.");
            } finally {
                sc.nextLine(); // Remove the \n from the buffer
            }
        }
    }

    /**
     * This method request information from the user and return the correct input value (String data type).
     * {@link #errorInput(String)}.
     *
     * @param inputOption It is a String variable that we use to ask a user for information.
     * @return A value of non primitive type String entered by the user.
     */
    public String askString(String inputOption) {
        String answear;

        while (true) {
            try {
                System.out.print(inputOption);
                answear = sc.nextLine();
                return answear;
            } catch (InputMismatchException e) {
                errorInput("Error, incorrect input data.");
            }
        }
    }

    /**
     * This method display an error message.
     *
     * @param message String that contains a message to be shown.
     */
    public void errorInput(String message) {
        System.out.println(TEXT_BOLD + COLOR_RED + System.lineSeparator() + message
                + System.lineSeparator() + RESET_COLOR);
    }


    public void showTittle() {
        System.out.println(System.lineSeparator() +
                " _____ _             _____      _       _" + System.lineSeparator() +
                "/__   \\ |__   ___   /__   \\_ __(_) __ _| |___" + System.lineSeparator() +
                "  / /\\/ '_ \\ / _ \\    / /\\/ '__| |/ _` | / __|" + System.lineSeparator() +
                " / /  | | | | ___/   / /  | |  | | (_| | \\__ \\" + System.lineSeparator() +
                " \\/   |_| |_|\\___|   \\/   |_|  |_|\\__,_|_|___/" + System.lineSeparator());
    }


    /**
     * Displays a message.
     *
     * @param message contains the message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }


    /**
     * It generates a new line space.
     */
    public void createNewLine() {
        System.out.println();
    }

    /**
     * @param list
     */
    public void menuTrials(List<String> list) {
        int max = list.size();
        for (int i = 0; i < max; i++) {
            System.out.println(i + 1 + ") " + list.get(i));
        }
        System.out.println();
        System.out.println(max + 1 + ") Back" + System.lineSeparator());
    }

    public void showlist(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public void menuEditions(List<String> list) {
        int max = list.size();
        System.out.println("\t--- Trials ---" + System.lineSeparator());
        for (int i = 0; i < max; i++) {
            System.out.println("\t" + (i + 1) + ") " + list.get(i));
        }
    }

    public void showListEditionByYear(List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
    }
}
