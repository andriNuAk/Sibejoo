package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M on 11/17/2017.
 */

public class LogHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DataSibejoo";
    private final static String TABLES[] = {"id", "penggunaID", "stepID", "statusStep"};
    private final static  String NAMA_TABEL = "tb_log";


    public LogHelper(Context context) {
        super(context ,DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTabelLog(SQLiteDatabase db){
        db.execSQL("CREATE TABLE if not exists "+NAMA_TABEL+" (id INTEGER PRIMARY KEY , penggunaID INTEGER, stepID INTEGER, statusStep TEXT);");
    }

    public void insertLog(SQLiteDatabase db, int id, int penggunaID, int stepID, String statusStep) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("penggunaID", penggunaID);
        contentValues.put("stepID", stepID);
        contentValues.put("statusStep", statusStep);
        db.insert(NAMA_TABEL, null, contentValues);
    }

    public void updateLog(SQLiteDatabase db, int id, int penggunaID, int stepID, String statusStep){
        ContentValues contentValues = new ContentValues();
        contentValues.put("penggunaID", penggunaID);
        contentValues.put("stepID", stepID);
        contentValues.put("statusStep", statusStep);
        db.update(NAMA_TABEL, contentValues, "id = "+id, null);
    }


    public void deleteLog(SQLiteDatabase db, int penggunaID) {
        db.delete(NAMA_TABEL, "penggunaID =" + penggunaID, null);
    }

    public Cursor getAllLog(SQLiteDatabase db){
        return  db.query(NAMA_TABEL, TABLES, null, null, null, null, null);
    }

    public Cursor getLog(SQLiteDatabase db, int id){
        return  db.query(NAMA_TABEL, TABLES, "id = "+id, null, null, null, null);
    }

    public Cursor getLogByPenggunaStep(SQLiteDatabase db, int penggunaID, int stepID){
        return  db.query(NAMA_TABEL, TABLES, "pengguna = "+penggunaID+" AND stepID ="+stepID, null, null, null, null);
    }
}
