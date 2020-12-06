package pl.sda.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;

class StringsUtilTest {

    @ParameterizedTest
    // given
    @CsvSource(value = {"test;TEST", "   jaVa   ;JAVA", "teST  ;TEST"}, delimiter = ';') // test - input / TEST - expected
    public void shouldTrimAndUpperCaseInput(String input, String expected) {
       // when
        String actual = StringsUtil.toUpperCase(input);

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    // given
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1, delimiter = ',' , lineSeparator = ";", encoding = "UTF-8") // pobranie danych z pliku
    public void shouldTrimAndUpperCaseInputCSVFile(String input, String expected) {
        // when
        String actual = StringsUtil.toUpperCase(input);

        // then
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullSource
    public void shouldBeBlankForNull(String input) {
        assertTrue(StringsUtil.isBlank(input));
    }

    @ParameterizedTest
    @EmptySource
    public void shouldBeBlankForEmptyString(String input) {
        assertTrue(StringsUtil.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldBeBlankForNullOrEmptyString(String input) {
        assertTrue(StringsUtil.isBlank(input));
    }

}