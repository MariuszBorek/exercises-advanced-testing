package pl.sda.parametrized;

public class NumbersUtil {

    public static boolean isOdd(final int number) {
        return number % 2 != 0;
    }

    public static float divide(final float a, final float b) {
        if (b == 0) {
            throw new IllegalArgumentException("dividend can't be 0");
        }
        return a / b;
    }

}
