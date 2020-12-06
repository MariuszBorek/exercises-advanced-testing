package pl.sda.parametrized;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumbersUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7, 57, 933})
    public void shouldReturnTrueForOddNumber(int input) {
        assertTrue(NumbersUtil.isOdd(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 32, 76, 578, 9336})
    public void shouldReturnFalseForEvenNumber(int input) {
        assertFalse(NumbersUtil.isOdd(input));
    }

    @ParameterizedTest
    @MethodSource(value = "provideNumbersWithInfoAboutParity")
    public void shouldReturnExpectedValueForGivenNumber(int input, boolean expected) {
        assertEquals(expected, NumbersUtil.isOdd(input));

    }

    @ParameterizedTest
    @ArgumentsSource(value = NumberWithInfoAboutParityProvider.class)
    public void shouldReturnExpectedValueForGivenNumberWithArgumentSource(int input, boolean expected) {
        assertEquals(expected, NumbersUtil.isOdd(input));

    }

    // dostarcza info na temat lliczb i ich nieparzystości
    private static Stream<Arguments> provideNumbersWithInfoAboutParity() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(2, false),
                Arguments.of(13, true),
                Arguments.of(28, false)
        );
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        Assertions
                .assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> NumbersUtil.divide(11, 0))
                .withMessage("dividend can't be 0");
    }

}