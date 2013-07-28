package test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kyxw007 on 13-7-28.
 */
public class DBzone {
    private static final String TAG = "DBzone";
    public static final String DATABASE_NAME = "zonedb";
    public static final String DATABASE_TABLENAME = "zonetable";
    public static final int DATABASE_VERSION = 1;

    public static final String key_useName = "useName";
    public static final String key_UidPic = "UidPic";
    public static final String key_Zstatus = "status";
    public static final String key_STRcreattime = "STRcreattime";
    public static final String key_ZanUrl = "ZanUrl";
    public static final String key_commentURL = "commentURL";
    public static final String key_authorUrl = "authorUrl";
    public static final String key_picUrl = "picUrl";
    public static final String key_repostUrl = "repostUrl";

    private Dbhelper ourHelper;
    private SQLiteDatabase ourDatabase;
    private Context ourContext;

    public void entry(String status) {

    }

    public String getstasus() {
        return null;
    }

    private static class Dbhelper extends SQLiteOpenHelper{
        public Dbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG,"table create preparing");

            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLENAME + "(" +
                    key_commentURL + " TEXT , " +
//                    key_authorUrl+" TEXT NOT NULL," +
//                    key_picUrl + " TEXT, " +
//                    key_repostUrl + " TEXT, " +
//                    key_STRcreattime +" TEXT," +
//                    key_UidPic + " TEXT," +
//                    key_useName + " TEXT, " +
//                    key_ZanUrl + " TEXT, " +
                    key_Zstatus + " TEXT );"


            );
            Log.d(TAG,"table created");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }

    public DBzone(Context context) {
        this.ourContext = context;
    }
    public DBzone open(){
        ourHelper = new Dbhelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
}
