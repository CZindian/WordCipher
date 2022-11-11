package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.*;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.Constants.*;
import static cz.osu.word.cipher.app.utils.TextColors.*;
import static cz.osu.word.cipher.app.utils.Utils.*;

public class Decryption {

    //region Attributes
    private static String consoleInputMsg;
    private static String consoleInputKey;
    private static String decodedText;
    private static String[] letters;
    //endregion

    /**
     * Main decryption method.
     */
    public static void run() {

        //TODO
        letters = getAlphabetLetters();
        System.out.println(ANSI_GREEN + "Zadejte zprávu, kterou chcete rozšifrovat:" + ANSI_RESET);
        setConsoleInputMsg();

        System.out.println(ANSI_GREEN + "Zadejte tajný klíč (jeden znak):" + ANSI_RESET);
        setConsoleInputKey();

        decodeMessageByKey();
        System.out.println("Rozšifrovaný text: " + decodedText);

        resetAttributes();

    }

    /**
     * Loads user message to decrypt.
     */
    private static void setConsoleInputMsg() {

        try {
            consoleInputMsg = getConsoleInput(
                    new Scanner(System.in)
            );
            if (consoleInputMsg.length() == 0) {
                throw new AnyTextToDecryptException();
            }

        } catch (AnyTextToDecryptException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Zadejte zprávu, kterou chcete rozšifrovat:" + ANSI_RESET);
            setConsoleInputMsg();

        }

    }

    /**
     * Loads user secret key.
     */
    private static void setConsoleInputKey() {

        try {
            consoleInputKey = getConsoleInput(
                    new Scanner(System.in)
            );
            checkValidity(consoleInputKey);

        } catch (IllegalMessageKeyException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Zadejte tajný klíč (jeden znak):" + ANSI_RESET);
            setConsoleInputKey();

        } catch (UnsupportedMessageKeyException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Zadejte tajný klíč z běžné abecedy:" + ANSI_RESET);
            setConsoleInputKey();

        }

    }

    /**
     * Decodes message by loaded key.
     */
    private static void decodeMessageByKey() {

        String key = consoleInputKey;
        StringBuilder sb = new StringBuilder();

        try {
            mixCharsByKey(consoleInputMsg, key, sb);
            decodedText = sb.toString();

        } catch (UnsupportedAlphabetCharacterException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Použijte validní klíč." + ANSI_RESET);
            setConsoleInputMsg();

        }

    }

    /**
     * Decrypts message by key against "auto key from encrypted message" cipher rules.
     *
     * @param msg encrypted message
     * @param key secret key
     * @param sb  StringBuilder instance to join encrypted characters in
     */
    private static void mixCharsByKey(String msg, String key, StringBuilder sb)
            throws UnsupportedAlphabetCharacterException {

        for (int i = 0; i < msg.length(); i++) {

            int newCharValue = getLetterIdx(msg.charAt(i), letters) - getLetterIdx(key.charAt(0), letters);
            if (newCharValue <= MIN_INT_CHAR_VALUE) {
                newCharValue = newCharValue + MAX_INT_CHAR_VALUE;
            }

            if (newCharValue >= MAX_INT_CHAR_VALUE) {
                newCharValue = newCharValue - MAX_INT_CHAR_VALUE;
            }

            key = String.valueOf(msg.charAt(i));
            sb.append(getAlphabetLetter(newCharValue, letters));

        }

    }

    /**
     * Resets class attributes, when they are no longer needed.
     */
    private static void resetAttributes() {

        consoleInputMsg = null;
        consoleInputKey = null;
        decodedText = null;

    }

}
