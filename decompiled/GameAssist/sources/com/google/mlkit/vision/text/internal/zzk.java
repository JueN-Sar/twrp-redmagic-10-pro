package com.google.mlkit.vision.text.internal;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbh;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzbu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzcp;
import com.google.android.gms.internal.mlkit_vision_text_common.zzu;
import com.google.android.gms.internal.mlkit_vision_text_common.zzv;
import com.google.android.gms.internal.mlkit_vision_text_common.zzy;
import com.google.mlkit.vision.text.Text;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzk {

    @VisibleForTesting
    static final zzv zza = zzv.a("\n");

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator f16127a = new Comparator() { // from class: com.google.mlkit.vision.text.internal.zzf
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            zzv zzvVar = zzk.zza;
            return ((Integer) ((Map.Entry) obj).getValue()).compareTo((Integer) ((Map.Entry) obj2).getValue());
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    static Text a(com.google.android.gms.internal.mlkit_vision_text_common.zzl[] zzlVarArr, final Matrix matrix) {
        SparseArray sparseArray = new SparseArray();
        int i2 = 0;
        for (com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar : zzlVarArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzlVar.f13397p);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzlVar.f13397p, sparseArray2);
            }
            sparseArray2.append(zzlVar.f13398q, zzlVar);
        }
        zzbh zzbhVar = new zzbh();
        int i3 = 0;
        while (i3 < sparseArray.size()) {
            SparseArray sparseArray3 = (SparseArray) sparseArray.valueAt(i3);
            zzbh zzbhVar2 = new zzbh();
            for (int i4 = i2; i4 < sparseArray3.size(); i4++) {
                zzbhVar2.a((com.google.android.gms.internal.mlkit_vision_text_common.zzl) sparseArray3.valueAt(i4));
            }
            zzbk b2 = zzbhVar2.b();
            List a2 = zzbu.a(b2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzh
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object a(Object obj) {
                    com.google.android.gms.internal.mlkit_vision_text_common.zzl zzlVar2 = (com.google.android.gms.internal.mlkit_vision_text_common.zzl) obj;
                    zzv zzvVar = zzk.zza;
                    List b3 = zza.b(zzlVar2.f13389h);
                    String str = zzy.b(zzlVar2.f13392k) ? "" : zzlVar2.f13392k;
                    Rect a3 = zza.a(b3);
                    String str2 = zzy.b(zzlVar2.f13394m) ? "und" : zzlVar2.f13394m;
                    final Matrix matrix2 = matrix;
                    return new Text.Line(str, a3, b3, str2, matrix2, zzbu.a(Arrays.asList(zzlVar2.f13388c), new zzu() { // from class: com.google.mlkit.vision.text.internal.zzj
                        @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                        public final Object a(Object obj2) {
                            com.google.android.gms.internal.mlkit_vision_text_common.zzr zzrVar = (com.google.android.gms.internal.mlkit_vision_text_common.zzr) obj2;
                            zzv zzvVar2 = zzk.zza;
                            List b4 = zza.b(zzrVar.f13505h);
                            return new Text.Element(zzy.b(zzrVar.f13507j) ? "" : zzrVar.f13507j, zza.a(b4), b4, zzy.b(zzrVar.f13509l) ? "und" : zzrVar.f13509l, matrix2, zzrVar.f13508k, zzrVar.f13505h.f13185k, zzbk.k());
                        }
                    }), zzlVar2.f13393l, zzlVar2.f13389h.f13185k);
                }
            });
            com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) b2.get(i2)).f13389h;
            zzcp listIterator = b2.listIterator(i2);
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MAX_VALUE;
            int i8 = Integer.MIN_VALUE;
            while (listIterator.hasNext()) {
                com.google.android.gms.internal.mlkit_vision_text_common.zzf zzfVar2 = ((com.google.android.gms.internal.mlkit_vision_text_common.zzl) listIterator.next()).f13389h;
                int i9 = -zzfVar.f13181c;
                int i10 = -zzfVar.f13182h;
                List list = a2;
                double sin = Math.sin(Math.toRadians(zzfVar.f13185k));
                zzcp zzcpVar = listIterator;
                double cos = Math.cos(Math.toRadians(zzfVar.f13185k));
                SparseArray sparseArray4 = sparseArray;
                int i11 = i3;
                zzbh zzbhVar3 = zzbhVar;
                Point point = new Point(zzfVar2.f13181c, zzfVar2.f13182h);
                point.offset(i9, i10);
                Point[] pointArr = {point, new Point(zzfVar2.f13183i + r0, r2), new Point(zzfVar2.f13183i + r0, zzfVar2.f13184j + r2), new Point(r0, r2 + zzfVar2.f13184j)};
                Point point2 = pointArr[0];
                int i12 = point2.x;
                int i13 = point2.y;
                int i14 = i5;
                double d2 = i13 * sin;
                int i15 = i6;
                double d3 = (-i12) * sin;
                double d4 = i13 * cos;
                int i16 = (int) ((i12 * cos) + d2);
                point2.x = i16;
                int i17 = (int) (d3 + d4);
                point2.y = i17;
                i5 = i14;
                i8 = i8;
                i6 = i15;
                i7 = i7;
                int i18 = 0;
                for (int i19 = 4; i18 < i19; i19 = 4) {
                    Point point3 = pointArr[i18];
                    i6 = Math.min(i6, point3.x);
                    i5 = Math.max(i5, point3.x);
                    i7 = Math.min(i7, point3.y);
                    i8 = Math.max(i8, point3.y);
                    i18++;
                }
                a2 = list;
                listIterator = zzcpVar;
                sparseArray = sparseArray4;
                i3 = i11;
                zzbhVar = zzbhVar3;
            }
            zzbh zzbhVar4 = zzbhVar;
            SparseArray sparseArray5 = sparseArray;
            int i20 = i3;
            int i21 = i5;
            int i22 = i8;
            int i23 = i6;
            List list2 = a2;
            int i24 = zzfVar.f13181c;
            int i25 = zzfVar.f13182h;
            double sin2 = Math.sin(Math.toRadians(zzfVar.f13185k));
            double cos2 = Math.cos(Math.toRadians(zzfVar.f13185k));
            Point[] pointArr2 = {new Point(i23, i7), new Point(i21, i7), new Point(i21, i22), new Point(i23, i22)};
            int i26 = 0;
            for (int i27 = 4; i26 < i27; i27 = 4) {
                Point point4 = pointArr2[i26];
                int i28 = point4.x;
                int i29 = point4.y;
                point4.x = (int) ((i28 * cos2) - (i29 * sin2));
                point4.y = (int) ((i28 * sin2) + (i29 * cos2));
                point4.offset(i24, i25);
                i26++;
                sin2 = sin2;
            }
            List asList = Arrays.asList(pointArr2);
            zzbhVar4.a(new Text.TextBlock(zza.b(zzbu.a(list2, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzi
                @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
                public final Object a(Object obj) {
                    return ((Text.Line) obj).c();
                }
            })), zza.a(asList), asList, b(list2), matrix, list2));
            i3 = i20 + 1;
            zzbhVar = zzbhVar4;
            sparseArray = sparseArray5;
            i2 = 0;
        }
        zzbk b3 = zzbhVar.b();
        return new Text(zza.b(zzbu.a(b3, new zzu() { // from class: com.google.mlkit.vision.text.internal.zzg
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzu
            public final Object a(Object obj) {
                return ((Text.TextBlock) obj).c();
            }
        })), b3);
    }

    private static String b(List list) {
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String a2 = ((Text.Line) it.next()).a();
            hashMap.put(a2, Integer.valueOf((hashMap.containsKey(a2) ? ((Integer) hashMap.get(a2)).intValue() : 0) + 1));
        }
        Set entrySet = hashMap.entrySet();
        if (entrySet.isEmpty()) {
            return "und";
        }
        String str = (String) ((Map.Entry) Collections.max(entrySet, f16127a)).getKey();
        return !zzy.b(str) ? str : "und";
    }
}
