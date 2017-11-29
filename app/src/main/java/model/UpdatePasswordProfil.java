package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/16/2017.
 */

public class UpdatePasswordProfil {
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("kataSandi")
    @Expose
    private String kataSandi;

    public UpdatePasswordProfil(Integer penggunaID, String kataSandi) {
        this.penggunaID = penggunaID;
        this.kataSandi = kataSandi;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }
}
