package jeken.com.jnews.net;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;

import java.util.List;

import jeken.com.jnews.data.NewsItem;

/**
 * Created by Administrator on 2017-04-24.
 */

public class HttpHandle implements NewsJsonInterface{
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<NewsItem> data;
    private ShowApiRequest showApiRequest;
    private NetBitmapGet netBitmapGet;
    private String url;
    private final String secret;
    private final String appid;
    private String channelId;//频道ID
    private String name;//频道名
    private String page = "1";//页数
    private String needContent = "0";//是否需要内容，默认不需要
    private String needHtml = "0";//是否需要html，默认不要
    private String needAllList = "0";//是否要全部列表，默认不要
    private String maxResult = "20";//最大返回结果数

    public HttpHandle(Context mContext,String url,String appid,String secret,Config paras,List<NewsItem> data){
        this.mContext = mContext;
        this.url = url;
        this.appid = appid;
        this.secret = secret;
        this.data = data;

        this.channelId = paras.channelId;
        this.name = paras.name;
        this.page = paras.page;
        this.needContent = paras.needContent;
        this.needAllList = paras.needAllList;
        this.needHtml = paras.needHtml;
        this.maxResult = paras.maxResult;

        netBitmapGet = new NetBitmapGet(mContext);
    }

    public void buildReuest(){
        if (showApiRequest == null){
            showApiRequest = new ShowApiRequest(url,appid,secret);
        }
        showApiRequest.addTextPara("channelId",channelId)
        .addTextPara("name",name)
        .addTextPara("page",page)
        .addTextPara("needContent",needContent)
        .addTextPara("needHtml",needHtml)
        .addTextPara("needAllList",needAllList)
        .addTextPara("maxResult",maxResult);
    }
    public String request(){
        if (showApiRequest == null) return null;
        return showApiRequest.post();
    }
    @Override
    public boolean fistResust(){
        try {
            buildReuest();
            final String res = request();
            JSONObject jobj = JSON.parseObject(res);
            if (jobj.getInteger("showapi_res_code") != 0)
                return false;
            JSONArray jarry = jobj.getJSONObject("showapi_res_body")
                    .getJSONObject("pagebean").getJSONArray("contentlist");
            //Log.e("TAG",""+jarry.size());
            for (int i = 0;i<jarry.size();i++){
                JSONObject jitem_obj = jarry.getJSONObject(i);
                NewsItem item = new NewsItem();
                item.title = jitem_obj.getString("title");
                item.artile_url = jitem_obj.getString("link");
                //Log.e("TAG",item.title);
                JSONArray array = jitem_obj.getJSONArray("imageurls");
                if (array.size() > 0)
                    //Log.e("TAG",array.size()+"--"+array.getJSONObject(0).get("url"));
                    for (int j = 0;j < array.size();j++){
                        netBitmapGet.bindBitmap((String) array.getJSONObject(j).get("url"),item);
                    }
                data.add(item);
            }
        }catch (Exception e){
            Log.e(TAG,"httppost error");
        }

        return true;
    }
    @Override
    public boolean updateRequest(String maxResult){
        try {
            showApiRequest.addTextPara("maxResult",maxResult);
            final String res = request();
            JSONObject jobj = JSON.parseObject(res);
            if (jobj.getInteger("showapi_res_code") != 0)
                return false;
            JSONArray jarry = jobj.getJSONObject("showapi_res_body")
                    .getJSONObject("pagebean").getJSONArray("contentlist");
            //Log.e("TAG",""+jarry.size());
            for (int i = 0;i<jarry.size();i++){
                JSONObject jitem_obj = jarry.getJSONObject(i);
                NewsItem item = new NewsItem();
                item.title = jitem_obj.getString("title");
                item.artile_url = jitem_obj.getString("link");
                //Log.e("TAG",item.title);
                JSONArray array = jitem_obj.getJSONArray("imageurls");
                if (array.size() > 0)
                    //Log.e("TAG",array.size()+"--"+array.getJSONObject(0).get("url"));
                    for (int j = 0;j < array.size();j++){
                        netBitmapGet.bindBitmap((String) array.getJSONObject(j).get("url"),item);
                    }
                if (!data.contains(item))//有了没必要加载
                    data.add(0,item);
            }
        }catch (Exception e){
            Log.e(TAG,"httppost error");
        }
        return true;
    }
}
