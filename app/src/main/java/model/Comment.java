package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/20/2017.
 */

public class Comment {
    @SerializedName("namaPengguna")
    @Expose
    private String namaPengguna;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("isiKomen")
    @Expose
    private String isiKomen;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("komenID")
    @Expose
    private Integer komenID;
    @SerializedName("hakAkses")
    @Expose
    private String hakAkses;
    @SerializedName("siswa_photo")
    @Expose
    private Object siswaPhoto;
    @SerializedName("guru_photo")
    @Expose
    private String guruPhoto;

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIsiKomen() {
        return isiKomen;
    }

    public void setIsiKomen(String isiKomen) {
        this.isiKomen = isiKomen;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Integer getKomenID() {
        return komenID;
    }

    public void setKomenID(Integer komenID) {
        this.komenID = komenID;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public Object getSiswaPhoto() {
        return siswaPhoto;
    }

    public void setSiswaPhoto(Object siswaPhoto) {
        this.siswaPhoto = siswaPhoto;
    }

    public String getGuruPhoto() {
        return guruPhoto;
    }

    public void setGuruPhoto(String guruPhoto) {
        this.guruPhoto = guruPhoto;
    }
}
