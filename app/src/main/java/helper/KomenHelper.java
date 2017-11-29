package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/23/2017.
 */

public class KomenHelper {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isiKomen")
    @Expose
    private String isiKomen;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("videoID")
    @Expose
    private int videoID;
    @SerializedName("userID")
    @Expose
    private int userID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("UUID")
    @Expose
    private String UUID;
    @SerializedName("read_status")
    @Expose
    private int read_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsiKomen() {
        return isiKomen;
    }

    public void setIsiKomen(String isiKomen) {
        this.isiKomen = isiKomen;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getVideoID() {
        return videoID;
    }

    public void setVideoID(int videoID) {
        this.videoID = videoID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getRead_status() {
        return read_status;
    }

    public void setRead_status(int read_status) {
        this.read_status = read_status;
    }
}
