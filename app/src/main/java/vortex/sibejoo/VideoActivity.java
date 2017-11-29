package vortex.sibejoo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private ProgressDialog pDialog;
    YouTubePlayerView video;
    TextView txtJudulVideo;
    int videoID;
    String judulVideo, linkVideo, trueLink;
    private static final int REQUEST_NUMBER = 999;
    String YOUTUBE_API_KEY = "AIzaSyBBjqg4ru2Z0KeN9EUuWT26Oi74eUwlffI";
    EditText inComment;
    ArrayList<Comment> mComment;
    TextView txtSumComment;
    ExpandableHeightListView listComment;
    ListCommentAdapter listCommentAdapter;
    ImageView imgSend;
    int penggunaID;
    ArrayList<SiswaHelper> penggunaHelper;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        txtJudulVideo = (TextView) findViewById(R.id.txtJudulVideo);
        video = (YouTubePlayerView) findViewById(R.id.youtube_fragment);
        inComment = (EditText) findViewById(R.id.inComment);
        txtSumComment = (TextView) findViewById(R.id.txtSumComment);
        listComment = (ExpandableHeightListView) findViewById(R.id.listComments);
        btnSend = (Button) findViewById(R.id.btnSend);
//        imgSend = (ImageView) findViewById(R.id.imageView10);
        //Picasso.with(getApplicationContext()).load("https://www.kanal247.com/images/media/photo/2017/06/07/cantik.jpg").into(imgSend);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_send_black_40dp);

        // Set image as bitmap for fourth ImageView
//        imgSend.setImageBitmap(bitmap);
//        inComment.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
//        inComment.getBackground().clearColorFilter;
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            videoID = Integer.valueOf(bundle.getString("videoID"));
            judulVideo = bundle.getString("judulVideo");
            linkVideo = bundle.getString("link");
        }
        System.out.println("Ewooooo "+linkVideo);
        System.out.println("video id pas diterima "+videoID);
        txtJudulVideo.setText(judulVideo);
        initSumComment(videoID);
//        initVideo(linkVideo);
        //initVideo(linkVideo);
//        String[] arr = linkVideo.split("/");
//        for (int i=0; i< arr.length; i++){
//            System.out.println("Elemen ke "+i+" isinya "+arr[i]);
//            trueLink = arr[i];
//        }
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendComment();
            }
        });
        video.initialize(YOUTUBE_API_KEY,this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
//<iframe width="854" height="480" src="https://www.youtube.com/embed/Ocl-PvVkATg?list=PLP0SflX7_QUX12WjgIhZwK0WIwskSYAxx&amp;ecver=1" frameborder="0" allowfullscreen></iframe>
//<iframe width="854" height="480" src="https://www.youtube.com/embed/y8JnipoOcU4?ecver=1" frameborder="0" allowfullscreen></iframe>
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            youTubePlayer.cueVideo(linkVideo);
            /**
             * there are 2 method you can user here :
             * .cueVideo(), for didn't play automatically
             * .loadVideo(), for do play automatically
             *
             * if you are using play automatically, it better if you hide the video controllers
             * do like below :
             * youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);//hide the players controllers
             */
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_NUMBER).show();
        }else{
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)", youTubeInitializationResult.toString()
            );
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_NUMBER){
            video.initialize(YOUTUBE_API_KEY,this);
        }
    }

//    public void initVideo(String url){
//        pDialog = new ProgressDialog(VideoActivity.this);
////        // Set progressbar title
//        pDialog.setTitle("Android Video Streaming Tutorial");
////        // Set progressbar message
//        pDialog.setMessage("Please Wait...");
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//
//        try {
//            Uri uri = Uri.parse(url);
//            // Start the MediaController
////            MediaController mediacontroller = new MediaController(
////                    PembahasanActivity.this);
////            mediacontroller.setAnchorView(videoPembahasan);
////            // Get the URL from String VideoURL
////            videoPembahasan.setMediaController(mediacontroller);
//            video.setVideoURI(uri);
//
//        } catch (Exception ex){
//
//        }
//
//        video.requestFocus();
//        //videoPembahasan.setZOrderOnTop(true);
//        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
//                mp.setLooping(true);
//                video.start();
//            }
//        });
//    }

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
