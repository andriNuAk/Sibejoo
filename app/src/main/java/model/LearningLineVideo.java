package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/15/2017.
 */

public class LearningLineVideo {
    @SerializedName("namaStep")
    @Expose
    private String namaStep;
    @SerializedName("namaTopik")
    @Expose
    private String namaTopik;
    @SerializedName("judulVideo")
    @Expose
    private String judulVideo;
    @SerializedName("namaFile")
    @Expose
    private String namaFile;
    @SerializedName("deskripsiVideo")
    @Expose
    private String deskripsiVideo;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("UUID")
    @Expose
    private String uUID;

    public String getNamaStep() {
        return namaStep;
    }

    public void setNamaStep(String namaStep) {
        this.namaStep = namaStep;
    }

    public String getNamaTopik() {
        return namaTopik;
    }

    public void setNamaTopik(String namaTopik) {
        this.namaTopik = namaTopik;
    }

    public String getJudulVideo() {
        return judulVideo;
    }

    public void setJudulVideo(String judulVideo) {
        this.judulVideo = judulVideo;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getDeskripsiVideo() {
        return deskripsiVideo;
    }

    public void setDeskripsiVideo(String deskripsiVideo) {
        this.deskripsiVideo = deskripsiVideo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }
}
