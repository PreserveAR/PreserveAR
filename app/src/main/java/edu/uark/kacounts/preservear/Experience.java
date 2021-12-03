package edu.uark.kacounts.preservear;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Experience implements Serializable {

    //Static strings for the column names usable by other classes
    public static final String EXPERIENCE_ID = "id";
    public static final String EXPERIENCE_TITLE = "title";
    public static final String EXPERIENCE_DESCRIPTION = "description";
    public static final String EXPERIENCE_IMAGE = "image";
    public static final String EXPERIENCE_AUDIO = "audio";
    public static final String EXPERIENCE_TYPE = "type";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = EXPERIENCE_TITLE)
    private String title;

    @ColumnInfo(name = EXPERIENCE_DESCRIPTION)
    private String description;

    // going to store audio and image(s) for URL which is string
    @ColumnInfo(name = EXPERIENCE_IMAGE)
    private String image;
    // type blob if storing in database, need type converter

    @ColumnInfo(name = EXPERIENCE_AUDIO)
    private String audio;

    @ColumnInfo(name = EXPERIENCE_TYPE)
    private String type;

    public Integer getExperienceId() {
        return id;
    }

    public void setExperienceId(Integer id) {
        this.id = id;
    }

    // get and set the experience title
    public String getExperienceTitle() {
        return title;
    }

    public void setExperienceTitle(String subject) {
        this.title = title;
    }

    // get and set experience description
    public String getExperienceDescription() {
        return description;
    }

    public void setExperienceDescription(String subject) {
        this.description = description;
    }

    // get and set experience image(s)


    // get and set experience audio


    // get and set experience type
    public String getExperienceType() { return type; }

    public void setType(String type) { this.type = type; }
}
