package cz.osu.word.cipher.app.exceptions;

/**
 * Should be called, when program catches any text to decode.
 */
public class AnyTextToDecryptException extends Exception {
    public AnyTextToDecryptException() {
        super("\t-nezadali jste zprávu pro dešifrování");
    }
}
