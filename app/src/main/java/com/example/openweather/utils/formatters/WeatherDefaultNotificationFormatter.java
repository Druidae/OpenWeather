package com.example.openweather.utils.formatters;

import static com.example.openweather.utils.TimeUtils.isDayTime;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.openweather.R;
import com.example.openweather.models.ImmutableWeather;

public class WeatherDefaultNotificationFormatter extends WeatherFormatter {
    @Override
    public boolean isEnoughValidData(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return weather.getTemperature() != ImmutableWeather.EMPTY.getTemperature()
                && !weather.getDescription().equals(ImmutableWeather.EMPTY.getDescription())
                && weather.getWeatherIcon() != ImmutableWeather.EMPTY.getWeatherIcon();
    }

    @NonNull
    @Override
    public String getTemperature(@NonNull ImmutableWeather weather,
                                 @NonNull String temperatureUnit,
                                 boolean roundedTemperature
    ) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (temperatureUnit == null)
            throw new NullPointerException("temperatureUnit should not be null");

        return TemperatureFormatter.getTemperature(weather, temperatureUnit, roundedTemperature);
    }

    @NonNull
    @Override
    public String getDescription(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return DescriptionFormatter.getDescription(weather);
    }

    @NonNull
    @Override
    public Bitmap getWeatherIconAsBitmap(@NonNull ImmutableWeather weather,
                                         @NonNull Context context) {
        String icon = getWeatherIconAsText(weather.getWeatherIcon(), isDayTime(weather), context);
        int color = ContextCompat.getColor(context, R.color.notification_icon_color);
        return getWeatherIconAsBitmap(context, icon, color);
    }
}
