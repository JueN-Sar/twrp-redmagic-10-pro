package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.mlkit.common.MlKitException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zbtu {

    /* renamed from: d, reason: collision with root package name */
    private static final zbtu f12966d = new zbtu(true);

    /* renamed from: a, reason: collision with root package name */
    final zbwh f12967a = new zbwa();

    /* renamed from: b, reason: collision with root package name */
    private boolean f12968b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f12969c;

    private zbtu() {
    }

    static int a(zbww zbwwVar, int i2, Object obj) {
        int f2;
        int d2;
        int d3 = zbtk.d(i2 << 3);
        if (zbwwVar == zbww.zbj) {
            zbuo.d((zbvm) obj);
            d3 += d3;
        }
        zbwx zbwxVar = zbwx.INT;
        int i3 = 4;
        switch (zbwwVar.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                i3 = 8;
                return d3 + i3;
            case 1:
                ((Float) obj).floatValue();
                return d3 + i3;
            case 2:
                i3 = zbtk.e(((Long) obj).longValue());
                return d3 + i3;
            case 3:
                i3 = zbtk.e(((Long) obj).longValue());
                return d3 + i3;
            case 4:
                i3 = zbtk.e(((Integer) obj).intValue());
                return d3 + i3;
            case 5:
                ((Long) obj).longValue();
                i3 = 8;
                return d3 + i3;
            case 6:
                ((Integer) obj).intValue();
                return d3 + i3;
            case 7:
                ((Boolean) obj).booleanValue();
                i3 = 1;
                return d3 + i3;
            case 8:
                if (!(obj instanceof zbtc)) {
                    i3 = zbtk.c((String) obj);
                    return d3 + i3;
                }
                f2 = ((zbtc) obj).f();
                d2 = zbtk.d(f2);
                i3 = d2 + f2;
                return d3 + i3;
            case 9:
                i3 = ((zbvm) obj).a();
                return d3 + i3;
            case 10:
                if (!(obj instanceof zbuv)) {
                    i3 = zbtk.a((zbvm) obj);
                    return d3 + i3;
                }
                f2 = ((zbuv) obj).a();
                d2 = zbtk.d(f2);
                i3 = d2 + f2;
                return d3 + i3;
            case 11:
                if (obj instanceof zbtc) {
                    f2 = ((zbtc) obj).f();
                    d2 = zbtk.d(f2);
                } else {
                    f2 = ((byte[]) obj).length;
                    d2 = zbtk.d(f2);
                }
                i3 = d2 + f2;
                return d3 + i3;
            case 12:
                i3 = zbtk.d(((Integer) obj).intValue());
                return d3 + i3;
            case 13:
                i3 = obj instanceof zbuh ? zbtk.e(((zbuh) obj).a()) : zbtk.e(((Integer) obj).intValue());
                return d3 + i3;
            case 14:
                ((Integer) obj).intValue();
                return d3 + i3;
            case 15:
                ((Long) obj).longValue();
                i3 = 8;
                return d3 + i3;
            case 16:
                int intValue = ((Integer) obj).intValue();
                i3 = zbtk.d((intValue >> 31) ^ (intValue + intValue));
                return d3 + i3;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                long longValue = ((Long) obj).longValue();
                i3 = zbtk.e((longValue >> 63) ^ (longValue + longValue));
                return d3 + i3;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int b(zbtt zbttVar, Object obj) {
        zbww zbd = zbttVar.zbd();
        zbttVar.a();
        zbttVar.k();
        return a(zbd, 32149011, obj);
    }

    public static zbtu e() {
        return f12966d;
    }

    static void k(zbtk zbtkVar, zbww zbwwVar, int i2, Object obj) {
        if (zbwwVar == zbww.zbj) {
            zbvm zbvmVar = (zbvm) obj;
            zbuo.d(zbvmVar);
            zbtkVar.B(i2, 3);
            zbvmVar.g(zbtkVar);
            zbtkVar.B(i2, 4);
            return;
        }
        zbtkVar.B(i2, zbwwVar.a());
        zbwx zbwxVar = zbwx.INT;
        switch (zbwwVar.ordinal()) {
            case 0:
                zbtkVar.r(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case 1:
                zbtkVar.p(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case 2:
                zbtkVar.F(((Long) obj).longValue());
                break;
            case 3:
                zbtkVar.F(((Long) obj).longValue());
                break;
            case 4:
                zbtkVar.t(((Integer) obj).intValue());
                break;
            case 5:
                zbtkVar.r(((Long) obj).longValue());
                break;
            case 6:
                zbtkVar.p(((Integer) obj).intValue());
                break;
            case 7:
                zbtkVar.j(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof zbtc)) {
                    zbtkVar.A((String) obj);
                    break;
                } else {
                    zbtkVar.n((zbtc) obj);
                    break;
                }
            case 9:
                ((zbvm) obj).g(zbtkVar);
                break;
            case 10:
                zbtkVar.w((zbvm) obj);
                break;
            case 11:
                if (!(obj instanceof zbtc)) {
                    byte[] bArr = (byte[]) obj;
                    zbtkVar.l(bArr, 0, bArr.length);
                    break;
                } else {
                    zbtkVar.n((zbtc) obj);
                    break;
                }
            case 12:
                zbtkVar.D(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof zbuh)) {
                    zbtkVar.t(((Integer) obj).intValue());
                    break;
                } else {
                    zbtkVar.t(((zbuh) obj).a());
                    break;
                }
            case 14:
                zbtkVar.p(((Integer) obj).intValue());
                break;
            case 15:
                zbtkVar.r(((Long) obj).longValue());
                break;
            case 16:
                int intValue = ((Integer) obj).intValue();
                zbtkVar.D((intValue >> 31) ^ (intValue + intValue));
                break;
            case MlKitException.NETWORK_ISSUE /* 17 */:
                long longValue = ((Long) obj).longValue();
                zbtkVar.F((longValue >> 63) ^ (longValue + longValue));
                break;
        }
    }

    private static Object n(Object obj) {
        if (obj instanceof zbvr) {
            return ((zbvr) obj).zbc();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void o(Map.Entry entry) {
        zbtt zbttVar = (zbtt) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zbuv;
        zbttVar.k();
        if (zbttVar.b() != zbwx.MESSAGE) {
            if (z) {
                throw new IllegalStateException("Lazy fields must be message-valued");
            }
            this.f12967a.put(zbttVar, n(value));
            return;
        }
        Object f2 = f(zbttVar);
        if (f2 != null) {
            if (z) {
                throw null;
            }
            this.f12967a.put(zbttVar, f2 instanceof zbvr ? zbttVar.q((zbvr) f2, (zbvr) value) : zbttVar.m(((zbvm) f2).e(), (zbvm) value).d());
        } else {
            this.f12967a.put(zbttVar, n(value));
            if (z) {
                this.f12969c = true;
            }
        }
    }

    private static boolean p(Map.Entry entry) {
        zbtt zbttVar = (zbtt) entry.getKey();
        if (zbttVar.b() != zbwx.MESSAGE) {
            return true;
        }
        zbttVar.k();
        Object value = entry.getValue();
        if (value instanceof zbvn) {
            return ((zbvn) value).b();
        }
        if (value instanceof zbuv) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int q(Map.Entry entry) {
        int i2;
        int d2;
        int d3;
        zbtt zbttVar = (zbtt) entry.getKey();
        Object value = entry.getValue();
        if (zbttVar.b() != zbwx.MESSAGE) {
            return b(zbttVar, value);
        }
        zbttVar.k();
        zbttVar.i();
        if (value instanceof zbuv) {
            ((zbtt) entry.getKey()).a();
            int d4 = zbtk.d(8);
            i2 = d4 + d4;
            d2 = zbtk.d(16) + zbtk.d(32149011);
            int d5 = zbtk.d(24);
            int a2 = ((zbuv) value).a();
            d3 = d5 + zbtk.d(a2) + a2;
        } else {
            ((zbtt) entry.getKey()).a();
            int d6 = zbtk.d(8);
            i2 = d6 + d6;
            d2 = zbtk.d(16) + zbtk.d(32149011);
            d3 = zbtk.d(24) + zbtk.a((zbvm) value);
        }
        return i2 + d2 + d3;
    }

    public final int c() {
        int c2 = this.f12967a.c();
        int i2 = 0;
        for (int i3 = 0; i3 < c2; i3++) {
            i2 += q(this.f12967a.g(i3));
        }
        Iterator it = this.f12967a.d().iterator();
        while (it.hasNext()) {
            i2 += q((Map.Entry) it.next());
        }
        return i2;
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public final zbtu clone() {
        zbtu zbtuVar = new zbtu();
        int c2 = this.f12967a.c();
        for (int i2 = 0; i2 < c2; i2++) {
            Map.Entry g2 = this.f12967a.g(i2);
            zbtuVar.j((zbtt) ((zbwb) g2).c(), g2.getValue());
        }
        for (Map.Entry entry : this.f12967a.d()) {
            zbtuVar.j((zbtt) entry.getKey(), entry.getValue());
        }
        zbtuVar.f12969c = this.f12969c;
        return zbtuVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zbtu) {
            return this.f12967a.equals(((zbtu) obj).f12967a);
        }
        return false;
    }

    public final Object f(zbtt zbttVar) {
        Object obj = this.f12967a.get(zbttVar);
        if (obj instanceof zbuv) {
            throw null;
        }
        return obj;
    }

    public final Iterator g() {
        return this.f12967a.isEmpty() ? Collections.emptyIterator() : this.f12969c ? new zbuu(this.f12967a.entrySet().iterator()) : this.f12967a.entrySet().iterator();
    }

    public final void h() {
        if (this.f12968b) {
            return;
        }
        int c2 = this.f12967a.c();
        for (int i2 = 0; i2 < c2; i2++) {
            Map.Entry g2 = this.f12967a.g(i2);
            if (g2.getValue() instanceof zbuf) {
                ((zbuf) g2.getValue()).k();
            }
        }
        this.f12967a.a();
        this.f12968b = true;
    }

    public final int hashCode() {
        return this.f12967a.hashCode();
    }

    public final void i(zbtu zbtuVar) {
        int c2 = zbtuVar.f12967a.c();
        for (int i2 = 0; i2 < c2; i2++) {
            o(zbtuVar.f12967a.g(i2));
        }
        Iterator it = zbtuVar.f12967a.d().iterator();
        while (it.hasNext()) {
            o((Map.Entry) it.next());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
    
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuh) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0034, code lost:
    
        if ((r4 instanceof byte[]) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        if (r0 == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0022, code lost:
    
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuv) == false) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j(com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtt r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.k()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbww r0 = r3.zbd()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuo.f12985b
            r4.getClass()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbww r1 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbww.zba
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwx r1 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwx.INT
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwx r0 = r0.c()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L46;
                case 1: goto L43;
                case 2: goto L40;
                case 3: goto L3d;
                case 4: goto L3a;
                case 5: goto L37;
                case 6: goto L2e;
                case 7: goto L25;
                case 8: goto L1c;
                default: goto L1b;
            }
        L1b:
            goto L57
        L1c:
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
            if (r0 != 0) goto L4a
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuv
            if (r0 == 0) goto L57
            goto L4a
        L25:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L4a
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuh
            if (r0 == 0) goto L57
            goto L4a
        L2e:
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc
            if (r0 != 0) goto L4a
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L57
            goto L4a
        L37:
            boolean r0 = r4 instanceof java.lang.String
            goto L48
        L3a:
            boolean r0 = r4 instanceof java.lang.Boolean
            goto L48
        L3d:
            boolean r0 = r4 instanceof java.lang.Double
            goto L48
        L40:
            boolean r0 = r4 instanceof java.lang.Float
            goto L48
        L43:
            boolean r0 = r4 instanceof java.lang.Long
            goto L48
        L46:
            boolean r0 = r4 instanceof java.lang.Integer
        L48:
            if (r0 == 0) goto L57
        L4a:
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuv
            if (r0 == 0) goto L51
            r0 = 1
            r2.f12969c = r0
        L51:
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwh r2 = r2.f12967a
            r2.put(r3, r4)
            return
        L57:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r3.a()
            r0 = 32149011(0x1ea8e13, float:8.616189E-38)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbww r3 = r3.zbd()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwx r3 = r3.c()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtu.j(com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtt, java.lang.Object):void");
    }

    public final boolean l() {
        return this.f12968b;
    }

    public final boolean m() {
        int c2 = this.f12967a.c();
        for (int i2 = 0; i2 < c2; i2++) {
            if (!p(this.f12967a.g(i2))) {
                return false;
            }
        }
        Iterator it = this.f12967a.d().iterator();
        while (it.hasNext()) {
            if (!p((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private zbtu(boolean z) {
        h();
        h();
    }
}
