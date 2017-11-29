package helper;

/**
 * Created by M on 10/10/2017.
 */

public class ProfilHelper {
    String img;
    String textLabel;
    String value;

    public ProfilHelper(String img, String textLabel, String value) {
        this.img = img;
        this.textLabel = textLabel;
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(String textLabel) {
        this.textLabel = textLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
