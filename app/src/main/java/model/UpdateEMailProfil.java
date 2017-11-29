package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/16/2017.
 */

public class UpdateEMailProfil {
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("eMail")
    @Expose
    private String eMail;

    public UpdateEMailProfil(Integer penggunaID, String eMail) {
        this.penggunaID = penggunaID;
        this.eMail = eMail;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
