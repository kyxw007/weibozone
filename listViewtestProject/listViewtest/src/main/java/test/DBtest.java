package test;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listviewtest.R;

/**
 * Created by kyxw007 on 13-7-28.
 */
public class DBtest extends Activity {

    public TextView showTv;
    public EditText statuEt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        Button writeBu = (Button) findViewById(R.id.write);
        Button readBu = (Button) findViewById(R.id.read);
        showTv = (TextView) findViewById(R.id.showTV);
        statuEt = (EditText) findViewById(R.id.status);


//        DB.open();


        Listener ourListener = new Listener();
        writeBu.setOnClickListener(ourListener);


    }

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.write:
                    String status = statuEt.getText().toString();
                    DBzone DB = new DBzone(DBtest.this);
                    DB.open();
                    DB.entry(status);
                    break;
                case R.id.read:
//                    status = DB.getstasus();
//                    showTv.setText(status);
                    break;


            }
        }
    }
}