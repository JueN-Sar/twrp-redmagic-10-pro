package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo
/* loaded from: classes.dex */
public class ViewPropertyAnimatorCompatSet {

    /* renamed from: c, reason: collision with root package name */
    private Interpolator f472c;

    /* renamed from: d, reason: collision with root package name */
    ViewPropertyAnimatorListener f473d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f474e;

    /* renamed from: b, reason: collision with root package name */
    private long f471b = -1;

    /* renamed from: f, reason: collision with root package name */
    private final ViewPropertyAnimatorListenerAdapter f475f = new ViewPropertyAnimatorListenerAdapter() { // from class: androidx.appcompat.view.ViewPropertyAnimatorCompatSet.1

        /* renamed from: a, reason: collision with root package name */
        private boolean f476a = false;

        /* renamed from: b, reason: collision with root package name */
        private int f477b = 0;

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void b(View view) {
            int i2 = this.f477b + 1;
            this.f477b = i2;
            if (i2 == ViewPropertyAnimatorCompatSet.this.f470a.size()) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.f473d;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.b(null);
                }
                d();
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void c(View view) {
            if (this.f476a) {
                return;
            }
            this.f476a = true;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.f473d;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.c(null);
            }
        }

        void d() {
            this.f477b = 0;
            this.f476a = false;
            ViewPropertyAnimatorCompatSet.this.b();
        }
    };

    /* renamed from: a, reason: collision with root package name */
    final ArrayList f470a = new ArrayList();

    public void a() {
        if (this.f474e) {
            Iterator it = this.f470a.iterator();
            while (it.hasNext()) {
                ((ViewPropertyAnimatorCompat) it.next()).c();
            }
            this.f474e = false;
        }
    }

    void b() {
        this.f474e = false;
    }

    public ViewPropertyAnimatorCompatSet c(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.f474e) {
            this.f470a.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet d(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.f470a.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.j(viewPropertyAnimatorCompat.d());
        this.f470a.add(viewPropertyAnimatorCompat2);
        return this;
    }

    public ViewPropertyAnimatorCompatSet e(long j2) {
        if (!this.f474e) {
            this.f471b = j2;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet f(Interpolator interpolator) {
        if (!this.f474e) {
            this.f472c = interpolator;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet g(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.f474e) {
            this.f473d = viewPropertyAnimatorListener;
        }
        return this;
    }

    public void h() {
        if (this.f474e) {
            return;
        }
        Iterator it = this.f470a.iterator();
        while (it.hasNext()) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat) it.next();
            long j2 = this.f471b;
            if (j2 >= 0) {
                viewPropertyAnimatorCompat.f(j2);
            }
            Interpolator interpolator = this.f472c;
            if (interpolator != null) {
                viewPropertyAnimatorCompat.g(interpolator);
            }
            if (this.f473d != null) {
                viewPropertyAnimatorCompat.h(this.f475f);
            }
            viewPropertyAnimatorCompat.l();
        }
        this.f474e = true;
    }
}
