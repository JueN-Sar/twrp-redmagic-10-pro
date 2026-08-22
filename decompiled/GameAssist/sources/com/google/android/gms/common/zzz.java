package com.google.android.gms.common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzag;
import java.util.List;

/* loaded from: classes.dex */
final class zzz {

    /* renamed from: a, reason: collision with root package name */
    private String f11332a = null;

    /* renamed from: b, reason: collision with root package name */
    private long f11333b = -1;

    /* renamed from: c, reason: collision with root package name */
    private zzag f11334c = zzag.n();

    /* renamed from: d, reason: collision with root package name */
    private zzag f11335d = zzag.n();

    zzz() {
    }

    final zzz a(long j2) {
        this.f11333b = j2;
        return this;
    }

    final zzz b(List list) {
        Preconditions.i(list);
        this.f11335d = zzag.m(list);
        return this;
    }

    final zzz c(List list) {
        Preconditions.i(list);
        this.f11334c = zzag.m(list);
        return this;
    }

    final zzz d(String str) {
        this.f11332a = str;
        return this;
    }

    final zzab e() {
        if (this.f11332a == null) {
            throw new IllegalStateException("packageName must be defined");
        }
        if (this.f11333b < 0) {
            throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
        }
        if (this.f11334c.isEmpty() && this.f11335d.isEmpty()) {
            throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
        }
        return new zzab(this.f11332a, this.f11333b, this.f11334c, this.f11335d, null);
    }
}
