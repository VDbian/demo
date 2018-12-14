package cn.com.ssii.college.model.api;

import java.util.Map;

import cn.com.ssii.college.model.entity.LoginEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public interface RetrofitInterface {

    //用户登录
    @POST("auth/mobile")
    Observable<LoginEntity> login(@Body Map map);

}
