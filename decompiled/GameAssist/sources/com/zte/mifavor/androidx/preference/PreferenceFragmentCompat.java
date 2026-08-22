package com.zte.mifavor.androidx.preference;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.PreferenceRecyclerViewAccessibilityDelegate;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.zte.extres.R;
import com.zte.mifavor.widget.Utils;

/* loaded from: classes2.dex */
public abstract class PreferenceFragmentCompat extends androidx.preference.PreferenceFragmentCompat {
    private RecyclerView s0;
    private RecyclerView t0;

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("XPG#PrefFragmentCompat", "onCreateView in.");
        View H0 = super.H0(layoutInflater, viewGroup, bundle);
        this.s0 = c2();
        k2(null);
        this.s0.getItemAnimator().w(0L);
        this.s0.getItemAnimator().x(0L);
        this.s0.getItemAnimator().z(0L);
        this.s0.getItemAnimator().A(0L);
        ((SimpleItemAnimator) this.s0.getItemAnimator()).V(false);
        return H0;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    protected RecyclerView.Adapter f2(PreferenceScreen preferenceScreen) {
        Log.d("XPG#PrefFragmentCompat", "onCreateAdapter in.");
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView i2(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        String obj = t().toString();
        boolean z = obj != null && obj.contains("SettingsHomepageActivity");
        boolean z2 = z && Utils.k(z());
        Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView in.. localActivity=" + obj + ", isCTSActivity=" + z + ", isCTSMode=" + z2);
        if (t().getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            if (z2) {
                recyclerView = (RecyclerView) viewGroup.findViewById(R.id.main_content_scrollable_container);
                Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView. main content scrollable container.");
            } else {
                recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
                Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView. recycler view");
            }
            if (recyclerView != null) {
                return recyclerView;
            }
        }
        if (z2) {
            this.t0 = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview_mfs_container, viewGroup, false);
            Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView. preference recyclerview mfs container.");
        } else {
            this.t0 = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview_mfs, viewGroup, false);
            Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView. preference recyclerview mfs.");
        }
        this.t0.setLayoutManager(g2());
        this.t0.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(this.t0));
        Log.d("XPG#PrefFragmentCompat", "onCreateRecyclerView out.");
        return this.t0;
    }
}
