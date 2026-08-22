package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public class LifecycleActivity {

    /* renamed from: a, reason: collision with root package name */
    private final Object f10603a;

    public LifecycleActivity(Activity activity) {
        Preconditions.j(activity, "Activity must not be null");
        this.f10603a = activity;
    }

    public final Activity a() {
        return (Activity) this.f10603a;
    }

    public final FragmentActivity b() {
        return (FragmentActivity) this.f10603a;
    }

    public final boolean c() {
        return this.f10603a instanceof Activity;
    }

    public final boolean d() {
        return this.f10603a instanceof FragmentActivity;
    }
}
