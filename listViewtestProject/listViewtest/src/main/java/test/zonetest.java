package test;

import android.app.Activity;
import android.os.Bundle;

import com.example.listviewtest.R;

/**
 * Created by kyxw007 on 13-7-30.
 */
public class zonetest extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBzone zoneDatabase = new DBzone(getApplicationContext());
        zoneDatabase.open();
        zoneDatabase.Logon("515779871","b2840255");
        if(zoneDatabase.isLogon(null)){
            zoneDatabase.close();
            setContentView(R.layout.dbtest);
        }else{
            zoneDatabase.close();
            setContentView(R.layout.notyetlogon);
        }
    }
}