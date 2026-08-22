package com.google.mlkit.vision.interfaces;

import androidx.lifecycle.LifecycleObserver;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public interface Detector<DetectionResultT> extends Closeable, LifecycleObserver {

    @KeepForSdk
    @Retention(RetentionPolicy.CLASS)
    public @interface DetectorType {
    }
}
