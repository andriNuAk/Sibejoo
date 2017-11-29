package vortex.sibejoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

import adapter.ListMapelAdapter;
import api.APIClient;
import api.APIInterface;
import model.MataPelajaran;
import model.MataPelajaranResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapelActivity extends AppCompatActivity {

    ArrayList<MataPelajaran> mMataPelajaran;
    ExpandableHeightListView listMapel;
    ListMapelAdapter listMapelAdapter;
    CardView cardListMapel;
    TextView txtText;
    int tingkatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listMapel = (ExpandableHeightListView) findViewById(R.id.listMapel);
        cardListMapel = (CardView) findViewById(R.id.cardListMapel);
        txtText = (TextView) findViewById(R.id.txtText);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            tingkatID = Integer.valueOf(bundle.getString("tingkatID"));
        }

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<MataPelajaranResponse> call = apiService.getMapel(tingkatID);
        call.enqueue(new Callback<MataPelajaranResponse>() {
            @Override
            public void onResponse(Call<MataPelajaranResponse> call, Response<MataPelajaranResponse> response) {
                ArrayList<MataPelajaran> mataPelajaren = (ArrayList<MataPelajaran>) response.body().getMataPelajaran();
                mMataPelajaran = mataPelajaren;
                System.out.println("eaaaakkkk"+mMataPelajaran);
                if (mataPelajaren != null || mataPelajaren.isEmpty() == false){
                    listMapelAdapter = new ListMapelAdapter(getApplicationContext(), mMataPelajaran);
                    listMapel.setAdapter(listMapelAdapter);
                    listMapel.setExpanded(true);
                }
            }

            @Override
            public void onFailure(Call<MataPelajaranResponse> call, Throwable t) {
                cardListMapel.setVisibility(View.GONE);
                txtText.setText("Tidak ada data mata pelajaran");
            }
        });

        listMapel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MateriActivity.class);
                intent.putExtra("IdMapel", mMataPelajaran.get(position).getId().toString());
                startActivity(intent);

//                Toast.makeText(getApplicationContext(), "Id : "+mMataPelajaran.get(position).getId(), Toast.LENGTH_LONG).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
//            BerandaActivity ba = new BerandaActivity();
//            ba.stopRipple();
//            super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
