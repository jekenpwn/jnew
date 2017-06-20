package jeken.com.jnews.entity;

import java.util.List;

import jeken.com.jnews.data.ActPostData;

/**
 * Created by Administrator on 2017-05-01.
 */

public interface ActFragmentInterCallback {
     public void showNewWeb(String url);
     public List<ActPostData> getData();
}
