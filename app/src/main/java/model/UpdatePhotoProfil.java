package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/16/2017.
 */

public class UpdatePhotoProfil {
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("photo")
    @Expose
    private String photo;

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String getPhoto() {
        return photo;
    }

    public UpdatePhotoProfil(Integer penggunaID, String photo) {
        this.penggunaID = penggunaID;
        this.photo = photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
