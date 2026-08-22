package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {

    /* renamed from: i, reason: collision with root package name */
    private final ShapeData f9519i;

    /* renamed from: j, reason: collision with root package name */
    private final Path f9520j;

    /* renamed from: k, reason: collision with root package name */
    private Path f9521k;

    /* renamed from: l, reason: collision with root package name */
    private Path f9522l;

    /* renamed from: m, reason: collision with root package name */
    private List f9523m;

    public ShapeKeyframeAnimation(List list) {
        super(list);
        this.f9519i = new ShapeData();
        this.f9520j = new Path();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public Path i(Keyframe keyframe, float f2) {
        ShapeData shapeData = (ShapeData) keyframe.f9942b;
        ShapeData shapeData2 = (ShapeData) keyframe.f9943c;
        this.f9519i.c(shapeData, shapeData2 == null ? shapeData : shapeData2, f2);
        ShapeData shapeData3 = this.f9519i;
        List list = this.f9523m;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                shapeData3 = ((ShapeModifierContent) this.f9523m.get(size)).c(shapeData3);
            }
        }
        MiscUtils.h(shapeData3, this.f9520j);
        if (this.f9487e == null) {
            return this.f9520j;
        }
        if (this.f9521k == null) {
            this.f9521k = new Path();
            this.f9522l = new Path();
        }
        MiscUtils.h(shapeData, this.f9521k);
        if (shapeData2 != null) {
            MiscUtils.h(shapeData2, this.f9522l);
        }
        LottieValueCallback lottieValueCallback = this.f9487e;
        float f3 = keyframe.f9947g;
        float floatValue = keyframe.f9948h.floatValue();
        Path path = this.f9521k;
        return (Path) lottieValueCallback.b(f3, floatValue, path, shapeData2 == null ? path : this.f9522l, f2, e(), f());
    }

    public void r(List list) {
        this.f9523m = list;
    }
}
