package com.example.openweather.utils.formatters;

import androidx.annotation.NonNull;

public abstract class WeatherFormatterFactory {

    @NonNull
    public static WeatherFormatter createFormatter(@NonNull WeatherFormatterType type) {
        switch (type) {
            case NOTIFICATION_DEFAULT:
                return new WeatherDefaultNotificationFormatter();
            case NOTIFICATION_SIMPLE:
                return new WeatherSimpleNotificationFormatter();
            default:
                throw new IllegalArgumentException("Unknown type " + type);
        }
    }
}
