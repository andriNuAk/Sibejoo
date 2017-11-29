package vortex.sibejoo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

import adapter.ListMapelLearningLineAdapter;
import api.APIClient;
import api.APIInterface;
import model.EduDrive;
import model.EduDriveResponse;
import model.MapelLearningLine;
import model.MapelLearningLineResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLineFragment extends Fragment {

    ArrayList<MapelLearningLine> mMapelLearningLine;
    View rootview;
    ListMapelLearningLineAdapter listMapelLearningLineAdapter;
    ExpandableHeightListView listMapel;
    Button btnSD, btnSMP, btnSMA, btnSMAIPA, btnSMAIPS;
    RelativeLayout relNull;
    int tingkat = 1;


    public LearningLineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_learning_line, container, false);

        listMapel = (ExpandableHeightListView) rootview.findViewById(R.id.listMapelLearningLine);
        btnSD = (Button) rootview.findViewById(R.id.btnSD);
        btnSMP = (Button) rootview.findViewById(R.id.btnSMP);
        btnSMA = (Button) rootview.findViewById(R.id.btnSMA);
        btnSMAIPA = (Button) rootview.findViewById(R.id.btnSMAIPA);
        btnSMAIPS = (Button) rootview.findViewById(R.id.btnSMAIPS);
        relNull = (RelativeLayout) rootview.findViewById(R.id.relNull);

        //init terlaris
        btnSD.setBackgroundResource(R.drawable.button_blue_pressed);
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(1);
        call.enqueue(new Callback<MapelLearningLineResponse>() {
            @Override
            public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                mMapelLearningLine = mapelLearningLines;

                if (mMapelLearningLine != null){
                    listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                    listMapel.setAdapter(listMapelLearningLineAdapter);
                    listMapel.setExpanded(true);
                    relNull.setVisibility(View.GONE);
                }else {
                    relNull.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                listMapel.setVisibility(View.GONE);
                relNull.setVisibility(View.VISIBLE);
            }
        });

        iniSearchButton();

        listMapel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext().getApplicationContext(), BabLearningLineActivity.class);
                intent.putExtra("tingkatID", String.valueOf(tingkat));
                intent.putExtra("keterangan", mMapelLearningLine.get(position).getMapel().toString());
                startActivity(intent);
            }
        });

        return rootview;
    }

    public void iniSearchButton(){
        final APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        btnSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tingkat = 1;
                retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(1);
                call.enqueue(new Callback<MapelLearningLineResponse>() {
                    @Override
                    public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                        Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                        listMapel.setVisibility(View.VISIBLE);
                        ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                        mMapelLearningLine = mapelLearningLines;
                        if (mMapelLearningLine != null){
                            listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                            listMapel.setAdapter(listMapelLearningLineAdapter);
                            listMapel.setExpanded(true);
                            relNull.setVisibility(View.GONE);
                        }else {
                            relNull.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                        listMapel.setVisibility(View.GONE);
                        relNull.setVisibility(View.VISIBLE);
                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        btnSMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tingkat = 2;
                retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(2);
                call.enqueue(new Callback<MapelLearningLineResponse>() {
                    @Override
                    public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                        Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                        listMapel.setVisibility(View.VISIBLE);
                        ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                        mMapelLearningLine = mapelLearningLines;
                        if (mMapelLearningLine != null){
                            listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                            listMapel.setAdapter(listMapelLearningLineAdapter);
                            listMapel.setExpanded(true);
                            relNull.setVisibility(View.GONE);
                        }else {
                            relNull.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                        listMapel.setVisibility(View.GONE);
                        relNull.setVisibility(View.VISIBLE);
                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_blue_pressed);
            }
        });

        btnSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tingkat = 3;
                retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(3);
                call.enqueue(new Callback<MapelLearningLineResponse>() {
                    @Override
                    public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                        Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                        listMapel.setVisibility(View.VISIBLE);
                        ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                        mMapelLearningLine = mapelLearningLines;
                        if (mMapelLearningLine != null){
                            listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                            listMapel.setAdapter(listMapelLearningLineAdapter);
                            listMapel.setExpanded(true);
                            relNull.setVisibility(View.GONE);
                        }else {
                            relNull.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                        listMapel.setVisibility(View.GONE);
                        relNull.setVisibility(View.VISIBLE);
                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        btnSMAIPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tingkat = 4;
                retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(4);
                call.enqueue(new Callback<MapelLearningLineResponse>() {
                    @Override
                    public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                        Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                        listMapel.setVisibility(View.VISIBLE);
                        ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                        mMapelLearningLine = mapelLearningLines;
                        if (mMapelLearningLine != null){
                            listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                            listMapel.setAdapter(listMapelLearningLineAdapter);
                            listMapel.setExpanded(true);
                            relNull.setVisibility(View.GONE);
                        }else {
                            relNull.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                        listMapel.setVisibility(View.GONE);
                        relNull.setVisibility(View.VISIBLE);
                    }
                });

                btnSD.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPS.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMAIPA.setBackgroundResource(R.drawable.button_blue_pressed);
                btnSMA.setBackgroundResource(R.drawable.button_green_pressed);
                btnSMP.setBackgroundResource(R.drawable.button_green_pressed);
            }
        });

        btnSMAIPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tingkat = 5;
                retrofit2.Call<MapelLearningLineResponse> call = apiService.getMapelLearningLine(5);
                call.enqueue(new Callback<MapelLearningLineResponse>() {
                    @Override
                    public void onResponse(Call<MapelLearningLineResponse> call, Response<MapelLearningLineResponse> response) {
//                        Toast.makeText(rootview.getContext(), "Ada data", Toast.LENGTH_LONG).show();
                        listMapel.setVisibility(View.VISIBLE);
                        ArrayList<MapelLearningLine> mapelLearningLines = (ArrayList<MapelLearningLine>) response.body().getMapelLearningLine();
                        mMapelLearningLine = mapelLearningLines;
                        if (mMapelLearningLine != null){
                            listMapelLearningLineAdapter = new ListMapelLearningLineAdapter(rootview.getContext(), mMapelLearningLine);
                            listMapel.setAdapter(listMapelLearningLineAdapter);
                            listMapel.setExpanded(true);
                            relNull.setVisibility(View.GONE);
                        } else {
                            relNull.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<MapelLearningLineResponse> call, Throwable t) {
                        listMapel.setVisibility(View.GONE);
                        relNull.setVisibility(View.VISIBLE);
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
