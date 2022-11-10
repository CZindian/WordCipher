package cz.osu.word.cipher.app.utils;

import cz.osu.word.cipher.app.exceptions.IllegalMessageKeyException;

import java.util.Scanner;

public class Utils {

    public static String getConsoleInput(Scanner scanner) {
        String input;
        input = scanner.nextLine();
        return input;
    }

    public static void checkValidity(String input) throws IllegalMessageKeyException {
        if (input.length() != 1) {
            throw new IllegalMessageKeyException();
        }
    }

}
