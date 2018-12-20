package cn.com.ssii.college.repository.impl;

import cn.com.ssii.college.model.api.ApiSource;
import cn.com.ssii.college.model.api.PreferenceSource;
import cn.com.ssii.college.repository.BaseRepertory;
import cn.com.ssii.college.repository.LaunchRepository;
import cn.com.ssii.core.base.model.api.BaseApiSource;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */

public class LaunchRepertoryImpl extends BaseRepertoryImpl implements LaunchRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;


    public LaunchRepertoryImpl(BaseApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
    }

    public LaunchRepertoryImpl(BaseApiSource apiSource) {
        super(apiSource);
    }
}
