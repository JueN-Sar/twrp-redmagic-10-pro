package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class Visibility extends Transition {
    private static final String[] X = {"android:visibility:visibility", "android:visibility:parent"};
    private int W;

    private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final View f5599c;

        /* renamed from: h, reason: collision with root package name */
        private final int f5600h;

        /* renamed from: i, reason: collision with root package name */
        private final ViewGroup f5601i;

        /* renamed from: j, reason: collision with root package name */
        private final boolean f5602j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f5603k;

        /* renamed from: l, reason: collision with root package name */
        boolean f5604l = false;

        DisappearListener(View view, int i2, boolean z) {
            this.f5599c = view;
            this.f5600h = i2;
            this.f5601i = (ViewGroup) view.getParent();
            this.f5602j = z;
            i(true);
        }

        private void a() {
            if (!this.f5604l) {
                ViewUtils.g(this.f5599c, this.f5600h);
                ViewGroup viewGroup = this.f5601i;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            i(false);
        }

        private void i(boolean z) {
            ViewGroup viewGroup;
            if (!this.f5602j || this.f5603k == z || (viewGroup = this.f5601i) == null) {
                return;
            }
            this.f5603k = z;
            ViewGroupUtils.b(viewGroup, z);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
            i(true);
            if (this.f5604l) {
                return;
            }
            ViewUtils.g(this.f5599c, 0);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
            i(false);
            if (this.f5604l) {
                return;
            }
            ViewUtils.g(this.f5599c, this.f5600h);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            transition.b0(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5604l = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                return;
            }
            a();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                ViewUtils.g(this.f5599c, 0);
                ViewGroup viewGroup = this.f5601i;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
        }
    }

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Mode {
    }

    private class OverlayListener extends AnimatorListenerAdapter implements Transition.TransitionListener {

        /* renamed from: c, reason: collision with root package name */
        private final ViewGroup f5605c;

        /* renamed from: h, reason: collision with root package name */
        private final View f5606h;

        /* renamed from: i, reason: collision with root package name */
        private final View f5607i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f5608j = true;

        OverlayListener(ViewGroup viewGroup, View view, View view2) {
            this.f5605c = viewGroup;
            this.f5606h = view;
            this.f5607i = view2;
        }

        private void a() {
            this.f5607i.setTag(R.id.save_overlay_view, null);
            this.f5605c.getOverlay().remove(this.f5606h);
            this.f5608j = false;
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void b(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void d(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void f(Transition transition) {
            transition.b0(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void g(Transition transition) {
            if (this.f5608j) {
                a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            this.f5605c.getOverlay().remove(this.f5606h);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (this.f5606h.getParent() == null) {
                this.f5605c.getOverlay().add(this.f5606h);
            } else {
                Visibility.this.h();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator, boolean z) {
            if (z) {
                this.f5607i.setTag(R.id.save_overlay_view, this.f5606h);
                this.f5605c.getOverlay().add(this.f5606h);
                this.f5608j = true;
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            if (z) {
                return;
            }
            a();
        }
    }

    private static class VisibilityInfo {

        /* renamed from: a, reason: collision with root package name */
        boolean f5610a;

        /* renamed from: b, reason: collision with root package name */
        boolean f5611b;

        /* renamed from: c, reason: collision with root package name */
        int f5612c;

        /* renamed from: d, reason: collision with root package name */
        int f5613d;

        /* renamed from: e, reason: collision with root package name */
        ViewGroup f5614e;

        /* renamed from: f, reason: collision with root package name */
        ViewGroup f5615f;

        VisibilityInfo() {
        }
    }

    public Visibility() {
        this.W = 3;
    }

    private void q0(TransitionValues transitionValues) {
        transitionValues.f5570a.put("android:visibility:visibility", Integer.valueOf(transitionValues.f5571b.getVisibility()));
        transitionValues.f5570a.put("android:visibility:parent", transitionValues.f5571b.getParent());
        int[] iArr = new int[2];
        transitionValues.f5571b.getLocationOnScreen(iArr);
        transitionValues.f5570a.put("android:visibility:screenLocation", iArr);
    }

    private VisibilityInfo s0(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.f5610a = false;
        visibilityInfo.f5611b = false;
        if (transitionValues == null || !transitionValues.f5570a.containsKey("android:visibility:visibility")) {
            visibilityInfo.f5612c = -1;
            visibilityInfo.f5614e = null;
        } else {
            visibilityInfo.f5612c = ((Integer) transitionValues.f5570a.get("android:visibility:visibility")).intValue();
            visibilityInfo.f5614e = (ViewGroup) transitionValues.f5570a.get("android:visibility:parent");
        }
        if (transitionValues2 == null || !transitionValues2.f5570a.containsKey("android:visibility:visibility")) {
            visibilityInfo.f5613d = -1;
            visibilityInfo.f5615f = null;
        } else {
            visibilityInfo.f5613d = ((Integer) transitionValues2.f5570a.get("android:visibility:visibility")).intValue();
            visibilityInfo.f5615f = (ViewGroup) transitionValues2.f5570a.get("android:visibility:parent");
        }
        if (transitionValues != null && transitionValues2 != null) {
            int i2 = visibilityInfo.f5612c;
            int i3 = visibilityInfo.f5613d;
            if (i2 == i3 && visibilityInfo.f5614e == visibilityInfo.f5615f) {
                return visibilityInfo;
            }
            if (i2 != i3) {
                if (i2 == 0) {
                    visibilityInfo.f5611b = false;
                    visibilityInfo.f5610a = true;
                } else if (i3 == 0) {
                    visibilityInfo.f5611b = true;
                    visibilityInfo.f5610a = true;
                }
            } else if (visibilityInfo.f5615f == null) {
                visibilityInfo.f5611b = false;
                visibilityInfo.f5610a = true;
            } else if (visibilityInfo.f5614e == null) {
                visibilityInfo.f5611b = true;
                visibilityInfo.f5610a = true;
            }
        } else if (transitionValues == null && visibilityInfo.f5613d == 0) {
            visibilityInfo.f5611b = true;
            visibilityInfo.f5610a = true;
        } else if (transitionValues2 == null && visibilityInfo.f5612c == 0) {
            visibilityInfo.f5611b = false;
            visibilityInfo.f5610a = true;
        }
        return visibilityInfo;
    }

    @Override // androidx.transition.Transition
    public String[] J() {
        return X;
    }

    @Override // androidx.transition.Transition
    public boolean M(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues != null && transitionValues2 != null && transitionValues2.f5570a.containsKey("android:visibility:visibility") != transitionValues.f5570a.containsKey("android:visibility:visibility")) {
            return false;
        }
        VisibilityInfo s0 = s0(transitionValues, transitionValues2);
        if (s0.f5610a) {
            return s0.f5612c == 0 || s0.f5613d == 0;
        }
        return false;
    }

    @Override // androidx.transition.Transition
    public void i(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void l(TransitionValues transitionValues) {
        q0(transitionValues);
    }

    @Override // androidx.transition.Transition
    public Animator p(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo s0 = s0(transitionValues, transitionValues2);
        if (!s0.f5610a) {
            return null;
        }
        if (s0.f5614e == null && s0.f5615f == null) {
            return null;
        }
        return s0.f5611b ? u0(viewGroup, transitionValues, s0.f5612c, transitionValues2, s0.f5613d) : w0(viewGroup, transitionValues, s0.f5612c, transitionValues2, s0.f5613d);
    }

    public int r0() {
        return this.W;
    }

    public Animator t0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator u0(ViewGroup viewGroup, TransitionValues transitionValues, int i2, TransitionValues transitionValues2, int i3) {
        if ((this.W & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.f5571b.getParent();
            if (s0(w(view, false), K(view, false)).f5610a) {
                return null;
            }
        }
        return t0(viewGroup, transitionValues2.f5571b, transitionValues, transitionValues2);
    }

    public Animator v0(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0083, code lost:
    
        if (r10.C != false) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator w0(android.view.ViewGroup r11, androidx.transition.TransitionValues r12, int r13, androidx.transition.TransitionValues r14, int r15) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.w0(android.view.ViewGroup, androidx.transition.TransitionValues, int, androidx.transition.TransitionValues, int):android.animation.Animator");
    }

    public void x0(int i2) {
        if ((i2 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.W = i2;
    }

    public Visibility(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        this.W = 3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f5512e);
        int k2 = TypedArrayUtils.k(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        obtainStyledAttributes.recycle();
        if (k2 != 0) {
            x0(k2);
        }
    }
}
