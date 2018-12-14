package cn.com.ssii.core.favor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p/>
 * Created by kai on 25/09/15.
 */
public class FavorAdapter {

    private static final String TAG = "Favor";
    private final SharedPreferences sp;

    private final Map<Class<?>, Map<Method, MethodInfo>> serviceMethodInfoCache =
            new LinkedHashMap<>();
    private String prefix;
    private boolean log;
    private boolean allFavor;


    private FavorAdapter(SharedPreferences sp) {
        this.sp = sp;
    }

    private static MethodInfo getMethodInfo(Map<Method, MethodInfo> cache, Method method, SharedPreferences sp, String prefix, boolean allFavor) {
        synchronized (cache) {
            MethodInfo methodInfo = cache.get(method);
            if (methodInfo == null) {
                methodInfo = new MethodInfo(method, sp, prefix, allFavor);
                cache.put(method, methodInfo);
            }
            return methodInfo;
        }
    }

    private static <T> void validateServiceClass(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("Only interface definitions are supported.");
        }
//        if (service.getInterfaces().length > 0) {
//            throw new IllegalArgumentException("Interface definitions must not extend other interfaces.");
//        }
    }

    /**
     * Create an implementation of the API defined by the specified {@code service} interface.
     */
    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> service) {
        validateServiceClass(service);
        for (Annotation annotation : service.getDeclaredAnnotations()) {
            if (annotation.annotationType() == AllFavor.class)
                allFavor = true;
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new FavorHandler(getMethodInfoCache(service)));
    }

    void enableLog(boolean enable) {
        this.log = enable;
    }

    private Map<Method, MethodInfo> getMethodInfoCache(Class<?> service) {
        synchronized (serviceMethodInfoCache) {
            Map<Method, MethodInfo> methodInfoCache = serviceMethodInfoCache.get(service);
            if (methodInfoCache == null) {
                methodInfoCache = new LinkedHashMap<>();
                serviceMethodInfoCache.put(service, methodInfoCache);
            }
            return methodInfoCache;
        }
    }

    public static class Builder {

        private String prefix;
        private SharedPreferences sp;

        public Builder(Context context) {
            sp = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }

        public Builder(SharedPreferences sp) {
            this.sp = sp;
        }

        public Builder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public FavorAdapter build() {
            FavorAdapter adapter = new FavorAdapter(sp);
            adapter.prefix = prefix;
            return adapter;
        }
    }

    private class FavorHandler implements InvocationHandler {
        private final Map<Method, MethodInfo> methodDetailsCache;

        FavorHandler(Map<Method, MethodInfo> methodDetailsCache) {
            this.methodDetailsCache = methodDetailsCache;
        }

        @SuppressWarnings("unchecked") //
        @Override
        public Object invoke(Object proxy, Method method, final Object[] args)
                throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }

            final MethodInfo methodInfo = getMethodInfo(methodDetailsCache, method, sp, prefix, allFavor);
            methodInfo.init();

            if (log) {
                Log.d(TAG, methodInfo.toString());
            }

            switch (methodInfo.responseType) {
                case VOID:
                    return methodInfo.set(args);
                case OBJECT:
                    return methodInfo.get();
                case OBSERVABLE:
                    return methodInfo.rxPref;
            }
            return null;
        }
    }

}
