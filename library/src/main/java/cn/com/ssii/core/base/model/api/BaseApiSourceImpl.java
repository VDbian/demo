package cn.com.ssii.core.base.model.api;

import retrofit2.Retrofit;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class BaseApiSourceImpl implements BaseApiSource {

    private Retrofit retrofit;

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
