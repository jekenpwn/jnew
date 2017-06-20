package jeken.com.jnews.entity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import jeken.com.jnews.R;
import jeken.com.jnews.adapter.GVAdapter;
import jeken.com.jnews.base.BaseActivity;
import jeken.com.jnews.data.NewsClasses;
import jeken.com.jnews.tools.StatusBarUtils;

/**
 * Created by Administrator on 2017-04-18.
 */

public class HomeActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.home_gv_news)
    private GridView home_gv_news;
    @ViewInject(R.id.home_frame)
    private FrameLayout home_frame;

    private List<NewsClasses> data;
    private GVAdapter gvAdapter;
    private final String[] newTitle = {"头条", "国际", "社会", "关注"};
    private final Integer[] newIcon = {R.drawable.newheader_unselected, R.drawable.newinfo_unselected,
            R.drawable.newsociety_unselected, R.drawable.newhappy_unselected};
    private final Integer[] selectedIcon = {R.drawable.newheader_selected, R.drawable.newinfo_selected,
            R.drawable.newsociety_selected, R.drawable.newhappy_selected};
    private int last_chooise = 0;

    @Override
    public void init(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorPrimary);
    }

    @Override
    public void initdata(Bundle savedInstanceState) {
        //新闻类别数据初始化
        data = new ArrayList<NewsClasses>();
        for (int i = 0; i < newTitle.length; i++) {
            data.add(new NewsClasses(newTitle[i], newIcon[i]));
        }
        gvAdapter = new GVAdapter(data, this);//适配器
        home_gv_news.setAdapter(gvAdapter);
        home_gv_listener();
        firstAutoClick();
    }

    /**
     * 进入activity第一次触发
     */
    private void firstAutoClick() {
        Fragment fragment = (Fragment) mFragmentPagerAdapter
                .instantiateItem(home_frame, 0);
        mFragmentPagerAdapter.setPrimaryItem(home_frame, 0, fragment);
        mFragmentPagerAdapter.finishUpdate(home_frame);
        data.get(0).icon_res = selectedIcon[0];
    }

    /**
     * 监听新闻选择
     */
    public void home_gv_listener() {
        home_gv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (last_chooise >= 0)
                    data.get(last_chooise).icon_res = newIcon[last_chooise];
                last_chooise = position;
                data.get(position).icon_res = selectedIcon[position];
                gvAdapter.notifyDataSetChanged();
                changeFragment(position);
            }
        });
    }

    private void changeFragment(int position) {

        Fragment fragment = (Fragment) mFragmentPagerAdapter
                .instantiateItem(home_frame, position);
        mFragmentPagerAdapter.setPrimaryItem(home_frame, 0, fragment);
        mFragmentPagerAdapter.finishUpdate(home_frame);
    }

    @Override
    public Object getContenView() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return new NewsInfoFragment();
                case 2:
                    return new NewsSocietyFragment();
                case 3:
                    return new NewsHappFragment();
                default:
                    return new NewsHeaderFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

}
