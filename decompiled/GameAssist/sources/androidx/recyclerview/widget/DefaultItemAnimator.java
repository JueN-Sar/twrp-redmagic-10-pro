package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DefaultItemAnimator extends SimpleItemAnimator {

    /* renamed from: s, reason: collision with root package name */
    private static TimeInterpolator f4925s;

    /* renamed from: h, reason: collision with root package name */
    private ArrayList f4926h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f4927i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private ArrayList f4928j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private ArrayList f4929k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    ArrayList f4930l = new ArrayList();

    /* renamed from: m, reason: collision with root package name */
    ArrayList f4931m = new ArrayList();

    /* renamed from: n, reason: collision with root package name */
    ArrayList f4932n = new ArrayList();

    /* renamed from: o, reason: collision with root package name */
    ArrayList f4933o = new ArrayList();

    /* renamed from: p, reason: collision with root package name */
    ArrayList f4934p = new ArrayList();

    /* renamed from: q, reason: collision with root package name */
    ArrayList f4935q = new ArrayList();

    /* renamed from: r, reason: collision with root package name */
    ArrayList f4936r = new ArrayList();

    private static class MoveInfo {

        /* renamed from: a, reason: collision with root package name */
        public RecyclerView.ViewHolder f4971a;

        /* renamed from: b, reason: collision with root package name */
        public int f4972b;

        /* renamed from: c, reason: collision with root package name */
        public int f4973c;

        /* renamed from: d, reason: collision with root package name */
        public int f4974d;

        /* renamed from: e, reason: collision with root package name */
        public int f4975e;

        MoveInfo(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
            this.f4971a = viewHolder;
            this.f4972b = i2;
            this.f4973c = i3;
            this.f4974d = i4;
            this.f4975e = i5;
        }
    }

    private void Z(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.f5252a;
        final ViewPropertyAnimator animate = view.animate();
        this.f4935q.add(viewHolder);
        animate.setDuration(o()).alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                view.setAlpha(1.0f);
                DefaultItemAnimator.this.L(viewHolder);
                DefaultItemAnimator.this.f4935q.remove(viewHolder);
                DefaultItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.M(viewHolder);
            }
        }).start();
    }

    private void c0(List list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (e0(changeInfo, viewHolder) && changeInfo.f4965a == null && changeInfo.f4966b == null) {
                list.remove(changeInfo);
            }
        }
    }

    private void d0(ChangeInfo changeInfo) {
        RecyclerView.ViewHolder viewHolder = changeInfo.f4965a;
        if (viewHolder != null) {
            e0(changeInfo, viewHolder);
        }
        RecyclerView.ViewHolder viewHolder2 = changeInfo.f4966b;
        if (viewHolder2 != null) {
            e0(changeInfo, viewHolder2);
        }
    }

    private boolean e0(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        boolean z = false;
        if (changeInfo.f4966b == viewHolder) {
            changeInfo.f4966b = null;
        } else {
            if (changeInfo.f4965a != viewHolder) {
                return false;
            }
            changeInfo.f4965a = null;
            z = true;
        }
        viewHolder.f5252a.setAlpha(1.0f);
        viewHolder.f5252a.setTranslationX(0.0f);
        viewHolder.f5252a.setTranslationY(0.0f);
        H(viewHolder, z);
        return true;
    }

    private void f0(RecyclerView.ViewHolder viewHolder) {
        if (f4925s == null) {
            f4925s = new ValueAnimator().getInterpolator();
        }
        viewHolder.f5252a.animate().setInterpolator(f4925s);
        j(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean B(RecyclerView.ViewHolder viewHolder) {
        f0(viewHolder);
        viewHolder.f5252a.setAlpha(0.0f);
        this.f4927i.add(viewHolder);
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean C(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4, int i5) {
        if (viewHolder == viewHolder2) {
            return D(viewHolder, i2, i3, i4, i5);
        }
        float translationX = viewHolder.f5252a.getTranslationX();
        float translationY = viewHolder.f5252a.getTranslationY();
        float alpha = viewHolder.f5252a.getAlpha();
        f0(viewHolder);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        viewHolder.f5252a.setTranslationX(translationX);
        viewHolder.f5252a.setTranslationY(translationY);
        viewHolder.f5252a.setAlpha(alpha);
        if (viewHolder2 != null) {
            f0(viewHolder2);
            viewHolder2.f5252a.setTranslationX(-i6);
            viewHolder2.f5252a.setTranslationY(-i7);
            viewHolder2.f5252a.setAlpha(0.0f);
        }
        this.f4929k.add(new ChangeInfo(viewHolder, viewHolder2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean D(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        View view = viewHolder.f5252a;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) viewHolder.f5252a.getTranslationY());
        f0(viewHolder);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            J(viewHolder);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.f4928j.add(new MoveInfo(viewHolder, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean E(RecyclerView.ViewHolder viewHolder) {
        f0(viewHolder);
        this.f4926h.add(viewHolder);
        return true;
    }

    void W(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.f5252a;
        final ViewPropertyAnimator animate = view.animate();
        this.f4933o.add(viewHolder);
        animate.alpha(1.0f).setDuration(l()).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                DefaultItemAnimator.this.F(viewHolder);
                DefaultItemAnimator.this.f4933o.remove(viewHolder);
                DefaultItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.G(viewHolder);
            }
        }).start();
    }

    void X(final ChangeInfo changeInfo) {
        RecyclerView.ViewHolder viewHolder = changeInfo.f4965a;
        final View view = viewHolder == null ? null : viewHolder.f5252a;
        RecyclerView.ViewHolder viewHolder2 = changeInfo.f4966b;
        final View view2 = viewHolder2 != null ? viewHolder2.f5252a : null;
        if (view != null) {
            final ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.f4936r.add(changeInfo.f4965a);
            duration.translationX(changeInfo.f4969e - changeInfo.f4967c);
            duration.translationY(changeInfo.f4970f - changeInfo.f4968d);
            duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    duration.setListener(null);
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    DefaultItemAnimator.this.H(changeInfo.f4965a, true);
                    DefaultItemAnimator.this.f4936r.remove(changeInfo.f4965a);
                    DefaultItemAnimator.this.b0();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DefaultItemAnimator.this.I(changeInfo.f4965a, true);
                }
            }).start();
        }
        if (view2 != null) {
            final ViewPropertyAnimator animate = view2.animate();
            this.f4936r.add(changeInfo.f4966b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    animate.setListener(null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    DefaultItemAnimator.this.H(changeInfo.f4966b, false);
                    DefaultItemAnimator.this.f4936r.remove(changeInfo.f4966b);
                    DefaultItemAnimator.this.b0();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DefaultItemAnimator.this.I(changeInfo.f4966b, false);
                }
            }).start();
        }
    }

    void Y(final RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
        final View view = viewHolder.f5252a;
        final int i6 = i4 - i2;
        final int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator animate = view.animate();
        this.f4934p.add(viewHolder);
        animate.setDuration(n()).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (i6 != 0) {
                    view.setTranslationX(0.0f);
                }
                if (i7 != 0) {
                    view.setTranslationY(0.0f);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                DefaultItemAnimator.this.J(viewHolder);
                DefaultItemAnimator.this.f4934p.remove(viewHolder);
                DefaultItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefaultItemAnimator.this.K(viewHolder);
            }
        }).start();
    }

    void a0(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((RecyclerView.ViewHolder) list.get(size)).f5252a.animate().cancel();
        }
    }

    void b0() {
        if (p()) {
            return;
        }
        i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean g(RecyclerView.ViewHolder viewHolder, List list) {
        return !list.isEmpty() || super.g(viewHolder, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void j(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.f5252a;
        view.animate().cancel();
        int size = this.f4928j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((MoveInfo) this.f4928j.get(size)).f4971a == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                J(viewHolder);
                this.f4928j.remove(size);
            }
        }
        c0(this.f4929k, viewHolder);
        if (this.f4926h.remove(viewHolder)) {
            view.setAlpha(1.0f);
            L(viewHolder);
        }
        if (this.f4927i.remove(viewHolder)) {
            view.setAlpha(1.0f);
            F(viewHolder);
        }
        for (int size2 = this.f4932n.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.f4932n.get(size2);
            c0(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.f4932n.remove(size2);
            }
        }
        for (int size3 = this.f4931m.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.f4931m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((MoveInfo) arrayList2.get(size4)).f4971a == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    J(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f4931m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f4930l.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.f4930l.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                F(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.f4930l.remove(size5);
                }
            }
        }
        this.f4935q.remove(viewHolder);
        this.f4933o.remove(viewHolder);
        this.f4936r.remove(viewHolder);
        this.f4934p.remove(viewHolder);
        b0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void k() {
        int size = this.f4928j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = (MoveInfo) this.f4928j.get(size);
            View view = moveInfo.f4971a.f5252a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            J(moveInfo.f4971a);
            this.f4928j.remove(size);
        }
        for (int size2 = this.f4926h.size() - 1; size2 >= 0; size2--) {
            L((RecyclerView.ViewHolder) this.f4926h.get(size2));
            this.f4926h.remove(size2);
        }
        int size3 = this.f4927i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.f4927i.get(size3);
            viewHolder.f5252a.setAlpha(1.0f);
            F(viewHolder);
            this.f4927i.remove(size3);
        }
        for (int size4 = this.f4929k.size() - 1; size4 >= 0; size4--) {
            d0((ChangeInfo) this.f4929k.get(size4));
        }
        this.f4929k.clear();
        if (p()) {
            for (int size5 = this.f4931m.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.f4931m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.f4971a.f5252a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    J(moveInfo2.f4971a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f4931m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f4930l.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.f4930l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder2.f5252a.setAlpha(1.0f);
                    F(viewHolder2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f4930l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f4932n.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.f4932n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    d0((ChangeInfo) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f4932n.remove(arrayList3);
                    }
                }
            }
            a0(this.f4935q);
            a0(this.f4934p);
            a0(this.f4933o);
            a0(this.f4936r);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean p() {
        return (this.f4927i.isEmpty() && this.f4929k.isEmpty() && this.f4928j.isEmpty() && this.f4926h.isEmpty() && this.f4934p.isEmpty() && this.f4935q.isEmpty() && this.f4933o.isEmpty() && this.f4936r.isEmpty() && this.f4931m.isEmpty() && this.f4930l.isEmpty() && this.f4932n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void v() {
        boolean z = !this.f4926h.isEmpty();
        boolean z2 = !this.f4928j.isEmpty();
        boolean z3 = !this.f4929k.isEmpty();
        boolean z4 = !this.f4927i.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator it = this.f4926h.iterator();
            while (it.hasNext()) {
                Z((RecyclerView.ViewHolder) it.next());
            }
            this.f4926h.clear();
            if (z2) {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f4928j);
                this.f4931m.add(arrayList);
                this.f4928j.clear();
                Runnable runnable = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo) it2.next();
                            DefaultItemAnimator.this.Y(moveInfo.f4971a, moveInfo.f4972b, moveInfo.f4973c, moveInfo.f4974d, moveInfo.f4975e);
                        }
                        arrayList.clear();
                        DefaultItemAnimator.this.f4931m.remove(arrayList);
                    }
                };
                if (z) {
                    ViewCompat.b0(((MoveInfo) arrayList.get(0)).f4971a.f5252a, runnable, o());
                } else {
                    runnable.run();
                }
            }
            if (z3) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.f4929k);
                this.f4932n.add(arrayList2);
                this.f4929k.clear();
                Runnable runnable2 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            DefaultItemAnimator.this.X((ChangeInfo) it2.next());
                        }
                        arrayList2.clear();
                        DefaultItemAnimator.this.f4932n.remove(arrayList2);
                    }
                };
                if (z) {
                    ViewCompat.b0(((ChangeInfo) arrayList2.get(0)).f4965a.f5252a, runnable2, o());
                } else {
                    runnable2.run();
                }
            }
            if (z4) {
                final ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.f4927i);
                this.f4930l.add(arrayList3);
                this.f4927i.clear();
                Runnable runnable3 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            DefaultItemAnimator.this.W((RecyclerView.ViewHolder) it2.next());
                        }
                        arrayList3.clear();
                        DefaultItemAnimator.this.f4930l.remove(arrayList3);
                    }
                };
                if (z || z2 || z3) {
                    ViewCompat.b0(((RecyclerView.ViewHolder) arrayList3.get(0)).f5252a, runnable3, (z ? o() : 0L) + Math.max(z2 ? n() : 0L, z3 ? m() : 0L));
                } else {
                    runnable3.run();
                }
            }
        }
    }

    private static class ChangeInfo {

        /* renamed from: a, reason: collision with root package name */
        public RecyclerView.ViewHolder f4965a;

        /* renamed from: b, reason: collision with root package name */
        public RecyclerView.ViewHolder f4966b;

        /* renamed from: c, reason: collision with root package name */
        public int f4967c;

        /* renamed from: d, reason: collision with root package name */
        public int f4968d;

        /* renamed from: e, reason: collision with root package name */
        public int f4969e;

        /* renamed from: f, reason: collision with root package name */
        public int f4970f;

        private ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.f4965a = viewHolder;
            this.f4966b = viewHolder2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f4965a + ", newHolder=" + this.f4966b + ", fromX=" + this.f4967c + ", fromY=" + this.f4968d + ", toX=" + this.f4969e + ", toY=" + this.f4970f + '}';
        }

        ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4, int i5) {
            this(viewHolder, viewHolder2);
            this.f4967c = i2;
            this.f4968d = i3;
            this.f4969e = i4;
            this.f4970f = i5;
        }
    }
}
