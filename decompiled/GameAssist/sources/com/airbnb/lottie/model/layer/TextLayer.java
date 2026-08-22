package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class TextLayer extends BaseLayer {
    private final StringBuilder D;
    private final RectF E;
    private final Matrix F;
    private final Paint G;
    private final Paint H;
    private final Map I;
    private final LongSparseArray J;
    private final List K;
    private final TextKeyframeAnimation L;
    private final LottieDrawable M;
    private final LottieComposition N;
    private BaseKeyframeAnimation O;
    private BaseKeyframeAnimation P;
    private BaseKeyframeAnimation Q;
    private BaseKeyframeAnimation R;
    private BaseKeyframeAnimation S;
    private BaseKeyframeAnimation T;
    private BaseKeyframeAnimation U;
    private BaseKeyframeAnimation V;
    private BaseKeyframeAnimation W;
    private BaseKeyframeAnimation X;

    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f9780a;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            f9780a = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9780a[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9780a[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static class TextSubLine {

        /* renamed from: a, reason: collision with root package name */
        private String f9781a;

        /* renamed from: b, reason: collision with root package name */
        private float f9782b;

        private TextSubLine() {
            this.f9781a = "";
            this.f9782b = 0.0f;
        }

        void c(String str, float f2) {
            this.f9781a = str;
            this.f9782b = f2;
        }
    }

    TextLayer(LottieDrawable lottieDrawable, Layer layer) {
        super(lottieDrawable, layer);
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableColorValue animatableColorValue;
        AnimatableColorValue animatableColorValue2;
        this.D = new StringBuilder(2);
        this.E = new RectF();
        this.F = new Matrix();
        int i2 = 1;
        this.G = new Paint(i2) { // from class: com.airbnb.lottie.model.layer.TextLayer.1
            {
                setStyle(Paint.Style.FILL);
            }
        };
        this.H = new Paint(i2) { // from class: com.airbnb.lottie.model.layer.TextLayer.2
            {
                setStyle(Paint.Style.STROKE);
            }
        };
        this.I = new HashMap();
        this.J = new LongSparseArray();
        this.K = new ArrayList();
        this.M = lottieDrawable;
        this.N = layer.c();
        TextKeyframeAnimation a2 = layer.t().a();
        this.L = a2;
        a2.a(this);
        j(a2);
        AnimatableTextProperties u = layer.u();
        if (u != null && (animatableColorValue2 = u.f9626a) != null) {
            BaseKeyframeAnimation a3 = animatableColorValue2.a();
            this.O = a3;
            a3.a(this);
            j(this.O);
        }
        if (u != null && (animatableColorValue = u.f9627b) != null) {
            BaseKeyframeAnimation a4 = animatableColorValue.a();
            this.Q = a4;
            a4.a(this);
            j(this.Q);
        }
        if (u != null && (animatableFloatValue2 = u.f9628c) != null) {
            BaseKeyframeAnimation a5 = animatableFloatValue2.a();
            this.S = a5;
            a5.a(this);
            j(this.S);
        }
        if (u == null || (animatableFloatValue = u.f9629d) == null) {
            return;
        }
        BaseKeyframeAnimation a6 = animatableFloatValue.a();
        this.U = a6;
        a6.a(this);
        j(this.U);
    }

    private String Q(String str, int i2) {
        int codePointAt = str.codePointAt(i2);
        int charCount = Character.charCount(codePointAt) + i2;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!e0(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j2 = codePointAt;
        if (this.J.e(j2)) {
            return (String) this.J.f(j2);
        }
        this.D.setLength(0);
        while (i2 < charCount) {
            int codePointAt3 = str.codePointAt(i2);
            this.D.appendCodePoint(codePointAt3);
            i2 += Character.charCount(codePointAt3);
        }
        String sb = this.D.toString();
        this.J.k(j2, sb);
        return sb;
    }

    private void R(DocumentData documentData, int i2) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.P;
        if (baseKeyframeAnimation != null) {
            this.G.setColor(((Integer) baseKeyframeAnimation.h()).intValue());
        } else {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.O;
            if (baseKeyframeAnimation2 != null) {
                this.G.setColor(((Integer) baseKeyframeAnimation2.h()).intValue());
            } else {
                this.G.setColor(documentData.f9596h);
            }
        }
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.R;
        if (baseKeyframeAnimation3 != null) {
            this.H.setColor(((Integer) baseKeyframeAnimation3.h()).intValue());
        } else {
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.Q;
            if (baseKeyframeAnimation4 != null) {
                this.H.setColor(((Integer) baseKeyframeAnimation4.h()).intValue());
            } else {
                this.H.setColor(documentData.f9597i);
            }
        }
        int intValue = ((((this.x.h() == null ? 100 : ((Integer) this.x.h().h()).intValue()) * 255) / 100) * i2) / 255;
        this.G.setAlpha(intValue);
        this.H.setAlpha(intValue);
        BaseKeyframeAnimation baseKeyframeAnimation5 = this.T;
        if (baseKeyframeAnimation5 != null) {
            this.H.setStrokeWidth(((Float) baseKeyframeAnimation5.h()).floatValue());
            return;
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.S;
        if (baseKeyframeAnimation6 != null) {
            this.H.setStrokeWidth(((Float) baseKeyframeAnimation6.h()).floatValue());
        } else {
            this.H.setStrokeWidth(documentData.f9598j * Utils.e());
        }
    }

    private void S(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private void T(FontCharacter fontCharacter, float f2, DocumentData documentData, Canvas canvas) {
        List b0 = b0(fontCharacter);
        for (int i2 = 0; i2 < b0.size(); i2++) {
            Path d2 = ((ContentGroup) b0.get(i2)).d();
            d2.computeBounds(this.E, false);
            this.F.reset();
            this.F.preTranslate(0.0f, (-documentData.f9595g) * Utils.e());
            this.F.preScale(f2, f2);
            d2.transform(this.F);
            if (documentData.f9599k) {
                W(d2, this.G, canvas);
                W(d2, this.H, canvas);
            } else {
                W(d2, this.H, canvas);
                W(d2, this.G, canvas);
            }
        }
    }

    private void U(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.f9599k) {
            S(str, this.G, canvas);
            S(str, this.H, canvas);
        } else {
            S(str, this.H, canvas);
            S(str, this.G, canvas);
        }
    }

    private void V(String str, DocumentData documentData, Canvas canvas, float f2) {
        int i2 = 0;
        while (i2 < str.length()) {
            String Q = Q(str, i2);
            i2 += Q.length();
            U(Q, documentData, canvas);
            canvas.translate(this.G.measureText(Q) + f2, 0.0f);
        }
    }

    private void W(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void X(String str, DocumentData documentData, Font font, Canvas canvas, float f2, float f3, float f4) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            FontCharacter fontCharacter = (FontCharacter) this.N.c().e(FontCharacter.c(str.charAt(i2), font.a(), font.c()));
            if (fontCharacter != null) {
                T(fontCharacter, f3, documentData, canvas);
                canvas.translate((((float) fontCharacter.b()) * f3 * Utils.e()) + f4, 0.0f);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void Y(com.airbnb.lottie.model.DocumentData r19, com.airbnb.lottie.model.Font r20, android.graphics.Canvas r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            android.graphics.Typeface r0 = r7.d0(r9)
            if (r0 != 0) goto Lf
            return
        Lf:
            java.lang.String r1 = r8.f9589a
            com.airbnb.lottie.LottieDrawable r2 = r7.M
            com.airbnb.lottie.TextDelegate r2 = r2.Z()
            if (r2 == 0) goto L21
            java.lang.String r3 = r18.getName()
            java.lang.String r1 = r2.c(r3, r1)
        L21:
            android.graphics.Paint r2 = r7.G
            r2.setTypeface(r0)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r7.W
            if (r0 == 0) goto L35
            java.lang.Object r0 = r0.h()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L37
        L35:
            float r0 = r8.f9591c
        L37:
            android.graphics.Paint r2 = r7.G
            float r3 = com.airbnb.lottie.utils.Utils.e()
            float r3 = r3 * r0
            r2.setTextSize(r3)
            android.graphics.Paint r2 = r7.H
            android.graphics.Paint r3 = r7.G
            android.graphics.Typeface r3 = r3.getTypeface()
            r2.setTypeface(r3)
            android.graphics.Paint r2 = r7.H
            android.graphics.Paint r3 = r7.G
            float r3 = r3.getTextSize()
            r2.setTextSize(r3)
            int r2 = r8.f9593e
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r3 = r7.V
            if (r3 == 0) goto L6d
            java.lang.Object r3 = r3.h()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L6b:
            float r2 = r2 + r3
            goto L7c
        L6d:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r3 = r7.U
            if (r3 == 0) goto L7c
            java.lang.Object r3 = r3.h()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L6b
        L7c:
            float r3 = com.airbnb.lottie.utils.Utils.e()
            float r2 = r2 * r3
            float r2 = r2 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r11 = r2 / r0
            java.util.List r12 = r7.c0(r1)
            int r13 = r12.size()
            r14 = 0
            r0 = -1
            r15 = r0
            r6 = r14
        L92:
            if (r6 >= r13) goto Le1
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r8.f9601m
            if (r0 != 0) goto La2
            r0 = 0
        La0:
            r2 = r0
            goto La5
        La2:
            float r0 = r0.x
            goto La0
        La5:
            r4 = 0
            r16 = 0
            r0 = r18
            r3 = r20
            r5 = r11
            r17 = r6
            r6 = r16
            java.util.List r0 = r0.g0(r1, r2, r3, r4, r5, r6)
            r1 = r14
        Lb6:
            int r2 = r0.size()
            if (r1 >= r2) goto Lde
            java.lang.Object r2 = r0.get(r1)
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r2 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r2
            int r15 = r15 + 1
            r21.save()
            float r3 = com.airbnb.lottie.model.layer.TextLayer.TextSubLine.a(r2)
            boolean r3 = r7.f0(r10, r8, r15, r3)
            if (r3 == 0) goto Ld8
            java.lang.String r2 = com.airbnb.lottie.model.layer.TextLayer.TextSubLine.b(r2)
            r7.V(r2, r8, r10, r11)
        Ld8:
            r21.restore()
            int r1 = r1 + 1
            goto Lb6
        Lde:
            int r6 = r17 + 1
            goto L92
        Le1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.Y(com.airbnb.lottie.model.DocumentData, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void Z(com.airbnb.lottie.model.DocumentData r21, android.graphics.Matrix r22, com.airbnb.lottie.model.Font r23, android.graphics.Canvas r24) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r8.W
            if (r0 == 0) goto L13
            java.lang.Object r0 = r0.h()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L15
        L13:
            float r0 = r9.f9591c
        L15:
            r1 = 1120403456(0x42c80000, float:100.0)
            float r10 = r0 / r1
            float r11 = com.airbnb.lottie.utils.Utils.g(r22)
            java.lang.String r0 = r9.f9589a
            java.util.List r12 = r8.c0(r0)
            int r13 = r12.size()
            int r0 = r9.f9593e
            float r0 = (float) r0
            r1 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 / r1
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r1 = r8.V
            if (r1 == 0) goto L3e
            java.lang.Object r1 = r1.h()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
        L3b:
            float r0 = r0 + r1
        L3c:
            r14 = r0
            goto L4d
        L3e:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r1 = r8.U
            if (r1 == 0) goto L3c
            java.lang.Object r1 = r1.h()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L3b
        L4d:
            r15 = 0
            r0 = -1
            r7 = r0
            r6 = r15
        L51:
            if (r6 >= r13) goto Lbe
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r9.f9601m
            if (r0 != 0) goto L61
            r0 = 0
        L5f:
            r2 = r0
            goto L64
        L61:
            float r0 = r0.x
            goto L5f
        L64:
            r16 = 1
            r0 = r20
            r3 = r23
            r4 = r10
            r5 = r14
            r17 = r6
            r6 = r16
            java.util.List r6 = r0.g0(r1, r2, r3, r4, r5, r6)
            r5 = r15
        L75:
            int r0 = r6.size()
            if (r5 >= r0) goto Lbb
            java.lang.Object r0 = r6.get(r5)
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r0 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r0
            int r7 = r7 + 1
            r24.save()
            float r1 = com.airbnb.lottie.model.layer.TextLayer.TextSubLine.a(r0)
            r4 = r24
            boolean r1 = r8.f0(r4, r9, r7, r1)
            if (r1 == 0) goto Lab
            java.lang.String r1 = com.airbnb.lottie.model.layer.TextLayer.TextSubLine.b(r0)
            r0 = r20
            r2 = r21
            r3 = r23
            r4 = r24
            r16 = r5
            r5 = r11
            r18 = r6
            r6 = r10
            r19 = r7
            r7 = r14
            r0.X(r1, r2, r3, r4, r5, r6, r7)
            goto Lb1
        Lab:
            r16 = r5
            r18 = r6
            r19 = r7
        Lb1:
            r24.restore()
            int r5 = r16 + 1
            r6 = r18
            r7 = r19
            goto L75
        Lbb:
            int r6 = r17 + 1
            goto L51
        Lbe:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.Z(com.airbnb.lottie.model.DocumentData, android.graphics.Matrix, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    private TextSubLine a0(int i2) {
        for (int size = this.K.size(); size < i2; size++) {
            this.K.add(new TextSubLine());
        }
        return (TextSubLine) this.K.get(i2 - 1);
    }

    private List b0(FontCharacter fontCharacter) {
        if (this.I.containsKey(fontCharacter)) {
            return (List) this.I.get(fontCharacter);
        }
        List a2 = fontCharacter.a();
        int size = a2.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new ContentGroup(this.M, this, (ShapeGroup) a2.get(i2), this.N));
        }
        this.I.put(fontCharacter, arrayList);
        return arrayList;
    }

    private List c0(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\u0003", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private Typeface d0(Font font) {
        Typeface typeface;
        BaseKeyframeAnimation baseKeyframeAnimation = this.X;
        if (baseKeyframeAnimation != null && (typeface = (Typeface) baseKeyframeAnimation.h()) != null) {
            return typeface;
        }
        Typeface a0 = this.M.a0(font);
        return a0 != null ? a0 : font.d();
    }

    private boolean e0(int i2) {
        return Character.getType(i2) == 16 || Character.getType(i2) == 27 || Character.getType(i2) == 6 || Character.getType(i2) == 28 || Character.getType(i2) == 8 || Character.getType(i2) == 19;
    }

    private boolean f0(Canvas canvas, DocumentData documentData, int i2, float f2) {
        PointF pointF = documentData.f9600l;
        PointF pointF2 = documentData.f9601m;
        float e2 = Utils.e();
        float f3 = (i2 * documentData.f9594f * e2) + (pointF == null ? 0.0f : (documentData.f9594f * e2) + pointF.y);
        if (this.M.G() && pointF2 != null && pointF != null && f3 >= pointF.y + pointF2.y + documentData.f9591c) {
            return false;
        }
        float f4 = pointF == null ? 0.0f : pointF.x;
        float f5 = pointF2 != null ? pointF2.x : 0.0f;
        int i3 = AnonymousClass3.f9780a[documentData.f9592d.ordinal()];
        if (i3 == 1) {
            canvas.translate(f4, f3);
        } else if (i3 == 2) {
            canvas.translate((f4 + f5) - f2, f3);
        } else if (i3 == 3) {
            canvas.translate((f4 + (f5 / 2.0f)) - (f2 / 2.0f), f3);
        }
        return true;
    }

    private List g0(String str, float f2, Font font, float f3, float f4, boolean z) {
        float measureText;
        int i2 = 0;
        int i3 = 0;
        boolean z2 = false;
        int i4 = 0;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char charAt = str.charAt(i5);
            if (z) {
                FontCharacter fontCharacter = (FontCharacter) this.N.c().e(FontCharacter.c(charAt, font.a(), font.c()));
                if (fontCharacter != null) {
                    measureText = ((float) fontCharacter.b()) * f3 * Utils.e();
                }
            } else {
                measureText = this.G.measureText(str.substring(i5, i5 + 1));
            }
            float f8 = measureText + f4;
            if (charAt == ' ') {
                z2 = true;
                f7 = f8;
            } else if (z2) {
                z2 = false;
                i4 = i5;
                f6 = f8;
            } else {
                f6 += f8;
            }
            f5 += f8;
            if (f2 > 0.0f && f5 >= f2 && charAt != ' ') {
                i2++;
                TextSubLine a0 = a0(i2);
                if (i4 == i3) {
                    a0.c(str.substring(i3, i5).trim(), (f5 - f8) - ((r9.length() - r7.length()) * f7));
                    i3 = i5;
                    i4 = i3;
                    f5 = f8;
                    f6 = f5;
                } else {
                    a0.c(str.substring(i3, i4 - 1).trim(), ((f5 - f6) - ((r7.length() - r13.length()) * f7)) - f7);
                    f5 = f6;
                    i3 = i4;
                }
            }
        }
        if (f5 > 0.0f) {
            i2++;
            a0(i2).c(str.substring(i3), f5);
        }
        return this.K.subList(0, i2);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public void e(Object obj, LottieValueCallback lottieValueCallback) {
        super.e(obj, lottieValueCallback);
        if (obj == LottieProperty.f9305a) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.P;
            if (baseKeyframeAnimation != null) {
                I(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.P = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.P = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.a(this);
            j(this.P);
            return;
        }
        if (obj == LottieProperty.f9306b) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.R;
            if (baseKeyframeAnimation2 != null) {
                I(baseKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.R = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.R = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.a(this);
            j(this.R);
            return;
        }
        if (obj == LottieProperty.f9323s) {
            BaseKeyframeAnimation baseKeyframeAnimation3 = this.T;
            if (baseKeyframeAnimation3 != null) {
                I(baseKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.T = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.T = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.a(this);
            j(this.T);
            return;
        }
        if (obj == LottieProperty.t) {
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.V;
            if (baseKeyframeAnimation4 != null) {
                I(baseKeyframeAnimation4);
            }
            if (lottieValueCallback == null) {
                this.V = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.V = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.a(this);
            j(this.V);
            return;
        }
        if (obj == LottieProperty.F) {
            BaseKeyframeAnimation baseKeyframeAnimation5 = this.W;
            if (baseKeyframeAnimation5 != null) {
                I(baseKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.W = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.W = valueCallbackKeyframeAnimation5;
            valueCallbackKeyframeAnimation5.a(this);
            j(this.W);
            return;
        }
        if (obj != LottieProperty.M) {
            if (obj == LottieProperty.O) {
                this.L.r(lottieValueCallback);
                return;
            }
            return;
        }
        BaseKeyframeAnimation baseKeyframeAnimation6 = this.X;
        if (baseKeyframeAnimation6 != null) {
            I(baseKeyframeAnimation6);
        }
        if (lottieValueCallback == null) {
            this.X = null;
            return;
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
        this.X = valueCallbackKeyframeAnimation6;
        valueCallbackKeyframeAnimation6.a(this);
        j(this.X);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void g(RectF rectF, Matrix matrix, boolean z) {
        super.g(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.N.b().width(), this.N.b().height());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    void u(Canvas canvas, Matrix matrix, int i2) {
        DocumentData documentData = (DocumentData) this.L.h();
        Font font = (Font) this.N.g().get(documentData.f9590b);
        if (font == null) {
            return;
        }
        canvas.save();
        canvas.concat(matrix);
        R(documentData, i2);
        if (this.M.i1()) {
            Z(documentData, matrix, font, canvas);
        } else {
            Y(documentData, font, canvas);
        }
        canvas.restore();
    }
}
