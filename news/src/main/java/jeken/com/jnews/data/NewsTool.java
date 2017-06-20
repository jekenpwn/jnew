package jeken.com.jnews.data;

/**
 * Created by Administrator on 2017-05-21.
 */

public class NewsTool {

    public static News NewsItem_To_News(NewsItem item){
        if (item==null) return null;
        News news = new News();
        news.setTitle(item.title);
        news.setUrl(item.artile_url);
        news.setDate(item.datefrom);
        news.setJpg(item.jpg);
        return news;
    }
}
