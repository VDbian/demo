package cn.com.ssii.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import cn.com.ssii.core.BaseAppApplication;
import cn.com.ssii.core.base.di.AppComponent;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void setupActivityComponent(AppComponent appComponent);

    private static final String TAG = "BaseActivity";
    private ImmersionBar immersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(BaseAppApplication.get(this).getAppComponent());
        immersionBar = ImmersionBar.with(this)
                .keyboardEnable(false)
                .statusBarColor("#000000")
                .fitsSystemWindows(true);
        immersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

}
