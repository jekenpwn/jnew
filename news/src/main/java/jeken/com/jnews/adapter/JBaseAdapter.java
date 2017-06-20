package jeken.com.jnews.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by Administrator on 2017-04-19.
 */

public abstract class JBaseAdapter<T> extends BaseAdapter {
    private List<T> list;
    private Context mContext;
    public JBaseAdapter(List<T> list, Context mContext){
         this.list = list;
         this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean isNew = false;
        if (convertView == null){
            if (getAdapterContentView() != null){
                convertView = View.inflate(mContext,getAdapterContentView(),null);
                isNew = true;
            }else {
                return null;
            }
        }
        initViewItem(position,convertView,isNew);
        return convertView;
    }

    /**
     * 初始化每个Item的View
     */
    public abstract void initViewItem(int position,View convertView,boolean isNew);

    /**
     * 设置对应Item布局的资源
     * @return
     */
    public abstract Integer getAdapterContentView();

}
