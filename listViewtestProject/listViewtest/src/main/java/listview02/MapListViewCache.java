package listview02;

/**
 * Created by Administrator on 13-7-3.
 * 定义类MapListViewCache实例化ListViewItem中的控件
 *
 */


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewtest.R;

public class MapListViewCache {

    private View baseView;
    private TextView shopname;
    private TextView activitynifo;
    private TextView address;
    private TextView telephone;
    private TextView distance;

    private ImageView imageView;

    public MapListViewCache(View baseView) {
        this.baseView = baseView;
    }

    public TextView getShopname() {
        if (shopname == null) {
            shopname = (TextView) baseView.findViewById(R.id.maplistviewitemshopname);
        }
        return shopname;
    }

    public TextView getActivitynifo() {
        if (activitynifo == null) {
            activitynifo = (TextView) baseView.findViewById(R.id.maplistviewitemActi);
        }
        return activitynifo;
    }

    public TextView getAddress() {
        if (address == null) {
            address = (TextView) baseView.findViewById(R.id.maplistviewitemaddr);
        }
        return address;
    }

    public TextView getTelephone() {
        if (telephone == null) {
            telephone = (TextView) baseView.findViewById(R.id.maplistviewitemtelphone);
        }
        return telephone;
    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) baseView.findViewById(R.id.maplistviewitemImage);
        }
        return imageView;
    }

    public TextView getDistance() {
        if (distance == null) {
            distance = (TextView) baseView.findViewById(R.id.maplistviewitemtelphone);//(R.id.maplistviewitemdistance);
        }
        return distance;
    }

}
