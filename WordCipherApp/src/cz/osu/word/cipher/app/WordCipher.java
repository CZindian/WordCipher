package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.IllegalConsoleInputException;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.TextColors.*;
import static cz.osu.word.cipher.app.utils.Utils.getConsoleInput;

public class WordCipher {
    private static String consoleInput;

    /**
     * Main program run method.
     */
    public static void run() {

        System.out.println(ANSI_GREEN + "Vítejte!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Tato aplikace vám zašifruje nebo dešifruje Vaší tajnou zprávu." + ANSI_RESET);

        listenConsoleInput();
    }

    //region Util methods
    private static void listenConsoleInput() {

        resetConsoleInput();
        System.out.println(ANSI_YELLOW + "Pro začátek zvolte 'š' pro šifrování nebo 'd' pro dešifrování zprávy." + ANSI_RESET);

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
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Zvolte 'š' pro šifrování nebo 'd' pro dešifrování zprávy." + ANSI_RESET);
            listenConsoleInput();

        }

    }

    private static void listenConsoleInputExit() {

        System.out.println(ANSI_RED + "Chcete program opustit? (a/n)" + ANSI_RED);
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
