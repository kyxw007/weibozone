package listview02;

/**
 * Created by Administrator on 13-7-3.
 */

import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewtest.R;

public class MapListImageAndTextListAdapter extends ArrayAdapter<MapListImageAndText> {

    private ListView listView;
    private AsyncImageLoader asyncImageLoader;

    public MapListImageAndTextListAdapter(Activity activity, List<MapListImageAndText> imageAndTexts, ListView listView) {
        super(activity, 0, imageAndTexts);
        this.listView = listView;
        asyncImageLoader = new AsyncImageLoader();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();

        // Inflate the views from XML
        View rowView = convertView;
        MapListViewCache viewCache;
        if (rowView == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.unit02, null);
            viewCache = new MapListViewCache(rowView);
            rowView.setTag(viewCache);
        } else {
            viewCache = (MapListViewCache) rowView.getTag();
        }
        MapListImageAndText imageAndText = getItem(position);

        // Load the image and set it on the ImageView
        String imageUrl = imageAndText.getImageUrl();


        ImageView imageView = viewCache.getImageView();
        imageView.setTag(imageUrl);
        Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl, new AsyncImageLoader.ImageCallback()
        {


            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(imageUrl);
                if (imageViewByTag != null) {

                  imageViewByTag.setImageDrawable(imageDrawable);
                }
            }
        });
        if (cachedImage == null) {
            imageView.setImageResource(R.drawable.reflash);
        } else {
            imageView.setImageDrawable(cachedImage);
        }
        // Set the text on the TextView
        TextView shopname = viewCache.getShopname();
        shopname.setText(imageAndText.getShopname());

        TextView activitynifo = viewCache.getActivitynifo();
        activitynifo.setText(imageAndText.getActivitynifo());

        TextView address = viewCache.getAddress();
        address.setText(imageAndText.getAddress());

        TextView telephone = viewCache.getTelephone();
        telephone.setText(imageAndText.getTelephone());

        TextView distance = viewCache.getDistance();
        distance.setText(imageAndText.getDistance());

        return rowView;
    }

}
