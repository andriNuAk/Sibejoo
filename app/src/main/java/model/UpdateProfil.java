package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/13/2017.
 */

public class UpdateProfil {
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("namaDepan")
    @Expose
    private String namaDepan;
    @SerializedName("namaBelakang")
    @Expose
    private String namaBelakang;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("noKontak")
    @Expose
    private String noKontak;
    @SerializedName("biografi")
    @Expose
    private String biografi;
    @SerializedName("namaSekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("alamatSekolah")
    @Expose
    private String alamatSekolah;

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoKontak() {
        return noKontak;
    }

    public void setNoKontak(String noKontak) {
        this.noKontak = noKontak;
    }

    public String getBiografi() {
        return biografi;
    }

    public void setBiografi(String biografi) {
        this.biografi = biografi;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getAlamatSekolah() {
        return alamatSekolah;
    }

    public void setAlamatSekolah(String alamatSekolah) {
        this.alamatSekolah = alamatSekolah;
    }

    public UpdateProfil(Integer penggunaID, String namaDepan, String namaBelakang, String alamat, String noKontak, String biografi, String namaSekolah, String alamatSekolah) {
        this.penggunaID = penggunaID;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.alamat = alamat;
        this.noKontak = noKontak;
        this.biografi = biografi;
        this.namaSekolah = namaSekolah;
        this.alamatSekolah = alamatSekolah;
    }
}
