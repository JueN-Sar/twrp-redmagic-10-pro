package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes.dex */
final class AutoValue_CreationContext extends CreationContext {

    /* renamed from: a, reason: collision with root package name */
    private final Context f10250a;

    /* renamed from: b, reason: collision with root package name */
    private final Clock f10251b;

    /* renamed from: c, reason: collision with root package name */
    private final Clock f10252c;

    /* renamed from: d, reason: collision with root package name */
    private final String f10253d;

    AutoValue_CreationContext(Context context, Clock clock, Clock clock2, String str) {
        if (context == null) {
            throw new NullPointerException("Null applicationContext");
        }
        this.f10250a = context;
        if (clock == null) {
            throw new NullPointerException("Null wallClock");
        }
        this.f10251b = clock;
        if (clock2 == null) {
            throw new NullPointerException("Null monotonicClock");
        }
        this.f10252c = clock2;
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        this.f10253d = str;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Context b() {
        return this.f10250a;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public String c() {
        return this.f10253d;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock d() {
        return this.f10252c;
    }

    @Override // com.google.android.datatransport.runtime.backends.CreationContext
    public Clock e() {
        return this.f10251b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreationContext)) {
            return false;
        }
        CreationContext creationContext = (CreationContext) obj;
        return this.f10250a.equals(creationContext.b()) && this.f10251b.equals(creationContext.e()) && this.f10252c.equals(creationContext.d()) && this.f10253d.equals(creationContext.c());
    }

    public int hashCode() {
        return this.f10253d.hashCode() ^ ((((((this.f10250a.hashCode() ^ 1000003) * 1000003) ^ this.f10251b.hashCode()) * 1000003) ^ this.f10252c.hashCode()) * 1000003);
    }

    public String toString() {
        return "CreationContext{applicationContext=" + this.f10250a + ", wallClock=" + this.f10251b + ", monotonicClock=" + this.f10252c + ", backendName=" + this.f10253d + "}";
    }
}
