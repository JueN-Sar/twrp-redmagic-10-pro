package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

/* loaded from: classes2.dex */
public class TagView extends FrameLayout implements Checkable {
    private static final int[] CHECK_STATE = {R.attr.state_checked};
    private boolean isChecked;

    public TagView(Context context) {
        super(context);
    }

    public View getTagView() {
        return getChildAt(0);
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.isChecked;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(onCreateDrawableState, CHECK_STATE);
        }
        return onCreateDrawableState;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.isChecked != z) {
            this.isChecked = z;
            refreshDrawableState();
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.isChecked);
    }
}
