package vortex.sibejoo;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.android.youtube.player.YouTubePlayerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapter.ListCommentAdapter;
import api.APIClient;
import api.APIInterface;
import helper.KomenHelper;
import helper.SessionHelper;
import helper.SiswaHelper;
import model.Comment;
import model.CommentResponse;
import model.Materi;
import model.MateriResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoServerActivity extends AppCompatActivity {
    VideoView videoView;
    TextView txtJudulVideo;
    int videoID;
    String judulVideo, linkVideo, trueLink;
    private ProgressDialog pDialog;
    ArrayList<Comment> mComment;
    TextView txtSumComment, txtStatusVideo;
    ExpandableHeightListView listComment;
    ListCommentAdapter listCommentAdapter;
    int penggunaID;
    ArrayList<SiswaHelper> penggunaHelper;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    EditText inComment;
    Button imgSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_server);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        txtJudulVideo = (TextView) findViewById(R.id.txtJudulVideo);
        videoView = (VideoView) findViewById(R.id.videoView);
        txtSumComment = (TextView) findViewById(R.id.txtSumComment);
        listComment = (ExpandableHeightListView) findViewById(R.id.listComments);
        txtStatusVideo = (TextView) findViewById(R.id.txtStatusVideo);
        inComment = (EditText) findViewById(R.id.inComment);
        imgSend = (Button) findViewById(R.id.imageView4);


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            videoID = Integer.valueOf(bundle.getString("videoID"));
            judulVideo = bundle.getString("judulVideo");
            linkVideo = bundle.getString("link");
        }

        if (linkVideo.equals("") || linkVideo == null){
            txtStatusVideo.setVisibility(View.VISIBLE);
        }
        initSumComment(videoID);
        txtJudulVideo.setText(judulVideo);
        initVideo(linkVideo);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void initVideo(String VideoURL){
//        pDialog = new ProgressDialog(PembahasanActivity.this);
//        // Set progressbar title
//        pDialog.setTitle("Android Video Streaming Tutorial");
//        // Set progressbar message
//        pDialog.setMessage("Buffering...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        // Show progressbar
//        pDialog.show();

//        try {
        // Start the MediaController
//            MediaController mediacontroller = new MediaController(
//                    PembahasanActivity.this);
//            mediacontroller.setAnchorView(videoPembahasan);
//            // Get the URL from String VideoURL
//            Uri video = Uri.parse(VideoURL);
//            videoPembahasan.setMediaController(mediacontroller);
//            videoPembahasan.setVideoURI(video);
//            videoPembahasan.start();
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }

//        videoPembahasan.requestFocus();
//        videoPembahasan.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            // Close the progress bar and play the video
//            public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
//                videoPembahasan.start();
//            }
//        });
        pDialog = new ProgressDialog(VideoServerActivity.this);
//        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
//        // Set progressbar message
        pDialog.setMessage("Please Wait...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        final MediaController mediaController = new MediaController(this);

        try {
            Uri uri = Uri.parse(VideoURL);
            // Start the MediaController
//            MediaController mediacontroller = new MediaController(
//                    PembahasanActivity.this);
//            mediacontroller.setAnchorView(videoPembahasan);
//            // Get the URL from String VideoURL
//            videoPembahasan.setMediaController(mediacontroller);
            videoView.setVideoURI(uri);

        } catch (Exception ex){

        }

        videoView.requestFocus();
        //videoPembahasan.setZOrderOnTop(true);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mp.setLooping(true);

                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
            }
        });

    }

    public void initSumComment(int videoID){
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<CommentResponse> call = apiInterface.getComments(videoID);
        call.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                ArrayList<Comment> comment = (ArrayList<Comment>) response.body().getComment();
                mComment = comment;
                if (mComment != null){
                    txtSumComment.setText("("+mComment.size()+") comments");

                    listCommentAdapter = new ListCommentAdapter(getApplicationContext(), mComment);
                    listComment.setAdapter(listCommentAdapter);
                    listComment.setExpanded(true);
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                txtSumComment.setText("(0) comments");
            }
        });
    }

    public void sendComment(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        initPengguna();
        String komen = inComment.getText().toString();
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        apiInterface.insertKomen(komen,dateFormat.format(date), videoID, penggunaID, "1", 0).enqueue(new Callback<KomenHelper>() {
            @Override
            public void onResponse(Call<KomenHelper> call, Response<KomenHelper> response) {
                Toast.makeText(getApplicationContext(), "Komentar berhasil ditambahkan", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
//                intent.putExtra("videoID", videoID);
//                intent.putExtra("judulVideo", judulVideo);
//                intent.putExtra("link", trueLink);
//                System.out.println("video id pas dikirim "+videoID);
//                startActivity(intent);
                initSumComment(videoID);
                inComment.setText("");
            }

            @Override
            public void onFailure(Call<KomenHelper> call, Throwable t) {
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

}
