package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

/* loaded from: classes.dex */
public class TextKeyframeAnimation extends KeyframeAnimation<DocumentData> {
    public TextKeyframeAnimation(List list) {
        super(list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public DocumentData i(Keyframe keyframe, float f2) {
        Object obj;
        LottieValueCallback lottieValueCallback = this.f9487e;
        if (lottieValueCallback == null) {
            return (f2 != 1.0f || (obj = keyframe.f9943c) == null) ? (DocumentData) keyframe.f9942b : (DocumentData) obj;
        }
        float f3 = keyframe.f9947g;
        Float f4 = keyframe.f9948h;
        float floatValue = f4 == null ? Float.MAX_VALUE : f4.floatValue();
        Object obj2 = keyframe.f9942b;
        DocumentData documentData = (DocumentData) obj2;
        Object obj3 = keyframe.f9943c;
        return (DocumentData) lottieValueCallback.b(f3, floatValue, documentData, obj3 == null ? (DocumentData) obj2 : (DocumentData) obj3, f2, d(), f());
    }

    public void r(final LottieValueCallback lottieValueCallback) {
        final LottieFrameInfo lottieFrameInfo = new LottieFrameInfo();
        final DocumentData documentData = new DocumentData();
        super.o(new LottieValueCallback<DocumentData>() { // from class: com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation.1
            @Override // com.airbnb.lottie.value.LottieValueCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public DocumentData a(LottieFrameInfo lottieFrameInfo2) {
                lottieFrameInfo.h(lottieFrameInfo2.f(), lottieFrameInfo2.a(), ((DocumentData) lottieFrameInfo2.g()).f9589a, ((DocumentData) lottieFrameInfo2.b()).f9589a, lottieFrameInfo2.d(), lottieFrameInfo2.c(), lottieFrameInfo2.e());
                String str = (String) lottieValueCallback.a(lottieFrameInfo);
                DocumentData documentData2 = (DocumentData) (lottieFrameInfo2.c() == 1.0f ? lottieFrameInfo2.b() : lottieFrameInfo2.g());
                documentData.a(str, documentData2.f9590b, documentData2.f9591c, documentData2.f9592d, documentData2.f9593e, documentData2.f9594f, documentData2.f9595g, documentData2.f9596h, documentData2.f9597i, documentData2.f9598j, documentData2.f9599k, documentData2.f9600l, documentData2.f9601m);
                return documentData;
            }
        });
    }
}
