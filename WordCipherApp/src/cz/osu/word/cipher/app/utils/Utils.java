package cz.osu.word.cipher.app.utils;

import cz.osu.word.cipher.app.exceptions.UnsupportedAlphabetCharacterException;
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
    }

    /**
     * @param newCharValue index of character from supported alphabet array
     * @param letters list of supported alphabet characters
     * @return current letter from list of supported alphabet characters
     * @throws UnsupportedAlphabetCharacterException is thrown, when there is character, which is not supported
     */
    public static String getAlphabetLetter(int newCharValue, String[] letters)
            throws UnsupportedAlphabetCharacterException {

        String ret = null;

        for (int i = 0; i < letters.length; i++) {
            if (newCharValue == i) {
                ret = letters[i];
                break;
            }
        }

        if (ret == null) {
            throw new UnsupportedAlphabetCharacterException(newCharValue);
        }

        return ret;

    }

    /**
     * @param c single character
     * @param letters list of supported alphabet characters
     * @return current index of character from list of supported alphabet characters
     * @throws UnsupportedAlphabetCharacterException is thrown, when there is character, which is not supported
     */
    public static int getLetterIdx(char c, String[] letters) throws UnsupportedAlphabetCharacterException {

        String stringC = String.valueOf(c);
        int ret = -1;

        for (int i = 0; i < letters.length; i++) {
            if (stringC.equals(letters[i])) {
                ret = i;
                break;
            }
        }

        if (ret == -1) {
            throw new UnsupportedAlphabetCharacterException(c);
        }

        return ret;

    }

}
