package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/20/2017.
 */

public class LearningLineModulResponse {
    @SerializedName("LearningLineModul")
    @Expose
    private List<LearningLineModul> learningLineModul = null;

    public List<LearningLineModul> getLearningLineModul() {
        return learningLineModul;
    }

    public void setLearningLineModul(List<LearningLineModul> learningLineModul) {
        this.learningLineModul = learningLineModul;
    }
}
