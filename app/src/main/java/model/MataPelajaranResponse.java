package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/17/2017.
 */

public class MataPelajaranResponse {
    @SerializedName("MataPelajaran")
    @Expose
    private List<MataPelajaran> mataPelajaran = null;

    public List<MataPelajaran> getMataPelajaran() {
        return mataPelajaran;
    }

    public void setMataPelajaran(List<MataPelajaran> mataPelajaran) {
        this.mataPelajaran = mataPelajaran;
    }
}
