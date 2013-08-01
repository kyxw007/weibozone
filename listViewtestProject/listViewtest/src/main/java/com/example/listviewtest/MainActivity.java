package com.example.listviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.List;

import kyxw007.QzoneV2;
import kyxw007.qzone;
import listview01.listview01;
import test.internetst;
import test.jsoupt;

public class MainActivity extends Activity {
    public String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "start QzoneV2!");
        QzoneV2 qzone =new QzoneV2("515779871","b2840255",getApplicationContext());
        listenner clicklisten=new listenner();
        Button listview01bu = (Button) findViewById(R.id.listview01);
        listview01bu.setOnClickListener(clicklisten);

        Button listview02bu = (Button) findViewById(R.id.listview02);
        listview02bu.setOnClickListener(clicklisten);

        Button DBtestbu=(Button) findViewById(R.id.database);
        DBtestbu.setOnClickListener(clicklisten);

        Button choseContent = (Button) findViewById(R.id.chosecontent);
        choseContent.setOnClickListener(clicklisten);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public class listenner implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.listview01:
                    Intent listview01Intent = new Intent();
                    listview01Intent.setClass(MainActivity.this, listview01.class);
                    MainActivity.this.startActivity(listview01Intent);
                    break;
                case R.id.listview02:
                    Intent listview02Intent = new Intent();
                    listview02Intent.setClass(MainActivity.this, listview02.listview02.class);
                    MainActivity.this.startActivity(listview02Intent);
                    break;
                case R.id.database:
                    Intent DBIntent = new Intent();
                    DBIntent.setClass(MainActivity.this,test.DBtest.class);
                    MainActivity.this.startActivity(DBIntent);
                    break;
                case R.id.chosecontent:
                    Intent chosecontent = new Intent();
                    chosecontent.setClass(MainActivity.this,test.zonetest.class);
                    MainActivity.this.startActivity(chosecontent);
                    break;


            }
        }
    }

}
///home/kyxw007/android-studio/sdk/tools/templates/gradle/wrapper/gradlew clean

