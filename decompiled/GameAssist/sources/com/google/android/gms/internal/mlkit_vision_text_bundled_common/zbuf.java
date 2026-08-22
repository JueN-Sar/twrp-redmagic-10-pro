package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbtz;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbuf;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public abstract class zbuf<MessageType extends zbuf<MessageType, BuilderType>, BuilderType extends zbtz<MessageType, BuilderType>> extends zbsj<MessageType, BuilderType> {
    private static final Map zbb = new ConcurrentHashMap();
    private int zbd = -1;
    protected zbwm zbc = zbwm.c();

    protected static zbul A() {
        return zbug.f();
    }

    protected static zbum B() {
        return zbva.f();
    }

    protected static zbun C() {
        return zbvv.d();
    }

    static Object D(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static Object j(zbvm zbvmVar, String str, Object[] objArr) {
        return new zbvw(zbvmVar, str, objArr);
    }

    protected static void m(Class cls, zbuf zbufVar) {
        zbufVar.l();
        zbb.put(cls, zbufVar);
    }

    protected static final boolean o(zbuf zbufVar, boolean z) {
        byte byteValue = ((Byte) zbufVar.q(1, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean e2 = zbvu.a().b(zbufVar.getClass()).e(zbufVar);
        if (z) {
            zbufVar.q(2, true != e2 ? null : zbufVar, null);
        }
        return e2;
    }

    private final int r(zbvx zbvxVar) {
        return zbvu.a().b(getClass()).a(this);
    }

    private static zbuf s(zbuf zbufVar, byte[] bArr, int i2, int i3, zbtp zbtpVar) {
        if (i3 == 0) {
            return zbufVar;
        }
        zbuf x = zbufVar.x();
        try {
            zbvx b2 = zbvu.a().b(x.getClass());
            b2.f(x, bArr, 0, i3, new zbsq(zbtpVar));
            b2.g(x);
            return x;
        } catch (zbuq e2) {
            throw e2;
        } catch (zbwk e3) {
            throw e3.a();
        } catch (IOException e4) {
            if (e4.getCause() instanceof zbuq) {
                throw ((zbuq) e4.getCause());
            }
            throw new zbuq(e4);
        } catch (IndexOutOfBoundsException unused) {
            throw new zbuq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    public static zbud v(zbvm zbvmVar, Object obj, zbvm zbvmVar2, zbui zbuiVar, int i2, zbww zbwwVar, Class cls) {
        return new zbud(zbvmVar, obj, zbvmVar2, new zbuc(null, 32149011, zbwwVar, false, false), cls);
    }

    static zbuf w(Class cls) {
        Map map = zbb;
        zbuf zbufVar = (zbuf) map.get(cls);
        if (zbufVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zbufVar = (zbuf) map.get(cls);
            } catch (ClassNotFoundException e2) {
                throw new IllegalStateException("Class initialization cannot fail.", e2);
            }
        }
        if (zbufVar == null) {
            zbufVar = (zbuf) ((zbuf) zbws.j(cls)).q(6, null, null);
            if (zbufVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zbufVar);
        }
        return zbufVar;
    }

    protected static zbuf y(zbuf zbufVar, byte[] bArr, zbtp zbtpVar) {
        zbuf s2 = s(zbufVar, bArr, 0, bArr.length, zbtpVar);
        if (s2 == null || o(s2, true)) {
            return s2;
        }
        throw new zbwk(s2).a();
    }

    protected static zbuk z() {
        return zbtw.f();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
    public final int a() {
        if (p()) {
            int r2 = r(null);
            if (r2 >= 0) {
                return r2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + r2);
        }
        int i2 = this.zbd & Api.BaseClientBuilder.API_PRIORITY_OTHER;
        if (i2 == Integer.MAX_VALUE) {
            i2 = r(null);
            if (i2 < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i2);
            }
            this.zbd = (this.zbd & Integer.MIN_VALUE) | i2;
        }
        return i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn
    public final boolean b() {
        return o(this, true);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
    public final /* synthetic */ zbvl c() {
        return (zbtz) q(5, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
    public final /* synthetic */ zbvl e() {
        zbtz zbtzVar = (zbtz) q(5, null, null);
        zbtzVar.k(this);
        return zbtzVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zbvu.a().b(getClass()).h(this, (zbuf) obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvn
    public final /* synthetic */ zbvm f() {
        return (zbuf) q(6, null, null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbvm
    public final void g(zbtk zbtkVar) {
        zbvu.a().b(getClass()).d(this, zbtl.M(zbtkVar));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbsj
    final int h(zbvx zbvxVar) {
        if (p()) {
            int a2 = zbvxVar.a(this);
            if (a2 >= 0) {
                return a2;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + a2);
        }
        int i2 = this.zbd & Api.BaseClientBuilder.API_PRIORITY_OTHER;
        if (i2 != Integer.MAX_VALUE) {
            return i2;
        }
        int a3 = zbvxVar.a(this);
        if (a3 >= 0) {
            this.zbd = (this.zbd & Integer.MIN_VALUE) | a3;
            return a3;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + a3);
    }

    public final int hashCode() {
        if (p()) {
            return t();
        }
        int i2 = this.zba;
        if (i2 != 0) {
            return i2;
        }
        int t = t();
        this.zba = t;
        return t;
    }

    protected final void k() {
        zbvu.a().b(getClass()).g(this);
        l();
    }

    final void l() {
        this.zbd &= Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    final void n(int i2) {
        this.zbd = (this.zbd & Integer.MIN_VALUE) | Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    final boolean p() {
        return (this.zbd & Integer.MIN_VALUE) != 0;
    }

    protected abstract Object q(int i2, Object obj, Object obj2);

    final int t() {
        return zbvu.a().b(getClass()).i(this);
    }

    public final String toString() {
        return zbvo.a(this, super.toString());
    }

    protected final zbtz u() {
        return (zbtz) q(5, null, null);
    }

    final zbuf x() {
        return (zbuf) q(4, null, null);
    }
}
