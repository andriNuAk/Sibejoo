package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/8/2017.
 */

public class BabLearningLine {

    @SerializedName("mapel")
    @Expose
    private String mapel;
    @SerializedName("judulBab")
    @Expose
    private String judulBab;
    @SerializedName("babID")
    @Expose
    private Integer babID;

    public BabLearningLine(String mapel, String judulBab, Integer babID, String statusAksesLearningLine) {
        this.mapel = mapel;
        this.judulBab = judulBab;
        this.babID = babID;
        this.statusAksesLearningLine = statusAksesLearningLine;
    }

    @SerializedName("statusAksesLearningLine")
    @Expose



    private String statusAksesLearningLine;

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }

    public Integer getBabID() {
        return babID;
    }

    public void setBabID(Integer babID) {
        this.babID = babID;
    }

    public String getStatusAksesLearningLine() {
        return statusAksesLearningLine;
    }

    public void setStatusAksesLearningLine(String statusAksesLearningLine) {
        this.statusAksesLearningLine = statusAksesLearningLine;
    }
}
