package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/6/2017.
 */

public class MapelLearningLineResponse {

    @SerializedName("MapelLearningLine")
    @Expose
    private List<MapelLearningLine> mapelLearningLine = null;

    public List<MapelLearningLine> getMapelLearningLine() {
        return mapelLearningLine;
    }

    public void setMapelLearningLine(List<MapelLearningLine> mapelLearningLine) {
        this.mapelLearningLine = mapelLearningLine;
    }

}
