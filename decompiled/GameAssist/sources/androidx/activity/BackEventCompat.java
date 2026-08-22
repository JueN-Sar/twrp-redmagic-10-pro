package androidx.activity;

import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
/* loaded from: classes.dex */
public final class BackEventCompat {

    /* renamed from: e, reason: collision with root package name */
    public static final Companion f2e = new Companion(null);

    /* renamed from: a, reason: collision with root package name */
    private final float f3a;

    /* renamed from: b, reason: collision with root package name */
    private final float f4b;

    /* renamed from: c, reason: collision with root package name */
    private final float f5c;

    /* renamed from: d, reason: collision with root package name */
    private final int f6d;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Target({ElementType.TYPE_USE})
    @Metadata
    @kotlin.annotation.Target
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention
    @RestrictTo
    public @interface SwipeEdge {
    }

    @VisibleForTesting
    public BackEventCompat(float f2, float f3, @FloatRange float f4, int i2) {
        this.f3a = f2;
        this.f4b = f3;
        this.f5c = f4;
        this.f6d = i2;
    }

    public final float a() {
        return this.f5c;
    }

    public final int b() {
        return this.f6d;
    }

    public final float c() {
        return this.f4b;
    }

    public String toString() {
        return "BackEventCompat{touchX=" + this.f3a + ", touchY=" + this.f4b + ", progress=" + this.f5c + ", swipeEdge=" + this.f6d + '}';
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public BackEventCompat(android.window.BackEvent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "backEvent"
            kotlin.jvm.internal.Intrinsics.e(r5, r0)
            androidx.activity.Api34Impl r0 = androidx.activity.Api34Impl.f1a
            float r1 = r0.d(r5)
            float r2 = r0.e(r5)
            float r3 = r0.b(r5)
            int r5 = r0.c(r5)
            r4.<init>(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.BackEventCompat.<init>(android.window.BackEvent):void");
    }
}
