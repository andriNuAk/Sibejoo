package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/29/2017.
 */

public class LearningLineSoal {
    @SerializedName("idlat")
    @Expose
    private Integer idlat;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("soalid")
    @Expose
    private Integer soalid;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("jaw")
    @Expose
    private String jaw;
    @SerializedName("pembahasan")
    @Expose
    private String pembahasan;
    @SerializedName("gambar_pembahasan")
    @Expose
    private String gambarPembahasan;
    @SerializedName("video_pembahasan")
    @Expose
    private String videoPembahasan;
    @SerializedName("status_pembahasan")
    @Expose
    private Integer statusPembahasan;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("audio")
    @Expose
    private String audio;

    public Integer getIdlat() {
        return idlat;
    }

    public void setIdlat(Integer idlat) {
        this.idlat = idlat;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public Integer getSoalid() {
        return soalid;
    }

    public void setSoalid(Integer soalid) {
        this.soalid = soalid;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJaw() {
        return jaw;
    }

    public void setJaw(String jaw) {
        this.jaw = jaw;
    }

    public String getPembahasan() {
        return pembahasan;
    }

    public void setPembahasan(String pembahasan) {
        this.pembahasan = pembahasan;
    }

    public String getGambarPembahasan() {
        return gambarPembahasan;
    }

    public void setGambarPembahasan(String gambarPembahasan) {
        this.gambarPembahasan = gambarPembahasan;
    }

    public String getVideoPembahasan() {
        return videoPembahasan;
    }

    public void setVideoPembahasan(String videoPembahasan) {
        this.videoPembahasan = videoPembahasan;
    }

    public Integer getStatusPembahasan() {
        return statusPembahasan;
    }

    public void setStatusPembahasan(Integer statusPembahasan) {
        this.statusPembahasan = statusPembahasan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
