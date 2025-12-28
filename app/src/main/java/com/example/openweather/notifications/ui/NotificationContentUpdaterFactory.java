package com.example.openweather.notifications.ui;

import androidx.annotation.NonNull;

import com.example.openweather.utils.formatters.WeatherFormatter;
import com.example.openweather.utils.formatters.WeatherFormatterFactory;
import com.example.openweather.utils.formatters.WeatherFormatterType;

public abstract class NotificationContentUpdaterFactory {

    @NonNull
    public static NotificationContentUpdater createNotificationContentUpdater(
            @NonNull WeatherFormatterType type
    ) {
        WeatherFormatter formatter = WeatherFormatterFactory.createFormatter(type);
        switch (type) {
            case NOTIFICATION_DEFAULT:
                return new DefaultNotificationContentUpdater(formatter);
            case NOTIFICATION_SIMPLE:
                return new SimpleNotificationContentUpdater(formatter);
            default:
                throw new IllegalArgumentException("Unknown type" + type);
        }
    }

    public static boolean doesContentUpdaterMatchType(
            @NonNull WeatherFormatterType type,
            @NonNull  NotificationContentUpdater contentUpdater) {
        return (type == WeatherFormatterType.NOTIFICATION_DEFAULT
                && contentUpdater instanceof DefaultNotificationContentUpdater)
                ||
                (type == WeatherFormatterType.NOTIFICATION_SIMPLE
                        && contentUpdater instanceof SimpleNotificationContentUpdater);
    }
}
