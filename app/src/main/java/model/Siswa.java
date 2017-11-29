package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 10/11/2017.
 */

public class Siswa {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nisn")
    @Expose
    private String nisn;
    @SerializedName("namaDepan")
    @Expose
    private String namaDepan;
    @SerializedName("namaBelakang")
    @Expose
    private String namaBelakang;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("tgl_lahir")
    @Expose
    private Object tglLahir;
    @SerializedName("noKontak")
    @Expose
    private String noKontak;
    @SerializedName("namaSekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("alamatSekolah")
    @Expose
    private String alamatSekolah;
    @SerializedName("noKontakSekolah")
    @Expose
    private Integer noKontakSekolah;
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("biografi")
    @Expose
    private String biografi;
    @SerializedName("cabangID")
    @Expose
    private Integer cabangID;
    @SerializedName("noIndukNeutron")
    @Expose
    private String noIndukNeutron;
    @SerializedName("tingkatID")
    @Expose
    private Integer tingkatID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mentorID")
    @Expose
    private Integer mentorID;
    @SerializedName("id_kelompok_kelas")
    @Expose
    private Integer idKelompokKelas;
    @SerializedName("namaPengguna")
    @Expose
    private String namaPengguna;
    @SerializedName("kataSandi")
    @Expose
    private String kataSandi;
    @SerializedName("eMail")
    @Expose
    private String eMail;
    @SerializedName("regTime")
    @Expose
    private Object regTime;
    @SerializedName("aktivasi")
    @Expose
    private Object aktivasi;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("oauth_uid")
    @Expose
    private Object oauthUid;
    @SerializedName("oauth_provider")
    @Expose
    private Object oauthProvider;
    @SerializedName("hakAkses")
    @Expose
    private String hakAkses;
    @SerializedName("last_akses")
    @Expose
    private Object lastAkses;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("uuid_user")
    @Expose
    private Object uuidUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
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

    public Object getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Object tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoKontak() {
        return noKontak;
    }

    public void setNoKontak(String noKontak) {
        this.noKontak = noKontak;
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

    public Integer getNoKontakSekolah() {
        return noKontakSekolah;
    }

    public void setNoKontakSekolah(Integer noKontakSekolah) {
        this.noKontakSekolah = noKontakSekolah;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiografi() {
        return biografi;
    }

    public void setBiografi(String biografi) {
        this.biografi = biografi;
    }

    public Integer getCabangID() {
        return cabangID;
    }

    public void setCabangID(Integer cabangID) {
        this.cabangID = cabangID;
    }

    public String getNoIndukNeutron() {
        return noIndukNeutron;
    }

    public void setNoIndukNeutron(String noIndukNeutron) {
        this.noIndukNeutron = noIndukNeutron;
    }

    public Integer getTingkatID() {
        return tingkatID;
    }

    public void setTingkatID(Integer tingkatID) {
        this.tingkatID = tingkatID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMentorID() {
        return mentorID;
    }

    public void setMentorID(Integer mentorID) {
        this.mentorID = mentorID;
    }

    public Integer getIdKelompokKelas() {
        return idKelompokKelas;
    }

    public void setIdKelompokKelas(Integer idKelompokKelas) {
        this.idKelompokKelas = idKelompokKelas;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Object getRegTime() {
        return regTime;
    }

    public void setRegTime(Object regTime) {
        this.regTime = regTime;
    }

    public Object getAktivasi() {
        return aktivasi;
    }

    public void setAktivasi(Object aktivasi) {
        this.aktivasi = aktivasi;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getOauthUid() {
        return oauthUid;
    }

    public void setOauthUid(Object oauthUid) {
        this.oauthUid = oauthUid;
    }

    public Object getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(Object oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public Object getLastAkses() {
        return lastAkses;
    }

    public void setLastAkses(Object lastAkses) {
        this.lastAkses = lastAkses;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Object getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(Object uuidUser) {
        this.uuidUser = uuidUser;
    }
}
