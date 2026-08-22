package com.google.android.material.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.EdgeToEdgeUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class MaterialDatePicker<S> extends DialogFragment {
    static final Object c1 = "CONFIRM_BUTTON_TAG";
    static final Object d1 = "CANCEL_BUTTON_TAG";
    static final Object e1 = "TOGGLE_BUTTON_TAG";
    private int C0;
    private DateSelector D0;
    private PickerFragment E0;
    private CalendarConstraints F0;
    private DayViewDecorator G0;
    private MaterialCalendar H0;
    private int I0;
    private CharSequence J0;
    private boolean K0;
    private int L0;
    private int M0;
    private CharSequence N0;
    private int O0;
    private CharSequence P0;
    private int Q0;
    private CharSequence R0;
    private int S0;
    private CharSequence T0;
    private TextView U0;
    private TextView V0;
    private CheckableImageButton W0;
    private MaterialShapeDrawable X0;
    private Button Y0;
    private boolean Z0;
    private CharSequence a1;
    private CharSequence b1;
    private final LinkedHashSet y0 = new LinkedHashSet();
    private final LinkedHashSet z0 = new LinkedHashSet();
    private final LinkedHashSet A0 = new LinkedHashSet();
    private final LinkedHashSet B0 = new LinkedHashSet();

    public static final class Builder<S> {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface InputMode {
    }

    private String A2() {
        return y2().e(D1());
    }

    private static int C2(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_content_padding);
        int i2 = Month.n().f14494j;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_width) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_horizontal_padding));
    }

    private int E2(Context context) {
        int i2 = this.C0;
        return i2 != 0 ? i2 : y2().h(context);
    }

    private void F2(Context context) {
        this.W0.setTag(e1);
        this.W0.setImageDrawable(w2(context));
        this.W0.setChecked(this.L0 != 0);
        ViewCompat.i0(this.W0, null);
        N2(this.W0);
        this.W0.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MaterialDatePicker.this.J2(view);
            }
        });
    }

    static boolean G2(Context context) {
        return K2(context, android.R.attr.windowFullscreen);
    }

    private boolean H2() {
        return U().getConfiguration().orientation == 2;
    }

    static boolean I2(Context context) {
        return K2(context, R.attr.nestedScrollable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J2(View view) {
        this.Y0.setEnabled(y2().D());
        this.W0.toggle();
        this.L0 = this.L0 == 1 ? 0 : 1;
        N2(this.W0);
        L2();
    }

    static boolean K2(Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.d(context, R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{i2});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    private void L2() {
        int E2 = E2(D1());
        MaterialCalendar s2 = MaterialCalendar.s2(y2(), E2, this.F0, this.G0);
        this.H0 = s2;
        PickerFragment pickerFragment = s2;
        if (this.L0 == 1) {
            pickerFragment = MaterialTextInputPicker.c2(y2(), E2, this.F0);
        }
        this.E0 = pickerFragment;
        M2();
        updateHeader(B2());
        FragmentTransaction p2 = y().p();
        p2.q(R.id.mtrl_calendar_frame, this.E0);
        p2.j();
        this.E0.a2(new OnSelectionChangedListener<S>() { // from class: com.google.android.material.datepicker.MaterialDatePicker.4
            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public void a() {
                MaterialDatePicker.this.Y0.setEnabled(false);
            }

            @Override // com.google.android.material.datepicker.OnSelectionChangedListener
            public void b(Object obj) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
                materialDatePicker.updateHeader(materialDatePicker.B2());
                MaterialDatePicker.this.Y0.setEnabled(MaterialDatePicker.this.y2().D());
            }
        });
    }

    private void M2() {
        this.U0.setText((this.L0 == 1 && H2()) ? this.b1 : this.a1);
    }

    private void N2(CheckableImageButton checkableImageButton) {
        this.W0.setContentDescription(this.L0 == 1 ? checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(R.string.mtrl_picker_toggle_to_text_input_mode));
    }

    private static Drawable w2(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, AppCompatResources.b(context, R.drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], AppCompatResources.b(context, R.drawable.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    private void x2(Window window) {
        if (this.Z0) {
            return;
        }
        final View findViewById = E1().findViewById(R.id.fullscreen_header);
        EdgeToEdgeUtils.a(window, true, ViewUtils.i(findViewById), null);
        final int paddingTop = findViewById.getPaddingTop();
        final int i2 = findViewById.getLayoutParams().height;
        ViewCompat.x0(findViewById, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                int i3 = windowInsetsCompat.f(WindowInsetsCompat.Type.e()).f2921b;
                if (i2 >= 0) {
                    findViewById.getLayoutParams().height = i2 + i3;
                    View view2 = findViewById;
                    view2.setLayoutParams(view2.getLayoutParams());
                }
                View view3 = findViewById;
                view3.setPadding(view3.getPaddingLeft(), paddingTop + i3, findViewById.getPaddingRight(), findViewById.getPaddingBottom());
                return windowInsetsCompat;
            }
        });
        this.Z0 = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DateSelector y2() {
        if (this.D0 == null) {
            this.D0 = (DateSelector) x().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.D0;
    }

    private static CharSequence z2(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        String[] split = TextUtils.split(String.valueOf(charSequence), "\n");
        return split.length > 1 ? split[0] : charSequence;
    }

    public String B2() {
        return y2().s(z());
    }

    public final Object D2() {
        return y2().F();
    }

    @Override // androidx.fragment.app.Fragment
    public final View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.K0 ? R.layout.mtrl_picker_fullscreen : R.layout.mtrl_picker_dialog, viewGroup);
        Context context = inflate.getContext();
        DayViewDecorator dayViewDecorator = this.G0;
        if (dayViewDecorator != null) {
            dayViewDecorator.l(context);
        }
        if (this.K0) {
            inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(C2(context), -2));
        } else {
            inflate.findViewById(R.id.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(C2(context), -1));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text);
        this.V0 = textView;
        ViewCompat.k0(textView, 1);
        this.W0 = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
        this.U0 = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
        F2(context);
        this.Y0 = (Button) inflate.findViewById(R.id.confirm_button);
        if (y2().D()) {
            this.Y0.setEnabled(true);
        } else {
            this.Y0.setEnabled(false);
        }
        this.Y0.setTag(c1);
        CharSequence charSequence = this.N0;
        if (charSequence != null) {
            this.Y0.setText(charSequence);
        } else {
            int i2 = this.M0;
            if (i2 != 0) {
                this.Y0.setText(i2);
            }
        }
        CharSequence charSequence2 = this.P0;
        if (charSequence2 != null) {
            this.Y0.setContentDescription(charSequence2);
        } else if (this.O0 != 0) {
            this.Y0.setContentDescription(z().getResources().getText(this.O0));
        }
        this.Y0.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator it = MaterialDatePicker.this.y0.iterator();
                while (it.hasNext()) {
                    ((MaterialPickerOnPositiveButtonClickListener) it.next()).a(MaterialDatePicker.this.D2());
                }
                MaterialDatePicker.this.d2();
            }
        });
        Button button = (Button) inflate.findViewById(R.id.cancel_button);
        button.setTag(d1);
        CharSequence charSequence3 = this.R0;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i3 = this.Q0;
            if (i3 != 0) {
                button.setText(i3);
            }
        }
        CharSequence charSequence4 = this.T0;
        if (charSequence4 != null) {
            button.setContentDescription(charSequence4);
        } else if (this.S0 != 0) {
            button.setContentDescription(z().getResources().getText(this.S0));
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.datepicker.MaterialDatePicker.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Iterator it = MaterialDatePicker.this.z0.iterator();
                while (it.hasNext()) {
                    ((View.OnClickListener) it.next()).onClick(view);
                }
                MaterialDatePicker.this.d2();
            }
        });
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void X0(Bundle bundle) {
        super.X0(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.C0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.D0);
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder(this.F0);
        MaterialCalendar materialCalendar = this.H0;
        Month n2 = materialCalendar == null ? null : materialCalendar.n2();
        if (n2 != null) {
            builder.b(n2.f14496l);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", builder.a());
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.G0);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.I0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.J0);
        bundle.putInt("INPUT_MODE_KEY", this.L0);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.M0);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.N0);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.O0);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.P0);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.Q0);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.R0);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.S0);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.T0);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void Y0() {
        super.Y0();
        Window window = n2().getWindow();
        if (this.K0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.X0);
            x2(window);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = U().getDimensionPixelOffset(R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.X0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(n2(), rect));
        }
        L2();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void Z0() {
        this.E0.b2();
        super.Z0();
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog j2(Bundle bundle) {
        Dialog dialog = new Dialog(D1(), E2(D1()));
        Context context = dialog.getContext();
        this.K0 = G2(context);
        this.X0 = new MaterialShapeDrawable(context, null, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.MaterialCalendar, R.attr.materialCalendarStyle, R.style.Widget_MaterialComponents_MaterialCalendar);
        int color = obtainStyledAttributes.getColor(R.styleable.MaterialCalendar_backgroundTint, 0);
        obtainStyledAttributes.recycle();
        this.X0.P(context);
        this.X0.a0(ColorStateList.valueOf(color));
        this.X0.Z(ViewCompat.r(dialog.getWindow().getDecorView()));
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
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = x();
        }
        this.C0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.D0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.F0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.G0 = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.I0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.J0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.L0 = bundle.getInt("INPUT_MODE_KEY");
        this.M0 = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.N0 = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.O0 = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.P0 = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.Q0 = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.R0 = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.S0 = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.T0 = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence charSequence = this.J0;
        if (charSequence == null) {
            charSequence = D1().getResources().getText(this.I0);
        }
        this.a1 = charSequence;
        this.b1 = z2(charSequence);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.B0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) h0();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @VisibleForTesting
    void updateHeader(String str) {
        this.V0.setContentDescription(A2());
        this.V0.setText(str);
    }
}
