package androidx.fragment.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.ComponentDialog;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

/* loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private Handler i0;
    private boolean r0;
    private Dialog t0;
    private boolean u0;
    private boolean v0;
    private boolean w0;
    private Runnable j0 = new Runnable() { // from class: androidx.fragment.app.DialogFragment.1
        @Override // java.lang.Runnable
        public void run() {
            DialogFragment.this.l0.onDismiss(DialogFragment.this.t0);
        }
    };
    private DialogInterface.OnCancelListener k0 = new DialogInterface.OnCancelListener() { // from class: androidx.fragment.app.DialogFragment.2
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            if (DialogFragment.this.t0 != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onCancel(dialogFragment.t0);
            }
        }
    };
    private DialogInterface.OnDismissListener l0 = new DialogInterface.OnDismissListener() { // from class: androidx.fragment.app.DialogFragment.3
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (DialogFragment.this.t0 != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onDismiss(dialogFragment.t0);
            }
        }
    };
    private int m0 = 0;
    private int n0 = 0;
    private boolean o0 = true;
    private boolean p0 = true;
    private int q0 = -1;
    private Observer s0 = new Observer<LifecycleOwner>() { // from class: androidx.fragment.app.DialogFragment.4
        @Override // androidx.lifecycle.Observer
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(LifecycleOwner lifecycleOwner) {
            if (lifecycleOwner == null || !DialogFragment.this.p0) {
                return;
            }
            View E1 = DialogFragment.this.E1();
            if (E1.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (DialogFragment.this.t0 != null) {
                if (FragmentManager.N0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + DialogFragment.this.t0);
                }
                DialogFragment.this.t0.setContentView(E1);
            }
        }
    };
    private boolean x0 = false;

    private void f2(boolean z, boolean z2, boolean z3) {
        if (this.v0) {
            return;
        }
        this.v0 = true;
        this.w0 = false;
        Dialog dialog = this.t0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.t0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.i0.getLooper()) {
                    onDismiss(this.t0);
                } else {
                    this.i0.post(this.j0);
                }
            }
        }
        this.u0 = true;
        if (this.q0 >= 0) {
            if (z3) {
                O().h1(this.q0, 1);
            } else {
                O().f1(this.q0, 1, z);
            }
            this.q0 = -1;
            return;
        }
        FragmentTransaction p2 = O().p();
        p2.t(true);
        p2.p(this);
        if (z3) {
            p2.j();
        } else if (z) {
            p2.i();
        } else {
            p2.h();
        }
    }

    private void m2(Bundle bundle) {
        if (this.p0 && !this.x0) {
            try {
                this.r0 = true;
                Dialog j2 = j2(bundle);
                this.t0 = j2;
                if (this.p0) {
                    p2(j2, this.m0);
                    Context z = z();
                    if (z instanceof Activity) {
                        this.t0.setOwnerActivity((Activity) z);
                    }
                    this.t0.setCancelable(this.o0);
                    this.t0.setOnCancelListener(this.k0);
                    this.t0.setOnDismissListener(this.l0);
                    this.x0 = true;
                } else {
                    this.t0 = null;
                }
                this.r0 = false;
            } catch (Throwable th) {
                this.r0 = false;
                throw th;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void B0(Context context) {
        super.B0(context);
        i0().j(this.s0);
        if (this.w0) {
            return;
        }
        this.v0 = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        Dialog dialog = this.t0;
        if (dialog != null) {
            this.u0 = true;
            dialog.setOnDismissListener(null);
            this.t0.dismiss();
            if (!this.v0) {
                onDismiss(this.t0);
            }
            this.t0 = null;
            this.x0 = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void L0() {
        super.L0();
        if (!this.w0 && !this.v0) {
            this.v0 = true;
        }
        i0().n(this.s0);
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater M0(Bundle bundle) {
        LayoutInflater M0 = super.M0(bundle);
        if (this.p0 && !this.r0) {
            m2(bundle);
            if (FragmentManager.N0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.t0;
            return dialog != null ? M0.cloneInContext(dialog.getContext()) : M0;
        }
        if (FragmentManager.N0(2)) {
            String str = "getting layout inflater for DialogFragment " + this;
            if (this.p0) {
                Log.d("FragmentManager", "mCreatingDialog = true: " + str);
            } else {
                Log.d("FragmentManager", "mShowsDialog = false: " + str);
            }
        }
        return M0;
    }

    @Override // androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        Dialog dialog = this.t0;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", onSaveInstanceState);
        }
        int i2 = this.m0;
        if (i2 != 0) {
            bundle.putInt("android:style", i2);
        }
        int i3 = this.n0;
        if (i3 != 0) {
            bundle.putInt("android:theme", i3);
        }
        boolean z = this.o0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.p0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i4 = this.q0;
        if (i4 != -1) {
            bundle.putInt("android:backStackId", i4);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void Y0() {
        super.Y0();
        Dialog dialog = this.t0;
        if (dialog != null) {
            this.u0 = false;
            dialog.show();
            View decorView = this.t0.getWindow().getDecorView();
            ViewTreeLifecycleOwner.a(decorView, this);
            ViewTreeViewModelStoreOwner.a(decorView, this);
            ViewTreeSavedStateRegistryOwner.a(decorView, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void Z0() {
        super.Z0();
        Dialog dialog = this.t0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void b1(Bundle bundle) {
        Bundle bundle2;
        super.b1(bundle);
        if (this.t0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.t0.onRestoreInstanceState(bundle2);
    }

    public void d2() {
        f2(false, false, false);
    }

    public void e2() {
        f2(true, false, false);
    }

    public Dialog g2() {
        return this.t0;
    }

    public int h2() {
        return this.n0;
    }

    @Override // androidx.fragment.app.Fragment
    void i1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle bundle2;
        super.i1(layoutInflater, viewGroup, bundle);
        if (this.O != null || this.t0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.t0.onRestoreInstanceState(bundle2);
    }

    public boolean i2() {
        return this.o0;
    }

    public Dialog j2(Bundle bundle) {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new ComponentDialog(D1(), h2());
    }

    View k2(int i2) {
        Dialog dialog = this.t0;
        if (dialog != null) {
            return dialog.findViewById(i2);
        }
        return null;
    }

    boolean l2() {
        return this.x0;
    }

    @Override // androidx.fragment.app.Fragment
    FragmentContainer m() {
        final FragmentContainer m2 = super.m();
        return new FragmentContainer() { // from class: androidx.fragment.app.DialogFragment.5
            @Override // androidx.fragment.app.FragmentContainer
            public View m(int i2) {
                return m2.p() ? m2.m(i2) : DialogFragment.this.k2(i2);
            }

            @Override // androidx.fragment.app.FragmentContainer
            public boolean p() {
                return m2.p() || DialogFragment.this.l2();
            }
        };
    }

    public final Dialog n2() {
        Dialog g2 = g2();
        if (g2 != null) {
            return g2;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    public void o2(boolean z) {
        this.p0 = z;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.i0 = new Handler();
        this.p0 = this.E == 0;
        if (bundle != null) {
            this.m0 = bundle.getInt("android:style", 0);
            this.n0 = bundle.getInt("android:theme", 0);
            this.o0 = bundle.getBoolean("android:cancelable", true);
            this.p0 = bundle.getBoolean("android:showsDialog", this.p0);
            this.q0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.u0) {
            return;
        }
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        f2(true, true, false);
    }

    public void p2(Dialog dialog, int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public void q2(FragmentManager fragmentManager, String str) {
        this.v0 = false;
        this.w0 = true;
        FragmentTransaction p2 = fragmentManager.p();
        p2.t(true);
        p2.e(this, str);
        p2.h();
    }

    @Override // androidx.fragment.app.Fragment
    public void y0(Bundle bundle) {
        super.y0(bundle);
    }
}
