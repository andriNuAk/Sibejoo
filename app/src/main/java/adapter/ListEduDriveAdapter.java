package adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import helper.FileDownloader;
import model.EduDrive;
import vortex.sibejoo.R;

import static vortex.sibejoo.R.id.imageView;

/**
 * Created by M on 11/1/2017.
 */

public class ListEduDriveAdapter extends BaseAdapter {

    Context context;
    ArrayList<EduDrive> mEduDrive;
    LayoutInflater inflater;
    ProgressDialog pDialog;

    public ListEduDriveAdapter(Context context, ArrayList<EduDrive> mEduDrive) {
        this.context = context;
        this.mEduDrive = mEduDrive;
    }

    @Override
    public int getCount() {
        return mEduDrive.size();
    }

    @Override
    public Object getItem(int position) {
        return mEduDrive.get(position);
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
            convertView = inflater.inflate(R.layout.list_modul, parent, false);
        }

        TextView txtJudulModul, txtDeskripsiModul;
        final Button btnDownload;
//        final ImageView imgDownloadModul;

        txtJudulModul = (TextView) convertView.findViewById(R.id.txtJudulModul);
        txtDeskripsiModul = (TextView) convertView.findViewById(R.id.txtDeskripsiModul);
        btnDownload = (Button) convertView.findViewById(R.id.btnDownloadModul);
//        imgDownloadModul = (ImageView) convertView.findViewById(R.id.imgDownloadModul);

        txtJudulModul.setTypeface(txtJudulModul.getTypeface(), Typeface.BOLD);
        txtJudulModul.setText(mEduDrive.get(position).getJudul().toString());
        txtDeskripsiModul.setText(mEduDrive.get(position).getDeskripsi().toString());

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        File f = new File((Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/").toString());
        File file[] = f.listFiles();
        for (int i = 0; i < file.length; i++){
            if (file[i].getName().equalsIgnoreCase(mEduDrive.get(position).getUrlFile())){
//                int id = context.getResources().getIdentifier("ic_find_in_page_black_24dp", "drawable", context.getPackageName());
//                imgDownloadModul.setImageResource(id);
//                imgDownloadModul.setTag("lihat");
                btnDownload.setText("Lihat");
                break;
            } else {
                int id = context.getResources().getIdentifier("ic_cloud_download_black_24dp", "drawable", context.getPackageName());
//                imgDownloadModul.setImageResource(id);
//                imgDownloadModul.setTag("download");
                btnDownload.setText("Download");
            }
        }

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDownload.getText().equals("Lihat")){
                    String file = mEduDrive.get(position).getUrlFile();
                    String[] format = file.split("\\.");
                    System.out.println("Weleeeeh "+format[1].toString());

                    new ListEduDriveAdapter.DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/modul/"+mEduDrive.get(position).getUrlFile(), mEduDrive.get(position).getUrlFile());
                    File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + mEduDrive.get(position).getUrlFile());  // -> filename = maven.pdf
                    Uri path = Uri.fromFile(pdfFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    if (format[1].equalsIgnoreCase("docx") || format[1].equalsIgnoreCase("doc")){
                        pdfIntent.setDataAndType(path, "application/word");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        System.out.println("weleeehhhh "+mEduDrive.get(position).getUrlFile());
                        context.startActivity(pdfIntent);
                    } else if (format[1].equalsIgnoreCase("pdf")){
                        pdfIntent.setDataAndType(path, "application/pdf");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        System.out.println("weleeehhhh "+mEduDrive.get(position).getUrlFile());
                        context.startActivity(pdfIntent);
                    }

                } else if (btnDownload.getText().equals("Download")){
                    new ListEduDriveAdapter.DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/modul/"+mEduDrive.get(position).getUrlFile(), mEduDrive.get(position).getUrlFile());
                    btnDownload.setText("Lihat");
                }
            }
        });




        return convertView;
    }


    public class DownloadFile extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (!pDialog.isShowing())
                pDialog.show();
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
            if (pDialog.isShowing())
                pDialog.dismiss();
            Toast.makeText(context.getApplicationContext(), "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }
}
