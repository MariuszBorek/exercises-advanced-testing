package pl.sda.parametrized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    @ParameterizedTest
    @EnumSource(TemperatureConverter.class)
    public void shouldConvertTempToValueThanMin273_15(TemperatureConverter temperatureConverter) {
        assertTrue(temperatureConverter.convertTemp(0) >= -273.15);
    }

    @ParameterizedTest
    @EnumSource(value = TemperatureConverter.class, names = {"CELSIUS_KELVIN"}, mode = EnumSource.Mode.EXCLUDE) // mode = EnumSource.Mode.EXCLUDE
    public void shouldConvertTempToValueThanMin273_15_V2(TemperatureConverter temperatureConverter) {
        assertTrue(temperatureConverter.convertTemp(0) >= -273.15);
    }

    @ParameterizedTest
    @EnumSource(value = TemperatureConverter.class, names = {"^.*CELSIUS*."}, mode = EnumSource.Mode.MATCH_ALL)
    public void shouldConvertTempToValueThanMin273_15_V3(TemperatureConverter temperatureConverter) {
        assertTrue(temperatureConverter.convertTemp(0) >= -273.15);
    }

}