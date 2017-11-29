package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/8/2017.
 */

public class BabLearningLineResponse {
    @SerializedName("BabLearningLine")
    @Expose
    private List<BabLearningLine> babLearningLine = null;

    public List<BabLearningLine> getBabLearningLine() {
        return babLearningLine;
    }

    public void setBabLearningLine(List<BabLearningLine> babLearningLine) {
        this.babLearningLine = babLearningLine;
    }
}
