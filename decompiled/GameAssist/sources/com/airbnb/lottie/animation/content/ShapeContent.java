package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {

    /* renamed from: b, reason: collision with root package name */
    private final String f9468b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f9469c;

    /* renamed from: d, reason: collision with root package name */
    private final LottieDrawable f9470d;

    /* renamed from: e, reason: collision with root package name */
    private final ShapeKeyframeAnimation f9471e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f9472f;

    /* renamed from: a, reason: collision with root package name */
    private final Path f9467a = new Path();

    /* renamed from: g, reason: collision with root package name */
    private final CompoundTrimPathContent f9473g = new CompoundTrimPathContent();

    public ShapeContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, ShapePath shapePath) {
        this.f9468b = shapePath.b();
        this.f9469c = shapePath.d();
        this.f9470d = lottieDrawable;
        ShapeKeyframeAnimation a2 = shapePath.c().a();
        this.f9471e = a2;
        baseLayer.j(a2);
        a2.a(this);
    }

    private void h() {
        this.f9472f = false;
        this.f9470d.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void a() {
        h();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void b(List list, List list2) {
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Content content = (Content) list.get(i2);
            if (content instanceof TrimPathContent) {
                TrimPathContent trimPathContent = (TrimPathContent) content;
                if (trimPathContent.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.f9473g.a(trimPathContent);
                    trimPathContent.e(this);
                }
            }
            if (content instanceof ShapeModifierContent) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add((ShapeModifierContent) content);
            }
        }
        this.f9471e.r(arrayList);
    }

    @Override // com.airbnb.lottie.animation.content.PathContent
    public Path d() {
        if (this.f9472f && !this.f9471e.k()) {
            return this.f9467a;
        }
        this.f9467a.reset();
        if (this.f9469c) {
            this.f9472f = true;
            return this.f9467a;
        }
        Path path = (Path) this.f9471e.h();
        if (path == null) {
            return this.f9467a;
        }
        this.f9467a.set(path);
        this.f9467a.setFillType(Path.FillType.EVEN_ODD);
        this.f9473g.b(this.f9467a);
        this.f9472f = true;
        return this.f9467a;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        if (obj == LottieProperty.P) {
            this.f9471e.o(lottieValueCallback);
        }
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void f(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        MiscUtils.k(keyPath, i2, list, keyPath2, this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.f9468b;
    }
}
