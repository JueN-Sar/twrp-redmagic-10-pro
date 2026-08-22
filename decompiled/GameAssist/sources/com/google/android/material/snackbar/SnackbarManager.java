package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class SnackbarManager {

    /* renamed from: e, reason: collision with root package name */
    private static SnackbarManager f15329e;

    /* renamed from: a, reason: collision with root package name */
    private final Object f15330a = new Object();

    /* renamed from: b, reason: collision with root package name */
    private final Handler f15331b = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.SnackbarManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            SnackbarManager.this.d((SnackbarRecord) message.obj);
            return true;
        }
    });

    /* renamed from: c, reason: collision with root package name */
    private SnackbarRecord f15332c;

    /* renamed from: d, reason: collision with root package name */
    private SnackbarRecord f15333d;

    interface Callback {
        void a(int i2);

        void show();
    }

    private static class SnackbarRecord {

        /* renamed from: a, reason: collision with root package name */
        final WeakReference f15335a;

        /* renamed from: b, reason: collision with root package name */
        int f15336b;

        /* renamed from: c, reason: collision with root package name */
        boolean f15337c;

        boolean a(Callback callback) {
            return callback != null && this.f15335a.get() == callback;
        }
    }

    private SnackbarManager() {
    }

    private boolean a(SnackbarRecord snackbarRecord, int i2) {
        Callback callback = (Callback) snackbarRecord.f15335a.get();
        if (callback == null) {
            return false;
        }
        this.f15331b.removeCallbacksAndMessages(snackbarRecord);
        callback.a(i2);
        return true;
    }

    static SnackbarManager c() {
        if (f15329e == null) {
            f15329e = new SnackbarManager();
        }
        return f15329e;
    }

    private boolean f(Callback callback) {
        SnackbarRecord snackbarRecord = this.f15332c;
        return snackbarRecord != null && snackbarRecord.a(callback);
    }

    private boolean g(Callback callback) {
        SnackbarRecord snackbarRecord = this.f15333d;
        return snackbarRecord != null && snackbarRecord.a(callback);
    }

    private void l(SnackbarRecord snackbarRecord) {
        int i2 = snackbarRecord.f15336b;
        if (i2 == -2) {
            return;
        }
        if (i2 <= 0) {
            i2 = i2 == -1 ? 1500 : 2750;
        }
        this.f15331b.removeCallbacksAndMessages(snackbarRecord);
        Handler handler = this.f15331b;
        handler.sendMessageDelayed(Message.obtain(handler, 0, snackbarRecord), i2);
    }

    private void m() {
        SnackbarRecord snackbarRecord = this.f15333d;
        if (snackbarRecord != null) {
            this.f15332c = snackbarRecord;
            this.f15333d = null;
            Callback callback = (Callback) snackbarRecord.f15335a.get();
            if (callback != null) {
                callback.show();
            } else {
                this.f15332c = null;
            }
        }
    }

    public void b(Callback callback, int i2) {
        synchronized (this.f15330a) {
            try {
                if (f(callback)) {
                    a(this.f15332c, i2);
                } else if (g(callback)) {
                    a(this.f15333d, i2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void d(SnackbarRecord snackbarRecord) {
        synchronized (this.f15330a) {
            try {
                if (this.f15332c != snackbarRecord) {
                    if (this.f15333d == snackbarRecord) {
                    }
                }
                a(snackbarRecord, 2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean e(Callback callback) {
        boolean z;
        synchronized (this.f15330a) {
            try {
                z = f(callback) || g(callback);
            } finally {
            }
        }
        return z;
    }

    public void h(Callback callback) {
        synchronized (this.f15330a) {
            try {
                if (f(callback)) {
                    this.f15332c = null;
                    if (this.f15333d != null) {
                        m();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void i(Callback callback) {
        synchronized (this.f15330a) {
            try {
                if (f(callback)) {
                    l(this.f15332c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void j(Callback callback) {
        synchronized (this.f15330a) {
            try {
                if (f(callback)) {
                    SnackbarRecord snackbarRecord = this.f15332c;
                    if (!snackbarRecord.f15337c) {
                        snackbarRecord.f15337c = true;
                        this.f15331b.removeCallbacksAndMessages(snackbarRecord);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void k(Callback callback) {
        synchronized (this.f15330a) {
            try {
                if (f(callback)) {
                    SnackbarRecord snackbarRecord = this.f15332c;
                    if (snackbarRecord.f15337c) {
                        snackbarRecord.f15337c = false;
                        l(snackbarRecord);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
