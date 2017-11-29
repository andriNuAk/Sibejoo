package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/11/2017.
 */

public class SiswaResponse {
    @SerializedName("Siswa")
    @Expose
    private List<Siswa> siswa = null;

    public List<Siswa> getSiswa() {
        return siswa;
    }

    public void setSiswa(List<Siswa> siswa) {
        this.siswa = siswa;
    }
}
