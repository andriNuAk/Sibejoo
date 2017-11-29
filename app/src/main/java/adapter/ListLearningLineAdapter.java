package adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import model.LearningLineVideo;
import model.LearningLineVideoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vortex.sibejoo.LearningLineActivity;
import vortex.sibejoo.LearningLineVideoActivity;
import vortex.sibejoo.ModulActivity;
import vortex.sibejoo.R;

/**
 * Created by M on 11/9/2017.
 */

public class ListLearningLineAdapter extends BaseAdapter {

    Context context;
    ArrayList<LearningLine> mLearningLine;
    LayoutInflater inflater;
    Activity activity;
    String statusStep ,inNamaDepan, inNamaBelakang, inAlamat, inNoKontak, inBio, inNamaSekolah, inAlamatSekolah, inPhoto, inKataSandi, inEMail, inPassword,strUsername, strPassword, md5Password;
    ArrayList<SiswaHelper> penggunaHelper;
    ArrayList<LearningLineLog> mLearningLineLog;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    int penggunaID;
    TextView txtNamaStep;
    CircleImageView iconStep;
    String uri=null;
    ImageView imgStatStep, imgStreetStart, imgStreetEnd;
    CardView cardStep;
    RelativeLayout relStepLearningLine;
    int length, idLog, penggunaIDLog, stepID;
    static int stat;
    static int statusCheck;
    int topikID;
    ProgressDialog pDialog;

    ArrayList<LearningLineLog> lineLog;
    protected LogHelper logHelper;
    protected SQLiteDatabase dbLog;

    public ListLearningLineAdapter(Activity activity, Context context, ArrayList<LearningLine> learningLine, int topikID) {
        this.activity = activity;
        this.context = context;
        this.mLearningLine = learningLine;
        this.topikID = topikID;
        initPengguna();

    }

    public void updateResults(ArrayList<LearningLine> mLearningLine){
        this.mLearningLine = mLearningLine;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mLearningLine.size();
    }

    @Override
    public Object getItem(int position) {
        return mLearningLine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_learning_line, parent, false);
        }



        txtNamaStep = (TextView) convertView.findViewById(R.id.txtNamaStep);
        iconStep = (CircleImageView) convertView.findViewById(R.id.circleImageStep);
        imgStatStep = (ImageView) convertView.findViewById(R.id.imgStatStep);
        cardStep = (CardView) convertView.findViewById(R.id.cardStep);
        imgStreetStart = (ImageView) convertView.findViewById(R.id.imageView27);
        imgStreetEnd = (ImageView) convertView.findViewById(R.id.imageView28);
        relStepLearningLine = (RelativeLayout) convertView.findViewById(R.id.relStepLearningLine);
        length = mLearningLine.size();
        imgStatStep.setTag("Ready");

        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initStreet(position);
        initLock(position);
        initStep(position);
//        imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
//        checkLog(penggunaID, mLearningLine.get(position).getStepID());
//        System.out.println("Status anying "+mLearningLineLog.size());
//        getLogByPenggunaStep(penggunaID, mLearningLine.get(position).getStepID());

//        if (position % 2 == 1){
//            imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
//        } else {
//            imgStatStep.setImageResource(R.drawable.ic_lock_black_24dp);
//        }


        txtNamaStep.setText(mLearningLine.get(position).getNamaStep().toString());
        if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("1")){
            uri = "@drawable/video";
        } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("2")){
            uri = "@drawable/ebook";
        } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("3")){
            uri = "@drawable/exam";
        }
        int imageResource = convertView.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = convertView.getResources().getDrawable(imageResource);
        iconStep.setImageDrawable(res);


