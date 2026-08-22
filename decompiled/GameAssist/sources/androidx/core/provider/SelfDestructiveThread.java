package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo
@Deprecated
/* loaded from: classes.dex */
public class SelfDestructiveThread {

    /* renamed from: a, reason: collision with root package name */
    private final Object f3177a;

    /* renamed from: b, reason: collision with root package name */
    private HandlerThread f3178b;

    /* renamed from: c, reason: collision with root package name */
    private Handler f3179c;

    /* renamed from: d, reason: collision with root package name */
    private int f3180d;

    /* renamed from: e, reason: collision with root package name */
    private final int f3181e;

    /* renamed from: androidx.core.provider.SelfDestructiveThread$1, reason: invalid class name */
    class AnonymousClass1 implements Handler.Callback {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ SelfDestructiveThread f3182c;

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                this.f3182c.a();
                return true;
            }
            if (i2 != 1) {
                return true;
            }
            this.f3182c.b((Runnable) message.obj);
            return true;
        }
    }

    /* renamed from: androidx.core.provider.SelfDestructiveThread$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f3183c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Handler f3184h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ ReplyCallback f3185i;

        @Override // java.lang.Runnable
        public void run() {
            final Object obj;
            try {
                obj = this.f3183c.call();
            } catch (Exception unused) {
                obj = null;
            }
            this.f3184h.post(new Runnable() { // from class: androidx.core.provider.SelfDestructiveThread.2.1
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass2.this.f3185i.a(obj);
                }
            });
        }
    }

    /* renamed from: androidx.core.provider.SelfDestructiveThread$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ AtomicReference f3188c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ Callable f3189h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f3190i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f3191j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ Condition f3192k;

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f3188c.set(this.f3189h.call());
            } catch (Exception unused) {
            }
            this.f3190i.lock();
            try {
                this.f3191j.set(false);
                this.f3192k.signal();
            } finally {
                this.f3190i.unlock();
            }
        }
    }

    public interface ReplyCallback<T> {
        void a(Object obj);
    }

    void a() {
        synchronized (this.f3177a) {
            try {
                if (this.f3179c.hasMessages(1)) {
                    return;
                }
                this.f3178b.quit();
                this.f3178b = null;
                this.f3179c = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f3177a) {
            this.f3179c.removeMessages(0);
            Handler handler = this.f3179c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f3181e);
        }
    }

    @VisibleForTesting
    public int getGeneration() {
        int i2;
        synchronized (this.f3177a) {
            i2 = this.f3180d;
        }
        return i2;
    }

    @VisibleForTesting
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3177a) {
            z = this.f3178b != null;
        }
        return z;
    }
}
