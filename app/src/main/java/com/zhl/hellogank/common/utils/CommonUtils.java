package com.zhl.hellogank.common.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created by zhouhl on 2016/11/1.
 * CommonUtils
 */

public class CommonUtils {

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    public static void copy( Context context, String content) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}
