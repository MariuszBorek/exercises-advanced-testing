package pl.sda.parametrized;

import java.util.function.Function;

public enum TemperatureConverter {

    CELSIUS_KELVIN(cTemp -> cTemp + 273.15f),
    KELVIN_CELSIUS(kTemp -> kTemp - 273.15f),
    CELSIUS_FAHRENHEIT(cTemp -> cTemp * 9 / 5f + 32);

    private Function<Float, Float> converter;

    TemperatureConverter(Function<Float, Float> converter) {
        this.converter = converter;
    }
    public float convertTemp(float temp) {
        return converter.apply(temp);
    }
}