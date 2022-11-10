package cz.osu.word.cipher.app.exceptions;

public class IllegalMessageKeyException extends Exception {
    public IllegalMessageKeyException() {
        super("\t-heslo musí obsahovat právě jeden znak");
    }
}
