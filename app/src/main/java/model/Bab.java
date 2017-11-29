package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/26/2017.
 */

public class Bab {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("judulBab")
    @Expose
    private String judulBab;

    public Bab(Integer id, String judulBab) {
        this.id = id;
        this.judulBab = judulBab;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }
}
