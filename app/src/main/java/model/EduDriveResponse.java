package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/31/2017.
 */

public class EduDriveResponse {
    @SerializedName("EduDrive")
    @Expose
    private List<EduDrive> eduDrive = null;

    public List<EduDrive> getEduDrive() {
        return eduDrive;
    }

    public void setEduDrive(List<EduDrive> eduDrive) {
        this.eduDrive = eduDrive;
    }
}
