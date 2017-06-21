package jeken.com.jnews.entity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import jeken.com.jnews.R;
import jeken.com.jnews.adapter.NewsItemAdapter;
import jeken.com.jnews.base.BaseFragment;
import jeken.com.jnews.data.ActPostData;
import jeken.com.jnews.data.News;
import jeken.com.jnews.data.NewsItem;
import jeken.com.jnews.data.NewsTool;
import jeken.com.jnews.net.ClickedAdd;
import jeken.com.jnews.net.JnewsHandle;
import jeken.com.jnews.view.AutoListView;


/**
 * Created by Administrator on 2017-04-22.
 */

public class NewsHappFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private final int UPDATA_LIST = 1;
//    private final String url = "http://route.showapi.com/109-35";
//    private final String channeId = "5572a108b3cdc86cf39001d5";
//    private final String name = "娱乐焦点";
//    private Config mConfig;

    private List<NewsItem> data;
    private List<ActPostData> postDatas;
    private NewsItemAdapter adapter;
//    private HttpHandle httpHandle;

    private JnewsHandle jnewsHandle;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATA_LIST){//更新操作
                if (adapter != null){
                    adapter.notifyDataSetChanged();
                }
                if (autoListView != null){
                    autoListView.onRefreshComplete();
                }
            }
        }
    };

    private AutoListView autoListView;
    @Override
    public void init(View view) {
        autoListView = (AutoListView) view.findViewById(R.id.fg_lv);
        data = new ArrayList<NewsItem>();
        adapter = new NewsItemAdapter(data,mContext);
        if (autoListView != null){
            autoListView.setAdapter(adapter);
            autoListView.setOnRefreshListener(new AutoListView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    netUpdateTask();
                }
            });
            autoListView.setOnLoadListener(new AutoListView.OnLoadListener() {
                @Override
                public void onLoad() {

                }
            });
            autoListView.setOnItemClickListener(this);
        }
        if (data.size() > 6){
            if (adapter != null)
                adapter.notifyDataSetChanged();
        }else {
            netTask();
        }


    }

    private void netTask(){
//        mConfig =  new Config(channeId,name);
//        httpHandle = new HttpHandle(mContext,url, NewsAPIHelper.getAppid(),
//                NewsAPIHelper.getSecret(),mConfig,data);
//        httpHandle.buildReuest();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (httpHandle.fistResust())
//                //提醒更新,先延时1s后再更新
//                mHandler.sendEmptyMessageDelayed(UPDATA_LIST,1000);
//            }
//        }).start();
        jnewsHandle = new JnewsHandle(getContext(),"clickedcount",data);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //if (httpHandle.fistResust())
                if (jnewsHandle.fistResust(30))
                    //提醒更新,先延时1s后再更新
                    mHandler.sendEmptyMessageDelayed(UPDATA_LIST,1000);
            }
        }).start();
    }
    public void netUpdateTask(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (httpHandle.updateRequest("4"))//加载多4条
//                //提醒更新,先延时600ms后再更新
//                mHandler.sendEmptyMessageDelayed(UPDATA_LIST,600);
//            }
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (jnewsHandle.updateRequest(4))
                    //提醒更新,先延时600ms后再更新
                    mHandler.sendEmptyMessageDelayed(UPDATA_LIST,600);
            }
        }).start();
    }
    @Override
    public Integer getFragmentRes() {
        return R.layout.fragment_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = NewsTool.NewsItem_To_News(data.get(position-1));
        if (news!=null){
            //ClickedAdd.getInstance().init();
            ClickedAdd.getInstance().add(news);
        }
        String artile_url = data.get(position-1).artile_url;
        if (postDatas==null){
            postDatas = new ArrayList<ActPostData>();
            for (int i = 0;i < data.size();i++){
                postDatas.add(new ActPostData(data.get(i).title,data.get(i).artile_url));
            }
        }else if (data.size() > postDatas.size()){
            int len = data.size()-postDatas.size();
            while ((len--)>=0){
                postDatas.add(0,new ActPostData(data.get(len).title,data.get(len).artile_url));
            }
        }
        if (artile_url != null && postDatas != null){
            Intent intent = new Intent(getContext(),WebActivity.class);
            intent.putExtra("URL",artile_url);
            intent.putParcelableArrayListExtra("DATA", (ArrayList<? extends Parcelable>) postDatas);
            startActivity(intent);
        }
    }
}
