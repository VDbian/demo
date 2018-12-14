package cn.com.ssii.core.base.di;

import android.app.Application;

import javax.inject.Singleton;

import cn.com.ssii.core.base.model.api.BaseApiPhoto;
import cn.com.ssii.core.base.model.api.BaseApiSource;
import cn.com.ssii.core.base.model.preference.BasePreferenceSource;
import dagger.Component;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Application getApplication();

    BaseApiSource getBaseApiSource();

    BaseApiPhoto getBaseApiPhoto();

    BasePreferenceSource getPreferenceSource();
}
