package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
final class zad {

    /* renamed from: a, reason: collision with root package name */
    public final Uri f10944a;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zad) {
            return Objects.a(((zad) obj).f10944a, this.f10944a);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.b(this.f10944a);
    }
}
