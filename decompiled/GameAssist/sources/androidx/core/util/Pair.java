package androidx.core.util;

/* loaded from: classes.dex */
public class Pair<F, S> {

    /* renamed from: a, reason: collision with root package name */
    public final Object f3270a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f3271b;

    public Pair(Object obj, Object obj2) {
        this.f3270a = obj;
        this.f3271b = obj2;
    }

    public static Pair a(Object obj, Object obj2) {
        return new Pair(obj, obj2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return ObjectsCompat.a(pair.f3270a, this.f3270a) && ObjectsCompat.a(pair.f3271b, this.f3271b);
    }

    public int hashCode() {
        Object obj = this.f3270a;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f3271b;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + this.f3270a + " " + this.f3271b + "}";
    }
}
