package vortex.sibejoo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import adapter.ListLearningLineAdapter;
import api.APIClient;
import api.APIInterface;
import de.hdodenhof.circleimageview.CircleImageView;
import helper.FileDownloader;
import helper.LogHelper;
import helper.SessionHelper;
import helper.SiswaHelper;
import model.LearningLine;
import model.LearningLineLog;
import model.LearningLineLogResponse;
import model.LearningLineModul;
import model.LearningLineModulResponse;
import model.LearningLineResponse;
import model.LearningLineVideo;
import model.LearningLineVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLineActivity extends AppCompatActivity {
    TextView txtID, txtNamaTopik, txtNamaMapel, txtDeskripsiTopik, txtNamaStepStart, txtNamaStepEnd;
    int idTopik, idPengguna, penggunaID;
    ArrayList<LearningLine> mLearningLine;
    ExpandableHeightListView listLeraningLine;
    ListLearningLineAdapter learningLineAdapter;
    CircleImageView iconStartStep, iconEndStep, imgIcon;
    RelativeLayout relEndStep, startStep;
    String statusStep, inNamaDepan, inNamaBelakang, inAlamat, inNoKontak, inBio, inNamaSekolah, inAlamatSekolah, inPhoto, inKataSandi, inEMail, inPassword,strUsername, strPassword, md5Password;
    ArrayList<SiswaHelper> penggunaHelper;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ImageView imgStatStepStart, imgStatStepEnd;
    CardView cardStart, cardEnd;
    ArrayList<LearningLineLog> lineLog;
    protected LogHelper logHelper;
    protected SQLiteDatabase dbLog;
    int idLog, penggunaIDLog, stepID;
    ProgressDialog pDialog;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNamaTopik = (TextView) findViewById(R.id.txtNamaTopik);
        txtNamaMapel = (TextView) findViewById(R.id.txtMapel);
        txtDeskripsiTopik = (TextView) findViewById(R.id.txtDeskripsiTopik);
        listLeraningLine = (ExpandableHeightListView) findViewById(R.id.listLearningLine);
        imgIcon = (CircleImageView) findViewById(R.id.iconLearningLine);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            idTopik = Integer.valueOf(bundle.getString("topikID"));
        } else {
            idTopik = Integer.valueOf(getIntent().getStringExtra("topikID"));
        }
//        IntentFilter filter = new IntentFilter("topikID");
//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                if (intent.getAction().equals(""))
//            }
//        };
        Toast.makeText(getApplicationContext(), "id "+idTopik, Toast.LENGTH_SHORT ).show();

        initPengguna();


        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineResponse> call = apiInterface.getLearningLine(idTopik);
        call.enqueue(new Callback<LearningLineResponse>() {
            @Override
            public void onResponse(Call<LearningLineResponse> call, Response<LearningLineResponse> response) {
                String uriStart = null;
                String uriEnd = null;
                ArrayList<LearningLine> learningLine = (ArrayList<LearningLine>) response.body().getLearningLine();
                mLearningLine = learningLine;

                initIcon(mLearningLine.get(0).getKeterangan());

                txtNamaMapel.setText(mLearningLine.get(0).getKeterangan()+" / "+mLearningLine.get(0).getJudulBab());
                txtNamaTopik.setText(mLearningLine.get(0).getNamaTopik());

                txtDeskripsiTopik.setText("'"+mLearningLine.get(0).getDeskripsi()+"'");


                if (mLearningLine != null || mLearningLine.isEmpty() == false){
                    learningLineAdapter= new ListLearningLineAdapter(LearningLineActivity.this, getApplicationContext(), mLearningLine, idTopik);
                    listLeraningLine.setAdapter(learningLineAdapter);
                    listLeraningLine.setExpanded(true);
                }

            }

            @Override
            public void onFailure(Call<LearningLineResponse> call, Throwable t) {

            }
        });

        listLeraningLine.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                        learningLineAdapter.notifyDataSetChanged()
                if (mLearningLine.size() == 1){
                    String jenisStep = null;
                    if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("1")){
                        jenisStep = "video";
                        initVideo(mLearningLine.get(position).getStepUUID());
                    } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("2")){
                        jenisStep = "ebook";
                        initModul(mLearningLine.get(position).getStepUUID(), mLearningLine.get(position).getStepID(), position);
                    } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("3")){
                        jenisStep = "latihan";
                    }
                    Toast.makeText(getApplicationContext(), "Jenis "+jenisStep, Toast.LENGTH_LONG).show();
                } else {
                    String jenisStep = null;
                    if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("1")){
                        jenisStep = "video";
                        initVideo(mLearningLine.get(position).getStepUUID());
                    } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("2")){
                        jenisStep = "ebook";
                        if (position >= 1){
                            if (mLearningLine.get(position-1).getLinlogId() == null){
                                Toast.makeText(getApplicationContext(), "Tidak Tersedia", Toast.LENGTH_LONG).show();
                            } else {
                                initModul(mLearningLine.get(position).getStepUUID(), mLearningLine.get(position).getStepID(), position);
                            }
                        } else {
                            initModul(mLearningLine.get(position).getStepUUID(), mLearningLine.get(position).getStepID(), position);
                        }

                    } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("3")){
                        jenisStep = "latihan";
                        Intent intent = new Intent(getApplicationContext(), SoalActivity.class);
                        intent.putExtra("latihanID", mLearningLine.get(position).getLatihanID().toString());
                        startActivity(intent);
                    }
                    Toast.makeText(getApplicationContext(), "Jenis "+jenisStep, Toast.LENGTH_LONG).show();
                }


