package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/18/2017.
 */

public class MateriResponse {
    @SerializedName("Materi")
    @Expose
    private List<Materi> materi = null;

    public List<Materi> getMateri() {
        return materi;
    }

    public void setMateri(List<Materi> materi) {
        this.materi = materi;
    }
}
