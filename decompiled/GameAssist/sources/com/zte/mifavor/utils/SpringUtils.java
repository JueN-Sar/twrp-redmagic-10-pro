package com.zte.mifavor.utils;

import android.util.Log;
import com.zte.mifavor.utils.overscroll.IOverScrollDecor;

/* loaded from: classes2.dex */
public class SpringUtils {
    public static boolean a(IOverScrollDecor iOverScrollDecor) {
        if (iOverScrollDecor != null) {
            return iOverScrollDecor.getIsBeingDragged();
        }
        return false;
    }

    public static boolean b(SpringAnimationCommon springAnimationCommon) {
        if (!d(springAnimationCommon)) {
            Log.d("SpringUtils", "isCollapsed don't support sink. return true.");
            return true;
        }
        boolean t = springAnimationCommon != null ? springAnimationCommon.t() : false;
        Log.d("SpringUtils", "isCollapsed out. isCollapsed = " + t);
        return t;
    }

    public static boolean c(SpringAnimationCommon springAnimationCommon) {
        boolean v = springAnimationCommon != null ? springAnimationCommon.v() : false;
        Log.d("SpringUtils", "isDisableSink out. isSupport = " + v);
        return v;
    }

    public static boolean d(SpringAnimationCommon springAnimationCommon) {
        boolean w = springAnimationCommon != null ? springAnimationCommon.w() : false;
        Log.d("SpringUtils", "isSupprtSink out. isSupport = " + w);
        return w;
    }

    public static void e(SpringAnimationCommon springAnimationCommon, boolean z) {
        if (springAnimationCommon != null) {
            springAnimationCommon.B(z);
        }
        Log.d("SpringUtils", "setUseSpring mIsUseSpring = " + z);
    }
}
