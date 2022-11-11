package cz.osu.word.cipher.app.utils;

public class Constants {

    private static final String alphabet = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿĀāĂăĄąĆćĈĉĊċČčĎďĐđĒēĔĕĖėĘęĚěĜĝĞğĠġĢģĤĥĦħĨĩĪīĬĭĮįİıĲĳĴĵĶķĸĹĺĻļĽľĿŀŁłŃńŅņŇňŉŊŋŌōŎŏŐőŒœŔŕŖŗŘřŚśŜŝŞşŠšŢţŤťŦŧŨũŪūŬŭŮůŰűŲųŴŵŶŷŸŹźŻżŽž";
    public static final int MIN_INT_CHAR_VALUE = 0;
    public static final int MAX_INT_CHAR_VALUE = alphabet.length();

    public static String[] getAlphabetLetters() {
        String[] ret = new String[alphabet.length()];

        for (int i = 0; i < alphabet.length(); i++) {
            ret[i] = String.valueOf(
                    alphabet.charAt(i)
            );
        }

        return ret;
    }

}
