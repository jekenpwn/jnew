package jeken.com.jnews.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jeken.com.jnews.R;
import jeken.com.jnews.data.NewsClasses;


/**
 * Created by Administrator on 2017-04-20.
 */

public class GVAdapter extends JBaseAdapter<NewsClasses>{

    private List<NewsClasses> list;
    public GVAdapter(List<NewsClasses> list, Context mContext) {
        super(list, mContext);
        this.list = list;
    }

    @Override
    public void initViewItem(int position, View convertView, boolean isNew) {
        ViewHolder viewHolder;
        if (isNew){
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.gv_item_title);
            viewHolder.ico = (ImageView) convertView.findViewById(R.id.gv_item_icon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(list.get(position).title);
        viewHolder.ico.setImageResource(list.get(position).icon_res);

    }

    @Override
    public Integer getAdapterContentView() {
        return R.layout.item_gv;
    }
    static class ViewHolder{
        TextView title;
        ImageView ico;
    }
}

