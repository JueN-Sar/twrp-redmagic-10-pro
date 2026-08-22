package com.google.android.gms.common.internal;

import android.util.Log;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class zzc {

    /* renamed from: a, reason: collision with root package name */
    private Object f11098a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f11099b = false;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ BaseGmsClient f11100c;

    public zzc(BaseGmsClient baseGmsClient, Object obj) {
        this.f11100c = baseGmsClient;
        this.f11098a = obj;
    }

    protected abstract void a(Object obj);

    protected abstract void b();

    public final void c() {
        Object obj;
        synchronized (this) {
            try {
                obj = this.f11098a;
                if (this.f11099b) {
                    Log.w("GmsClient", "Callback proxy " + toString() + " being reused. This is not safe.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (obj != null) {
            a(obj);
        }
        synchronized (this) {
            this.f11099b = true;
        }
        e();
    }

    public final void d() {
        synchronized (this) {
            this.f11098a = null;
        }
    }

    public final void e() {
        ArrayList arrayList;
        ArrayList arrayList2;
        d();
        arrayList = this.f11100c.v;
        synchronized (arrayList) {
            arrayList2 = this.f11100c.v;
            arrayList2.remove(this);
        }
    }
}
