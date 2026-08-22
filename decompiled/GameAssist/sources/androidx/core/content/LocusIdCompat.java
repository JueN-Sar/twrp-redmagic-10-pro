package androidx.core.content;

import android.content.LocusId;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class LocusIdCompat {

    /* renamed from: a, reason: collision with root package name */
    private final String f2850a;

    /* renamed from: b, reason: collision with root package name */
    private final LocusId f2851b;

    @RequiresApi
    private static class Api29Impl {
    }

    private String a() {
        return this.f2850a.length() + "_chars";
    }

    public LocusId b() {
        return this.f2851b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocusIdCompat.class != obj.getClass()) {
            return false;
        }
        LocusIdCompat locusIdCompat = (LocusIdCompat) obj;
        String str = this.f2850a;
        return str == null ? locusIdCompat.f2850a == null : str.equals(locusIdCompat.f2850a);
    }

    public int hashCode() {
        String str = this.f2850a;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "LocusIdCompat[" + a() + "]";
    }
}
