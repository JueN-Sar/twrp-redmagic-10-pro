package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata
@SourceDebugExtension
/* loaded from: classes2.dex */
public final class DurationJvmKt {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f18806a = false;

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal[] f18807b;

    static {
        ThreadLocal[] threadLocalArr = new ThreadLocal[4];
        for (int i2 = 0; i2 < 4; i2++) {
            threadLocalArr[i2] = new ThreadLocal();
        }
        f18807b = threadLocalArr;
    }

    public static final boolean a() {
        return f18806a;
    }
}
