package com.example.openweather.utils.localizers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.openweather.R;

// TODO replace "singleton" with DI
public abstract class WindSpeedUnitsLocalizer {
    // TODO replace String with enum
    @StringRes
    public static int localizeWindSpeedUnits(@NonNull String units)
            throws NullPointerException, IllegalArgumentException {
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");

        switch (units) {
            case "m/s": return R.string.speed_unit_mps;
            case "kph": return R.string.speed_unit_kph;
            case "mph": return R.string.speed_unit_mph;
            case "kn": return R.string.speed_unit_kn;
            default: throw new IllegalArgumentException("Unknown units: \"" + units + "\"");
        }
    }

    // TODO replace String with enum
    @NonNull
    public static String localizeWindSpeedUnits(@NonNull String units, @NonNull Context context)
            throws NullPointerException, IllegalArgumentException{
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        int unitsResourceId = localizeWindSpeedUnits(units);
        return context.getString(unitsResourceId);
    }
}
