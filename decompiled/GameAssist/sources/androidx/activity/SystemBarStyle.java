package androidx.activity;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata
/* loaded from: classes.dex */
public final class SystemBarStyle {

    /* renamed from: d, reason: collision with root package name */
    public static final Companion f79d = new Companion(null);

    /* renamed from: a, reason: collision with root package name */
    private final int f80a;

    /* renamed from: b, reason: collision with root package name */
    private final int f81b;

    /* renamed from: c, reason: collision with root package name */
    private final int f82c;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final int a() {
        return this.f81b;
    }

    public final int b() {
        return this.f82c;
    }

    public final int c(boolean z) {
        return z ? this.f81b : this.f80a;
    }

    public final int d(boolean z) {
        if (this.f82c == 0) {
            return 0;
        }
        return z ? this.f81b : this.f80a;
    }
}
