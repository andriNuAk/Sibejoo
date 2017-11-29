package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import model.MataPelajaran;
import vortex.sibejoo.R;

/**
 * Created by M on 10/17/2017.
 */

public class ListMapelAdapter extends BaseAdapter {

    Context context;
    ArrayList<MataPelajaran> mataPelajaran;
    LayoutInflater inflater;

    public ListMapelAdapter(Context context, ArrayList<MataPelajaran> mataPelajaran) {
        this.context = context;
        this.mataPelajaran = mataPelajaran;
    }

    @Override
    public int getCount() {
        return mataPelajaran.size();
    }

    @Override
    public Object getItem(int position) {
        return mataPelajaran.get(position);
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
            convertView = inflater.inflate(R.layout.list_mapel, parent, false);
        }


        ImageView imgIconMapel = (ImageView) convertView.findViewById(R.id.imgIconMapel);
        TextView txtNamaMapel = (TextView) convertView.findViewById(R.id.txtNamaMapel);

        String uri = null;
        String mapel = mataPelajaran.get(position).getNamaMataPelajaran();

        if (mapel.equals("Ilmu Pengetahuan Sosial")){
            uri = "@drawable/geografi";
        } else if (mapel.equals("Ilmu Pengetahuan Alam")){
            uri = "@drawable/kimia";
        } else if (mapel.equals("Bahasa Indonesia") || mapel.equals("Bahasa Inggris")){
            uri = "@drawable/bahasa";
        } else if (mapel.equals("Fisika")){
            uri = "@drawable/fisika";
        } else if (mapel.equals("Kimia")){
            uri = "@drawable/kimia";
        } else if (mapel.equals("Biologi")){
            uri = "@drawable/biologi";
        } else if (mapel.equals("Sosiologi")){
            uri = "@drawable/sosiologi";
        } else if (mapel.equals("Matematika") || mapel.equals("Matematika IPA")){
            uri = "@drawable/matematika";
        } else if (mapel.equals("Sejarah")){
            uri = "@drawable/sejarah";
        } else if (mapel.equals("Geografi")){
            uri = "@drawable/geografi";
        } else if (mapel.equals("Ekonomi")){
            uri = "@drawable/ekonomi";
        } else {
            uri = "@drawable/tryout";
        }

        int imageResource = convertView.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = convertView.getResources().getDrawable(imageResource);

        imgIconMapel.setImageDrawable(res);
        txtNamaMapel.setText(mataPelajaran.get(position).getNamaMataPelajaran());

        return convertView;
    }
}
