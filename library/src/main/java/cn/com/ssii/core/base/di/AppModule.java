package cn.com.ssii.core.base.di;

import android.app.Application;

import javax.inject.Singleton;

import cn.com.ssii.core.base.model.preference.BasePreferenceSource;
import cn.com.ssii.core.favor.FavorAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
@Module
public class AppModule {

    private Application application;
    private Class<?> preferenceSourceClass;

    public <T> AppModule(Application application, Class<T> preferenceSourceClass) {
        this.application = application;
        this.preferenceSourceClass = preferenceSourceClass;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    BasePreferenceSource providePreferenceSource(Application application) {
        return (BasePreferenceSource) new FavorAdapter.Builder(application.getApplicationContext()).build().create(preferenceSourceClass);
    }
}
