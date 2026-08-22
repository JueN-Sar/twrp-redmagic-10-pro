package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.Map;

/* loaded from: classes.dex */
public class ChangeBounds extends Transition {
    private static final Property Y;
    private static final Property Z;
    private static final Property a0;
    private static final Property b0;
    private static final Property c0;
    private boolean W;
    private static final String[] X = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final RectEvaluator d0 = new RectEvaluator();

    private static class ClipListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final View f5418c;

        /* renamed from: h, reason: collision with root package name */
        private final Rect f5419h;

        /* renamed from: i, reason: collision with root package name */
        private final boolean f5420i;

        /* renamed from: j, reason: collision with root package name */
        private final Rect f5421j;

        /* renamed from: k, reason: collision with root package name */
        private final boolean f5422k;

        /* renamed from: l, reason: collision with root package name */
        private final int f5423l;

        /* renamed from: m, reason: collision with root package name */
        private final int f5424m;

        /* renamed from: n, reason: collision with root package name */
        private final int f5425n;

        /* renamed from: o, reason: collision with root package name */
        private final int f5426o;

        /* renamed from: p, reason: collision with root package name */
        private final int f5427p;

        /* renamed from: q, reason: collision with root package name */
        private final int f5428q;

        /* renamed from: r, reason: collision with root package name */
        private final int f5429r;

        /* renamed from: s, reason: collision with root package name */
        private final int f5430s;
        private boolean t;

