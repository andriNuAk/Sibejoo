package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.Materi;
import vortex.sibejoo.R;

/**
 * Created by M on 10/18/2017.
 */

public class ListMateriAdapter extends BaseAdapter {

    Context context;
    ArrayList<Materi> mMateri;
    LayoutInflater inflater;

    public ListMateriAdapter(Context context, ArrayList<Materi> mMateri) {
        this.context = context;
        this.mMateri = mMateri;
    }

    @Override
    public int getCount() {
        return mMateri.size();
    }

    @Override
    public Object getItem(int position) {
        return mMateri.get(0);
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
            convertView = inflater.inflate(R.layout.list_materi, parent, false);
        }

        TextView txtNamaMapel = (TextView) convertView.findViewById(R.id.txtNamaMateri);
        String jenisVideo = mMateri.get(position).getJenisVideo();

        if (jenisVideo.equals("1")){
            txtNamaMapel.setText(mMateri.get(position).getJudulVideo() + " (R)");
        } else if (jenisVideo.equals("2")){
            txtNamaMapel.setText(mMateri.get(position).getJudulVideo() + " (S)");
        }


        return convertView;
    }
}
