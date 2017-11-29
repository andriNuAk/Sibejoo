package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/13/2017.
 */

public class UpdateProfilResponse {
    @SerializedName("UpdateProfil")
    @Expose
    private List<UpdateProfil> updateprofil = null;

    public List<UpdateProfil> getUpdateProfil() {
        return updateprofil;
    }

    public void setUpdateProfil(List<UpdateProfil> updateProfil) {
        this.updateprofil = updateProfil;
    }
}
