package cn.com.ssii.core.util;

import android.util.Log;

import cn.com.ssii.core.BuildConfig;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */
public class MLog {
	
	public static final boolean DEBUG_LOG = BuildConfig.LOG_DEBUG;
	public static final String symbol = "------>";

	public static void v(String tag, String msg){
		if (DEBUG_LOG) {
			Log.v(tag, symbol + msg);
		}
	}
	
	public static void d(String tag, String msg){
		if (DEBUG_LOG) {
			Log.d(tag, symbol + msg);
		}
	}
	
	public static void i(String tag, String msg){
		if (DEBUG_LOG) {
			Log.i(tag, symbol + msg);
		}
	}
	
	public static void w(String tag, String msg){
		if (DEBUG_LOG) {
			Log.w(tag, symbol + msg);
		}
	}
	
	public static void e(String tag, String msg){
		if (DEBUG_LOG) {
			Log.e(tag,symbol + msg);
		}
	}
}
