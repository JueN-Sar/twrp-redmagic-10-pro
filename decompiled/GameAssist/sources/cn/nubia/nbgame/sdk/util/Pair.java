package cn.nubia.nbgame.sdk.util;

/* loaded from: classes.dex */
final class Pair<A, B> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f8325a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f8326b;

    private Pair(Object obj, Object obj2) {
        this.f8325a = obj;
        this.f8326b = obj2;
    }

    public static Pair b(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public Object a() {
        return this.f8325a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Pair.class != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        Object obj2 = this.f8325a;
        if (obj2 == null) {
            if (pair.f8325a != null) {
                return false;
            }
        } else if (!obj2.equals(pair.f8325a)) {
            return false;
        }
        Object obj3 = this.f8326b;
        if (obj3 == null) {
            if (pair.f8326b != null) {
                return false;
            }
        } else if (!obj3.equals(pair.f8326b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.f8325a;
        int hashCode = ((obj == null ? 0 : obj.hashCode()) + 31) * 31;
        Object obj2 = this.f8326b;
        return hashCode + (obj2 != null ? obj2.hashCode() : 0);
    }
}
