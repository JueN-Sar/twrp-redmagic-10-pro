package cn.nubia.plugin.screenextraction.common;

import android.content.ComponentName;

/* loaded from: classes.dex */
public class ComponentActivity {

    /* renamed from: a, reason: collision with root package name */
    public final ComponentName f8579a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f8580b;

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ComponentActivity)) {
            return false;
        }
        ComponentActivity componentActivity = (ComponentActivity) obj;
        return this.f8580b == componentActivity.f8580b && this.f8579a.getPackageName().equals(componentActivity.f8579a.getPackageName());
    }

    public String toString() {
        return "ComponentActivity{mComponent=" + this.f8579a + ", mIsGameScene=" + this.f8580b + '}';
    }
}
