package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M on 10/12/2017.
 */

public class SessionHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "DataSibejoo";
    private final static String TABLES[] = {"penggunaID", "namaDepan", "namaBelakang", "alamat", "noKontak", "namaSekolah", "alamatSekolah", "photo", "biografi", "kataSandi", "eMail" };
    private final static  String NAMA_TABEL = "tb_pengguna_siswa";


    public SessionHelper(Context context) {
        super(context ,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTabelPengguna(SQLiteDatabase db){
        db.execSQL("CREATE TABLE if not exists "+NAMA_TABEL+" (penggunaID INTEGER PRIMARY KEY , namaDepan TEXT, namaBelakang TEXT, alamat TEXT, noKontak TEXT, namaSekolah TEXT, alamatSekolah TEXT, photo TEXT, biografi TEXT, kataSandi TEXT, eMail TEXT);");
    }

    public void insertAkun(SQLiteDatabase db, int penggunaID, String namaDepan, String namaBelakang, String alamat, String noKontak, String namaSekolah, String alamatSekolah, String photo, String biografi, String kataSandi, String eMail) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("penggunaID", penggunaID);
        contentValues.put("namaDepan", namaDepan);
        contentValues.put("namaBelakang", namaBelakang);
        contentValues.put("alamat", alamat);
        contentValues.put("noKontak", noKontak);
        contentValues.put("namaSekolah", namaSekolah);
        contentValues.put("alamatSekolah", alamatSekolah);
        contentValues.put("photo", photo);
        contentValues.put("biografi", biografi);
        contentValues.put("kataSandi", kataSandi);
        contentValues.put("eMail", eMail);
        db.insert(NAMA_TABEL, null, contentValues);
    }

    public void updateProfil(SQLiteDatabase db, int penggunaID, String namaDepan, String namaBelakang, String alamat, String noKontak, String namaSekolah, String alamatSekolah, String biografi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("namaDepan", namaDepan);
        contentValues.put("namaBelakang", namaBelakang);
        contentValues.put("alamat", alamat);
        contentValues.put("noKontak", noKontak);
        contentValues.put("namaSekolah", namaSekolah);
        contentValues.put("alamatSekolah", alamatSekolah);
        contentValues.put("biografi", biografi);
        db.update(NAMA_TABEL, contentValues, "penggunaID = "+penggunaID, null);
    }

    public void updatePhotoProfil(SQLiteDatabase db, int penggunaID, String photo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("photo", photo);
        db.update(NAMA_TABEL, contentValues, "penggunaID = "+penggunaID, null);
    }

    public void updateEMailProfil(SQLiteDatabase db, int penggunaID, String eMail){
        ContentValues contentValues = new ContentValues();
        contentValues.put("eMail", eMail);
        db.update(NAMA_TABEL, contentValues, "penggunaID = "+penggunaID, null);
    }

    public void updatePasswordProfil(SQLiteDatabase db, int penggunaID, String kataSandi){
        ContentValues contentValues = new ContentValues();
        contentValues.put("kataSandi", kataSandi);
        db.update(NAMA_TABEL, contentValues, "penggunaID = "+penggunaID, null);
    }

    public void deleteDataAkun(SQLiteDatabase db, int id) {
        db.delete(NAMA_TABEL, "penggunaID =" + id, null);
    }

    public Cursor getAllAkun(SQLiteDatabase db){
        return  db.query(NAMA_TABEL, TABLES, null, null, null, null, null);
    }
}
