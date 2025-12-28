package com.example.openweather.models;

import androidx.annotation.NonNull;

import com.example.openweather.utils.formatters.WeatherFormatterType;

public class WeatherPresentation {

    public static final boolean DEFAULT_DO_ROUND_TEMPERATURE = false;

    public static final String DEFAULT_TEMPERATURE_UNITS = "°C";

    public static final String DEFAULT_WIND_SPEED_UNITS = "m/s";

    public static final String DEFAULT_WIND_DIRECTION_FORMAT = "arrow";

    public static final String DEFAULT_PRESSURE_UNITS = "hPa/mBar";

    public static final boolean DEFAULT_SHOW_TEMPERATURE_IN_STATUS_BAR = true;

    private final boolean roundedTemperature;
    private final String temperatureUnits;
    private final String windSpeedUnits;
    private final String windDirectionFormat;
    private final String pressureUnits;
    private final boolean showTemperatureInStatusBar;

    private final ImmutableWeather weather;
    private final WeatherFormatterType type;

    public WeatherPresentation() {
        roundedTemperature = DEFAULT_DO_ROUND_TEMPERATURE;
        temperatureUnits = DEFAULT_TEMPERATURE_UNITS;
        windSpeedUnits = DEFAULT_WIND_SPEED_UNITS;
        windDirectionFormat = DEFAULT_WIND_DIRECTION_FORMAT;
        pressureUnits = DEFAULT_PRESSURE_UNITS;
        showTemperatureInStatusBar = DEFAULT_SHOW_TEMPERATURE_IN_STATUS_BAR;
        weather = ImmutableWeather.EMPTY;
        type = WeatherFormatterType.NOTIFICATION_SIMPLE;
    }

    public WeatherPresentation(
            boolean roundedTemperature, @NonNull String temperatureUnits,
            @NonNull String windSpeedUnits, @NonNull String windDirectionFormat,
            @NonNull String pressureUnits, boolean showTemperatureInStatusBar,
            @NonNull ImmutableWeather weather,
            @NonNull WeatherFormatterType type
    ) {
        this.roundedTemperature = roundedTemperature;
        this.temperatureUnits = temperatureUnits;
        this.windSpeedUnits = windSpeedUnits;
        this.windDirectionFormat = windDirectionFormat;
        this.pressureUnits = pressureUnits;
        this.showTemperatureInStatusBar = showTemperatureInStatusBar;
        this.weather = weather;
        this.type = type;
    }

    public boolean isRoundedTemperature() {
        return roundedTemperature;
    }

    public boolean shouldShowTemperatureInStatusBar() {
        return showTemperatureInStatusBar;
    }

    public String getTemperatureUnits() {
        return temperatureUnits;
    }

    public String getWindSpeedUnits() {
        return windSpeedUnits;
    }

    public String getWindDirectionFormat() {
        return windDirectionFormat;
    }

    public String getPressureUnits() {
        return pressureUnits;
    }

    public ImmutableWeather getWeather() {
        return weather;
    }

    public WeatherFormatterType getType() {
        return type;
    }

    public WeatherPresentation copy(boolean roundedTemperature) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copyTemperatureUnits(@NonNull String temperatureUnits) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copyWindSpeedUnits(@NonNull String windSpeedUnits) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copyWindDirectionFormat(@NonNull String windDirectionFormat) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copyPressureUnits(@NonNull String pressureUnits) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copyShowTemperatureInStatusBar(boolean showTemperatureInStatusBar) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copy(@NonNull ImmutableWeather weather) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    public WeatherPresentation copy(@NonNull WeatherFormatterType type) {
        return new WeatherPresentation(
                roundedTemperature, temperatureUnits, windSpeedUnits,
                windDirectionFormat, pressureUnits, showTemperatureInStatusBar,
                weather, type
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherPresentation)) return false;

        WeatherPresentation that = (WeatherPresentation) o;

        if (roundedTemperature != that.roundedTemperature) return false;
        if (!temperatureUnits.equals(that.temperatureUnits)) return false;
        if (!windSpeedUnits.equals(that.windSpeedUnits)) return false;
        if (!windDirectionFormat.equals(that.windDirectionFormat)) return false;
        if (!pressureUnits.equals(that.pressureUnits)) return false;
        if (!weather.equals(that.weather)) return false;
        if (showTemperatureInStatusBar != that.showTemperatureInStatusBar) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = (roundedTemperature ? 1 : 0);
        result = 31 * result + temperatureUnits.hashCode();
        result = 31 * result + windSpeedUnits.hashCode();
        result = 31 * result + windDirectionFormat.hashCode();
        result = 31 * result + pressureUnits.hashCode();
        result = 31 * result + (showTemperatureInStatusBar ? 1 : 0);
        result = 31 * result + weather.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "WeatherPresentation{" +
                "roundedTemperature=" + roundedTemperature +
                ", temperatureUnits='" + temperatureUnits + '\'' +
                ", windSpeedUnits='" + windSpeedUnits + '\'' +
                ", windDirectionFormat='" + windDirectionFormat + '\'' +
                ", pressureUnits='" + pressureUnits + '\'' +
                ", shouldShowTemperatureInStatusBar=" + showTemperatureInStatusBar +
                ", weather=" + weather +
                ", type=" + type +
                '}';
    }
}