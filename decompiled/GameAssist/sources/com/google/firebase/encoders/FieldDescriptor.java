package com.google.firebase.encoders;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class FieldDescriptor {

    /* renamed from: a, reason: collision with root package name */
    private final String f15862a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f15863b;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final String f15864a;

        /* renamed from: b, reason: collision with root package name */
        private Map f15865b = null;

        Builder(String str) {
            this.f15864a = str;
        }

        public FieldDescriptor a() {
            return new FieldDescriptor(this.f15864a, this.f15865b == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(this.f15865b)));
        }

        public Builder b(Annotation annotation) {
            if (this.f15865b == null) {
                this.f15865b = new HashMap();
            }
            this.f15865b.put(annotation.annotationType(), annotation);
            return this;
        }
    }

    public static Builder a(String str) {
        return new Builder(str);
    }

    public static FieldDescriptor d(String str) {
        return new FieldDescriptor(str, Collections.emptyMap());
    }

    public String b() {
        return this.f15862a;
    }

    public Annotation c(Class cls) {
        return (Annotation) this.f15863b.get(cls);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldDescriptor)) {
            return false;
        }
        FieldDescriptor fieldDescriptor = (FieldDescriptor) obj;
        return this.f15862a.equals(fieldDescriptor.f15862a) && this.f15863b.equals(fieldDescriptor.f15863b);
    }

    public int hashCode() {
        return (this.f15862a.hashCode() * 31) + this.f15863b.hashCode();
    }

    public String toString() {
        return "FieldDescriptor{name=" + this.f15862a + ", properties=" + this.f15863b.values() + "}";
    }

    private FieldDescriptor(String str, Map map) {
        this.f15862a = str;
        this.f15863b = map;
    }
}
