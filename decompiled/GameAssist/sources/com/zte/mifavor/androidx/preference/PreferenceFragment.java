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
public abstract class PreferenceFragment extends androidx.preference.PreferenceFragment {

    /* renamed from: q, reason: collision with root package name */
    private RecyclerView f17118q;

    /* renamed from: r, reason: collision with root package name */
    private RecyclerView f17119r;

    @Override // androidx.preference.PreferenceFragment
    protected RecyclerView.Adapter h(PreferenceScreen preferenceScreen) {
        Log.d("XPG#PrefFragment", "onCreateAdapter in.");
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    @Override // androidx.preference.PreferenceFragment
    public RecyclerView m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        String obj = getActivity().toString();
        boolean z = obj != null && obj.contains("SettingsHomepageActivity");
        boolean z2 = z && Utils.k(getContext());
        Log.d("XPG#PrefFragment", "onCreateRecyclerView in.. localActivity=" + obj + ", isCTSActivity=" + z + ", isCTSMode=" + z2);
        if (getActivity().getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            if (z2) {
                recyclerView = (RecyclerView) viewGroup.findViewById(R.id.main_content_scrollable_container);
                Log.d("XPG#PrefFragment", "onCreateRecyclerView. main content scrollable container.");
            } else {
                recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
                Log.d("XPG#PrefFragment", "onCreateRecyclerView. recycler view");
            }
            if (recyclerView != null) {
                return recyclerView;
            }
        }
        if (z2) {
            this.f17119r = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview_mfs_container, viewGroup, false);
            Log.d("XPG#PrefFragment", "onCreateRecyclerView. preference recyclerview mfs container.");
        } else {
            this.f17119r = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview_mfs, viewGroup, false);
            Log.d("XPG#PrefFragment", "onCreateRecyclerView. preference recyclerview mfs.");
        }
        this.f17119r.setLayoutManager(i());
        this.f17119r.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(this.f17119r));
        Log.d("XPG#PrefFragment", "onCreateRecyclerView out.");
        return this.f17119r;
    }

    @Override // androidx.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f17118q = c();
        o(null);
        this.f17118q.getItemAnimator().w(0L);
        this.f17118q.getItemAnimator().x(0L);
        this.f17118q.getItemAnimator().z(0L);
        this.f17118q.getItemAnimator().A(0L);
        ((SimpleItemAnimator) this.f17118q.getItemAnimator()).V(false);
        return onCreateView;
    }
}
