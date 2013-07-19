package listview01;

/**
 * Created by kyxw007 on 13-7-13.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.os.Message;
import android.view.Menu;
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

public class listview01 extends Activity {
    ListView listview;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview01);
        listview = (ListView) findViewById(R.id.listView);
        listview.setDivider(null);
        SimpleAdapter adpter;
        adpter = new SimpleAdapter(this,   getData(),R.layout.unit01, new String[]{"uidpic", "status", "statusPic", "userName"}, new int[]{R.id.UIDpicture, R.id.status, R.id.statimg, R.id.username});

        listview.setAdapter(adpter);



    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uidpic", null);
        map.put("status", "我的第一条说说哦~~~~~~~~www.sina.com");
        map.put("statusPic",null);
        map.put("userName","Kyxw007");
        list.add(map);
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
        list.add(map);
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

}