package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private Paint A;
    float B;
    BlurMaskFilter C;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9737a = new Path();

    /* renamed from: b, reason: collision with root package name */
    private final Matrix f9738b = new Matrix();

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f9739c = new Matrix();

    /* renamed from: d, reason: collision with root package name */
    private final Paint f9740d = new LPaint(1);

    /* renamed from: e, reason: collision with root package name */
    private final Paint f9741e;

    /* renamed from: f, reason: collision with root package name */
    private final Paint f9742f;

    /* renamed from: g, reason: collision with root package name */
    private final Paint f9743g;

    /* renamed from: h, reason: collision with root package name */
    private final Paint f9744h;

    /* renamed from: i, reason: collision with root package name */
    private final RectF f9745i;

    /* renamed from: j, reason: collision with root package name */
    private final RectF f9746j;

    /* renamed from: k, reason: collision with root package name */
    private final RectF f9747k;

    /* renamed from: l, reason: collision with root package name */
    private final RectF f9748l;

    /* renamed from: m, reason: collision with root package name */
    private final RectF f9749m;

    /* renamed from: n, reason: collision with root package name */
    private final String f9750n;

    /* renamed from: o, reason: collision with root package name */
    final Matrix f9751o;

    /* renamed from: p, reason: collision with root package name */
    final LottieDrawable f9752p;

    /* renamed from: q, reason: collision with root package name */
    final Layer f9753q;

    /* renamed from: r, reason: collision with root package name */
    private MaskKeyframeAnimation f9754r;

    /* renamed from: s, reason: collision with root package name */
    private FloatKeyframeAnimation f9755s;
    private BaseLayer t;
    private BaseLayer u;
    private List v;
    private final List w;
    final TransformKeyframeAnimation x;
    private boolean y;
    private boolean z;

    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9756a;

        /* renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f9757b;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            f9757b = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9757b[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9757b[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9757b[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            f9756a = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9756a[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9756a[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9756a[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9756a[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9756a[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9756a[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    BaseLayer(LottieDrawable lottieDrawable, Layer layer) {
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.f9741e = new LPaint(1, mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.f9742f = new LPaint(1, mode2);
        LPaint lPaint = new LPaint(1);
        this.f9743g = lPaint;
        this.f9744h = new LPaint(PorterDuff.Mode.CLEAR);
        this.f9745i = new RectF();
        this.f9746j = new RectF();
        this.f9747k = new RectF();
        this.f9748l = new RectF();
        this.f9749m = new RectF();
        this.f9751o = new Matrix();
        this.w = new ArrayList();
        this.y = true;
        this.B = 0.0f;
        this.f9752p = lottieDrawable;
        this.f9753q = layer;
        this.f9750n = layer.j() + "#draw";
        if (layer.i() == Layer.MatteType.INVERT) {
            lPaint.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            lPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        TransformKeyframeAnimation b2 = layer.x().b();
        this.x = b2;
        b2.b(this);
        if (layer.h() != null && !layer.h().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation = new MaskKeyframeAnimation(layer.h());
            this.f9754r = maskKeyframeAnimation;
            Iterator it = maskKeyframeAnimation.a().iterator();
            while (it.hasNext()) {
                ((BaseKeyframeAnimation) it.next()).a(this);
            }
            for (BaseKeyframeAnimation baseKeyframeAnimation : this.f9754r.c()) {
                j(baseKeyframeAnimation);
                baseKeyframeAnimation.a(this);
            }
        }
        P();
    }

    private void D(RectF rectF, Matrix matrix) {
        this.f9747k.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (B()) {
            int size = this.f9754r.b().size();
            for (int i2 = 0; i2 < size; i2++) {
                Mask mask = (Mask) this.f9754r.b().get(i2);
                Path path = (Path) ((BaseKeyframeAnimation) this.f9754r.a().get(i2)).h();
                if (path != null) {
                    this.f9737a.set(path);
                    this.f9737a.transform(matrix);
                    int i3 = AnonymousClass1.f9757b[mask.a().ordinal()];
                    if (i3 == 1 || i3 == 2) {
                        return;
                    }
                    if ((i3 == 3 || i3 == 4) && mask.d()) {
                        return;
                    }
                    this.f9737a.computeBounds(this.f9749m, false);
                    if (i2 == 0) {
                        this.f9747k.set(this.f9749m);
                    } else {
                        RectF rectF2 = this.f9747k;
                        rectF2.set(Math.min(rectF2.left, this.f9749m.left), Math.min(this.f9747k.top, this.f9749m.top), Math.max(this.f9747k.right, this.f9749m.right), Math.max(this.f9747k.bottom, this.f9749m.bottom));
                    }
                }
            }
            if (rectF.intersect(this.f9747k)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void E(RectF rectF, Matrix matrix) {
        if (C() && this.f9753q.i() != Layer.MatteType.INVERT) {
            this.f9748l.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.t.g(this.f9748l, matrix, true);
            if (rectF.intersect(this.f9748l)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void F() {
        this.f9752p.invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G() {
        O(this.f9755s.q() == 1.0f);
    }

    private void H(float f2) {
        this.f9752p.I().n().a(this.f9753q.j(), f2);
    }

    private void O(boolean z) {
        if (z != this.y) {
            this.y = z;
            F();
        }
    }

    private void P() {
        if (this.f9753q.f().isEmpty()) {
            O(true);
            return;
        }
        FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.f9753q.f());
        this.f9755s = floatKeyframeAnimation;
        floatKeyframeAnimation.m();
        this.f9755s.a(new BaseKeyframeAnimation.AnimationListener() { // from class: com.airbnb.lottie.model.layer.a
            @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
            public final void a() {
                BaseLayer.this.G();
            }
        });
        O(((Float) this.f9755s.h()).floatValue() == 1.0f);
        j(this.f9755s);
    }

    private void k(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        this.f9740d.setAlpha((int) (((Integer) baseKeyframeAnimation2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f9737a, this.f9740d);
    }

    private void l(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.m(canvas, this.f9745i, this.f9741e);
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        this.f9740d.setAlpha((int) (((Integer) baseKeyframeAnimation2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f9737a, this.f9740d);
        canvas.restore();
    }

    private void m(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.m(canvas, this.f9745i, this.f9740d);
        canvas.drawRect(this.f9745i, this.f9740d);
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        this.f9740d.setAlpha((int) (((Integer) baseKeyframeAnimation2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f9737a, this.f9742f);
        canvas.restore();
    }

    private void n(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.m(canvas, this.f9745i, this.f9741e);
        canvas.drawRect(this.f9745i, this.f9740d);
        this.f9742f.setAlpha((int) (((Integer) baseKeyframeAnimation2.h()).intValue() * 2.55f));
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        canvas.drawPath(this.f9737a, this.f9742f);
        canvas.restore();
    }

    private void o(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.m(canvas, this.f9745i, this.f9742f);
        canvas.drawRect(this.f9745i, this.f9740d);
        this.f9742f.setAlpha((int) (((Integer) baseKeyframeAnimation2.h()).intValue() * 2.55f));
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        canvas.drawPath(this.f9737a, this.f9742f);
        canvas.restore();
    }

    private void p(Canvas canvas, Matrix matrix) {
        L.b("Layer#saveLayer");
        Utils.n(canvas, this.f9745i, this.f9741e, 19);
        L.c("Layer#saveLayer");
        for (int i2 = 0; i2 < this.f9754r.b().size(); i2++) {
            Mask mask = (Mask) this.f9754r.b().get(i2);
            BaseKeyframeAnimation baseKeyframeAnimation = (BaseKeyframeAnimation) this.f9754r.a().get(i2);
            BaseKeyframeAnimation baseKeyframeAnimation2 = (BaseKeyframeAnimation) this.f9754r.c().get(i2);
            int i3 = AnonymousClass1.f9757b[mask.a().ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    if (i2 == 0) {
                        this.f9740d.setColor(-16777216);
                        this.f9740d.setAlpha(255);
                        canvas.drawRect(this.f9745i, this.f9740d);
                    }
                    if (mask.d()) {
                        o(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        q(canvas, matrix, baseKeyframeAnimation);
                    }
                } else if (i3 != 3) {
                    if (i3 == 4) {
                        if (mask.d()) {
                            m(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                        } else {
                            k(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                        }
                    }
                } else if (mask.d()) {
                    n(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                } else {
                    l(canvas, matrix, baseKeyframeAnimation, baseKeyframeAnimation2);
                }
            } else if (r()) {
                this.f9740d.setAlpha(255);
                canvas.drawRect(this.f9745i, this.f9740d);
            }
        }
        L.b("Layer#restoreLayer");
        canvas.restore();
        L.c("Layer#restoreLayer");
    }

    private void q(Canvas canvas, Matrix matrix, BaseKeyframeAnimation baseKeyframeAnimation) {
        this.f9737a.set((Path) baseKeyframeAnimation.h());
        this.f9737a.transform(matrix);
        canvas.drawPath(this.f9737a, this.f9742f);
    }

    private boolean r() {
        if (this.f9754r.a().isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < this.f9754r.b().size(); i2++) {
            if (((Mask) this.f9754r.b().get(i2)).a() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void s() {
        if (this.v != null) {
            return;
        }
        if (this.u == null) {
            this.v = Collections.emptyList();
            return;
        }
        this.v = new ArrayList();
        for (BaseLayer baseLayer = this.u; baseLayer != null; baseLayer = baseLayer.u) {
            this.v.add(baseLayer);
        }
    }

    private void t(Canvas canvas) {
        L.b("Layer#clearLayer");
        RectF rectF = this.f9745i;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.f9744h);
        L.c("Layer#clearLayer");
    }

    static BaseLayer v(CompositionLayer compositionLayer, Layer layer, LottieDrawable lottieDrawable, LottieComposition lottieComposition) {
        switch (AnonymousClass1.f9756a[layer.g().ordinal()]) {
            case 1:
                return new ShapeLayer(lottieDrawable, layer, compositionLayer, lottieComposition);
            case 2:
                return new CompositionLayer(lottieDrawable, layer, lottieComposition.o(layer.n()), lottieComposition);
            case 3:
                return new SolidLayer(lottieDrawable, layer);
            case 4:
                return new ImageLayer(lottieDrawable, layer);
            case 5:
                return new NullLayer(lottieDrawable, layer);
            case 6:
                return new TextLayer(lottieDrawable, layer);
            default:
                Logger.c("Unknown layer type " + layer.g());
                return null;
        }
    }

    Layer A() {
        return this.f9753q;
    }

    boolean B() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.f9754r;
        return (maskKeyframeAnimation == null || maskKeyframeAnimation.a().isEmpty()) ? false : true;
    }

    boolean C() {
        return this.t != null;
    }

    public void I(BaseKeyframeAnimation baseKeyframeAnimation) {
        this.w.remove(baseKeyframeAnimation);
    }

    void J(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
    }

    void K(BaseLayer baseLayer) {
        this.t = baseLayer;
    }

    void L(boolean z) {
        if (z && this.A == null) {
            this.A = new LPaint();
        }
        this.z = z;
    }

    void M(BaseLayer baseLayer) {
        this.u = baseLayer;
    }

    void N(float f2) {
        L.b("BaseLayer#setProgress");
        L.b("BaseLayer#setProgress.transform");
        this.x.j(f2);
        L.c("BaseLayer#setProgress.transform");
        if (this.f9754r != null) {
            L.b("BaseLayer#setProgress.mask");
            for (int i2 = 0; i2 < this.f9754r.a().size(); i2++) {
                ((BaseKeyframeAnimation) this.f9754r.a().get(i2)).n(f2);
            }
            L.c("BaseLayer#setProgress.mask");
        }
        if (this.f9755s != null) {
            L.b("BaseLayer#setProgress.inout");
            this.f9755s.n(f2);
            L.c("BaseLayer#setProgress.inout");
        }
        if (this.t != null) {
            L.b("BaseLayer#setProgress.matte");
            this.t.N(f2);
            L.c("BaseLayer#setProgress.matte");
        }
        L.b("BaseLayer#setProgress.animations." + this.w.size());
        for (int i3 = 0; i3 < this.w.size(); i3++) {
            ((BaseKeyframeAnimation) this.w.get(i3)).n(f2);
        }
        L.c("BaseLayer#setProgress.animations." + this.w.size());
        L.c("BaseLayer#setProgress");
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        F();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        this.x.c(obj, lottieValueCallback);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        BaseLayer baseLayer = this.t;
        if (baseLayer != null) {
            KeyPath a2 = keyPath2.a(baseLayer.getName());
            if (keyPath.c(this.t.getName(), i2)) {
                list.add(a2.i(this.t));
            }
            if (keyPath.h(getName(), i2)) {
                this.t.J(keyPath, keyPath.e(this.t.getName(), i2) + i2, list, a2);
            }
        }
        if (keyPath.g(getName(), i2)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.a(getName());
                if (keyPath.c(getName(), i2)) {
                    list.add(keyPath2.i(this));
                }
            }
            if (keyPath.h(getName(), i2)) {
                J(keyPath, i2 + keyPath.e(getName(), i2), list, keyPath2);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        this.f9745i.set(0.0f, 0.0f, 0.0f, 0.0f);
        s();
        this.f9751o.set(matrix);
        if (z) {
            List list = this.v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f9751o.preConcat(((BaseLayer) this.v.get(size)).x.f());
                }
            } else {
                BaseLayer baseLayer = this.u;
                if (baseLayer != null) {
                    this.f9751o.preConcat(baseLayer.x.f());
                }
            }
        }
        this.f9751o.preConcat(this.x.f());
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9753q.j();
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        Paint paint;
        Integer num;
        L.b(this.f9750n);
        if (!this.y || this.f9753q.y()) {
            L.c(this.f9750n);
            return;
        }
        s();
        L.b("Layer#parentMatrix");
        this.f9738b.reset();
        this.f9738b.set(matrix);
        for (int size = this.v.size() - 1; size >= 0; size--) {
            this.f9738b.preConcat(((BaseLayer) this.v.get(size)).x.f());
        }
        L.c("Layer#parentMatrix");
        BaseKeyframeAnimation h2 = this.x.h();
        int intValue = (int) ((((i2 / 255.0f) * ((h2 == null || (num = (Integer) h2.h()) == null) ? 100 : num.intValue())) / 100.0f) * 255.0f);
        if (!C() && !B()) {
            this.f9738b.preConcat(this.x.f());
            L.b("Layer#drawLayer");
            u(canvas, this.f9738b, intValue);
            L.c("Layer#drawLayer");
            H(L.c(this.f9750n));
            return;
        }
        L.b("Layer#computeBounds");
        g(this.f9745i, this.f9738b, false);
        E(this.f9745i, matrix);
        this.f9738b.preConcat(this.x.f());
        D(this.f9745i, this.f9738b);
        this.f9746j.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        canvas.getMatrix(this.f9739c);
        if (!this.f9739c.isIdentity()) {
            Matrix matrix2 = this.f9739c;
            matrix2.invert(matrix2);
            this.f9739c.mapRect(this.f9746j);
        }
        if (!this.f9745i.intersect(this.f9746j)) {
            this.f9745i.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        L.c("Layer#computeBounds");
        if (this.f9745i.width() >= 1.0f && this.f9745i.height() >= 1.0f) {
            L.b("Layer#saveLayer");
            this.f9740d.setAlpha(255);
            Utils.m(canvas, this.f9745i, this.f9740d);
            L.c("Layer#saveLayer");
            t(canvas);
            L.b("Layer#drawLayer");
            u(canvas, this.f9738b, intValue);
            L.c("Layer#drawLayer");
            if (B()) {
                p(canvas, this.f9738b);
            }
            if (C()) {
                L.b("Layer#drawMatte");
                L.b("Layer#saveLayer");
                Utils.n(canvas, this.f9745i, this.f9743g, 19);
                L.c("Layer#saveLayer");
                t(canvas);
                this.t.i(canvas, matrix, intValue);
                L.b("Layer#restoreLayer");
                canvas.restore();
                L.c("Layer#restoreLayer");
                L.c("Layer#drawMatte");
            }
            L.b("Layer#restoreLayer");
            canvas.restore();
            L.c("Layer#restoreLayer");
        }
        if (this.z && (paint = this.A) != null) {
            paint.setStyle(Paint.Style.STROKE);
            this.A.setColor(-251901);
            this.A.setStrokeWidth(4.0f);
            canvas.drawRect(this.f9745i, this.A);
            this.A.setStyle(Paint.Style.FILL);
            this.A.setColor(1357638635);
            canvas.drawRect(this.f9745i, this.A);
        }
        H(L.c(this.f9750n));
    }

    public void j(BaseKeyframeAnimation baseKeyframeAnimation) {
        if (baseKeyframeAnimation == null) {
            return;
        }
        this.w.add(baseKeyframeAnimation);
    }

    abstract void u(Canvas canvas, Matrix matrix, int i2);

    public LBlendMode w() {
        return this.f9753q.a();
    }

    public BlurEffect x() {
        return this.f9753q.b();
    }

    public BlurMaskFilter y(float f2) {
        if (this.B == f2) {
            return this.C;
        }
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(f2 / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.C = blurMaskFilter;
        this.B = f2;
        return blurMaskFilter;
    }

    public DropShadowEffect z() {
        return this.f9753q.d();
    }
}
