package com.google.android.gms.common.api.internal;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import java.util.Set;

/* loaded from: classes.dex */
public final class zadc {

    /* renamed from: b, reason: collision with root package name */
    public static final Status f10834b = new Status(8, "The connection to Google Play services was lost");

    /* renamed from: a, reason: collision with root package name */
    private final zadb f10835a;

    @VisibleForTesting
    final Set zab;

    final void a(BasePendingResult basePendingResult) {
        this.zab.add(basePendingResult);
        basePendingResult.q(this.f10835a);
    }

    public final void b() {
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zab.toArray(new BasePendingResult[0])) {
            basePendingResult.q(null);
            if (basePendingResult.p()) {
                this.zab.remove(basePendingResult);
            }
        }
    }
}
