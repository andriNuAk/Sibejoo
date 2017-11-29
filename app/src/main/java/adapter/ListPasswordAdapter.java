package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import helper.ProfilHelper;
import vortex.sibejoo.R;

/**
 * Created by M on 10/10/2017.
 */

public class ListPasswordAdapter extends BaseAdapter {

    Context context;
    //ArrayList<BeritaModel> beritaList;
    ArrayList<ProfilHelper> profilList;
    LayoutInflater inflater;

    public ListPasswordAdapter(Context context, ArrayList<ProfilHelper> profilList) {
        this.context = context;
        this.profilList = profilList;
    }

    @Override
    public int getCount() {
        return profilList.size();
    }

    @Override
    public Object getItem(int position) {
        return profilList.get(position);
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
            convertView = inflater.inflate(R.layout.list_profil, parent, false);
        }


        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.iconProfil);
        TextView txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);
        EditText txtValue = (EditText) convertView.findViewById(R.id.txtValue);

        String icon = profilList.get(position).getImg();
        String label = profilList.get(position).getTextLabel();
        String value = profilList.get(position).getValue();
        // ini pake picasso
//        Picasso.with(context.getApplicationContext()).load(gambar).into(imgGambar);
//        System.out.println(gambar);
//        DownloadImageWithURLTask downloadTask = new DownloadImageWithURLTask(imgGambar);
//        downloadTask.execute(gambar);
//
        //ini pake drawable
        int id = context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
        imgIcon.setImageResource(id);
        txtLabel.setText(label);
        txtValue.setHint(value);


        return convertView;
    }
}
