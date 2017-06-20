package jeken.com.jnews.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jeken.com.jnews.R;
import jeken.com.jnews.data.NewsItem;


/**
 * Created by Administrator on 2017-04-23.
 */

public class NewsItemAdapter extends JBaseAdapter<NewsItem> {
    public List<NewsItem> list;
    public NewsItemAdapter(List<NewsItem> list, Context mContext) {
        super(list, mContext);
        this.list = list;
    }
    @Override
    public void initViewItem(int position, View convertView, boolean isNew) {
        ViewHolder viewHolder;
        if (isNew){
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.icon1 = (ImageView) convertView.findViewById(R.id.item_icon1);
            viewHolder.icon2 = (ImageView) convertView.findViewById(R.id.item_icon2);
            viewHolder.icon3 = (ImageView) convertView.findViewById(R.id.item_icon3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NewsItem newsItem = list.get(position);
        viewHolder.title.setText(newsItem.title);
        //Log.e("TAG",newsItem.title);
        //net bind img to iv by xutil3
        if (newsItem.imageurls.size() >= 3){
//            x.image().bind(viewHolder.icon1,newsItem.imageurls.get(0));
//            x.image().bind(viewHolder.icon2,newsItem.imageurls.get(1));
//            x.image().bind(viewHolder.icon3,newsItem.imageurls.get(2));
            viewHolder.icon1.setImageBitmap(newsItem.imageurls.get(0));
            viewHolder.icon2.setImageBitmap(newsItem.imageurls.get(1));
            viewHolder.icon3.setImageBitmap(newsItem.imageurls.get(2));
        }else if (newsItem.imageurls.size() == 2){
//            x.image().bind(viewHolder.icon1,newsItem.imageurls.get(0));
//            x.image().bind(viewHolder.icon2,newsItem.imageurls.get(1));
            viewHolder.icon1.setImageBitmap(newsItem.imageurls.get(0));
            viewHolder.icon2.setImageBitmap(newsItem.imageurls.get(1));
        }else if (newsItem.imageurls.size() == 1){
//            x.image().bind(viewHolder.icon1,newsItem.imageurls.get(0));
            viewHolder.icon1.setImageBitmap(newsItem.imageurls.get(0));
        }
    }
    @Override
    public Integer getAdapterContentView() {
        return R.layout.item_news_content1;
    }
    static class ViewHolder{
        TextView title;
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;
    }
}
