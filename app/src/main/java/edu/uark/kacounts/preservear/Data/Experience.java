package edu.uark.kacounts.preservear.Data;

import java.util.ArrayList;

public class Experience {

    private int id;
    private String title;
    private String description;
    private String fName;
    private Double lat;
    private Double longi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Photo PostToPhoto(){
        Photo photo = new Photo();
        photo.setId(this.id);
        photo.setComment(this.description);
        photo.setLatitude(this.lat);
        photo.setLongitude(this.longi);
        photo.setFilename(this.fName);
        return photo;
    }
}
