package vortex.sibejoo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import adapter.ListPasswordAdapter;
import adapter.ListProfilAdapter;
import api.APIClient;
import api.APIInterface;
import helper.ProfilHelper;
import helper.SessionHelper;
import helper.SiswaHelper;
import model.UpdateEMailProfil;
import model.UpdatePhotoProfil;
import model.UpdatePhotoProfilResponse;
import model.UpdateProfil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilActivity extends AppCompatActivity {
    ArrayList<ProfilHelper> listProfil;
    RelativeLayout lvProfil, lvEmail, lvPassword;
    ListProfilAdapter listProfilAdapter;
    ListPasswordAdapter listPasswordAdapter;
    ImageView imgDown1, imgDown2, imgDown3;
    Button btnUbahProfil, btnUbahEmail, btnUbahPassword;
    ImageButton btnPhoto;
    CardView cardTextProfil, cardtextEmail, cardTextPassword;
    TextView txtNamaLengkap, txtSekolah;
    String inNamaDepan, inNamaBelakang, inAlamat, inNoKontak, inBio, inNamaSekolah, inAlamatSekolah, inPhoto, inKataSandi, inEMail, inPassword,strUsername, strPassword, md5Password, path, newPath;
    int penggunaID;
    ArrayList<SiswaHelper> penggunaHelper;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    EditText txtNamaDepan, txtNamaBelakang, txtAlamat, txtNoKontak, txtNamaSekolah, txtAlamatSekolah, txtBiografi, txtEmail, txtPassLama, txtPassBaru, txtConPassBaru;
    ImageView imgProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lvProfil = (RelativeLayout) findViewById(R.id.lvProfil);
        lvEmail = (RelativeLayout) findViewById(R.id.lvEmail);
        lvPassword = (RelativeLayout) findViewById(R.id.lvPassword);
        imgDown1 = (ImageView) findViewById(R.id.imgDown1);
        imgDown2 = (ImageView) findViewById(R.id.imgDown2);
        imgDown3 = (ImageView) findViewById(R.id.imgDown3);
        btnUbahProfil = (Button) findViewById(R.id.btnUbahProfil);
        btnUbahEmail = (Button) findViewById(R.id.btnUbahEmail);
        btnUbahPassword = (Button) findViewById(R.id.btnUbahPassword);
        btnPhoto = (ImageButton) findViewById(R.id.btnPhoto);
        cardTextProfil = (CardView) findViewById(R.id.cardTextProfil);
        cardtextEmail = (CardView) findViewById(R.id.cardTextEmail);
        cardTextPassword = (CardView) findViewById(R.id.cardTextPassword);
        txtNamaLengkap = (TextView) findViewById(R.id.txtNamaLengkap);
        txtSekolah = (TextView) findViewById(R.id.txtSekolah);
        txtNamaDepan = (EditText) findViewById(R.id.txtValueNamaDepan);
        txtNamaBelakang = (EditText) findViewById(R.id.txtValueNamaBelakang);
        txtAlamat = (EditText) findViewById(R.id.txtValueAlamat);
        txtNoKontak = (EditText) findViewById(R.id.txtValueNoKontak);
        txtNamaSekolah = (EditText) findViewById(R.id.txtValueNamaSekolah);
        txtAlamatSekolah = (EditText) findViewById(R.id.txtValueAlamatSekolah);
        txtBiografi = (EditText) findViewById(R.id.txtValueBiografi);
        txtEmail = (EditText) findViewById(R.id.txtValueEmail);
        txtPassLama = (EditText) findViewById(R.id.txtValuePassLama);
        txtPassBaru = (EditText) findViewById(R.id.txtValuePassBaru);
        txtConPassBaru = (EditText) findViewById(R.id.txtValueConPassBaru);
        imgProfil = (ImageView) findViewById(R.id.imgProfil);


        lvProfil.setVisibility(View.GONE);
        btnUbahProfil.setVisibility(View.GONE);
        lvEmail.setVisibility(View.GONE);
        btnUbahEmail.setVisibility(View.GONE);
        lvPassword.setVisibility(View.GONE);
        btnUbahPassword.setVisibility(View.GONE);

        initPengguna();
        txtNamaLengkap.setText(inNamaDepan+" "+inNamaBelakang);
        txtSekolah.setText(inNamaSekolah);
        Picasso.with(getApplicationContext()).load(inPhoto).into(imgProfil);

        //initEnableButtonSaveProfil();
        setProfil();
//        listProfilAdapter = new ListProfilAdapter(this, listProfil);
//        lvProfil.setAdapter(listProfilAdapter);
//        lvProfil.setExpanded(true);
//
//        setEmail();
//        listPasswordAdapter = new ListPasswordAdapter(this, listProfil);
//        lvEmail.setAdapter(listPasswordAdapter);
//        lvEmail.setExpanded(true);
//
//        setPassword();
//        listPasswordAdapter = new ListPasswordAdapter(this, listProfil);
//        lvPassword.setAdapter(listPasswordAdapter);
//        lvPassword.setExpanded(true);
        initEnableButtonSaveProfil();
        initEnableButtonSaveEmail();
        initEnableButtonSavePassword();
        cardTextProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvProfil.getVisibility() == View.GONE){
                    lvProfil.setVisibility(View.VISIBLE);
                    imgDown1.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                    btnUbahProfil.setVisibility(View.VISIBLE);
                } else {
                    lvProfil.setVisibility(View.GONE);
                    imgDown1.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    btnUbahProfil.setVisibility(View.GONE);
                }
            }
        });

        updateProfil();

        cardtextEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvEmail.getVisibility() == View.GONE){
                    lvEmail.setVisibility(View.VISIBLE);
                    imgDown2.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                    btnUbahEmail.setVisibility(View.VISIBLE);
                } else {
                    lvEmail.setVisibility(View.GONE);
                    imgDown2.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    btnUbahEmail.setVisibility(View.GONE);
                }
            }
        });

        updateEMailProfil();

        cardTextPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvPassword.getVisibility() == View.GONE){
                    lvPassword.setVisibility(View.VISIBLE);
                    imgDown3.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
                    btnUbahPassword.setVisibility(View.VISIBLE);
                } else {
                    lvPassword.setVisibility(View.GONE);
                    imgDown3.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    btnUbahPassword.setVisibility(View.GONE);
                }
            }
        });

        updatePasswordProfil();




        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                //Picasso.with(getApplicationContext()).load(newPath).into(imgProfil);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setProfil(){
        txtNamaDepan.setText(inNamaDepan);
        txtNamaBelakang.setText(inNamaBelakang);
        txtAlamat.setText(inAlamat);
        txtNoKontak.setText(inNoKontak);
        txtNamaSekolah.setText(inNamaSekolah);
        txtAlamatSekolah.setText(inAlamatSekolah);
        if (inBio == null || inBio.equalsIgnoreCase("")){
            txtBiografi.setText("Tidak ada biografi");
        } else {
            txtBiografi.setText(inBio);
        }
    }

    private ArrayList<ProfilHelper> setEmail(){
        listProfil = new ArrayList<>();
        ProfilHelper modelProfil = new ProfilHelper("ic_email_black_24dp", "Email", "Masukan Email Baru");
        listProfil.add(modelProfil);



        return listProfil;
    }

    private ArrayList<ProfilHelper> setPassword(){
        listProfil = new ArrayList<>();
        ProfilHelper modelProfil = new ProfilHelper("ic_vpn_key_black_24dp", "Kata Sandi Lama", "Masukan Kata Sandi Lama");
        listProfil.add(modelProfil);

        modelProfil = new ProfilHelper("ic_vpn_key_black_24dp", "Kata Sandi Baru", "Masukan Kata Sandi Baru");
        listProfil.add(modelProfil);

        modelProfil = new ProfilHelper("ic_vpn_key_black_24dp", "Ulang Kata Sandi Baru", "Ulangi Kata Sandi Baru");
        listProfil.add(modelProfil);

        return listProfil;
    }

    public void  updatePasswordProfil(){
        btnUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("password = "+inKataSandi);
                String oldPwd = txtPassLama.getText().toString();
                String md5OldPwd = md5(oldPwd);
                if (txtPassLama.getText() == null || txtPassLama.getText().toString().equalsIgnoreCase("")){
                    txtPassLama.setError("Kata sandi lama tidak boleh kosong");
                    txtPassLama.setFocusable(true);
                } else if (txtPassBaru.getText() == null || txtPassBaru.getText().toString().equalsIgnoreCase("") ){
                    txtPassBaru.setError("Kata sandi lama tidak boleh kosong");
                    txtPassBaru.setFocusable(true);
                } else if (txtConPassBaru.getText() == null || txtConPassBaru.getText().toString().equalsIgnoreCase("")){
                    txtConPassBaru.setError("Konfirmasi kata sandi baru tidak boleh kosong");
                    txtConPassBaru.setFocusable(true);
                } else if (!inKataSandi.equalsIgnoreCase(md5OldPwd)){
                    txtPassLama.setError("Kata sandi salah");
                } else if (!txtPassBaru.getText().toString().equalsIgnoreCase(txtConPassBaru.getText().toString())){
                    txtConPassBaru.setError("Pastikan password pada kolom konfirmasi password dan password baru sama");
                } else {
                    Toast.makeText(getApplicationContext(), "Siap memperbaharui password", Toast.LENGTH_LONG).show();
                    System.out.println("password eakkk"+txtPassBaru.getText());

                    final String md5Pass = md5(txtPassBaru.getText().toString());
                    APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
                    apiInterface.updatePassowrdProfil(penggunaID, md5Pass).enqueue(new Callback<UpdateEMailProfil>() {
                        @Override
                        public void onResponse(Call<UpdateEMailProfil> call, Response<UpdateEMailProfil> response) {
                            Toast.makeText(getApplicationContext(), "Berhasil memperbaharui profil", Toast.LENGTH_LONG).show();
                            SessionHelper dbHelper =  new SessionHelper(ProfilActivity.this);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
//                                dbHelper.deleteDataAkun(db, 12);
                            dbHelper.createTabelPengguna(db);
                            dbHelper.updatePasswordProfil(db, penggunaID, md5Pass);


                            Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<UpdateEMailProfil> call, Throwable t) {

                        }
                    });
                }
//                if (!inKataSandi.equalsIgnoreCase(md5OldPwd)){
//                    txtPassLama.setError("Kata sandi salah");
//                } else if (!txtPassBaru.getText().toString().equalsIgnoreCase(txtConPassBaru.getText().toString())){
//                    txtConPassBaru.setError("Pastikan password pada kolom konfirmasi password dan password baru sama");
//                } else {
//
//                }
            }
        });

        txtPassBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txtConPassBaru.setError(null);
            }
        });
    }

    public void updateProfil(){
        btnUbahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
                apiInterface.updateProfil(txtNamaDepan.getText().toString(), txtNamaBelakang.getText().toString(), txtAlamat.getText().toString(), txtNoKontak.getText().toString(), txtBiografi.getText().toString(), txtNamaSekolah.getText().toString(), txtAlamatSekolah.getText().toString(), penggunaID).enqueue(new Callback<UpdateProfil>() {
                    @Override
                    public void onResponse(Call<UpdateProfil> call, Response<UpdateProfil> response) {
                        Toast.makeText(getApplicationContext(), "Berhasil memperbaharui profil", Toast.LENGTH_LONG).show();
                        SessionHelper dbHelper =  new SessionHelper(ProfilActivity.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
//                                dbHelper.deleteDataAkun(db, 12);
                        dbHelper.createTabelPengguna(db);
                        dbHelper.updateProfil(db, penggunaID, txtNamaDepan.getText().toString(), txtNamaBelakang.getText().toString(), txtAlamat.getText().toString(), txtNoKontak.getText().toString(), txtNamaSekolah.getText().toString(), txtAlamatSekolah.getText().toString(), txtBiografi.getText().toString());

                        txtNamaLengkap.setText(txtNamaDepan.getText().toString()+" "+txtNamaBelakang.getText().toString());
                        txtSekolah.setText(txtNamaSekolah.getText().toString());


                        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UpdateProfil> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Gagal memperbaharui profil", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void updateEMailProfil(){
        btnUbahEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
                apiInterface.updateEMailProfil(penggunaID, txtEmail.getText().toString()).enqueue(new Callback<UpdateEMailProfil>() {
                    @Override
                    public void onResponse(Call<UpdateEMailProfil> call, Response<UpdateEMailProfil> response) {
                        Toast.makeText(getApplicationContext(), "Berhasil memperbaharui profil", Toast.LENGTH_LONG).show();
                        SessionHelper dbHelper =  new SessionHelper(ProfilActivity.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
//                                dbHelper.deleteDataAkun(db, 12);
                        dbHelper.createTabelPengguna(db);
                        dbHelper.updateEMailProfil(db, penggunaID, txtEmail.getText().toString());

                        initPengguna();
                        System.out.println("nama email "+inEMail);

                        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<UpdateEMailProfil> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void updatePhotoProfil(){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        apiInterface.updatePhotoProfil(penggunaID).enqueue(new Callback<UpdateProfil>() {
            @Override
            public void onResponse(Call<UpdateProfil> call, Response<UpdateProfil> response) {
//                SessionHelper dbHelper =  new SessionHelper(ProfilActivity.this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
////                                dbHelper.deleteDataAkun(db, 12);
//                dbHelper.createTabelPengguna(db);
//                dbHelper.updatePhotoProfil(db, penggunaID, "");
                    getNewPhoto();
            }

            @Override
            public void onFailure(Call<UpdateProfil> call, Throwable t) {

            }
        });
    }

    //ambil photo terbaru
    public void getNewPhoto(){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<UpdatePhotoProfilResponse> call = apiInterface.getPhoto(penggunaID);
        call.enqueue(new Callback<UpdatePhotoProfilResponse>() {
            @Override
            public void onResponse(Call<UpdatePhotoProfilResponse> call, Response<UpdatePhotoProfilResponse> response) {
                ArrayList<UpdatePhotoProfil> updatePhotoProfil = (ArrayList<UpdatePhotoProfil>) response.body().getUpdatePhotoProfil();
                System.out.println("nama path baru "+updatePhotoProfil.get(0).getPhoto());
                newPath = updatePhotoProfil.get(0).getPhoto();
                SessionHelper dbHelper =  new SessionHelper(ProfilActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
////                                dbHelper.deleteDataAkun(db, 12);
                dbHelper.createTabelPengguna(db);
                dbHelper.updatePhotoProfil(db, penggunaID, newPath);
                Picasso.with(getApplicationContext()).load(newPath).into(imgProfil);
            }

            @Override
            public void onFailure(Call<UpdatePhotoProfilResponse> call, Throwable t) {

            }
        });
    }

    private void selectImage(){
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfilActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

//                    viewImage.setImageBitmap(bitmap);
                    //imgProfil.setImageBitmap(bitmap);

                    String pathPhoto = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator;
//                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(pathPhoto, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                    APIInterface service;
// Change base URL to your upload server URL.
//        service = new Retrofit.Builder().baseUrl("http://192.168.1.16:5500").client(client).build().create(APIInterface.class); // (lokasl)
                    service = new Retrofit.Builder().baseUrl("http://192.168.42.183:3100").client(client).build().create(APIInterface.class); // (vps)

                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), f);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("upload", f.getName(), reqFile);
                    final RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

                    retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
                    req.enqueue(new Callback<ResponseBody>() {

                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                            Toast.makeText(getApplicationContext(), "Upload berhasil, nama file = "+name.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
//                startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            t.printStackTrace();
                        }
                    });


                    path = file.getPath();
                    System.out.println("nama file "+f.getName());
                    System.out.println("nama path "+f);
//                    uploadFoto();
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.write(path.getBytes());
                        //outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    updatePhotoProfil();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Picasso.with(getApplicationContext()).load(newPath).into(imgProfil);
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image", picturePath+"");
//                viewImage.setImageBitmap(thumbnail);
                //imgProfil.setImageBitmap(thumbnail);
                System.out.println("nama file "+picturePath);
                path = picturePath;
                uploadFoto();
                //updatePhotoProfil();
            }
        }
    }

    private void uploadFoto(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        APIInterface service;
// Change base URL to your upload server URL.
//        service = new Retrofit.Builder().baseUrl("http://192.168.1.16:5500").client(client).build().create(APIInterface.class); // (lokasl)
        service = new Retrofit.Builder().baseUrl("http://192.168.42.183:3100").client(client).build().create(APIInterface.class); // (vps)


        File file = new File(path);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
        final RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");

        retrofit2.Call<okhttp3.ResponseBody> req = service.postImage(body, name);
        req.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "Upload berhasil, nama file = "+name.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
//                startActivity(intent);
                updatePhotoProfil();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }

    private void initPengguna() {
        dbHelper = new SessionHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        try {
//            loadDataMahasiswa();
            Cursor cursor = dbHelper.getAllAkun(db);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//            String data = cursor.getString(0)+" - "+cursor.getString(1);
//            akun.add(data);
                penggunaHelper = new ArrayList();

                penggunaID = Integer.valueOf(cursor.getString(0));
                inNamaDepan = cursor.getString(1);
                inNamaBelakang = cursor.getString(2);
                inAlamat = cursor.getString(3);
                inNoKontak = cursor.getString(4);
                inNamaSekolah = cursor.getString(5);
                inAlamatSekolah = cursor.getString(6);
                inPhoto = cursor.getString(7);
                inBio = cursor.getString(8);
                inKataSandi = cursor.getString(9);
                inEMail = cursor.getString(10);
                SiswaHelper ph = new SiswaHelper(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
                penggunaHelper.add(ph);
                cursor.moveToNext();
            }
// close cursor
            cursor.close();
//            Collections.sort(akun);
        } catch (Exception e){
            Log.e("masuk","-> "+e.getMessage()) ;
        }

        System.out.println("Udah ada di arraylist, datanya : kode user = "+penggunaID);
    }

    public void initEnableButtonSaveProfil(){
        txtNamaDepan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
        txtNamaBelakang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
        txtAlamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
        txtNoKontak.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
        txtNamaSekolah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
        txtAlamatSekolah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });

        txtBiografi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahProfil.setEnabled(true);
            }
        });
    }

    public void initEnableButtonSaveEmail(){
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahEmail.setEnabled(true);
            }
        });
    }

    public void initEnableButtonSavePassword(){
        txtPassLama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahPassword.setEnabled(true);
            }
        });

        txtPassBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahPassword.setEnabled(true);
            }
        });

        txtConPassBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnUbahPassword.setEnabled(true);
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
