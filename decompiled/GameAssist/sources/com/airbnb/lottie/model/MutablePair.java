package com.airbnb.lottie.model;

import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;

@RestrictTo
/* loaded from: classes.dex */
public class MutablePair<T> {

    /* renamed from: a, reason: collision with root package name */
    Object f9621a;

    /* renamed from: b, reason: collision with root package name */
    Object f9622b;

    private static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void b(Object obj, Object obj2) {
        this.f9621a = obj;
        this.f9622b = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return a(pair.f3270a, this.f9621a) && a(pair.f3271b, this.f9622b);
    }

    public int hashCode() {
        Object obj = this.f9621a;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f9622b;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f9621a + " " + this.f9622b + "}";
    }
}
