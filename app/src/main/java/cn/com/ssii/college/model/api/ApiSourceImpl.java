package cn.com.ssii.college.model.api;

import java.util.HashMap;
import java.util.Map;

import cn.com.ssii.college.model.entity.LoginEntity;
import cn.com.ssii.core.base.model.api.BaseApiSourceImpl;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class ApiSourceImpl extends BaseApiSourceImpl implements ApiSource {

    private RetrofitInterface retrofitInterface;

    @Override
    public void setRetrofit(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public Observable<LoginEntity> login(String username, String pwd) {
        Map<String, String> map = new HashMap();
        map.put("name", username);
        map.put("password", pwd);
        return retrofitInterface.login(map);
    }

}
