package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ContentGroup implements DrawingContent, PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {

    /* renamed from: a, reason: collision with root package name */
    private final Paint f9363a;

    /* renamed from: b, reason: collision with root package name */
    private final RectF f9364b;

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f9365c;

    /* renamed from: d, reason: collision with root package name */
    private final Path f9366d;

    /* renamed from: e, reason: collision with root package name */
    private final RectF f9367e;

    /* renamed from: f, reason: collision with root package name */
    private final String f9368f;

    /* renamed from: g, reason: collision with root package name */
    private final boolean f9369g;

    /* renamed from: h, reason: collision with root package name */
    private final List f9370h;

    /* renamed from: i, reason: collision with root package name */
    private final LottieDrawable f9371i;

    /* renamed from: j, reason: collision with root package name */
    private List f9372j;

    /* renamed from: k, reason: collision with root package name */
    private TransformKeyframeAnimation f9373k;

    public ContentGroup(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapeGroup shapeGroup, LottieComposition lottieComposition) {
        this(lottieDrawable, baseLayer, shapeGroup.c(), shapeGroup.d(), h(lottieDrawable, lottieComposition, baseLayer, shapeGroup.b()), j(shapeGroup.b()));
    }

    private static List h(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            Content a2 = ((ContentModel) list.get(i2)).a(lottieDrawable, lottieComposition, baseLayer);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    static AnimatableTransform j(List list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            ContentModel contentModel = (ContentModel) list.get(i2);
            if (contentModel instanceof AnimatableTransform) {
                return (AnimatableTransform) contentModel;
            }
        }
        return null;
    }

    private boolean n() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f9370h.size(); i3++) {
            if ((this.f9370h.get(i3) instanceof DrawingContent) && (i2 = i2 + 1) >= 2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9371i.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.f9370h.size());
        arrayList.addAll(list);
        for (int size = this.f9370h.size() - 1; size >= 0; size--) {
            Content content = (Content) this.f9370h.get(size);
            content.b(arrayList, this.f9370h.subList(0, size));
            arrayList.add(content);
        }
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        this.f9365c.reset();
        TransformKeyframeAnimation transformKeyframeAnimation = this.f9373k;
        if (transformKeyframeAnimation != null) {
            this.f9365c.set(transformKeyframeAnimation.f());
        }
        this.f9366d.reset();
        if (this.f9369g) {
            return this.f9366d;
        }
        for (int size = this.f9370h.size() - 1; size >= 0; size--) {
            Content content = (Content) this.f9370h.get(size);
            if (content instanceof PathContent) {
                this.f9366d.addPath(((PathContent) content).d(), this.f9365c);
            }
        }
        return this.f9366d;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        TransformKeyframeAnimation transformKeyframeAnimation = this.f9373k;
        if (transformKeyframeAnimation != null) {
            transformKeyframeAnimation.c(obj, lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        if (keyPath.g(getName(), i2) || "__container".equals(getName())) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.a(getName());
                if (keyPath.c(getName(), i2)) {
                    list.add(keyPath2.i(this));
                }
            }
            if (keyPath.h(getName(), i2)) {
                int e2 = i2 + keyPath.e(getName(), i2);
                for (int i3 = 0; i3 < this.f9370h.size(); i3++) {
                    Content content = (Content) this.f9370h.get(i3);
                    if (content instanceof KeyPathElement) {
                        ((KeyPathElement) content).f(keyPath, e2, list, keyPath2);
                    }
                }
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        this.f9365c.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.f9373k;
        if (transformKeyframeAnimation != null) {
            this.f9365c.preConcat(transformKeyframeAnimation.f());
        }
        this.f9367e.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.f9370h.size() - 1; size >= 0; size--) {
            Content content = (Content) this.f9370h.get(size);
            if (content instanceof DrawingContent) {
                ((DrawingContent) content).g(this.f9367e, this.f9365c, z);
                rectF.union(this.f9367e);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9368f;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        if (this.f9369g) {
            return;
        }
        this.f9365c.set(matrix);
        TransformKeyframeAnimation transformKeyframeAnimation = this.f9373k;
        if (transformKeyframeAnimation != null) {
            this.f9365c.preConcat(transformKeyframeAnimation.f());
            i2 = (int) (((((this.f9373k.h() == null ? 100 : ((Integer) this.f9373k.h().h()).intValue()) / 100.0f) * i2) / 255.0f) * 255.0f);
        }
        boolean z = this.f9371i.e0() && n() && i2 != 255;
        if (z) {
            this.f9364b.set(0.0f, 0.0f, 0.0f, 0.0f);
            g(this.f9364b, this.f9365c, true);
            this.f9363a.setAlpha(i2);
            Utils.m(canvas, this.f9364b, this.f9363a);
        }
        if (z) {
            i2 = 255;
        }
        for (int size = this.f9370h.size() - 1; size >= 0; size--) {
            Object obj = this.f9370h.get(size);
            if (obj instanceof DrawingContent) {
                ((DrawingContent) obj).i(canvas, this.f9365c, i2);
            }
        }
        if (z) {
            canvas.restore();
        }
    }

    public List k() {
        return this.f9370h;
    }

    List l() {
        if (this.f9372j == null) {
            this.f9372j = new ArrayList();
            for (int i2 = 0; i2 < this.f9370h.size(); i2++) {
                Content content = (Content) this.f9370h.get(i2);
                if (content instanceof PathContent) {
                    this.f9372j.add((PathContent) content);
                }
            }
        }
        return this.f9372j;
    }

    Matrix m() {
        TransformKeyframeAnimation transformKeyframeAnimation = this.f9373k;
        if (transformKeyframeAnimation != null) {
            return transformKeyframeAnimation.f();
        }
        this.f9365c.reset();
        return this.f9365c;
    }

    ContentGroup(LottieDrawable lottieDrawable, BaseLayer baseLayer, String str, boolean z, List list, AnimatableTransform animatableTransform) {
        this.f9363a = new LPaint();
        this.f9364b = new RectF();
        this.f9365c = new Matrix();
        this.f9366d = new Path();
        this.f9367e = new RectF();
        this.f9368f = str;
        this.f9371i = lottieDrawable;
        this.f9369g = z;
        this.f9370h = list;
        if (animatableTransform != null) {
            TransformKeyframeAnimation b2 = animatableTransform.b();
            this.f9373k = b2;
            b2.a(baseLayer);
            this.f9373k.b(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            Content content = (Content) list.get(size);
            if (content instanceof GreedyContent) {
                arrayList.add((GreedyContent) content);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((GreedyContent) arrayList.get(size2)).h(list.listIterator(list.size()));
        }
    }
}
