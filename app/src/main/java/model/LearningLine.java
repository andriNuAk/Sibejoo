package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 11/9/2017.
 */

public class LearningLine {
    @SerializedName("topikId")
    @Expose
    private Integer topikId;
    @SerializedName("namaTopik")
    @Expose
    private String namaTopik;
    @SerializedName("namaStep")
    @Expose
    private String namaStep;
    @SerializedName("stepUUID")
    @Expose
    private String stepUUID;
    @SerializedName("jenisStep")
    @Expose
    private String jenisStep;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("judulBab")
    @Expose
    private String judulBab;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("aliasTingkat")
    @Expose
    private String aliasTingkat;
    @SerializedName("latihanID")
    @Expose
    private Object latihanID;
    @SerializedName("stepID")
    @Expose
    private Integer stepID;
    @SerializedName("urutan")
    @Expose
    private Integer urutan;
    @SerializedName("linlog_id")
    @Expose
    private Object linlogId;
    @SerializedName("linlog_penggunaID")
    @Expose
    private Object linlogPenggunaID;
    @SerializedName("linelog_statusStep")
    @Expose
    private Object linelogStatusStep;
    @SerializedName("linelog_stepID")
    @Expose
    private Object linelogStepID;

    public Integer getTopikId() {
        return topikId;
    }

    public void setTopikId(Integer topikId) {
        this.topikId = topikId;
    }

    public String getNamaTopik() {
        return namaTopik;
    }

    public void setNamaTopik(String namaTopik) {
        this.namaTopik = namaTopik;
    }

    public String getNamaStep() {
        return namaStep;
    }

    public void setNamaStep(String namaStep) {
        this.namaStep = namaStep;
    }

    public String getStepUUID() {
        return stepUUID;
    }

    public void setStepUUID(String stepUUID) {
        this.stepUUID = stepUUID;
    }

    public String getJenisStep() {
        return jenisStep;
    }

    public void setJenisStep(String jenisStep) {
        this.jenisStep = jenisStep;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getJudulBab() {
        return judulBab;
    }

    public void setJudulBab(String judulBab) {
        this.judulBab = judulBab;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getAliasTingkat() {
        return aliasTingkat;
    }

    public void setAliasTingkat(String aliasTingkat) {
        this.aliasTingkat = aliasTingkat;
    }

    public Object getLatihanID() {
        return latihanID;
    }

    public void setLatihanID(Object latihanID) {
        this.latihanID = latihanID;
    }

    public Integer getStepID() {
        return stepID;
    }

    public void setStepID(Integer stepID) {
        this.stepID = stepID;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public Object getLinlogId() {
        return linlogId;
    }

    public void setLinlogId(Object linlogId) {
        this.linlogId = linlogId;
    }

    public Object getLinlogPenggunaID() {
        return linlogPenggunaID;
    }

    public void setLinlogPenggunaID(Object linlogPenggunaID) {
        this.linlogPenggunaID = linlogPenggunaID;
    }

    public Object getLinelogStatusStep() {
        return linelogStatusStep;
    }

    public void setLinelogStatusStep(Object linelogStatusStep) {
        this.linelogStatusStep = linelogStatusStep;
    }

    public Object getLinelogStepID() {
        return linelogStepID;
    }

    public void setLinelogStepID(Object linelogStepID) {
        this.linelogStepID = linelogStepID;
    }


}
