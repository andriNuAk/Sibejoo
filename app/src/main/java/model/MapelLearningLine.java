package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/6/2017.
 */

public class MapelLearningLine {
    @SerializedName("mapel")
    @Expose
    private String mapel;
    @SerializedName("statusAksesLearningLine")
    @Expose
    private String statusAksesLearningLine;

    public String getMapel() {
        return mapel;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public String getStatusAksesLearningLine() {
        return statusAksesLearningLine;
    }

    public void setStatusAksesLearningLine(String statusAksesLearningLine) {
        this.statusAksesLearningLine = statusAksesLearningLine;
    }
}
