package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    private static final boolean a0 = false;
    private static final List b0 = Arrays.asList("reduced motion", "reduced_motion", "reduced-motion", "reducedmotion");
    private static final Executor c0 = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new LottieThreadFactory());
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private RenderMode E;
    private boolean F;
    private final Matrix G;
    private Bitmap H;
    private Canvas I;
    private Rect J;
    private RectF K;
    private Paint L;
    private Rect M;
    private Rect N;
    private RectF O;
    private RectF P;
    private Matrix Q;
    private Matrix R;
    private boolean S;
    private AsyncUpdates T;
    private final ValueAnimator.AnimatorUpdateListener U;
    private final Semaphore V;
    private Handler W;
    private Runnable X;
    private final Runnable Y;
    private float Z;

    /* renamed from: c, reason: collision with root package name */
    private LottieComposition f9285c;

    /* renamed from: h, reason: collision with root package name */
    private final LottieValueAnimator f9286h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f9287i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f9288j;

    /* renamed from: k, reason: collision with root package name */
    private boolean f9289k;

    /* renamed from: l, reason: collision with root package name */
    private OnVisibleAction f9290l;

    /* renamed from: m, reason: collision with root package name */
    private final ArrayList f9291m;

    /* renamed from: n, reason: collision with root package name */
    private ImageAssetManager f9292n;

    /* renamed from: o, reason: collision with root package name */
    private String f9293o;

    /* renamed from: p, reason: collision with root package name */
    private ImageAssetDelegate f9294p;

    /* renamed from: q, reason: collision with root package name */
    private FontAssetManager f9295q;

    /* renamed from: r, reason: collision with root package name */
    private Map f9296r;

    /* renamed from: s, reason: collision with root package name */
    String f9297s;
    FontAssetDelegate t;
    TextDelegate u;
    private boolean v;
    private boolean w;
    private boolean x;
    private CompositionLayer y;
    private int z;

    /* renamed from: com.airbnb.lottie.LottieDrawable$1, reason: invalid class name */
    class AnonymousClass1 extends LottieValueCallback<Object> {

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ SimpleLottieValueCallback f9298d;

        @Override // com.airbnb.lottie.value.LottieValueCallback
        public Object a(LottieFrameInfo lottieFrameInfo) {
            return this.f9298d.a(lottieFrameInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface LazyCompositionTask {
        void a(LottieComposition lottieComposition);
    }

    private enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.f9286h = lottieValueAnimator;
        this.f9287i = true;
        this.f9288j = false;
        this.f9289k = false;
        this.f9290l = OnVisibleAction.NONE;
        this.f9291m = new ArrayList();
        this.w = false;
        this.x = true;
        this.z = 255;
        this.D = false;
        this.E = RenderMode.AUTOMATIC;
        this.F = false;
        this.G = new Matrix();
        this.S = false;
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.airbnb.lottie.v
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                LottieDrawable.this.g0(valueAnimator);
            }
        };
        this.U = animatorUpdateListener;
        this.V = new Semaphore(1);
        this.Y = new Runnable() { // from class: com.airbnb.lottie.w
            @Override // java.lang.Runnable
            public final void run() {
                LottieDrawable.this.i0();
            }
        };
        this.Z = -3.4028235E38f;
        lottieValueAnimator.addUpdateListener(animatorUpdateListener);
    }

    private void A0(RectF rectF, float f2, float f3) {
        rectF.set(rectF.left * f2, rectF.top * f3, rectF.right * f2, rectF.bottom * f3);
    }

    private void B(int i2, int i3) {
        Bitmap bitmap = this.H;
        if (bitmap == null || bitmap.getWidth() < i2 || this.H.getHeight() < i3) {
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.H = createBitmap;
            this.I.setBitmap(createBitmap);
            this.S = true;
            return;
        }
        if (this.H.getWidth() > i2 || this.H.getHeight() > i3) {
            Bitmap createBitmap2 = Bitmap.createBitmap(this.H, 0, 0, i2, i3);
            this.H = createBitmap2;
            this.I.setBitmap(createBitmap2);
            this.S = true;
        }
    }

    private void C() {
        if (this.I != null) {
            return;
        }
        this.I = new Canvas();
        this.P = new RectF();
        this.Q = new Matrix();
        this.R = new Matrix();
        this.J = new Rect();
        this.K = new RectF();
        this.L = new LPaint();
        this.M = new Rect();
        this.N = new Rect();
        this.O = new RectF();
    }

    private Context J() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    private FontAssetManager K() {
        if (getCallback() == null) {
            return null;
        }
        if (this.f9295q == null) {
            FontAssetManager fontAssetManager = new FontAssetManager(getCallback(), this.t);
            this.f9295q = fontAssetManager;
            String str = this.f9297s;
            if (str != null) {
                fontAssetManager.c(str);
            }
        }
        return this.f9295q;
    }

    private ImageAssetManager M() {
        ImageAssetManager imageAssetManager = this.f9292n;
        if (imageAssetManager != null && !imageAssetManager.b(J())) {
            this.f9292n = null;
        }
        if (this.f9292n == null) {
            this.f9292n = new ImageAssetManager(getCallback(), this.f9293o, this.f9294p, this.f9285c.j());
        }
        return this.f9292n;
    }

    private Marker Q() {
        Iterator it = b0.iterator();
        Marker marker = null;
        while (it.hasNext()) {
            marker = this.f9285c.l((String) it.next());
            if (marker != null) {
                break;
            }
        }
        return marker;
    }

    private boolean b0() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        if (((View) callback).getParent() instanceof ViewGroup) {
            return !((ViewGroup) r2).getClipChildren();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f0(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback, LottieComposition lottieComposition) {
        q(keyPath, obj, lottieValueCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g0(ValueAnimator valueAnimator) {
        if (E()) {
            invalidateSelf();
            return;
        }
        CompositionLayer compositionLayer = this.y;
        if (compositionLayer != null) {
            compositionLayer.N(this.f9286h.j());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h0() {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    private boolean h1() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return false;
        }
        float f2 = this.Z;
        float j2 = this.f9286h.j();
        this.Z = j2;
        return Math.abs(j2 - f2) * lottieComposition.d() >= 50.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void i0() {
        CompositionLayer compositionLayer = this.y;
        if (compositionLayer == null) {
            return;
        }
        try {
            this.V.acquire();
            compositionLayer.N(this.f9286h.j());
            if (a0 && this.S) {
                if (this.W == null) {
                    this.W = new Handler(Looper.getMainLooper());
                    this.X = new Runnable() { // from class: com.airbnb.lottie.r
                        @Override // java.lang.Runnable
                        public final void run() {
                            LottieDrawable.this.h0();
                        }
                    };
                }
                this.W.post(this.X);
            }
        } catch (InterruptedException unused) {
        } catch (Throwable th) {
            this.V.release();
            throw th;
        }
        this.V.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0(LottieComposition lottieComposition) {
        w0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k0(LottieComposition lottieComposition) {
        z0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l0(int i2, LottieComposition lottieComposition) {
        J0(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m0(String str, LottieComposition lottieComposition) {
        P0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n0(int i2, LottieComposition lottieComposition) {
        O0(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o0(float f2, LottieComposition lottieComposition) {
        Q0(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p0(String str, LottieComposition lottieComposition) {
        S0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q0(int i2, int i3, LottieComposition lottieComposition) {
        R0(i2, i3);
    }

    private boolean r() {
        return this.f9287i || this.f9288j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r0(int i2, LottieComposition lottieComposition) {
        T0(i2);
    }

    private void s() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return;
        }
        CompositionLayer compositionLayer = new CompositionLayer(this, LayerParser.a(lottieComposition), lottieComposition.k(), lottieComposition);
        this.y = compositionLayer;
        if (this.B) {
            compositionLayer.L(true);
        }
        this.y.R(this.x);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s0(String str, LottieComposition lottieComposition) {
        U0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t0(float f2, LottieComposition lottieComposition) {
        V0(f2);
    }

    private void u() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return;
        }
        this.F = this.E.d(Build.VERSION.SDK_INT, lottieComposition.q(), lottieComposition.m());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u0(float f2, LottieComposition lottieComposition) {
        Y0(f2);
    }

    private void v(Rect rect, RectF rectF) {
        rectF.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    private void w(RectF rectF, Rect rect) {
        rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    private void x(Canvas canvas) {
        CompositionLayer compositionLayer = this.y;
        LottieComposition lottieComposition = this.f9285c;
        if (compositionLayer == null || lottieComposition == null) {
            return;
        }
        this.G.reset();
        if (!getBounds().isEmpty()) {
            this.G.preScale(r2.width() / lottieComposition.b().width(), r2.height() / lottieComposition.b().height());
            this.G.preTranslate(r2.left, r2.top);
        }
        compositionLayer.i(canvas, this.G, this.z);
    }

    private void x0(Canvas canvas, CompositionLayer compositionLayer) {
        if (this.f9285c == null || compositionLayer == null) {
            return;
        }
        C();
        canvas.getMatrix(this.Q);
        canvas.getClipBounds(this.J);
        v(this.J, this.K);
        this.Q.mapRect(this.K);
        w(this.K, this.J);
        if (this.x) {
            this.P.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        } else {
            compositionLayer.g(this.P, null, false);
        }
        this.Q.mapRect(this.P);
        Rect bounds = getBounds();
        float width = bounds.width() / getIntrinsicWidth();
        float height = bounds.height() / getIntrinsicHeight();
        A0(this.P, width, height);
        if (!b0()) {
            RectF rectF = this.P;
            Rect rect = this.J;
            rectF.intersect(rect.left, rect.top, rect.right, rect.bottom);
        }
        int ceil = (int) Math.ceil(this.P.width());
        int ceil2 = (int) Math.ceil(this.P.height());
        if (ceil <= 0 || ceil2 <= 0) {
            return;
        }
        B(ceil, ceil2);
        if (this.S) {
            this.G.set(this.Q);
            this.G.preScale(width, height);
            Matrix matrix = this.G;
            RectF rectF2 = this.P;
            matrix.postTranslate(-rectF2.left, -rectF2.top);
            this.H.eraseColor(0);
            compositionLayer.i(this.I, this.G, this.z);
            this.Q.invert(this.R);
            this.R.mapRect(this.O, this.P);
            w(this.O, this.N);
        }
        this.M.set(0, 0, ceil, ceil2);
        canvas.drawBitmap(this.H, this.M, this.N, this.L);
    }

    public void A() {
        this.f9291m.clear();
        this.f9286h.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f9290l = OnVisibleAction.NONE;
    }

    public void B0(boolean z) {
        this.C = z;
    }

    public void C0(AsyncUpdates asyncUpdates) {
        this.T = asyncUpdates;
    }

    public AsyncUpdates D() {
        AsyncUpdates asyncUpdates = this.T;
        return asyncUpdates != null ? asyncUpdates : L.d();
    }

    public void D0(boolean z) {
        if (z != this.D) {
            this.D = z;
            invalidateSelf();
        }
    }

    public boolean E() {
        return D() == AsyncUpdates.ENABLED;
    }

    public void E0(boolean z) {
        if (z != this.x) {
            this.x = z;
            CompositionLayer compositionLayer = this.y;
            if (compositionLayer != null) {
                compositionLayer.R(z);
            }
            invalidateSelf();
        }
    }

    public Bitmap F(String str) {
        ImageAssetManager M = M();
        if (M != null) {
            return M.a(str);
        }
        return null;
    }

    public boolean F0(LottieComposition lottieComposition) {
        if (this.f9285c == lottieComposition) {
            return false;
        }
        this.S = true;
        t();
        this.f9285c = lottieComposition;
        s();
        this.f9286h.x(lottieComposition);
        Y0(this.f9286h.getAnimatedFraction());
        Iterator it = new ArrayList(this.f9291m).iterator();
        while (it.hasNext()) {
            LazyCompositionTask lazyCompositionTask = (LazyCompositionTask) it.next();
            if (lazyCompositionTask != null) {
                lazyCompositionTask.a(lottieComposition);
            }
            it.remove();
        }
        this.f9291m.clear();
        lottieComposition.v(this.A);
        u();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable(null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public boolean G() {
        return this.D;
    }

    public void G0(String str) {
        this.f9297s = str;
        FontAssetManager K = K();
        if (K != null) {
            K.c(str);
        }
    }

    public boolean H() {
        return this.x;
    }

    public void H0(FontAssetDelegate fontAssetDelegate) {
        this.t = fontAssetDelegate;
        FontAssetManager fontAssetManager = this.f9295q;
        if (fontAssetManager != null) {
            fontAssetManager.d(fontAssetDelegate);
        }
    }

    public LottieComposition I() {
        return this.f9285c;
    }

    public void I0(Map map) {
        if (map == this.f9296r) {
            return;
        }
        this.f9296r = map;
        invalidateSelf();
    }

    public void J0(final int i2) {
        if (this.f9285c == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.A
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.l0(i2, lottieComposition);
                }
            });
        } else {
            this.f9286h.y(i2);
        }
    }

    public void K0(boolean z) {
        this.f9288j = z;
    }

    public int L() {
        return (int) this.f9286h.k();
    }

    public void L0(ImageAssetDelegate imageAssetDelegate) {
        this.f9294p = imageAssetDelegate;
        ImageAssetManager imageAssetManager = this.f9292n;
        if (imageAssetManager != null) {
            imageAssetManager.d(imageAssetDelegate);
        }
    }

    public void M0(String str) {
        this.f9293o = str;
    }

    public String N() {
        return this.f9293o;
    }

    public void N0(boolean z) {
        this.w = z;
    }

    public LottieImageAsset O(String str) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return null;
        }
        return (LottieImageAsset) lottieComposition.j().get(str);
    }

    public void O0(final int i2) {
        if (this.f9285c == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.m
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.n0(i2, lottieComposition);
                }
            });
        } else {
            this.f9286h.z(i2 + 0.99f);
        }
    }

    public boolean P() {
        return this.w;
    }

    public void P0(final String str) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.t
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.m0(str, lottieComposition2);
                }
            });
            return;
        }
        Marker l2 = lottieComposition.l(str);
        if (l2 != null) {
            O0((int) (l2.f9619b + l2.f9620c));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void Q0(final float f2) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.p
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.o0(f2, lottieComposition2);
                }
            });
        } else {
            this.f9286h.z(MiscUtils.i(lottieComposition.p(), this.f9285c.f(), f2));
        }
    }

    public float R() {
        return this.f9286h.m();
    }

    public void R0(final int i2, final int i3) {
        if (this.f9285c == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.q
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.q0(i2, i3, lottieComposition);
                }
            });
        } else {
            this.f9286h.A(i2, i3 + 0.99f);
        }
    }

    public float S() {
        return this.f9286h.n();
    }

    public void S0(final String str) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.l
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.p0(str, lottieComposition2);
                }
            });
            return;
        }
        Marker l2 = lottieComposition.l(str);
        if (l2 != null) {
            int i2 = (int) l2.f9619b;
            R0(i2, ((int) l2.f9620c) + i2);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public PerformanceTracker T() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition != null) {
            return lottieComposition.n();
        }
        return null;
    }

    public void T0(final int i2) {
        if (this.f9285c == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.n
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.r0(i2, lottieComposition);
                }
            });
        } else {
            this.f9286h.B(i2);
        }
    }

    public float U() {
        return this.f9286h.j();
    }

    public void U0(final String str) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.u
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.s0(str, lottieComposition2);
                }
            });
            return;
        }
        Marker l2 = lottieComposition.l(str);
        if (l2 != null) {
            T0((int) l2.f9619b);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public RenderMode V() {
        return this.F ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    public void V0(final float f2) {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.y
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition2) {
                    LottieDrawable.this.t0(f2, lottieComposition2);
                }
            });
        } else {
            T0((int) MiscUtils.i(lottieComposition.p(), this.f9285c.f(), f2));
        }
    }

    public int W() {
        return this.f9286h.getRepeatCount();
    }

    public void W0(boolean z) {
        if (this.B == z) {
            return;
        }
        this.B = z;
        CompositionLayer compositionLayer = this.y;
        if (compositionLayer != null) {
            compositionLayer.L(z);
        }
    }

    public int X() {
        return this.f9286h.getRepeatMode();
    }

    public void X0(boolean z) {
        this.A = z;
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition != null) {
            lottieComposition.v(z);
        }
    }

    public float Y() {
        return this.f9286h.o();
    }

    public void Y0(final float f2) {
        if (this.f9285c == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.z
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.u0(f2, lottieComposition);
                }
            });
            return;
        }
        L.b("Drawable#setProgress");
        this.f9286h.y(this.f9285c.h(f2));
        L.c("Drawable#setProgress");
    }

    public TextDelegate Z() {
        return this.u;
    }

    public void Z0(RenderMode renderMode) {
        this.E = renderMode;
        u();
    }

    public Typeface a0(Font font) {
        Map map = this.f9296r;
        if (map != null) {
            String a2 = font.a();
            if (map.containsKey(a2)) {
                return (Typeface) map.get(a2);
            }
            String b2 = font.b();
            if (map.containsKey(b2)) {
                return (Typeface) map.get(b2);
            }
            String str = font.a() + "-" + font.c();
            if (map.containsKey(str)) {
                return (Typeface) map.get(str);
            }
        }
        FontAssetManager K = K();
        if (K != null) {
            return K.b(font);
        }
        return null;
    }

    public void a1(int i2) {
        this.f9286h.setRepeatCount(i2);
    }

    public void b1(int i2) {
        this.f9286h.setRepeatMode(i2);
    }

    public boolean c0() {
        LottieValueAnimator lottieValueAnimator = this.f9286h;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    public void c1(boolean z) {
        this.f9289k = z;
    }

    boolean d0() {
        if (isVisible()) {
            return this.f9286h.isRunning();
        }
        OnVisibleAction onVisibleAction = this.f9290l;
        return onVisibleAction == OnVisibleAction.PLAY || onVisibleAction == OnVisibleAction.RESUME;
    }

    public void d1(float f2) {
        this.f9286h.C(f2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        CompositionLayer compositionLayer = this.y;
        if (compositionLayer == null) {
            return;
        }
        boolean E = E();
        if (E) {
            try {
                this.V.acquire();
            } catch (InterruptedException unused) {
                L.c("Drawable#draw");
                if (!E) {
                    return;
                }
                this.V.release();
                if (compositionLayer.Q() == this.f9286h.j()) {
                    return;
                }
            } catch (Throwable th) {
                L.c("Drawable#draw");
                if (E) {
                    this.V.release();
                    if (compositionLayer.Q() != this.f9286h.j()) {
                        c0.execute(this.Y);
                    }
                }
                throw th;
            }
        }
        L.b("Drawable#draw");
        if (E && h1()) {
            Y0(this.f9286h.j());
        }
        if (this.f9289k) {
            try {
                if (this.F) {
                    x0(canvas, compositionLayer);
                } else {
                    x(canvas);
                }
            } catch (Throwable th2) {
                Logger.b("Lottie crashed in draw!", th2);
            }
        } else if (this.F) {
            x0(canvas, compositionLayer);
        } else {
            x(canvas);
        }
        this.S = false;
        L.c("Drawable#draw");
        if (E) {
            this.V.release();
            if (compositionLayer.Q() == this.f9286h.j()) {
                return;
            }
            c0.execute(this.Y);
        }
    }

    public boolean e0() {
        return this.C;
    }

    public void e1(Boolean bool) {
        this.f9287i = bool.booleanValue();
    }

    public void f1(TextDelegate textDelegate) {
        this.u = textDelegate;
    }

    public void g1(boolean z) {
        this.f9286h.D(z);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.z;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.b().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.f9285c;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.b().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean i1() {
        return this.f9296r == null && this.u == null && this.f9285c.c().j() > 0;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable.Callback callback;
        if (this.S) {
            return;
        }
        this.S = true;
        if ((!a0 || Looper.getMainLooper() == Looper.myLooper()) && (callback = getCallback()) != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return c0();
    }

    public void q(final KeyPath keyPath, final Object obj, final LottieValueCallback lottieValueCallback) {
        CompositionLayer compositionLayer = this.y;
        if (compositionLayer == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.o
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.f0(keyPath, obj, lottieValueCallback, lottieComposition);
                }
            });
            return;
        }
        if (keyPath == KeyPath.f9613c) {
            compositionLayer.e(obj, lottieValueCallback);
        } else if (keyPath.d() != null) {
            keyPath.d().e(obj, lottieValueCallback);
        } else {
            List y0 = y0(keyPath);
            for (int i2 = 0; i2 < y0.size(); i2++) {
                ((KeyPath) y0.get(i2)).d().e(obj, lottieValueCallback);
            }
            if (!(!y0.isEmpty())) {
                return;
            }
        }
        invalidateSelf();
        if (obj == LottieProperty.E) {
            Y0(U());
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.z = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Logger.c("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean z3 = !isVisible();
        boolean visible = super.setVisible(z, z2);
        if (z) {
            OnVisibleAction onVisibleAction = this.f9290l;
            if (onVisibleAction == OnVisibleAction.PLAY) {
                w0();
            } else if (onVisibleAction == OnVisibleAction.RESUME) {
                z0();
            }
        } else if (this.f9286h.isRunning()) {
            v0();
            this.f9290l = OnVisibleAction.RESUME;
        } else if (!z3) {
            this.f9290l = OnVisibleAction.NONE;
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        w0();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        A();
    }

    public void t() {
        if (this.f9286h.isRunning()) {
            this.f9286h.cancel();
            if (!isVisible()) {
                this.f9290l = OnVisibleAction.NONE;
            }
        }
        this.f9285c = null;
        this.y = null;
        this.f9292n = null;
        this.Z = -3.4028235E38f;
        this.f9286h.i();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    public void v0() {
        this.f9291m.clear();
        this.f9286h.q();
        if (isVisible()) {
            return;
        }
        this.f9290l = OnVisibleAction.NONE;
    }

    public void w0() {
        if (this.y == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.x
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.j0(lottieComposition);
                }
            });
            return;
        }
        u();
        if (r() || W() == 0) {
            if (isVisible()) {
                this.f9286h.r();
                this.f9290l = OnVisibleAction.NONE;
            } else {
                this.f9290l = OnVisibleAction.PLAY;
            }
        }
        if (r()) {
            return;
        }
        Marker Q = Q();
        if (Q != null) {
            J0((int) Q.f9619b);
        } else {
            J0((int) (Y() < 0.0f ? S() : R()));
        }
        this.f9286h.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f9290l = OnVisibleAction.NONE;
    }

    public void y(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (this.f9285c != null) {
            s();
        }
    }

    public List y0(KeyPath keyPath) {
        if (this.y == null) {
            Logger.c("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.y.f(keyPath, 0, arrayList, new KeyPath(new String[0]));
        return arrayList;
    }

    public boolean z() {
        return this.v;
    }

    public void z0() {
        if (this.y == null) {
            this.f9291m.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.s
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void a(LottieComposition lottieComposition) {
                    LottieDrawable.this.k0(lottieComposition);
                }
            });
            return;
        }
        u();
        if (r() || W() == 0) {
            if (isVisible()) {
                this.f9286h.v();
                this.f9290l = OnVisibleAction.NONE;
            } else {
                this.f9290l = OnVisibleAction.RESUME;
            }
        }
        if (r()) {
            return;
        }
        J0((int) (Y() < 0.0f ? S() : R()));
        this.f9286h.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f9290l = OnVisibleAction.NONE;
    }
}
