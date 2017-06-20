package jeken.com.jnews.net;

/**
 * Created by Administrator on 2017-04-26.
 */

public class Config{
    public Config(String channelId,String name){
        this.channelId = channelId;
        this.name = name;
    }
    public String channelId;//频道ID
    public String name;//频道名
    public String page = "1";//页数
    public String needContent = "0";//是否需要内容，默认不需要
    public String needHtml = "0";//是否需要html，默认不要
    public String needAllList = "0";//是否要全部列表，默认不要
    public String maxResult = "20";//最大返回结果数
}
