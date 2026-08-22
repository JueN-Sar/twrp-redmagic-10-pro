package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo
/* loaded from: classes.dex */
public abstract class VersionedParcel {

    /* renamed from: a, reason: collision with root package name */
    protected final ArrayMap f5724a;

    /* renamed from: b, reason: collision with root package name */
    protected final ArrayMap f5725b;

    /* renamed from: c, reason: collision with root package name */
    protected final ArrayMap f5726c;

    /* renamed from: androidx.versionedparcelable.VersionedParcel$1, reason: invalid class name */
    class AnonymousClass1 extends ObjectInputStream {
        @Override // java.io.ObjectInputStream
        protected Class resolveClass(ObjectStreamClass objectStreamClass) {
            Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
            return cls != null ? cls : super.resolveClass(objectStreamClass);
        }
    }

    public static class ParcelException extends RuntimeException {
        public ParcelException(Throwable th) {
            super(th);
        }
    }

    public VersionedParcel(ArrayMap arrayMap, ArrayMap arrayMap2, ArrayMap arrayMap3) {
        this.f5724a = arrayMap;
        this.f5725b = arrayMap2;
        this.f5726c = arrayMap3;
    }

    private void N(VersionedParcelable versionedParcelable) {
        try {
            I(c(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    private Class c(Class cls) {
        Class cls2 = (Class) this.f5726c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f5726c.put(cls.getName(), cls3);
        return cls3;
    }

    private Method d(String str) {
        Method method = (Method) this.f5724a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
        this.f5724a.put(str, declaredMethod);
        return declaredMethod;
    }

    private Method e(Class cls) {
        Method method = (Method) this.f5725b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class c2 = c(cls);
        System.currentTimeMillis();
        Method declaredMethod = c2.getDeclaredMethod("write", cls, VersionedParcel.class);
        this.f5725b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    protected abstract void A(byte[] bArr);

    public void B(byte[] bArr, int i2) {
        w(i2);
        A(bArr);
    }

    protected abstract void C(CharSequence charSequence);

    public void D(CharSequence charSequence, int i2) {
        w(i2);
        C(charSequence);
    }

    protected abstract void E(int i2);

    public void F(int i2, int i3) {
        w(i3);
        E(i2);
    }

    protected abstract void G(Parcelable parcelable);

    public void H(Parcelable parcelable, int i2) {
        w(i2);
        G(parcelable);
    }

    protected abstract void I(String str);

    public void J(String str, int i2) {
        w(i2);
        I(str);
    }

    protected void K(VersionedParcelable versionedParcelable, VersionedParcel versionedParcel) {
        try {
            e(versionedParcelable.getClass()).invoke(null, versionedParcelable, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (!(e5.getCause() instanceof RuntimeException)) {
                throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
            }
            throw ((RuntimeException) e5.getCause());
        }
    }

    protected void L(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            I(null);
            return;
        }
        N(versionedParcelable);
        VersionedParcel b2 = b();
        K(versionedParcelable, b2);
        b2.a();
    }

    public void M(VersionedParcelable versionedParcelable, int i2) {
        w(i2);
        L(versionedParcelable);
    }

    protected abstract void a();

    protected abstract VersionedParcel b();

    public boolean f() {
        return false;
    }

    protected abstract boolean g();

    public boolean h(boolean z, int i2) {
        return !m(i2) ? z : g();
    }

    protected abstract byte[] i();

    public byte[] j(byte[] bArr, int i2) {
        return !m(i2) ? bArr : i();
    }

    protected abstract CharSequence k();

    public CharSequence l(CharSequence charSequence, int i2) {
        return !m(i2) ? charSequence : k();
    }

    protected abstract boolean m(int i2);

    protected VersionedParcelable n(String str, VersionedParcel versionedParcel) {
        try {
            return (VersionedParcelable) d(str).invoke(null, versionedParcel);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    protected abstract int o();

    public int p(int i2, int i3) {
        return !m(i3) ? i2 : o();
    }

    protected abstract Parcelable q();

    public Parcelable r(Parcelable parcelable, int i2) {
        return !m(i2) ? parcelable : q();
    }

    protected abstract String s();

    public String t(String str, int i2) {
        return !m(i2) ? str : s();
    }

    protected VersionedParcelable u() {
        String s2 = s();
        if (s2 == null) {
            return null;
        }
        return n(s2, b());
    }

    public VersionedParcelable v(VersionedParcelable versionedParcelable, int i2) {
        return !m(i2) ? versionedParcelable : u();
    }

    protected abstract void w(int i2);

    public void x(boolean z, boolean z2) {
    }

    protected abstract void y(boolean z);

    public void z(boolean z, int i2) {
        w(i2);
        y(z);
    }
}
