package com.google.android.libraries.vision.visionkit.pipeline;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zbbf {

    /* renamed from: a, reason: collision with root package name */
    private final int f13770a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f13771b = new HashMap();

    public zbbf(int i2) {
        this.f13770a = i2;
    }

    public final synchronized void a(long j2) {
        this.f13771b.remove(Long.valueOf(j2));
    }

    public final synchronized boolean b(Object obj, long j2) {
        if (this.f13771b.size() != this.f13770a) {
            this.f13771b.put(Long.valueOf(j2), obj);
            return true;
        }
        com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbcq.f12749b.c(this, "Buffer is full. Drop frame " + j2, new Object[0]);
        return false;
    }
}
