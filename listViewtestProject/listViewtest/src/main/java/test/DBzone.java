package test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
        Logoninit();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    /*test 插入status*/
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

    public long insert_inuse(ContentValues cv) {
        long result = ourDatabase.insert(DATABASE_TABLENAME, null, cv);
//        long result = 100;

        Log.d(TAG, "successuf insert " + result);
        return result;
    }
    /*test 获取status*/
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
                all = all + "@" + result.getString(nameColumnIndex);
            }
            return all;
        }

        //     return null;
    }

    public ArrayList<ContentValues> getstasus_inuse(int num) {
        Log.d(TAG, "Select PREPARE? ");
        Cursor result = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLENAME + " LIMIT " + num, null);
        Log.d(TAG, "Select sucessful: " + result.toString());

        if (result.moveToFirst() == false) {
            return null;
        } else {
            ArrayList<ContentValues> av= new ArrayList<ContentValues>();

            while (result.moveToNext()) {
                ContentValues cv = new ContentValues();
                int nameColumnIndex;
                nameColumnIndex = result.getColumnIndex(key_Zstatus);
                cv.put(key_Zstatus,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_useName);
                cv.put(key_useName,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_UidPic);
                cv.put(key_UidPic,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_STRcreattime);
                cv.put(key_STRcreattime,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_ZanUrl);
                cv.put(key_ZanUrl,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_commentURL);
                cv.put(key_commentURL,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_authorUrl);
                cv.put(key_authorUrl,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_picUrl);
                cv.put(key_picUrl,result.getString(nameColumnIndex));
                nameColumnIndex = result.getColumnIndex(key_repostUrl);
                cv.put(key_repostUrl,result.getString(nameColumnIndex));
                av.add(cv);
            }
            return av;
        }

        //     return null;
    }

    /*删除表 DATABASE_TABLENAME*/
    public void DELET() {
        Log.d(TAG, "clear table processing");
        ourDatabase.execSQL("DELETE FROM " + DATABASE_TABLENAME);
        Log.d(TAG, "clear job Done");
//        ourDatabase.execSQL("DROP TABLE " + DATABASE_TABLENAME);
    }


    /*登录信息写入数据库*/
    public void Logon(String UID ,String password){
        ContentValues cv = new ContentValues();
        cv.put("UID",UID);
        cv.put("PASSWORD",password);
        long result = ourDatabase.insert("LOGINUSER", null, cv);
    }

    /*判断ＱＱ用户是否已经登录*/
    public boolean isLogon(String UID){
        if (UID!=null){
            Cursor result = ourDatabase.rawQuery("SELECT " + UID + " FROM  LOGINUSER;",null);
            if(result.moveToFirst()){
                return true;
            }
            return false;
        }else{
            Cursor result = ourDatabase.rawQuery("SELECT * FROM  LOGINUSER;",null);
            if(result.moveToFirst()){
                return true;
            }
            return false;
        }

    }

    /*初始化用户表*/
    private void Logoninit(){
        ourDatabase.execSQL("CREATE TABLE IF NOT EXISTS LOGINUSER (" +
                "UID TEXT  PRIMARY KEY , " +
                "PASSWORD TEXT); "
        );
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