        ClipListener(View view, Rect rect, boolean z, Rect rect2, boolean z2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.f5418c = view;
            this.f5419h = rect;
            this.f5420i = z;
            this.f5421j = rect2;
            this.f5422k = z2;
            this.f5423l = i2;
            this.f5424m = i3;
            this.f5425n = i4;
            this.f5426o = i5;
            this.f5427p = i6;
            this.f5428q = i7;
            this.f5429r = i8;
            this.f5430s = i9;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            Rect rect = (Rect) this.f5418c.getTag(R.id.transition_clip);
            this.f5418c.setTag(R.id.transition_clip, null);
            this.f5418c.setClipBounds(rect);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            this.f5418c.setTag(R.id.transition_clip, this.f5418c.getClipBounds());
            this.f5418c.setClipBounds(this.f5422k ? null : this.f5421j);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
            this.t = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            onAnimationStart(animator, false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (this.t) {
                return;
            }
            Rect rect = null;
            if (z) {
                if (!this.f5420i) {
                    rect = this.f5419h;
                }
            } else if (!this.f5422k) {
                rect = this.f5421j;
            }
            this.f5418c.setClipBounds(rect);
            if (z) {
                ViewUtils.e(this.f5418c, this.f5423l, this.f5424m, this.f5425n, this.f5426o);
            } else {
                ViewUtils.e(this.f5418c, this.f5427p, this.f5428q, this.f5429r, this.f5430s);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator, boolean z) {
            int max = Math.max(this.f5425n - this.f5423l, this.f5429r - this.f5427p);
            int max2 = Math.max(this.f5426o - this.f5424m, this.f5430s - this.f5428q);
            int i2 = z ? this.f5427p : this.f5423l;
            int i3 = z ? this.f5428q : this.f5424m;
            ViewUtils.e(this.f5418c, i2, i3, max + i2, max2 + i3);
            this.f5418c.setClipBounds(z ? this.f5421j : this.f5419h);
        }
    }

    private static class SuppressLayoutListener extends TransitionListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        boolean f5431c = false;

        /* renamed from: h, reason: collision with root package name */
        final ViewGroup f5432h;

        SuppressLayoutListener(ViewGroup viewGroup) {
            this.f5432h = viewGroup;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            ViewGroupUtils.b(this.f5432h, true);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            ViewGroupUtils.b(this.f5432h, false);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            if (!this.f5431c) {
                ViewGroupUtils.b(this.f5432h, false);
            }
            transition.b0(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
            ViewGroupUtils.b(this.f5432h, false);
            this.f5431c = true;
        }
    }

    private static class ViewBounds {

        /* renamed from: a, reason: collision with root package name */
        private int f5433a;

        /* renamed from: b, reason: collision with root package name */
        private int f5434b;

        /* renamed from: c, reason: collision with root package name */
        private int f5435c;

        /* renamed from: d, reason: collision with root package name */
        private int f5436d;

        /* renamed from: e, reason: collision with root package name */
        private final View f5437e;

        /* renamed from: f, reason: collision with root package name */
        private int f5438f;

        /* renamed from: g, reason: collision with root package name */
        private int f5439g;

        ViewBounds(View view) {
            this.f5437e = view;
        }

        private void b() {
            ViewUtils.e(this.f5437e, this.f5433a, this.f5434b, this.f5435c, this.f5436d);
            this.f5438f = 0;
            this.f5439g = 0;
        }

        void a(PointF pointF) {
            this.f5435c = Math.round(pointF.x);
            this.f5436d = Math.round(pointF.y);
            int i2 = this.f5439g + 1;
            this.f5439g = i2;
            if (this.f5438f == i2) {
                b();
            }
        }

        void c(PointF pointF) {
            this.f5433a = Math.round(pointF.x);
            this.f5434b = Math.round(pointF.y);
            int i2 = this.f5438f + 1;
            this.f5438f = i2;
            if (i2 == this.f5439g) {
                b();
            }
        }
    }

    static {
        Class<PointF> cls = PointF.class;
        String str = "topLeft";
        Y = new Property<ViewBounds, PointF>(cls, str) { // from class: androidx.transition.ChangeBounds.1
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.c(pointF);
            }
        };
        String str2 = "bottomRight";
        Z = new Property<ViewBounds, PointF>(cls, str2) { // from class: androidx.transition.ChangeBounds.2
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PointF get(ViewBounds viewBounds) {
                return null;
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(ViewBounds viewBounds, PointF pointF) {
                viewBounds.a(pointF);
            }
        };
        a0 = new Property<View, PointF>(cls, str2) { // from class: androidx.transition.ChangeBounds.3
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, PointF pointF) {
                ViewUtils.e(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
            }
        };
        b0 = new Property<View, PointF>(cls, str) { // from class: androidx.transition.ChangeBounds.4
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, PointF pointF) {
                ViewUtils.e(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
            }
        };
        c0 = new Property<View, PointF>(cls, "position") { // from class: androidx.transition.ChangeBounds.5
            @Override // android.util.Property
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public PointF get(View view) {
                return null;
            }

            @Override // android.util.Property
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void set(View view, PointF pointF) {
                int round = Math.round(pointF.x);
                int round2 = Math.round(pointF.y);
                ViewUtils.e(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
            }
        };
    }

    public ChangeBounds() {
        this.W = false;
    }

    private void q0(TransitionValues transitionValues) {
        View view = transitionValues.f5571b;
        if (!view.isLaidOut() && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.f5570a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.f5570a.put("android:changeBounds:parent", transitionValues.f5571b.getParent());
        if (this.W) {
            transitionValues.f5570a.put("android:changeBounds:clip", view.getClipBounds());
        }
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return X;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        Rect rect;
        q0(transitionValues);
        if (!this.W || (rect = (Rect) transitionValues.f5571b.getTag(R.id.transition_clip)) == null) {
            return;
        }
        transitionValues.f5570a.put("android:changeBounds:clip", rect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i2;
        View view;
        int i3;
        int i4;
        int i5;
        ObjectAnimator a2;
        int i6;
        ObjectAnimator objectAnimator;
        Animator c2;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map map = transitionValues.f5570a;
        Map map2 = transitionValues2.f5570a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = transitionValues2.f5571b;
        Rect rect = (Rect) transitionValues.f5570a.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) transitionValues2.f5570a.get("android:changeBounds:bounds");
        int i7 = rect.left;
        int i8 = rect2.left;
        int i9 = rect.top;
        int i10 = rect2.top;
        int i11 = rect.right;
        int i12 = rect2.right;
        int i13 = rect.bottom;
        int i14 = rect2.bottom;
        int i15 = i11 - i7;
        int i16 = i13 - i9;
        int i17 = i12 - i8;
        int i18 = i14 - i10;
        Rect rect3 = (Rect) transitionValues.f5570a.get("android:changeBounds:clip");
        Rect rect4 = (Rect) transitionValues2.f5570a.get("android:changeBounds:clip");
        if ((i15 == 0 || i16 == 0) && (i17 == 0 || i18 == 0)) {
            i2 = 0;
        } else {
            i2 = (i7 == i8 && i9 == i10) ? 0 : 1;
            if (i11 != i12 || i13 != i14) {
                i2++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i2++;
        }
        if (i2 <= 0) {
            return null;
        }
        if (this.W) {
            view = view2;
            ViewUtils.e(view, i7, i9, Math.max(i15, i17) + i7, i9 + Math.max(i16, i18));
            if (i7 == i8 && i9 == i10) {
                i3 = i12;
                i4 = i11;
                i5 = i9;
                a2 = null;
            } else {
                i3 = i12;
                i4 = i11;
                i5 = i9;
                a2 = ObjectAnimatorUtils.a(view, c0, y().a(i7, i9, i8, i10));
            }
            boolean z = rect3 == null;
            if (z) {
                i6 = 0;
                rect3 = new Rect(0, 0, i15, i16);
            } else {
                i6 = 0;
            }
            Rect rect5 = rect3;
            int i19 = rect4 == null ? 1 : i6;
            Rect rect6 = i19 != 0 ? new Rect(i6, i6, i17, i18) : rect4;
            if (rect5.equals(rect6)) {
                objectAnimator = null;
            } else {
                view.setClipBounds(rect5);
                objectAnimator = ObjectAnimator.ofObject(view, "clipBounds", d0, rect5, rect6);
                ClipListener clipListener = new ClipListener(view, rect5, z, rect6, i19, i7, i5, i4, i13, i8, i10, i3, i14);
                objectAnimator.addListener(clipListener);
                a(clipListener);
            }
            c2 = TransitionUtils.c(a2, objectAnimator);
        } else {
            view = view2;
            ViewUtils.e(view, i7, i9, i11, i13);
            if (i2 != 2) {
                c2 = (i7 == i8 && i9 == i10) ? ObjectAnimatorUtils.a(view, a0, y().a(i11, i13, i12, i14)) : ObjectAnimatorUtils.a(view, b0, y().a(i7, i9, i8, i10));
            } else if (i15 == i17 && i16 == i18) {
                c2 = ObjectAnimatorUtils.a(view, c0, y().a(i7, i9, i8, i10));
            } else {
                ViewBounds viewBounds = new ViewBounds(view);
                ObjectAnimator a3 = ObjectAnimatorUtils.a(viewBounds, Y, y().a(i7, i9, i8, i10));
                ObjectAnimator a4 = ObjectAnimatorUtils.a(viewBounds, Z, y().a(i11, i13, i12, i14));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(a3, a4);
                animatorSet.addListener(new AnimatorListenerAdapter(viewBounds) { // from class: androidx.transition.ChangeBounds.6

                    /* renamed from: c, reason: collision with root package name */
                    final /* synthetic */ ViewBounds f5416c;
                    private final ViewBounds mViewBounds;

                    {
                        this.f5416c = viewBounds;
                        this.mViewBounds = viewBounds;
                    }
                });
                c2 = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ViewGroupUtils.b(viewGroup4, true);
            A().a(new SuppressLayoutListener(viewGroup4));
        }
        return c2;
    }

    public void r0(boolean z) {
        this.W = z;
    }

    public ChangeBounds(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.W = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5511d);
        boolean e2 = TypedArrayUtils.e(obtainStyledAttributes, (XmlResourceParser) attributeSet, "resizeClip", 0, false);
        obtainStyledAttributes.recycle();
        r0(e2);
    }
}
