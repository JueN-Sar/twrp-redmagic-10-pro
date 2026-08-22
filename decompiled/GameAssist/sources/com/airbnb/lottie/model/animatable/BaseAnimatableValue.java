package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
abstract class BaseAnimatableValue<V, O> implements AnimatableValue<V, O> {

    /* renamed from: a, reason: collision with root package name */
    final List f9640a;

    BaseAnimatableValue(List list) {
        this.f9640a = list;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean b() {
        if (this.f9640a.isEmpty()) {
            return true;
        }
        return this.f9640a.size() == 1 && ((Keyframe) this.f9640a.get(0)).i();
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List getKeyframes() {
        return this.f9640a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f9640a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f9640a.toArray()));
        }
        return sb.toString();
    }
}
