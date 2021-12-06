package edu.uark.kacounts.preservear;

import android.util.Log;

import java.util.List;

import edu.uark.kacounts.preservear.Data.Photo;
import edu.uark.kacounts.preservear.Data.PhotoDataSource;

public class MapsPresenter implements MapsContract.Presenter{

    private MapsContract.View mView;
    private PhotoDataSource mRepository;
    List<Photo> photoList;
    private boolean mapReady = false;

    PhotoDataSource.LoadPhotosCallback loadPhotosCallback;

    MapsPresenter(){
        loadPhotosCallback = new PhotoDataSource.LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photos) {
                photoList = photos;
                photosLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("MapsPresenter","Data Not Available!");
            }
        };
    }

    private void photosLoaded() {
        if(mapReady){
            drawMarkers();
        }
    }

    @Override
    public void start() {
        mView.setPresenter(this);
        mRepository.getPhotos(loadPhotosCallback);
    }

    @Override
    public void resume(){
        mRepository.getPhotos(loadPhotosCallback);
    }

    @Override
    public void setView(MapsContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(PhotoDataSource repository) {
        mRepository = repository;
    }

    @Override
    public void notifyMapReady() {
        mapReady = true;
        if(photoList!= null && photoList.size()!=0){
            drawMarkers();
        }
    }

    @Override
    public void notifyAddClicked() {
        mView.startPhotoActivity(-1);
    }

    private void drawMarkers() {
        mView.clearMarkers();
        for(Photo photo:photoList){
            Log.d("drawing markers", "Photo philename = " + photo.getFilename());
            mView.addMarker(photo.getLatitude(),photo.getLongitude(),photo.getId());
        }
    }
}
