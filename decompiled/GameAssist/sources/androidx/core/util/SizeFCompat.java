package androidx.core.util;

import android.util.SizeF;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class SizeFCompat {

    /* renamed from: a, reason: collision with root package name */
    private final float f3286a;

    /* renamed from: b, reason: collision with root package name */
    private final float f3287b;

    @RequiresApi
    private static final class Api21Impl {
        @NonNull
        @DoNotInline
        static SizeF a(@NonNull SizeFCompat sizeFCompat) {
            Preconditions.h(sizeFCompat);
            return new SizeF(sizeFCompat.b(), sizeFCompat.a());
        }

        @NonNull
        @DoNotInline
        static SizeFCompat b(@NonNull SizeF sizeF) {
            Preconditions.h(sizeF);
            return new SizeFCompat(sizeF.getWidth(), sizeF.getHeight());
        }
    }

    public SizeFCompat(float f2, float f3) {
        this.f3286a = Preconditions.c(f2, "width");
        this.f3287b = Preconditions.c(f3, "height");
    }

    public float a() {
        return this.f3287b;
    }

    public float b() {
        return this.f3286a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeFCompat)) {
            return false;
        }
        SizeFCompat sizeFCompat = (SizeFCompat) obj;
        return sizeFCompat.f3286a == this.f3286a && sizeFCompat.f3287b == this.f3287b;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.f3287b) ^ Float.floatToIntBits(this.f3286a);
    }

    public String toString() {
        return this.f3286a + "x" + this.f3287b;
    }
}
