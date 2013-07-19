package test;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by kyxw007 on 13-7-14.
 */
public class jsoupt {
    private String Tag = "jsoupt";

    public jsoupt() {
        final String s = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><title></title><head></head><body></body></html>";

        Document doc = Jsoup.parse(s);//Jsoup.connect("http://www.baidu.com").get();

        Log.d(Tag, "come on");
        Log.d(Tag, doc.toString());


//        Document doc = Jsoup.parse(s);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Log.d(Tag, "oh man");

            }
        }.start();

    }
}
