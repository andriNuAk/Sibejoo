package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/26/2017.
 */

public class BabResponse {
    @SerializedName("Bab")
    @Expose
    private List<Bab> bab = null;

    public List<Bab> getBab() {
        return bab;
    }

    public void setBab(List<Bab> bab) {
        this.bab = bab;
    }
}
