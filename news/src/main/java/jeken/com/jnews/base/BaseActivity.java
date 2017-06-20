package jeken.com.jnews.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.xutils.x;

/**
 * Created by Administrator on 2017-04-18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContenView() != null){
            if (getContenView() instanceof View){
                setContentView((View)getContenView());
            }else if (getContenView() instanceof Integer){
                setContentView((Integer) getContenView());
            }
        }else{
            View view = new View(this);
            setContentView(view);
        }
        x.view().inject(this);
        init(savedInstanceState);
        initdata(savedInstanceState);
    }
    /**
     * activity初始化
     */
    public abstract void init(Bundle savedInstanceState);
    /**
     * activity数据初始化
     */
    public abstract void initdata(Bundle savedInstanceState);
    /**
     *
     * @return Object 返回视图布局资源对象
     */
    public abstract Object getContenView();
}
