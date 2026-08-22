package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class zbvp<T> implements zbvx<T> {

    /* renamed from: l, reason: collision with root package name */
    private static final int[] f13004l = new int[0];

    /* renamed from: m, reason: collision with root package name */
    private static final Unsafe f13005m = zbws.l();

    /* renamed from: a, reason: collision with root package name */
    private final int[] f13006a;

    /* renamed from: b, reason: collision with root package name */
    private final Object[] f13007b;

    /* renamed from: c, reason: collision with root package name */
    private final int f13008c;

    /* renamed from: d, reason: collision with root package name */
    private final int f13009d;

    /* renamed from: e, reason: collision with root package name */
    private final zbvm f13010e;

    /* renamed from: f, reason: collision with root package name */
    private final boolean f13011f;

    /* renamed from: g, reason: collision with root package name */
    private final int[] f13012g;

    /* renamed from: h, reason: collision with root package name */
    private final int f13013h;

    /* renamed from: i, reason: collision with root package name */
    private final int f13014i;

    /* renamed from: j, reason: collision with root package name */
    private final zbwl f13015j;

    /* renamed from: k, reason: collision with root package name */
    private final zbtq f13016k;

    private zbvp(int[] iArr, Object[] objArr, int i2, int i3, zbvm zbvmVar, boolean z, int[] iArr2, int i4, int i5, zbvs zbvsVar, zbuy zbuyVar, zbwl zbwlVar, zbtq zbtqVar, zbvh zbvhVar) {
        this.f13006a = iArr;
        this.f13007b = objArr;
        this.f13008c = i2;
        this.f13009d = i3;
        boolean z2 = false;
        if (zbtqVar != null && (zbvmVar instanceof zbub)) {
            z2 = true;
        }
        this.f13011f = z2;
        this.f13012g = iArr2;
        this.f13013h = i4;
        this.f13014i = i5;
        this.f13015j = zbwlVar;
        this.f13016k = zbtqVar;
        this.f13010e = zbvmVar;
    }

    static zbwm A(Object obj) {
        zbuf zbufVar = (zbuf) obj;
        zbwm zbwmVar = zbufVar.zbc;
        if (zbwmVar != zbwm.c()) {
            return zbwmVar;
        }
        zbwm f2 = zbwm.f();
        zbufVar.zbc = f2;
        return f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0269  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvp B(java.lang.Class r34, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvj r35, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvs r36, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuy r37, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwl r38, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtq r39, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvh r40) {
        /*
            Method dump skipped, instructions count: 1041
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvp.B(java.lang.Class, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvj, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvs, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuy, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwl, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtq, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvh):com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvp");
    }

    private static double C(Object obj, long j2) {
        return ((Double) zbws.k(obj, j2)).doubleValue();
    }

    private static float D(Object obj, long j2) {
        return ((Float) zbws.k(obj, j2)).floatValue();
    }

    private static int E(Object obj, long j2) {
        return ((Integer) zbws.k(obj, j2)).intValue();
    }

    private final int F(int i2) {
        return this.f13006a[i2 + 2];
    }

    private final int G(int i2, int i3) {
        int length = (this.f13006a.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int i6 = this.f13006a[i5];
            if (i2 == i6) {
                return i5;
            }
            if (i2 < i6) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    private static int H(int i2) {
        return (i2 >>> 20) & 255;
    }

    private final int I(int i2) {
        return this.f13006a[i2 + 1];
    }

    private static long J(Object obj, long j2) {
        return ((Long) zbws.k(obj, j2)).longValue();
    }

    private final zbuj K(int i2) {
        int i3 = i2 / 3;
        return (zbuj) this.f13007b[i3 + i3 + 1];
    }

    private final zbvx L(int i2) {
        Object[] objArr = this.f13007b;
        int i3 = i2 / 3;
        int i4 = i3 + i3;
        zbvx zbvxVar = (zbvx) objArr[i4];
        if (zbvxVar != null) {
            return zbvxVar;
        }
        zbvx b2 = zbvu.a().b((Class) objArr[i4 + 1]);
        this.f13007b[i4] = b2;
        return b2;
    }

    private final Object M(int i2) {
        int i3 = i2 / 3;
        return this.f13007b[i3 + i3];
    }

    private final Object N(Object obj, int i2) {
        zbvx L = L(i2);
        int I = I(i2) & 1048575;
        if (!r(obj, i2)) {
            return L.b();
        }
        Object object = f13005m.getObject(obj, I);
        if (u(object)) {
            return object;
        }
        Object b2 = L.b();
        if (object != null) {
            L.c(b2, object);
        }
        return b2;
    }

    private final Object O(Object obj, int i2, int i3) {
        zbvx L = L(i3);
        if (!v(obj, i2, i3)) {
            return L.b();
        }
        Object object = f13005m.getObject(obj, I(i3) & 1048575);
        if (u(object)) {
            return object;
        }
        Object b2 = L.b();
        if (object != null) {
            L.c(b2, object);
        }
        return b2;
    }

    private static Field P(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void j(Object obj) {
        if (!u(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private final void k(Object obj, Object obj2, int i2) {
        if (r(obj2, i2)) {
            int I = I(i2) & 1048575;
            Unsafe unsafe = f13005m;
            long j2 = I;
            Object object = unsafe.getObject(obj2, j2);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.f13006a[i2] + " is present but null: " + obj2.toString());
            }
            zbvx L = L(i2);
            if (!r(obj, i2)) {
                if (u(object)) {
                    Object b2 = L.b();
                    L.c(b2, object);
                    unsafe.putObject(obj, j2, b2);
                } else {
                    unsafe.putObject(obj, j2, object);
                }
                m(obj, i2);
                return;
            }
            Object object2 = unsafe.getObject(obj, j2);
            if (!u(object2)) {
                Object b3 = L.b();
                L.c(b3, object2);
                unsafe.putObject(obj, j2, b3);
                object2 = b3;
            }
            L.c(object2, object);
        }
    }

    private final void l(Object obj, Object obj2, int i2) {
        int i3 = this.f13006a[i2];
        if (v(obj2, i3, i2)) {
            int I = I(i2) & 1048575;
            Unsafe unsafe = f13005m;
            long j2 = I;
            Object object = unsafe.getObject(obj2, j2);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.f13006a[i2] + " is present but null: " + obj2.toString());
            }
            zbvx L = L(i2);
            if (!v(obj, i3, i2)) {
                if (u(object)) {
                    Object b2 = L.b();
                    L.c(b2, object);
                    unsafe.putObject(obj, j2, b2);
                } else {
                    unsafe.putObject(obj, j2, object);
                }
                n(obj, i3, i2);
                return;
            }
            Object object2 = unsafe.getObject(obj, j2);
            if (!u(object2)) {
                Object b3 = L.b();
                L.c(b3, object2);
                unsafe.putObject(obj, j2, b3);
                object2 = b3;
            }
            L.c(object2, object);
        }
    }

    private final void m(Object obj, int i2) {
        int F = F(i2);
        long j2 = 1048575 & F;
        if (j2 == 1048575) {
            return;
        }
        zbws.v(obj, j2, (1 << (F >>> 20)) | zbws.h(obj, j2));
    }

    private final void n(Object obj, int i2, int i3) {
        zbws.v(obj, F(i3) & 1048575, i2);
    }

    private final void o(Object obj, int i2, Object obj2) {
        f13005m.putObject(obj, I(i2) & 1048575, obj2);
        m(obj, i2);
    }

    private final void p(Object obj, int i2, int i3, Object obj2) {
        f13005m.putObject(obj, I(i3) & 1048575, obj2);
        n(obj, i2, i3);
    }

    private final boolean q(Object obj, Object obj2, int i2) {
        return r(obj, i2) == r(obj2, i2);
    }

    private final boolean r(Object obj, int i2) {
        int F = F(i2);
        long j2 = F & 1048575;
        if (j2 != 1048575) {
            return ((1 << (F >>> 20)) & zbws.h(obj, j2)) != 0;
        }
        int I = I(i2);
        long j3 = I & 1048575;
        switch (H(I)) {
            case 0:
                return Double.doubleToRawLongBits(zbws.f(obj, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zbws.g(obj, j3)) != 0;
            case 2:
                return zbws.i(obj, j3) != 0;
            case 3:
                return zbws.i(obj, j3) != 0;
            case 4:
                return zbws.h(obj, j3) != 0;
            case 5:
                return zbws.i(obj, j3) != 0;
            case 6:
                return zbws.h(obj, j3) != 0;
            case 7:
                return zbws.B(obj, j3);
            case 8:
                Object k2 = zbws.k(obj, j3);
                if (k2 instanceof String) {
                    return !((String) k2).isEmpty();
                }
                if (k2 instanceof zbtc) {
                    return !zbtc.zbb.equals(k2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zbws.k(obj, j3) != null;
            case 10:
                return !zbtc.zbb.equals(zbws.k(obj, j3));
            case 11:
                return zbws.h(obj, j3) != 0;
            case 12:
                return zbws.h(obj, j3) != 0;
            case 13:
                return zbws.h(obj, j3) != 0;
            case 14:
                return zbws.i(obj, j3) != 0;
            case 15:
                return zbws.h(obj, j3) != 0;
            case 16:
                return zbws.i(obj, j3) != 0;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                return zbws.k(obj, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean s(Object obj, int i2, int i3, int i4, int i5) {
        return i3 == 1048575 ? r(obj, i2) : (i4 & i5) != 0;
    }

    private static boolean t(Object obj, int i2, zbvx zbvxVar) {
        return zbvxVar.e(zbws.k(obj, i2 & 1048575));
    }

    private static boolean u(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zbuf) {
            return ((zbuf) obj).p();
        }
        return true;
    }

    private final boolean v(Object obj, int i2, int i3) {
        return zbws.h(obj, (long) (F(i3) & 1048575)) == i2;
    }

    private static boolean w(Object obj, long j2) {
        return ((Boolean) zbws.k(obj, j2)).booleanValue();
    }

    private static final int x(byte[] bArr, int i2, int i3, zbww zbwwVar, Class cls, zbsq zbsqVar) {
        int i4;
        zbww zbwwVar2 = zbww.zba;
        switch (zbwwVar.ordinal()) {
            case 0:
                i4 = i2 + 8;
                zbsqVar.f12937c = Double.valueOf(Double.longBitsToDouble(zbsr.r(bArr, i2)));
                break;
            case 1:
                i4 = i2 + 4;
                zbsqVar.f12937c = Float.valueOf(Float.intBitsToFloat(zbsr.c(bArr, i2)));
                break;
            case 2:
            case 3:
                int n2 = zbsr.n(bArr, i2, zbsqVar);
                zbsqVar.f12937c = Long.valueOf(zbsqVar.f12936b);
                return n2;
            case 4:
            case 12:
            case 13:
                int k2 = zbsr.k(bArr, i2, zbsqVar);
                zbsqVar.f12937c = Integer.valueOf(zbsqVar.f12935a);
                return k2;
            case 5:
            case 15:
                i4 = i2 + 8;
                zbsqVar.f12937c = Long.valueOf(zbsr.r(bArr, i2));
                break;
            case 6:
            case 14:
                i4 = i2 + 4;
                zbsqVar.f12937c = Integer.valueOf(zbsr.c(bArr, i2));
                break;
            case 7:
                int n3 = zbsr.n(bArr, i2, zbsqVar);
                zbsqVar.f12937c = Boolean.valueOf(zbsqVar.f12936b != 0);
                return n3;
            case 8:
                return zbsr.i(bArr, i2, zbsqVar);
            case 9:
            default:
                throw new RuntimeException("unsupported field type.");
            case 10:
                return zbsr.e(zbvu.a().b(cls), bArr, i2, i3, zbsqVar);
            case 11:
                return zbsr.a(bArr, i2, zbsqVar);
            case 16:
                int k3 = zbsr.k(bArr, i2, zbsqVar);
                zbsqVar.f12937c = Integer.valueOf(zbtg.a(zbsqVar.f12935a));
                return k3;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                int n4 = zbsr.n(bArr, i2, zbsqVar);
                zbsqVar.f12937c = Long.valueOf(zbtg.b(zbsqVar.f12936b));
                return n4;
        }
        return i4;
    }

    private static final void y(int i2, Object obj, zbwy zbwyVar) {
        if (obj instanceof String) {
            zbwyVar.t(i2, (String) obj);
        } else {
            zbwyVar.G(i2, (zbtc) obj);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v115, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v118, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v120, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v137 */
    /* JADX WARN: Type inference failed for: r0v185, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v253, types: [int] */
    /* JADX WARN: Type inference failed for: r0v260, types: [int] */
    /* JADX WARN: Type inference failed for: r0v262 */
    /* JADX WARN: Type inference failed for: r0v263 */
    /* JADX WARN: Type inference failed for: r0v264 */
    /* JADX WARN: Type inference failed for: r0v265 */
    /* JADX WARN: Type inference failed for: r0v266 */
    /* JADX WARN: Type inference failed for: r0v267 */
    /* JADX WARN: Type inference failed for: r0v268 */
    /* JADX WARN: Type inference failed for: r0v269 */
    /* JADX WARN: Type inference failed for: r0v270 */
    /* JADX WARN: Type inference failed for: r0v271 */
    /* JADX WARN: Type inference failed for: r0v272 */
    /* JADX WARN: Type inference failed for: r0v273 */
    /* JADX WARN: Type inference failed for: r0v274 */
    /* JADX WARN: Type inference failed for: r0v275 */
    /* JADX WARN: Type inference failed for: r0v276 */
    /* JADX WARN: Type inference failed for: r0v277 */
    /* JADX WARN: Type inference failed for: r1v118, types: [int] */
    /* JADX WARN: Type inference failed for: r1v121, types: [int] */
    /* JADX WARN: Type inference failed for: r1v165 */
    /* JADX WARN: Type inference failed for: r1v166 */
    /* JADX WARN: Type inference failed for: r1v78, types: [int] */
    /* JADX WARN: Type inference failed for: r1v80 */
    /* JADX WARN: Type inference failed for: r2v32, types: [int] */
    /* JADX WARN: Type inference failed for: r2v40, types: [int] */
    /* JADX WARN: Type inference failed for: r2v44, types: [int] */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53, types: [int] */
    /* JADX WARN: Type inference failed for: r2v81, types: [int] */
    /* JADX WARN: Type inference failed for: r2v82 */
    /* JADX WARN: Type inference failed for: r2v84 */
    /* JADX WARN: Type inference failed for: r2v85, types: [int] */
    /* JADX WARN: Type inference failed for: r2v93 */
    /* JADX WARN: Type inference failed for: r2v94 */
    /* JADX WARN: Type inference failed for: r2v95 */
    /* JADX WARN: Type inference failed for: r2v96 */
    /* JADX WARN: Type inference failed for: r2v97 */
    /* JADX WARN: Type inference failed for: r2v98 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27, types: [int] */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30, types: [int] */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v39, types: [int] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v46, types: [int] */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v61 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31, types: [int] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v38, types: [int] */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v62 */
    /* JADX WARN: Type inference failed for: r4v63 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int] */
    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final int a(Object obj) {
        int i2;
        boolean z;
        ?? r5;
        int d2;
        int d3;
        int d4;
        int e2;
        int d5;
        int d6;
        int f2;
        int d7;
        ?? l2;
        int size;
        int d8;
        int c2;
        int c3;
        ?? r3;
        int b2;
        ?? r1;
        ?? r0;
        int j2;
        int d9;
        int d10;
        ?? r4;
        Unsafe unsafe = f13005m;
        boolean z2 = false;
        int i3 = 1048575;
        boolean z3 = false;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i4 < this.f13006a.length) {
            int I = I(i4);
            int H = H(I);
            int[] iArr = this.f13006a;
            int i7 = iArr[i4];
            int i8 = iArr[i4 + 2];
            int i9 = i8 & i3;
            if (H <= 17) {
                if (i9 != i6) {
                    z3 = i9 == i3 ? z2 : unsafe.getInt(obj, i9);
                    i6 = i9;
                }
                i2 = i6;
                z = z3;
                r5 = 1 << (i8 >>> 20);
            } else {
                i2 = i6;
                z = z3;
                r5 = z2;
            }
            int i10 = I & i3;
            if (H >= zbtv.zbJ.a()) {
                zbtv.zbW.a();
            }
            long j3 = i10;
            switch (H) {
                case 0:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        long j4 = unsafe.getLong(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(j4);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        long j5 = unsafe.getLong(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(j5);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        long j6 = unsafe.getInt(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(j6);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d5 = zbtk.d(i7 << 3);
                        r0 = d5 + 1;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!s(obj, i4, i2, z ? 1 : 0, r5)) {
                        break;
                    } else {
                        int i11 = i7 << 3;
                        Object object = unsafe.getObject(obj, j3);
                        if (object instanceof zbtc) {
                            d6 = zbtk.d(i11);
                            f2 = ((zbtc) object).f();
                            d7 = zbtk.d(f2);
                            r0 = d6 + d7 + f2;
                            i5 += r0;
                            break;
                        } else {
                            d4 = zbtk.d(i11);
                            e2 = zbtk.c((String) object);
                            r0 = d4 + e2;
                            i5 += r0;
                        }
                    }
                case 9:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        r0 = zbvz.m(i7, unsafe.getObject(obj, j3), L(i4));
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        zbtc zbtcVar = (zbtc) unsafe.getObject(obj, j3);
                        d6 = zbtk.d(i7 << 3);
                        f2 = zbtcVar.f();
                        d7 = zbtk.d(f2);
                        r0 = d6 + d7 + f2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        int i12 = unsafe.getInt(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.d(i12);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        long j7 = unsafe.getInt(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(j7);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        int i13 = unsafe.getInt(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.d((i13 >> 31) ^ (i13 + i13));
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        long j8 = unsafe.getLong(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e((j8 >> 63) ^ (j8 + j8));
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    if (s(obj, i4, i2, z ? 1 : 0, r5)) {
                        r0 = zbtk.G(i7, (zbvm) unsafe.getObject(obj, j3), L(i4));
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case MlKitException.UNSUPPORTED /* 18 */:
                    r0 = zbvz.i(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 19:
                    r0 = zbvz.g(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj, j3);
                    int i14 = zbvz.f13034b;
                    if (list.size() != 0) {
                        l2 = zbvz.l(list) + (list.size() * zbtk.d(i7 << 3));
                        i5 += l2;
                        break;
                    }
                    l2 = z2;
                    i5 += l2;
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    List list2 = (List) unsafe.getObject(obj, j3);
                    int i15 = zbvz.f13034b;
                    size = list2.size();
                    if (size != 0) {
                        d4 = zbvz.q(list2);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 22:
                    List list3 = (List) unsafe.getObject(obj, j3);
                    int i16 = zbvz.f13034b;
                    size = list3.size();
                    if (size != 0) {
                        d4 = zbvz.k(list3);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 23:
                    r0 = zbvz.i(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 24:
                    r0 = zbvz.g(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj, j3);
                    int i17 = zbvz.f13034b;
                    int size2 = list4.size();
                    if (size2 != 0) {
                        r0 = size2 * (zbtk.d(i7 << 3) + 1);
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 26:
                    ?? r02 = (List) unsafe.getObject(obj, j3);
                    int i18 = zbvz.f13034b;
                    int size3 = r02.size();
                    if (size3 != 0) {
                        int d11 = zbtk.d(i7 << 3) * size3;
                        if (r02 instanceof zbux) {
                            zbux zbuxVar = (zbux) r02;
                            l2 = d11;
                            for (?? r32 = z2; r32 < size3; r32++) {
                                Object a2 = zbuxVar.a();
                                if (a2 instanceof zbtc) {
                                    int f3 = ((zbtc) a2).f();
                                    c3 = l2 + zbtk.d(f3) + f3;
                                } else {
                                    c3 = l2 + zbtk.c((String) a2);
                                }
                                l2 = c3;
                            }
                        } else {
                            l2 = d11;
                            for (?? r33 = z2; r33 < size3; r33++) {
                                Object obj2 = r02.get(r33);
                                if (obj2 instanceof zbtc) {
                                    int f4 = ((zbtc) obj2).f();
                                    c2 = l2 + zbtk.d(f4) + f4;
                                } else {
                                    c2 = l2 + zbtk.c((String) obj2);
                                }
                                l2 = c2;
                            }
                        }
                        i5 += l2;
                        break;
                    }
                    l2 = z2;
                    i5 += l2;
                case 27:
                    ?? r03 = (List) unsafe.getObject(obj, j3);
                    zbvx L = L(i4);
                    int i19 = zbvz.f13034b;
                    int size4 = r03.size();
                    if (size4 == 0) {
                        r3 = z2;
                    } else {
                        r3 = zbtk.d(i7 << 3) * size4;
                        for (?? r42 = z2; r42 < size4; r42++) {
                            Object obj3 = r03.get(r42);
                            if (obj3 instanceof zbuw) {
                                int a3 = ((zbuw) obj3).a();
                                b2 = (r3 == true ? 1 : 0) + zbtk.d(a3) + a3;
                            } else {
                                b2 = (r3 == true ? 1 : 0) + zbtk.b((zbvm) obj3, L);
                            }
                            r3 = b2;
                        }
                    }
                    i5 += r3;
                    break;
                case 28:
                    ?? r04 = (List) unsafe.getObject(obj, j3);
                    int i20 = zbvz.f13034b;
                    int size5 = r04.size();
                    if (size5 == 0) {
                        r1 = z2;
                    } else {
                        r1 = size5 * zbtk.d(i7 << 3);
                        for (?? r2 = z2; r2 < r04.size(); r2++) {
                            int f5 = ((zbtc) r04.get(r2)).f();
                            r1 += zbtk.d(f5) + f5;
                        }
                    }
                    i5 += r1;
                    break;
                case 29:
                    List list5 = (List) unsafe.getObject(obj, j3);
                    int i21 = zbvz.f13034b;
                    size = list5.size();
                    if (size != 0) {
                        d4 = zbvz.p(list5);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 30:
                    List list6 = (List) unsafe.getObject(obj, j3);
                    int i22 = zbvz.f13034b;
                    size = list6.size();
                    if (size != 0) {
                        d4 = zbvz.f(list6);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 31:
                    r0 = zbvz.g(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 32:
                    r0 = zbvz.i(i7, (List) unsafe.getObject(obj, j3), z2);
                    i5 += r0;
                    break;
                case 33:
                    List list7 = (List) unsafe.getObject(obj, j3);
                    int i23 = zbvz.f13034b;
                    size = list7.size();
                    if (size != 0) {
                        d4 = zbvz.n(list7);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 34:
                    List list8 = (List) unsafe.getObject(obj, j3);
                    int i24 = zbvz.f13034b;
                    size = list8.size();
                    if (size != 0) {
                        d4 = zbvz.o(list8);
                        d8 = zbtk.d(i7 << 3);
                        e2 = size * d8;
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    }
                    r0 = z2;
                    i5 += r0;
                case 35:
                    j2 = zbvz.j((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    j2 = zbvz.h((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    j2 = zbvz.l((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    j2 = zbvz.q((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    j2 = zbvz.k((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    j2 = zbvz.j((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    j2 = zbvz.h((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    List list9 = (List) unsafe.getObject(obj, j3);
                    int i25 = zbvz.f13034b;
                    j2 = list9.size();
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    j2 = zbvz.p((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    j2 = zbvz.f((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    j2 = zbvz.h((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    j2 = zbvz.j((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    j2 = zbvz.n((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    j2 = zbvz.o((List) unsafe.getObject(obj, j3));
                    if (j2 > 0) {
                        d9 = zbtk.d(i7 << 3);
                        d10 = zbtk.d(j2);
                        r1 = d9 + d10 + j2;
                        i5 += r1;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    ?? r05 = (List) unsafe.getObject(obj, j3);
                    zbvx L2 = L(i4);
                    int i26 = zbvz.f13034b;
                    int size6 = r05.size();
                    if (size6 == 0) {
                        r4 = z2;
                    } else {
                        boolean z4 = z2;
                        r4 = z4;
                        ?? r34 = z4;
                        while (r34 < size6) {
                            int G = zbtk.G(i7, (zbvm) r05.get(r34), L2);
                            r34++;
                            r4 = (r4 == true ? 1 : 0) + G;
                        }
                    }
                    i5 += r4;
                    break;
                case 50:
                    zbvg zbvgVar = (zbvg) unsafe.getObject(obj, j3);
                    zbvf zbvfVar = (zbvf) M(i4);
                    if (!zbvgVar.isEmpty()) {
                        l2 = z2;
                        for (Map.Entry entry : zbvgVar.entrySet()) {
                            l2 += zbvfVar.a(i7, entry.getKey(), entry.getValue());
                        }
                        i5 += l2;
                        break;
                    }
                    l2 = z2;
                    i5 += l2;
                case 51:
                    if (v(obj, i7, i4)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (v(obj, i7, i4)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (v(obj, i7, i4)) {
                        long J = J(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(J);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (v(obj, i7, i4)) {
                        long J2 = J(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(J2);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (v(obj, i7, i4)) {
                        long E = E(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(E);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (v(obj, i7, i4)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (v(obj, i7, i4)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (v(obj, i7, i4)) {
                        d5 = zbtk.d(i7 << 3);
                        r0 = d5 + 1;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!v(obj, i7, i4)) {
                        break;
                    } else {
                        int i27 = i7 << 3;
                        Object object2 = unsafe.getObject(obj, j3);
                        if (object2 instanceof zbtc) {
                            d6 = zbtk.d(i27);
                            f2 = ((zbtc) object2).f();
                            d7 = zbtk.d(f2);
                            r0 = d6 + d7 + f2;
                            i5 += r0;
                            break;
                        } else {
                            d4 = zbtk.d(i27);
                            e2 = zbtk.c((String) object2);
                            r0 = d4 + e2;
                            i5 += r0;
                        }
                    }
                case 60:
                    if (v(obj, i7, i4)) {
                        r0 = zbvz.m(i7, unsafe.getObject(obj, j3), L(i4));
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (v(obj, i7, i4)) {
                        zbtc zbtcVar2 = (zbtc) unsafe.getObject(obj, j3);
                        d6 = zbtk.d(i7 << 3);
                        f2 = zbtcVar2.f();
                        d7 = zbtk.d(f2);
                        r0 = d6 + d7 + f2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (v(obj, i7, i4)) {
                        int E2 = E(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.d(E2);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (v(obj, i7, i4)) {
                        long E3 = E(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e(E3);
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (v(obj, i7, i4)) {
                        d3 = zbtk.d(i7 << 3);
                        r0 = d3 + 4;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (v(obj, i7, i4)) {
                        d2 = zbtk.d(i7 << 3);
                        r0 = d2 + 8;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (v(obj, i7, i4)) {
                        int E4 = E(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.d((E4 >> 31) ^ (E4 + E4));
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (v(obj, i7, i4)) {
                        long J3 = J(obj, j3);
                        d4 = zbtk.d(i7 << 3);
                        e2 = zbtk.e((J3 >> 63) ^ (J3 + J3));
                        r0 = d4 + e2;
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (v(obj, i7, i4)) {
                        r0 = zbtk.G(i7, (zbvm) unsafe.getObject(obj, j3), L(i4));
                        i5 += r0;
                        break;
                    } else {
                        break;
                    }
            }
            i4 += 3;
            i6 = i2;
            z3 = z;
            z2 = false;
            i3 = 1048575;
        }
        int a4 = i5 + ((zbuf) obj).zbc.a();
        if (!this.f13011f) {
            return a4;
        }
        zbtu zbtuVar = ((zbub) obj).zbb;
        int c4 = zbtuVar.f12967a.c();
        int i28 = 0;
        for (int i29 = 0; i29 < c4; i29++) {
            Map.Entry g2 = zbtuVar.f12967a.g(i29);
            i28 += zbtu.b((zbtt) ((zbwb) g2).c(), g2.getValue());
        }
        for (Map.Entry entry2 : zbtuVar.f12967a.d()) {
            i28 += zbtu.b((zbtt) entry2.getKey(), entry2.getValue());
        }
        return a4 + i28;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final Object b() {
        return ((zbuf) this.f13010e).x();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void c(Object obj, Object obj2) {
        j(obj);
        obj2.getClass();
        for (int i2 = 0; i2 < this.f13006a.length; i2 += 3) {
            int I = I(i2);
            int i3 = 1048575 & I;
            int[] iArr = this.f13006a;
            int H = H(I);
            int i4 = iArr[i2];
            long j2 = i3;
            switch (H) {
                case 0:
                    if (r(obj2, i2)) {
                        zbws.t(obj, j2, zbws.f(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (r(obj2, i2)) {
                        zbws.u(obj, j2, zbws.g(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (r(obj2, i2)) {
                        zbws.w(obj, j2, zbws.i(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (r(obj2, i2)) {
                        zbws.w(obj, j2, zbws.i(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (r(obj2, i2)) {
                        zbws.w(obj, j2, zbws.i(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (r(obj2, i2)) {
                        zbws.r(obj, j2, zbws.B(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (r(obj2, i2)) {
                        zbws.x(obj, j2, zbws.k(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    k(obj, obj2, i2);
                    break;
                case 10:
                    if (r(obj2, i2)) {
                        zbws.x(obj, j2, zbws.k(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (r(obj2, i2)) {
                        zbws.w(obj, j2, zbws.i(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (r(obj2, i2)) {
                        zbws.v(obj, j2, zbws.h(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (r(obj2, i2)) {
                        zbws.w(obj, j2, zbws.i(obj2, j2));
                        m(obj, i2);
                        break;
                    } else {
                        break;
                    }
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    k(obj, obj2, i2);
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                case 19:
                case 20:
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zbun zbunVar = (zbun) zbws.k(obj, j2);
                    zbun zbunVar2 = (zbun) zbws.k(obj2, j2);
                    int size = zbunVar.size();
                    int size2 = zbunVar2.size();
                    if (size > 0 && size2 > 0) {
                        if (!zbunVar.zbc()) {
                            zbunVar = zbunVar.e(size2 + size);
                        }
                        zbunVar.addAll(zbunVar2);
                    }
                    if (size > 0) {
                        zbunVar2 = zbunVar;
                    }
                    zbws.x(obj, j2, zbunVar2);
                    break;
                case 50:
                    int i5 = zbvz.f13034b;
                    zbws.x(obj, j2, zbvh.a(zbws.k(obj, j2), zbws.k(obj2, j2)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (v(obj2, i4, i2)) {
                        zbws.x(obj, j2, zbws.k(obj2, j2));
                        n(obj, i4, i2);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    l(obj, obj2, i2);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (v(obj2, i4, i2)) {
                        zbws.x(obj, j2, zbws.k(obj2, j2));
                        n(obj, i4, i2);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    l(obj, obj2, i2);
                    break;
            }
        }
        zbvz.u(this.f13015j, obj, obj2);
        if (this.f13011f) {
            zbvz.t(this.f13016k, obj, obj2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:250:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(java.lang.Object r24, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy r25) {
        /*
            Method dump skipped, instructions count: 1896
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvp.d(java.lang.Object, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwy):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final boolean e(Object obj) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        while (i5 < this.f13013h) {
            int[] iArr = this.f13012g;
            int[] iArr2 = this.f13006a;
            int i7 = iArr[i5];
            int i8 = iArr2[i7];
            int I = I(i7);
            int i9 = this.f13006a[i7 + 2];
            int i10 = i9 & 1048575;
            int i11 = 1 << (i9 >>> 20);
            if (i10 != i6) {
                if (i10 != 1048575) {
                    i4 = f13005m.getInt(obj, i10);
                }
                i3 = i4;
                i2 = i10;
            } else {
                i2 = i6;
                i3 = i4;
            }
            if ((268435456 & I) != 0 && !s(obj, i7, i2, i3, i11)) {
                return false;
            }
            int H = H(I);
            if (H != 9 && H != 17) {
                if (H != 27) {
                    if (H == 60 || H == 68) {
                        if (v(obj, i8, i7) && !t(obj, I, L(i7))) {
                            return false;
                        }
                    } else if (H != 49) {
                        if (H != 50) {
                            continue;
                        } else {
                            zbvg zbvgVar = (zbvg) zbws.k(obj, I & 1048575);
                            if (!zbvgVar.isEmpty() && ((zbvf) M(i7)).c().f12999c.c() == zbwx.MESSAGE) {
                                zbvx zbvxVar = null;
                                for (Object obj2 : zbvgVar.values()) {
                                    if (zbvxVar == null) {
                                        zbvxVar = zbvu.a().b(obj2.getClass());
                                    }
                                    if (!zbvxVar.e(obj2)) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                List list = (List) zbws.k(obj, I & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zbvx L = L(i7);
                    for (int i12 = 0; i12 < list.size(); i12++) {
                        if (!L.e(list.get(i12))) {
                            return false;
                        }
                    }
                }
            } else if (s(obj, i7, i2, i3, i11) && !t(obj, I, L(i7))) {
                return false;
            }
            i5++;
            i6 = i2;
            i4 = i3;
        }
        return !this.f13011f || ((zbub) obj).zbb.m();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void f(Object obj, byte[] bArr, int i2, int i3, zbsq zbsqVar) {
        z(obj, bArr, i2, i3, 0, zbsqVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void g(Object obj) {
        if (u(obj)) {
            if (obj instanceof zbuf) {
                zbuf zbufVar = (zbuf) obj;
                zbufVar.n(Api.BaseClientBuilder.API_PRIORITY_OTHER);
                zbufVar.zba = 0;
                zbufVar.l();
            }
            int[] iArr = this.f13006a;
            for (int i2 = 0; i2 < iArr.length; i2 += 3) {
                int I = I(i2);
                int i3 = 1048575 & I;
                int H = H(I);
                long j2 = i3;
                if (H != 9) {
                    if (H != 60 && H != 68) {
                        switch (H) {
                            case MlKitException.UNSUPPORTED /* 18 */:
                            case 19:
                            case 20:
                            case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                ((zbun) zbws.k(obj, j2)).c();
                                break;
                            case 50:
                                Unsafe unsafe = f13005m;
                                Object object = unsafe.getObject(obj, j2);
                                if (object != null) {
                                    ((zbvg) object).c();
                                    unsafe.putObject(obj, j2, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    } else if (v(obj, this.f13006a[i2], i2)) {
                        L(i2).g(f13005m.getObject(obj, j2));
                    }
                }
                if (r(obj, i2)) {
                    L(i2).g(f13005m.getObject(obj, j2));
                }
            }
            this.f13015j.b(obj);
            if (this.f13011f) {
                this.f13016k.a(obj);
            }
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final boolean h(Object obj, Object obj2) {
        boolean e2;
        for (int i2 = 0; i2 < this.f13006a.length; i2 += 3) {
            int I = I(i2);
            long j2 = I & 1048575;
            switch (H(I)) {
                case 0:
                    if (q(obj, obj2, i2) && Double.doubleToLongBits(zbws.f(obj, j2)) == Double.doubleToLongBits(zbws.f(obj2, j2))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (q(obj, obj2, i2) && Float.floatToIntBits(zbws.g(obj, j2)) == Float.floatToIntBits(zbws.g(obj2, j2))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (q(obj, obj2, i2) && zbws.i(obj, j2) == zbws.i(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (q(obj, obj2, i2) && zbws.i(obj, j2) == zbws.i(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (q(obj, obj2, i2) && zbws.i(obj, j2) == zbws.i(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (q(obj, obj2, i2) && zbws.B(obj, j2) == zbws.B(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (q(obj, obj2, i2) && zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (q(obj, obj2, i2) && zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (q(obj, obj2, i2) && zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (q(obj, obj2, i2) && zbws.i(obj, j2) == zbws.i(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (q(obj, obj2, i2) && zbws.h(obj, j2) == zbws.h(obj2, j2)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (q(obj, obj2, i2) && zbws.i(obj, j2) == zbws.i(obj2, j2)) {
                        continue;
                    }
                    return false;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    if (q(obj, obj2, i2) && zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2))) {
                        continue;
                    }
                    return false;
                case MlKitException.UNSUPPORTED /* 18 */:
                case 19:
                case 20:
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    e2 = zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2));
                    break;
                case 50:
                    e2 = zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long F = F(i2) & 1048575;
                    if (zbws.h(obj, F) == zbws.h(obj2, F) && zbvz.e(zbws.k(obj, j2), zbws.k(obj2, j2))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!e2) {
                return false;
            }
        }
        if (!((zbuf) obj).zbc.equals(((zbuf) obj2).zbc)) {
            return false;
        }
        if (this.f13011f) {
            return ((zbub) obj).zbb.equals(((zbub) obj2).zbb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final int i(Object obj) {
        int i2;
        long doubleToLongBits;
        int floatToIntBits;
        int i3;
        int i4 = 0;
        for (int i5 = 0; i5 < this.f13006a.length; i5 += 3) {
            int I = I(i5);
            int[] iArr = this.f13006a;
            int i6 = 1048575 & I;
            int H = H(I);
            int i7 = iArr[i5];
            long j2 = i6;
            int i8 = 37;
            switch (H) {
                case 0:
                    i2 = i4 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zbws.f(obj, j2));
                    byte[] bArr = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case 1:
                    i2 = i4 * 53;
                    floatToIntBits = Float.floatToIntBits(zbws.g(obj, j2));
                    i4 = i2 + floatToIntBits;
                    break;
                case 2:
                    i2 = i4 * 53;
                    doubleToLongBits = zbws.i(obj, j2);
                    byte[] bArr2 = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case 3:
                    i2 = i4 * 53;
                    doubleToLongBits = zbws.i(obj, j2);
                    byte[] bArr3 = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case 4:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 5:
                    i2 = i4 * 53;
                    doubleToLongBits = zbws.i(obj, j2);
                    byte[] bArr4 = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case 6:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 7:
                    i2 = i4 * 53;
                    floatToIntBits = zbuo.a(zbws.B(obj, j2));
                    i4 = i2 + floatToIntBits;
                    break;
                case 8:
                    i2 = i4 * 53;
                    floatToIntBits = ((String) zbws.k(obj, j2)).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 9:
                    i3 = i4 * 53;
                    Object k2 = zbws.k(obj, j2);
                    if (k2 != null) {
                        i8 = k2.hashCode();
                    }
                    i4 = i3 + i8;
                    break;
                case 10:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.k(obj, j2).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 11:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 12:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 13:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 14:
                    i2 = i4 * 53;
                    doubleToLongBits = zbws.i(obj, j2);
                    byte[] bArr5 = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case 15:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.h(obj, j2);
                    i4 = i2 + floatToIntBits;
                    break;
                case 16:
                    i2 = i4 * 53;
                    doubleToLongBits = zbws.i(obj, j2);
                    byte[] bArr6 = zbuo.f12985b;
                    floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                    i4 = i2 + floatToIntBits;
                    break;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    i3 = i4 * 53;
                    Object k3 = zbws.k(obj, j2);
                    if (k3 != null) {
                        i8 = k3.hashCode();
                    }
                    i4 = i3 + i8;
                    break;
                case MlKitException.UNSUPPORTED /* 18 */:
                case 19:
                case 20:
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.k(obj, j2).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 50:
                    i2 = i4 * 53;
                    floatToIntBits = zbws.k(obj, j2).hashCode();
                    i4 = i2 + floatToIntBits;
                    break;
                case 51:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = Double.doubleToLongBits(C(obj, j2));
                        byte[] bArr7 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = Float.floatToIntBits(D(obj, j2));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = J(obj, j2);
                        byte[] bArr8 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = J(obj, j2);
                        byte[] bArr9 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = J(obj, j2);
                        byte[] bArr10 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zbuo.a(w(obj, j2));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = ((String) zbws.k(obj, j2)).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zbws.k(obj, j2).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zbws.k(obj, j2).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = J(obj, j2);
                        byte[] bArr11 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = E(obj, j2);
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        doubleToLongBits = J(obj, j2);
                        byte[] bArr12 = zbuo.f12985b;
                        floatToIntBits = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (v(obj, i7, i5)) {
                        i2 = i4 * 53;
                        floatToIntBits = zbws.k(obj, j2).hashCode();
                        i4 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i4 * 53) + ((zbuf) obj).zbc.hashCode();
        return this.f13011f ? (hashCode * 53) + ((zbub) obj).zbb.f12967a.hashCode() : hashCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:224:0x0bc5, code lost:
    
        throw new com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0f82, code lost:
    
        if (r0 == r3) goto L559;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0f84, code lost:
    
        r32.putInt(r10, r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0f8a, code lost:
    
        r0 = r11.f13013h;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0f90, code lost:
    
        if (r0 >= r11.f13014i) goto L691;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0f92, code lost:
    
        r3 = r11.f13012g;
        r4 = r11.f13015j;
        r5 = r11.f13006a;
        r3 = r3[r0];
        r5 = r5[r3];
        r6 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbws.k(r10, r11.I(r3) & 1048575);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0fa9, code lost:
    
        if (r6 == null) goto L692;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0fab, code lost:
    
        r12 = r11.K(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0faf, code lost:
    
        if (r12 == null) goto L693;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0fb1, code lost:
    
        r3 = ((com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvf) r11.M(r3)).c();
        r6 = ((com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvg) r6).entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0fc9, code lost:
    
        if (r6.hasNext() == false) goto L694;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0fcb, code lost:
    
        r13 = (java.util.Map.Entry) r6.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0fdf, code lost:
    
        if (r12.a(((java.lang.Integer) r13.getValue()).intValue()) != false) goto L697;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0fe1, code lost:
    
        if (r1 != 0) goto L573;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0fe3, code lost:
    
        r1 = r4.a(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0fe7, code lost:
    
        r14 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvf.b(r3, r13.getKey(), r13.getValue());
        r15 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc.zbb;
        r15 = new byte[r14];
        r16 = r4;
        r7 = new com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbth(r15, 0, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0fff, code lost:
    
        com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvf.e(r7, r3, r13.getKey(), r13.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x100a, code lost:
    
        r1.j((r5 << 3) | 2, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsy.a(r7, r15));
        r6.remove();
        r4 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x1021, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x1027, code lost:
    
        throw new java.lang.RuntimeException(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x1028, code lost:
    
        r0 = r0 + 1;
        r1 = (com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm) r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x102f, code lost:
    
        if (r1 == 0) goto L582;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x1031, code lost:
    
        ((com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf) r10).zbc = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x1036, code lost:
    
        if (r9 != 0) goto L588;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x103a, code lost:
    
        if (r8 != r42) goto L586;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x1044, code lost:
    
        throw new com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq(r33);
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x104d, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x1045, code lost:
    
        r1 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x1049, code lost:
    
        if (r8 > r42) goto L592;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x104b, code lost:
    
        if (r2 != r9) goto L592;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x1053, code lost:
    
        throw new com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq(r1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0a45 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0a5c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0ea3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0ebf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:744:0x005c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0ee0  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v199 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v126, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final int z(java.lang.Object r39, byte[] r40, int r41, int r42, int r43, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsq r44) {
        /*
            Method dump skipped, instructions count: 4324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvp.z(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsq):int");
    }
}
