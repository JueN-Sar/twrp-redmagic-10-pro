package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.Mask;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MaskKeyframeAnimation {

    /* renamed from: a, reason: collision with root package name */
    private final List f9507a;

    /* renamed from: b, reason: collision with root package name */
    private final List f9508b;

    /* renamed from: c, reason: collision with root package name */
    private final List f9509c;

    public MaskKeyframeAnimation(List list) {
        this.f9509c = list;
        this.f9507a = new ArrayList(list.size());
        this.f9508b = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f9507a.add(((Mask) list.get(i2)).b().a());
            this.f9508b.add(((Mask) list.get(i2)).c().a());
        }
    }

    public List a() {
        return this.f9507a;
    }

    public List b() {
        return this.f9509c;
    }

    public List c() {
        return this.f9508b;
    }
}
