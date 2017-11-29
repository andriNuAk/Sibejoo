package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/9/2017.
 */

public class TopikLearningLine {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("namaTopik")
    @Expose
    private String namaTopik;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public String getNamaTopik() {
        return namaTopik;
    }

    public void setNamaTopik(String namaTopik) {
        this.namaTopik = namaTopik;
    }
}
