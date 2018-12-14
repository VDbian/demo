package cn.com.ssii.college.model.api;

import java.util.List;

import cn.com.ssii.college.model.entity.LoginEntity;
import cn.com.ssii.core.base.model.api.BaseApiSource;
import io.reactivex.Observable;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public interface ApiSource extends BaseApiSource {

    Observable<LoginEntity> login(String username, String pwd);

}