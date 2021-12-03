package edu.uark.kacounts.preservear.Data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import util.AppExecutors;


public class PhotoRepository implements PhotoDataSource{

    public static volatile PhotoRepository INSTANCE;
    PhotoDao commentedPhotoDao;
    AppExecutors mAppExecutors;
    //Context for calling MessageProvider
    private Context mContext;



    private PhotoRepository(AppExecutors executors,Context context){
        commentedPhotoDao = PhotoDatabase.getInstance(context).getPhotoDao();
        mAppExecutors = executors;
    }

    /**
     * public constructor - prevent creation of instance if one already exists
     * @param appExecutors
     * @param context
     * @return
     */
    public static PhotoRepository getInstance(@NonNull AppExecutors appExecutors, @NonNull Context context){
        if(INSTANCE == null){
            synchronized (PhotoRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new PhotoRepository(appExecutors, context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPhotos(@NonNull LoadPhotosCallback callback) {

    }

    @Override
    public void getPhoto(@NonNull Integer PhotoId, @NonNull GetPhotoCallback callback) {
        Log.d("REPOSITORY", "GetMessage");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String[] projection = {
                        Photo.ID,
                        Photo.COMMENT,
                        Photo.FNAME,
                        Photo.LAT,
                        Photo.LONG,};
                final Cursor c = mContext.getContentResolver().query(Uri.parse("content://" + "edu.uark.kacounts.preservear.data" + "/" + "photos_db"), projection, null, null, null);
                final Photo photo = new Photo();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @SuppressLint("Range")
                    @Override
                    public void run() {
                        if (c == null) {
                            callback.onDataNotAvailable();
                        } else {
                            if (c.getCount() == 0) {
                                callback.onDataNotAvailable();
                            }
                            while (c.moveToNext()) {
                                if (c.getInt(c.getColumnIndex(Photo.ID)) == PhotoId) {
                                    photo.setId(c.getInt(c.getColumnIndex(Photo.ID)));
                                    photo.setComment(c.getString(c.getColumnIndex(Photo.COMMENT)));
                                    photo.setFilename(c.getString(c.getColumnIndex(Photo.FNAME)));
                                    photo.setLatitude(c.getDouble(c.getColumnIndex(Photo.LAT)));
                                    photo.setLongitude(c.getDouble(c.getColumnIndex(Photo.LONG)));
                                }
                            }
                            c.close();
                            callback.onPhotoLoaded(photo);
                        }
                    }
                });

            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void savePhoto(@NonNull Photo photo) {

    }

    @Override
    public void createPhoto(@NonNull Photo photo, @NonNull CreatePhotoCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long id = commentedPhotoDao.insert(photo);
                mAppExecutors.mainThread().execute(new Runnable(){
                    @Override
                    public void run() {
                        callback.onPhotoCreated((int)id);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void deletePhoto(@NonNull Integer id, @NonNull DeletePhotoCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = commentedPhotoDao.delete(id);
                    callback.onPhotoDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onPhotoDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}