package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/31/2017.
 */

public class EduDrive {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("url_file")
    @Expose
    private String urlFile;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("create_by")
    @Expose
    private String createBy;
    @SerializedName("download")
    @Expose
    private Integer download;
    @SerializedName("tipefile")
    @Expose
    private Integer tipefile;
    @SerializedName("id_tingkatpelajaran")
    @Expose
    private Integer idTingkatpelajaran;
    @SerializedName("statusAksesFile")
    @Expose
    private String statusAksesFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Integer getTipefile() {
        return tipefile;
    }

    public void setTipefile(Integer tipefile) {
        this.tipefile = tipefile;
    }

    public Integer getIdTingkatpelajaran() {
        return idTingkatpelajaran;
    }

    public void setIdTingkatpelajaran(Integer idTingkatpelajaran) {
        this.idTingkatpelajaran = idTingkatpelajaran;
    }

    public String getStatusAksesFile() {
        return statusAksesFile;
    }

    public void setStatusAksesFile(String statusAksesFile) {
        this.statusAksesFile = statusAksesFile;
    }

}
