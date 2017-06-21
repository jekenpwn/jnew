package jeken.com.jnews.net;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jeken.com.jnews.data.News;

/**
 * Created by Administrator on 2017-05-21.
 */

public class ClickedAdd {
    private String TAG = getClass().getSimpleName();
    private static final String url = "http://jekenpwn.cn:8989/NewsPj/clickedcount";
    private static ClickedAdd clickedAdd = null;
    private ClickedAdd(){}
    public static ClickedAdd getInstance(){
        if(clickedAdd == null){
            synchronized (ClickedAdd.class){
                if (clickedAdd == null){
                    clickedAdd = new ClickedAdd();
                }
            }
        }
        return clickedAdd;
    }

    public void add(final News news){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    task(JSON.toJSONString(news));

                }
            }).start();

    }

    public boolean isRight(News news){
        if (news == null) return false;
        if (news.getTitle()!=null&news.getUrl()!=null){
            if (!news.getTitle().equals("")&&!news.getUrl().equals(""))
                return true;
        }
        return false;
    }

    private void task(String content){
       // Log.e(TAG,content);

        HttpURLConnection conn  = null;
        //if (conn == null) return;
        try {
            //打开URL并创建HTTP连接对象
            URL myurl = new URL(url);
            //设置参数
            conn = (HttpURLConnection) myurl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(7000);
            conn.setConnectTimeout(9000);
            conn.setDoOutput(true);
            OutputStream  os = conn.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(os);
            dataOutputStream.write(content.getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            if (conn.getResponseCode()==200) Log.e(TAG,"YES");
            else Log.e(TAG,"error="+conn.getResponseCode());
            if(os!=null)
                os.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"error");
        }finally {
            if (conn != null)
                conn.disconnect();

        }

    }
}
