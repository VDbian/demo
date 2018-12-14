package cn.com.ssii.college;

import cn.com.ssii.college.model.api.ApiSourceImpl;
import cn.com.ssii.college.model.api.PreferenceSource;
import cn.com.ssii.core.BaseAppApplication;
import cn.com.ssii.core.base.model.api.BaseApiPhotoImpl;
import cn.com.ssii.core.base.model.api.BaseApiSourceImpl;


/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class AppApplication extends BaseAppApplication {

    private static final String BASE_URL =BuildConfig.API_ROOT;
    @Override
    protected BaseApiSourceImpl getApiSoure() {
        return new ApiSourceImpl();
    }

//    @Override
//    protected BaseApiPhotoImpl getApiPhoto() {
//        return new ApiPhotoImpl();
//    }

    @Override
    protected String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    protected <T> Class<T> getPreferenceSourceClass() {
        return (Class<T>) PreferenceSource.class;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
