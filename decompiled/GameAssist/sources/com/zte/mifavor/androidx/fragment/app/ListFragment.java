package com.zte.mifavor.androidx.fragment.app;

import android.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/* loaded from: classes2.dex */
public class ListFragment extends androidx.fragment.app.ListFragment {
    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2;
        View H0 = super.H0(layoutInflater, viewGroup, bundle);
        ListView listView = (ListView) H0.findViewById(R.id.list);
        if (listView != null && (viewGroup2 = (ViewGroup) listView.getParent()) != null) {
            com.zte.mifavor.widget.ListView listView2 = new com.zte.mifavor.widget.ListView(D1());
            listView2.setId(R.id.list);
            listView2.setDrawSelectorOnTop(false);
            viewGroup2.removeView(listView);
            viewGroup2.addView(listView2, listView.getLayoutParams());
        }
        return H0;
    }
}
