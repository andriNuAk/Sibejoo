package vortex.sibejoo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import model.LearningLineVideo;

public class LearningLineVideoActivity extends Activity {

    String url;
    VideoView videoView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_line_video);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            url = bundle.getString("url");
        }

        videoView = (VideoView) findViewById(R.id.vidServerLearningLine);

        pDialog = new ProgressDialog(LearningLineVideoActivity.this);
//        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
//        // Set progressbar message
        pDialog.setMessage("Please Wait...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        final MediaController mediaController = new MediaController(this);

        try {
            Uri uri = Uri.parse(url);
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
}
