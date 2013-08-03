package listview03;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.listviewtest.R;


public class listview03 extends Activity {

	private final static String TAG = "ChrisLV";
	private ListViewExt mListView = null;
	private String[] mList = {
		"abcd1", "abcd2", "abcd3", "abcd4", "abcd5", "abcd6",
		"abcd7", "abcd8", "abcd9", "abcd10", "abcd11", "abcd12",
		"abcd13", "abcd14", "abcd15", "abcd16", "abcd17", "abcd18",
		"abcd19", "abcd20", "abcd21", "abcd22", "abcd23", "abcd24"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview03);
		
		mListView = (ListViewExt) findViewById(R.id.listview);
		mListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, 
				mList));
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d(TAG, "arg2 = " + arg2);
				if(arg2 > 0){
					mListView.stopRefresh();
					mListView.stopLoad();
				}
				
				mListView.setFooterMode(arg2 % 2);
			}
		});
	}

}
