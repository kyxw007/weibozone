package listview02;

/**
 * Created by Administrator on 13-7-3.
 */
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {
    /*图片异步加载器对像*/

    private HashMap<String, SoftReference<Drawable>> imageCache;
    /*哈希表 Ｓtring 映射 Ｄrawable*/

    public AsyncImageLoader() {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
    }

    public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            Log.d(this.getClass().getName(),"缓存读取"+imageUrl);
            if (drawable != null)
            {
                Log.d(this.getClass().getName(),"缓存图片"+imageUrl);
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                Log.d(this.getClass().getName(),"HTTP读取"+imageUrl);
                Drawable drawable = loadImageFromUrl(imageUrl);

                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Log.d(this.getClass().getName(),"写入缓存"+drawable);
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }

    public static Drawable loadImageFromUrl(String url) {
        URL m;
        InputStream i = null;
        try {
            m = new URL(url);
            i = (InputStream) m.getContent();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(i, "src");
        return d;
    }

    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl);
    }

}
