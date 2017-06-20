package jeken.com.jnews.entity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import jeken.com.jnews.base.BaseListFragment;
import jeken.com.jnews.data.ActPostData;


/**
 * Created by Administrator on 2017-04-21.
 */

public class NewsListFrament extends BaseListFragment implements AdapterView.OnItemClickListener {

    private String TAG = getClass().getSimpleName();
    private int CHECK = 1;
    private ActFragmentInterCallback mCallback;
    private List<ActPostData> data;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CHECK){
                checkData();
            }
        }
    };
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity != null){
            mCallback = (ActFragmentInterCallback) activity;
        }
    }

    @Override
    public void init() {
        checkData();
    }

    private void checkData(){
        data = mCallback.getData();
        if (data != null){
            String[] titles = new String[data.size()];
            for (int i = 0;i < data.size();i++){
                titles[i] = data.get(i).title;
            }
            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,
                    titles);
            setListAdapter(adapter);
        }else {
            Log.e(TAG,"data==null");
            mHandler.sendEmptyMessageDelayed(1,500);
        }
    }
    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
        if (mCallback != null){
            mCallback.showNewWeb(data.get(position).artile_url);
        }

    }
    @Override
    public Integer getFragmentRes() {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCallback != null){
            mCallback.showNewWeb("jeken");
        }
    }
}
