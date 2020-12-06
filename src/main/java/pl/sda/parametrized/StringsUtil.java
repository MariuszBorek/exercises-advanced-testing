package pl.sda.parametrized;

public class StringsUtil {
    public static String toUpperCase(final String input) {
        return input.trim().toUpperCase();
    }

    public static boolean isBlank(final String input) {
        return input == null || input.trim().isEmpty();
    }
}
