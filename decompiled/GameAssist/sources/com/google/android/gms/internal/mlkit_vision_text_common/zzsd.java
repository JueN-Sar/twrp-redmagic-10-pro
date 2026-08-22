package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class zzsd {

    /* renamed from: a, reason: collision with root package name */
    private final zzsb f13520a;

    /* renamed from: b, reason: collision with root package name */
    private final Boolean f13521b;

    /* renamed from: c, reason: collision with root package name */
    private final String f13522c;

    /* synthetic */ zzsd(zzsa zzsaVar, zzsc zzscVar) {
        zzsb zzsbVar;
        zzsbVar = zzsaVar.f13519a;
        this.f13520a = zzsbVar;
        this.f13521b = null;
        this.f13522c = null;
    }

    public final zzsb a() {
        return this.f13520a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzsd) && Objects.a(this.f13520a, ((zzsd) obj).f13520a) && Objects.a(null, null) && Objects.a(null, null);
    }

    public final int hashCode() {
        return Objects.b(this.f13520a, null, null);
    }
}
