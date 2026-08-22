package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* loaded from: classes.dex */
final class zbws {

    /* renamed from: a, reason: collision with root package name */
    private static final Unsafe f13056a;

    /* renamed from: b, reason: collision with root package name */
    private static final Class f13057b;

    /* renamed from: c, reason: collision with root package name */
    private static final boolean f13058c;

    /* renamed from: d, reason: collision with root package name */
    private static final zbwr f13059d;

    /* renamed from: e, reason: collision with root package name */
    private static final boolean f13060e;

    /* renamed from: f, reason: collision with root package name */
    private static final boolean f13061f;

    /* renamed from: g, reason: collision with root package name */
    static final long f13062g;

    /* renamed from: h, reason: collision with root package name */
    static final boolean f13063h;

    /* JADX WARN: Removed duplicated region for block: B:15:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0069  */
    static {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbws.<clinit>():void");
    }

    static boolean A(Class cls) {
        int i2 = zbsm.f12933a;
        try {
            Class cls2 = f13057b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean B(Object obj, long j2) {
        return f13059d.g(obj, j2);
    }

    static boolean C() {
        return f13061f;
    }

    static boolean D() {
        return f13060e;
    }

    private static int E(Class cls) {
        if (f13061f) {
            return f13059d.f13055a.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int a(Class cls) {
        if (f13061f) {
            return f13059d.f13055a.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field b() {
        int i2 = zbsm.f12933a;
        Field c2 = c(Buffer.class, "effectiveDirectAddress");
        if (c2 != null) {
            return c2;
        }
        Field c3 = c(Buffer.class, "address");
        if (c3 == null || c3.getType() != Long.TYPE) {
            return null;
        }
        return c3;
    }

    private static Field c(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Object obj, long j2, byte b2) {
        zbwr zbwrVar = f13059d;
        long j3 = (-4) & j2;
        int i2 = zbwrVar.f13055a.getInt(obj, j3);
        int i3 = ((~((int) j2)) & 3) << 3;
        zbwrVar.f13055a.putInt(obj, j3, ((255 & b2) << i3) | (i2 & (~(255 << i3))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Object obj, long j2, byte b2) {
        zbwr zbwrVar = f13059d;
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        zbwrVar.f13055a.putInt(obj, j3, ((255 & b2) << i2) | (zbwrVar.f13055a.getInt(obj, j3) & (~(255 << i2))));
    }

    static double f(Object obj, long j2) {
        return f13059d.a(obj, j2);
    }

    static float g(Object obj, long j2) {
        return f13059d.b(obj, j2);
    }

    static int h(Object obj, long j2) {
        return f13059d.f13055a.getInt(obj, j2);
    }

    static long i(Object obj, long j2) {
        return f13059d.f13055a.getLong(obj, j2);
    }

    static Object j(Class cls) {
        try {
            return f13056a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    static Object k(Object obj, long j2) {
        return f13059d.f13055a.getObject(obj, j2);
    }

    static Unsafe l() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zbwo());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void m(Throwable th) {
        Logger.getLogger(zbws.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    static void r(Object obj, long j2, boolean z) {
        f13059d.c(obj, j2, z);
    }

    static void s(byte[] bArr, long j2, byte b2) {
        f13059d.d(bArr, f13062g + j2, b2);
    }

    static void t(Object obj, long j2, double d2) {
        f13059d.e(obj, j2, d2);
    }

    static void u(Object obj, long j2, float f2) {
        f13059d.f(obj, j2, f2);
    }

    static void v(Object obj, long j2, int i2) {
        f13059d.f13055a.putInt(obj, j2, i2);
    }

    static void w(Object obj, long j2, long j3) {
        f13059d.f13055a.putLong(obj, j2, j3);
    }

    static void x(Object obj, long j2, Object obj2) {
        f13059d.f13055a.putObject(obj, j2, obj2);
    }

    static /* bridge */ /* synthetic */ boolean y(Object obj, long j2) {
        return ((byte) ((f13059d.f13055a.getInt(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean z(Object obj, long j2) {
        return ((byte) ((f13059d.f13055a.getInt(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255)) != 0;
    }
}
