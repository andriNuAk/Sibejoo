package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 11/9/2017.
 */

public class TopikLearningLineResponse {
    @SerializedName("TopikLearningLine")
    @Expose
    private List<TopikLearningLine> topikLearningLine = null;

    public List<TopikLearningLine> getTopikLearningLine() {
        return topikLearningLine;
    }

    public void setTopikLearningLine(List<TopikLearningLine> topikLearningLine) {
        this.topikLearningLine = topikLearningLine;
    }
}
