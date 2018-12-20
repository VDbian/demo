package cn.com.ssii.college.di.launch;

import cn.com.ssii.college.view.ui.activity.LaunchActivity;
import cn.com.ssii.core.base.di.Activity;
import cn.com.ssii.core.base.di.AppComponent;
import dagger.Component;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/20.
 */
@Activity
@Component(dependencies = AppComponent.class, modules = LaunchModule.class)
public interface LaunchComponent {
    void inject(LaunchActivity launchActivity);
}
