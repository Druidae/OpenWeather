package com.example.openweather.utils.formatters;

import androidx.annotation.NonNull;

import com.example.openweather.models.ImmutableWeather;

// TODO rid off static and use DI
public abstract class DescriptionFormatter {
    @NonNull
    public static String getDescription(@NonNull ImmutableWeather weather)
            throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        String description = weather.getDescription();
        String result;
        if (description.isEmpty())
            result = description;
        else
            result = description.substring(0,1).toUpperCase() + description.substring(1);
        return result;
    }
}
