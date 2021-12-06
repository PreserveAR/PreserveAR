package edu.uark.kacounts.preservear;

import android.content.Context;

import androidx.annotation.NonNull;

import edu.uark.kacounts.preservear.Data.PhotoRepository;
import util.AppExecutors;

public class Injection {
    public static PhotoRepository providePhotoRepository(AppExecutors executors, @NonNull Context context) {
        return PhotoRepository.getInstance(executors,context);
    }
}
