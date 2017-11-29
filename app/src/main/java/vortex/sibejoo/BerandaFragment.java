package vortex.sibejoo;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyfishjy.library.RippleBackground;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import helper.SessionHelper;
import helper.SiswaHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    int penggunaID;
    ArrayList<SiswaHelper> penggunaHelper;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    CircleImageView imgSD, imgSMP, imgSMA, imgSMAIPA, imgSMAIPS;
    RippleBackground rippleBackgroundSD, rippleBackgroundSMP, rippleBackgroundSMA, rippleBackgroundSMAIPA, rippleBackgroundSMAIPS;
    View rootview;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_beranda, container, false);

        rippleBackgroundSD =(RippleBackground) rootview.findViewById(R.id.rippleSD);
        rippleBackgroundSMP =(RippleBackground) rootview.findViewById(R.id.rippleSMP);
        rippleBackgroundSMA=(RippleBackground) rootview.findViewById(R.id.rippleSMA);
        rippleBackgroundSMAIPA=(RippleBackground) rootview.findViewById(R.id.rippleSMAIPA);
        rippleBackgroundSMAIPS=(RippleBackground) rootview.findViewById(R.id.rippleSMAIPS);
        imgSD = (CircleImageView) rootview.findViewById(R.id.imgSD);
        imgSMP = (CircleImageView) rootview.findViewById(R.id.imgSMP);
        imgSMA = (CircleImageView) rootview.findViewById(R.id.imgSMA);
        imgSMAIPA = (CircleImageView) rootview.findViewById(R.id.imgSMAIPA);
        imgSMAIPS = (CircleImageView) rootview.findViewById(R.id.imgSMAIPS);

        stopRipple();

        imgSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleBackgroundSD.startRippleAnimation();
                Intent intent = new Intent(getContext().getApplicationContext(), MataPelajaranActivity.class);
                intent.putExtra("tingkatID", "1");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //rippleBackgroundSD.stopRippleAnimation();
            }
        });


        imgSMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleBackgroundSMP.startRippleAnimation();
                Intent intent = new Intent(getContext().getApplicationContext(), MataPelajaranActivity.class);
                intent.putExtra("tingkatID", "2");
                startActivity(intent);
            }
        });

        imgSMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleBackgroundSMA.startRippleAnimation();
                Intent intent = new Intent(getContext().getApplicationContext(), MataPelajaranActivity.class);
                intent.putExtra("tingkatID", "3");
                startActivity(intent);
            }
        });


        imgSMAIPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleBackgroundSMAIPA.startRippleAnimation();
                Intent intent = new Intent(getContext().getApplicationContext(), MataPelajaranActivity.class);
                intent.putExtra("tingkatID", "4");
                startActivity(intent);
            }
        });


        imgSMAIPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rippleBackgroundSMAIPS.startRippleAnimation();
                Intent intent = new Intent(getContext().getApplicationContext(), MataPelajaranActivity.class);
                intent.putExtra("tingkatID", "5");
                startActivity(intent);
            }
        });

        return rootview;
    }


//    private void confirmDialog() {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BerandaActivity.this);
//
//        // Setting Dialog Title
//        alertDialog.setTitle("Logout");
//
//        // Setting Dialog Message
//        alertDialog.setMessage("Apakah anda yakin untuk logout?");
//
//        // Setting icon
//        alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_lock_power_off));
//
//        // Setting Icon to Dialog
//        //alertDialog.setIcon(R.drawable.delete);
//
//        // Setting Positive "Yes" Button
//        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which) {
//
//                // Write your code here to invoke YES event
//                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
//                initPengguna();
//                SessionHelper dbHelper =  new SessionHelper(BerandaActivity.this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                dbHelper.deleteDataAkun(db, penggunaID);
//                initPengguna();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });
//
//        // Setting Negative "NO" Button
//        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                // Write your code here to invoke NO event
//                dialog.cancel();
//            }
//        });
//
//        // Showing Alert Message
//        alertDialog.show();
//    }

//    private void initPengguna() {
//        dbHelper = new SessionHelper(getApplicationContext());
//        db = dbHelper.getWritableDatabase();
//        try {
////            loadDataMahasiswa();
//            Cursor cursor = dbHelper.getAllAkun(db);
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
////            String data = cursor.getString(0)+" - "+cursor.getString(1);
////            akun.add(data);
//                penggunaHelper = new ArrayList();
//
//                penggunaID = Integer.valueOf(cursor.getString(0));
////                        inNamaDepan = cursor.getString(1);
////                        inNamaBelakang = cursor.getString(2);
////                        inAlamat = cursor.getString(3);
////                        inNoKontak = cursor.getString(4);
////                        inBio = cursor.getString(5);
//                SiswaHelper ph = new SiswaHelper(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
//                penggunaHelper.add(ph);
//                cursor.moveToNext();
//            }
//// close cursor
//            cursor.close();
////            Collections.sort(akun);
//        } catch (Exception e){
//            Log.e("masuk","-> "+e.getMessage()) ;
//        }
//
//        System.out.println("Udah ada di arraylist, datanya : kode user = "+penggunaID);
//    }

    public void stopRipple(){
        rippleBackgroundSD.stopRippleAnimation();
        rippleBackgroundSMP.stopRippleAnimation();
        rippleBackgroundSMA.stopRippleAnimation();
        rippleBackgroundSMAIPA.stopRippleAnimation();
        rippleBackgroundSMAIPS.stopRippleAnimation();
    }

}
