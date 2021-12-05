package edu.uark.kacounts.preservear.PhotoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.uark.kacounts.preservear.Data.PhotoDataSource;
import edu.uark.kacounts.preservear.Data.PhotoRepository;
import edu.uark.kacounts.preservear.R;
import util.AppExecutors;

public class TakePhotoActivity extends AppCompatActivity {

    private TakePhotoContract.Presenter mPresenter;
    private TakePhotoContract.View mView;
    private PhotoDataSource mModel;
    static Integer id;

    public static Integer getId() {
        return id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        Intent callingIntent = this.getIntent();
        id = callingIntent.getIntExtra("id",-1);
        mPresenter = new TakePhotoPresenter();
        mModel = PhotoRepository.getInstance(new AppExecutors(),getApplicationContext());
        mPresenter.setModel(mModel);
        mView = (TakePhotoContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView3);
        mPresenter.setView(mView);
    }

    @Override
    protected void onStart(){
        super.onStart();
        mPresenter.startPresenter();
    }
}