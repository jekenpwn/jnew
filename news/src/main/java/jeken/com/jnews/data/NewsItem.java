package jeken.com.jnews.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017-04-23.
 */

public class NewsItem implements Parcelable{
    public String title;
    public String main_content;
    public List<Bitmap> imageurls;
    public List<String> jpg;
    public String artile_url;
    public String datefrom;
    public NewsItem() {
        imageurls = new ArrayList<Bitmap>();
        jpg = new LinkedList<String>();
    }

    public NewsItem(String title, String main_content, List<Bitmap> imageurls, String artile_url) {
        this.title = title;
        this.main_content = main_content;
        this.imageurls = imageurls;
        this.artile_url = artile_url;
    }

    public NewsItem(Parcel in) {
        title = in.readString();
        main_content = in.readString();
        imageurls = in.createTypedArrayList(Bitmap.CREATOR);
        jpg = in.createStringArrayList();
        artile_url = in.readString();
        datefrom = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(main_content);
        dest.writeTypedList(imageurls);
        dest.writeString(artile_url);
        dest.writeStringList(jpg);
        dest.writeString(datefrom);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };
}
