package cz.osu.word.cipher.app.utils;

import cz.osu.word.cipher.app.exceptions.UnsupportedMessageKeyException;
import cz.osu.word.cipher.app.exceptions.IllegalMessageKeyException;

import java.util.Scanner;

import static cz.osu.word.cipher.app.utils.Constants.MAX_INT_CHAR_VALUE;

public class Utils {

    public static String getConsoleInput(Scanner scanner) {
        String input;
        input = scanner.nextLine();
        return input;
    }

    public static void checkValidity(String input)
            throws IllegalMessageKeyException, UnsupportedMessageKeyException {
        if (input.length() != 1) {
            throw new IllegalMessageKeyException();
        }
        if ((int) input.charAt(0) > MAX_INT_CHAR_VALUE) {
            throw new UnsupportedMessageKeyException();
        }
    }

}
