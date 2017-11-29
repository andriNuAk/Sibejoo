package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.TopikLearningLine;
import vortex.sibejoo.R;

/**
 * Created by M on 11/9/2017.
 */

public class ListTopikLearningLineAdapter extends BaseAdapter {
    Context context;
    ArrayList<TopikLearningLine> topik;
    LayoutInflater inflater;

    public ListTopikLearningLineAdapter(Context context, ArrayList<TopikLearningLine> topik) {
        this.context = context;
        this.topik = topik;
    }

    @Override
    public int getCount() {
        return topik.size();
    }

    @Override
    public Object getItem(int position) {
        return topik.get(position);
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
        String mtopik = topik.get(position).getNamaTopik();

        txtNamaMapel.setText(mtopik);
        return convertView;
    }
}
