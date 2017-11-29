package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/20/2017.
 */

public class LearningLineModul {
    @SerializedName("namaStep")
    @Expose
    private String namaStep;
    @SerializedName("namaTopik")
    @Expose
    private String namaTopik;
    @SerializedName("judulMateri")
    @Expose
    private String judulMateri;
    @SerializedName("isiMateri")
    @Expose
    private Object isiMateri;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("url_file")
    @Expose
    private String urlFile;

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

    public String getJudulMateri() {
        return judulMateri;
    }

    public void setJudulMateri(String judulMateri) {
        this.judulMateri = judulMateri;
    }

    public Object getIsiMateri() {
        return isiMateri;
    }

    public void setIsiMateri(Object isiMateri) {
        this.isiMateri = isiMateri;
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

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }
}
