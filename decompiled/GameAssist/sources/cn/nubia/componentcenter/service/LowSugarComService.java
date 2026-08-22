package cn.nubia.componentcenter.service;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;

/* loaded from: classes.dex */
public interface LowSugarComService {

    /* renamed from: a, reason: collision with root package name */
    public static final Uri f5873a = Uri.parse("content://com.zte.aispeaker.contentProvider");

    public interface ICallback {
        void u();
    }

    void b();

    boolean c();

    boolean d();

    void e(int i2);

    boolean f(String str);

    void g(String str, Context context);

    @VisibleForTesting
    int getSceneIndex();

    void h(Context context);

    void i(boolean z, ICallback iCallback);

    @VisibleForTesting
    boolean isDetecting();

    boolean j(Context context);
}
