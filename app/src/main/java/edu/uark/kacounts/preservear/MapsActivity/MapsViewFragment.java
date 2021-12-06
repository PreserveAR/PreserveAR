package edu.uark.kacounts.preservear.MapsActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

import edu.uark.kacounts.preservear.PhotoActivity.TakePhotoActivity;
import edu.uark.kacounts.preservear.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsViewFragment} factory method to
 * create an instance of this fragment.
 */
public class MapsViewFragment extends Fragment implements MapsContract.View, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private MapsContract.Presenter mPresenter;
    private GoogleMap mMap;
    private LatLng mCurrentLocation;
    private boolean firstLocation = true;
    private List<Marker> markers;


    public MapsViewFragment() {
        // Required empty public constructor
        markers = new LinkedList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_maps_view, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FloatingActionButton fabTakePhoto = root.findViewById(R.id.fabAddPicture);
        fabTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.notifyAddClicked();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        centerCamera();
        if(mPresenter!=null){
            mPresenter.resume();
        }
    }

    @Override
    public void setPresenter(MapsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setLocation(LatLng myLocation) {
        mCurrentLocation = myLocation;
        if(firstLocation){
            firstLocation = false;
            centerCamera();
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        setMyLocationEnabled(true);
        centerCamera();
        mPresenter.notifyMapReady();
    }

    @Override
    public void setMyLocationEnabled(boolean isEnabled){
        if(isEnabled){
            if(mMap!=null){
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }else {
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }

    @Override
    public void addMarker(Double latitude, Double longitude, Integer id) {
        if(mMap!=null){
            LatLng position = new LatLng(latitude,longitude);
//            Marker marker = mMap.addMarker(new MarkerOptions().position(position));
            Marker temp = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(Integer.toString(id)));
            assert temp != null;
            temp.setTag(id.toString());
            markers.add(temp);
            Log.d("fragment", "added marker at " + latitude + " " + longitude + " marker count = " + markers.size());
        }
    }

    @Override
    public void startPhotoActivity(int photoId) {
        Intent photoActivityIntent = new Intent();
        photoActivityIntent.setClass(getActivity(), TakePhotoActivity.class);
        photoActivityIntent.putExtra("id",photoId);
        photoActivityIntent.putExtra("Lat",mCurrentLocation.latitude);
        photoActivityIntent.putExtra("Long",mCurrentLocation.longitude);
        addMarker(mCurrentLocation.latitude, mCurrentLocation.latitude, photoId);
        startActivity(photoActivityIntent);
    }

    @Override
    public void clearMarkers() {
        if(mMap!=null){
            for(Marker marker:markers){
                marker.remove();
            }
            markers = new LinkedList<>();
        }
    }

    private void centerCamera(){
        if(mMap!=null){
            if(mCurrentLocation!=null){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLocation,15));
            }
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        int id = Integer.parseInt(marker.getTag().toString());
        startPhotoActivity(id);
        return true;
    }
}
