package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/17/2017.
 */

public class MataPelajaran {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tingkatID")
    @Expose
    private Integer tingkatID;
    @SerializedName("matapelajaranID")
    @Expose
    private Integer matapelajaranID;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("namaMataPelajaran")
    @Expose
    private String namaMataPelajaran;
    @SerializedName("aliasMataPelajaran")
    @Expose
    private String aliasMataPelajaran;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTingkatID() {
        return tingkatID;
    }

    public void setTingkatID(Integer tingkatID) {
        this.tingkatID = tingkatID;
    }

    public Integer getMatapelajaranID() {
        return matapelajaranID;
    }

    public void setMatapelajaranID(Integer matapelajaranID) {
        this.matapelajaranID = matapelajaranID;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaMataPelajaran() {
        return namaMataPelajaran;
    }

    public void setNamaMataPelajaran(String namaMataPelajaran) {
        this.namaMataPelajaran = namaMataPelajaran;
    }

    public String getAliasMataPelajaran() {
        return aliasMataPelajaran;
    }

    public void setAliasMataPelajaran(String aliasMataPelajaran) {
        this.aliasMataPelajaran = aliasMataPelajaran;
    }
}
