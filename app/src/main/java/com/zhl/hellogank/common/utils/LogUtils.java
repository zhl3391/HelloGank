package com.zhl.hellogank.common.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhouhl on 2016/11/1.
 * LogUtils
 */

public class LogUtils implements HttpLoggingInterceptor.Logger {

    private static final String TAG = "LogUtils";

    public static void e(String msg){
        Logger.e(msg + "");
    }

    public static void e(String tag, String msg){
        Logger.t(tag).e(msg + "");
    }

    public static void json(String tag, String json){
        Logger.t(tag).json(json + "");
    }

    public static void d(String tag, String msg){
        Logger.t(tag).d(msg + "");
    }

    public static void d(String msg){
        Logger.d(msg + "");
    }

    public static void i(String tag, String msg){
        Logger.t(tag).i(msg + "");
    }

    public static void init(boolean debug){
        if (debug){
            Logger.init(TAG).setMethodCount(0).hideThreadInfo();
        }else {
            Logger.init(TAG).setMethodCount(0).hideThreadInfo().setLogLevel(LogLevel.NONE);
        }
    }

    @Override
    public void log(String message) {
        if (message != null && !message.isEmpty()){
            if (message.startsWith("{")){
                json(TAG, message);
            }else {
                i(TAG, message);
            }
        }
    }
}
