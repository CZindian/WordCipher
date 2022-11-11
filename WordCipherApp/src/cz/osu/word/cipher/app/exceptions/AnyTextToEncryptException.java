package cz.osu.word.cipher.app.exceptions;

public class AnyTextToEncryptException extends Exception {
    public AnyTextToEncryptException() {
        super("\t-není zadaný žáden text, který by mohl být šifrován");
    }
}
