package com.example.openweather.notifications.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.example.openweather.R;
import com.example.openweather.models.WeatherPresentation;
import com.example.openweather.utils.formatters.WeatherFormatter;

public abstract class NotificationContentUpdater {
    public static final int DEFAULT_NOTIFICATION_ICON = R.drawable.cloud;

    public boolean isLayoutCustom() {
        return false;
    }

    public void updateNotification(@NonNull WeatherPresentation weatherPresentation,
                                   @NonNull NotificationCompat.Builder notification,
                                   @NonNull Context context
    ) throws NullPointerException {
        setTemperatureAsIcon(weatherPresentation, notification, context);
    }

    @NonNull
    public RemoteViews prepareRemoteView(@NonNull Context context) throws NullPointerException {
        throw new UnsupportedOperationException("prepareRemoteView is not implemented");
    }

    @CallSuper
    public void updateNotification(@NonNull WeatherPresentation weatherPresentation,
                                   @NonNull NotificationCompat.Builder notification,
                                   @NonNull RemoteViews notificationLayout,
                                   @NonNull Context context
    ) throws NullPointerException {
        setTemperatureAsIcon(weatherPresentation, notification, context);
    }

    // TODO add tests
    private void setTemperatureAsIcon(
            @NonNull WeatherPresentation weatherPresentation,
            @NonNull NotificationCompat.Builder notification,
            @NonNull Context context
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final IconCompat icon;
            if (weatherPresentation.shouldShowTemperatureInStatusBar()) {
                int color = ContextCompat.getColor(context, R.color.notification_icon_color);
                Bitmap statusBarIcon = WeatherFormatter.getTemperatureAsBitmap(context,
                        weatherPresentation.getWeather(),
                        weatherPresentation.getTemperatureUnits(),
                        color);
                icon = IconCompat.createWithBitmap(statusBarIcon);
            } else {
                icon = IconCompat.createWithResource(context, DEFAULT_NOTIFICATION_ICON);
            }
            notification.setSmallIcon(icon);
        } else {
            notification.setSmallIcon(DEFAULT_NOTIFICATION_ICON);
        }
    }
}