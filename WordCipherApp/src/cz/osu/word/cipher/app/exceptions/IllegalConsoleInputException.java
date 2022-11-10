package cz.osu.word.cipher.app.exceptions;

/**
 * Should be called, when program catches unexpected console value.
 */
public class IllegalConsoleInputException extends Exception {
    public IllegalConsoleInputException(String input) {
        super("\t-neočekávaná hodnota: '" + input + "'");
    }
}
