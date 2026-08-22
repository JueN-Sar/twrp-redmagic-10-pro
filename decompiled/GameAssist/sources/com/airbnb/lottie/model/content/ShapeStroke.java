package com.airbnb.lottie.model.content;

import android.graphics.Paint;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.StrokeContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

/* loaded from: classes.dex */
public class ShapeStroke implements ContentModel {

    /* renamed from: a, reason: collision with root package name */
    private final String f9719a;

    /* renamed from: b, reason: collision with root package name */
    private final AnimatableFloatValue f9720b;

    /* renamed from: c, reason: collision with root package name */
    private final List f9721c;

    /* renamed from: d, reason: collision with root package name */
    private final AnimatableColorValue f9722d;

    /* renamed from: e, reason: collision with root package name */
    private final AnimatableIntegerValue f9723e;

    /* renamed from: f, reason: collision with root package name */
    private final AnimatableFloatValue f9724f;

    /* renamed from: g, reason: collision with root package name */
    private final LineCapType f9725g;

    /* renamed from: h, reason: collision with root package name */
    private final LineJoinType f9726h;

    /* renamed from: i, reason: collision with root package name */
    private final float f9727i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f9728j;

    /* renamed from: com.airbnb.lottie.model.content.ShapeStroke$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9729a;

        /* renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f9730b;

        static {
            int[] iArr = new int[LineJoinType.values().length];
            f9730b = iArr;
            try {
                iArr[LineJoinType.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9730b[LineJoinType.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9730b[LineJoinType.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[LineCapType.values().length];
            f9729a = iArr2;
            try {
                iArr2[LineCapType.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9729a[LineCapType.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9729a[LineCapType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap d() {
            int i2 = AnonymousClass1.f9729a[ordinal()];
            return i2 != 1 ? i2 != 2 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }
    }

    public enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join d() {
            int i2 = AnonymousClass1.f9730b[ordinal()];
            if (i2 == 1) {
                return Paint.Join.BEVEL;
            }
            if (i2 == 2) {
                return Paint.Join.MITER;
            }
            if (i2 != 3) {
                return null;
            }
            return Paint.Join.ROUND;
        }
    }

    public ShapeStroke(String str, AnimatableFloatValue animatableFloatValue, List list, AnimatableColorValue animatableColorValue, AnimatableIntegerValue animatableIntegerValue, AnimatableFloatValue animatableFloatValue2, LineCapType lineCapType, LineJoinType lineJoinType, float f2, boolean z) {
        this.f9719a = str;
        this.f9720b = animatableFloatValue;
        this.f9721c = list;
        this.f9722d = animatableColorValue;
        this.f9723e = animatableIntegerValue;
        this.f9724f = animatableFloatValue2;
        this.f9725g = lineCapType;
        this.f9726h = lineJoinType;
        this.f9727i = f2;
        this.f9728j = z;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    public Content a(LottieDrawable lottieDrawable, LottieComposition lottieComposition, BaseLayer baseLayer) {
        return new StrokeContent(lottieDrawable, baseLayer, this);
    }

    public LineCapType b() {
        return this.f9725g;
    }

    public AnimatableColorValue c() {
        return this.f9722d;
    }

    public AnimatableFloatValue d() {
        return this.f9720b;
    }

    public LineJoinType e() {
        return this.f9726h;
    }

    public List f() {
        return this.f9721c;
    }

    public float g() {
        return this.f9727i;
    }

    public String h() {
        return this.f9719a;
    }

    public AnimatableIntegerValue i() {
        return this.f9723e;
    }

    public AnimatableFloatValue j() {
        return this.f9724f;
    }

    public boolean k() {
        return this.f9728j;
    }
}
