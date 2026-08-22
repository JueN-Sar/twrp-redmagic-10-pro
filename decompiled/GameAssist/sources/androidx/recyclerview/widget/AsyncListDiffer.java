package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.widget.DiffUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class AsyncListDiffer<T> {

    /* renamed from: h, reason: collision with root package name */
    private static final Executor f4874h = new MainThreadExecutor();

    /* renamed from: a, reason: collision with root package name */
    private final ListUpdateCallback f4875a;

    /* renamed from: b, reason: collision with root package name */
    final AsyncDifferConfig f4876b;

    /* renamed from: c, reason: collision with root package name */
    Executor f4877c;

    /* renamed from: d, reason: collision with root package name */
    private final List f4878d;

    /* renamed from: e, reason: collision with root package name */
    private List f4879e;

    /* renamed from: f, reason: collision with root package name */
    private List f4880f;

    /* renamed from: g, reason: collision with root package name */
    int f4881g;

    /* renamed from: androidx.recyclerview.widget.AsyncListDiffer$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f4882c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ List f4883h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f4884i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ Runnable f4885j;

        /* renamed from: k, reason: collision with root package name */
        final /* synthetic */ AsyncListDiffer f4886k;

        @Override // java.lang.Runnable
        public void run() {
            final DiffUtil.DiffResult a2 = DiffUtil.a(new DiffUtil.Callback() { // from class: androidx.recyclerview.widget.AsyncListDiffer.1.1
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean a(int i2, int i3) {
                    Object obj = AnonymousClass1.this.f4882c.get(i2);
                    Object obj2 = AnonymousClass1.this.f4883h.get(i3);
                    if (obj != null && obj2 != null) {
                        return AnonymousClass1.this.f4886k.f4876b.a().a(obj, obj2);
                    }
                    if (obj == null && obj2 == null) {
                        return true;
                    }
                    throw new AssertionError();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean b(int i2, int i3) {
                    Object obj = AnonymousClass1.this.f4882c.get(i2);
                    Object obj2 = AnonymousClass1.this.f4883h.get(i3);
                    return (obj == null || obj2 == null) ? obj == null && obj2 == null : AnonymousClass1.this.f4886k.f4876b.a().b(obj, obj2);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public Object c(int i2, int i3) {
                    Object obj = AnonymousClass1.this.f4882c.get(i2);
                    Object obj2 = AnonymousClass1.this.f4883h.get(i3);
                    if (obj == null || obj2 == null) {
                        throw new AssertionError();
                    }
                    return AnonymousClass1.this.f4886k.f4876b.a().c(obj, obj2);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int d() {
                    return AnonymousClass1.this.f4883h.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int e() {
                    return AnonymousClass1.this.f4882c.size();
                }
            });
            this.f4886k.f4877c.execute(new Runnable() { // from class: androidx.recyclerview.widget.AsyncListDiffer.1.2
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                    AsyncListDiffer asyncListDiffer = anonymousClass1.f4886k;
                    if (asyncListDiffer.f4881g == anonymousClass1.f4884i) {
                        asyncListDiffer.b(anonymousClass1.f4883h, a2, anonymousClass1.f4885j);
                    }
                }
            });
        }
    }

    public interface ListListener<T> {
        void a(List list, List list2);
    }

    private static class MainThreadExecutor implements Executor {

        /* renamed from: c, reason: collision with root package name */
        final Handler f4890c = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f4890c.post(runnable);
        }
    }

    private void c(List list, Runnable runnable) {
        Iterator it = this.f4878d.iterator();
        while (it.hasNext()) {
            ((ListListener) it.next()).a(list, this.f4880f);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public List a() {
        return this.f4880f;
    }

    void b(List list, DiffUtil.DiffResult diffResult, Runnable runnable) {
        List list2 = this.f4880f;
        this.f4879e = list;
        this.f4880f = Collections.unmodifiableList(list);
        diffResult.d(this.f4875a);
        c(list2, runnable);
    }
}
