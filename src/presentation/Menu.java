package presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;


    private static final String TEXT_BOLD = "\033[0;1m";
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_GREEN = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";

    /**
     * Instantiates a new constructor
     */
    public Menu() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Show the format menu options on the screen.
     */
    public void showFormatMenu() { // 4.1
        System.out.println(new StringBuilder(System.lineSeparator())
                .append("\t").append("I) People’s Front of Engineering (CSV)").append(System.lineSeparator())
                .append("\t").append("II) Engineering People’s Front (JSON)").append(System.lineSeparator()));
    }

    /**
     * Show the role menu options on the screen.
     */
    public void showRoleMenu() { // 4.2
        System.out.println(new StringBuilder(System.lineSeparator())
                .append("\t").append("A) The Composer").append(System.lineSeparator())
                .append("\t").append("B) This year’s Conductor").append(System.lineSeparator()));
    }

    /**
     * Show the manager menu options on the screen.
     */
    public void showManagerMenu() { // 4.3
        System.out.println(new StringBuilder(System.lineSeparator())
                .append("\t").append("1) Manage Trials").append(System.lineSeparator())
                .append("\t").append("2) Manage Editions").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("\t").append("3) Exit").append(System.lineSeparator()));
    }

    /**
     * Show the trial menu options on the screen.
     */
    public void showTrialsMenu() { // 4.3.1
        System.out.println(new StringBuilder(System.lineSeparator())
                .append("\t").append("a) Create Trial").append(System.lineSeparator())
                .append("\t").append("b) List Trials").append(System.lineSeparator())
                .append("\t").append("c) Delete Trial").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("\t").append("d) Back").append(System.lineSeparator()));
    }

    /**
     * Show the edition's menu options on the screen.
     */
    public void showEditionsMenu() { // 4.3.2
        System.out.println(new StringBuilder(System.lineSeparator())
                .append("\t").append("a) Create Edition").append(System.lineSeparator())
                .append("\t").append("b) List Editions").append(System.lineSeparator())
                .append("\t").append("c) Duplicate Edition").append(System.lineSeparator())
                .append("\t").append("d) Delete Edition").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("\t").append("e) Back").append(System.lineSeparator()));
    }

    /**
     * Show the Trial type's menu options on the screen.
     */
    public void showTrialTypesMenu() { // 4.3.1.1
        System.out.println(new StringBuilder("\t").append("--- Trial types ---").append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("\t").append(" 1) Paper publication").append(System.lineSeparator())
                .append("\t").append(" 2) Master studies").append(System.lineSeparator())
                .append("\t").append(" 3) Doctoral thesis defense").append(System.lineSeparator())
                .append("\t").append(" 4) Budget request").append(System.lineSeparator()));
    }

    /**
     * This method request information from the user and return the correct input value (integer data type).
     * {@link #showError(String)}.
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
                showError(new StringBuilder("Incorrect input! Enter a number.").append(System.lineSeparator()).toString());
            } finally {
                sc.nextLine(); // Remove the \n from the buffer
            }
        }
    }

    /**
     * This method request information from the user and return the correct input value (String data type).
     * {@link #showError(String)}.
     *
     * @param inputOption It is a String variable that we use to ask a user for information.
     * @return A value of non primitive type String entered by the user.
     */
    public String askString(String inputOption) {
        String answer;

        while (true) {
            try {
                System.out.print(inputOption);
                answer = sc.nextLine();
                return answer;
            } catch (InputMismatchException e) {
                showError("Error, incorrect input data.");
            }
        }
    }

    /**
     * This method display an error message.
     * Is displayed on the screen in red and bold.
     *
     * @param message String that contains a message to be shown.
     */
    public void showError(String message) {
        System.out.println(TEXT_BOLD + COLOR_RED + System.lineSeparator() + message + RESET_COLOR);
    }

    /**
     * String that is displayed on the screen in green and bold.
     *
     * @param message String message
     */
    public void showSuccess(String message) {
        System.out.println(TEXT_BOLD + COLOR_GREEN + message + RESET_COLOR);
    }

    /**
     * Displays "THE TRICKS" tittle.
     */
    public void showTittle() {
        System.out.println(new StringBuilder(System.lineSeparator())
                .append(" _____ _             _____      _       _").append(System.lineSeparator())
                .append("/__   \\ |__   ___   /__   \\_ __(_) __ _| |___").append(System.lineSeparator())
                .append("  / /\\/ '_ \\ / _ \\    / /\\/ '__| |/ _` | / __|").append(System.lineSeparator())
                .append(" / /  | | | | ___/   / /  | |  | | (_| | \\__ \\").append(System.lineSeparator())
                .append(" \\/   |_| |_|\\___|   \\/   |_|  |_|\\__,_|_|___/").append(System.lineSeparator()));
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
            System.out.println(new StringBuilder("\t" + (i + 1)).append(") ").append(list.get(i)));
        }
        System.out.println();
        System.out.println(new StringBuilder("\t" + (max + 1)).append(") Back").append(System.lineSeparator()));
    }

    /**
     * @param list that is displayed on the screen as a menu.
     */
    public void menuEditions(List<String> list) {
        int max = list.size();
        System.out.println("\t--- Trials ---" + System.lineSeparator());
        for (int i = 0; i < max; i++) {
            System.out.println(new StringBuilder("\t" + (i + 1)).append(") ").append(list.get(i)));
        }
    }

    /**
     * @param list that is displayed on the screen.
     */
    public void showListEditionByYear(List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
    }
}
