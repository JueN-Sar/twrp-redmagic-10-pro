package com.google.android.gms.internal.common;

import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes.dex */
final class zzv implements Iterable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ CharSequence f11404c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ zzx f11405h;

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        Iterator d2;
        d2 = this.f11405h.d(this.f11404c);
        return d2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = iterator();
        try {
            if (it.hasNext()) {
                sb.append(zzq.a(it.next(), ", "));
                while (it.hasNext()) {
                    sb.append((CharSequence) ", ");
                    sb.append(zzq.a(it.next(), ", "));
                }
            }
            sb.append(']');
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
