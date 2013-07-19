package kyxw007;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kyxw007 on 13-7-15.
 */
public class QzoneV2 {
    public String sid;
    public String UID;
    public String psw;
    public String index;
    private String TAG = "QzoneV2";
    Context context;

    /**
    * QQID qq号码
    * password qq 密码
    */
    public QzoneV2(String QQID, String password,Context context) {
        this.UID=QQID;
        this.psw=password;
        this.context=context;
        login();
    }

    public void login(){
        final String url = "http://pt.3g.qq.com/s?aid=nLoginqz&sid=AenUUJJMMG1FPQHHKFt65K91&KqqWap_Act=3&g_ut=2&go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp%3Fsid%3DAenUUJJMMG1FPQHHKFt65K91%26KqqWap_Act%3D5%263g_style%3D1%26g_f%3D2425";

        new Thread(){
            @Override
            public void run() {
                super.run();
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String loginURL = doc.getElementById("qq_loginform").attr("action").toString();
                String data = "login_url=http://pt.3g.qq.com/s?aid=nLogin&go_url=http%3A%2F%2Fish.z.qq.com%2Ffeeds_my.jsp%3FB_UID%3D515779871%26myFeedNo%3D1%26sid%3DAdkdKEH7wD1_Onyh1yxcIb6M%26rand%3D717581&defaultQQ="
                        + UID
                        + "&sid="
                        + "&sidtype=1&nopre=0&bid=0&go_url=http://ish.z.qq.com/feeds_my.jsp?B_UID=515779871&myFeedNo=1&sid=AdkdKEH7wD1_Onyh1yxcIb6M&rand=717581&qq="
                        + UID + "&pwd=" + psw + "&loginType=3";
                HttpURLConnection updateConn;
                String respone = "";
                try {
                    URL updateURL = new URL(loginURL);
                    updateConn = (HttpURLConnection) updateURL.openConnection();
                    updateConn.setDoOutput(true);
                    updateConn.setRequestMethod("POST");
                    updateConn.connect();
                    OutputStream out = updateConn.getOutputStream();
                    out.write(data.getBytes());
                    out.flush();
                    // updateConn.setDoInput(true);
                    InputStream inputStream;
                    inputStream = updateConn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            inputStream, "utf-8"));
                    String L = br.readLine();

                    while (L != null) {
                        respone = respone + L;
                        L = br.readLine();
                    }
                    // System.out.print("zzzzz");
                    // System.out.print(respone);
                  //  Toast.makeText(context,respone,Toast.LENGTH_LONG);
                    Log.d(TAG,respone);


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
