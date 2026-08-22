package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class MaterialTimePicker extends DialogFragment implements TimePickerView.OnDoubleTapListener {
    private TimePickerView C0;
    private ViewStub D0;
    private TimePickerClockPresenter E0;
    private TimePickerTextInputPresenter F0;
    private TimePickerPresenter G0;
    private int H0;
    private int I0;
    private CharSequence K0;
    private CharSequence M0;
    private CharSequence O0;
    private MaterialButton P0;
    private Button Q0;
    private TimeModel S0;
    private final Set y0 = new LinkedHashSet();
    private final Set z0 = new LinkedHashSet();
    private final Set A0 = new LinkedHashSet();
    private final Set B0 = new LinkedHashSet();
    private int J0 = 0;
    private int L0 = 0;
    private int N0 = 0;
    private int R0 = 0;
    private int T0 = 0;

    public static final class Builder {
    }

    private TimePickerPresenter A2(int i2, TimePickerView timePickerView, ViewStub viewStub) {
        if (i2 != 0) {
            if (this.F0 == null) {
                this.F0 = new TimePickerTextInputPresenter((LinearLayout) viewStub.inflate(), this.S0);
            }
            this.F0.f();
            return this.F0;
        }
        TimePickerClockPresenter timePickerClockPresenter = this.E0;
        if (timePickerClockPresenter == null) {
            timePickerClockPresenter = new TimePickerClockPresenter(timePickerView, this.S0);
        }
        this.E0 = timePickerClockPresenter;
        return timePickerClockPresenter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B2() {
        TimePickerPresenter timePickerPresenter = this.G0;
        if (timePickerPresenter instanceof TimePickerTextInputPresenter) {
            ((TimePickerTextInputPresenter) timePickerPresenter).j();
        }
    }

    private void C2(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        TimeModel timeModel = (TimeModel) bundle.getParcelable("TIME_PICKER_TIME_MODEL");
        this.S0 = timeModel;
        if (timeModel == null) {
            this.S0 = new TimeModel();
        }
        this.R0 = bundle.getInt("TIME_PICKER_INPUT_MODE", this.S0.f15477i != 1 ? 0 : 1);
        this.J0 = bundle.getInt("TIME_PICKER_TITLE_RES", 0);
        this.K0 = bundle.getCharSequence("TIME_PICKER_TITLE_TEXT");
        this.L0 = bundle.getInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", 0);
        this.M0 = bundle.getCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT");
        this.N0 = bundle.getInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", 0);
        this.O0 = bundle.getCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT");
        this.T0 = bundle.getInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", 0);
    }

    private void D2() {
        Button button = this.Q0;
        if (button != null) {
            button.setVisibility(i2() ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E2(MaterialButton materialButton) {
        if (materialButton == null || this.C0 == null || this.D0 == null) {
            return;
        }
        TimePickerPresenter timePickerPresenter = this.G0;
        if (timePickerPresenter != null) {
            timePickerPresenter.hide();
        }
        TimePickerPresenter A2 = A2(this.R0, this.C0, this.D0);
        this.G0 = A2;
        A2.show();
        this.G0.a();
        Pair y2 = y2(this.R0);
        materialButton.setIconResource(((Integer) y2.first).intValue());
        materialButton.setContentDescription(U().getString(((Integer) y2.second).intValue()));
        materialButton.sendAccessibilityEvent(4);
    }

    private Pair y2(int i2) {
        if (i2 == 0) {
            return new Pair(Integer.valueOf(this.H0), Integer.valueOf(R.string.material_timepicker_text_input_mode_description));
        }
        if (i2 == 1) {
            return new Pair(Integer.valueOf(this.I0), Integer.valueOf(R.string.material_timepicker_clock_mode_description));
        }
        throw new IllegalArgumentException("no icon for mode: " + i2);
    }

    private int z2() {
        int i2 = this.T0;
        if (i2 != 0) {
            return i2;
        }
        TypedValue a2 = MaterialAttributes.a(D1(), R.attr.materialTimePickerTheme);
        if (a2 == null) {
            return 0;
        }
        return a2.data;
    }

    @Override // androidx.fragment.app.Fragment
    public final View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.material_timepicker_dialog, viewGroup);
        TimePickerView timePickerView = (TimePickerView) viewGroup2.findViewById(R.id.material_timepicker_view);
        this.C0 = timePickerView;
        timePickerView.Q(this);
        this.D0 = (ViewStub) viewGroup2.findViewById(R.id.material_textinput_timepicker);
        this.P0 = (MaterialButton) viewGroup2.findViewById(R.id.material_timepicker_mode_button);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.header_title);
        int i2 = this.J0;
        if (i2 != 0) {
            textView.setText(i2);
        } else if (!TextUtils.isEmpty(this.K0)) {
            textView.setText(this.K0);
        }
        E2(this.P0);
        Button button = (Button) viewGroup2.findViewById(R.id.material_timepicker_ok_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator it = MaterialTimePicker.this.y0.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                MaterialTimePicker.this.d2();
            }
        });
        int i3 = this.L0;
        if (i3 != 0) {
            button.setText(i3);
        } else if (!TextUtils.isEmpty(this.M0)) {
            button.setText(this.M0);
        }
        Button button2 = (Button) viewGroup2.findViewById(R.id.material_timepicker_cancel_button);
        this.Q0 = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator it = MaterialTimePicker.this.z0.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                MaterialTimePicker.this.d2();
            }
        });
        int i4 = this.N0;
        if (i4 != 0) {
            this.Q0.setText(i4);
        } else if (!TextUtils.isEmpty(this.O0)) {
            this.Q0.setText(this.O0);
        }
        D2();
        this.P0.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.timepicker.MaterialTimePicker.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
                materialTimePicker.R0 = materialTimePicker.R0 == 0 ? 1 : 0;
                MaterialTimePicker materialTimePicker2 = MaterialTimePicker.this;
                materialTimePicker2.E2(materialTimePicker2.P0);
            }
        });
        return viewGroup2;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        this.G0 = null;
        this.E0 = null;
        this.F0 = null;
        TimePickerView timePickerView = this.C0;
        if (timePickerView != null) {
            timePickerView.Q(null);
            this.C0 = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putParcelable("TIME_PICKER_TIME_MODEL", this.S0);
        bundle.putInt("TIME_PICKER_INPUT_MODE", this.R0);
        bundle.putInt("TIME_PICKER_TITLE_RES", this.J0);
        bundle.putCharSequence("TIME_PICKER_TITLE_TEXT", this.K0);
        bundle.putInt("TIME_PICKER_POSITIVE_BUTTON_TEXT_RES", this.L0);
        bundle.putCharSequence("TIME_PICKER_POSITIVE_BUTTON_TEXT", this.M0);
        bundle.putInt("TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES", this.N0);
        bundle.putCharSequence("TIME_PICKER_NEGATIVE_BUTTON_TEXT", this.O0);
        bundle.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", this.T0);
    }

    @Override // androidx.fragment.app.Fragment
    public void a1(View view, Bundle bundle) {
        super.a1(view, bundle);
        if (this.G0 instanceof TimePickerTextInputPresenter) {
            view.postDelayed(new Runnable() { // from class: com.google.android.material.timepicker.b
                @Override // java.lang.Runnable
                public final void run() {
                    MaterialTimePicker.this.B2();
                }
            }, 100L);
        }
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnDoubleTapListener
    public void d() {
        this.R0 = 1;
        E2(this.P0);
        this.F0.j();
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog j2(Bundle bundle) {
        Dialog dialog = new Dialog(D1(), z2());
        Context context = dialog.getContext();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, R.attr.materialTimePickerStyle, R.style.Widget_MaterialComponents_TimePicker);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.MaterialTimePicker, R.attr.materialTimePickerStyle, R.style.Widget_MaterialComponents_TimePicker);
        this.I0 = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_clockIcon, 0);
        this.H0 = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_keyboardIcon, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.MaterialTimePicker_backgroundTint, 0);
        obtainStyledAttributes.recycle();
        materialShapeDrawable.P(context);
        materialShapeDrawable.a0(ColorStateList.valueOf(color));
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(materialShapeDrawable);
        window.requestFeature(1);
        window.setLayout(-2, -2);
        materialShapeDrawable.Z(ViewCompat.r(window.getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.A0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = x();
        }
        C2(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.B0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    @VisibleForTesting
    void setActivePresenter(@Nullable TimePickerPresenter timePickerPresenter) {
        this.G0 = timePickerPresenter;
    }
}
