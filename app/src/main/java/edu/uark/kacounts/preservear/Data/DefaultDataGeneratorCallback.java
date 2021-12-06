package edu.uark.kacounts.preservear.Data;

import java.util.List;

public interface DefaultDataGeneratorCallback {
    void defaultDataCreated(List<Photo> photos);
    void onDataNotCreated();
}