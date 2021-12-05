package edu.uark.kacounts.preservear.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Photo {
    public static final String FNAME = "filename";
    public static final String TITLE = "title";
    public static final String COMMENT = "comment";
    public static final String ID = "id";
    public static final String LAT = "latitude";
    public static final String LONG = "longitude";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name=TITLE)
    private String title;

    @ColumnInfo(name=COMMENT)
    private String comment;

    @ColumnInfo(name=FNAME)
    private String filename;

    @ColumnInfo(name = LAT)
    private Double latitude;

    @ColumnInfo(name = LONG)
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String comment) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
