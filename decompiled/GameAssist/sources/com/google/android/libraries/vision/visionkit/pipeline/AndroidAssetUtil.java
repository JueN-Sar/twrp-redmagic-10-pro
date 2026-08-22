package com.google.android.libraries.vision.visionkit.pipeline;

import android.content.Context;

/* loaded from: classes.dex */
public final class AndroidAssetUtil {
    public static synchronized boolean a(Context context) {
        boolean nativeInitializeAssetManager;
        synchronized (AndroidAssetUtil.class) {
            nativeInitializeAssetManager = nativeInitializeAssetManager(context, context.getCacheDir().getAbsolutePath());
        }
        return nativeInitializeAssetManager;
    }

    private static native boolean nativeInitializeAssetManager(Context context, String str);
}
