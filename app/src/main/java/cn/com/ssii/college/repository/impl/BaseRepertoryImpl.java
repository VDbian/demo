package cn.com.ssii.college.repository.impl;

import java.util.List;

import cn.com.ssii.college.model.api.ApiSource;
import cn.com.ssii.college.model.api.PreferenceSource;
import cn.com.ssii.college.repository.BaseRepertory;
import cn.com.ssii.core.base.model.api.BaseApiSource;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */

public class BaseRepertoryImpl implements BaseRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public BaseRepertoryImpl(BaseApiSource apiSource, PreferenceSource preferenceSource) {
        this.apiSource = (ApiSource) apiSource;
        this.preferenceSource = preferenceSource;
    }

    public BaseRepertoryImpl(BaseApiSource apiSource) {
        this.apiSource = (ApiSource) apiSource;
    }

//    @Override
//    public Observable<UserInfoEntity> getUserInfo() {
//        return apiSource.getUserInfo().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
