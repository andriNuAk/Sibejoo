package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/18/2017.
 */

public class Materi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("judulVideo")
    @Expose
    private String judulVideo;
    @SerializedName("namaFile")
    @Expose
    private Object namaFile;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("path")
    @Expose
    private Object path;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("subBabID")
    @Expose
    private Integer subBabID;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("jenis_video")
    @Expose
    private String jenisVideo;
    @SerializedName("judulSubBab")
    @Expose
    private String judulSubBab;
    @SerializedName("babID")
    @Expose
    private Integer babID;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("judulBab")
    @Expose
    private String judulBab;
    @SerializedName("tingkatPelajaranID")
    @Expose
    private Integer tingkatPelajaranID;
    @SerializedName("statusLearningLine")
    @Expose
    private String statusLearningLine;
    @SerializedName("statusAksesLatihan")
    @Expose
    private String statusAksesLatihan;
    @SerializedName("statusAksesKonsultasi")
    @Expose
    private String statusAksesKonsultasi;
    @SerializedName("statusAksesLearningLine")
    @Expose
    private String statusAksesLearningLine;
    @SerializedName("tingkatID")
    @Expose
    private Integer tingkatID;
    @SerializedName("mataPelajaranID")
    @Expose
    private Integer mataPelajaranID;
    @SerializedName("aliasTingkat")
    @Expose
    private String aliasTingkat;
    @SerializedName("namaTingkat")
    @Expose
    private String namaTingkat;
    @SerializedName("depedensi")
    @Expose
    private Object depedensi;
    @SerializedName("aliasMataPelajaran")
    @Expose
    private String aliasMataPelajaran;
    @SerializedName("namaMataPelajaran")
    @Expose
    private String namaMataPelajaran;
    @SerializedName("videoID")
    @Expose
    private Integer videoID;
    @SerializedName("subbabID")
    @Expose
    private Integer subbabID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudulVideo() {
        return judulVideo;
    }

    public void setJudulVideo(String judulVideo) {
        this.judulVideo = judulVideo;
    }

    public Object getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(Object namaFile) {
        this.namaFile = namaFile;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Object getPath() {
        return path;
    }

    public void setPath(Object path) {
        this.path = path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public Integer getSubBabID() {
        return subBabID;
    }

    public void setSubBabID(Integer subBabID) {
        this.subBabID = subBabID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJenisVideo() {
        return jenisVideo;
    }

    public void setJenisVideo(String jenisVideo) {
        this.jenisVideo = jenisVideo;
    }

    public String getJudulSubBab() {
        return judulSubBab;
    }

    public void setJudulSubBab(String judulSubBab) {
        this.judulSubBab = judulSubBab;
    }

    public Integer getBabID() {
        return babID;
    }

    public void setBabID(Integer babID) {
        this.babID = babID;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }

    public Integer getTingkatPelajaranID() {
        return tingkatPelajaranID;
    }

    public void setTingkatPelajaranID(Integer tingkatPelajaranID) {
        this.tingkatPelajaranID = tingkatPelajaranID;
    }

    public String getStatusLearningLine() {
        return statusLearningLine;
    }

    public void setStatusLearningLine(String statusLearningLine) {
        this.statusLearningLine = statusLearningLine;
    }

    public String getStatusAksesLatihan() {
        return statusAksesLatihan;
    }

    public void setStatusAksesLatihan(String statusAksesLatihan) {
        this.statusAksesLatihan = statusAksesLatihan;
    }

    public String getStatusAksesKonsultasi() {
        return statusAksesKonsultasi;
    }

    public void setStatusAksesKonsultasi(String statusAksesKonsultasi) {
        this.statusAksesKonsultasi = statusAksesKonsultasi;
    }

    public String getStatusAksesLearningLine() {
        return statusAksesLearningLine;
    }

    public void setStatusAksesLearningLine(String statusAksesLearningLine) {
        this.statusAksesLearningLine = statusAksesLearningLine;
    }

    public Integer getTingkatID() {
        return tingkatID;
    }

    public void setTingkatID(Integer tingkatID) {
        this.tingkatID = tingkatID;
    }

    public Integer getMataPelajaranID() {
        return mataPelajaranID;
    }

    public void setMataPelajaranID(Integer mataPelajaranID) {
        this.mataPelajaranID = mataPelajaranID;
    }

    public String getAliasTingkat() {
        return aliasTingkat;
    }

    public void setAliasTingkat(String aliasTingkat) {
        this.aliasTingkat = aliasTingkat;
    }

    public String getNamaTingkat() {
        return namaTingkat;
    }

    public void setNamaTingkat(String namaTingkat) {
        this.namaTingkat = namaTingkat;
    }

    public Object getDepedensi() {
        return depedensi;
    }

    public void setDepedensi(Object depedensi) {
        this.depedensi = depedensi;
    }

    public String getAliasMataPelajaran() {
        return aliasMataPelajaran;
    }

    public void setAliasMataPelajaran(String aliasMataPelajaran) {
        this.aliasMataPelajaran = aliasMataPelajaran;
    }

    public String getNamaMataPelajaran() {
        return namaMataPelajaran;
    }

    public void setNamaMataPelajaran(String namaMataPelajaran) {
        this.namaMataPelajaran = namaMataPelajaran;
    }

    public Integer getVideoID() {
        return videoID;
    }

    public void setVideoID(Integer videoID) {
        this.videoID = videoID;
    }

    public Integer getSubbabID() {
        return subbabID;
    }

    public void setSubbabID(Integer subbabID) {
        this.subbabID = subbabID;
    }
}
