package com.zte.mifavor.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import com.zte.extres.R;

/* loaded from: classes2.dex */
public class ZTEDaysOfWeek extends LinearLayout {
    private static int mCheckedColor = -65536;
    private static final int mUncheckedColor = -1579033;
    private boolean isEnabled;
    private int mDays;
    private View mView;
    private ToggleButton[] mWeekButton;

    public ZTEDaysOfWeek(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWeekButton = new ToggleButton[7];
        this.isEnabled = true;
        View inflate = LayoutInflater.from(context).inflate(R.layout.zte_days_of_week, (ViewGroup) null);
        this.mView = inflate;
        addView(inflate);
    }

    private void b() {
        this.mWeekButton[0] = (ToggleButton) this.mView.findViewById(R.id.repeat_mon);
        this.mWeekButton[1] = (ToggleButton) this.mView.findViewById(R.id.repeat_tue);
        this.mWeekButton[2] = (ToggleButton) this.mView.findViewById(R.id.repeat_wed);
        this.mWeekButton[3] = (ToggleButton) this.mView.findViewById(R.id.repeat_thu);
        this.mWeekButton[4] = (ToggleButton) this.mView.findViewById(R.id.repeat_fri);
        this.mWeekButton[5] = (ToggleButton) this.mView.findViewById(R.id.repeat_sat);
        this.mWeekButton[6] = (ToggleButton) this.mView.findViewById(R.id.repeat_sun);
        this.mWeekButton[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(0, z);
            }
        });
        this.mWeekButton[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(1, z);
            }
        });
        this.mWeekButton[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(2, z);
            }
        });
        this.mWeekButton[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(3, z);
            }
        });
        this.mWeekButton[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(4, z);
            }
        });
        this.mWeekButton[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(5, z);
            }
        });
        this.mWeekButton[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.ZTEDaysOfWeek.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ZTEDaysOfWeek.this.d(6, z);
            }
        });
        f();
        e();
    }

    private boolean c(int i2) {
        return (this.mDays & (1 << i2)) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i2, boolean z) {
        if (z) {
            this.mDays |= 1 << i2;
        } else {
            this.mDays &= ~(1 << i2);
        }
        setButtonBackGround(this.mWeekButton[i2]);
    }

    private void e() {
        for (int i2 = 0; i2 < 7; i2++) {
            setButtonBackGround(this.mWeekButton[i2]);
        }
    }

    private void f() {
        for (int i2 = 0; i2 < 7; i2++) {
            if (c(i2)) {
                this.mWeekButton[i2].setChecked(true);
            } else {
                this.mWeekButton[i2].setChecked(false);
            }
        }
        for (int i3 = 0; i3 < 7; i3++) {
            setButtonBackGround(this.mWeekButton[i3]);
        }
    }

    private void setButtonBackGround(ToggleButton toggleButton) {
        Drawable background = toggleButton.getBackground();
        if (toggleButton.isChecked()) {
            background.setColorFilter(mCheckedColor, PorterDuff.Mode.SRC);
        } else {
            background.setColorFilter(mUncheckedColor, PorterDuff.Mode.SRC);
        }
    }

    public static void setValue(int i2) {
        mCheckedColor = i2;
    }

    public boolean[] getBooleanArray() {
        boolean[] zArr = new boolean[7];
        for (int i2 = 0; i2 < 7; i2++) {
            zArr[i2] = c(i2);
        }
        return zArr;
    }

    public int getDays() {
        return this.mDays;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setBackGroundColor(int i2) {
        setValue(i2);
    }

    public void setDays(int i2) {
        this.mDays = i2;
        b();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.isEnabled = z;
        for (int i2 = 0; i2 < 7; i2++) {
            this.mWeekButton[i2].setEnabled(z);
        }
    }
}
