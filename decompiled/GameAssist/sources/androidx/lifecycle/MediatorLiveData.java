package androidx.lifecycle;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class MediatorLiveData<T> extends MutableLiveData<T> {

    /* renamed from: l, reason: collision with root package name */
    private SafeIterableMap f4334l;

    private static class Source<V> implements Observer<V> {

        /* renamed from: a, reason: collision with root package name */
        final LiveData f4335a;

        /* renamed from: b, reason: collision with root package name */
        final Observer f4336b;

        /* renamed from: c, reason: collision with root package name */
        int f4337c = -1;

        Source(LiveData liveData, Observer observer) {
            this.f4335a = liveData;
            this.f4336b = observer;
        }

        @Override // androidx.lifecycle.Observer
        public void a(Object obj) {
            if (this.f4337c != this.f4335a.g()) {
                this.f4337c = this.f4335a.g();
                this.f4336b.a(obj);
            }
        }

        void b() {
            this.f4335a.j(this);
        }

        void c() {
            this.f4335a.n(this);
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void k() {
        Iterator it = this.f4334l.iterator();
        while (it.hasNext()) {
            ((Source) ((Map.Entry) it.next()).getValue()).b();
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void l() {
        Iterator it = this.f4334l.iterator();
        while (it.hasNext()) {
            ((Source) ((Map.Entry) it.next()).getValue()).c();
        }
    }

    public void p(LiveData liveData, Observer observer) {
        if (liveData == null) {
            throw new NullPointerException("source cannot be null");
        }
        Source source = new Source(liveData, observer);
        Source source2 = (Source) this.f4334l.i(liveData, source);
        if (source2 != null && source2.f4336b != observer) {
            throw new IllegalArgumentException("This source was already added with the different observer");
        }
        if (source2 == null && h()) {
            source.b();
        }
    }

    public void q(LiveData liveData) {
        Source source = (Source) this.f4334l.j(liveData);
        if (source != null) {
            source.c();
        }
    }
}
