package listview01;

/**
 * Created by kyxw007 on 13-7-13.
 */
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.listviewtest.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import test.DBzone;

public class listview01 extends Activity {
    ListView listview;
    DBzone DB;
    Handler handler;
    listview01 class_this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview01);
        listview = (ListView) findViewById(R.id.listView);
        DB = new DBzone(getApplicationContext());
        initData();
        listview.setDivider(null);
        SimpleAdapter adpter;
        adpter = new SimpleAdapter(this,   getData(),R.layout.unit01, new String[]{"uidpic", "status", "statusPic", "userName"}, new int[]{R.id.UIDpicture, R.id.status, R.id.statimg, R.id.username});
        class_this = this;
        listview.setAdapter(adpter);

        listener listen = new listener();
        Button reflashBu = (Button) findViewById(R.id.reflash);
        reflashBu.setOnClickListener(listen);
    }
    private void initData(){
        DB.open();
        ContentValues cv = new ContentValues();
        cv.put(DB.key_useName, "kyxw0071");
        cv.put(DB.key_UidPic, "kyxw0072");
        cv.put(DB.key_Zstatus, "wowuioehfgfqaijoi");
        cv.put(DB.key_STRcreattime, "kyxw0073");
        cv.put(DB.key_ZanUrl, "kyxw0074");
        cv.put(DB.key_commentURL, "kyxw0075");
        cv.put(DB.key_authorUrl, "kyxw0076");
        cv.put(DB.key_picUrl, "kyxw0077");
        cv.put(DB.key_repostUrl, "kyxw0078");
        for (int i=0 ; i<30 ; i++){
            DB.insert_inuse(cv);
        }
        DB.close();

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        DB.open();
        ArrayList<ContentValues> av = DB.getstasus_inuse(10);

        for (int i = 0 ; i< av.size() ; i++){
            ContentValues cv = av.get(i);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("uidpic", null);
            map.put("status", cv.get(DB.key_Zstatus));
            map.put("statusPic",null);
            map.put("userName",cv.get(DB.key_useName));
            list.add(map);


        }






       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("uidpic", null);
        map.put("status", "我的第一条说说哦~~~~~~~~www.sina.com");
        map.put("statusPic",null);
        map.put("userName","Kyxw007");
        list.add(map);
        DBzone DB = new DBzone(getApplicationContext());
        DB.open();


        for (int i=0;i<20;i++){
            map = new HashMap<String, Object>();
            map.put("uidpic", R.drawable.usercool);
            map.put("status", "http://www.baidu.com");
            map.put("statusPic", R.drawable.usercool);
            map.put("userName","Kyxw007");
            list.add(map);
        }
        map = new HashMap<String, Object>();
        map.put("uidpic", R.drawable.usercool);
        map.put("status", "http://www.baidu.com");
        map.put("statusPic", R.drawable.m_02);
        map.put("userName","Kyxw007");
        list.add(map);*/
        return list;
    }

    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private class Myadapter extends SimpleAdapter{

        int count;
        private List<Map<String, Object>>  mItemList;

        public Myadapter(Context context, List<Map<String, Object>>  data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mItemList=data;
            if(data == null){
                count = 0;
            }else{
                count = data.size();
            }

        }
        public int getCount() {
            return mItemList.size();
        }

        public Object getItem(int pos) {
            return pos;
        }

        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map<String, Object> map = mItemList.get(position);

            return super.getView(position, convertView, parent);
        }
    }

    private class listener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.reflash:
                    SimpleAdapter adpter;
                    adpter = new SimpleAdapter(class_this,getData(),R.layout.unit01, new String[]{"uidpic", "status", "statusPic", "userName"}, new int[]{R.id.UIDpicture, R.id.status, R.id.statimg, R.id.username});
                    listview.setAdapter(adpter);

            }
        }
    }
}