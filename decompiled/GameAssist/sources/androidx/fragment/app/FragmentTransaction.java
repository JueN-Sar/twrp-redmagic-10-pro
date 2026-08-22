package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class FragmentTransaction {

    /* renamed from: a, reason: collision with root package name */
    private final FragmentFactory f4150a;

    /* renamed from: b, reason: collision with root package name */
    private final ClassLoader f4151b;

    /* renamed from: c, reason: collision with root package name */
    ArrayList f4152c;

    /* renamed from: d, reason: collision with root package name */
    int f4153d;

    /* renamed from: e, reason: collision with root package name */
    int f4154e;

    /* renamed from: f, reason: collision with root package name */
    int f4155f;

    /* renamed from: g, reason: collision with root package name */
    int f4156g;

    /* renamed from: h, reason: collision with root package name */
    int f4157h;

    /* renamed from: i, reason: collision with root package name */
    boolean f4158i;

    /* renamed from: j, reason: collision with root package name */
    boolean f4159j;

    /* renamed from: k, reason: collision with root package name */
    String f4160k;

    /* renamed from: l, reason: collision with root package name */
    int f4161l;

    /* renamed from: m, reason: collision with root package name */
    CharSequence f4162m;

    /* renamed from: n, reason: collision with root package name */
    int f4163n;

    /* renamed from: o, reason: collision with root package name */
    CharSequence f4164o;

    /* renamed from: p, reason: collision with root package name */
    ArrayList f4165p;

    /* renamed from: q, reason: collision with root package name */
    ArrayList f4166q;

    /* renamed from: r, reason: collision with root package name */
    boolean f4167r;

    /* renamed from: s, reason: collision with root package name */
    ArrayList f4168s;

    static final class Op {

        /* renamed from: a, reason: collision with root package name */
        int f4169a;

        /* renamed from: b, reason: collision with root package name */
        Fragment f4170b;

        /* renamed from: c, reason: collision with root package name */
        boolean f4171c;

        /* renamed from: d, reason: collision with root package name */
        int f4172d;

        /* renamed from: e, reason: collision with root package name */
        int f4173e;

        /* renamed from: f, reason: collision with root package name */
        int f4174f;

        /* renamed from: g, reason: collision with root package name */
        int f4175g;

        /* renamed from: h, reason: collision with root package name */
        Lifecycle.State f4176h;

        /* renamed from: i, reason: collision with root package name */
        Lifecycle.State f4177i;

        Op() {
        }

        Op(int i2, Fragment fragment) {
            this.f4169a = i2;
            this.f4170b = fragment;
            this.f4171c = false;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f4176h = state;
            this.f4177i = state;
        }

        Op(int i2, Fragment fragment, boolean z) {
            this.f4169a = i2;
            this.f4170b = fragment;
            this.f4171c = z;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.f4176h = state;
            this.f4177i = state;
        }

        Op(int i2, Fragment fragment, Lifecycle.State state) {
            this.f4169a = i2;
            this.f4170b = fragment;
            this.f4171c = false;
            this.f4176h = fragment.X;
            this.f4177i = state;
        }

        Op(Op op) {
            this.f4169a = op.f4169a;
            this.f4170b = op.f4170b;
            this.f4171c = op.f4171c;
            this.f4172d = op.f4172d;
            this.f4173e = op.f4173e;
            this.f4174f = op.f4174f;
            this.f4175g = op.f4175g;
            this.f4176h = op.f4176h;
            this.f4177i = op.f4177i;
        }
    }

    FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader) {
        this.f4152c = new ArrayList();
        this.f4159j = true;
        this.f4167r = false;
        this.f4150a = fragmentFactory;
        this.f4151b = classLoader;
    }

    public FragmentTransaction b(int i2, Fragment fragment) {
        n(i2, fragment, null, 1);
        return this;
    }

    public FragmentTransaction c(int i2, Fragment fragment, String str) {
        n(i2, fragment, str, 1);
        return this;
    }

    FragmentTransaction d(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.N = viewGroup;
        return c(viewGroup.getId(), fragment, str);
    }

    public FragmentTransaction e(Fragment fragment, String str) {
        n(0, fragment, str, 1);
        return this;
    }

    void f(Op op) {
        this.f4152c.add(op);
        op.f4172d = this.f4153d;
        op.f4173e = this.f4154e;
        op.f4174f = this.f4155f;
        op.f4175g = this.f4156g;
    }

    public FragmentTransaction g(Fragment fragment) {
        f(new Op(7, fragment));
        return this;
    }

    public abstract int h();

    public abstract int i();

    public abstract void j();

    public abstract void k();

    public FragmentTransaction l(Fragment fragment) {
        f(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction m() {
        if (this.f4158i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.f4159j = false;
        return this;
    }

    void n(int i2, Fragment fragment, String str, int i3) {
        String str2 = fragment.W;
        if (str2 != null) {
            FragmentStrictMode.h(fragment, str2);
        }
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = fragment.F;
            if (str3 != null && !str.equals(str3)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.F + " now " + str);
            }
            fragment.F = str;
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.D;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.D + " now " + i2);
            }
            fragment.D = i2;
            fragment.E = i2;
        }
        f(new Op(i3, fragment));
    }

    public boolean o() {
        return this.f4152c.isEmpty();
    }

    public FragmentTransaction p(Fragment fragment) {
        f(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction q(int i2, Fragment fragment) {
        return r(i2, fragment, null);
    }

    public FragmentTransaction r(int i2, Fragment fragment, String str) {
        if (i2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        n(i2, fragment, str, 2);
        return this;
    }

    public FragmentTransaction s(Fragment fragment, Lifecycle.State state) {
        f(new Op(10, fragment, state));
        return this;
    }

    public FragmentTransaction t(boolean z) {
        this.f4167r = z;
        return this;
    }

    FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader, FragmentTransaction fragmentTransaction) {
        this(fragmentFactory, classLoader);
        Iterator it = fragmentTransaction.f4152c.iterator();
        while (it.hasNext()) {
            this.f4152c.add(new Op((Op) it.next()));
        }
        this.f4153d = fragmentTransaction.f4153d;
        this.f4154e = fragmentTransaction.f4154e;
        this.f4155f = fragmentTransaction.f4155f;
        this.f4156g = fragmentTransaction.f4156g;
        this.f4157h = fragmentTransaction.f4157h;
        this.f4158i = fragmentTransaction.f4158i;
        this.f4159j = fragmentTransaction.f4159j;
        this.f4160k = fragmentTransaction.f4160k;
        this.f4163n = fragmentTransaction.f4163n;
        this.f4164o = fragmentTransaction.f4164o;
        this.f4161l = fragmentTransaction.f4161l;
        this.f4162m = fragmentTransaction.f4162m;
        if (fragmentTransaction.f4165p != null) {
            ArrayList arrayList = new ArrayList();
            this.f4165p = arrayList;
            arrayList.addAll(fragmentTransaction.f4165p);
        }
        if (fragmentTransaction.f4166q != null) {
            ArrayList arrayList2 = new ArrayList();
            this.f4166q = arrayList2;
            arrayList2.addAll(fragmentTransaction.f4166q);
        }
        this.f4167r = fragmentTransaction.f4167r;
    }
}
