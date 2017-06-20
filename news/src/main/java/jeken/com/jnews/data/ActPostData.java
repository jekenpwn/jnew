package jeken.com.jnews.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-04-29.
 */

public class ActPostData implements Parcelable {
    public String title;
    public String artile_url;

    public ActPostData(Parcel in) {
        title = in.readString();
        artile_url = in.readString();
    }

    public ActPostData(String title, String artile_url) {
        this.title = title;
        this.artile_url = artile_url;
    }

    public ActPostData(){

    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(artile_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActPostData> CREATOR = new Creator<ActPostData>() {
        @Override
        public ActPostData createFromParcel(Parcel in) {
            return new ActPostData(in);
        }

        @Override
        public ActPostData[] newArray(int size) {
            return new ActPostData[size];
        }
    };
}
