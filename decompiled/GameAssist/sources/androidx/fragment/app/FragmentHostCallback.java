package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {

    /* renamed from: c, reason: collision with root package name */
    private final Activity f4033c;

    /* renamed from: h, reason: collision with root package name */
    private final Context f4034h;

    /* renamed from: i, reason: collision with root package name */
    private final Handler f4035i;

    /* renamed from: j, reason: collision with root package name */
    private final int f4036j;

    /* renamed from: k, reason: collision with root package name */
    final FragmentManager f4037k;

    FragmentHostCallback(FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    public void A() {
    }

    @Override // androidx.fragment.app.FragmentContainer
    public View m(int i2) {
        return null;
    }

    @Override // androidx.fragment.app.FragmentContainer
    public boolean p() {
        return true;
    }

    Activity q() {
        return this.f4033c;
    }

    Context r() {
        return this.f4034h;
    }

    public Handler s() {
        return this.f4035i;
    }

    public void u(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public abstract Object x();

    public LayoutInflater y() {
        return LayoutInflater.from(this.f4034h);
    }

    public void z(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        if (i2 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        ContextCompat.m(this.f4034h, intent, bundle);
    }

    FragmentHostCallback(Activity activity, Context context, Handler handler, int i2) {
        this.f4037k = new FragmentManagerImpl();
        this.f4033c = activity;
        this.f4034h = (Context) Preconditions.i(context, "context == null");
        this.f4035i = (Handler) Preconditions.i(handler, "handler == null");
        this.f4036j = i2;
    }
}
