package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

import model.MapelLearningLine;
import vortex.sibejoo.R;

/**
 * Created by M on 11/6/2017.
 */

public class ListMapelLearningLineAdapter extends BaseAdapter {
    Context context;
    ArrayList<MapelLearningLine> mMapelLearningLine;
    LayoutInflater inflater;

    public ListMapelLearningLineAdapter(Context context, ArrayList<MapelLearningLine> mMapelLearningLine) {
        this.context = context;
        this.mMapelLearningLine = mMapelLearningLine;
    }

    @Override
    public int getCount() {
        return mMapelLearningLine.size();
    }

    @Override
    public Object getItem(int position) {
        return mMapelLearningLine.get(position);
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
            convertView = inflater.inflate(R.layout.card_mapel_learning_line, parent, false);
        }

        TextView txtNamaMapel;
        String uri = null;
        ImageView imgIcon;
        String mapel = mMapelLearningLine.get(position).getMapel();

        txtNamaMapel = (TextView) convertView.findViewById(R.id.txtNamaMapelLearningLine);
        imgIcon = (ImageView) convertView.findViewById(R.id.imgIconMapelLearningLine);

        if (mapel.substring(0, 3).equalsIgnoreCase("IND")){
            uri = "@drawable/bahasa";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("IPA")){
            uri = "@drawable/kimia";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("ING") || mapel.substring(0, 5).equalsIgnoreCase("B ING")){
            uri = "@drawable/bahasa";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("IPS")){
            uri = "@drawable/geografi";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("FIS")){
            uri = "@drawable/fisika";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("MAT")){
            uri = "@drawable/matematika";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("BIO")){
            uri = "@drawable/biologi";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("KIM")){
            uri = "@drawable/kimia";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("SOS")){
            uri = "@drawable/sosiologi";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("GEO")){
            uri = "@drawable/geografi";
        } else if (mapel.substring(0, 3).equalsIgnoreCase("EKO")){
            uri = "@drawable/ekonomi";
        } else {
            uri = "@drawable/geografi";
        }



        int imageResource = convertView.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = convertView.getResources().getDrawable(imageResource);

        imgIcon.setImageDrawable(res);
        txtNamaMapel.setText(mMapelLearningLine.get(position).getMapel());

        return convertView;
    }
}
