package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes.dex */
public final class zzv {

    /* renamed from: a, reason: collision with root package name */
    private final String f13602a = "\n";

    private zzv(String str) {
    }

    public static zzv a(String str) {
        return new zzv("\n");
    }

    static final CharSequence c(Object obj) {
        Objects.requireNonNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public final String b(Iterable iterable) {
        Iterator it = iterable.iterator();
        StringBuilder sb = new StringBuilder();
        try {
            if (it.hasNext()) {
                sb.append(c(it.next()));
                while (it.hasNext()) {
                    sb.append((CharSequence) this.f13602a);
                    sb.append(c(it.next()));
                }
            }
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
