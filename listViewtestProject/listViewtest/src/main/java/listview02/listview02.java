package listview02;

/**
 * Created by kyxw007 on 13-7-13.
 */
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

import com.example.listviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class listview02 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview02);
        //tuangoupoints为对后台传回来的数据解析后得到的字符串
        //String[] mtuangoupoints =tuangoupoints.split("@");
        ListView mlistView = (ListView) findViewById(R.id.listView);
        List<MapListImageAndText> dataArray=new ArrayList<MapListImageAndText>();

        for(int i=0; i<20;i++)
        {

            String shopname="wowowo";
            String activityinfo="my first";
            String address="回复发挥阿飞胡光荣得23号";
            String telephone="15017564829";
            String imageurl="http://www.jeanneverdoux.com/design/images/wowo_1.jpg";
            String distance="10000";

            MapListImageAndText test=new MapListImageAndText(imageurl,shopname,activityinfo,address,telephone,distance);
            dataArray.add(test);
        }

        MapListImageAndTextListAdapter adapter=new MapListImageAndTextListAdapter(this, dataArray, mlistView);
        mlistView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}