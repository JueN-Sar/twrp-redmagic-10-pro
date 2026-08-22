package com.google.firebase.components;

/* loaded from: classes.dex */
public final class Dependency {

    /* renamed from: a, reason: collision with root package name */
    private final Class f15832a;

    /* renamed from: b, reason: collision with root package name */
    private final int f15833b;

    /* renamed from: c, reason: collision with root package name */
    private final int f15834c;

    private Dependency(Class cls, int i2, int i3) {
        this.f15832a = (Class) Preconditions.c(cls, "Null dependency anInterface.");
        this.f15833b = i2;
        this.f15834c = i3;
    }

    private static String a(int i2) {
        if (i2 == 0) {
            return "direct";
        }
        if (i2 == 1) {
            return "provider";
        }
        if (i2 == 2) {
            return "deferred";
        }
        throw new AssertionError("Unsupported injection: " + i2);
    }

    public static Dependency g(Class cls) {
        return new Dependency(cls, 1, 0);
    }

    public static Dependency h(Class cls) {
        return new Dependency(cls, 1, 1);
    }

    public static Dependency i(Class cls) {
        return new Dependency(cls, 2, 0);
    }

    public Class b() {
        return this.f15832a;
    }

    public boolean c() {
        return this.f15834c == 2;
    }

    public boolean d() {
        return this.f15834c == 0;
    }

    public boolean e() {
        return this.f15833b == 1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dependency)) {
            return false;
        }
        Dependency dependency = (Dependency) obj;
        return this.f15832a == dependency.f15832a && this.f15833b == dependency.f15833b && this.f15834c == dependency.f15834c;
    }

    public boolean f() {
        return this.f15833b == 2;
    }

    public int hashCode() {
        return this.f15834c ^ ((((this.f15832a.hashCode() ^ 1000003) * 1000003) ^ this.f15833b) * 1000003);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.f15832a);
        sb.append(", type=");
        int i2 = this.f15833b;
        sb.append(i2 == 1 ? "required" : i2 == 0 ? "optional" : "set");
        sb.append(", injection=");
        sb.append(a(this.f15834c));
        sb.append("}");
        return sb.toString();
    }
}
