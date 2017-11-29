package helper;

/**
 * Created by M on 10/12/2017.
 */

public class SiswaHelper {
    private int penggunaID;
    private String namaDepan;
    private String namaBelakang;
    private String alamat;
    private String noKontak;
    private String namaSekolah;
    private String alamatSekolah;
    private String photo;
    private String biografi;
    private String kataSandi;
    private String eMail;


    public SiswaHelper(int penggunaID, String namaDepan, String namaBelakang, String alamat, String noKontak, String namaSekolah, String alamatSekolah, String photo, String biografi, String kataSandi, String eMail) {
        this.penggunaID = penggunaID;
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.alamat = alamat;
        this.noKontak = noKontak;
        this.namaSekolah = namaSekolah;
        this.alamatSekolah = alamatSekolah;
        this.photo = photo;
        this.biografi = biografi;
        this.kataSandi = kataSandi;
        this.eMail = eMail;
    }

    public int getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(int penggunaID) {
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

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
