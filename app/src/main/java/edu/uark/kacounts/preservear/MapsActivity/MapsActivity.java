package edu.uark.kacounts.preservear.MapsActivity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import edu.uark.kacounts.preservear.Data.PhotoDataSource;
import edu.uark.kacounts.preservear.Data.PhotoRepository;
import edu.uark.kacounts.preservear.R;
import util.AppExecutors;

public class MapsActivity extends FragmentActivity{

    private MapsContract.View mView;
    private MapsContract.Presenter mPresenter;
    private boolean requestingLocationUpdates;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private PhotoDataSource mRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        requestLocationPermissions();
        requestLocationUpdates();
        mRepository = PhotoRepository.getInstance(new AppExecutors(),getApplicationContext());
        mView = (MapsContract.View) getSupportFragmentManager().findFragmentById(R.id.mapsFragmentContainerView);
        mPresenter = new MapsPresenter();
        mPresenter.setRepository(mRepository);
        mPresenter.setView(mView);
        requestingLocationUpdates = true;
    }

    @Override
    protected void onStart(){
        super.onStart();
        mPresenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //requestLocationPermissions();
            return;
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            if (mView != null) {
                                LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                mView.setLocation(myLocation);
                            }
                            Log.d("MapsActivity", "" + location.getLatitude() + ":" + location.getLongitude());
                            // Logic to handle location object
                        }
                    }
                });
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }

    }

    private void requestLocationUpdates(){
        requestingLocationUpdates = true;
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                Location location = locationResult.getLastLocation();
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                if(mView!=null){
                    mView.setLocation(latLng);
                }
                Log.d("MapsActivity", "" + location.getLatitude() + ":" + location.getLongitude());
            }
        };
    }

    private void requestLocationPermissions(){
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                                requestLocationUpdates();
                                if(mView!=null){
                                    mView.setMyLocationEnabled(true);
                                }
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                                Toast.makeText(this,"Precise location will improve marker placement",Toast.LENGTH_LONG);
                                requestLocationUpdates();
                                if(mView!=null){
                                    mView.setMyLocationEnabled(true);
                                }

                            } else {
                                // No location access granted.
                                Toast.makeText(this,"Some features won't work without location permission",Toast.LENGTH_LONG);
                            }
                        }
                );

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermissions();
            return;
        }
        fusedLocationClient.requestLocationUpdates(createLocationRequest(),
                locationCallback,
                Looper.getMainLooper());
    }
}