//                CardView cardStep = (CardView) view.findViewById(R.id.cardStep);
//                cardStep.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String jenisStep = null;
////                        if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("1")){
////                            initVideo(mLearningLine.get(position).getStepUUID());
////                        } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("2")){
////                            initModul(mLearningLine.get(position).getStepUUID(), mLearningLine.get(position).getStepID(), position);
//////                            initList();
////                        } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("3")){
////                            jenisStep = "latihan";
////                        }
//                    }
//                });


            }
        });

//        txtID.setText(String.valueOf(idTopik));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null)
            setIntent(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String a = String.valueOf(getIntent().getExtras());
        System.out.println(a);
//        getIntent().getExtras();
    }

    public void initList(){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineResponse> call = apiInterface.getLearningLine(idTopik);
        call.enqueue(new Callback<LearningLineResponse>() {
            @Override
            public void onResponse(Call<LearningLineResponse> call, Response<LearningLineResponse> response) {
                String uriStart = null;
                String uriEnd = null;
                ArrayList<LearningLine> learningLine = (ArrayList<LearningLine>) response.body().getLearningLine();
                mLearningLine = learningLine;
                initIcon(mLearningLine.get(0).getKeterangan());
                txtNamaMapel.setText(mLearningLine.get(0).getKeterangan()+" / "+mLearningLine.get(0).getJudulBab());
                txtNamaTopik.setText(mLearningLine.get(0).getNamaTopik());
                txtDeskripsiTopik.setText("'"+mLearningLine.get(0).getDeskripsi()+"'");
                learningLineAdapter= new ListLearningLineAdapter(LearningLineActivity.this, getApplicationContext(), mLearningLine, idTopik);
                listLeraningLine.setAdapter(learningLineAdapter);
                listLeraningLine.setExpanded(true);
                Toast.makeText(getApplicationContext(), "Update lis success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LearningLineResponse> call, Throwable t) {

            }
        });
    }

    public void initIcon(String mapel){
        String uri = null;

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

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);

        imgIcon.setImageDrawable(res);
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
//                        inNamaDepan = cursor.getString(1);
//                        inNamaBelakang = cursor.getString(2);
//                        inAlamat = cursor.getString(3);
//                        inNoKontak = cursor.getString(4);
//                        inBio = cursor.getString(5);
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

    public void initVideo(String UUID){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineVideoResponse> call = apiInterface.getLearningLineVid(UUID);
        call.enqueue(new Callback<LearningLineVideoResponse>() {
            @Override
            public void onResponse(Call<LearningLineVideoResponse> call, Response<LearningLineVideoResponse> response) {
                ArrayList<LearningLineVideo> videos = (ArrayList<LearningLineVideo>) response.body().getLearningLineVideo();
                String url = "http://sibejooclass.com/heroo/beta/assets/video/"+videos.get(0).getNamaFile();
//                showDialogVideo(url);

                Intent intent = new Intent(getApplicationContext(), LearningLineVideoActivity.class);
                intent.putExtra("url", url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//                context.startActivity(new Intent(context, LearningLineVideoActivity.class));
            }

            @Override
            public void onFailure(Call<LearningLineVideoResponse> call, Throwable t) {

            }
        });

    }

    public void initModul(String UUID, final int stepID, final int position){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineModulResponse> call = apiInterface.getLearningLineMod(UUID);
        call.enqueue(new Callback<LearningLineModulResponse>() {
            @Override
            public void onResponse(Call<LearningLineModulResponse> call, Response<LearningLineModulResponse> response) {
                ArrayList<LearningLineModul> moduls = (ArrayList<LearningLineModul>) response.body().getLearningLineModul();
                String url = "http://sibejooclass.com/heroo/beta/assets/file_materi/"+moduls.get(0).getUrlFile();
//                Toast.makeText(context.getApplicationContext(), "URL "+url, Toast.LENGTH_LONG).show();
                if (moduls.get(0).getUrlFile() != null){
                    new DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/file_materi/"+moduls.get(0).getUrlFile().toString(), moduls.get(0).getUrlFile().toString());

//                    Intent intent = new Intent(context.getApplicationContext(), LearningLineActivity.class);
//                    intent.putExtra("topikID", topikID);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);

                    File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + moduls.get(0).getUrlFile().toString());  // -> filename = maven.pdf
                    Uri path = Uri.fromFile(pdfFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(path, "application/pdf");
                    pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                System.out.println("weleeehhhh "+mEduDrive.get(position).getUrlFile());
                    startActivity(pdfIntent);
                    if (mLearningLine.get(position).getLinlogId() == null){
                        insertLog(penggunaID, stepID, "");

                    }

//                    initStreet(position);


//                Intent intent = new Intent(context, ModulActivity.class);
//                intent.putExtra("url", url);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
                } else {
                    Dialog dialog = new Dialog(context);// add here your class name
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog_err);//add your own xml with defied with and height of videoview
                    dialog.show();
                }



            }

            @Override
            public void onFailure(Call<LearningLineModulResponse> call, Throwable t) {

            }
        });

    }

    public void insertLog(int penggunaID, int stepID, String statusStep){
//        logHelper =  new LogHelper(context.getApplicationContext());
//        dbLog = logHelper.getWritableDatabase();
////                                dbHelper.deleteDataAkun(db, 12);
//        logHelper.createTabelLog(dbLog);
//        logHelper.insertLog(dbLog, id, penggunaID, stepID, statusStep);
////        Toast.makeText(context.getApplicationContext(), "Berhasil insert", Toast.LENGTH_SHORT).show();
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        apiInterface.insertLog(penggunaID, stepID, statusStep).enqueue(new Callback<LearningLineLog>() {
            @Override
            public void onResponse(Call<LearningLineLog> call, Response<LearningLineLog> response) {
                Toast.makeText(getApplicationContext(), "Insert Berhasil", Toast.LENGTH_SHORT).show();
                initList();
            }

            @Override
            public void onFailure(Call<LearningLineLog> call, Throwable t) {

            }
        });

    }

    public class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!pDialog.isShowing()){
                pDialog.show();
            }

        }

        @Override
        protected Void doInBackground(String... params) {
            String fileUrl = params[0];
            String fileName = params[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "PDF DOWNLOAD");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            Toast.makeText(getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }


}
