package androidx.core.graphics;

import android.graphics.Rect;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class Insets {

    /* renamed from: e, reason: collision with root package name */
    public static final Insets f2919e = new Insets(0, 0, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    public final int f2920a;

    /* renamed from: b, reason: collision with root package name */
    public final int f2921b;

    /* renamed from: c, reason: collision with root package name */
    public final int f2922c;

    /* renamed from: d, reason: collision with root package name */
    public final int f2923d;

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static android.graphics.Insets a(int i2, int i3, int i4, int i5) {
            return android.graphics.Insets.of(i2, i3, i4, i5);
        }
    }

    private Insets(int i2, int i3, int i4, int i5) {
        this.f2920a = i2;
        this.f2921b = i3;
        this.f2922c = i4;
        this.f2923d = i5;
    }

    public static Insets a(Insets insets, Insets insets2) {
        return b(Math.max(insets.f2920a, insets2.f2920a), Math.max(insets.f2921b, insets2.f2921b), Math.max(insets.f2922c, insets2.f2922c), Math.max(insets.f2923d, insets2.f2923d));
    }

    public static Insets b(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f2919e : new Insets(i2, i3, i4, i5);
    }

    public static Insets c(Rect rect) {
        return b(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static Insets d(android.graphics.Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public android.graphics.Insets e() {
        return Api29Impl.a(this.f2920a, this.f2921b, this.f2922c, this.f2923d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Insets.class != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.f2923d == insets.f2923d && this.f2920a == insets.f2920a && this.f2922c == insets.f2922c && this.f2921b == insets.f2921b;
    }

    public int hashCode() {
        return (((((this.f2920a * 31) + this.f2921b) * 31) + this.f2922c) * 31) + this.f2923d;
    }

    public String toString() {
        return "Insets{left=" + this.f2920a + ", top=" + this.f2921b + ", right=" + this.f2922c + ", bottom=" + this.f2923d + '}';
    }
}
