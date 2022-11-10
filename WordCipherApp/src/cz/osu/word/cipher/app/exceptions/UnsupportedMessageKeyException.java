package cz.osu.word.cipher.app.exceptions;

public class UnsupportedMessageKeyException extends Exception {
    public UnsupportedMessageKeyException() {
        super("\t-klíč nepatří do standartní české abecedy");
    }
}
