package com.example.openweather.utils.formatters;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import com.example.openweather.models.ImmutableWeather;

// TODO rid off static and use DI
public abstract class TemperatureFormatter {
    @NonNull
    public static String getTemperature(@NonNull ImmutableWeather weather,
                                        @NonNull String temperatureUnit,
                                        boolean roundedTemperature
    ) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (temperatureUnit == null)
            throw new NullPointerException("temperatureUnit should not be null");

        String temperature = roundedTemperature
                ? String.valueOf(weather.getRoundedTemperature(temperatureUnit))
                : new DecimalFormat("0.#").format(weather.getTemperature(temperatureUnit));
        return temperature + temperatureUnit;
    }
}
