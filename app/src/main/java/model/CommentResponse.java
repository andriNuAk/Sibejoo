package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 10/20/2017.
 */

public class CommentResponse {
    @SerializedName("Comment")
    @Expose
    private List<Comment> comment = null;

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
