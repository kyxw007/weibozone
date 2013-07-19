package listview02;

/**
 * Created by kyxw007 on 13-7-3.
 * 定义类MapListImageAndText管理ListViewItem中控件的内容
 */


public class MapListImageAndText {
    private String imageUrl;
    private String shopname;
    private String activitynifo;
    private String address;
    private String telephone;
    private String distance;

    public MapListImageAndText(String imageUrl, String shopname, String activitynifo, String address, String telephone, String distance) {
        this.imageUrl = imageUrl;
        this.shopname = shopname;
        this.activitynifo = activitynifo;
        this.address = address;
        this.telephone = telephone;
        this.distance = distance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShopname() {
        return shopname;
    }

    public String getActivitynifo() {
        return activitynifo;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDistance() {
        return distance;
    }


}
