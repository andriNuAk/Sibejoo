package vortex.sibejoo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import api.APIClient;
import api.APIInterface;
import helper.SessionHelper;
import helper.SiswaHelper;
import model.Siswa;
import model.SiswaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText txtUsername, txtPassword;
    String inNamaDepan, inNamaBelakang, inAlamat, inNoKontak, inBio, inNamaSekolah, inAlamatSekolah, inPhoto, inKataSandi, inEMail, strUsername, strPassword, md5Password;
    int penggunaID;
    ArrayList<Siswa> mSiswa;
    TextView txtStatusLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogins);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtStatusLogin = (TextView) findViewById(R.id.txtStatusLogin);

        setErrorNull();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
//                startActivity(intent);
                if (txtUsername.getText() == null || txtUsername.getText().toString().equalsIgnoreCase("")){
                    txtUsername.setError("Masukan username anda");
                    txtUsername.setFocusable(true);
                } else if (txtPassword.getText() == null || txtPassword.getText().toString().equalsIgnoreCase("")){
                    txtPassword.setError("Masukan password anda");
                    txtPassword.setFocusable(true);
                } else {
//                    System.out.println("udah akses ke db");
//                    SessionHelper dbHelper =  new SessionHelper(LoginActivity.this);
//                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    //dbHelper.deleteDataAkun(db, 15);
//                    dbHelper.createTabelPengguna(db);
//                    System.out.println("udah berhasil bikin tabel");
//                    dbHelper.insertAkun(db, 12, "asfas", "afasf", "asfasf", "asfasf", "asfasfasf", "asdasdas","asdfasf", "asfasfsa", "asfasfa","asfas");
//                    System.out.println("udah berhasil masukin data");
//
//
//                    ArrayList<SiswaHelper> penggunaHelper;
//                    dbHelper = new SessionHelper(getApplicationContext());
//                    db = dbHelper.getWritableDatabase();
//                    try {
////            loadDataMahasiswa();
//                        Cursor cursor = dbHelper.getAllAkun(db);
//                        cursor.moveToFirst();
//                        while (!cursor.isAfterLast()) {
////            String data = cursor.getString(0)+" - "+cursor.getString(1);
////            akun.add(data);
//                            penggunaHelper = new ArrayList();
//
//                            penggunaID = Integer.valueOf(cursor.getString(0));
////                        inNamaDepan = cursor.getString(1);
////                        inNamaBelakang = cursor.getString(2);
////                        inAlamat = cursor.getString(3);
////                        inNoKontak = cursor.getString(4);
////                        inBio = cursor.getString(5);
//                            SiswaHelper ph = new SiswaHelper(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
//                            penggunaHelper.add(ph);
//                            cursor.moveToNext();
//                        }
//// close cursor
//                        cursor.close();
////            Collections.sort(akun);
//                    } catch (Exception e){
//                        Log.e("masuk","-> "+e.getMessage()) ;
//                    }
//
//                    System.out.println("Udah ada di arraylist, datanya : kode user = "+penggunaID);
                    strUsername = txtUsername.getText().toString();
                    strPassword = txtPassword.getText().toString();
                    md5Password = md5(strPassword);
                    APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                    retrofit2.Call<SiswaResponse> call = apiService.getSiswa(md5Password, strUsername, strUsername);
                    call.enqueue(new Callback<SiswaResponse>() {
                        @Override
                        public void onResponse(Call<SiswaResponse> call, Response<SiswaResponse> response) {
                            ArrayList<Siswa> siswa = (ArrayList<Siswa>) response.body().getSiswa();
                            if (siswa != null){
                                mSiswa = siswa;
                                for (int i = 0; i < mSiswa.size(); i++){
                                    penggunaID = mSiswa.get(i).getPenggunaID();
                                    inNamaDepan = mSiswa.get(i).getNamaDepan();
                                    inNamaBelakang = mSiswa.get(i).getNamaBelakang();
                                    inAlamat = mSiswa.get(i).getAlamat();
                                    inNoKontak = mSiswa.get(i).getNoKontak();
                                    inBio = mSiswa.get(i).getBiografi();
                                    inNamaSekolah = mSiswa.get(i).getNamaSekolah();
                                    inAlamatSekolah = mSiswa.get(i).getAlamatSekolah();
                                    inPhoto = mSiswa.get(i).getPhoto();
                                    inKataSandi = mSiswa.get(i).getKataSandi();
                                    inEMail = mSiswa.get(i).getEMail();
                                }

                                //create session
                                Toast.makeText(getApplicationContext(), "Login sebagai : "+penggunaID+" "+mSiswa.get(0).getNamaDepan()+" "+mSiswa.get(0).getNamaBelakang(), Toast.LENGTH_LONG).show();
                                SessionHelper dbHelper =  new SessionHelper(LoginActivity.this);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                                dbHelper.deleteDataAkun(db, 12);
                                dbHelper.createTabelPengguna(db);
                                dbHelper.insertAkun(db, penggunaID, inNamaDepan, inNamaBelakang, inAlamat, inNoKontak, inNamaSekolah, inAlamatSekolah, inPhoto, inBio, inKataSandi, inEMail);
                                Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<SiswaResponse> call, Throwable t) {
//                            t.printStackTrace();
                            txtStatusLogin.setVisibility(View.VISIBLE);
                        }
                    });

                }
            }
        });

    }

    public void setErrorNull(){
        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtUsername.setError(null);
                txtStatusLogin.setVisibility(View.GONE);
            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtPassword.setError(null);
                txtStatusLogin.setVisibility(View.GONE);
            }
        });
    }

    private static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(s.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
