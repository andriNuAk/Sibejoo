package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/15/2017.
 */

public class LearningLineVideoResponse {
    @SerializedName("LearningLineVideo")
    @Expose
    private List<LearningLineVideo> learningLineVideo = null;

    public List<LearningLineVideo> getLearningLineVideo() {
        return learningLineVideo;
    }

    public void setLearningLineVideo(List<LearningLineVideo> learningLineVideo) {
        this.learningLineVideo = learningLineVideo;
    }
}
