package com.zte.gameassist.common;

import android.os.Handler;
import android.os.Looper;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.SystemWindowMonitor;
import com.zte.shared.wrapper.ContextWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SystemWindowMonitor {

    /* renamed from: c, reason: collision with root package name */
    private static volatile SystemWindowMonitor f16572c = new SystemWindowMonitor();

    /* renamed from: a, reason: collision with root package name */
    private Handler f16573a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    private final List f16574b = new ArrayList();

    protected static class Callback {

        /* renamed from: a, reason: collision with root package name */
        private final Handler f16575a;

        /* renamed from: b, reason: collision with root package name */
        private final String f16576b;

        /* renamed from: c, reason: collision with root package name */
        private final String f16577c;

        /* renamed from: d, reason: collision with root package name */
        private final ICallback f16578d;

        protected Callback(String str, String str2, ICallback iCallback, Handler handler) {
            this.f16575a = handler;
            this.f16576b = str;
            this.f16577c = str2;
            this.f16578d = iCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(boolean z) {
            this.f16578d.a(z, this.f16576b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean g(String str, String str2, ICallback iCallback) {
            if (this.f16578d != iCallback) {
                return false;
            }
            if (str == null || str.equals(this.f16576b)) {
                return str2 == null || str2.equals(this.f16577c);
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean h(AbsGameAssistToken.SystemWindow systemWindow) {
            return i(systemWindow.mTitle, systemWindow.mPackageName);
        }

        private boolean i(String str, String str2) {
            String str3 = this.f16576b;
            if (str3 != null && !str3.equals(str)) {
                return false;
            }
            String str4 = this.f16577c;
            return str4 == null || str4.equals(str2);
        }

        public void e(final boolean z) {
            if (Looper.myLooper() == this.f16575a.getLooper()) {
                this.f16578d.a(z, this.f16576b);
            } else {
                this.f16575a.post(new Runnable() { // from class: com.zte.gameassist.common.r
                    @Override // java.lang.Runnable
                    public final void run() {
                        SystemWindowMonitor.Callback.this.d(z);
                    }
                });
            }
        }

        protected boolean f(Callback callback) {
            return g(callback.f16576b, callback.f16577c, callback.f16578d);
        }
    }

    public interface ICallback {
        void a(boolean z, String str);
    }

    public interface ISystemWindowContainer {
        void a(boolean z, AbsGameAssistToken.SystemWindow systemWindow);
    }

    public static ISystemWindowContainer g() {
        final SystemWindowMonitor h2 = h();
        Objects.requireNonNull(h2);
        return new ISystemWindowContainer() { // from class: com.zte.gameassist.common.p
            @Override // com.zte.gameassist.common.SystemWindowMonitor.ISystemWindowContainer
            public final void a(boolean z, AbsGameAssistToken.SystemWindow systemWindow) {
                SystemWindowMonitor.this.m(z, systemWindow);
            }
        };
    }

    public static SystemWindowMonitor h() {
        if (f16572c == null) {
            synchronized (SystemWindowMonitor.class) {
                try {
                    if (f16572c == null) {
                        f16572c = new SystemWindowMonitor();
                    }
                } finally {
                }
            }
        }
        return f16572c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i(boolean z, AbsGameAssistToken.SystemWindow systemWindow) {
        if (z) {
            j(true, systemWindow);
        } else {
            j(false, systemWindow);
        }
    }

    private void j(boolean z, AbsGameAssistToken.SystemWindow systemWindow) {
        for (Callback callback : this.f16574b) {
            if (callback.h(systemWindow)) {
                callback.e(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(final boolean z, final AbsGameAssistToken.SystemWindow systemWindow) {
        this.f16573a.post(new Runnable() { // from class: com.zte.gameassist.common.q
            @Override // java.lang.Runnable
            public final void run() {
                SystemWindowMonitor.this.i(z, systemWindow);
            }
        });
    }

    public void c(String str, ICallback iCallback, Handler handler) {
        d(str, null, iCallback, handler);
    }

    public void d(String str, String str2, ICallback iCallback, Handler handler) {
        if (str == null || iCallback == null) {
            return;
        }
        Callback callback = new Callback(str, str2, iCallback, handler);
        if (e(callback)) {
            return;
        }
        this.f16574b.add(callback);
        if (f(str, str2)) {
            callback.e(true);
        }
    }

    public boolean e(Callback callback) {
        Iterator it = this.f16574b.iterator();
        while (it.hasNext()) {
            if (((Callback) it.next()).f(callback)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(String str, String str2) {
        for (AbsGameAssistToken.SystemWindow systemWindow : SystemMgr.y(ContextWrapper.getContext()).f16566i) {
            if (str2 != null && str2.equals(systemWindow.mPackageName) && str.equals(systemWindow.mTitle)) {
                return true;
            }
            if (str2 != null && str.equals(systemWindow.mTitle)) {
                return true;
            }
        }
        return false;
    }

    public void k(ICallback iCallback) {
        l(null, null, iCallback);
    }

    public void l(String str, String str2, ICallback iCallback) {
        for (Callback callback : this.f16574b) {
            if (callback.g(str, str2, iCallback)) {
                this.f16574b.remove(callback);
                return;
            }
        }
    }
}
