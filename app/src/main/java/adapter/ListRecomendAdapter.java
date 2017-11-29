package adapter;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import helper.FileDownloader;
import model.EduDrive;
import vortex.sibejoo.MainActivity;
import vortex.sibejoo.R;

/**
 * Created by M on 10/31/2017.
 */

public class ListRecomendAdapter extends RecyclerView.Adapter<ListRecomendAdapter.ItemHolder> {

    ArrayList<EduDrive> eduDrive;
    Context context;
    ProgressDialog pDialog;


    public ListRecomendAdapter(ArrayList<EduDrive> eduDrive, Context context) {
        this.eduDrive = eduDrive;
        this.context = context;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        TextView txtJudul;
        Button btnRecDownload;

        public ItemHolder(View itemView) {
            super(itemView);

            this.txtJudul = (TextView) itemView.findViewById(R.id.txtJudulModul);
            this.btnRecDownload = (Button) itemView.findViewById(R.id.btnRecDownload);



        }
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recomend, null);
        ItemHolder ih = new ItemHolder(v);
        return ih;
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        String judulModul = eduDrive.get(position).getJudul();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        holder.txtJudul.setText(judulModul);
        File f = new File((Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/").toString());
        File file[] = f.listFiles();
        for (int i = 0; i < file.length; i++){
            if (file[i].getName().equalsIgnoreCase(eduDrive.get(position).getUrlFile())){
                holder.btnRecDownload.setText("Lihat");
                break;
            } else {
                holder.btnRecDownload.setText("Unduh");
            }
        }
        holder.btnRecDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/modul/"+eduDrive.get(position).getUrlFile(), eduDrive.get(position).getUrlFile());
                if (holder.btnRecDownload.getText().equals("Lihat")){
                    String file = eduDrive.get(position).getUrlFile();
                    String[] format = file.split("\\.");
                        System.out.println("Weleeeeh "+format[1].toString());

                    new DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/modul/"+eduDrive.get(position).getUrlFile(), eduDrive.get(position).getUrlFile());
                    File pdfFile = new File(Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/" + eduDrive.get(position).getUrlFile());  // -> filename = maven.pdf
                    Uri path = Uri.fromFile(pdfFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    if (format[1].equalsIgnoreCase("docx") || format[1].equalsIgnoreCase("doc")){
                        pdfIntent.setDataAndType(path, "application/word");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        System.out.println("weleeehhhh "+eduDrive.get(position).getUrlFile());
                        context.startActivity(pdfIntent);
                    } else if (format[1].equalsIgnoreCase("pdf")){
                        pdfIntent.setDataAndType(path, "application/pdf");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        System.out.println("weleeehhhh "+eduDrive.get(position).getUrlFile());
                        context.startActivity(pdfIntent);
                    }

                } else if (holder.btnRecDownload.getText().equals("Unduh")){
                    new DownloadFile().execute("http://sibejooclass.com/heroo/beta/assets/modul/"+eduDrive.get(position).getUrlFile(), eduDrive.get(position).getUrlFile());
                    holder.btnRecDownload.setText("Lihat");
                }



//                Toast.makeText(context.getApplicationContext(), "PDF available in"+ path,Toast.LENGTH_LONG ).show();
//
//                File f = new File((Environment.getExternalStorageDirectory() + "/PDF DOWNLOAD/").toString());
//                File file[] = f.listFiles();
//                for (int i = 0; i < file.length; i++){
//                    if (file[i].getName().equalsIgnoreCase(eduDrive.get(position).getUrlFile())){
//
//                    } else {
//                        Toast.makeText(context.getApplicationContext(), "No Application available to view PDF", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                try{

//                }catch(ActivityNotFoundException e){
////
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eduDrive.size();
    }

    public class DownloadFile extends AsyncTask<String, Void, Void>{


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


