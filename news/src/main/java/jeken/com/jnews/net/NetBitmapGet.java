package jeken.com.jnews.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import jeken.com.jnews.R;
import jeken.com.jnews.data.NewsItem;

/**
 * Created by Administrator on 2017-04-24.
 */

public class NetBitmapGet {
    private static RequestQueue mQueue;
    private Context mContext;
    public NetBitmapGet(Context mContext){
        this.mContext = mContext;
        mQueue = Volley.newRequestQueue(mContext);
    }

    public void  bindBitmap(String url, final NewsItem newsItem){
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                newsItem.imageurls.add(bitmap);
            }
        }, 250, 250, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.nullpotp);
                newsItem.imageurls.add(bitmap);
            }
        });
        mQueue.add(imageRequest);
    }
}
