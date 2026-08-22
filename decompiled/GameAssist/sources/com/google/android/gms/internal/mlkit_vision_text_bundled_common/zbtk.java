package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class zbtk extends zbst {

    /* renamed from: b, reason: collision with root package name */
    private static final Logger f12954b = Logger.getLogger(zbtk.class.getName());

    /* renamed from: c, reason: collision with root package name */
    private static final boolean f12955c = zbws.C();

    /* renamed from: a, reason: collision with root package name */
    zbtl f12956a;

    /* synthetic */ zbtk(zbtj zbtjVar) {
    }

    static int G(int i2, zbvm zbvmVar, zbvx zbvxVar) {
        int d2 = d(i2 << 3);
        return d2 + d2 + ((zbsj) zbvmVar).h(zbvxVar);
    }

    public static int a(zbvm zbvmVar) {
        int a2 = zbvmVar.a();
        return d(a2) + a2;
    }

    static int b(zbvm zbvmVar, zbvx zbvxVar) {
        int h2 = ((zbsj) zbvmVar).h(zbvxVar);
        return d(h2) + h2;
    }

    public static int c(String str) {
        int length;
        try {
            length = zbwv.c(str);
        } catch (zbwu unused) {
            length = str.getBytes(zbuo.f12984a).length;
        }
        return d(length) + length;
    }

    public static int d(int i2) {
        return (352 - (Integer.numberOfLeadingZeros(i2) * 9)) >>> 6;
    }

    public static int e(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    public abstract void A(String str);

    public abstract void B(int i2, int i3);

    public abstract void C(int i2, int i3);

    public abstract void D(int i2);

    public abstract void E(int i2, long j2);

    public abstract void F(long j2);

    public final void f() {
        if (i() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void g(String str, zbwu zbwuVar) {
        f12954b.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zbwuVar);
        byte[] bytes = str.getBytes(zbuo.f12984a);
        try {
            int length = bytes.length;
            D(length);
            u(bytes, 0, length);
        } catch (IndexOutOfBoundsException e2) {
            throw new zbti(e2);
        }
    }

    public abstract int i();

    public abstract void j(byte b2);

    public abstract void k(int i2, boolean z);

    abstract void l(byte[] bArr, int i2, int i3);

    public abstract void m(int i2, zbtc zbtcVar);

    public abstract void n(zbtc zbtcVar);

    public abstract void o(int i2, int i3);

    public abstract void p(int i2);

    public abstract void q(int i2, long j2);

    public abstract void r(long j2);

    public abstract void s(int i2, int i3);

    public abstract void t(int i2);

    public abstract void u(byte[] bArr, int i2, int i3);

    abstract void v(int i2, zbvm zbvmVar, zbvx zbvxVar);

    public abstract void w(zbvm zbvmVar);

    public abstract void x(int i2, zbvm zbvmVar);

    public abstract void y(int i2, zbtc zbtcVar);

    public abstract void z(int i2, String str);
}
