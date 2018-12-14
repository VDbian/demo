package cn.com.ssii.core.util;

import com.google.gson.Gson;

import java.io.IOException;

import cn.com.ssii.core.base.model.entity.BaseEntity;
import retrofit2.HttpException;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class HttpErrorUtil {

    public static String getErrorMsg(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            try {
                String response = error.response().errorBody().string();
                if (response.equals("")) {
                    return "服务器错误";
                }
                BaseEntity baseEntity = new Gson().fromJson(response,
                        BaseEntity.class);
                MLog.d("error", " result " + baseEntity.getMessage());
                return baseEntity.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                return "服务器错误";
            }
        }
        return "服务器错误";
    }

    public static boolean isHttpException(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return true;
        }
        return false;
    }
}
