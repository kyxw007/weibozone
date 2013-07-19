package test;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kyxw007 on 13-7-15.
 */
public class internetst {
    private String TAG="internet";
    public internetst() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String url = "http://www.baidu.com";
                HttpURLConnection Conn;
                try {
                    URL myURL = new URL(url);
                    Conn = (HttpURLConnection) myURL.openConnection();
                    Conn.setDoInput(true);
                    Conn.setRequestMethod("GET");
                    // PrintWriter out = new PrintWriter(new
                    // OutputStreamWriter(Conn.getOutputStream(),"utf-8"));
                    Conn.connect();
                    InputStream inputStream;
                    inputStream = Conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    String L = br.readLine();
                    String respone = "";
                    while (L != null) {
                        respone = respone + L;
                        L = br.readLine();
                    }
                    Log.d(TAG,respone);
                    Log.d(TAG,"good");


                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    Log.d(TAG,e.getMessage());

                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.d(TAG,"exit 1");
                    e.printStackTrace();
                }
                //String s = GET("www.baidu.com");

            }
        }.start();



    }


    public String GET(String url) {
        HttpURLConnection Conn;
        try {
            URL myURL = new URL(url);
            Conn = (HttpURLConnection) myURL.openConnection();
            Conn.setDoInput(true);
            Conn.setRequestMethod("GET");
            // PrintWriter out = new PrintWriter(new
            // OutputStreamWriter(Conn.getOutputStream(),"utf-8"));
            Conn.connect();
            InputStream inputStream;
            inputStream = Conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String L = br.readLine();
            String respone = "";
            while (L != null) {
                respone = respone + L;
                L = br.readLine();
            }

            return respone;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

}
