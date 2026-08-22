package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes.dex */
public final class zzod {

    /* renamed from: a, reason: collision with root package name */
    private final zzob f13477a;

    /* renamed from: b, reason: collision with root package name */
    private final Integer f13478b;

    /* renamed from: c, reason: collision with root package name */
    private final Integer f13479c;

    /* renamed from: d, reason: collision with root package name */
    private final Boolean f13480d;

    /* synthetic */ zzod(zzoa zzoaVar, zzoc zzocVar) {
        zzob zzobVar;
        Integer num;
        zzobVar = zzoaVar.f13475a;
        this.f13477a = zzobVar;
        num = zzoaVar.f13476b;
        this.f13478b = num;
        this.f13479c = null;
        this.f13480d = null;
    }

    public final zzob a() {
        return this.f13477a;
    }

    public final Integer b() {
        return this.f13478b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzod)) {
            return false;
        }
        zzod zzodVar = (zzod) obj;
        return Objects.a(this.f13477a, zzodVar.f13477a) && Objects.a(this.f13478b, zzodVar.f13478b) && Objects.a(null, null) && Objects.a(null, null);
    }

    public final int hashCode() {
        return Objects.b(this.f13477a, this.f13478b, null, null);
    }
}
