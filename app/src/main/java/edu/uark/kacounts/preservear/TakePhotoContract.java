package edu.uark.kacounts.preservear;

import edu.uark.kacounts.preservear.Data.Photo;
import edu.uark.kacounts.preservear.Data.PhotoDataSource;

public interface TakePhotoContract {

    interface Presenter{
        void startPresenter();
        void setView(TakePhotoContract.View view);
        void notifyAddClicked();
        void setModel(PhotoDataSource model);
        void notifyPictureCaptured(String photoPath);
        Photo getPhoto(int id);
    }

    interface View{
        void setPresenter(TakePhotoContract.Presenter presenter);
        void captureNewPhoto();
        void setPicture(String photoPath);
        void photoAdded(int id);
    }
}
