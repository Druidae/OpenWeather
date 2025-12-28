package com.example.openweather.utils.localizers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.openweather.models.Weather;

// TODO replace "singleton" with DI
public abstract class WindDirectionLocalizer {
    // TODO replace String with enum
    @NonNull
    public static String localizeWindDirection(@NonNull Weather.WindDirection direction,
                                               @NonNull String format, @NonNull Context context
    ) throws NullPointerException, IllegalArgumentException {
        //noinspection ConstantConditions
        if (direction == null)
            throw new NullPointerException("direction should not be null");
        //noinspection ConstantConditions
        if (format == null)
            throw new NullPointerException("format should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        String result;
        switch (format) {
            case "abbr":
                result = direction.getLocalizedString(context);
                break;
            case "arrow":
                result = direction.getArrow(context);
                break;
            case "none":
                result = "";
                break;
            default:
                throw new IllegalArgumentException("Unknown format: \"" + format + "\"");
        }
        return result;
    }
}
