package jeken.com.jnews.data;

import java.util.ArrayList;
import java.util.List;

public class News {
	private String url;
	private List<String> jpg;
	private String title;
	private String date;
	private long join_time;

	public News(){
		jpg = new ArrayList<String>();
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getJpg() {
		return jpg;
	}

	public void setJpg(List<String> jpg) {
		this.jpg = jpg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getJoin_time() {
		return join_time;
	}

	public void setJoin_time(long join_time) {
		this.join_time = join_time;
	}

}
