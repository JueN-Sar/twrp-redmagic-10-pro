package com.google.android.gms.tasks;

import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {

    /* renamed from: a, reason: collision with root package name */
    private final long f13669a;

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public void a(Task task) {
        Object obj;
        String str;
        Exception h2;
        if (task.l()) {
            obj = task.i();
            str = null;
        } else if (task.j() || (h2 = task.h()) == null) {
            obj = null;
            str = null;
        } else {
            str = h2.getMessage();
            obj = null;
        }
        nativeOnComplete(this.f13669a, obj, task.l(), task.j(), str);
    }

    @KeepForSdk
    public native void nativeOnComplete(long j2, @Nullable Object obj, boolean z, boolean z2, @Nullable String str);
}
