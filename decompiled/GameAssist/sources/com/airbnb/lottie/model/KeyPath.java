package com.airbnb.lottie.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class KeyPath {

    /* renamed from: c, reason: collision with root package name */
    public static final KeyPath f9613c = new KeyPath("COMPOSITION");

    /* renamed from: a, reason: collision with root package name */
    private final List f9614a;

    /* renamed from: b, reason: collision with root package name */
    private KeyPathElement f9615b;

    public KeyPath(String... strArr) {
        this.f9614a = Arrays.asList(strArr);
    }

    private boolean b() {
        return ((String) this.f9614a.get(r1.size() - 1)).equals("**");
    }

    private boolean f(String str) {
        return "__container".equals(str);
    }

    public KeyPath a(String str) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.f9614a.add(str);
        return keyPath;
    }

    public boolean c(String str, int i2) {
        if (i2 >= this.f9614a.size()) {
            return false;
        }
        boolean z = i2 == this.f9614a.size() - 1;
        String str2 = (String) this.f9614a.get(i2);
        if (!str2.equals("**")) {
            return (z || (i2 == this.f9614a.size() + (-2) && b())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z && ((String) this.f9614a.get(i2 + 1)).equals(str)) {
            return i2 == this.f9614a.size() + (-2) || (i2 == this.f9614a.size() + (-3) && b());
        }
        if (z) {
            return true;
        }
        int i3 = i2 + 1;
        if (i3 < this.f9614a.size() - 1) {
            return false;
        }
        return ((String) this.f9614a.get(i3)).equals(str);
    }

    public KeyPathElement d() {
        return this.f9615b;
    }

    public int e(String str, int i2) {
        if (f(str)) {
            return 0;
        }
        if (((String) this.f9614a.get(i2)).equals("**")) {
            return (i2 != this.f9614a.size() - 1 && ((String) this.f9614a.get(i2 + 1)).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyPath keyPath = (KeyPath) obj;
        if (!this.f9614a.equals(keyPath.f9614a)) {
            return false;
        }
        KeyPathElement keyPathElement = this.f9615b;
        return keyPathElement != null ? keyPathElement.equals(keyPath.f9615b) : keyPath.f9615b == null;
    }

    public boolean g(String str, int i2) {
        if (f(str)) {
            return true;
        }
        if (i2 >= this.f9614a.size()) {
            return false;
        }
        return ((String) this.f9614a.get(i2)).equals(str) || ((String) this.f9614a.get(i2)).equals("**") || ((String) this.f9614a.get(i2)).equals("*");
    }

    public boolean h(String str, int i2) {
        return "__container".equals(str) || i2 < this.f9614a.size() - 1 || ((String) this.f9614a.get(i2)).equals("**");
    }

    public int hashCode() {
        int hashCode = this.f9614a.hashCode() * 31;
        KeyPathElement keyPathElement = this.f9615b;
        return hashCode + (keyPathElement != null ? keyPathElement.hashCode() : 0);
    }

    public KeyPath i(KeyPathElement keyPathElement) {
        KeyPath keyPath = new KeyPath(this);
        keyPath.f9615b = keyPathElement;
        return keyPath;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f9614a);
        sb.append(",resolved=");
        sb.append(this.f9615b != null);
        sb.append('}');
        return sb.toString();
    }

    private KeyPath(KeyPath keyPath) {
        this.f9614a = new ArrayList(keyPath.f9614a);
        this.f9615b = keyPath.f9615b;
    }
}
