package com.google.mlkit.vision.text;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzuz;
import com.google.android.gms.internal.mlkit_vision_text_common.zzvb;
import com.google.android.gms.internal.mlkit_vision_text_common.zzvd;
import com.google.android.gms.internal.mlkit_vision_text_common.zzvf;
import com.google.android.gms.internal.mlkit_vision_text_common.zzvj;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.text.Text;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class Text {

    /* renamed from: a, reason: collision with root package name */
    private final List f16090a;

    /* renamed from: b, reason: collision with root package name */
    private final String f16091b;

    public static class Symbol extends TextBase {

        /* renamed from: e, reason: collision with root package name */
        private final float f16098e;

        /* renamed from: f, reason: collision with root package name */
        private final float f16099f;

        Symbol(zzvj zzvjVar, Matrix matrix) {
            super(zzvjVar.T(), zzvjVar.R(), zzvjVar.W(), "", matrix);
            this.f16098e = zzvjVar.P();
            this.f16099f = zzvjVar.G();
        }
    }

    static class TextBase {

        /* renamed from: a, reason: collision with root package name */
        private final String f16100a;

        /* renamed from: b, reason: collision with root package name */
        private final Rect f16101b;

        /* renamed from: c, reason: collision with root package name */
        private final Point[] f16102c;

        /* renamed from: d, reason: collision with root package name */
        private final String f16103d;

        TextBase(String str, Rect rect, List list, String str2, Matrix matrix) {
            this.f16100a = str;
            Rect rect2 = new Rect(rect);
            if (matrix != null) {
                CommonConvertUtils.d(rect2, matrix);
            }
            this.f16101b = rect2;
            Point[] pointArr = new Point[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                pointArr[i2] = new Point((Point) list.get(i2));
            }
            if (matrix != null) {
                CommonConvertUtils.c(pointArr, matrix);
            }
            this.f16102c = pointArr;
            this.f16103d = str2;
        }

        public String a() {
            return this.f16103d;
        }

        protected final String b() {
            String str = this.f16100a;
            return str == null ? "" : str;
        }
    }

    public Text(zzvf zzvfVar, final Matrix matrix) {
        ArrayList arrayList = new ArrayList();
        this.f16090a = arrayList;
        this.f16091b = zzvfVar.G();
        arrayList.addAll(zzbu.a(zzvfVar.P(), new zzu() { // from class: com.google.mlkit.vision.text.zza
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object a(Object obj) {
                return new Text.TextBlock((zzuz) obj, matrix);
            }
        }));
    }

    public String a() {
        return this.f16091b;
    }

    public Text(String str, List list) {
        ArrayList arrayList = new ArrayList();
        this.f16090a = arrayList;
        arrayList.addAll(list);
        this.f16091b = str;
    }

    public static class Line extends TextBase {

        /* renamed from: e, reason: collision with root package name */
        private final List f16095e;

        /* renamed from: f, reason: collision with root package name */
        private final float f16096f;

        /* renamed from: g, reason: collision with root package name */
        private final float f16097g;

        Line(zzvd zzvdVar, final Matrix matrix, float f2, float f3) {
            super(zzvdVar.W(), zzvdVar.R(), zzvdVar.Y(), zzvdVar.T(), matrix);
            this.f16095e = zzbu.a(zzvdVar.a0(), new zzu() { // from class: com.google.mlkit.vision.text.zzc
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object a(Object obj) {
                    return new Text.Element((zzvb) obj, matrix);
                }
            });
            this.f16096f = f2;
            this.f16097g = f3;
        }

        @Override // com.google.mlkit.vision.text.Text.TextBase
        public /* bridge */ /* synthetic */ String a() {
            return super.a();
        }

        public String c() {
            return b();
        }

        public Line(String str, Rect rect, List list, String str2, Matrix matrix, List list2, float f2, float f3) {
            super(str, rect, list, str2, matrix);
            this.f16095e = list2;
            this.f16096f = f2;
            this.f16097g = f3;
        }
    }

    public static class TextBlock extends TextBase {

        /* renamed from: e, reason: collision with root package name */
        private final List f16104e;

        TextBlock(zzuz zzuzVar, final Matrix matrix) {
            super(zzuzVar.R(), zzuzVar.G(), zzuzVar.T(), zzuzVar.P(), matrix);
            this.f16104e = zzbu.a(zzuzVar.W(), new zzu() { // from class: com.google.mlkit.vision.text.zzd
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object a(Object obj) {
                    zzvd zzvdVar = (zzvd) obj;
                    return new Text.Line(zzvdVar, matrix, zzvdVar.P(), zzvdVar.G());
                }
            });
        }

        public String c() {
            return b();
        }

        public TextBlock(String str, Rect rect, List list, String str2, Matrix matrix, List list2) {
            super(str, rect, list, str2, matrix);
            this.f16104e = list2;
        }
    }

    public static class Element extends TextBase {

        /* renamed from: e, reason: collision with root package name */
        private final List f16092e;

        /* renamed from: f, reason: collision with root package name */
        private final float f16093f;

        /* renamed from: g, reason: collision with root package name */
        private final float f16094g;

        Element(zzvb zzvbVar, final Matrix matrix) {
            super(zzvbVar.W(), zzvbVar.R(), zzvbVar.Y(), zzvbVar.T(), matrix);
            this.f16093f = zzvbVar.P();
            this.f16094g = zzvbVar.G();
            List a0 = zzvbVar.a0();
            this.f16092e = zzbu.a(a0 == null ? new ArrayList() : a0, new zzu() { // from class: com.google.mlkit.vision.text.zzb
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object a(Object obj) {
                    return new Text.Symbol((zzvj) obj, matrix);
                }
            });
        }

        public Element(String str, Rect rect, List list, String str2, Matrix matrix, float f2, float f3, List list2) {
            super(str, rect, list, str2, matrix);
            this.f16093f = f2;
            this.f16094g = f3;
            this.f16092e = list2;
        }
    }
}
