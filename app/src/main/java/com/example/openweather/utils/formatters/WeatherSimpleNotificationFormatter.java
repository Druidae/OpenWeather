package com.example.openweather.utils.formatters;

import static com.example.openweather.utils.TimeUtils.isDayTime;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.util.Locale;

import com.example.openweather.R;
import com.example.openweather.models.ImmutableWeather;
import com.example.openweather.models.Weather;
import com.example.openweather.utils.UnitConvertor;
import com.example.openweather.utils.localizers.PressureUnitsLocalizer;
import com.example.openweather.utils.localizers.WindDirectionLocalizer;
import com.example.openweather.utils.localizers.WindSpeedUnitsLocalizer;

public class WeatherSimpleNotificationFormatter extends WeatherFormatter {

    @Override
    public boolean isEnoughValidData(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return weather.getTemperature() != ImmutableWeather.EMPTY.getTemperature()
                && !weather.getDescription().equals(ImmutableWeather.EMPTY.getDescription())
                && weather.getWeatherIcon() != ImmutableWeather.EMPTY.getWeatherIcon()
                && weather.getWindSpeed() != ImmutableWeather.EMPTY.getWindSpeed()
                && weather.getWindDirection() != ImmutableWeather.EMPTY.getWindDirection()
                && weather.getPressure() != ImmutableWeather.EMPTY.getPressure()
                && weather.getHumidity() != ImmutableWeather.EMPTY.getHumidity();
    }

    public boolean isEnoughValidMainData(@NonNull ImmutableWeather weather) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");

        return weather.getTemperature() != ImmutableWeather.EMPTY.getTemperature();
    }

    @NonNull
    @Override
    public String getTemperature(@NonNull ImmutableWeather weather, @NonNull String temperatureUnit,
                                 boolean roundedTemperature) throws NullPointerException {
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
    public String getWind(@NonNull ImmutableWeather weather, @NonNull String units,
                          @NonNull String directionFormat, @NonNull Context context
    ) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");
        //noinspection ConstantConditions
        if (directionFormat == null)
            throw new NullPointerException("directionFormat should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        StringBuilder builder = new StringBuilder();
        if (weather.getWindSpeed() != ImmutableWeather.EMPTY.getWindSpeed()) {
            builder
                    .append(context.getString(R.string.wind))
                    .append(": ");
            try {
                double windSpeed = weather.getWindSpeed(units);
                if (units.equals("bft"))
                    builder.append(UnitConvertor.getBeaufortName((int) windSpeed, context));
                else {
                    builder.append(new DecimalFormat("0.0").format(windSpeed));
                    builder
                            .append(' ')
                            .append(WindSpeedUnitsLocalizer.localizeWindSpeedUnits(units, context));
                }

                Weather.WindDirection windDirection = weather.getWindDirection();
                if (windDirection != null) {
                    try {
                        String localizedWindDirection = WindDirectionLocalizer.localizeWindDirection(
                                windDirection, directionFormat, context);
                        if (!localizedWindDirection.isEmpty()) {
                            builder
                                    .append(' ')
                                    .append(localizedWindDirection);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                builder.delete(0, builder.length());
            }
        }
        return builder.toString();
    }

    @NonNull
    @Override
    public String getPressure(@NonNull ImmutableWeather weather, @NonNull String units,
                              @NonNull Context context
    ) throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (units == null)
            throw new NullPointerException("units should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        StringBuilder builder = new StringBuilder();
        if (weather.getPressure() != ImmutableWeather.EMPTY.getPressure()) {
            builder
                    .append(context.getString(R.string.pressure))
                    .append(new DecimalFormat(": 0.0 ").format(weather.getPressure(units)));
            try {
                builder
                        .append(PressureUnitsLocalizer.localizePressureUnits(units, context));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                builder.delete(0, builder.length());
            }
        }
        return builder.toString();
    }

    @NonNull
    @Override
    public String getHumidity(@NonNull ImmutableWeather weather, @NonNull Context context)
            throws NullPointerException {
        //noinspection ConstantConditions
        if (weather == null)
            throw new NullPointerException("weather should not be null");
        //noinspection ConstantConditions
        if (context == null)
            throw new NullPointerException("context should not be null");

        String result;
        if (weather.getHumidity() != ImmutableWeather.EMPTY.getHumidity()) {
            result = String.format(Locale.getDefault(), "%s: %d %%",
                    context.getString(R.string.humidity),
                    weather.getHumidity());
        } else {
            result = "";
        }
        return result;
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
