package cz.osu.word.cipher.app.exceptions;

public class UnsupportedAlphabetCharacterException extends Exception {
    public UnsupportedAlphabetCharacterException(char c) {
        super("\t-znak '" + c + "' není součástí abecedy programu");
    }

    public UnsupportedAlphabetCharacterException(int value) {
        super("\t-znak s hodnotou '" + value + "' není součástí abecedy programu");
    }
}
