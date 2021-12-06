package edu.uark.kacounts.preservear.MapsActivity;

import com.google.android.gms.maps.model.LatLng;

import edu.uark.kacounts.preservear.Data.PhotoDataSource;

public interface MapsContract {

    interface Presenter{
        void start();
        void setView(MapsContract.View view);
        void setRepository(PhotoDataSource repository);
        void notifyMapReady();
        void notifyAddClicked();
        void resume();
    }

    interface View{
        void setPresenter(MapsContract.Presenter presenter);
        void setLocation(LatLng myLocation);
        void setMyLocationEnabled(boolean isEnabled);
        void addMarker(Double latitude, Double longitude, Integer id);
        void startPhotoActivity(int photoId);
        void clearMarkers();
    }
}
