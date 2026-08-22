package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Pools;
import java.util.concurrent.ArrayBlockingQueue;

/* loaded from: classes.dex */
public final class AsyncLayoutInflater {

    /* renamed from: a, reason: collision with root package name */
    LayoutInflater f1123a;

    /* renamed from: b, reason: collision with root package name */
    Handler f1124b;

    /* renamed from: c, reason: collision with root package name */
    InflateThread f1125c;

    /* renamed from: androidx.asynclayoutinflater.view.AsyncLayoutInflater$1, reason: invalid class name */
    class AnonymousClass1 implements Handler.Callback {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ AsyncLayoutInflater f1126c;

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            InflateRequest inflateRequest = (InflateRequest) message.obj;
            if (inflateRequest.f1131d == null) {
                inflateRequest.f1131d = this.f1126c.f1123a.inflate(inflateRequest.f1130c, inflateRequest.f1129b, false);
            }
            inflateRequest.f1132e.a(inflateRequest.f1131d, inflateRequest.f1130c, inflateRequest.f1129b);
            this.f1126c.f1125c.a(inflateRequest);
            return true;
        }
    }

    private static class BasicInflater extends LayoutInflater {

        /* renamed from: a, reason: collision with root package name */
        private static final String[] f1127a = {"android.widget.", "android.webkit.", "android.app."};

        BasicInflater(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new BasicInflater(context);
        }

        @Override // android.view.LayoutInflater
        protected View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            for (String str2 : f1127a) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    private static class InflateRequest {

        /* renamed from: a, reason: collision with root package name */
        AsyncLayoutInflater f1128a;

        /* renamed from: b, reason: collision with root package name */
        ViewGroup f1129b;

        /* renamed from: c, reason: collision with root package name */
        int f1130c;

        /* renamed from: d, reason: collision with root package name */
        View f1131d;

        /* renamed from: e, reason: collision with root package name */
        OnInflateFinishedListener f1132e;
    }

    private static class InflateThread extends Thread {

        /* renamed from: i, reason: collision with root package name */
        private static final InflateThread f1133i;

        /* renamed from: c, reason: collision with root package name */
        private ArrayBlockingQueue f1134c = new ArrayBlockingQueue(10);

        /* renamed from: h, reason: collision with root package name */
        private Pools.SynchronizedPool f1135h = new Pools.SynchronizedPool(10);

        static {
            InflateThread inflateThread = new InflateThread();
            f1133i = inflateThread;
            inflateThread.start();
        }

        private InflateThread() {
        }

        public void a(InflateRequest inflateRequest) {
            inflateRequest.f1132e = null;
            inflateRequest.f1128a = null;
            inflateRequest.f1129b = null;
            inflateRequest.f1130c = 0;
            inflateRequest.f1131d = null;
            this.f1135h.release(inflateRequest);
        }

        public void b() {
            try {
                InflateRequest inflateRequest = (InflateRequest) this.f1134c.take();
                try {
                    inflateRequest.f1131d = inflateRequest.f1128a.f1123a.inflate(inflateRequest.f1130c, inflateRequest.f1129b, false);
                } catch (RuntimeException e2) {
                    Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e2);
                }
                Message.obtain(inflateRequest.f1128a.f1124b, 0, inflateRequest).sendToTarget();
            } catch (InterruptedException e3) {
                Log.w("AsyncLayoutInflater", e3);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                b();
            }
        }
    }

    public interface OnInflateFinishedListener {
        void a(View view, int i2, ViewGroup viewGroup);
    }
}
