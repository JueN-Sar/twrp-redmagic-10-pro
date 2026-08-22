package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zbvq implements zbvx {

    /* renamed from: a, reason: collision with root package name */
    private final zbvm f13017a;

    /* renamed from: b, reason: collision with root package name */
    private final zbwl f13018b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f13019c;

    /* renamed from: d, reason: collision with root package name */
    private final zbtq f13020d;

    private zbvq(zbwl zbwlVar, zbtq zbtqVar, zbvm zbvmVar) {
        this.f13018b = zbwlVar;
        this.f13019c = zbvmVar instanceof zbub;
        this.f13020d = zbtqVar;
        this.f13017a = zbvmVar;
    }

    static zbvq j(zbwl zbwlVar, zbtq zbtqVar, zbvm zbvmVar) {
        return new zbvq(zbwlVar, zbtqVar, zbvmVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final int a(Object obj) {
        int b2 = ((zbuf) obj).zbc.b();
        return this.f13019c ? b2 + ((zbub) obj).zbb.c() : b2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final Object b() {
        zbvm zbvmVar = this.f13017a;
        return zbvmVar instanceof zbuf ? ((zbuf) zbvmVar).x() : zbvmVar.c().m();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void c(Object obj, Object obj2) {
        zbvz.u(this.f13018b, obj, obj2);
        if (this.f13019c) {
            zbvz.t(this.f13020d, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void d(Object obj, zbwy zbwyVar) {
        Iterator g2 = ((zbub) obj).zbb.g();
        while (g2.hasNext()) {
            Map.Entry entry = (Map.Entry) g2.next();
            zbtt zbttVar = (zbtt) entry.getKey();
            if (zbttVar.b() != zbwx.MESSAGE) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            zbttVar.k();
            zbttVar.i();
            if (entry instanceof zbut) {
                zbttVar.a();
                zbwyVar.p(32149011, ((zbut) entry).a().b());
            } else {
                zbttVar.a();
                zbwyVar.p(32149011, entry.getValue());
            }
        }
        ((zbuf) obj).zbc.k(zbwyVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final boolean e(Object obj) {
        return ((zbub) obj).zbb.m();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b5 A[EDGE_INSN: B:24:0x00b5->B:25:0x00b5 BREAK  A[LOOP:1: B:10:0x0062->B:18:0x0062], SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsq r15) {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf r0 = (com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf) r0
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm r1 = r0.zbc
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm r2 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm.c()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm r1 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbwm.f()
            r0.zbc = r1
        L11:
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbub r11 = (com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbub) r11
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtu r11 = r11.E()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lc0
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.k(r12, r13, r15)
            int r13 = r15.f12935a
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L60
            r3 = r13 & 7
            if (r3 != r5) goto L5b
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp r2 = r15.f12938d
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm r3 = r10.f13017a
            int r5 = r13 >>> 3
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbud r8 = r2.c(r3, r5)
            if (r8 == 0) goto L51
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm r13 = r8.f12979a
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvu r2 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvu.a()
            java.lang.Class r13 = r13.getClass()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx r13 = r2.b(r13)
            int r13 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.e(r13, r12, r4, r14, r15)
            java.lang.Object r2 = r15.f12937c
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuc r3 = r8.f12980b
            r11.j(r3, r2)
        L4f:
            r2 = r8
            goto L19
        L51:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.j(r2, r3, r4, r5, r6, r7)
            goto L4f
        L5b:
            int r13 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.q(r13, r12, r4, r14, r15)
            goto L19
        L60:
            r13 = 0
            r3 = r0
        L62:
            if (r4 >= r14) goto Lb5
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.k(r12, r4, r15)
            int r6 = r15.f12935a
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto L9b
            r9 = 3
            if (r7 == r9) goto L74
            goto Lac
        L74:
            if (r2 == 0) goto L90
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm r6 = r2.f12979a
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvu r7 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvu.a()
            java.lang.Class r6 = r6.getClass()
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx r6 = r7.b(r6)
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.e(r6, r12, r4, r14, r15)
            java.lang.Object r6 = r15.f12937c
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuc r7 = r2.f12980b
            r11.j(r7, r6)
            goto L62
        L90:
            if (r8 != r5) goto Lac
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.a(r12, r4, r15)
            java.lang.Object r3 = r15.f12937c
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc r3 = (com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtc) r3
            goto L62
        L9b:
            if (r8 != 0) goto Lac
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.k(r12, r4, r15)
            int r13 = r15.f12935a
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtp r2 = r15.f12938d
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm r6 = r10.f13017a
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbud r2 = r2.c(r6, r13)
            goto L62
        Lac:
            r7 = 12
            if (r6 == r7) goto Lb5
            int r4 = com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsr.q(r6, r12, r4, r14, r15)
            goto L62
        Lb5:
            if (r3 == 0) goto Lbd
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.j(r13, r3)
        Lbd:
            r13 = r4
            goto L19
        Lc0:
            if (r13 != r14) goto Lc3
            return
        Lc3:
            com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq r10 = new com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuq
            java.lang.String r11 = "Failed to parse the message."
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvq.f(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsq):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final void g(Object obj) {
        this.f13018b.b(obj);
        this.f13020d.a(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final boolean h(Object obj, Object obj2) {
        if (!((zbuf) obj).zbc.equals(((zbuf) obj2).zbc)) {
            return false;
        }
        if (this.f13019c) {
            return ((zbub) obj).zbb.equals(((zbub) obj2).zbb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvx
    public final int i(Object obj) {
        int hashCode = ((zbuf) obj).zbc.hashCode();
        return this.f13019c ? (hashCode * 53) + ((zbub) obj).zbb.f12967a.hashCode() : hashCode;
    }
}
