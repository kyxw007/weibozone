package kyxw007;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import test.DBzone;

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
    public QzoneV2(String QQID, String password, Context context) {
        this.UID = QQID;
        this.psw = password;
        this.context = context;
        login();
    }

    public void login() {
        final String url = "http://pt.3g.qq.com/s?aid=nLoginqz&sid=AenUUJJMMG1FPQHHKFt65K91&KqqWap_Act=3&g_ut=2&go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp%3Fsid%3DAenUUJJMMG1FPQHHKFt65K91%26KqqWap_Act%3D5%263g_style%3D1%26g_f%3D2425";

        new Thread() {
            @Override
            public void run() {
                super.run();
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                    //Log.d(TAG,doc.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String loginURL = doc.getElementById("qq_loginform").attr("action").toString();
                Log.d(TAG, loginURL);

                String data = "aid=nLogin&go_url=http%3A%2F%2Fish.z.qq.com%2Ffeeds_my.jsp%3FB_UID%3D515779871%26myFeedNo%3D1%26sid%3DAdkdKEH7wD1_Onyh1yxcIb6M%26rand%3D717581&defaultQQ="
                        + UID
                        + "&sid="
                        + "&sidtype=1&nopre=0&bid=0&go_url=http://ish.z.qq.com/feeds_my.jsp?B_UID=515779871&myFeedNo=1&sid=AdkdKEH7wD1_Onyh1yxcIb6M&rand=717581&qq="
                        + UID + "&pwd=" + psw + "&loginType=3";
                HttpURLConnection updateConn;
                String respone = "";
                HttpPost httpPost = new HttpPost(loginURL);
                List params = new ArrayList();
                NameValuePair pair1 = new BasicNameValuePair("qq", UID);
                NameValuePair pair2 = new BasicNameValuePair("pwd", psw);
                params.add(pair1);
                params.add(pair2);

                HttpEntity he;
                try {
                    he = new UrlEncodedFormEntity(params, "utf-8");
                    httpPost.setEntity(he);
                    Log.d(TAG, "参数设置成功");

                } catch (UnsupportedEncodingException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                HttpClient hc = new DefaultHttpClient();
                try {
                    HttpResponse ht = hc.execute(httpPost);
                    //连接成功
                    Log.d(TAG, "链接成功");
                    if (ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        HttpEntity het = ht.getEntity();
                        InputStream is = het.getContent();
                        Log.d(TAG, "接受返回数据");
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        Log.d(TAG, "生成 BufferReader ");
                        String response = "";
                        String readLine = br.readLine();
                        while (readLine != null) {

                            readLine = br.readLine();
                            response = response + readLine;

                        }
                        is.close();
                        br.close();
                        //Log.d(TAG, response);
                        doc = Jsoup.parse(response);
                        // Log.d(TAG,doc.toString());


                        //获取sid
                        String S = doc.getElementById("main-nav-host")
                                .getElementsByClass("current").attr("href").toString();
                        // System.out.print("\n" + S);
                        int poSid = S.indexOf("sid=") + 4;
                        sid = S.substring(poSid);
                        Log.d(TAG, sid);


                    } else {
                        Log.d(TAG, "code not OK!");

                    }
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


/*
                String data ="&qq="+ UID + "&pwd=" + psw ;
                Log.d(TAG,"参数设置晚成");
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
                    Log.d(TAG,respone);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
            }
        }.start();
    }

    public void getCurrent() {

    }

    public class Current {
        public DBzone DB;

        public Current() {
            DB = new DBzone(context);
        }

        private void InsertData(String useName, String UidPic, String Zstatus, String STRcreattime, String ZanUrl, String commentURL, String authorUrl, String picUrl, String repostUrl) {
            DB.open();
            ContentValues cv = new ContentValues();
            cv.put(DB.key_useName, useName);
            cv.put(DB.key_UidPic, UidPic);
            cv.put(DB.key_Zstatus, Zstatus);
            cv.put(DB.key_STRcreattime, STRcreattime);
            cv.put(DB.key_ZanUrl, ZanUrl);
            cv.put(DB.key_commentURL, commentURL);
            cv.put(DB.key_authorUrl, authorUrl);
            cv.put(DB.key_picUrl, picUrl);
            cv.put(DB.key_repostUrl, repostUrl);
            DB.insert_inuse(cv);
            DB.close();

        }
    }


}
