package cn.com.ssii.core.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */

public class CheckUtils {

    /**
     * 判断是否符合手机号规则
     * @param phone 手机号
     * @return
     */
    public static boolean isMobileNum(String phone) {

        if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
            Pattern p = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
            Matcher m = p.matcher(phone);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断密码长度是否大于6
     * @param password 密码
     * @return
     */
    public static boolean checkPassword(String password) {
        if (!TextUtils.isEmpty(password) && password.length() >= 6) {
            return true;
        }
        return false;
    }

    /**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
}
