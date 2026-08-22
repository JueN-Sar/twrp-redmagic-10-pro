package com.zte.mifavor.androidx.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DefineItemAnimator extends SimpleItemAnimator {

    /* renamed from: s, reason: collision with root package name */
    private static TimeInterpolator f17123s;

    /* renamed from: h, reason: collision with root package name */
    private ArrayList f17124h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private ArrayList f17125i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private ArrayList f17126j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private ArrayList f17127k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    ArrayList f17128l = new ArrayList();

    /* renamed from: m, reason: collision with root package name */
    ArrayList f17129m = new ArrayList();

    /* renamed from: n, reason: collision with root package name */
    ArrayList f17130n = new ArrayList();

    /* renamed from: o, reason: collision with root package name */
    ArrayList f17131o = new ArrayList();

    /* renamed from: p, reason: collision with root package name */
    ArrayList f17132p = new ArrayList();

    /* renamed from: q, reason: collision with root package name */
    ArrayList f17133q = new ArrayList();

    /* renamed from: r, reason: collision with root package name */
    ArrayList f17134r = new ArrayList();

    private static class MoveInfo {

        /* renamed from: a, reason: collision with root package name */
        public RecyclerView.ViewHolder f17169a;

        /* renamed from: b, reason: collision with root package name */
        public int f17170b;

        /* renamed from: c, reason: collision with root package name */
        public int f17171c;

        /* renamed from: d, reason: collision with root package name */
        public int f17172d;

        /* renamed from: e, reason: collision with root package name */
        public int f17173e;

        MoveInfo(RecyclerView.ViewHolder viewHolder, int i2, int i3, int i4, int i5) {
            this.f17169a = viewHolder;
            this.f17170b = i2;
            this.f17171c = i3;
            this.f17172d = i4;
            this.f17173e = i5;
        }
    }

    private void Z(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.f5252a;
        final ViewPropertyAnimator animate = view.animate();
        this.f17133q.add(viewHolder);
        animate.setDuration(o()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                view.setAlpha(1.0f);
                DefineItemAnimator.this.L(viewHolder);
                DefineItemAnimator.this.f17133q.remove(viewHolder);
                DefineItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefineItemAnimator.this.M(viewHolder);
            }
        }).start();
    }

    private void c0(List list, RecyclerView.ViewHolder viewHolder) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (e0(changeInfo, viewHolder) && changeInfo.f17163a == null && changeInfo.f17164b == null) {
                list.remove(changeInfo);
            }
        }
    }

    private void d0(ChangeInfo changeInfo) {
        RecyclerView.ViewHolder viewHolder = changeInfo.f17163a;
        if (viewHolder != null) {
            e0(changeInfo, viewHolder);
        }
        RecyclerView.ViewHolder viewHolder2 = changeInfo.f17164b;
        if (viewHolder2 != null) {
            e0(changeInfo, viewHolder2);
        }
    }

    private boolean e0(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        boolean z = false;
        if (changeInfo.f17164b == viewHolder) {
            changeInfo.f17164b = null;
        } else {
            if (changeInfo.f17163a != viewHolder) {
                return false;
            }
            changeInfo.f17163a = null;
            z = true;
        }
        viewHolder.f5252a.setAlpha(1.0f);
        viewHolder.f5252a.setTranslationX(0.0f);
        viewHolder.f5252a.setTranslationY(0.0f);
        H(viewHolder, z);
        return true;
    }

    private void f0(RecyclerView.ViewHolder viewHolder) {
        if (f17123s == null) {
            f17123s = new ValueAnimator().getInterpolator();
        }
        viewHolder.f5252a.animate().setInterpolator(f17123s);
        j(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean B(RecyclerView.ViewHolder viewHolder) {
        f0(viewHolder);
        viewHolder.f5252a.setAlpha(0.0f);
        this.f17125i.add(viewHolder);
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
        this.f17127k.add(new ChangeInfo(viewHolder, viewHolder2, i2, i3, i4, i5));
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
        this.f17126j.add(new MoveInfo(viewHolder, translationX, translationY, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public boolean E(RecyclerView.ViewHolder viewHolder) {
        f0(viewHolder);
        this.f17124h.add(viewHolder);
        return true;
    }

    void W(final RecyclerView.ViewHolder viewHolder) {
        final View view = viewHolder.f5252a;
        final ViewPropertyAnimator animate = view.animate();
        this.f17131o.add(viewHolder);
        animate.alpha(1.0f).setDuration(l()).setListener(new AnimatorListenerAdapter() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                view.setAlpha(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animate.setListener(null);
                DefineItemAnimator.this.F(viewHolder);
                DefineItemAnimator.this.f17131o.remove(viewHolder);
                DefineItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefineItemAnimator.this.G(viewHolder);
            }
        }).start();
    }

    void X(final ChangeInfo changeInfo) {
        RecyclerView.ViewHolder viewHolder = changeInfo.f17163a;
        final View view = viewHolder == null ? null : viewHolder.f5252a;
        RecyclerView.ViewHolder viewHolder2 = changeInfo.f17164b;
        final View view2 = viewHolder2 != null ? viewHolder2.f5252a : null;
        if (view != null) {
            final ViewPropertyAnimator duration = view.animate().setDuration(m());
            this.f17134r.add(changeInfo.f17163a);
            duration.translationX(changeInfo.f17167e - changeInfo.f17165c);
            duration.translationY(changeInfo.f17168f - changeInfo.f17166d);
            duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.7
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    duration.setListener(null);
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    DefineItemAnimator.this.H(changeInfo.f17163a, true);
                    DefineItemAnimator.this.f17134r.remove(changeInfo.f17163a);
                    DefineItemAnimator.this.b0();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DefineItemAnimator.this.I(changeInfo.f17163a, true);
                }
            }).start();
        }
        if (view2 != null) {
            final ViewPropertyAnimator animate = view2.animate();
            this.f17134r.add(changeInfo.f17164b);
            animate.translationX(0.0f).translationY(0.0f).setDuration(m()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    animate.setListener(null);
                    view2.setAlpha(1.0f);
                    view2.setTranslationX(0.0f);
                    view2.setTranslationY(0.0f);
                    DefineItemAnimator.this.H(changeInfo.f17164b, false);
                    DefineItemAnimator.this.f17134r.remove(changeInfo.f17164b);
                    DefineItemAnimator.this.b0();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    DefineItemAnimator.this.I(changeInfo.f17164b, false);
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
        this.f17132p.add(viewHolder);
        animate.setDuration(n()).setListener(new AnimatorListenerAdapter() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.6
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
                DefineItemAnimator.this.J(viewHolder);
                DefineItemAnimator.this.f17132p.remove(viewHolder);
                DefineItemAnimator.this.b0();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                DefineItemAnimator.this.K(viewHolder);
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
        int size = this.f17126j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((MoveInfo) this.f17126j.get(size)).f17169a == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                J(viewHolder);
                this.f17126j.remove(size);
            }
        }
        c0(this.f17127k, viewHolder);
        if (this.f17124h.remove(viewHolder)) {
            view.setAlpha(1.0f);
            L(viewHolder);
        }
        if (this.f17125i.remove(viewHolder)) {
            view.setAlpha(1.0f);
            F(viewHolder);
        }
        for (int size2 = this.f17130n.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.f17130n.get(size2);
            c0(arrayList, viewHolder);
            if (arrayList.isEmpty()) {
                this.f17130n.remove(size2);
            }
        }
        for (int size3 = this.f17129m.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.f17129m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((MoveInfo) arrayList2.get(size4)).f17169a == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    J(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f17129m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.f17128l.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.f17128l.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                F(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.f17128l.remove(size5);
                }
            }
        }
        this.f17133q.remove(viewHolder);
        this.f17131o.remove(viewHolder);
        this.f17134r.remove(viewHolder);
        this.f17132p.remove(viewHolder);
        b0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void k() {
        int size = this.f17126j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = (MoveInfo) this.f17126j.get(size);
            View view = moveInfo.f17169a.f5252a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            J(moveInfo.f17169a);
            this.f17126j.remove(size);
        }
        for (int size2 = this.f17124h.size() - 1; size2 >= 0; size2--) {
            L((RecyclerView.ViewHolder) this.f17124h.get(size2));
            this.f17124h.remove(size2);
        }
        int size3 = this.f17125i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.f17125i.get(size3);
            viewHolder.f5252a.setAlpha(1.0f);
            F(viewHolder);
            this.f17125i.remove(size3);
        }
        for (int size4 = this.f17127k.size() - 1; size4 >= 0; size4--) {
            d0((ChangeInfo) this.f17127k.get(size4));
        }
        this.f17127k.clear();
        if (p()) {
            for (int size5 = this.f17129m.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.f17129m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.f17169a.f5252a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    J(moveInfo2.f17169a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f17129m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f17128l.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.f17128l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.ViewHolder viewHolder2 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder2.f5252a.setAlpha(1.0f);
                    F(viewHolder2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f17128l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f17130n.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.f17130n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    d0((ChangeInfo) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f17130n.remove(arrayList3);
                    }
                }
            }
            a0(this.f17133q);
            a0(this.f17132p);
            a0(this.f17131o);
            a0(this.f17134r);
            i();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean p() {
        return (this.f17125i.isEmpty() && this.f17127k.isEmpty() && this.f17126j.isEmpty() && this.f17124h.isEmpty() && this.f17132p.isEmpty() && this.f17133q.isEmpty() && this.f17131o.isEmpty() && this.f17134r.isEmpty() && this.f17129m.isEmpty() && this.f17128l.isEmpty() && this.f17130n.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void v() {
        boolean z = !this.f17124h.isEmpty();
        boolean z2 = !this.f17126j.isEmpty();
        boolean z3 = !this.f17127k.isEmpty();
        boolean z4 = !this.f17125i.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator it = this.f17124h.iterator();
            while (it.hasNext()) {
                Z((RecyclerView.ViewHolder) it.next());
            }
            this.f17124h.clear();
            if (z2) {
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f17126j);
                this.f17129m.add(arrayList);
                this.f17126j.clear();
                Runnable runnable = new Runnable() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            MoveInfo moveInfo = (MoveInfo) it2.next();
                            DefineItemAnimator.this.Y(moveInfo.f17169a, moveInfo.f17170b, moveInfo.f17171c, moveInfo.f17172d, moveInfo.f17173e);
                        }
                        arrayList.clear();
                        DefineItemAnimator.this.f17129m.remove(arrayList);
                    }
                };
                if (z) {
                    ViewCompat.b0(((MoveInfo) arrayList.get(0)).f17169a.f5252a, runnable, o());
                } else {
                    runnable.run();
                }
            }
            if (z3) {
                final ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.f17127k);
                this.f17130n.add(arrayList2);
                this.f17127k.clear();
                Runnable runnable2 = new Runnable() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            DefineItemAnimator.this.X((ChangeInfo) it2.next());
                        }
                        arrayList2.clear();
                        DefineItemAnimator.this.f17130n.remove(arrayList2);
                    }
                };
                if (z) {
                    ViewCompat.b0(((ChangeInfo) arrayList2.get(0)).f17163a.f5252a, runnable2, o());
                } else {
                    runnable2.run();
                }
            }
            if (z4) {
                final ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.f17125i);
                this.f17128l.add(arrayList3);
                this.f17125i.clear();
                Runnable runnable3 = new Runnable() { // from class: com.zte.mifavor.androidx.widget.DefineItemAnimator.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            DefineItemAnimator.this.W((RecyclerView.ViewHolder) it2.next());
                        }
                        arrayList3.clear();
                        DefineItemAnimator.this.f17128l.remove(arrayList3);
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
        public RecyclerView.ViewHolder f17163a;

        /* renamed from: b, reason: collision with root package name */
        public RecyclerView.ViewHolder f17164b;

        /* renamed from: c, reason: collision with root package name */
        public int f17165c;

        /* renamed from: d, reason: collision with root package name */
        public int f17166d;

        /* renamed from: e, reason: collision with root package name */
        public int f17167e;

        /* renamed from: f, reason: collision with root package name */
        public int f17168f;

        private ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            this.f17163a = viewHolder;
            this.f17164b = viewHolder2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f17163a + ", newHolder=" + this.f17164b + ", fromX=" + this.f17165c + ", fromY=" + this.f17166d + ", toX=" + this.f17167e + ", toY=" + this.f17168f + '}';
        }

        ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4, int i5) {
            this(viewHolder, viewHolder2);
            this.f17165c = i2;
            this.f17166d = i3;
            this.f17167e = i4;
            this.f17168f = i5;
        }
    }
}
