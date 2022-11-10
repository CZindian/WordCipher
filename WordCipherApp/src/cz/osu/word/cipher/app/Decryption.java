package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.AnyTextToEncryptException;
import cz.osu.word.cipher.app.exceptions.IllegalMessageKeyException;
import cz.osu.word.cipher.app.exceptions.UnsupportedMessageKeyException;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.Constants.MAX_INT_CHAR_VALUE;
import static cz.osu.word.cipher.app.utils.Constants.MIN_INT_CHAR_VALUE;
import static cz.osu.word.cipher.app.utils.Utils.checkValidity;
import static cz.osu.word.cipher.app.utils.Utils.getConsoleInput;

public class Decryption {

    //region Attributes
    private static String consoleInputMsg;
    private static String consoleInputKey;
    private static String decodedText;
    //endregion

    /**
     * Main encryption method.
     */
    public static void run() {

        System.out.println("Zadejte zprávu, kterou chcete rozšifrovat:");
        setConsoleInputMsg();

        System.out.println("Zadejte tajný klíč (jeden znak):");
        setConsoleInputKey();

        decodeMessageByKey();
        System.out.println("Rozšifrovaný text: " + decodedText);

        resetAttributes();

    }

    private static void setConsoleInputMsg() {

        try {
            consoleInputMsg = getConsoleInput(
                    new Scanner(System.in)
            );
            if (consoleInputMsg.length() == 0) {
                throw new AnyTextToEncryptException();
            }

        } catch (AnyTextToEncryptException e) {
            System.out.println(e.getMessage());
            System.out.println("Zadejte zprávu, kterou chcete rozšifrovat:");
            setConsoleInputMsg();

        }

    }

    private static void setConsoleInputKey() {

        try {
            consoleInputKey = getConsoleInput(
                    new Scanner(System.in)
            );
            checkValidity(consoleInputKey);

        } catch (IllegalMessageKeyException e) {
            System.out.println(e.getMessage());
            System.out.println("Zadejte tajný klíč (jeden znak):");
            setConsoleInputKey();

        } catch (UnsupportedMessageKeyException e) {
            System.out.println(e.getMessage());
            System.out.println("Zadejte tajný klíč z běžné abecedy:");
            setConsoleInputKey();

        }

    }

    private static void decodeMessageByKey() {

        String key = consoleInputKey;

        StringBuilder sb = new StringBuilder();
        mixCharsByKey(consoleInputMsg, key, sb);
        decodedText = sb.toString();

    }

    private static void mixCharsByKey(String msg, String key, StringBuilder sb) {

        for (int i = 0; i < msg.length(); i++) {

            int newCharValue = (int) msg.charAt(i) - (int) key.charAt(0);
            if (newCharValue < MIN_INT_CHAR_VALUE) {
                newCharValue = newCharValue + MAX_INT_CHAR_VALUE;
            }

            key = String.valueOf(msg.charAt(i));
            sb.append((char) newCharValue);

        }

    }

    /**
     * Resets class attributes.
     */
    private static void resetAttributes() {

        consoleInputMsg = null;
        consoleInputKey = null;
        decodedText = null;

    }

}
