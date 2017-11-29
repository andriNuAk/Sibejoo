package adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;
import helper.ProfilHelper;
import model.Comment;
import vortex.sibejoo.R;

/**
 * Created by M on 10/20/2017.
 */

public class ListCommentAdapter extends BaseAdapter {

    Context context;
    //ArrayList<BeritaModel> beritaList;
    ArrayList<Comment> commentList;
    LayoutInflater inflater;

    public ListCommentAdapter(Context context, ArrayList<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_comment, parent, false);
        }

        CircleImageView imgProfil = (CircleImageView) convertView.findViewById(R.id.imgProfilComment);
        LinearLayout lineBlockquote = (LinearLayout) convertView.findViewById(R.id.lineBlockquote);
        TextView txtBlockquote, txtComment, txtPubDate, txtAuthor, txtSay;
        txtBlockquote = (TextView) convertView.findViewById(R.id.txtBlockquote);
        txtComment = (TextView) convertView.findViewById(R.id.txtComment);
        txtPubDate = (TextView) convertView.findViewById(R.id.txtPubDate);
        txtAuthor = (TextView) convertView.findViewById(R.id.txtAuthor);
        txtSay = (TextView) convertView.findViewById(R.id.txtSay);
        txtBlockquote.setTypeface(txtBlockquote.getTypeface(), Typeface.BOLD_ITALIC);

        String photo;
        if (commentList.get(position).getGuruPhoto() == null){
            if (commentList.get(position).getSiswaPhoto() != null){
                photo = commentList.get(position).getSiswaPhoto().toString();
                Picasso.with(context.getApplicationContext()).load(photo).into(imgProfil);
            }
        } else {
            photo = commentList.get(position).getGuruPhoto();
            Picasso.with(context.getApplicationContext()).load(photo).into(imgProfil);
        }

        txtSay.setText(commentList.get(position).getNamaPengguna()+ " say :");

        String comment;
        String blockquote;
        String text = commentList.get(position).getIsiKomen();
        String[] split = text.split("</blockquote>");
        for (int i = 0; i < split.length ; i++) {
            System.out.println("Index ke: "+i+" value: "+split[i]);
        }

        if (split.length == 2) {
            String[] splitBlockquote = split[0].split("<blockquote>");
            blockquote = splitBlockquote[1];
            System.out.println("Isi comment : "+split[split.length - 1]);
            System.out.println("Isi blockquote : "+blockquote);
            txtComment.setText(split[1]);
            lineBlockquote.setVisibility(View.VISIBLE);
            txtBlockquote.setText(blockquote);
        } else {
            lineBlockquote.setVisibility(View.GONE);
            System.out.println("Isi comment : "+split[0]);
            txtComment.setText(split[0]);
        }

//        txtComment.setText(commentList.get(position).getIsiKomen());
        String pubDate = commentList.get(position).getDateCreated();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = null;
        try {

            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date d = dateFormat.parse(pubDate);
            formattedTime = output.format(d);
            output.setTimeZone(TimeZone.getTimeZone("GMT+07:00"));
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("formated "+formattedTime);
        txtPubDate.setText(formattedTime);

        txtAuthor.setText(commentList.get(position).getNamaPengguna());

        return convertView;
    }
}
