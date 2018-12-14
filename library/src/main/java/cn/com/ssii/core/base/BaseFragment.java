package cn.com.ssii.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import cn.com.ssii.core.BaseAppApplication;
import cn.com.ssii.core.base.di.AppComponent;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public abstract class BaseFragment extends Fragment{
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent(BaseAppApplication.get(getActivity()).getAppComponent());
    }
}
