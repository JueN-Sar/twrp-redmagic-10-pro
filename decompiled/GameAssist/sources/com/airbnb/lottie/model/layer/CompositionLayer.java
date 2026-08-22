package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CompositionLayer extends BaseLayer {
    private BaseKeyframeAnimation D;
    private final List E;
    private final RectF F;
    private final RectF G;
    private final Paint H;
    private float I;
    private boolean J;

    /* renamed from: com.airbnb.lottie.model.layer.CompositionLayer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9758a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f9758a = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9758a[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public CompositionLayer(LottieDrawable lottieDrawable, Layer layer, List list, LottieComposition lottieComposition) {
        super(lottieDrawable, layer);
        int i2;
        BaseLayer baseLayer;
        this.E = new ArrayList();
        this.F = new RectF();
        this.G = new RectF();
        this.H = new Paint();
        this.J = true;
        AnimatableFloatValue v = layer.v();
        if (v != null) {
            BaseKeyframeAnimation a2 = v.a();
            this.D = a2;
            j(a2);
            this.D.a(this);
        } else {
            this.D = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(lottieComposition.k().size());
        int size = list.size() - 1;
        BaseLayer baseLayer2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = (Layer) list.get(size);
            BaseLayer v2 = BaseLayer.v(this, layer2, lottieDrawable, lottieComposition);
            if (v2 != null) {
                longSparseArray.k(v2.A().e(), v2);
                if (baseLayer2 != null) {
                    baseLayer2.K(v2);
                    baseLayer2 = null;
                } else {
                    this.E.add(0, v2);
                    int i3 = AnonymousClass1.f9758a[layer2.i().ordinal()];
                    if (i3 == 1 || i3 == 2) {
                        baseLayer2 = v2;
                    }
                }
            }
            size--;
        }
        for (i2 = 0; i2 < longSparseArray.n(); i2++) {
            BaseLayer baseLayer3 = (BaseLayer) longSparseArray.f(longSparseArray.j(i2));
            if (baseLayer3 != null && (baseLayer = (BaseLayer) longSparseArray.f(baseLayer3.A().k())) != null) {
                baseLayer3.M(baseLayer);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    protected void J(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        for (int i3 = 0; i3 < this.E.size(); i3++) {
            ((BaseLayer) this.E.get(i3)).f(keyPath, i2, list, keyPath2);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void L(boolean z) {
        super.L(z);
        Iterator it = this.E.iterator();
        while (it.hasNext()) {
            ((BaseLayer) it.next()).L(z);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void N(float f2) {
        L.b("CompositionLayer#setProgress");
        this.I = f2;
        super.N(f2);
        if (this.D != null) {
            f2 = ((((Float) this.D.h()).floatValue() * this.f9753q.c().i()) - this.f9753q.c().p()) / (this.f9752p.I().e() + 0.01f);
        }
        if (this.D == null) {
            f2 -= this.f9753q.s();
        }
        if (this.f9753q.w() != 0.0f && !"__container".equals(this.f9753q.j())) {
            f2 /= this.f9753q.w();
        }
        for (int size = this.E.size() - 1; size >= 0; size--) {
            ((BaseLayer) this.E.get(size)).N(f2);
        }
        L.c("CompositionLayer#setProgress");
    }

    public float Q() {
        return this.I;
    }

    public void R(boolean z) {
        this.J = z;
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.E) {
            if (lottieValueCallback == null) {
                BaseKeyframeAnimation baseKeyframeAnimation = this.D;
                if (baseKeyframeAnimation != null) {
                    baseKeyframeAnimation.o(null);
                    return;
                }
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.D = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            j(this.D);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        super.g(rectF, matrix, z);
        for (int size = this.E.size() - 1; size >= 0; size--) {
            this.F.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((BaseLayer) this.E.get(size)).g(this.F, this.f9751o, true);
            rectF.union(this.F);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void u(Canvas canvas, Matrix matrix, int i2) {
        L.b("CompositionLayer#draw");
        this.G.set(0.0f, 0.0f, this.f9753q.m(), this.f9753q.l());
        matrix.mapRect(this.G);
        boolean z = this.f9752p.e0() && this.E.size() > 1 && i2 != 255;
        if (z) {
            this.H.setAlpha(i2);
            Utils.m(canvas, this.G, this.H);
        } else {
            canvas.save();
        }
        if (z) {
            i2 = 255;
        }
        for (int size = this.E.size() - 1; size >= 0; size--) {
            if ((!this.J && "__container".equals(this.f9753q.j())) || this.G.isEmpty() || canvas.clipRect(this.G)) {
                ((BaseLayer) this.E.get(size)).i(canvas, matrix, i2);
            }
        }
        canvas.restore();
        L.c("CompositionLayer#draw");
    }
}
