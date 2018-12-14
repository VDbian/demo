package cn.com.ssii.core.base.model.api;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class BaseApiPhotoImpl implements BaseApiPhoto {

    private Retrofit retrofit;

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
