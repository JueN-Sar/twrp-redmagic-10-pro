package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class PagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final DataSetObservable f5750a = new DataSetObservable();

    /* renamed from: b, reason: collision with root package name */
    private DataSetObserver f5751b;

    public void a(View view, int i2, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void b(ViewGroup viewGroup, int i2, Object obj) {
        a(viewGroup, i2, obj);
    }

    public void c(View view) {
    }

    public void d(ViewGroup viewGroup) {
        c(viewGroup);
    }

    public abstract int e();

    public int f(Object obj) {
        return -1;
    }

    public CharSequence g(int i2) {
        return null;
    }

    public float h(int i2) {
        return 1.0f;
    }

    public Object i(View view, int i2) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object j(ViewGroup viewGroup, int i2) {
        return i(viewGroup, i2);
    }

    public abstract boolean k(View view, Object obj);

    public void l() {
        synchronized (this) {
            try {
                DataSetObserver dataSetObserver = this.f5751b;
                if (dataSetObserver != null) {
                    dataSetObserver.onChanged();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f5750a.notifyChanged();
    }

    public void m(DataSetObserver dataSetObserver) {
        this.f5750a.registerObserver(dataSetObserver);
    }

    public void n(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable o() {
        return null;
    }

    public void p(View view, int i2, Object obj) {
    }

    public void q(ViewGroup viewGroup, int i2, Object obj) {
        p(viewGroup, i2, obj);
    }

    void r(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f5751b = dataSetObserver;
        }
    }

    public void s(View view) {
    }

    public void t(ViewGroup viewGroup) {
        s(viewGroup);
    }

    public void u(DataSetObserver dataSetObserver) {
        this.f5750a.unregisterObserver(dataSetObserver);
    }
}
