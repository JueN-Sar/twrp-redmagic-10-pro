package com.zte.mifavor.widget;

import android.R;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes2.dex */
public class ListFragmentZTE extends ListFragment implements IBottomBar {
    @Override // android.app.ListFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2;
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        android.widget.ListView listView = (android.widget.ListView) onCreateView.findViewById(R.id.list);
        if (listView != null && (viewGroup2 = (ViewGroup) listView.getParent()) != null) {
            ListView listView2 = new ListView(getContext());
            listView2.setId(R.id.list);
            listView2.setDrawSelectorOnTop(false);
            viewGroup2.removeView(listView);
            viewGroup2.addView(listView2, listView.getLayoutParams());
        }
        return onCreateView;
    }
}
