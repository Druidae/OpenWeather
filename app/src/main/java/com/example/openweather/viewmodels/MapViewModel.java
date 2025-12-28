package com.example.openweather.viewmodels;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.example.openweather.Constants;


public class MapViewModel extends ViewModel {
    public SharedPreferences sharedPreferences;
    public String apiKey;
    public double mapLat = Constants.DEFAULT_LAT;
    public double mapLon = Constants.DEFAULT_LON;
    public int mapZoom = Constants.DEFAULT_ZOOM_LEVEL;
    public int tabPosition = 0;
}
