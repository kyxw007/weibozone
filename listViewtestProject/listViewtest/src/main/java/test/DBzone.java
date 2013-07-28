package test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public static final String key_useName = "USERNAME";
    public static final String key_UidPic = "UIDPIC";
    public static final String key_Zstatus = "STATUS";
    public static final String key_STRcreattime = "CREATIME";
    public static final String key_ZanUrl = "ZANURL";
    public static final String key_commentURL = "COMMENTURL";
    public static final String key_authorUrl = "AUTHORURL";
    public static final String key_picUrl = "PICURL";
    public static final String key_repostUrl = "REPOSTURL";
    public static final String key_rowiD = "_ID";

    private Dbhelper ourHelper;
    private SQLiteDatabase ourDatabase;
    private Context ourContext;

    public DBzone(Context context) {
        this.ourContext = context;
    }

    public DBzone open() {
        ourHelper = new Dbhelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }


    public long insert(String status) {
        ContentValues cv = new ContentValues();
        cv.put(key_useName, "kyxw007");
        cv.put(key_UidPic, "kyxw007");
        cv.put(key_Zstatus, status);
        cv.put(key_STRcreattime, "kyxw007");
        cv.put(key_ZanUrl, "kyxw007");
        cv.put(key_commentURL, "kyxw007");
        cv.put(key_authorUrl, "kyxw007");
        cv.put(key_picUrl, "kyxw007");
        cv.put(key_repostUrl, "kyxw007");
        long result = ourDatabase.insert(DATABASE_TABLENAME, null, cv);
//        long result = 100;

        Log.d(TAG, "successuf insert " + result);
        return result;

    }

    public String getstasus() {
        Log.d(TAG, "Select PREPARE? ");
        Cursor result = ourDatabase.rawQuery("SELECT " + key_Zstatus +
                " FROM " + DATABASE_TABLENAME, null);
        Log.d(TAG, "Select sucessful: " + result.toString());

        if (result.moveToFirst() == false) {
            return "数据库为空";
        } else {
            String all = "";
            while (result.moveToNext()) {
                int nameColumnIndex = result.getColumnIndex(key_Zstatus);
                all = all +"@"+ result.getString(nameColumnIndex);
            }
            return all;
        }

        //     return null;
    }
    public void DELET(){
        Log.d(TAG,"clear table processing");
        ourDatabase.execSQL("DELETE FROM " + DATABASE_TABLENAME);
        Log.d(TAG,"clear job Done");
//        ourDatabase.execSQL("DROP TABLE " + DATABASE_TABLENAME);
    }


    private static class Dbhelper extends SQLiteOpenHelper {
        public Dbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "table create preparing");

            sqLiteDatabase.execSQL("CREATE TABLE " + DATABASE_TABLENAME + " (" +
                    key_rowiD + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
                    key_commentURL + " TEXT, " +
                    key_authorUrl + " TEXT, " +
                    key_picUrl + " TEXT, " +
                    key_repostUrl + " TEXT, " +
                    key_STRcreattime + " TEXT, " +
                    key_UidPic + " TEXT, " +
                    key_useName + " TEXT, " +
                    key_ZanUrl + " TEXT, " +
                    key_Zstatus + " TEXT);"


            );
            Log.d(TAG, "table created");

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        }
    }
}
