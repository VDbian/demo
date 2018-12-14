package cn.com.ssii.core.base.model.preference;

import cn.com.ssii.core.favor.AllFavor;
import cn.com.ssii.core.favor.Default;
import cn.com.ssii.core.favor.Favor;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
@AllFavor
public interface BasePreferenceSource {

    @Favor("token")
    @Default("")
    String getToken();

    @Favor("token")
    void setToken(String token);

    @Favor("name")
    @Default("")
    String getName();

    @Favor("name")
    void setName(String name);

}
