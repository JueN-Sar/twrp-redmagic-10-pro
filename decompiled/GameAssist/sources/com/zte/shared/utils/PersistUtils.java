package com.zte.shared.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.zte.shared.utils.PersistUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public final class PersistUtils implements ServiceConnection, Runnable {

    /* renamed from: k, reason: collision with root package name */
    private static final PersistUtils f18111k = new PersistUtils();

    /* renamed from: c, reason: collision with root package name */
    private final Handler f18112c = new Handler(Looper.getMainLooper());

    /* renamed from: h, reason: collision with root package name */
    private final List f18113h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private IBinder f18114i;

    /* renamed from: j, reason: collision with root package name */
    private Context f18115j;

    public interface ReadCallback {
    }

    private PersistUtils() {
    }

    private void b() {
        AsyncTask.execute(new Runnable() { // from class: s.a
            @Override // java.lang.Runnable
            public final void run() {
                PersistUtils.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c() {
        this.f18112c.removeCallbacks(this);
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            arrayList.addAll(this.f18113h);
            this.f18113h.clear();
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((Consumer) arrayList.get(i2)).accept(this.f18114i);
        }
        if (this.f18113h.size() > 0) {
            b();
        } else {
            this.f18112c.postDelayed(this, 5000L);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f18114i = iBinder;
        b();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f18114i = null;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f18115j.unbindService(this);
        this.f18114i = null;
    }
}
