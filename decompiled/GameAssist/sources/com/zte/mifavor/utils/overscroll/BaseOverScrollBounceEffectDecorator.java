package com.zte.mifavor.utils.overscroll;

import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.recyclerview.widget.RecyclerView;
import com.zte.mifavor.androidx.widget.NestedScrollView;
import com.zte.mifavor.androidx.widget.RecyclerView;
import com.zte.mifavor.utils.Utils;
import com.zte.mifavor.utils.overscroll.ListenerStubs;
import com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter;
import com.zte.mifavor.widget.GridView;
import com.zte.mifavor.widget.ISpringView;
import com.zte.mifavor.widget.ListView;
import com.zte.mifavor.widget.ScrollView;

/* loaded from: classes2.dex */
public abstract class BaseOverScrollBounceEffectDecorator implements IOverScrollDecor, View.OnTouchListener {

    /* renamed from: c, reason: collision with root package name */
    protected final float f17464c;

    /* renamed from: h, reason: collision with root package name */
    protected final float f17465h;

    /* renamed from: j, reason: collision with root package name */
    protected final IOverScrollDecoratorAdapter f17467j;

    /* renamed from: k, reason: collision with root package name */
    protected final IdleState f17468k;

    /* renamed from: l, reason: collision with root package name */
    protected final OverScrollingState f17469l;

    /* renamed from: m, reason: collision with root package name */
    protected final BounceBackState f17470m;

    /* renamed from: n, reason: collision with root package name */
    protected IDecoratorState f17471n;

    /* renamed from: q, reason: collision with root package name */
    private final SpringAnimation f17474q;

    /* renamed from: r, reason: collision with root package name */
    private final VelocityTracker f17475r;

    /* renamed from: i, reason: collision with root package name */
    protected final OverScrollStartAttributes f17466i = new OverScrollStartAttributes();

    /* renamed from: o, reason: collision with root package name */
    protected IOverScrollStateListener f17472o = new ListenerStubs.OverScrollStateListenerStub();

    /* renamed from: p, reason: collision with root package name */
    protected IOverScrollUpdateListener f17473p = new ListenerStubs.OverScrollUpdateListenerStub();

    /* renamed from: s, reason: collision with root package name */
    private boolean f17476s = false;
    private boolean t = true;
    private float u = 1.0f;
    private float v = 1.0f;

    protected static abstract class BaseAnimationAttributes {

        /* renamed from: a, reason: collision with root package name */
        public Property f17478a = View.TRANSLATION_Y;

        protected BaseAnimationAttributes() {
        }
    }

    protected static abstract class BaseMotionAttributes {

        /* renamed from: a, reason: collision with root package name */
        public float f17479a;

        /* renamed from: b, reason: collision with root package name */
        public float f17480b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f17481c;

        protected BaseMotionAttributes() {
        }

        protected abstract boolean a(View view, MotionEvent motionEvent);
    }

    protected class BounceBackState implements IDecoratorState {

        /* renamed from: a, reason: collision with root package name */
        protected final float f17482a;

        /* renamed from: b, reason: collision with root package name */
        protected final float f17483b;

        /* renamed from: c, reason: collision with root package name */
        protected final BaseAnimationAttributes f17484c;

