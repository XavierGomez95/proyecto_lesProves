package presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;

    /** Global variable that stores a code that generates bold letters. */
    private static final String TEXT_BOLD = "\033[0;1m";
    /** Global variable that stores a code that generates letters in red color. */
    private static final String COLOR_RED = "\u001B[31m";
    /** Global variable that stores a code that stops the generation of letters in red color. */
    private static final String RESET_COLOR = "\u001B[0m";

    /** Instantiates a new constructor */
    public Menu () {
        this.sc = new Scanner(System.in);
    }


    /** This method is called to show the general menu options on the screen. */
    public void showMenu () {
        System.out.println("--- The Factory ---" + System.lineSeparator() +
                System.lineSeparator() +
                "1. Show progress" + System.lineSeparator() +
                "2. Exit" + System.lineSeparator());
    }

    /** This method is called to show the trial menu options on the screen. */
    public void showTrialMenu () {
        System.out.println("--- The Factory ---" + System.lineSeparator() +
                System.lineSeparator() +
                "1. Show progress" + System.lineSeparator() +
                "2. Exit" + System.lineSeparator());
    }

    /** This method is called to show the trial menu options on the screen. */
    public void deleteTrialMenu () {
        System.out.println("--- The Factory ---" + System.lineSeparator() +
                System.lineSeparator() +
                "1. Show progress" + System.lineSeparator() +
                "2. Exit" + System.lineSeparator());
    }

    /** This method is called to show the trial menu options on the screen. */
    public void showList (List<String> list) {
        for(int i = 0; i < list.size(); i++) {
            //
        }
    }

    /**
     * This method request information from the user and return the correct input value (integer data type).
     * {@link #errorInput(String)}.
     *
     * @param inputOption It is a String variable that we use to ask a user for information.
     * @return A value of primitive type integer entered by the user.
     */
    public int askOptionInteger (String inputOption) {
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
    public String askOptionString (String inputOption) {
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
    public void errorInput (String message) {
        System.out.println(TEXT_BOLD + COLOR_RED + System.lineSeparator() + message
                + System.lineSeparator() + RESET_COLOR);
    }


    /**
     * Displays a message.
     *
     * @param message contains the message to be displayed.
     */
    public void showMessage (String message) {
        System.out.print(message);
    }


    /** It generates a new line space. */
    public void createNewLine() {
        System.out.println();
    }
}
