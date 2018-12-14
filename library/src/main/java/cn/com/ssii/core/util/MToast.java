package cn.com.ssii.core.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class MToast {
    private static Toast mToast = null;

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
