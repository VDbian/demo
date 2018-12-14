package cn.com.ssii.core;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


import cn.com.ssii.core.base.di.AppComponent;
import cn.com.ssii.core.base.di.AppModule;
import cn.com.ssii.core.base.di.DaggerAppComponent;
import cn.com.ssii.core.base.di.NetworkModule;
import cn.com.ssii.core.base.model.api.BaseApiPhotoImpl;
import cn.com.ssii.core.base.model.api.BaseApiSourceImpl;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public abstract class BaseAppApplication extends Application {
    private AppComponent appComponent;

    abstract protected BaseApiSourceImpl getApiSoure();

//    abstract protected BaseApiPhotoImpl getApiPhoto();

    abstract protected String getBaseUrl();

    abstract protected <T> Class<T> getPreferenceSourceClass();

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //SugarContext.terminate();  //orm数据库结束
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, getPreferenceSourceClass()))
                .networkModule(new NetworkModule(getApiSoure(), getBaseUrl()))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static BaseAppApplication get(Context context) {
        return (BaseAppApplication) context.getApplicationContext();
    }
}
