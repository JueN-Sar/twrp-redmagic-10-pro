package androidx.core.app;

import android.content.res.Configuration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata
/* loaded from: classes.dex */
public final class PictureInPictureModeChangedInfo {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2820a;

    /* renamed from: b, reason: collision with root package name */
    private Configuration f2821b;

    public PictureInPictureModeChangedInfo(boolean z) {
        this.f2820a = z;
    }

    public final boolean a() {
        return this.f2820a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PictureInPictureModeChangedInfo(boolean z, Configuration newConfig) {
        this(z);
        Intrinsics.e(newConfig, "newConfig");
        this.f2821b = newConfig;
    }
}
