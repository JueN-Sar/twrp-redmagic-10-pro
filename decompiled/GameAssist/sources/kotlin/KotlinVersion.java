package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@SinceKotlin
@Metadata
/* loaded from: classes2.dex */
public final class KotlinVersion implements Comparable<KotlinVersion> {

    /* renamed from: k, reason: collision with root package name */
    public static final Companion f18259k = new Companion(null);

    /* renamed from: l, reason: collision with root package name */
    public static final KotlinVersion f18260l = KotlinVersionCurrentValue.a();

    /* renamed from: c, reason: collision with root package name */
    private final int f18261c;

    /* renamed from: h, reason: collision with root package name */
    private final int f18262h;

    /* renamed from: i, reason: collision with root package name */
    private final int f18263i;

    /* renamed from: j, reason: collision with root package name */
    private final int f18264j;

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public KotlinVersion(int i2, int i3, int i4) {
        this.f18261c = i2;
        this.f18262h = i3;
        this.f18263i = i4;
        this.f18264j = d(i2, i3, i4);
    }

    private final int d(int i2, int i3, int i4) {
        if (new IntRange(0, 255).l(i2) && new IntRange(0, 255).l(i3) && new IntRange(0, 255).l(i4)) {
            return (i2 << 16) + (i3 << 8) + i4;
        }
        throw new IllegalArgumentException(("Version components are out of range: " + i2 + '.' + i3 + '.' + i4).toString());
    }

    @Override // java.lang.Comparable
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public int compareTo(KotlinVersion other) {
        Intrinsics.e(other, "other");
        return this.f18264j - other.f18264j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        KotlinVersion kotlinVersion = obj instanceof KotlinVersion ? (KotlinVersion) obj : null;
        return kotlinVersion != null && this.f18264j == kotlinVersion.f18264j;
    }

    public int hashCode() {
        return this.f18264j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f18261c);
        sb.append('.');
        sb.append(this.f18262h);
        sb.append('.');
        sb.append(this.f18263i);
        return sb.toString();
    }
}
