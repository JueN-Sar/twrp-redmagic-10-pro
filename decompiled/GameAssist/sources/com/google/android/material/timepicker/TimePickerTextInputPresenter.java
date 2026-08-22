package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Locale;

/* loaded from: classes.dex */
class TimePickerTextInputPresenter implements TimePickerView.OnSelectionChange, TimePickerPresenter {

    /* renamed from: c, reason: collision with root package name */
    private final LinearLayout f15496c;

    /* renamed from: h, reason: collision with root package name */
    private final TimeModel f15497h;

    /* renamed from: i, reason: collision with root package name */
    private final TextWatcher f15498i = new TextWatcherAdapter() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.1
        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    TimePickerTextInputPresenter.this.f15497h.m(0);
                } else {
                    TimePickerTextInputPresenter.this.f15497h.m(Integer.parseInt(editable.toString()));
                }
            } catch (NumberFormatException unused) {
            }
        }
    };

    /* renamed from: j, reason: collision with root package name */
    private final TextWatcher f15499j = new TextWatcherAdapter() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.2
        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    TimePickerTextInputPresenter.this.f15497h.l(0);
                } else {
                    TimePickerTextInputPresenter.this.f15497h.l(Integer.parseInt(editable.toString()));
                }
            } catch (NumberFormatException unused) {
            }
        }
    };

    /* renamed from: k, reason: collision with root package name */
    private final ChipTextInputComboView f15500k;

    /* renamed from: l, reason: collision with root package name */
    private final ChipTextInputComboView f15501l;

    /* renamed from: m, reason: collision with root package name */
    private final TimePickerTextInputKeyController f15502m;

    /* renamed from: n, reason: collision with root package name */
    private final EditText f15503n;

    /* renamed from: o, reason: collision with root package name */
    private final EditText f15504o;

    /* renamed from: p, reason: collision with root package name */
    private MaterialButtonToggleGroup f15505p;

    public TimePickerTextInputPresenter(LinearLayout linearLayout, final TimeModel timeModel) {
        this.f15496c = linearLayout;
        this.f15497h = timeModel;
        Resources resources = linearLayout.getResources();
        ChipTextInputComboView chipTextInputComboView = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_minute_text_input);
        this.f15500k = chipTextInputComboView;
        ChipTextInputComboView chipTextInputComboView2 = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_hour_text_input);
        this.f15501l = chipTextInputComboView2;
        TextView textView = (TextView) chipTextInputComboView.findViewById(R.id.material_label);
        TextView textView2 = (TextView) chipTextInputComboView2.findViewById(R.id.material_label);
        textView.setText(resources.getString(R.string.material_timepicker_minute));
        textView2.setText(resources.getString(R.string.material_timepicker_hour));
        chipTextInputComboView.setTag(R.id.selection_type, 12);
        chipTextInputComboView2.setTag(R.id.selection_type, 10);
        if (timeModel.f15477i == 0) {
            l();
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TimePickerTextInputPresenter.this.d(((Integer) view.getTag(R.id.selection_type)).intValue());
            }
        };
        chipTextInputComboView2.setOnClickListener(onClickListener);
        chipTextInputComboView.setOnClickListener(onClickListener);
        chipTextInputComboView2.c(timeModel.g());
        chipTextInputComboView.c(timeModel.i());
        this.f15503n = chipTextInputComboView2.e().getEditText();
        this.f15504o = chipTextInputComboView.e().getEditText();
        this.f15502m = new TimePickerTextInputKeyController(chipTextInputComboView2, chipTextInputComboView, timeModel);
        chipTextInputComboView2.f(new ClickActionDelegate(linearLayout.getContext(), R.string.material_hour_selection) { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.4
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.l0(view.getResources().getString(timeModel.d(), String.valueOf(timeModel.f())));
            }
        });
        chipTextInputComboView.f(new ClickActionDelegate(linearLayout.getContext(), R.string.material_minute_selection) { // from class: com.google.android.material.timepicker.TimePickerTextInputPresenter.5
            @Override // com.google.android.material.timepicker.ClickActionDelegate, androidx.core.view.AccessibilityDelegateCompat
            public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.g(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.l0(view.getResources().getString(R.string.material_minute_suffix, String.valueOf(timeModel.f15479k)));
            }
        });
        g();
    }

    private void e() {
        this.f15503n.addTextChangedListener(this.f15499j);
        this.f15504o.addTextChangedListener(this.f15498i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z) {
        if (z) {
            this.f15497h.n(i2 == R.id.material_clock_period_pm_button ? 1 : 0);
        }
    }

    private void i() {
        this.f15503n.removeTextChangedListener(this.f15499j);
        this.f15504o.removeTextChangedListener(this.f15498i);
    }

    private void k(TimeModel timeModel) {
        i();
        Locale locale = this.f15496c.getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(timeModel.f15479k));
        String format2 = String.format(locale, "%02d", Integer.valueOf(timeModel.f()));
        this.f15500k.g(format);
        this.f15501l.g(format2);
        e();
        m();
    }

    private void l() {
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) this.f15496c.findViewById(R.id.material_clock_period_toggle);
        this.f15505p = materialButtonToggleGroup;
        materialButtonToggleGroup.b(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.google.android.material.timepicker.d
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public final void a(MaterialButtonToggleGroup materialButtonToggleGroup2, int i2, boolean z) {
                TimePickerTextInputPresenter.this.h(materialButtonToggleGroup2, i2, z);
            }
        });
        this.f15505p.setVisibility(0);
        m();
    }

    private void m() {
        MaterialButtonToggleGroup materialButtonToggleGroup = this.f15505p;
        if (materialButtonToggleGroup == null) {
            return;
        }
        materialButtonToggleGroup.e(this.f15497h.f15481m == 0 ? R.id.material_clock_period_am_button : R.id.material_clock_period_pm_button);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void a() {
        k(this.f15497h);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.OnSelectionChange
    public void d(int i2) {
        this.f15497h.f15480l = i2;
        this.f15500k.setChecked(i2 == 12);
        this.f15501l.setChecked(i2 == 10);
        m();
    }

    public void f() {
        this.f15500k.setChecked(false);
        this.f15501l.setChecked(false);
    }

    public void g() {
        e();
        k(this.f15497h);
        this.f15502m.a();
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void hide() {
        View focusedChild = this.f15496c.getFocusedChild();
        if (focusedChild != null) {
            ViewUtils.o(focusedChild, false);
        }
        this.f15496c.setVisibility(8);
    }

    public void j() {
        this.f15500k.setChecked(this.f15497h.f15480l == 12);
        this.f15501l.setChecked(this.f15497h.f15480l == 10);
    }

    @Override // com.google.android.material.timepicker.TimePickerPresenter
    public void show() {
        this.f15496c.setVisibility(0);
        d(this.f15497h.f15480l);
    }
}