        public BounceBackState(float f2) {
            this.f17482a = f2;
            this.f17483b = f2 * 2.0f;
            this.f17484c = BaseOverScrollBounceEffectDecorator.this.q();
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean a(MotionEvent motionEvent) {
            return true;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public int b() {
            return 3;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public void c(IDecoratorState iDecoratorState) {
            try {
                if (BaseOverScrollBounceEffectDecorator.this.f17476s) {
                    BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
                    baseOverScrollBounceEffectDecorator.f17472o.a(baseOverScrollBounceEffectDecorator, iDecoratorState.b(), b());
                    BaseOverScrollBounceEffectDecorator.this.f17475r.computeCurrentVelocity(1000);
                    double translationY = BaseOverScrollBounceEffectDecorator.this.f17467j.c().getTranslationY();
                    BaseOverScrollBounceEffectDecorator.this.f17474q.j((-1.0E-5d >= translationY || translationY >= 1.0E-5d) ? BaseOverScrollBounceEffectDecorator.this.f17475r.getYVelocity() : BaseOverScrollBounceEffectDecorator.this.f17475r.getXVelocity());
                    if (!BaseOverScrollBounceEffectDecorator.this.f17474q.f()) {
                        BaseOverScrollBounceEffectDecorator.this.f17474q.k();
                    }
                    BaseOverScrollBounceEffectDecorator.this.f17475r.clear();
                }
                BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator2 = BaseOverScrollBounceEffectDecorator.this;
                baseOverScrollBounceEffectDecorator2.z(baseOverScrollBounceEffectDecorator2.f17468k);
                BaseOverScrollBounceEffectDecorator.this.u = 1.0f;
                BaseOverScrollBounceEffectDecorator.this.v = 1.0f;
            } catch (Exception e2) {
                Log.e("Z#QScroll-BounceSta", "handleEntryTransition error, e = ", e2);
            }
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean d(MotionEvent motionEvent) {
            return true;
        }
    }

    protected interface IDecoratorState {
        boolean a(MotionEvent motionEvent);

        int b();

        void c(IDecoratorState iDecoratorState);

        boolean d(MotionEvent motionEvent);
    }

    protected class IdleState implements IDecoratorState {

        /* renamed from: a, reason: collision with root package name */
        final BaseMotionAttributes f17486a;

        public IdleState() {
            this.f17486a = BaseOverScrollBounceEffectDecorator.this.r();
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean a(MotionEvent motionEvent) {
            BaseOverScrollBounceEffectDecorator.this.f17476s = false;
            return false;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public int b() {
            return 0;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public void c(IDecoratorState iDecoratorState) {
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
            baseOverScrollBounceEffectDecorator.f17472o.a(baseOverScrollBounceEffectDecorator, iDecoratorState.b(), b());
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean d(MotionEvent motionEvent) {
            View c2 = BaseOverScrollBounceEffectDecorator.this.f17467j.c();
            if (!this.f17486a.a(c2, motionEvent)) {
                return false;
            }
            if (c2 != null) {
                float translationX = c2.getTranslationX();
                float translationY = c2.getTranslationY();
                double d2 = translationX;
                if (-1.0E-4d < d2 && d2 < 1.0E-4d) {
                    double d3 = translationY;
                    if (-1.0E-4d < d3 && d3 < 1.0E-4d) {
                        BaseOverScrollBounceEffectDecorator.this.t = true;
                    }
                }
                BaseOverScrollBounceEffectDecorator.this.t = false;
            }
            if (!(BaseOverScrollBounceEffectDecorator.this.f17467j.b() && this.f17486a.f17481c) && (!BaseOverScrollBounceEffectDecorator.this.f17467j.a() || this.f17486a.f17481c)) {
                return false;
            }
            BaseOverScrollBounceEffectDecorator.this.f17466i.f17488a = motionEvent.getPointerId(0);
            BaseMotionAttributes baseMotionAttributes = this.f17486a;
            baseMotionAttributes.f17479a = 0.0f;
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
            OverScrollStartAttributes overScrollStartAttributes = baseOverScrollBounceEffectDecorator.f17466i;
            overScrollStartAttributes.f17489b = 0.0f;
            overScrollStartAttributes.f17490c = baseMotionAttributes.f17481c;
            baseOverScrollBounceEffectDecorator.z(baseOverScrollBounceEffectDecorator.f17469l);
            return BaseOverScrollBounceEffectDecorator.this.f17469l.d(motionEvent);
        }
    }

    protected static class OverScrollStartAttributes {

        /* renamed from: a, reason: collision with root package name */
        protected int f17488a;

        /* renamed from: b, reason: collision with root package name */
        protected float f17489b;

        /* renamed from: c, reason: collision with root package name */
        protected boolean f17490c;

        protected OverScrollStartAttributes() {
        }
    }

    protected class OverScrollingState implements IDecoratorState {

        /* renamed from: a, reason: collision with root package name */
        final BaseMotionAttributes f17491a;

        /* renamed from: b, reason: collision with root package name */
        int f17492b;

        public OverScrollingState(float f2, float f3) {
            this.f17491a = BaseOverScrollBounceEffectDecorator.this.r();
            BaseOverScrollBounceEffectDecorator.this.u = f2;
            BaseOverScrollBounceEffectDecorator.this.v = f3;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean a(MotionEvent motionEvent) {
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
            baseOverScrollBounceEffectDecorator.z(baseOverScrollBounceEffectDecorator.f17470m);
            return false;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public int b() {
            return this.f17492b;
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public void c(IDecoratorState iDecoratorState) {
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
            this.f17492b = baseOverScrollBounceEffectDecorator.f17466i.f17490c ? 1 : 2;
            baseOverScrollBounceEffectDecorator.f17472o.a(baseOverScrollBounceEffectDecorator, iDecoratorState.b(), b());
        }

        @Override // com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.IDecoratorState
        public boolean d(MotionEvent motionEvent) {
            boolean z;
            View c2 = BaseOverScrollBounceEffectDecorator.this.f17467j.c();
            if (c2 == null) {
                return true;
            }
            if (c2.getParent() != null) {
                c2.getParent().requestDisallowInterceptTouchEvent(true);
            }
            if (BaseOverScrollBounceEffectDecorator.this.f17466i.f17488a != motionEvent.getPointerId(0)) {
                BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
                baseOverScrollBounceEffectDecorator.z(baseOverScrollBounceEffectDecorator.f17470m);
                return true;
            }
            if (!this.f17491a.a(c2, motionEvent)) {
                return true;
            }
            if (BaseOverScrollBounceEffectDecorator.this.t) {
                BaseOverScrollBounceEffectDecorator.this.t = false;
                return false;
            }
            BaseMotionAttributes baseMotionAttributes = this.f17491a;
            float f2 = baseMotionAttributes.f17480b;
            boolean z2 = baseMotionAttributes.f17481c;
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator2 = BaseOverScrollBounceEffectDecorator.this;
            float f3 = this.f17491a.f17479a + (f2 / (z2 == baseOverScrollBounceEffectDecorator2.f17466i.f17490c ? baseOverScrollBounceEffectDecorator2.u : baseOverScrollBounceEffectDecorator2.v));
            int s2 = BaseOverScrollBounceEffectDecorator.this.s(c2);
            if (s2 == 0 || (((z = this.f17491a.f17481c) && f3 >= BaseOverScrollBounceEffectDecorator.this.f17466i.f17489b && -1 == s2) || (!z && f3 <= BaseOverScrollBounceEffectDecorator.this.f17466i.f17489b && -2 == s2))) {
                return false;
            }
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator3 = BaseOverScrollBounceEffectDecorator.this;
            OverScrollStartAttributes overScrollStartAttributes = baseOverScrollBounceEffectDecorator3.f17466i;
            boolean z3 = overScrollStartAttributes.f17490c;
            if ((z3 && !z && f3 <= overScrollStartAttributes.f17489b) || (!z3 && z && f3 >= overScrollStartAttributes.f17489b)) {
                baseOverScrollBounceEffectDecorator3.D(c2, overScrollStartAttributes.f17489b, motionEvent);
                BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator4 = BaseOverScrollBounceEffectDecorator.this;
                baseOverScrollBounceEffectDecorator4.f17473p.a(baseOverScrollBounceEffectDecorator4, this.f17492b, 0.0f);
                BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator5 = BaseOverScrollBounceEffectDecorator.this;
                baseOverScrollBounceEffectDecorator5.z(baseOverScrollBounceEffectDecorator5.f17468k);
                return true;
            }
            if (baseOverScrollBounceEffectDecorator3.f17474q != null && BaseOverScrollBounceEffectDecorator.this.f17474q.f()) {
                BaseOverScrollBounceEffectDecorator.this.f17474q.b();
            }
            boolean v = BaseOverScrollBounceEffectDecorator.this.v(c2);
            boolean y = BaseOverScrollBounceEffectDecorator.this.y(c2);
            boolean w = BaseOverScrollBounceEffectDecorator.this.w(c2);
            if (!BaseOverScrollBounceEffectDecorator.this.x(c2) && y && !w && (f3 >= BaseOverScrollBounceEffectDecorator.this.f17466i.f17489b || !v)) {
                return false;
            }
            BaseOverScrollBounceEffectDecorator.this.C(c2, f3);
            BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator6 = BaseOverScrollBounceEffectDecorator.this;
            baseOverScrollBounceEffectDecorator6.f17473p.a(baseOverScrollBounceEffectDecorator6, this.f17492b, f3);
            BaseOverScrollBounceEffectDecorator.this.f17476s = true;
            BaseOverScrollBounceEffectDecorator.this.t = false;
            return true;
        }
    }

    public BaseOverScrollBounceEffectDecorator(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f2, float f3, float f4, SpringAnimation springAnimation, VelocityTracker velocityTracker) {
        this.f17467j = iOverScrollDecoratorAdapter;
        this.f17474q = springAnimation;
        this.f17475r = velocityTracker;
        this.f17470m = new BounceBackState(f2);
        this.f17469l = new OverScrollingState(f3, f4);
        IdleState idleState = new IdleState();
        this.f17468k = idleState;
        this.f17465h = Utils.d(u().getContext()) * 0.3f;
        this.f17464c = Utils.e(u().getContext()) * 0.3f;
        this.f17471n = idleState;
        if (springAnimation != null) {
            springAnimation.a(new DynamicAnimation.OnAnimationEndListener() { // from class: com.zte.mifavor.utils.overscroll.BaseOverScrollBounceEffectDecorator.1
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public void a(DynamicAnimation dynamicAnimation, boolean z, float f5, float f6) {
                    BaseOverScrollBounceEffectDecorator.this.f17476s = false;
                    Log.i("Z#QScrollBaseDecorator", "on Animation End. +++++++++++++++++++++ mIsBeingDragged = " + BaseOverScrollBounceEffectDecorator.this.f17476s);
                    BaseOverScrollBounceEffectDecorator.this.f17474q.b();
                    BaseOverScrollBounceEffectDecorator baseOverScrollBounceEffectDecorator = BaseOverScrollBounceEffectDecorator.this;
                    baseOverScrollBounceEffectDecorator.z(baseOverScrollBounceEffectDecorator.f17468k);
                }
            });
        }
        p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int s(View view) {
        if (view instanceof RecyclerView) {
            return ((RecyclerView) view).getInterruptSlideDirection();
        }
        if (view instanceof ListView) {
            return ((ListView) view).getInterruptSlideDirection();
        }
        if (view instanceof GridView) {
            return ((GridView) view).getInterruptSlideDirection();
        }
        if (view instanceof ScrollView) {
            return ((ScrollView) view).getInterruptSlideDirection();
        }
        if (view instanceof NestedScrollView) {
            return ((NestedScrollView) view).getInterruptSlideDirection();
        }
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean t(View view) {
        if (view instanceof RecyclerView) {
            return ((RecyclerView) view).getUseSpring();
        }
        if (view instanceof ISpringView) {
            return ((ISpringView) view).getUseSpring();
        }
        if (view instanceof GridView) {
            return ((GridView) view).getUseSpring();
        }
        if (view instanceof ScrollView) {
            return ((ScrollView) view).getUseSpring();
        }
        if (view instanceof NestedScrollView) {
            return ((NestedScrollView) view).getUseSpring();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean v(View view) {
        if (view instanceof RecyclerView) {
            return ((RecyclerView) view).L1();
        }
        if (view instanceof ISpringView) {
            return ((ISpringView) view).isCollapsed();
        }
        if (view instanceof ScrollView) {
            return ((ScrollView) view).isCollapsed();
        }
        if (view instanceof GridView) {
            return ((GridView) view).e();
        }
        if (view instanceof NestedScrollView) {
            return ((NestedScrollView) view).a0();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean w(View view) {
        if (view instanceof RecyclerView) {
            return ((RecyclerView) view).M1();
        }
        if (view instanceof ISpringView) {
            return ((ISpringView) view).isDisableSink();
        }
        if (view instanceof ListView) {
            return ((ListView) view).isDisableSink();
        }
        if (view instanceof GridView) {
            return ((GridView) view).f();
        }
        if (view instanceof ScrollView) {
            return ((ScrollView) view).isDisableSink();
        }
        if (view instanceof NestedScrollView) {
            return ((NestedScrollView) view).b0();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean x(View view) {
        RecyclerView.LayoutManager layoutManager;
        return (view instanceof com.zte.mifavor.androidx.widget.RecyclerView) && (layoutManager = ((com.zte.mifavor.androidx.widget.RecyclerView) view).getLayoutManager()) != null && layoutManager.q() && !layoutManager.r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean y(View view) {
        if (view instanceof com.zte.mifavor.androidx.widget.RecyclerView) {
            return ((com.zte.mifavor.androidx.widget.RecyclerView) view).N1();
        }
        if (view instanceof ISpringView) {
            return ((ISpringView) view).isSupportSink();
        }
        if (view instanceof GridView) {
            return ((GridView) view).g();
        }
        if (view instanceof ScrollView) {
            return ((ScrollView) view).isSupportSink();
        }
        if (view instanceof NestedScrollView) {
            return ((NestedScrollView) view).c0();
        }
        return false;
    }

    public void A(float f2) {
        this.v = f2;
    }

    public void B(float f2) {
        this.u = f2;
    }

    protected abstract void C(View view, float f2);

    protected abstract void D(View view, float f2, MotionEvent motionEvent);

    @Override // com.zte.mifavor.utils.overscroll.IOverScrollDecor
    public boolean getIsBeingDragged() {
        return this.f17476s;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean t = t(this.f17467j.c());
        int action = motionEvent.getAction();
        if (!t) {
            return false;
        }
        this.f17475r.addMovement(motionEvent);
        if (action != 1) {
            if (action == 2) {
                return this.f17471n.d(motionEvent);
            }
            if (action != 3) {
                return false;
            }
        }
        return this.f17471n.a(motionEvent);
    }

    protected void p() {
        u().setOnTouchListener(this);
        u().setOverScrollMode(2);
    }

    protected abstract BaseAnimationAttributes q();

    protected abstract BaseMotionAttributes r();

    public View u() {
        return this.f17467j.c();
    }

    protected void z(IDecoratorState iDecoratorState) {
        IDecoratorState iDecoratorState2 = this.f17471n;
        this.f17471n = iDecoratorState;
        iDecoratorState.c(iDecoratorState2);
    }
}
