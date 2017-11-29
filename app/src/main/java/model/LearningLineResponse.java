package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/9/2017.
 */

public class LearningLineResponse {
    @SerializedName("LearningLine")
    @Expose
    private List<LearningLine> learningLine = null;

    public List<LearningLine> getLearningLine() {
        return learningLine;
    }

    public void setLearningLine(List<LearningLine> learningLine) {
        this.learningLine = learningLine;
    }
}
