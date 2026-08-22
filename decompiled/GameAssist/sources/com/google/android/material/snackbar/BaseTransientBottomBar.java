package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {

    /* renamed from: a, reason: collision with root package name */
    private final int f15286a;

    /* renamed from: b, reason: collision with root package name */
    private final int f15287b;

    /* renamed from: c, reason: collision with root package name */
    private final int f15288c;

    /* renamed from: d, reason: collision with root package name */
    private final TimeInterpolator f15289d;

    /* renamed from: e, reason: collision with root package name */
    private final TimeInterpolator f15290e;

    /* renamed from: f, reason: collision with root package name */
    private final TimeInterpolator f15291f;

    /* renamed from: g, reason: collision with root package name */
    private final ViewGroup f15292g;

    /* renamed from: h, reason: collision with root package name */
    private final Context f15293h;

    /* renamed from: i, reason: collision with root package name */
    protected final SnackbarBaseLayout f15294i;

    /* renamed from: j, reason: collision with root package name */
    private final com.google.android.material.snackbar.ContentViewCallback f15295j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f15296k;

    /* renamed from: l, reason: collision with root package name */
    private Anchor f15297l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f15298m;

    /* renamed from: n, reason: collision with root package name */
    private final Runnable f15299n;

    /* renamed from: o, reason: collision with root package name */
    private int f15300o;

    /* renamed from: p, reason: collision with root package name */
    private int f15301p;

    /* renamed from: q, reason: collision with root package name */
    private int f15302q;

    /* renamed from: r, reason: collision with root package name */
    private int f15303r;

    /* renamed from: s, reason: collision with root package name */
    private int f15304s;
    private int t;
    private boolean u;
    private List v;
    private Behavior w;
    private final AccessibilityManager x;
    SnackbarManager.Callback y;
    private static final TimeInterpolator z = AnimationUtils.f13815b;
    private static final TimeInterpolator A = AnimationUtils.f13814a;
    private static final TimeInterpolator B = AnimationUtils.f13817d;
    private static final boolean D = false;
    private static final int[] E = {R.attr.snackbarStyle};
    private static final String F = BaseTransientBottomBar.class.getSimpleName();
    static final Handler C = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                ((BaseTransientBottomBar) message.obj).T();
                return true;
            }
            if (i2 != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).H(message.arg1);
            return true;
        }
    });

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ BaseTransientBottomBar f15317c;

        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar baseTransientBottomBar = this.f15317c;
            if (baseTransientBottomBar.f15294i == null || baseTransientBottomBar.f15293h == null) {
                return;
            }
            int height = (WindowUtils.a(this.f15317c.f15293h).height() - this.f15317c.G()) + ((int) this.f15317c.f15294i.getTranslationY());
            if (height >= this.f15317c.f15304s) {
                BaseTransientBottomBar baseTransientBottomBar2 = this.f15317c;
                baseTransientBottomBar2.t = baseTransientBottomBar2.f15304s;
                return;
            }
            ViewGroup.LayoutParams layoutParams = this.f15317c.f15294i.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.F, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            BaseTransientBottomBar baseTransientBottomBar3 = this.f15317c;
            baseTransientBottomBar3.t = baseTransientBottomBar3.f15304s;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += this.f15317c.f15304s - height;
            this.f15317c.f15294i.requestLayout();
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$3, reason: invalid class name */
    class AnonymousClass3 implements OnApplyWindowInsetsListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ BaseTransientBottomBar f15318a;

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
            this.f15318a.f15300o = windowInsetsCompat.i();
            this.f15318a.f15301p = windowInsetsCompat.j();
            this.f15318a.f15302q = windowInsetsCompat.k();
            this.f15318a.Z();
            return windowInsetsCompat;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$4, reason: invalid class name */
    class AnonymousClass4 extends AccessibilityDelegateCompat {

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ BaseTransientBottomBar f15319d;

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.a(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY);
            accessibilityNodeInfoCompat.m0(true);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean j(View view, int i2, Bundle bundle) {
            if (i2 != 1048576) {
                return super.j(view, i2, bundle);
            }
            this.f15319d.z();
            return true;
        }
    }

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$5, reason: invalid class name */
    class AnonymousClass5 implements SnackbarManager.Callback {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ BaseTransientBottomBar f15320a;

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void a(int i2) {
            Handler handler = BaseTransientBottomBar.C;
            handler.sendMessage(handler.obtainMessage(1, i2, 0, this.f15320a));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            Handler handler = BaseTransientBottomBar.C;
            handler.sendMessage(handler.obtainMessage(0, this.f15320a));
        }
    }

    static class Anchor implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: c, reason: collision with root package name */
        private final WeakReference f15325c;

        /* renamed from: h, reason: collision with root package name */
        private final WeakReference f15326h;

        private boolean c() {
            if (this.f15325c.get() != null) {
                return false;
            }
            b();
            return true;
        }

        View a() {
            return (View) this.f15326h.get();
        }

        void b() {
            if (this.f15326h.get() != null) {
                ((View) this.f15326h.get()).removeOnAttachStateChangeListener(this);
                ViewUtils.s((View) this.f15326h.get(), this);
            }
            this.f15326h.clear();
            this.f15325c.clear();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (c() || !((BaseTransientBottomBar) this.f15325c.get()).f15298m) {
                return;
            }
            ((BaseTransientBottomBar) this.f15325c.get()).P();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (c()) {
                return;
            }
            ViewUtils.b(view, this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (c()) {
                return;
            }
            ViewUtils.s(view, this);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface AnimationMode {
    }

    public static abstract class BaseCallback<B> {

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo
        public @interface DismissEvent {
        }

        public void a(Object obj, int i2) {
        }

        public void b(Object obj) {
        }
    }

    public static class Behavior extends SwipeDismissBehavior<View> {

        /* renamed from: r, reason: collision with root package name */
        private final BehaviorDelegate f15327r = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void V(BaseTransientBottomBar baseTransientBottomBar) {
            this.f15327r.c(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean K(View view) {
            return this.f15327r.a(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean p(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.f15327r.b(coordinatorLayout, view, motionEvent);
            return super.p(coordinatorLayout, view, motionEvent);
        }
    }

    @RestrictTo
    public static class BehaviorDelegate {

        /* renamed from: a, reason: collision with root package name */
        private SnackbarManager.Callback f15328a;

        public BehaviorDelegate(SwipeDismissBehavior swipeDismissBehavior) {
            swipeDismissBehavior.R(0.1f);
            swipeDismissBehavior.P(0.6f);
            swipeDismissBehavior.S(0);
        }

        public boolean a(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.F(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    SnackbarManager.c().j(this.f15328a);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                SnackbarManager.c().k(this.f15328a);
            }
        }

        public void c(BaseTransientBottomBar baseTransientBottomBar) {
            this.f15328a = baseTransientBottomBar.y;
        }
    }

    @Deprecated
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @IntRange
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Duration {
    }

    @RestrictTo
    protected static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        };
        private final float actionTextColorAlpha;
        private boolean addingToTargetParent;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;

        @Nullable
        private BaseTransientBottomBar<?> baseTransientBottomBar;
        private final int maxInlineActionWidth;
        private final int maxWidth;

        @Nullable
        private Rect originalMargins;

        @Nullable
        ShapeAppearanceModel shapeAppearanceModel;

        protected SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.c(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.q0(this, obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            this.animationMode = obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_animationMode, 0);
            if (obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_shapeAppearance) || obtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_shapeAppearanceOverlay)) {
                this.shapeAppearanceModel = ShapeAppearanceModel.e(context2, attributeSet, 0, 0).m();
            }
            this.backgroundOverlayColorAlpha = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.a(context2, obtainStyledAttributes, R.styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.r(obtainStyledAttributes.getInt(R.styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = obtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.maxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            this.maxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            obtainStyledAttributes.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() == null) {
                ViewCompat.m0(this, c());
            }
        }

        private Drawable c() {
            int m2 = MaterialColors.m(this, R.attr.colorSurface, R.attr.colorOnSurface, getBackgroundOverlayColorAlpha());
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
            Drawable y = shapeAppearanceModel != null ? BaseTransientBottomBar.y(m2, shapeAppearanceModel) : BaseTransientBottomBar.x(m2, getResources());
            if (this.backgroundTint == null) {
                return DrawableCompat.r(y);
            }
            Drawable r2 = DrawableCompat.r(y);
            DrawableCompat.o(r2, this.backgroundTint);
            return r2;
        }

        private void d(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.originalMargins = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.baseTransientBottomBar = baseTransientBottomBar;
        }

        void b(ViewGroup viewGroup) {
            this.addingToTargetParent = true;
            viewGroup.addView(this);
            this.addingToTargetParent = false;
        }

        float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        int getAnimationMode() {
            return this.animationMode;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        int getMaxInlineActionWidth() {
            return this.maxInlineActionWidth;
        }

        int getMaxWidth() {
            return this.maxWidth;
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.K();
            }
            ViewCompat.f0(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.L();
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.M();
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (this.maxWidth > 0) {
                int measuredWidth = getMeasuredWidth();
                int i4 = this.maxWidth;
                if (measuredWidth > i4) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i4, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                }
            }
        }

        void setAnimationMode(int i2) {
            this.animationMode = i2;
        }

        @Override // android.view.View
        public void setBackground(@Nullable Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(@Nullable Drawable drawable) {
            if (drawable != null && this.backgroundTint != null) {
                drawable = DrawableCompat.r(drawable.mutate());
                DrawableCompat.o(drawable, this.backgroundTint);
                DrawableCompat.p(drawable, this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
            this.backgroundTint = colorStateList;
            if (getBackground() != null) {
                Drawable r2 = DrawableCompat.r(getBackground().mutate());
                DrawableCompat.o(r2, colorStateList);
                DrawableCompat.p(r2, this.backgroundTintMode);
                if (r2 != getBackground()) {
                    super.setBackgroundDrawable(r2);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
            this.backgroundTintMode = mode;
            if (getBackground() != null) {
                Drawable r2 = DrawableCompat.r(getBackground().mutate());
                DrawableCompat.p(r2, mode);
                if (r2 != getBackground()) {
                    super.setBackgroundDrawable(r2);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.addingToTargetParent || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            d((ViewGroup.MarginLayoutParams) layoutParams);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.Z();
            }
        }

        @Override // android.view.View
        public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : consumeAllTouchListener);
            super.setOnClickListener(onClickListener);
        }
    }

    private ValueAnimator B(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.f15289d);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseTransientBottomBar.this.f15294i.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        return ofFloat;
    }

    private ValueAnimator E(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.f15291f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BaseTransientBottomBar.this.f15294i.setScaleX(floatValue);
                BaseTransientBottomBar.this.f15294i.setScaleY(floatValue);
            }
        });
        return ofFloat;
    }

    private int F() {
        int height = this.f15294i.getHeight();
        ViewGroup.LayoutParams layoutParams = this.f15294i.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int G() {
        int[] iArr = new int[2];
        this.f15294i.getLocationInWindow(iArr);
        return iArr[1] + this.f15294i.getHeight();
    }

    private boolean J() {
        ViewGroup.LayoutParams layoutParams = this.f15294i.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).f() instanceof SwipeDismissBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        this.f15303r = w();
        Z();
    }

    private void Q(CoordinatorLayout.LayoutParams layoutParams) {
        SwipeDismissBehavior swipeDismissBehavior = this.w;
        if (swipeDismissBehavior == null) {
            swipeDismissBehavior = D();
        }
        if (swipeDismissBehavior instanceof Behavior) {
            ((Behavior) swipeDismissBehavior).V(this);
        }
        swipeDismissBehavior.Q(new SwipeDismissBehavior.OnDismissListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7
            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void a(View view) {
                if (view.getParent() != null) {
                    view.setVisibility(8);
                }
                BaseTransientBottomBar.this.A(0);
            }

            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void b(int i2) {
                if (i2 == 0) {
                    SnackbarManager.c().k(BaseTransientBottomBar.this.y);
                } else if (i2 == 1 || i2 == 2) {
                    SnackbarManager.c().j(BaseTransientBottomBar.this.y);
                }
            }
        });
        layoutParams.o(swipeDismissBehavior);
        if (C() == null) {
            layoutParams.f2586g = 80;
        }
    }

    private boolean S() {
        return this.f15304s > 0 && !this.f15296k && J();
    }

    private void U() {
        if (R()) {
            u();
            return;
        }
        if (this.f15294i.getParent() != null) {
            this.f15294i.setVisibility(0);
        }
        O();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        ValueAnimator B2 = B(0.0f, 1.0f);
        ValueAnimator E2 = E(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(B2, E2);
        animatorSet.setDuration(this.f15286a);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.O();
            }
        });
        animatorSet.start();
    }

    private void W(final int i2) {
        ValueAnimator B2 = B(1.0f, 0.0f);
        B2.setDuration(this.f15287b);
        B2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.N(i2);
            }
        });
        B2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        int F2 = F();
        if (D) {
            ViewCompat.T(this.f15294i, F2);
        } else {
            this.f15294i.setTranslationY(F2);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(F2, 0);
        valueAnimator.setInterpolator(this.f15290e);
        valueAnimator.setDuration(this.f15288c);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.13
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.O();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.f15295j.a(BaseTransientBottomBar.this.f15288c - BaseTransientBottomBar.this.f15286a, BaseTransientBottomBar.this.f15286a);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(F2) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14

            /* renamed from: c, reason: collision with root package name */
            private int f15310c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ int f15311h;

            {
                this.f15311h = F2;
                this.f15310c = F2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.D) {
                    ViewCompat.T(BaseTransientBottomBar.this.f15294i, intValue - this.f15310c);
                } else {
                    BaseTransientBottomBar.this.f15294i.setTranslationY(intValue);
                }
                this.f15310c = intValue;
            }
        });
        valueAnimator.start();
    }

    private void Y(final int i2) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, F());
        valueAnimator.setInterpolator(this.f15290e);
        valueAnimator.setDuration(this.f15288c);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.N(i2);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.f15295j.b(0, BaseTransientBottomBar.this.f15287b);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.16

            /* renamed from: c, reason: collision with root package name */
            private int f15315c = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.D) {
                    ViewCompat.T(BaseTransientBottomBar.this.f15294i, intValue - this.f15315c);
                } else {
                    BaseTransientBottomBar.this.f15294i.setTranslationY(intValue);
                }
                this.f15315c = intValue;
            }
        });
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z() {
        ViewGroup.LayoutParams layoutParams = this.f15294i.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            Log.w(F, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        if (this.f15294i.originalMargins == null) {
            Log.w(F, "Unable to update margins because original view margins are not set");
            return;
        }
        if (this.f15294i.getParent() == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int i2 = this.f15294i.originalMargins.bottom + (C() != null ? this.f15303r : this.f15300o);
        int i3 = this.f15294i.originalMargins.left + this.f15301p;
        int i4 = this.f15294i.originalMargins.right + this.f15302q;
        int i5 = this.f15294i.originalMargins.top;
        boolean z2 = (marginLayoutParams.bottomMargin == i2 && marginLayoutParams.leftMargin == i3 && marginLayoutParams.rightMargin == i4 && marginLayoutParams.topMargin == i5) ? false : true;
        if (z2) {
            marginLayoutParams.bottomMargin = i2;
            marginLayoutParams.leftMargin = i3;
            marginLayoutParams.rightMargin = i4;
            marginLayoutParams.topMargin = i5;
            this.f15294i.requestLayout();
        }
        if ((z2 || this.t != this.f15304s) && S()) {
            this.f15294i.removeCallbacks(this.f15299n);
            this.f15294i.post(this.f15299n);
        }
    }

    private void v(int i2) {
        if (this.f15294i.getAnimationMode() == 1) {
            W(i2);
        } else {
            Y(i2);
        }
    }

    private int w() {
        if (C() == null) {
            return 0;
        }
        int[] iArr = new int[2];
        C().getLocationOnScreen(iArr);
        int i2 = iArr[1];
        int[] iArr2 = new int[2];
        this.f15292g.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.f15292g.getHeight()) - i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GradientDrawable x(int i2, Resources resources) {
        float dimension = resources.getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(i2);
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MaterialShapeDrawable y(int i2, ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        materialShapeDrawable.a0(ColorStateList.valueOf(i2));
        return materialShapeDrawable;
    }

    protected void A(int i2) {
        SnackbarManager.c().b(this.y, i2);
    }

    public View C() {
        Anchor anchor = this.f15297l;
        if (anchor == null) {
            return null;
        }
        return anchor.a();
    }

    protected SwipeDismissBehavior D() {
        return new Behavior();
    }

    final void H(int i2) {
        if (R() && this.f15294i.getVisibility() == 0) {
            v(i2);
        } else {
            N(i2);
        }
    }

    public boolean I() {
        return SnackbarManager.c().e(this.y);
    }

    void K() {
        WindowInsets rootWindowInsets = this.f15294i.getRootWindowInsets();
        if (rootWindowInsets != null) {
            this.f15304s = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
            Z();
        }
    }

    void L() {
        if (I()) {
            C.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
                @Override // java.lang.Runnable
                public void run() {
                    BaseTransientBottomBar.this.N(3);
                }
            });
        }
    }

    void M() {
        if (this.u) {
            U();
            this.u = false;
        }
    }

    void N(int i2) {
        SnackbarManager.c().h(this.y);
        List list = this.v;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.v.get(size)).a(this, i2);
            }
        }
        ViewParent parent = this.f15294i.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.f15294i);
        }
    }

    void O() {
        SnackbarManager.c().i(this.y);
        List list = this.v;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((BaseCallback) this.v.get(size)).b(this);
            }
        }
    }

    boolean R() {
        AccessibilityManager accessibilityManager = this.x;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    final void T() {
        if (this.f15294i.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.f15294i.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                Q((CoordinatorLayout.LayoutParams) layoutParams);
            }
            this.f15294i.b(this.f15292g);
            P();
            this.f15294i.setVisibility(4);
        }
        if (ViewCompat.N(this.f15294i)) {
            U();
        } else {
            this.u = true;
        }
    }

    void u() {
        this.f15294i.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.8
            @Override // java.lang.Runnable
            public void run() {
                SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.f15294i;
                if (snackbarBaseLayout == null) {
                    return;
                }
                if (snackbarBaseLayout.getParent() != null) {
                    BaseTransientBottomBar.this.f15294i.setVisibility(0);
                }
                if (BaseTransientBottomBar.this.f15294i.getAnimationMode() == 1) {
                    BaseTransientBottomBar.this.V();
                } else {
                    BaseTransientBottomBar.this.X();
                }
            }
        });
    }

    public void z() {
        A(3);
    }
}
