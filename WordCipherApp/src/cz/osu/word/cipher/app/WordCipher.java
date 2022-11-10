package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.IllegalConsoleInputException;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.Utils.getConsoleInput;

public class WordCipher {
    private static String consoleInput;

    /**
     * Main program run method.
     */
    public static void run() {

        System.out.println("Vítejte!");
        System.out.println("Tato aplikace vám zašifruje nebo dešifruje Vaší tajnou zprávu.");

        listenConsoleInput();
    }

    //region Util methods
    private static void listenConsoleInput() {

        resetConsoleInput();
        System.out.println("Pro začátek zvolte 'š' pro šifrování nebo 'd' pro dešifrování zprávy.");

        consoleInput = getConsoleInput(
                new Scanner(System.in)
        );
        processConsoleInput();
    }

    /**
     * Decides to run encryption or decryption on user console input.
     */
    private static void processConsoleInput() {

        try {
            switch (consoleInput) {
                case "š" -> Encryption.run();
                case "d" -> Decryption.run();
                default -> throw new IllegalConsoleInputException(consoleInput);
            }
            listenConsoleInputExit();

        } catch (IllegalConsoleInputException e) {
            System.out.println(e.getMessage());
            System.out.println("Zvolte 'š' pro šifrování nebo 'd' pro dešifrování zprávy.");
            listenConsoleInput();

        }

    }

    private static void listenConsoleInputExit() {

        System.out.println("Chcete program opustit? (a/n)");
        consoleInput = getConsoleInput(
                new Scanner(System.in)
        );

        if (consoleInput.equals("a"))
            System.exit(1);
        else
            listenConsoleInput();

    }
    //endregion

    /**
     * Resets user console input, if program continues
     * after listenConsoleInputExit() is called.
     */
    private static void resetConsoleInput() {
        consoleInput = null;
    }
}
