package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeLayer extends BaseLayer {
    private final ContentGroup D;
    private final CompositionLayer E;

    ShapeLayer(LottieDrawable lottieDrawable, Layer layer, CompositionLayer compositionLayer, LottieComposition lottieComposition) {
        super(lottieDrawable, layer);
        this.E = compositionLayer;
        ContentGroup contentGroup = new ContentGroup(lottieDrawable, this, new ShapeGroup("__container", layer.o(), false), lottieComposition);
        this.D = contentGroup;
        contentGroup.b(Collections.emptyList(), Collections.emptyList());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    protected void J(KeyPath keyPath, int i2, List list, KeyPath keyPath2) {
        this.D.f(keyPath, i2, list, keyPath2);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        super.g(rectF, matrix, z);
        this.D.g(rectF, this.f9751o, z);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void u(Canvas canvas, Matrix matrix, int i2) {
        this.D.i(canvas, matrix, i2);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public BlurEffect x() {
        BlurEffect x = super.x();
        return x != null ? x : this.E.x();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public DropShadowEffect z() {
        DropShadowEffect z = super.z();
        return z != null ? z : this.E.z();
    }
}
