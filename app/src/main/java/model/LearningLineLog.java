package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/13/2017.
 */

public class LearningLineLog {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("stepID")
    @Expose
    private Integer stepID;
    @SerializedName("statusStep")
    @Expose
    private String statusStep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public Integer getStepID() {
        return stepID;
    }

    public void setStepID(Integer stepID) {
        this.stepID = stepID;
    }

    public String getStatusStep() {
        return statusStep;
    }

    public void setStatusStep(String statusStep) {
        this.statusStep = statusStep;
    }

    public LearningLineLog(Integer id, Integer penggunaID, Integer stepID, String statusStep) {
        this.id = id;
        this.penggunaID = penggunaID;
        this.stepID = stepID;
        this.statusStep = statusStep;
    }
}
