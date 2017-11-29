package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/13/2017.
 */

public class LearningLineLogResponse {
    @SerializedName("LearningLineLog")
    @Expose
    private List<LearningLineLog> learningLineLog = null;

    public List<LearningLineLog> getLearningLineLog() {
        return learningLineLog;
    }

    public void setLearningLineLog(List<LearningLineLog> learningLineLog) {
        this.learningLineLog = learningLineLog;
    }
}
