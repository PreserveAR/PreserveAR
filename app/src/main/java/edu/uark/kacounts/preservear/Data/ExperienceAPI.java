package edu.uark.kacounts.preservear.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExperienceAPI {
    @GET("view?usp=sharing/")
    Call<List<Experience>> loadExperiences();

//    https://drive.google.com/file/d/14orWfp51C95PPeCyTqUjLWusO6dcycNg/view?usp=sharing
}