package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* loaded from: classes2.dex */
public class DialogButtonBar extends LinearLayout {
    private View[] mButtons;
    private View[] mDividers;

    public DialogButtonBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mButtons = new View[3];
        this.mDividers = new View[2];
    }

    private void a() {
        View[] viewArr = this.mButtons;
        if (viewArr[0] != null) {
            return;
        }
        viewArr[0] = findViewById(R.id.button2);
        this.mButtons[1] = findViewById(R.id.button3);
        this.mButtons[2] = findViewById(R.id.button1);
        this.mDividers[0] = findViewById(com.zte.extres.R.id.divider1);
        this.mDividers[1] = findViewById(com.zte.extres.R.id.divider2);
    }

    private void b() {
        View[] viewArr;
        a();
        View view = null;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            viewArr = this.mButtons;
            if (i2 >= viewArr.length) {
                break;
            }
            View view2 = viewArr[i2];
            if (view2 != null && view2.getVisibility() == 0) {
                i3++;
                view = this.mButtons[i2];
            }
            i2++;
        }
        if (i3 == 3) {
            this.mDividers[0].setVisibility(0);
            this.mDividers[1].setVisibility(0);
            return;
        }
        if (i3 == 1) {
            this.mDividers[0].setVisibility(8);
            this.mDividers[1].setVisibility(8);
        } else if (i3 == 2) {
            if (view == viewArr[1]) {
                this.mDividers[0].setVisibility(0);
                this.mDividers[1].setVisibility(8);
            } else {
                this.mDividers[0].setVisibility(8);
                this.mDividers[1].setVisibility(0);
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        b();
        super.onMeasure(i2, i3);
    }

    public DialogButtonBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mButtons = new View[3];
        this.mDividers = new View[2];
    }

    public DialogButtonBar(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mButtons = new View[3];
        this.mDividers = new View[2];
    }
}
