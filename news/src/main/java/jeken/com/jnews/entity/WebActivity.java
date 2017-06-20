package jeken.com.jnews.entity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

import jeken.com.jnews.R;
import jeken.com.jnews.base.BaseActivity;
import jeken.com.jnews.data.ActPostData;

/**
  * Created by Administrator on 2017-04-30.
 */

public class WebActivity extends BaseActivity implements ActFragmentInterCallback{
    private String TAG = getClass().getSimpleName();
    @ViewInject(R.id.web_wv)
    private WebView web_wv;

    private String web_url;
    private List<ActPostData> data;
    @Override
    public void init(Bundle savedInstanceState) {

        data = getIntent().getParcelableArrayListExtra("DATA");
        web_url = getIntent().getStringExtra("URL");

        WebSettings settings = web_wv.getSettings();
        settings.setUseWideViewPort(false);//图片自适应
        settings.setLoadsImagesAutomatically(true);//自动加载图片
        settings.setJavaScriptEnabled(true);

        web_wv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("WEB",web_url);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            String web = savedInstanceState.getString("WEB");
            if (web!=null&&!web.equals("")){
                web_url = web;
            }
        }
    }

    @Override
    protected void onResume() {
        if (web_url != null)
            web_wv.loadUrl(web_url);
        else
            Log.e(TAG,"web_url==null");
        super.onResume();
    }

    @Override
    public void initdata(Bundle savedInstanceState) {

    }

    @Override
    public Object getContenView() {
        return R.layout.activity_web;
    }

    @Override
    public void showNewWeb(String url) {
        if (web_wv!=null){
            if (url != null){
                web_wv.loadUrl(url);
                web_url = url;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(web_wv!=null)
           web_wv.destroy();
        super.onDestroy();
    }

    @Override
    public List<ActPostData> getData() {
        return data;
    }
}
