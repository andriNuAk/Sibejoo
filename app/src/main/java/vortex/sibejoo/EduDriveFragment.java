package vortex.sibejoo;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import adapter.ListEduDriveAdapter;
import adapter.ListRecomendAdapter;
import api.APIClient;
import api.APIInterface;
import model.EduDrive;
import model.EduDriveResponse;
import model.MataPelajaranResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EduDriveFragment extends Fragment {


    RecyclerView recyclerRecomend;
    TextView txtDownloadTeratas;
    ListRecomendAdapter listRecomendAdapter;
    ArrayList<EduDrive> mEduDrive;
    ExpandableHeightListView listEdu;
    ListEduDriveAdapter listEduDriveAdapter;
    EditText txtCari;
    CardView cardLayoutRecomend;
    ProgressBar progressBar_cyclic, progressBar_cyclic2;
    Button btnSD, btnSMP, btnSMA, btnSMAIPA, btnSMAIPS;
    View rootview;

    public EduDriveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_edu_drive, container, false);

        recyclerRecomend = (RecyclerView) rootview.findViewById(R.id.recyclerRecomend);
        txtDownloadTeratas = (TextView) rootview.findViewById(R.id.txtDownloadTeratas);
        listEdu = (ExpandableHeightListView) rootview.findViewById(R.id.listEdu);
        btnSD = (Button) rootview.findViewById(R.id.btnSD);
        btnSMP = (Button) rootview.findViewById(R.id.btnSMP);
        btnSMA = (Button) rootview.findViewById(R.id.btnSMA);
        btnSMAIPA = (Button) rootview.findViewById(R.id.btnSMAIPA);
        btnSMAIPS = (Button) rootview.findViewById(R.id.btnSMAIPS);
        txtCari =(EditText) rootview.findViewById(R.id.txtCari);
        cardLayoutRecomend = (CardView) rootview.findViewById(R.id.cardLayoutRecomend);
        progressBar_cyclic = (ProgressBar) rootview.findViewById(R.id.progressBar_cyclic);
        progressBar_cyclic.setIndeterminate(true);
        progressBar_cyclic.getIndeterminateDrawable().setColorFilter(0xFF202C45, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar_cyclic2 = (ProgressBar) rootview.findViewById(R.id.progressBar_cyclic2);
        progressBar_cyclic2.setIndeterminate(true);
        progressBar_cyclic2.getIndeterminateDrawable().setColorFilter(0xFF202C45, android.graphics.PorterDuff.Mode.MULTIPLY);
        recyclerRecomend.setVisibility(View.GONE);
        listEdu.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listEdu.setNestedScrollingEnabled(true);
            recyclerRecomend.setNestedScrollingEnabled(true);
        }

        initSearchButton();

        txtCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(rootview.getContext(), "Key "+s, Toast.LENGTH_SHORT).show();
                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByJudul(s.toString());
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //init terlaris
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveFav();
        call.enqueue(new Callback<EduDriveResponse>() {
            @Override
            public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
//                txtDownloadTeratas.setText(eduDriveList.get(0).getJudul());
                mEduDrive = eduDriveList;
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false);


                listRecomendAdapter = new ListRecomendAdapter(mEduDrive, rootview.getContext());
                recyclerRecomend.setHasFixedSize(true);
                recyclerRecomend.setLayoutManager(layoutManager);
                recyclerRecomend.setAdapter(listRecomendAdapter);
                recyclerRecomend.setVisibility(View.VISIBLE);
                progressBar_cyclic.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<EduDriveResponse> call, Throwable t) {

            }
        });

        //init modul
        retrofit2.Call<EduDriveResponse> call2 = apiService.getEduDrive();
        call2.enqueue(new Callback<EduDriveResponse>() {
            @Override
            public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                mEduDrive = eduDriveList;

                listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                listEdu.setAdapter(listEduDriveAdapter);
                listEdu.setExpanded(true);
                listEdu.setVisibility(View.VISIBLE);
                progressBar_cyclic2.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<EduDriveResponse> call, Throwable t) {

            }
        });

        return rootview;
    }

    public void initSearchButton(){
        final APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        //kategori SD
        btnSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByTingkat(1);
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        //kategori SMP
        btnSMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByTingkat(2);
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_blue_pressed);
            }
        });


        //kategori SMA
        btnSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByTingkat(3);
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        //kategori SMA IPA
        btnSMAIPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByTingkat(4);
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        //kategori SMA IPS
        btnSMAIPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit2.Call<EduDriveResponse> call = apiService.getEduDriveByTingkat(5);
                listEdu.setVisibility(View.GONE);
                progressBar_cyclic2.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<EduDriveResponse>() {
                    @Override
                    public void onResponse(Call<EduDriveResponse> call, Response<EduDriveResponse> response) {
                        ArrayList<EduDrive> eduDriveList = (ArrayList<EduDrive>) response.body().getEduDrive();
                        mEduDrive = eduDriveList;

                        cardLayoutRecomend.setVisibility(View.GONE);

                        listEduDriveAdapter = new ListEduDriveAdapter(rootview.getContext(), mEduDrive);
                        listEdu.setAdapter(listEduDriveAdapter);
                        listEdu.setExpanded(true);

                        listEdu.setVisibility(View.VISIBLE);
                        progressBar_cyclic2.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<EduDriveResponse> call, Throwable t) {

                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });
    }

}
