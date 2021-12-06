package edu.uark.kacounts.preservear.Data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DefaultDataGenerator {
    static final String BASE_URL = "https://drive.google.com/file/d/14orWfp51C95PPeCyTqUjLWusO6dcycNg/";
    private List<Experience> experiences;


    public void generateDefaultData(DefaultDataGeneratorCallback callback) {
        makeExperienceCall(callback);
    }

    public void makeExperienceCall(DefaultDataGeneratorCallback callback){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ExperienceAPI experienceAPI = retrofit.create(ExperienceAPI.class);
        Call<List<Experience>> call = experienceAPI.loadExperiences();
        call.enqueue(new Callback<List<Experience>>() {
            @Override
            public void onResponse(Call<List<Experience>> call, Response<List<Experience>> response) {
                ExperiencesResponse(response,callback);
            }

            @Override
            public void onFailure(Call<List<Experience>> call, Throwable t) {
                Log.e("DefaultDataGenerator","Call Failed");
                callback.onDataNotCreated();
            }
        });

    }

    public void ExperiencesResponse(Response<List<Experience>> response, DefaultDataGeneratorCallback callback){
        if(response.isSuccessful()) {
            experiences = new ArrayList<Experience>(response.body());
            List<Photo> photosList = new ArrayList<>();
            for (int i = 0; i< experiences.size();i++){
                Experience e = experiences.get(i);
                Photo p = e.PostToPhoto();
                photosList.add(p);
            }
            callback.defaultDataCreated(photosList);

        }else{
            Log.e("DummyDataGenerator","Can't create data");
            callback.onDataNotCreated();
        }
    }
}