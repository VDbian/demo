package cn.com.ssii.college.di.launch;

import cn.com.ssii.college.repository.LaunchRepository;
import cn.com.ssii.college.repository.impl.LaunchRepertoryImpl;
import cn.com.ssii.college.view.ui.activity.LaunchActivity;
import cn.com.ssii.college.viewmodel.LaunchViewModel;
import cn.com.ssii.core.base.di.Activity;
import cn.com.ssii.core.base.model.api.BaseApiSource;
import cn.com.ssii.core.base.model.preference.BasePreferenceSource;
import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/20.
 */
@Module
public class LaunchModule {
    private LaunchActivity launchActivity;

    public LaunchModule(LaunchActivity loginActivity) {
        this.launchActivity = loginActivity;
    }

    @Provides
    @Activity
    LaunchRepository provideLaunchRepository(BaseApiSource apiSource) {
        return new LaunchRepertoryImpl(apiSource);
    }

    @Provides
    @Activity
    LaunchViewModel provideLaunchViewModel(LaunchRepository launchRepository, BasePreferenceSource basePreferenceSource) {
        return new LaunchViewModel(launchActivity, launchRepository, basePreferenceSource);
    }
}
