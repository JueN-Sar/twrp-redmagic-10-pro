package androidx.recyclerview.widget;

import android.os.Handler;
import android.util.Log;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class MessageThreadUtil<T> implements ThreadUtil<T> {

    /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$1, reason: invalid class name */
    class AnonymousClass1 implements ThreadUtil.MainThreadCallback<Object> {

        /* renamed from: a, reason: collision with root package name */
        final MessageQueue f5120a;

        /* renamed from: b, reason: collision with root package name */
        private final Handler f5121b;

        /* renamed from: c, reason: collision with root package name */
        private Runnable f5122c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ThreadUtil.MainThreadCallback f5123d;

        /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$1$1, reason: invalid class name and collision with other inner class name */
        class RunnableC00041 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ AnonymousClass1 f5124c;

            @Override // java.lang.Runnable
            public void run() {
                SyncQueueItem a2 = this.f5124c.f5120a.a();
                while (a2 != null) {
                    int i2 = a2.f5135b;
                    if (i2 == 1) {
                        this.f5124c.f5123d.c(a2.f5136c, a2.f5137d);
                    } else if (i2 == 2) {
                        this.f5124c.f5123d.b(a2.f5136c, (TileList.Tile) a2.f5141h);
                    } else if (i2 != 3) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a2.f5135b);
                    } else {
                        this.f5124c.f5123d.a(a2.f5136c, a2.f5137d);
                    }
                    a2 = this.f5124c.f5120a.a();
                }
            }
        }

        private void d(SyncQueueItem syncQueueItem) {
            this.f5120a.c(syncQueueItem);
            this.f5121b.post(this.f5122c);
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void a(int i2, int i3) {
            d(SyncQueueItem.a(3, i2, i3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void b(int i2, TileList.Tile tile) {
            d(SyncQueueItem.c(2, i2, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void c(int i2, int i3) {
            d(SyncQueueItem.a(1, i2, i3));
        }
    }

    /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$2, reason: invalid class name */
    class AnonymousClass2 implements ThreadUtil.BackgroundCallback<Object> {

        /* renamed from: a, reason: collision with root package name */
        final MessageQueue f5125a;

        /* renamed from: b, reason: collision with root package name */
        private final Executor f5126b;

        /* renamed from: c, reason: collision with root package name */
        AtomicBoolean f5127c;

        /* renamed from: d, reason: collision with root package name */
        private Runnable f5128d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ ThreadUtil.BackgroundCallback f5129e;

        /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$2$1, reason: invalid class name */
        class AnonymousClass1 implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ AnonymousClass2 f5130c;

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    SyncQueueItem a2 = this.f5130c.f5125a.a();
                    if (a2 == null) {
                        this.f5130c.f5127c.set(false);
                        return;
                    }
                    int i2 = a2.f5135b;
                    if (i2 == 1) {
                        this.f5130c.f5125a.b(1);
                        this.f5130c.f5129e.c(a2.f5136c);
                    } else if (i2 == 2) {
                        this.f5130c.f5125a.b(2);
                        this.f5130c.f5125a.b(3);
                        this.f5130c.f5129e.a(a2.f5136c, a2.f5137d, a2.f5138e, a2.f5139f, a2.f5140g);
                    } else if (i2 == 3) {
                        this.f5130c.f5129e.b(a2.f5136c, a2.f5137d);
                    } else if (i2 != 4) {
                        Log.e("ThreadUtil", "Unsupported message, what=" + a2.f5135b);
                    } else {
                        this.f5130c.f5129e.d((TileList.Tile) a2.f5141h);
                    }
                }
            }
        }

        private void e() {
            if (this.f5127c.compareAndSet(false, true)) {
                this.f5126b.execute(this.f5128d);
            }
        }

        private void f(SyncQueueItem syncQueueItem) {
            this.f5125a.c(syncQueueItem);
            e();
        }

        private void g(SyncQueueItem syncQueueItem) {
            this.f5125a.d(syncQueueItem);
            e();
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void a(int i2, int i3, int i4, int i5, int i6) {
            g(SyncQueueItem.b(2, i2, i3, i4, i5, i6, null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void b(int i2, int i3) {
            f(SyncQueueItem.a(3, i2, i3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void c(int i2) {
            g(SyncQueueItem.c(1, i2, null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void d(TileList.Tile tile) {
            f(SyncQueueItem.c(4, 0, tile));
        }
    }

    static class MessageQueue {

        /* renamed from: a, reason: collision with root package name */
        private SyncQueueItem f5131a;

        synchronized SyncQueueItem a() {
            SyncQueueItem syncQueueItem = this.f5131a;
            if (syncQueueItem == null) {
                return null;
            }
            this.f5131a = syncQueueItem.f5134a;
            return syncQueueItem;
        }

        synchronized void b(int i2) {
            SyncQueueItem syncQueueItem;
            while (true) {
                try {
                    syncQueueItem = this.f5131a;
                    if (syncQueueItem == null || syncQueueItem.f5135b != i2) {
                        break;
                    }
                    this.f5131a = syncQueueItem.f5134a;
                    syncQueueItem.d();
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (syncQueueItem != null) {
                SyncQueueItem syncQueueItem2 = syncQueueItem.f5134a;
                while (syncQueueItem2 != null) {
                    SyncQueueItem syncQueueItem3 = syncQueueItem2.f5134a;
                    if (syncQueueItem2.f5135b == i2) {
                        syncQueueItem.f5134a = syncQueueItem3;
                        syncQueueItem2.d();
                    } else {
                        syncQueueItem = syncQueueItem2;
                    }
                    syncQueueItem2 = syncQueueItem3;
                }
            }
        }

        synchronized void c(SyncQueueItem syncQueueItem) {
            SyncQueueItem syncQueueItem2 = this.f5131a;
            if (syncQueueItem2 == null) {
                this.f5131a = syncQueueItem;
                return;
            }
            while (true) {
                SyncQueueItem syncQueueItem3 = syncQueueItem2.f5134a;
                if (syncQueueItem3 == null) {
                    syncQueueItem2.f5134a = syncQueueItem;
                    return;
                }
                syncQueueItem2 = syncQueueItem3;
            }
        }

        synchronized void d(SyncQueueItem syncQueueItem) {
            syncQueueItem.f5134a = this.f5131a;
            this.f5131a = syncQueueItem;
        }
    }

    static class SyncQueueItem {

        /* renamed from: i, reason: collision with root package name */
        private static SyncQueueItem f5132i;

        /* renamed from: j, reason: collision with root package name */
        private static final Object f5133j = new Object();

        /* renamed from: a, reason: collision with root package name */
        SyncQueueItem f5134a;

        /* renamed from: b, reason: collision with root package name */
        public int f5135b;

        /* renamed from: c, reason: collision with root package name */
        public int f5136c;

        /* renamed from: d, reason: collision with root package name */
        public int f5137d;

        /* renamed from: e, reason: collision with root package name */
        public int f5138e;

        /* renamed from: f, reason: collision with root package name */
        public int f5139f;

        /* renamed from: g, reason: collision with root package name */
        public int f5140g;

        /* renamed from: h, reason: collision with root package name */
        public Object f5141h;

        SyncQueueItem() {
        }

        static SyncQueueItem a(int i2, int i3, int i4) {
            return b(i2, i3, i4, 0, 0, 0, null);
        }

        static SyncQueueItem b(int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
            SyncQueueItem syncQueueItem;
            synchronized (f5133j) {
                try {
                    syncQueueItem = f5132i;
                    if (syncQueueItem == null) {
                        syncQueueItem = new SyncQueueItem();
                    } else {
                        f5132i = syncQueueItem.f5134a;
                        syncQueueItem.f5134a = null;
                    }
                    syncQueueItem.f5135b = i2;
                    syncQueueItem.f5136c = i3;
                    syncQueueItem.f5137d = i4;
                    syncQueueItem.f5138e = i5;
                    syncQueueItem.f5139f = i6;
                    syncQueueItem.f5140g = i7;
                    syncQueueItem.f5141h = obj;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return syncQueueItem;
        }

        static SyncQueueItem c(int i2, int i3, Object obj) {
            return b(i2, i3, 0, 0, 0, 0, obj);
        }

        void d() {
            this.f5134a = null;
            this.f5140g = 0;
            this.f5139f = 0;
            this.f5138e = 0;
            this.f5137d = 0;
            this.f5136c = 0;
            this.f5135b = 0;
            this.f5141h = null;
            synchronized (f5133j) {
                try {
                    SyncQueueItem syncQueueItem = f5132i;
                    if (syncQueueItem != null) {
                        this.f5134a = syncQueueItem;
                    }
                    f5132i = this;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
