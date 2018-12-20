package cn.com.ssii.college.viewmodel;

import cn.com.ssii.college.model.api.PreferenceSource;
import cn.com.ssii.college.repository.LaunchRepository;
import cn.com.ssii.college.view.ui.activity.LaunchActivity;
import cn.com.ssii.core.base.BaseViewModel;
import cn.com.ssii.core.base.model.preference.BasePreferenceSource;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/20.
 */
public class LaunchViewModel extends BaseViewModel {
    private LaunchActivity activity;
    private LaunchRepository repository;
    private PreferenceSource preferenceSource;

    public LaunchViewModel(LaunchActivity activity, LaunchRepository repository, BasePreferenceSource preferenceSource) {
        this.activity = activity;
        this.repository = repository;
        this.preferenceSource = (PreferenceSource) preferenceSource;
    }
}
