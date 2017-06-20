package jeken.com.jnews.net;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import jeken.com.jnews.data.NewsItem;

/**
 * Created by Administrator on 2017-05-20.
 */

public class JnewsHandle implements NewsJsonInterface {

    private String TAG = getClass().getSimpleName();
    private int lastindex;
    private NetBitmapGet netBitmapGet;
    private List<NewsItem> data;
    private String URL = "http://jekenpwn.cn:8989/NewsPj/jnews?";
    private String clazz;

    private int len;
    public JnewsHandle(Context mContent,String clazz,List<NewsItem> data){
        netBitmapGet = new NetBitmapGet(mContent);
        this.clazz = clazz;
        this.data = data;
    }
    public boolean fistResust(int len){
        this.len = len;
        return fistResust();
    }
    @Override
    public boolean fistResust() {
        String url = URL+"clazz="+clazz+"&len="+len+"&lastindex=0";
        httpGet(url,false);
        return true;
    }

    public boolean updateRequest(int len){
        this.len = len;
        return updateRequest("noyiyuan");
    }
    @Override
    public boolean updateRequest(String maxResult) {
        String url = URL+"clazz="+clazz+"&len="+len+"&lastindex="+lastindex;
        httpGet(url,true);
        return true;
    }

    private void httpGet(String url, final boolean isUpdate){
        String result = urlHttpGet(url);
        if (result != null){
            if (result!=null) {
                JSONObject jobj = JSON.parseObject(result);
                lastindex = jobj.getInteger("lastindex");
                JSONArray jarry = jobj.getJSONArray("lsnews");
                Log.e(TAG,lastindex+"");
                for (int i = 0;i<jarry.size();i++) {
                    JSONObject jitem_obj = jarry.getJSONObject(i);
                    NewsItem item = new NewsItem();
                    item.title = jitem_obj.getString("title");
                    item.artile_url = jitem_obj.getString("url");
                    item.datefrom = jitem_obj.getString("date");
                    JSONArray array = jitem_obj.getJSONArray("jpg");
                    if (array.size() > 0)
                        for (int j = 0;j < array.size();j++){
                            item.jpg.add(array.getString(j));
                            netBitmapGet.bindBitmap((String) array.get(j),item);
                            Log.d(TAG,(String) array.get(j));
                        }
                    if (isUpdate){
                        if (!data.contains(item))//有了没必要加载
                            data.add(0,item);
                    }else {
                        data.add(item);
                    }
                }
            }
        }
    }
    private String urlHttpGet(String url){
        HttpURLConnection conn  = null;
        try {
            URL url_ = new URL(url);
            conn = (HttpURLConnection) url_.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(7000);
            conn.setConnectTimeout(9000);
            //连接
            conn.connect();
            //响应码200才正常
            if (conn.getResponseCode()==200){
                //得到InputStream, 并读取成String
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while((len=is.read(buffer))!=-1) {
                    baos.write(buffer, 0, len);
                }
                String res =  baos.toString("GBK");
                baos.close();
                is.close();
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
