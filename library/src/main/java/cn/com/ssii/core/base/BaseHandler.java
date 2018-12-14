package cn.com.ssii.core.base;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */

public class BaseHandler<T extends BaseHandler.BaseHandlerCallBack> extends Handler {

    WeakReference<T> wr;

    public BaseHandler(T t) {
        wr = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t = wr.get();
        if (t != null) {
            t.callBack(msg);
        }
    }

    public interface BaseHandlerCallBack {
        public void callBack(Message msg);
    }
}