//        cardStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(), "Enable", Toast.LENGTH_LONG).show();
//                if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("1")){
//                    initVideo(mLearningLine.get(position).getStepUUID());
//                } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("2")){
////                    Toast.makeText(context.getApplicationContext(), "Ebook", Toast.LENGTH_LONG).show();
//                    initModul(mLearningLine.get(position).getStepUUID(), mLearningLine.get(position).getStepID(), position);
//
//                } else if (mLearningLine.get(position).getJenisStep().equalsIgnoreCase("3")){
//                    Toast.makeText(context.getApplicationContext(), "Latihan", Toast.LENGTH_LONG).show();
//                }
//                notifyDataSetChanged();
//            }
//        });




        return convertView;
    }

    private void initPengguna() {
        dbHelper = new SessionHelper(context.getApplicationContext());
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

    public void showDialogVideo(String urlVideo){
        final Dialog dialog = new Dialog(activity);// add here your class name
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.costum_dialog_video_server);//add your own xml with defied with and height of videoview
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
//        uriPath= "android.resource://" + getPackageName() + "/" + R.raw.yourvid;

        activity.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        VideoView mVideoView = (VideoView) dialog.findViewById(R.id.vidServerLearningLine);
        mVideoView.setVideoURI(Uri.parse(urlVideo));
        mVideoView.start();
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

                Intent intent = new Intent(context, LearningLineVideoActivity.class);
                intent.putExtra("url", url);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
                    new ListLearningLineAdapter.DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/file_materi/"+moduls.get(0).getUrlFile().toString(), moduls.get(0).getUrlFile().toString());

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
                    context.startActivity(pdfIntent);
                    if (mLearningLine.get(position).getLinlogId() == null){
                        insertLog(penggunaID, stepID, "");
                    }

//                    initStreet(position);


//                Intent intent = new Intent(context, ModulActivity.class);
//                intent.putExtra("url", url);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
                } else {
                    final Dialog dialog = new Dialog(activity);// add here your class name
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

    public void initStreet(int position){
        if (length == 1){
            imgStreetStart.setVisibility(View.GONE);
            imgStreetEnd.setVisibility(View.GONE);
        } else if (length == 2){
            System.out.println("Position ="+position);
            if (position == 0){
                imgStreetStart.setVisibility(View.GONE);
                imgStreetEnd.setVisibility(View.VISIBLE);
            } else if (position == 1){
                imgStreetStart.setVisibility(View.VISIBLE);
                imgStreetEnd.setVisibility(View.GONE);
            }
        } else {

            if (position == 0){
                imgStreetStart.setVisibility(View.GONE);
                imgStreetEnd.setVisibility(View.VISIBLE);
            } else if ((length - 1) == position){
                imgStreetStart.setVisibility(View.VISIBLE);
                imgStreetEnd.setVisibility(View.GONE);
            } else {
                imgStreetStart.setVisibility(View.VISIBLE);
                imgStreetEnd.setVisibility(View.VISIBLE);
            }
        }

        if (position == 0){
            if (mLearningLine.get(position).getLinlogId() != null){
                imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
                cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                imgStatStep.setVisibility(View.VISIBLE);
            } else {
                cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                imgStatStep.setVisibility(View.GONE);
            }

        } else {
            cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
            imgStatStep.setVisibility(View.VISIBLE);
        }
    }

    public void initLock(int position){
        if (mLearningLine.get(position).getLinlogId() != null){
            imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
            cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
            imgStatStep.setVisibility(View.VISIBLE);
            cardStep.setEnabled(true);
        } else {
            if (position == 0){
                cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                imgStatStep.setVisibility(View.GONE);
                cardStep.setEnabled(true);
            } else {
                imgStatStep.setImageResource(R.drawable.ic_lock_black_24dp);
                cardStep.setEnabled(false);
                cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                imgStatStep.setVisibility(View.VISIBLE);
            }

        }
    }

    public void initStep(int position){
        int prevStep, nextStep;
        if (position > 0 && position < mLearningLine.size()){
            prevStep = position - 1;
            nextStep = position + 1;
            if (position + 1 == mLearningLine.size()){
                if (mLearningLine.get(position).getLinlogId() != null){
                    imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
                    cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                    imgStatStep.setVisibility(View.VISIBLE);
                } else {
                    if (mLearningLine.get(prevStep).getLinlogId() != null){
                        cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        imgStatStep.setVisibility(View.GONE);
                        cardStep.setEnabled(true);
                    } else {
                        cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                        imgStatStep.setVisibility(View.VISIBLE);
                    }
                }
            } else if (mLearningLine.get(prevStep).getLinlogId() != null && mLearningLine.get(nextStep).getLinlogId() == null){
                if (mLearningLine.get(position).getLinlogId() == null){
                    cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    imgStatStep.setVisibility(View.GONE);
                    cardStep.setEnabled(true);
                } else {
                    cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                    imgStatStep.setVisibility(View.VISIBLE);
                }

            } else {
                cardStep.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorTealPressed));
                imgStatStep.setVisibility(View.VISIBLE);
            }
        }
    }

    public void checkLog(int idPengguna, int stepID){
        final int[] idl = new int[1];
        final int[] penggunaIDl = new int[1];
        final int[] stepIDl = new int[1];
        final String[] statusStepl = new String[1];
        System.out.println("anying pengguna "+idPengguna+" ,step "+stepID);
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineLogResponse> call = apiInterface.getLearningLineLog(idPengguna, stepID);
        call.enqueue(new Callback<LearningLineLogResponse>() {
            @Override
            public void onResponse(Call<LearningLineLogResponse> call, Response<LearningLineLogResponse> response) {
//                int imageResource = getResources().getIdentifier("@drawable/ic_check_box_black_24dp", null, getPackageName());
//
//                Drawable res = getResources().getDrawable(imageResource);
                ArrayList<LearningLineLog> learningLineLog = (ArrayList<LearningLineLog>) response.body().getLearningLineLog();
//                System.out.println("Position "+position+", pengguna id "+penggunaID+" , stepID "+mLearningLine.get(position).getStepID());
                int id = 0, penggunaID = 0, stepID = 0;
                String statusStep = null;
                for (int i = 0; i < learningLineLog.size(); i++){
//                    mLearningLineLog = new ArrayList<LearningLineLog>();
                    id = learningLineLog.get(i).getId();
                    penggunaID = learningLineLog.get(i).getPenggunaID();
                    stepID = learningLineLog.get(i).getStepID();
                    statusStep = learningLineLog.get(i).getStatusStep();

//                    mLearningLineLog.add(new LearningLineLog(id, penggunaID, stepID, statusStep));
                    System.out.println("kamfreett "+id+" , "+penggunaID+" ,"+stepID+" , "+statusStep);
                }
                idl[0] = id;
                penggunaIDl[0] = penggunaID;
                stepIDl[0] = stepID;
                statusStepl[0] = statusStep;
                getLog(id);



            }

            @Override
            public void onFailure(Call<LearningLineLogResponse> call, Throwable t) {
//                setAvailable(0);
                imgStatStep.setImageResource(R.drawable.ic_lock_black_24dp);

            }


        });

        if (lineLog == null){
//                    Toast.makeText(context.getApplicationContext(), "Masukin insert", Toast.LENGTH_SHORT).show();
//            insertLog(idl[0], penggunaIDl[0], stepIDl[0], statusStepl[0]);
        } else {
//                    Toast.makeText(context.getApplicationContext(), "Udah ada data", Toast.LENGTH_SHORT).show();
//                    imgStatStep.setImageResource(R.drawable.ic_check_box_black_24dp);
        }
    }

    public static int helperStat(int statCheck){
        statusCheck = statCheck;

        return statusCheck;
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
                Toast.makeText(context.getApplicationContext(), "Insert Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LearningLineLog> call, Throwable t) {

            }
        });

    }

    public void getLog(int id){
        logHelper = new LogHelper(context.getApplicationContext());
        dbLog = logHelper.getWritableDatabase();
        try {
//            loadDataMahasiswa();
            Cursor cursor = logHelper.getLog(dbLog, id);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//            String data = cursor.getString(0)+" - "+cursor.getString(1);
//            akun.add(data);
                lineLog = new ArrayList();

                idLog = Integer.valueOf(cursor.getString(0));
                        penggunaIDLog = Integer.valueOf(cursor.getString(1));
                        stepID = Integer.valueOf(cursor.getString(2));
                        statusStep = cursor.getString(3);
//                        inNoKontak = cursor.getString(4);
//                        inBio = cursor.getString(5);
                LearningLineLog ph = new LearningLineLog(Integer.valueOf(cursor.getString(0)), Integer.valueOf(cursor.getString(1)), Integer.valueOf(cursor.getString(2)), cursor.getString(3));
                lineLog.add(ph);
                cursor.moveToNext();
            }
// close cursor
            cursor.close();
//            Collections.sort(akun);
        } catch (Exception e){
            Log.e("masuk","-> "+e.getMessage()) ;
        }
    }


    public void getLogByPenggunaStep(int penggunaID, int stepIDlog){
        logHelper = new LogHelper(context.getApplicationContext());
        dbLog = logHelper.getWritableDatabase();
        try {
//            loadDataMahasiswa();
            Cursor cursor = logHelper.getLogByPenggunaStep(dbLog, penggunaID, stepIDlog);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//            String data = cursor.getString(0)+" - "+cursor.getString(1);
//            akun.add(data);
                lineLog = new ArrayList();

                idLog = Integer.valueOf(cursor.getString(0));
                penggunaIDLog = Integer.valueOf(cursor.getString(1));
                stepID = Integer.valueOf(cursor.getString(2));
                statusStep = cursor.getString(3);
//                        inNoKontak = cursor.getString(4);
//                        inBio = cursor.getString(5);
                LearningLineLog ph = new LearningLineLog(Integer.valueOf(cursor.getString(0)), Integer.valueOf(cursor.getString(1)), Integer.valueOf(cursor.getString(2)), cursor.getString(3));
                lineLog.add(ph);
                cursor.moveToNext();
            }
// close cursor
            cursor.close();
//            Collections.sort(akun);
        } catch (Exception e){
            Log.e("masuk","-> "+e.getMessage()) ;
        }
    }

    public class DownloadFile extends AsyncTask<String, Void, Void>{

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
            Toast.makeText(context.getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }


}
