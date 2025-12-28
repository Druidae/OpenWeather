package com.example.openweather.utils.localizers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.openweather.R;

// TODO replace "singleton" with DI
public abstract class PressureUnitsLocalizer {
    // TODO replace String with enum
    @StringRes
    public static int localizePressureUnits(@NonNull String units)
            throws NullPointerException, IllegalArgumentException {
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");

        switch (units) {
            // hPa added for future compatibility with other code where it is used as default value
            case "hPa":
            case "hPa/mBar":
                return R.string.pressure_unit_hpa;
            case "kPa":
                return R.string.pressure_unit_kpa;
            case "mm Hg":
                return R.string.pressure_unit_mmhg;
            case "in Hg":
                return R.string.pressure_unit_inhg;
            default:
                throw new IllegalArgumentException("Unknown units: \"" + units + "\"");
        }
    }

    // TODO replace String with enum
    @NonNull
    public static String localizePressureUnits(@NonNull String units, @NonNull Context context)
            throws NullPointerException, IllegalArgumentException {
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        int unitsResourceId = localizePressureUnits(units);
        return context.getString(unitsResourceId);
    }
}
