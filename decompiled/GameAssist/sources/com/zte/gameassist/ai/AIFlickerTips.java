package com.zte.gameassist.ai;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.PathInterpolator;
import com.zte.gameassist.ai.AIFlickerTips;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* loaded from: classes2.dex */
public class AIFlickerTips {

    /* renamed from: i, reason: collision with root package name */
    private static volatile AIFlickerTips f16340i;

    /* renamed from: e, reason: collision with root package name */
    private final Paint f16346e;

    /* renamed from: h, reason: collision with root package name */
    public static boolean f16339h = "userdebug".equals(Build.TYPE);

    /* renamed from: j, reason: collision with root package name */
    private static final PathInterpolator f16341j = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);

    /* renamed from: a, reason: collision with root package name */
    private final Map f16342a = new ArrayMap();

    /* renamed from: b, reason: collision with root package name */
    private final List f16343b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final List f16344c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private final ThreadLocal f16345d = new ThreadLocal();

    /* renamed from: f, reason: collision with root package name */
    private final float f16347f = 1.0f;

    /* renamed from: g, reason: collision with root package name */
    private final Runnable f16348g = new Runnable() { // from class: com.zte.gameassist.ai.b
        @Override // java.lang.Runnable
        public final void run() {
            AIFlickerTips.this.D();
        }
    };

    private static class AnimatorListener implements Animator.AnimatorListener {

        /* renamed from: c, reason: collision with root package name */
        private final Runnable f16349c;

        /* renamed from: h, reason: collision with root package name */
        private final Runnable f16350h;

        public AnimatorListener(Runnable runnable, Runnable runnable2) {
            this.f16349c = runnable;
            this.f16350h = runnable2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            Runnable runnable = this.f16350h;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Runnable runnable = this.f16350h;
            if (runnable != null) {
                runnable.run();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Runnable runnable = this.f16349c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public class Flicker implements View.OnAttachStateChangeListener {

        /* renamed from: c, reason: collision with root package name */
        private final ViewTreeObserver.OnPreDrawListener f16351c;

        /* renamed from: h, reason: collision with root package name */
        private final String f16352h;

        /* renamed from: i, reason: collision with root package name */
        private final View f16353i;

        /* renamed from: j, reason: collision with root package name */
        private ValueAnimator f16354j;

        /* renamed from: k, reason: collision with root package name */
        private boolean f16355k;

        /* renamed from: l, reason: collision with root package name */
        private boolean f16356l;

        /* renamed from: m, reason: collision with root package name */
        private View f16357m;

        /* renamed from: n, reason: collision with root package name */
        private String f16358n;

        /* renamed from: o, reason: collision with root package name */
        private String f16359o;

        /* renamed from: p, reason: collision with root package name */
        private long f16360p;

        /* renamed from: q, reason: collision with root package name */
        private final Runnable f16361q = new Runnable() { // from class: com.zte.gameassist.ai.i
            @Override // java.lang.Runnable
            public final void run() {
                AIFlickerTips.Flicker.this.t();
            }
        };

        /* renamed from: com.zte.gameassist.ai.AIFlickerTips$Flicker$1, reason: invalid class name */
        class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {

            /* renamed from: c, reason: collision with root package name */
            private long f16363c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ AIFlickerTips f16364h;

            /* renamed from: i, reason: collision with root package name */
            final /* synthetic */ View f16365i;

            AnonymousClass1(AIFlickerTips aIFlickerTips, View view) {
                this.f16364h = aIFlickerTips;
                this.f16365i = view;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void d(ValueAnimator valueAnimator) {
                ViewGroup viewGroup = (ViewGroup) Flicker.this.f16353i.getRootView().getRootView();
                String p2 = Flicker.this.p();
                if (viewGroup == null || !TextUtils.equals(p2, Flicker.this.f16352h)) {
                    return;
                }
                int[] iArr = new int[2];
                Flicker.this.f16353i.getLocationInWindow(iArr);
                String str = "onFlickerAnimationUpdate mName=" + Flicker.this.r() + " attach=" + Flicker.this.f16353i.isAttachedToWindow() + " vis=" + AIFlickerTips.y(Flicker.this.f16353i) + " p=" + iArr[0] + "," + iArr[1] + " : " + Flicker.this.f16357m.getWidth() + "," + Flicker.this.f16357m.getHeight() + " f=" + Flicker.this.f16357m.isAttachedToWindow();
                if (!TextUtils.equals(str, Flicker.this.f16359o) && System.currentTimeMillis() - this.f16363c >= 250) {
                    this.f16363c = System.currentTimeMillis();
                    Flicker.this.f16359o = str;
                    AIFlickerTips.G(Flicker.this.f16359o + ", v=" + Flicker.this.f16353i);
                }
                if (Flicker.this.f16357m != null) {
                    Flicker.this.f16357m.postInvalidate();
                }
                List list = (List) AIFlickerTips.this.f16342a.get(Flicker.this.f16352h);
                if (list == null || list.contains(Flicker.this) || !Flicker.this.f16353i.isAttachedToWindow() || !AIFlickerTips.y(Flicker.this.f16353i)) {
                    return;
                }
                if (!Flicker.this.f16355k) {
                    Flicker.this.f16353i.addOnAttachStateChangeListener(Flicker.this);
                    Flicker.this.f16355k = true;
                }
                ((List) AIFlickerTips.this.f16342a.get(Flicker.this.f16352h)).add(Flicker.this);
                AIFlickerTips.G("release, but add tower. " + Flicker.this.r());
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                String str = Flicker.this.r() + " onPreDraw";
                this.f16365i.getViewTreeObserver().removeOnPreDrawListener(this);
                if (Flicker.this.f16354j == null && TextUtils.equals(Flicker.this.p(), Flicker.this.f16352h)) {
                    Flicker.this.f16354j = ValueAnimator.ofFloat(0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f);
                    Flicker.this.f16354j.setDuration(1000L);
                    Flicker.this.f16354j.setInterpolator(AIFlickerTips.f16341j);
                    Flicker.this.f16354j.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.zte.gameassist.ai.j
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            AIFlickerTips.Flicker.AnonymousClass1.this.d(valueAnimator);
                        }
                    });
                    ValueAnimator valueAnimator = Flicker.this.f16354j;
                    final Flicker flicker = Flicker.this;
                    valueAnimator.addListener(new AnimatorListener(null, new Runnable() { // from class: com.zte.gameassist.ai.k
                        @Override // java.lang.Runnable
                        public final void run() {
                            AIFlickerTips.Flicker.g(AIFlickerTips.Flicker.this);
                        }
                    }));
                    Flicker.this.f16354j.start();
                    str = str + ", start animator " + Integer.toHexString(Flicker.this.hashCode()) + " " + Flicker.this.f16353i;
                }
                AIFlickerTips.G(str);
                return true;
            }
        }

        public Flicker(String str, View view) {
            this.f16352h = str;
            this.f16353i = view;
            view.setTag(539234817, str);
            view.setTag(539234819, this);
            this.f16351c = new AnonymousClass1(AIFlickerTips.this, view);
            if (this.f16355k) {
                return;
            }
            view.addOnAttachStateChangeListener(this);
            this.f16355k = true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void g(Flicker flicker) {
            flicker.n();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n() {
            v();
        }

        private RectF q() {
            View view = this.f16353i;
            if (view == null || !AIFlickerTips.y(view) || !AIFlickerTips.this.w(p()) || !this.f16353i.isAttachedToWindow()) {
                return new RectF();
            }
            Rect rect = new Rect();
            Object tag = this.f16353i.getTag(539234818);
            if (tag instanceof Rect) {
                rect.set((Rect) tag);
            }
            int[] iArr = new int[2];
            this.f16353i.getLocationInWindow(iArr);
            int i2 = iArr[0];
            return new RectF(rect.left + i2, iArr[1] + rect.top, (i2 + this.f16353i.getWidth()) - rect.right, (iArr[1] + this.f16353i.getHeight()) - rect.bottom);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s() {
            if (TextUtils.equals(this.f16352h, p())) {
                this.f16353i.getViewTreeObserver().removeOnPreDrawListener(this.f16351c);
                this.f16353i.getViewTreeObserver().addOnPreDrawListener(this.f16351c);
                this.f16353i.postInvalidate();
            }
        }

        private void v() {
            if (this.f16354j == null) {
                return;
            }
            AIFlickerTips.H("releaseAnimator, mName = " + r() + " mAnimator=" + Integer.toHexString(this.f16354j.hashCode()));
            ValueAnimator valueAnimator = this.f16354j;
            this.f16354j = null;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }

        public boolean equals(Object obj) {
            boolean z;
            if (obj == null || !((z = obj instanceof Flicker))) {
                return false;
            }
            return z ? ((Flicker) obj).f16353i == this.f16353i : super.equals(obj);
        }

        public boolean m(MotionEvent motionEvent) {
            StringBuilder sb = new StringBuilder();
            try {
                ValueAnimator valueAnimator = this.f16354j;
                boolean z = valueAnimator != null && valueAnimator.isRunning();
                sb.append("ani=" + z);
                if (z) {
                    RectF q2 = q();
                    sb.append(" bounds=" + q2);
                    if (q2.contains(motionEvent.getX(), motionEvent.getY())) {
                        sb.append(" allow");
                        AIFlickerTips.G("allowRelease " + r() + "  " + ((Object) sb));
                        return true;
                    }
                }
                sb.append(" unallow");
                AIFlickerTips.G("allowRelease " + r() + "  " + ((Object) sb));
                return false;
            } catch (Throwable th) {
                AIFlickerTips.G("allowRelease " + r() + "  " + ((Object) sb));
                throw th;
            }
        }

        public void o(Canvas canvas) {
            RectF q2 = q();
            ValueAnimator valueAnimator = this.f16354j;
            float floatValue = valueAnimator != null ? ((Float) valueAnimator.getAnimatedValue()).floatValue() : 1.0f;
            View view = (View) this.f16353i.getParent();
            if (view == null) {
                AIFlickerTips.G("mName = " + r() + "  " + q2 + ", parent is null");
                return;
            }
            String str = "mName = " + r() + "  " + q2 + " lt=" + this.f16353i.getLeft() + "," + this.f16353i.getTop() + " txy=" + view.getTranslationX() + "," + view.getTranslationY() + " sxy=" + view.getScrollX() + "," + view.getScrollY();
            if (!TextUtils.equals(this.f16358n, str) && System.currentTimeMillis() - this.f16360p >= 250) {
                this.f16360p = System.currentTimeMillis();
                this.f16358n = str;
                AIFlickerTips.G(str);
            }
            Paint paint = AIFlickerTips.v().f16346e;
            paint.setColor(15547195);
            paint.setAlpha((int) (51.0f * floatValue));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(q2, paint);
            paint.setColor(15547195);
            paint.setAlpha((int) (floatValue * 102.0f));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(q2, paint);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (AIFlickerTips.this.f16343b.contains(this.f16352h)) {
                this.f16356l = true;
                AIFlickerTips.this.u().removeCallbacks(this.f16361q);
                AIFlickerTips.G("onViewAttachedToWindow " + r() + " view=" + this.f16353i + " getRootView=" + this.f16353i.getRootView());
                ViewGroup viewGroup = (ViewGroup) this.f16353i.getRootView();
                if (this.f16357m != null && viewGroup != null && (viewGroup.getTag(539234820) instanceof FlickerView)) {
                    FlickerView flickerView = (FlickerView) viewGroup.getTag(539234820);
                    View view2 = this.f16357m;
                    if (flickerView != view2) {
                        ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
                        if (viewGroup2 != null) {
                            viewGroup2.removeView(this.f16357m);
                        }
                        this.f16357m = null;
                    }
                }
                if (!AIFlickerTips.this.f16344c.contains(this.f16357m)) {
                    this.f16357m = null;
                }
                if (view.getRootView() instanceof FlickerView) {
                    this.f16357m = view.getRootView();
                }
                if (this.f16357m == null) {
                    this.f16357m = AIFlickerTips.this.new FlickerView(this.f16353i.getContext());
                    AIFlickerTips.this.f16344c.add(this.f16357m);
                    viewGroup.addView(this.f16357m);
                    viewGroup.setTag(539234820, this.f16357m);
                    this.f16357m.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.zte.gameassist.ai.AIFlickerTips.Flicker.2
                        @Override // android.view.View.OnLayoutChangeListener
                        public void onLayoutChange(View view3, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                            if ((Flicker.this.f16354j == null || !Flicker.this.f16354j.isStarted()) && Flicker.this.f16355k && !AIFlickerTips.this.u().hasCallbacks(Flicker.this.f16361q)) {
                                Flicker.this.s();
                            }
                            Flicker.this.f16357m.removeOnLayoutChangeListener(this);
                        }
                    });
                    return;
                }
                ValueAnimator valueAnimator = this.f16354j;
                if ((valueAnimator == null || !valueAnimator.isStarted()) && this.f16355k) {
                    s();
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (this.f16356l) {
                AIFlickerTips.G("onViewDetachedFromWindow " + r() + "  " + view);
                this.f16356l = false;
                AIFlickerTips.this.u().removeCallbacks(this.f16361q);
                AIFlickerTips.this.u().postDelayed(this.f16361q, 200L);
            }
        }

        public String p() {
            View view = this.f16353i;
            if (view == null) {
                return "null";
            }
            Object tag = view.getTag(539234817);
            return tag instanceof String ? (String) tag : "null";
        }

        public String r() {
            return this.f16352h + "@" + Integer.toHexString(this.f16353i.hashCode());
        }

        public void t() {
            u(false);
        }

        public void u(boolean z) {
            if (z) {
                AIFlickerTips.G("release mName = " + r() + " attach=" + this.f16353i.isAttachedToWindow() + ":" + this.f16356l);
            }
            this.f16358n = "";
            this.f16359o = "";
            if (this.f16356l) {
                return;
            }
            if (this.f16355k) {
                this.f16353i.removeOnAttachStateChangeListener(this);
                this.f16355k = false;
            }
            this.f16353i.getViewTreeObserver().removeOnPreDrawListener(this.f16351c);
            List list = (List) AIFlickerTips.this.f16342a.get(this.f16352h);
            if (list != null) {
                list.remove(this);
            }
            v();
        }

        public void w() {
            boolean isAttachedToWindow = this.f16353i.isAttachedToWindow();
            boolean z = this.f16356l;
            View view = this.f16357m;
            boolean z2 = view != null && view.isAttachedToWindow();
            ValueAnimator valueAnimator = this.f16354j;
            AIFlickerTips.G("showAnimation " + r() + " a1=" + isAttachedToWindow + " a2=" + z + " a3=" + z2 + " animator=" + (valueAnimator == null || !valueAnimator.isStarted()) + " view=" + this.f16353i);
            if (isAttachedToWindow) {
                onViewAttachedToWindow(this.f16353i);
            }
        }
    }

    private class FlickerView extends View implements View.OnLayoutChangeListener {
        private final CheckFlicker mCheckFlicker;
        private boolean mIsFingerTouch;

        private class CheckFlicker implements Runnable {

            /* renamed from: c, reason: collision with root package name */
            private MotionEvent f16368c;

            private CheckFlicker() {
            }

            public void a(MotionEvent motionEvent) {
                MotionEvent motionEvent2 = this.f16368c;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                    this.f16368c = null;
                }
                this.f16368c = MotionEvent.obtain(motionEvent);
                AIFlickerTips.this.u().removeCallbacks(this);
                AIFlickerTips.this.u().postDelayed(this, 20L);
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f16368c == null) {
                    return;
                }
                try {
                    Iterator it = AIFlickerTips.this.f16343b.iterator();
                    long j2 = 0;
                    while (it.hasNext()) {
                        List<Flicker> list = (List) AIFlickerTips.this.f16342a.get((String) it.next());
                        if (list != null && list.size() > 0) {
                            for (Flicker flicker : list) {
                                if (flicker.m(this.f16368c)) {
                                    AIFlickerTips.this.I();
                                    return;
                                } else if (flicker.f16354j != null && flicker.f16354j.isRunning()) {
                                    j2++;
                                }
                            }
                        }
                    }
                    if (j2 == 0 && AIFlickerTips.this.f16343b.size() > 0) {
                        AIFlickerTips.G("allowRelease when no ani");
                        AIFlickerTips.this.I();
                    }
                } finally {
                    this.f16368c.recycle();
                    this.f16368c = null;
                }
            }
        }

        public FlickerView(Context context) {
            super(context);
            this.mCheckFlicker = new CheckFlicker();
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 0) {
                this.mIsFingerTouch = true;
                this.mCheckFlicker.a(motionEvent);
            } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                this.mIsFingerTouch = false;
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            getRootView().addOnLayoutChangeListener(this);
        }

        @Override // android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            getRootView().removeOnLayoutChangeListener(this);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            AIFlickerTips.v().t(canvas);
            if (!this.mIsFingerTouch || AIFlickerTips.this.f16343b.isEmpty()) {
                return;
            }
            postInvalidate();
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            layout(i2, i3, i4, i5);
        }
    }

    private AIFlickerTips() {
        Paint paint = new Paint();
        this.f16346e = paint;
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(final Canvas canvas, String str) {
        List list = (List) this.f16342a.get(str);
        if (list != null && list.size() != 0) {
            ((List) this.f16342a.get(str)).forEach(new Consumer() { // from class: com.zte.gameassist.ai.f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AIFlickerTips.Flicker) obj).o(canvas);
                }
            });
            return;
        }
        G(str + " filckers is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(String str) {
        List list = (List) this.f16342a.get(str);
        if (list != null) {
            list.forEach(new Consumer() { // from class: com.zte.gameassist.ai.h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AIFlickerTips.Flicker) obj).w();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D() {
        this.f16343b.forEach(new Consumer() { // from class: com.zte.gameassist.ai.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AIFlickerTips.this.C((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void F(int i2, String str, List list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            ((Flicker) list.get(size)).u(i2 < 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void G(String str) {
        Log.i("AIFlickerTips_0.0520", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void H(String str) {
        if (f16339h) {
            G(str);
        } else {
            Log.v("AIFlickerTips_0.0520", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        final int sum = this.f16342a.values().stream().mapToInt(new ToIntFunction() { // from class: com.zte.gameassist.ai.d
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int size;
                size = ((List) obj).size();
                return size;
            }
        }).sum();
        G("release = " + this.f16343b + " flickers count =" + sum);
        this.f16343b.clear();
        this.f16342a.forEach(new BiConsumer() { // from class: com.zte.gameassist.ai.e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AIFlickerTips.F(sum, (String) obj, (List) obj2);
            }
        });
        for (int size = this.f16344c.size() + (-1); size >= 0; size--) {
            View view = (View) this.f16344c.remove(size);
            if (view != null) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup == null) {
                    return;
                }
                viewGroup.removeView(view);
                viewGroup.setTag(539234820, null);
            }
        }
    }

    public static void J(View view, String str) {
        if (view == null || str == null) {
            return;
        }
        v().r(str, view);
        if (v().w(str)) {
            H("setFlickerName view=" + view.getClass().getSimpleName() + " name=" + str + "@" + Integer.toHexString(view.hashCode()));
            M(str);
        }
    }

    public static void K(View view, Rect rect) {
        L(view, new RectF(rect));
    }

    public static void L(View view, RectF rectF) {
        view.setTag(539234818, rectF);
    }

    public static void M(String str) {
        N(str, 0L);
    }

    public static void N(String str, long j2) {
        v().O(str, j2);
    }

    private static float s(Context context, float f2) {
        return TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(final Canvas canvas) {
        this.f16343b.forEach(new Consumer() { // from class: com.zte.gameassist.ai.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AIFlickerTips.this.B(canvas, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Handler u() {
        Handler handler = (Handler) this.f16345d.get();
        if (handler != null) {
            return handler;
        }
        Handler handler2 = new Handler(Looper.myLooper());
        this.f16345d.set(handler2);
        return handler2;
    }

    public static AIFlickerTips v() {
        if (f16340i == null) {
            synchronized (AIFlickerTips.class) {
                try {
                    if (f16340i == null) {
                        f16340i = new AIFlickerTips();
                    }
                } finally {
                }
            }
        }
        return f16340i;
    }

    public static void x() {
        v().I();
    }

    public static boolean y(View view) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null) {
            return false;
        }
        return view.getParent() instanceof View ? y((View) view.getParent()) : view.getVisibility() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void z(View view, String str, List list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            Flicker flicker = (Flicker) list.get(size);
            if (flicker.f16353i == view) {
                flicker.t();
            }
        }
    }

    public void O(String str, long j2) {
        if (!this.f16343b.contains(str)) {
            this.f16343b.add(str);
        }
        u().removeCallbacks(this.f16348g);
        u().postDelayed(this.f16348g, 100L);
    }

    public void r(String str, final View view) {
        if (this.f16346e.getStrokeWidth() == 0.0f) {
            this.f16346e.setStrokeWidth(s(view.getContext(), 1.0f));
        }
        this.f16342a.forEach(new BiConsumer() { // from class: com.zte.gameassist.ai.a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                AIFlickerTips.z(view, (String) obj, (List) obj2);
            }
        });
        List list = (List) this.f16342a.get(str);
        if (list == null) {
            list = new ArrayList();
            this.f16342a.put(str, list);
        }
        Flicker flicker = new Flicker(str, view);
        if (list.contains(flicker)) {
            return;
        }
        list.add(flicker);
    }

    public String toString() {
        return "AIFlickerTips : show=" + this.f16343b + " all=" + ((String) this.f16342a.keySet().stream().collect(Collectors.joining(",")));
    }

    public boolean w(String str) {
        return this.f16343b.contains(str);
    }
}
