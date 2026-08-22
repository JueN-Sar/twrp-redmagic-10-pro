package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class BackStackRecord extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManager.OpGenerator {
    final FragmentManager t;
    boolean u;
    int v;
    boolean w;

    BackStackRecord(FragmentManager fragmentManager) {
        super(fragmentManager.x0(), fragmentManager.A0() != null ? fragmentManager.A0().r().getClassLoader() : null);
        this.v = -1;
        this.w = false;
        this.t = fragmentManager;
    }

    void A() {
        for (int size = this.f4152c.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(size);
            Fragment fragment = op.f4170b;
            if (fragment != null) {
                fragment.t = this.w;
                fragment.P1(true);
                fragment.O1(FragmentManager.s1(this.f4157h));
                fragment.S1(this.f4166q, this.f4165p);
            }
            switch (op.f4169a) {
                case 1:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, true);
                    this.t.m1(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.f4169a);
                case 3:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.j(fragment);
                    break;
                case 4:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.B1(fragment);
                    break;
                case 5:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, true);
                    this.t.K0(fragment);
                    break;
                case 6:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.o(fragment);
                    break;
                case 7:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, true);
                    this.t.z(fragment);
                    break;
                case 8:
                    this.t.z1(null);
                    break;
                case 9:
                    this.t.z1(fragment);
                    break;
                case 10:
                    this.t.y1(fragment, op.f4176h);
                    break;
            }
        }
    }

    Fragment B(ArrayList arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i2 = 0;
        while (i2 < this.f4152c.size()) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(i2);
            int i3 = op.f4169a;
            if (i3 != 1) {
                if (i3 == 2) {
                    Fragment fragment3 = op.f4170b;
                    int i4 = fragment3.E;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment4 = (Fragment) arrayList.get(size);
                        if (fragment4.E == i4) {
                            if (fragment4 == fragment3) {
                                z = true;
                            } else {
                                if (fragment4 == fragment2) {
                                    this.f4152c.add(i2, new FragmentTransaction.Op(9, fragment4, true));
                                    i2++;
                                    fragment2 = null;
                                }
                                FragmentTransaction.Op op2 = new FragmentTransaction.Op(3, fragment4, true);
                                op2.f4172d = op.f4172d;
                                op2.f4174f = op.f4174f;
                                op2.f4173e = op.f4173e;
                                op2.f4175g = op.f4175g;
                                this.f4152c.add(i2, op2);
                                arrayList.remove(fragment4);
                                i2++;
                            }
                        }
                    }
                    if (z) {
                        this.f4152c.remove(i2);
                        i2--;
                    } else {
                        op.f4169a = 1;
                        op.f4171c = true;
                        arrayList.add(fragment3);
                    }
                } else if (i3 == 3 || i3 == 6) {
                    arrayList.remove(op.f4170b);
                    Fragment fragment5 = op.f4170b;
                    if (fragment5 == fragment2) {
                        this.f4152c.add(i2, new FragmentTransaction.Op(9, fragment5));
                        i2++;
                        fragment2 = null;
                    }
                } else if (i3 != 7) {
                    if (i3 == 8) {
                        this.f4152c.add(i2, new FragmentTransaction.Op(9, fragment2, true));
                        op.f4171c = true;
                        i2++;
                        fragment2 = op.f4170b;
                    }
                }
                i2++;
            }
            arrayList.add(op.f4170b);
            i2++;
        }
        return fragment2;
    }

    public String C() {
        return this.f4160k;
    }

    public void D() {
        if (this.f4168s != null) {
            for (int i2 = 0; i2 < this.f4168s.size(); i2++) {
                ((Runnable) this.f4168s.get(i2)).run();
            }
            this.f4168s = null;
        }
    }

    Fragment E(ArrayList arrayList, Fragment fragment) {
        for (int size = this.f4152c.size() - 1; size >= 0; size--) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(size);
            int i2 = op.f4169a;
            if (i2 != 1) {
                if (i2 != 3) {
                    switch (i2) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = op.f4170b;
                            break;
                        case 10:
                            op.f4177i = op.f4176h;
                            break;
                    }
                }
                arrayList.add(op.f4170b);
            }
            arrayList.remove(op.f4170b);
        }
        return fragment;
    }

    @Override // androidx.fragment.app.FragmentManager.OpGenerator
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f4158i) {
            return true;
        }
        this.t.i(this);
        return true;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int h() {
        return w(false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int i() {
        return w(true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void j() {
        m();
        this.t.e0(this, false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void k() {
        m();
        this.t.e0(this, true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction l(Fragment fragment) {
        FragmentManager fragmentManager = fragment.z;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.l(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    void n(int i2, Fragment fragment, String str, int i3) {
        super.n(i2, fragment, str, i3);
        fragment.z = this.t;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public boolean o() {
        return this.f4152c.isEmpty();
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction p(Fragment fragment) {
        FragmentManager fragmentManager = fragment.z;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.p(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction s(Fragment fragment, Lifecycle.State state) {
        if (fragment.z != this.t) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.t);
        }
        if (state == Lifecycle.State.INITIALIZED && fragment.f3974c > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + " after the Fragment has been created");
        }
        if (state != Lifecycle.State.DESTROYED) {
            return super.s(fragment, state);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.v >= 0) {
            sb.append(" #");
            sb.append(this.v);
        }
        if (this.f4160k != null) {
            sb.append(" ");
            sb.append(this.f4160k);
        }
        sb.append("}");
        return sb.toString();
    }

    void u(int i2) {
        if (this.f4158i) {
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            int size = this.f4152c.size();
            for (int i3 = 0; i3 < size; i3++) {
                FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(i3);
                Fragment fragment = op.f4170b;
                if (fragment != null) {
                    fragment.y += i2;
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + op.f4170b + " to " + op.f4170b.y);
                    }
                }
            }
        }
    }

    void v() {
        int size = this.f4152c.size() - 1;
        while (size >= 0) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(size);
            if (op.f4171c) {
                if (op.f4169a == 8) {
                    op.f4171c = false;
                    this.f4152c.remove(size - 1);
                    size--;
                } else {
                    int i2 = op.f4170b.E;
                    op.f4169a = 2;
                    op.f4171c = false;
                    for (int i3 = size - 1; i3 >= 0; i3--) {
                        FragmentTransaction.Op op2 = (FragmentTransaction.Op) this.f4152c.get(i3);
                        if (op2.f4171c && op2.f4170b.E == i2) {
                            this.f4152c.remove(i3);
                            size--;
                        }
                    }
                }
            }
            size--;
        }
    }

    int w(boolean z) {
        if (this.u) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
            x("  ", printWriter);
            printWriter.close();
        }
        this.u = true;
        if (this.f4158i) {
            this.v = this.t.m();
        } else {
            this.v = -1;
        }
        this.t.b0(this, z);
        return this.v;
    }

    public void x(String str, PrintWriter printWriter) {
        y(str, printWriter, true);
    }

    public void y(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f4160k);
            printWriter.print(" mIndex=");
            printWriter.print(this.v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.u);
            if (this.f4157h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f4157h));
            }
            if (this.f4153d != 0 || this.f4154e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f4153d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f4154e));
            }
            if (this.f4155f != 0 || this.f4156g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f4155f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f4156g));
            }
            if (this.f4161l != 0 || this.f4162m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f4161l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f4162m);
            }
            if (this.f4163n != 0 || this.f4164o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f4163n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f4164o);
            }
        }
        if (this.f4152c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f4152c.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(i2);
            switch (op.f4169a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + op.f4169a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(op.f4170b);
            if (z) {
                if (op.f4172d != 0 || op.f4173e != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(op.f4172d));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(op.f4173e));
                }
                if (op.f4174f != 0 || op.f4175g != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(op.f4174f));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(op.f4175g));
                }
            }
        }
    }

    void z() {
        int size = this.f4152c.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.Op op = (FragmentTransaction.Op) this.f4152c.get(i2);
            Fragment fragment = op.f4170b;
            if (fragment != null) {
                fragment.t = this.w;
                fragment.P1(false);
                fragment.O1(this.f4157h);
                fragment.S1(this.f4165p, this.f4166q);
            }
            switch (op.f4169a) {
                case 1:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, false);
                    this.t.j(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.f4169a);
                case 3:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.m1(fragment);
                    break;
                case 4:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.K0(fragment);
                    break;
                case 5:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, false);
                    this.t.B1(fragment);
                    break;
                case 6:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.z(fragment);
                    break;
                case 7:
                    fragment.I1(op.f4172d, op.f4173e, op.f4174f, op.f4175g);
                    this.t.x1(fragment, false);
                    this.t.o(fragment);
                    break;
                case 8:
                    this.t.z1(fragment);
                    break;
                case 9:
                    this.t.z1(null);
                    break;
                case 10:
                    this.t.y1(fragment, op.f4177i);
                    break;
            }
        }
    }

    BackStackRecord(BackStackRecord backStackRecord) {
        super(backStackRecord.t.x0(), backStackRecord.t.A0() != null ? backStackRecord.t.A0().r().getClassLoader() : null, backStackRecord);
        this.v = -1;
        this.w = false;
        this.t = backStackRecord.t;
        this.u = backStackRecord.u;
        this.v = backStackRecord.v;
        this.w = backStackRecord.w;
    }
}
