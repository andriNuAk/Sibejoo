package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/29/2017.
 */

public class LearningLineSoalResponse {
    @SerializedName("LearningLineSoal")
    @Expose
    private List<LearningLineSoal> learningLineSoal = null;

    public List<LearningLineSoal> getLearningLineSoal() {
        return learningLineSoal;
    }

    public void setLearningLineSoal(List<LearningLineSoal> learningLineSoal) {
        this.learningLineSoal = learningLineSoal;
    }
}
