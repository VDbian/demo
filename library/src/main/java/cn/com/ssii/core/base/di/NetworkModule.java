package cn.com.ssii.core.base.di;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import cn.com.ssii.core.base.model.api.BaseApiPhoto;
import cn.com.ssii.core.base.model.api.BaseApiPhotoImpl;
import cn.com.ssii.core.base.model.api.BaseApiSource;
import cn.com.ssii.core.base.model.api.BaseApiSourceImpl;
import cn.com.ssii.core.base.model.factory.CryptoConverterFactory;
import cn.com.ssii.core.base.model.preference.BasePreferenceSource;
import cn.com.ssii.core.util.MLog;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
@Module
public class NetworkModule {
    public static final String PHOTO_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjEsIm5hbWUiOjEsImNyZWF0ZSI6MTUxNTA0NTI4MjkxM30.LYqOZDsoRX_VsH9reQ4ZXNhb3lOkzWlYo0mF81kYptKm4QSfHRWbP04yxt_6ebLD91iiuiMEaYtWlaXU7enyyg";
    private static final String TAG = "api";
    private BaseApiSourceImpl baseApiSource;
    private BaseApiPhotoImpl baseApiPhoto;
    private String baseUrl;

    public NetworkModule(BaseApiSourceImpl baseApiSource, String baseUrl) {
        this.baseApiSource = baseApiSource;
        this.baseUrl = baseUrl;
    }

    public NetworkModule(BaseApiSourceImpl baseApiSource, String baseUrl,BaseApiPhotoImpl baseApiPhoto) {
        this.baseApiSource = baseApiSource;
        this.baseUrl = baseUrl;
        this.baseApiPhoto = baseApiPhoto;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final BasePreferenceSource preferenceSource) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                MLog.d(TAG, "request token " + preferenceSource.getToken());
                MLog.d(TAG, "request url " + original.url());
                if (!preferenceSource.getToken().equals("")) {
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer " + preferenceSource.getToken())
                            .build();
                    return chain.proceed(request);
                }
                return chain.proceed(original);
            }
        });
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(CryptoConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    BaseApiSource provideBaseApiSource(final Retrofit retrofit) {
        baseApiSource.setRetrofit(retrofit);
        return baseApiSource;
    }

    @Provides
    @Singleton
    BaseApiPhoto provideApiPhoto() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(CryptoConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                MLog.d(TAG, "request url " + original.url());
                                Request request = original.newBuilder()
                                        .header("RainbowKey", PHOTO_TOKEN)
                                        .build();
                                return chain.proceed(request);
                            }
                        }).build())
                .build();

        baseApiPhoto.setRetrofit(retrofit);
        return baseApiPhoto;
    }

}
