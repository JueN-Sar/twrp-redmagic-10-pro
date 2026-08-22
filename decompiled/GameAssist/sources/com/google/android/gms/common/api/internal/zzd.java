package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzd extends Fragment implements LifecycleFragment {
    private static final WeakHashMap l0 = new WeakHashMap();
    private final Map i0 = Collections.synchronizedMap(new ArrayMap());
    private int j0 = 0;
    private Bundle k0;

    public static zzd c2(FragmentActivity fragmentActivity) {
        zzd zzdVar;
        WeakHashMap weakHashMap = l0;
        WeakReference weakReference = (WeakReference) weakHashMap.get(fragmentActivity);
        if (weakReference != null && (zzdVar = (zzd) weakReference.get()) != null) {
            return zzdVar;
        }
        try {
            zzd zzdVar2 = (zzd) fragmentActivity.V().l0("SupportLifecycleFragmentImpl");
            if (zzdVar2 == null || zzdVar2.t0()) {
                zzdVar2 = new zzd();
                fragmentActivity.V().p().e(zzdVar2, "SupportLifecycleFragmentImpl").i();
            }
            weakHashMap.put(fragmentActivity, new WeakReference(zzdVar2));
            return zzdVar2;
        } catch (ClassCastException e2) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void I0() {
        super.I0();
        this.j0 = 5;
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).onDestroy();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void X0(Bundle bundle) {
        super.X0(bundle);
        if (bundle == null) {
            return;
        }
        for (Map.Entry entry : this.i0.entrySet()) {
            Bundle bundle2 = new Bundle();
            ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Y0() {
        super.Y0();
        this.j0 = 2;
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).onStart();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z0() {
        super.Z0();
        this.j0 = 4;
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).onStop();
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void b(String str, LifecycleCallback lifecycleCallback) {
        if (this.i0.containsKey(str)) {
            throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
        }
        this.i0.put(str, lifecycleCallback);
        if (this.j0 > 0) {
            new zzi(Looper.getMainLooper()).post(new zzc(this, lifecycleCallback, str));
        }
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final LifecycleCallback c(String str, Class cls) {
        return (LifecycleCallback) cls.cast(this.i0.get(str));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleFragment
    public final /* synthetic */ Activity g() {
        return t();
    }

    @Override // androidx.fragment.app.Fragment
    public final void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.o(str, fileDescriptor, printWriter, strArr);
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.j0 = 1;
        this.k0 = bundle;
        for (Map.Entry entry : this.i0.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.j0 = 3;
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).onResume();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void z0(int i2, int i3, Intent intent) {
        super.z0(i2, i3, intent);
        Iterator it = this.i0.values().iterator();
        while (it.hasNext()) {
            ((LifecycleCallback) it.next()).onActivityResult(i2, i3, intent);
        }
    }
}
