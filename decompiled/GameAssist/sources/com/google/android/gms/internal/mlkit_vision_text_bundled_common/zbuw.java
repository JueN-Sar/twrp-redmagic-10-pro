package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
public class zbuw {

    /* renamed from: a, reason: collision with root package name */
    protected volatile zbvm f12988a;

    /* renamed from: b, reason: collision with root package name */
    private volatile zbtc f12989b;

    public final int a() {
        if (this.f12989b != null) {
            return ((zbtb) this.f12989b).zba.length;
        }
        if (this.f12988a != null) {
            return this.f12988a.a();
        }
        return 0;
    }

    public final zbtc b() {
        if (this.f12989b != null) {
            return this.f12989b;
        }
        synchronized (this) {
            try {
                if (this.f12989b != null) {
                    return this.f12989b;
                }
                if (this.f12988a == null) {
                    this.f12989b = zbtc.zbb;
                } else {
                    this.f12989b = this.f12988a.d();
                }
                return this.f12989b;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zbvm c(zbvm zbvmVar) {
        zbvm zbvmVar2 = this.f12988a;
        this.f12989b = null;
        this.f12988a = zbvmVar;
        return zbvmVar2;
    }

    protected final void d(zbvm zbvmVar) {
        if (this.f12988a != null) {
            return;
        }
        synchronized (this) {
            if (this.f12988a != null) {
                return;
            }
            try {
                this.f12988a = zbvmVar;
                this.f12989b = zbtc.zbb;
            } catch (zbuq unused) {
                this.f12988a = zbvmVar;
                this.f12989b = zbtc.zbb;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zbuw)) {
            return false;
        }
        zbuw zbuwVar = (zbuw) obj;
        zbvm zbvmVar = this.f12988a;
        zbvm zbvmVar2 = zbuwVar.f12988a;
        if (zbvmVar == null && zbvmVar2 == null) {
            return b().equals(zbuwVar.b());
        }
        if (zbvmVar != null && zbvmVar2 != null) {
            return zbvmVar.equals(zbvmVar2);
        }
        if (zbvmVar != null) {
            zbuwVar.d(zbvmVar.f());
            return zbvmVar.equals(zbuwVar.f12988a);
        }
        d(zbvmVar2.f());
        return this.f12988a.equals(zbvmVar2);
    }

    public int hashCode() {
        return 1;
    }
}
