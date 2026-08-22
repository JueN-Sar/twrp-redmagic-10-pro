package com.google.android.datatransport;

/* loaded from: classes.dex */
public final class Encoding {

    /* renamed from: a, reason: collision with root package name */
    private final String f10055a;

    private Encoding(String str) {
        if (str == null) {
            throw new NullPointerException("name is null");
        }
        this.f10055a = str;
    }

    public static Encoding b(String str) {
        return new Encoding(str);
    }

    public String a() {
        return this.f10055a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Encoding) {
            return this.f10055a.equals(((Encoding) obj).f10055a);
        }
        return false;
    }

    public int hashCode() {
        return this.f10055a.hashCode() ^ 1000003;
    }

    public String toString() {
        return "Encoding{name=\"" + this.f10055a + "\"}";
    }
}
