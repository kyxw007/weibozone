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
    public DBzone DB;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        Button writeBu = (Button) findViewById(R.id.write);
        Button readBu = (Button) findViewById(R.id.read);
        Button resetBu = (Button) findViewById(R.id.reset);
        showTv = (TextView) findViewById(R.id.showTV);
        statuEt = (EditText) findViewById(R.id.status);
        DB = new DBzone(DBtest.this);

//        DB.open();


        Listener ourListener = new Listener();
        writeBu.setOnClickListener(ourListener);
        readBu.setOnClickListener(ourListener);
        resetBu.setOnClickListener(ourListener);


    }

    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.write:
                    String status = statuEt.getText().toString();
                    DB.open();
                    DB.insert(status);
                    DB.close();
                    break;
                case R.id.read:
                    DB.open();
                    status = DB.getstasus();
                    showTv.setText(status);
                    DB.close();
                    break;
                case R.id.reset:
                    DB.open();
                    DB.DELET();
                    DB.close();
                    break;



            }
        }
    }
}