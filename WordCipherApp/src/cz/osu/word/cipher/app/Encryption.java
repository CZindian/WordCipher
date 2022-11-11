package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.AnyTextToEncryptException;
import cz.osu.word.cipher.app.exceptions.UnsupportedMessageKeyException;
import cz.osu.word.cipher.app.exceptions.IllegalMessageKeyException;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.Constants.MAX_INT_CHAR_VALUE;
import static cz.osu.word.cipher.app.utils.TextColors.*;
import static cz.osu.word.cipher.app.utils.Utils.checkValidity;
import static cz.osu.word.cipher.app.utils.Utils.getConsoleInput;

public class Encryption {

    //region Attributes
    private static String consoleInputMsg;
    private static String consoleInputKey;
    private static String encodedText;
    //endregion

    /**
     * Main encryption method.
     */
    public static void run() {

        System.out.println(ANSI_GREEN + "Zadejte zprávu, kterou chcete utajit:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Volte pouze znaky, které uznává jazyk český nebo slovenký!" + ANSI_RESET);
        setConsoleInputMsg();

        System.out.println(ANSI_GREEN + "Zadejte tajný klíč (jeden znak):" + ANSI_GREEN);
        setConsoleInputKey();

        encodeMessageByKey();
        System.out.println(ANSI_YELLOW + "Pozor! Bílé znaky za ':' taktéž spadají do zašifrovaného textu!" + ANSI_RESET);
        System.out.println("Zašifrovaný text:" + encodedText);

        resetAttributes();

    }

    /**
     * Loads user message to encrypt.
     */
    private static void setConsoleInputMsg() {

        try {
            consoleInputMsg = getConsoleInput(
                    new Scanner(System.in)
            );
            if (consoleInputMsg.length() == 0) {
                throw new AnyTextToEncryptException();
            }

        } catch (AnyTextToEncryptException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Zadejte zprávu, kterou chcete utajit:" + ANSI_YELLOW);
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
            System.out.println(ANSI_GREEN+"Zadejte tajný klíč (jeden znak):"+ANSI_RESET);
            setConsoleInputKey();

        } catch (UnsupportedMessageKeyException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "Zadejte tajný klíč z běžné abecedy:" + ANSI_RESET);
            setConsoleInputKey();

        }

    }

    /**
     * Encodes message by loaded key.
     */
    private static void encodeMessageByKey() {

        String key = consoleInputKey;

        StringBuilder sb = new StringBuilder();
        mixCharsByKey(consoleInputMsg, key, sb);
        encodedText = sb.toString();

    }

    /**
     * Encrypts message by key against "auto key from encrypted message" cipher rules.
     * @param msg encrypted message
     * @param key secret key
     * @param sb StringBuilder instance to join encrypted characters in
     */
    private static void mixCharsByKey(String msg, String key, StringBuilder sb) {

        for (int i = 0; i < msg.length(); i++) {

            int newCharValue = (int) key.charAt(0) + (int) msg.charAt(i);
            if (newCharValue > MAX_INT_CHAR_VALUE) {
                newCharValue = newCharValue - MAX_INT_CHAR_VALUE;
            }

            key = String.valueOf((char) newCharValue);
            sb.append(key);

        }

    }

    /**
     * Resets class attributes, when they are no longer needed.
     */
    private static void resetAttributes() {

        consoleInputMsg = null;
        consoleInputKey = null;
        encodedText = null;

    }

}
