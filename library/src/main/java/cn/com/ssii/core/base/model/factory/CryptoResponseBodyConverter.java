package cn.com.ssii.core.base.model.factory;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import cn.com.ssii.core.util.MLog;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class CryptoResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<T> adapter;

    CryptoResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();
        MLog.d("api", "response value : " + result);
        return adapter.fromJson(result);
    }
}
