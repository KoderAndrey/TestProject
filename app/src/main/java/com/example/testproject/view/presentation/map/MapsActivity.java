package com.example.testproject.view.presentation.map;

import android.Manifest;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.testproject.R;
import com.example.testproject.data.model.WeatherDisplayData;
import com.example.testproject.view.presentation.requstlist.ListRequestsActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import dagger.Lazy;
import dagger.android.AndroidInjection;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.example.testproject.view.presentation.requstlist.ListRequestsActivity.LATITUDE;
import static com.example.testproject.view.presentation.requstlist.ListRequestsActivity.LONGTITUDE;
import static com.example.testproject.view.presentation.requstlist.ListRequestsActivity.REQUEST_CODE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

public class MapsActivity extends MvpAppCompatActivity implements MapView, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final int REQUEST_COORDINATE = 1;
    private static final int ZOOM_VALUE = 5;

    @InjectPresenter
    MapPresenter mMapPresenter;
    @Inject
    Lazy<MapPresenter> mDaggerPresenter;

    private MarkerOptions mMarkerOptions = new MarkerOptions();
    private GoogleMap mMap;
    private Geocoder mGeocoder;
    private FusedLocationProviderClient mFusedLocationClient;

    @BindView(R.id.progress_view)
    FrameLayout mProgressBar;
    @BindView(R.id.spinner_map_type)
    AppCompatSpinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        mGeocoder = new Geocoder(this, Locale.getDefault());
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initUI();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        requestGpsPermission();
    }

    @ProvidePresenter
    MapPresenter providePresenter() {
        return mDaggerPresenter.get();
    }

    @Override
    public void getWeather(ArrayList<WeatherDisplayData> listWeather) {
        mProgressBar.setVisibility(View.GONE);
        showWeatherDialog(listWeather);
    }

    @OnItemSelected(R.id.spinner_map_type)
    public void spinnerItemSelected(AppCompatSpinner spinner, int position) {
        int typeMap = MAP_TYPE_NONE;
        switch (position) {
            case 0:
                typeMap = MAP_TYPE_NONE;
                break;
            case 1:
                typeMap = MAP_TYPE_NORMAL;
                break;
            case 2:
                typeMap = MAP_TYPE_SATELLITE;
                break;
            case 3:
                typeMap = MAP_TYPE_TERRAIN;
                break;
            case 4:
                typeMap = MAP_TYPE_HYBRID;
                break;
        }
        mMap.setMapType(typeMap);
    }

    @Override
    public void onError(Throwable throwable) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.error) + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartLoad() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.show_list, R.id.my_location_button})
    void click(View view) {
        switch (view.getId()) {
            case R.id.show_list:
                startActivityForResult(new Intent(this, ListRequestsActivity.class), REQUEST_CODE);
                break;
            case R.id.my_location_button:
                requestGpsPermission();
                break;

        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        mMarkerOptions.position(latLng);
        mMap.addMarker(mMarkerOptions);
        mMapPresenter.onMapClicked(latLng);
    }

    private void showWeatherDialog(ArrayList<WeatherDisplayData> displayData) {
        WeatherInfoDialog
                .getInstance(displayData)
                .show(getSupportFragmentManager(), WeatherInfoDialog.class.getSimpleName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            double lat = data.getDoubleExtra(LATITUDE, 0);
            double longt = data.getDoubleExtra(LONGTITUDE, 0);
            LatLng latLng = new LatLng(lat, longt);
            mMap.clear();
            mMarkerOptions.position(latLng);
            mMap.addMarker(mMarkerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_VALUE));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initUI() {
        SupportMapFragment mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
        mSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.map_types)));
        mSpinner.setSelection(1);
    }

    protected void requestGpsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COORDINATE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COORDINATE);
            }
        } else {
            getMyLocation();
        }
    }

    private void getMyLocation() {
        mFusedLocationClient.getLastLocation().addOnCompleteListener(
                task -> {
                    Location location = task.getResult();
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.clear();
                    mMarkerOptions.position(latLng);
                    mMap.addMarker(mMarkerOptions);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_VALUE));
                }
        );
    }
}