package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Rect;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

@RestrictTo
/* loaded from: classes.dex */
public class WindowUtils {

    private static class Api14Impl {
    }

    @RequiresApi
    private static class Api17Impl {
    }

    @RequiresApi
    private static class Api30Impl {
        static Rect a(WindowManager windowManager) {
            return windowManager.getCurrentWindowMetrics().getBounds();
        }
    }

    public static Rect a(Context context) {
        return Api30Impl.a((WindowManager) context.getSystemService("window"));
    }
}
