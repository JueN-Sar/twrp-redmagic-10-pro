package androidx.core.app;

import android.content.res.Configuration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class MultiWindowModeChangedInfo {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2669a;

    /* renamed from: b, reason: collision with root package name */
    private Configuration f2670b;

    public MultiWindowModeChangedInfo(boolean z) {
        this.f2669a = z;
    }

    public final boolean a() {
        return this.f2669a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MultiWindowModeChangedInfo(boolean z, Configuration newConfig) {
        this(z);
        Intrinsics.e(newConfig, "newConfig");
        this.f2670b = newConfig;
    }
}
