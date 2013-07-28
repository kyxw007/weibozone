package kyxw007;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class qzone {
    public String sid;
    public String UID;
    public String psw;
    public String index;
    private String TAG = "Qzone";

    public qzone(String UID, String psw) {
        this.UID = UID;
        this.psw = psw;
        String s = "<html><body>wowowowo</body></html>";
        try {
            Document doc = Jsoup.parse(s);
            Log.d(TAG, doc.toString());

        } catch (Throwable e) {
            Log.d(TAG,"Throw@@@@");
        }

    }

    public void inintQzone() {
        /* loging */
        String s = login();
        index = s;
        // get the current module from the index
        Log.d(TAG, "nomal --");
        Document doc = Jsoup.parse(s);
        String S = doc.getElementById("main-nav-host")
                .getElementsByClass("current").attr("href").toString();
        //System.out.print("\n" + S);
        int poSid = S.indexOf("sid=") + 4;
        this.sid = S.substring(poSid);
    }

    public List<current.currentLine> getCurrent(int num) {
        current cur = new current();
        cur.reflashCurr(num);
        return cur.currenList;
    }

    public String reflashIndex() {
        String url = "http://info60.z.qq.com/infocenter_v2.jsp?B_UID=" + UID
                + "&sid=" + sid;
        index = GET(url);
        return UID;

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

    public String getLoginUrl() {
        String url = "http://pt.3g.qq.com/s?aid=nLoginqz&sid=AenUUJJMMG1FPQHHKFt65K91&KqqWap_Act=3&g_ut=2&go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp%3Fsid%3DAenUUJJMMG1FPQHHKFt65K91%26KqqWap_Act%3D5%263g_style%3D1%26g_f%3D2425";
        Document doc = Jsoup.parse(GET(url));
        String S = doc.getElementById("qq_loginform").attr("action").toString();
        /* System.out.print(S); */
        return S;
    }

    public String login() {
        String data = "login_url=http://pt.3g.qq.com/s?aid=nLogin&go_url=http%3A%2F%2Fish.z.qq.com%2Ffeeds_my.jsp%3FB_UID%3D515779871%26myFeedNo%3D1%26sid%3DAdkdKEH7wD1_Onyh1yxcIb6M%26rand%3D717581&defaultQQ="
                + UID
                + "&sid="
                + "&sidtype=1&nopre=0&bid=0&go_url=http://ish.z.qq.com/feeds_my.jsp?B_UID=515779871&myFeedNo=1&sid=AdkdKEH7wD1_Onyh1yxcIb6M&rand=717581&qq="
                + UID + "&pwd=" + psw + "&loginType=3";
        String url = null;
        url = getLoginUrl();
        HttpURLConnection updateConn;
        String respone = "";
        try {
            URL updateURL = new URL(url);
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
            return respone;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.print(respone);
        return null;
    }

    /**
     * @author Administrator
     */
    public class current {
        public List<currentLine> currenList = new ArrayList<currentLine>();
        public String nextPage;
        public int CurrNum = 20;

        current() {
        }

        public void reflashCurr(int num) {
            CurrNum = num;
            String url = "http://info60.z.qq.com/infocenter_v2.jsp?B_UID="
                    + UID + "&sid=" + sid;
            //System.out.print("\n" + url);
            String html = GET(url);
            Document doc = Jsoup.parse(html);
            nextPage = doc.select("[href*=ish.z.qq.com/feeds_friends.jsp?]")
                    .attr("href").toString();
            String ccc = doc.getElementsByClass("module").first()
                    .getElementsByClass("module-content").first().toString();
            // System.out.print("\n"+ccc);
            // System.out.print("\n####" + nextPage + "\n");
            add7current(ccc, 0, 7);
            int currentNum = 0;
            while (currenList.size() < num) {
                html = GET(nextPage);
                doc = Jsoup.parse(html);
                Element bbb = doc.getElementsByClass("module").first()
                        .getElementsByClass("module-content").first();
                Elements dd = bbb.select("[href*=feeds_friends.jsp?]");

                boolean flag = true;
                int i = 0;
                while (flag) {
                    String innertext = dd.get(i).text();
                    if (innertext.contains("下页")) {
                        nextPage = dd.get(i).attr("href");
                        flag = false;
                        break;
                    }
                    ;
                    i++;
                    if (i == dd.size()) {
                        break;
                    }
                }
                if (flag) {
                    nextPage = "";
                }

                // nextPage = bbb.select("[href*=feeds_friends.jsp?]")
                // .attr("href").toString();
                // System.out.print("\n&&&&" + ee + "\n");
                // System.out.print("\n####" + nextPage + "\n");
                currentNum = currentNum + 8;
                System.out.print(((float) currentNum / (float) num * 100) + "%");
                add7current(bbb.toString(), 1, 8);

            }
            //System.out.print("\n总Current数：" + currenList.size());
        }

        public void add7current(String currenMoudle, int num1, int num2) {
            Document doc = Jsoup.parse(currenMoudle);
            Element element = doc.getElementsByClass("module-content").first();
            // System.out.print("\n" + element.toString() + "\n");
            //System.out.print(".");

            currentLine curr = null;
            for (int i = num1; i < num2 && currenList.size() < CurrNum; i++) {
                System.out.print(" 。");
                Element s = element.child(i);
                // System.out.print("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
                // System.out.print("\n"+s);
                curr = new currentLine();

                // get user name form each node
                String username = s.child(0).child(0).text();
                curr.useName = username;
                // System.out.print("\n@@用户：" + username);
                // System.out.print("\n"+curr.useName);

                // get author URl from each node
                String authorurl = s.child(0).child(0).attr("href");
                curr.authorUrl = authorurl;
                // System.out.print("\n"+authorurl);

                // get status
                String status = s.child(0).text().toString();
                int timestart = status.indexOf('[');
                // System.out.print("\n"+timestart);
                String statu = null, STRcreattime = "";
                if (timestart != -1) {
                    statu = status.substring(0, timestart);
                    STRcreattime = status.substring(timestart);
                } else {
                    statu = status;

                }
                curr.status = statu;
                curr.STRcreattime = STRcreattime;

                // System.out.print("\n@@内容："+statu+"---"+STRcreattime);

                // get ZanURL
                String zanURL = null;
                zanURL = s.select("[href*=/like/like_action.jsp]").attr("href")
                        .toString();
                curr.ZanUrl = zanURL;
                // System.out.print("\n@@赞："+zanURL);

                // get comment url
                String commentURL = null;
                commentURL = s.select("[href*=/infocenter/]").attr("href")
                        .toString();
                curr.commentURL=commentURL;
                // System.out.print("\n@@评论："+commentURL);

                // get repost url
                String repostUrl = null;
                repostUrl = s.select("[href*=/mood/mood_forward.jsp]")
                        .attr("href").toString();
                curr.repostUrl = repostUrl;
                // System.out.print("\n@@转发：" + repostUrl);

                String sss = GET(authorurl);
                Document docUidpic = Jsoup.parse(sss);
                String UidPic = docUidpic.getElementsByClass("top-info").first().select("img").first().attr("src");
                curr.UidPic = UidPic;
                //System.out.print("\n##"+UidPic);

                // writ node to the current list
                currenList.add(curr);
            }

            // System.out.print("\n"+GET(currenList.get(0).authorUrl));

        }

        public class currentLine {
            public String useName;
            public String UidPic;
            public String status;
            public String STRcreattime;
            public String ZanUrl;
            public String commentURL;
            public String authorUrl;
            public String picUrl;
            public String repostUrl;

        }

    }

}
