package cn.com.ssii.college.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import cn.com.ssii.college.R;
import cn.com.ssii.college.databinding.ActivityLaunchBinding;
import cn.com.ssii.college.di.launch.DaggerLaunchComponent;
import cn.com.ssii.college.di.launch.LaunchModule;
import cn.com.ssii.college.viewmodel.LaunchViewModel;
import cn.com.ssii.core.base.BaseActivity;
import cn.com.ssii.core.base.di.AppComponent;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/20.
 */
public class LaunchActivity extends BaseActivity {

    private ActivityLaunchBinding launchBinding;

    @Inject
    LaunchViewModel viewModel;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLaunchComponent.builder()
                .appComponent(appComponent)
                .launchModule(new LaunchModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        launchBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
        initView();
    }

    private void initView(){

    }
}
