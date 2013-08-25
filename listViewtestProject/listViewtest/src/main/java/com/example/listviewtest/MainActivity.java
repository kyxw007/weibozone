package com.example.listviewtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import kyxw007.QzoneV2;
import listview01.listview01;

public class MainActivity extends Activity {
    public String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "start QzoneV2!");
        final QzoneV2 qzone =new QzoneV2("515779871","b2840255",getApplicationContext());
/*
        new Thread(){
            @Override
            public void run() {
                super.run();
                qzone.login();
                qzone.getCurrent(20);
            }
        }.start();
*/

        listenner clicklisten=new listenner();
        Button listview01bu = (Button) findViewById(R.id.listview01);
        listview01bu.setOnClickListener(clicklisten);

        Button listview02bu = (Button) findViewById(R.id.listview02);
        listview02bu.setOnClickListener(clicklisten);

        Button DBtestbu=(Button) findViewById(R.id.database);
        DBtestbu.setOnClickListener(clicklisten);

        Button choseContent = (Button) findViewById(R.id.chosecontent);
        choseContent.setOnClickListener(clicklisten);

        Button listView03Bu = (Button) findViewById(R.id.listview03);
        listView03Bu.setOnClickListener(clicklisten);

        Button listview04Bu = (Button) findViewById(R.id.listview04);
        listview04Bu.setOnClickListener(clicklisten);


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
                case R.id.listview03:
                    Intent listview03Intent = new Intent();
                    listview03Intent.setClass(MainActivity.this,listview03.listview03.class);
                    MainActivity.this.startActivity(listview03Intent);
                    break;
                case R.id.listview04:
                    Intent listview04Intent = new Intent();
                    listview04Intent.setClass(MainActivity.this,listview04.listview04.class);
                    MainActivity.this.startActivity(listview04Intent);

            }
        }
    }

}
///home/kyxw007/android-studio/sdk/tools/templates/gradle/wrapper/gradlew clean

