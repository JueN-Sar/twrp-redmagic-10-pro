package com.airbnb.lottie.value;

import android.graphics.PointF;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieRelativePointValueCallback extends LottieValueCallback<PointF> {

    /* renamed from: d, reason: collision with root package name */
    private final PointF f9968d;

    public PointF d(LottieFrameInfo lottieFrameInfo) {
        Object obj = this.f9971c;
        if (obj != null) {
            return (PointF) obj;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }

    @Override // com.airbnb.lottie.value.LottieValueCallback
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public final PointF a(LottieFrameInfo lottieFrameInfo) {
        this.f9968d.set(MiscUtils.i(((PointF) lottieFrameInfo.g()).x, ((PointF) lottieFrameInfo.b()).x, lottieFrameInfo.c()), MiscUtils.i(((PointF) lottieFrameInfo.g()).y, ((PointF) lottieFrameInfo.b()).y, lottieFrameInfo.c()));
        PointF d2 = d(lottieFrameInfo);
        this.f9968d.offset(d2.x, d2.y);
        return this.f9968d;
    }
}
