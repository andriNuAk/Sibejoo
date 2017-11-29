package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/16/2017.
 */

public class UpdatePhotoProfilResponse {
    @SerializedName("UpdatePhotoProfil")
    @Expose
    private List<UpdatePhotoProfil> updatePhotoProfil = null;

    public List<UpdatePhotoProfil> getUpdatePhotoProfil() {
        return updatePhotoProfil;
    }

    public void setUpdatePhotoProfil(List<UpdatePhotoProfil> updatePhotoProfil) {
        this.updatePhotoProfil = updatePhotoProfil;
    }
}
