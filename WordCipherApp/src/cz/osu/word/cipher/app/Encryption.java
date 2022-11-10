package cz.osu.word.cipher.app;

import cz.osu.word.cipher.app.exceptions.AnyTextToEncryptException;
import cz.osu.word.cipher.app.exceptions.IllegalMessageKeyException;

import java.util.Scanner;

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
        System.out.println("Zadejte zprávu, kterou chcete utajit:");
        System.out.println("Volte pouze znaky, které uznává jazyk český nebo slovenký!");
        setConsoleInputMsg();

        System.out.println("Zadejte tajný klíč (jeden znak):");
        setConsoleInputKey();

        encodeMessageByKey();
        System.out.println("Zašifrovaný text:" + encodedText);

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
            System.out.println("Zadejte zprávu, kterou chcete utajit:");
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
        }

    }

    private static void encodeMessageByKey() {
        String key = consoleInputKey;

        StringBuilder sb = new StringBuilder();
        mixCharsByKey(consoleInputMsg, key, sb);
        encodedText = sb.toString();
    }

    private static void mixCharsByKey(String msg, String key, StringBuilder sb) {

        for (int i = 0; i < msg.length(); i++) {
            int newCharValue = (int) key.charAt(0) + (int) msg.charAt(i);
            if (newCharValue > 382){
                newCharValue = newCharValue - 382;
            }
            key = String.valueOf((char) newCharValue);
            sb.append(key);
        }

    }

    /**
     * Resets class attributes.
     */
    private static void resetAttributes() {
        consoleInputMsg = null;
        consoleInputKey = null;
        encodedText = null;
    }
}
