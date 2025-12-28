package com.example.openweather.utils;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.example.openweather.models.ImmutableWeather;
import com.example.openweather.models.Weather;

public class TimeUtils {

    public static boolean isDayTime(Weather W, Calendar Cal){
        Date Sunrise = W.getSunrise();
        Date Sunset = W.getSunset();
        boolean day;
        if((Sunrise != null) && (Sunset != null)){
            Date currentTime = Calendar.getInstance().getTime();
            day = currentTime.after(W.getSunrise()) && currentTime.before(W.getSunset());
        }
        else{
            // fallback
            int hourOfDay = Cal.get(Calendar.HOUR_OF_DAY);
            day = (hourOfDay >= 7 && hourOfDay < 20);
        }
        return day;
    }

    public static boolean isDayTime(@NonNull ImmutableWeather weather) {
        if (weather.getSunrise() < 0 || weather.getSunset() < 0)
            return true;

        Date sunrise = new Date(TimeUnit.SECONDS.convert(weather.getSunrise(), TimeUnit.MILLISECONDS));
        Date sunset = new Date(TimeUnit.SECONDS.convert(weather.getSunset(), TimeUnit.MILLISECONDS));;
        boolean isDay;
        Date currentTime = Calendar.getInstance().getTime();
        isDay = currentTime.after(sunrise) && currentTime.before(sunset);
        return isDay;
    }
}
