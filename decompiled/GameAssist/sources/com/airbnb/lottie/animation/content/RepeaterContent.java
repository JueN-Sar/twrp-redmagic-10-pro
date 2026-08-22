package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
public class RepeaterContent implements DrawingContent, PathContent, GreedyContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: a, reason: collision with root package name */
    private final Matrix f9453a = new Matrix();

    /* renamed from: b, reason: collision with root package name */
    private final Path f9454b = new Path();

    /* renamed from: c, reason: collision with root package name */
    private final LottieDrawable f9455c;

    /* renamed from: d, reason: collision with root package name */
    private final BaseLayer f9456d;

    /* renamed from: e, reason: collision with root package name */
    private final String f9457e;

    /* renamed from: f, reason: collision with root package name */
    private final boolean f9458f;

    /* renamed from: g, reason: collision with root package name */
    private final BaseKeyframeAnimation f9459g;

    /* renamed from: h, reason: collision with root package name */
    private final BaseKeyframeAnimation f9460h;

    /* renamed from: i, reason: collision with root package name */
    private final TransformKeyframeAnimation f9461i;

    /* renamed from: j, reason: collision with root package name */
    private ContentGroup f9462j;

    public RepeaterContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, Repeater repeater) {
        this.f9455c = lottieDrawable;
        this.f9456d = baseLayer;
        this.f9457e = repeater.c();
        this.f9458f = repeater.f();
        BaseKeyframeAnimation a2 = repeater.b().a();
        this.f9459g = a2;
        baseLayer.j(a2);
        a2.a(this);
        BaseKeyframeAnimation a3 = repeater.d().a();
        this.f9460h = a3;
        baseLayer.j(a3);
        a3.a(this);
        TransformKeyframeAnimation b2 = repeater.e().b();
        this.f9461i = b2;
        b2.a(baseLayer);
        b2.b(this);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        this.f9455c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        this.f9462j.b(list, list2);
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        Path d2 = this.f9462j.d();
        this.f9454b.reset();
        float floatValue = ((Float) this.f9459g.h()).floatValue();
        float floatValue2 = ((Float) this.f9460h.h()).floatValue();
        for (int i2 = ((int) floatValue) - 1; i2 >= 0; i2--) {
            this.f9453a.set(this.f9461i.g(i2 + floatValue2));
            this.f9454b.addPath(d2, this.f9453a);
        }
        return this.f9454b;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        if (this.f9461i.c(obj, lottieValueCallback)) {
            return;
        }
        if (obj == LottieProperty.u) {
            this.f9459g.o(lottieValueCallback);
        } else if (obj == LottieProperty.v) {
            this.f9460h.o(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
        for (int i3 = 0; i3 < this.f9462j.k().size(); i3++) {
            Content content = (Content) this.f9462j.k().get(i3);
            if (content instanceof KeyPathElementContent) {
                MiscUtils.k(keyPath, i2, list, keyPath2, (KeyPathElementContent) content);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        this.f9462j.g(rectF, matrix, z);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9457e;
    }

    @Override // com.airbnb.lottie.animation.content.GreedyContent
    public void h(ListIterator listIterator) {
        if (this.f9462j != null) {
            return;
        }
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        ArrayList arrayList = new ArrayList();
        while (listIterator.hasPrevious()) {
            arrayList.add((Content) listIterator.previous());
            listIterator.remove();
        }
        Collections.reverse(arrayList);
        this.f9462j = new ContentGroup(this.f9455c, this.f9456d, "Repeater", this.f9458f, arrayList, null);
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void i(Canvas canvas, Matrix matrix, int i2) {
        float floatValue = ((Float) this.f9459g.h()).floatValue();
        float floatValue2 = ((Float) this.f9460h.h()).floatValue();
        float floatValue3 = ((Float) this.f9461i.i().h()).floatValue() / 100.0f;
        float floatValue4 = ((Float) this.f9461i.e().h()).floatValue() / 100.0f;
        for (int i3 = ((int) floatValue) - 1; i3 >= 0; i3--) {
            this.f9453a.set(matrix);
            float f2 = i3;
            this.f9453a.preConcat(this.f9461i.g(f2 + floatValue2));
            this.f9462j.i(canvas, this.f9453a, (int) (i2 * MiscUtils.i(floatValue3, floatValue4, f2 / floatValue)));
        }
    }
}
