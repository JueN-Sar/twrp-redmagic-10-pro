package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.zte.distbus.basetransfer.DistBusKeys;
import com.zte.extres.R;
import com.zte.mifavor.utils.UIUtils;
import com.zte.mifavor.widget.NumberPickerZTE;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
public class TimePickerZTE extends FrameLayout {
    private static final boolean DEFAULT_ENABLED_STATE = true;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final OnTimeChangedListener NO_OP_CHANGE_LISTENER = new OnTimeChangedListener() { // from class: com.zte.mifavor.widget.TimePickerZTE.1
        @Override // com.zte.mifavor.widget.TimePickerZTE.OnTimeChangedListener
        public void b(TimePickerZTE timePickerZTE, int i2, int i3) {
        }
    };
    private static final String TAG = "TimePickerZTE";
    private final Button mAmPmButton;
    private final NumberPickerZTE mAmPmSpinner;
    private final EditText mAmPmSpinnerInput;
    private final String[] mAmPmStrings;
    private Locale mCurrentLocale;
    private final NumberPickerZTE mDividerSpinner;
    private int mEvenWhellPaintColor;
    private char mHourFormat;
    private final NumberPickerZTE mHourSpinner;
    private final EditText mHourSpinnerInput;
    private boolean mHourWithTwoDigit;
    private int mInputSize;
    private boolean mIs24HourView;
    private boolean mIsAm;
    private boolean mIsEnabled;
    private final NumberPickerZTE mMinuteSpinner;
    private final EditText mMinuteSpinnerInput;
    private OnTimeChangedListener mOnTimeChangedListener;
    private int mSelectorSize;
    private int mSelectorWhellPaintColor;
    private Calendar mTempCalendar;
    private final LinearLayout mTimePickerLayout;
    private int mUPdownWhellPaintColor;

    public interface OnTimeChangedListener {
        void b(TimePickerZTE timePickerZTE, int i2, int i3);
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.zte.mifavor.widget.TimePickerZTE.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        private final int f17805c;

        /* renamed from: h, reason: collision with root package name */
        private final int f17806h;

        /* renamed from: i, reason: collision with root package name */
        private final boolean f17807i;

        /* renamed from: j, reason: collision with root package name */
        private final int f17808j;

        public int a() {
            return this.f17805c;
        }

        public int b() {
            return this.f17806h;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f17805c);
            parcel.writeInt(this.f17806h);
            parcel.writeInt(this.f17807i ? 1 : 0);
            parcel.writeInt(this.f17808j);
        }

        public SavedState(Parcelable parcelable, int i2, int i3, boolean z) {
            this(parcelable, i2, i3, z, 0);
        }

        public SavedState(Parcelable parcelable, int i2, int i3, boolean z, int i4) {
            super(parcelable);
            this.f17805c = i2;
            this.f17806h = i3;
            this.f17807i = z;
            this.f17808j = i4;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f17805c = parcel.readInt();
            this.f17806h = parcel.readInt();
            this.f17807i = parcel.readInt() != 1 ? false : TimePickerZTE.DEFAULT_ENABLED_STATE;
            this.f17808j = parcel.readInt();
        }
    }

    public TimePickerZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void getHourFormatData() {
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), this.mIs24HourView ? "Hm" : "hm");
        int length = bestDateTimePattern.length();
        this.mHourWithTwoDigit = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = bestDateTimePattern.charAt(i2);
            if (charAt == 'H' || charAt == 'h' || charAt == 'K' || charAt == 'k') {
                this.mHourFormat = charAt;
                int i3 = i2 + 1;
                if (i3 >= length || charAt != bestDateTimePattern.charAt(i3)) {
                    return;
                }
                this.mHourWithTwoDigit = DEFAULT_ENABLED_STATE;
                return;
            }
        }
    }

    private boolean j() {
        return DateFormat.getBestDateTimePattern(Locale.getDefault(), "hm").startsWith(DistBusKeys.KEY_WIFI_ENABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (!this.mHourSpinner.mIsMonkey) {
            sendAccessibilityEvent(4);
        }
        OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
        if (onTimeChangedListener != null) {
            onTimeChangedListener.b(this, getHourMFV(), getMinuteMFV());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (h()) {
            NumberPickerZTE numberPickerZTE = this.mAmPmSpinner;
            if (numberPickerZTE != null) {
                numberPickerZTE.setVisibility(8);
            } else {
                this.mAmPmButton.setVisibility(8);
            }
        } else {
            int i2 = !this.mIsAm ? 1 : 0;
            NumberPickerZTE numberPickerZTE2 = this.mAmPmSpinner;
            if (numberPickerZTE2 != null) {
                numberPickerZTE2.setValue(i2);
                this.mAmPmSpinner.setVisibility(0);
                if (j()) {
                    this.mAmPmSpinnerInput.setImeOptions(5);
                } else {
                    this.mAmPmSpinnerInput.setImeOptions(6);
                }
            } else {
                this.mAmPmButton.setText(this.mAmPmStrings[i2]);
                this.mAmPmButton.setVisibility(0);
            }
        }
        sendAccessibilityEvent(4);
    }

    private void o() {
        if (g()) {
            if (this.mHourFormat == 'k') {
                this.mHourSpinner.setMinValue(1);
                this.mHourSpinner.setMaxValue(24);
            } else {
                this.mHourSpinner.setMinValue(0);
                this.mHourSpinner.setMaxValue(23);
            }
        } else if (this.mHourFormat == 'K') {
            this.mHourSpinner.setMinValue(0);
            this.mHourSpinner.setMaxValue(11);
        } else {
            this.mHourSpinner.setMinValue(1);
            this.mHourSpinner.setMaxValue(12);
        }
        this.mHourSpinner.setFormatter(this.mHourWithTwoDigit ? NumberPickerZTE.getTwoDigitFormatter() : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (inputMethodManager.isActive(this.mHourSpinnerInput)) {
                this.mHourSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.mMinuteSpinnerInput)) {
                this.mMinuteSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.mAmPmSpinnerInput)) {
                this.mAmPmSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    private void q() {
        if (h()) {
            this.mMinuteSpinnerInput.setImeOptions(6);
        } else if (j()) {
            this.mMinuteSpinnerInput.setImeOptions(6);
        } else {
            this.mMinuteSpinnerInput.setImeOptions(5);
        }
    }

    private void setCurrentLocale(Locale locale) {
        if (locale.equals(this.mCurrentLocale)) {
            return;
        }
        this.mCurrentLocale = locale;
        this.mTempCalendar = Calendar.getInstance(locale);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return DEFAULT_ENABLED_STATE;
    }

    public String f(int i2) {
        if (i2 > 1) {
            i2 = 1;
        }
        return this.mAmPmStrings[i2];
    }

    public boolean g() {
        return this.mIs24HourView;
    }

    public View getAmView() {
        return this.mAmPmSpinnerInput;
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.mHourSpinner.getBaseline();
    }

    public Integer getCurrentHour() {
        int value = this.mHourSpinner.getValue();
        return i() ? Integer.valueOf(value) : this.mIsAm ? Integer.valueOf(value % 12) : Integer.valueOf((value % 12) + 12);
    }

    public Integer getCurrentMinute() {
        return Integer.valueOf(this.mMinuteSpinner.getValue());
    }

    public int getHour() {
        int value = this.mHourSpinner.getValue();
        return g() ? value : this.mIsAm ? value % 12 : (value % 12) + 12;
    }

    public int getHourMFV() {
        return getHour();
    }

    public View getHourView() {
        return this.mHourSpinnerInput;
    }

    public int getMinute() {
        return this.mMinuteSpinner.getValue();
    }

    public int getMinuteMFV() {
        return getMinute();
    }

    public View getMinuteView() {
        return this.mMinuteSpinnerInput;
    }

    public View getPmView() {
        return this.mAmPmSpinnerInput;
    }

    public LinearLayout getTimePickerContainer() {
        return this.mTimePickerLayout;
    }

    public boolean h() {
        return g();
    }

    public boolean i() {
        return this.mIs24HourView;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void l(Integer num, boolean z) {
        if (num == null || num.equals(getCurrentHour())) {
            return;
        }
        if (!h()) {
            if (num.intValue() >= 12) {
                this.mIsAm = false;
                if (num.intValue() > 12) {
                    num = Integer.valueOf(num.intValue() - 12);
                }
            } else {
                this.mIsAm = DEFAULT_ENABLED_STATE;
                if (num.intValue() == 0) {
                    num = 12;
                }
            }
            n();
        }
        this.mHourSpinner.setValue(num.intValue());
        if (z) {
            k();
        }
    }

    public void m(Integer num, boolean z) {
        if (num.equals(getCurrentMinute())) {
            return;
        }
        this.mMinuteSpinner.setValue(num.intValue());
        if (z) {
            k();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TimePickerZTE.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TimePickerZTE.class.getName());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        int i2 = this.mIs24HourView ? 129 : 65;
        this.mTempCalendar.set(11, getHourMFV());
        this.mTempCalendar.set(12, getMinuteMFV());
        accessibilityEvent.getText().add(DateUtils.formatDateTime(((FrameLayout) this).mContext, this.mTempCalendar.getTimeInMillis(), i2));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setHourMFV(savedState.a());
        setMinuteMFV(savedState.b());
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getHourMFV(), getMinuteMFV(), h());
    }

    public boolean r() {
        return DEFAULT_ENABLED_STATE;
    }

    public void setColor(int i2) {
        this.mDividerSpinner.j0(this.mUPdownWhellPaintColor, i2);
        this.mHourSpinner.j0(this.mUPdownWhellPaintColor, i2);
        this.mMinuteSpinner.j0(this.mUPdownWhellPaintColor, i2);
        this.mAmPmSpinner.j0(this.mUPdownWhellPaintColor, i2);
    }

    public void setCurrentHour(Integer num) {
        l(num, DEFAULT_ENABLED_STATE);
    }

    public void setCurrentMinute(Integer num) {
        m(num, DEFAULT_ENABLED_STATE);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.mIsEnabled == z) {
            return;
        }
        super.setEnabled(z);
        this.mMinuteSpinner.setEnabled(z);
        NumberPickerZTE numberPickerZTE = this.mDividerSpinner;
        if (numberPickerZTE != null) {
            numberPickerZTE.setEnabled(z);
        }
        this.mHourSpinner.setEnabled(z);
        NumberPickerZTE numberPickerZTE2 = this.mAmPmSpinner;
        if (numberPickerZTE2 != null) {
            numberPickerZTE2.setEnabled(z);
        } else {
            this.mAmPmButton.setEnabled(z);
        }
        this.mIsEnabled = z;
    }

    public void setHour(int i2) {
        l(Integer.valueOf(i2), DEFAULT_ENABLED_STATE);
    }

    public void setHourMFV(int i2) {
        setHour(i2);
    }

    public void setIs24Hour(boolean z) {
        if (this.mIs24HourView == z) {
            return;
        }
        int hour = getHour();
        this.mIs24HourView = z;
        getHourFormatData();
        o();
        l(Integer.valueOf(hour), false);
        q();
        n();
    }

    public void setIs24HourView(Boolean bool) {
        if (this.mIs24HourView == bool.booleanValue()) {
            return;
        }
        Integer currentHour = getCurrentHour();
        currentHour.intValue();
        this.mIs24HourView = bool.booleanValue();
        getHourFormatData();
        o();
        l(currentHour, false);
        q();
        n();
    }

    public void setMinute(int i2) {
        if (i2 == getMinute()) {
            return;
        }
        this.mMinuteSpinner.setValue(i2);
        k();
    }

    public void setMinuteMFV(int i2) {
        setMinute(i2);
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.mOnTimeChangedListener = onTimeChangedListener;
    }

    public TimePickerZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsEnabled = DEFAULT_ENABLED_STATE;
        this.mSelectorWhellPaintColor = 36563;
        this.mUPdownWhellPaintColor = -1979711488;
        this.mEvenWhellPaintColor = 1107296256;
        this.mInputSize = 20;
        this.mSelectorSize = 16;
        setCurrentLocale(Locale.getDefault());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.time_picker_zte, this, DEFAULT_ENABLED_STATE);
        if (UIUtils.j(context)) {
            this.mInputSize = (int) (context.getResources().getDimensionPixelSize(R.dimen.mfv_common_input_text_size_outsrcreen) / context.getResources().getDisplayMetrics().scaledDensity);
            this.mSelectorSize = (int) (context.getResources().getDimensionPixelSize(R.dimen.mfv_common_selector_text_size_outsrcreen) / context.getResources().getDisplayMetrics().scaledDensity);
            Log.d(TAG, "TimePickerZTE dp mInputSize=" + this.mInputSize + ", mSelectorSize=" + this.mSelectorSize);
        }
        this.mSelectorWhellPaintColor = getResources().getColor(R.color.mfv_common_date_time_txt_fc);
        this.mUPdownWhellPaintColor = getResources().getColor(R.color.mfv_common_pop_secondary_txt);
        this.mEvenWhellPaintColor = getResources().getColor(R.color.mfv_common_tf_txt_watermark);
        this.mTimePickerLayout = (LinearLayout) findViewById(R.id.timePickerLayout);
        NumberPickerZTE numberPickerZTE = (NumberPickerZTE) findViewById(R.id.hour);
        this.mHourSpinner = numberPickerZTE;
        numberPickerZTE.setInputSize(this.mInputSize);
        numberPickerZTE.setSelectorSize(this.mSelectorSize);
        numberPickerZTE.k0(this.mEvenWhellPaintColor, this.mSelectorWhellPaintColor, this.mUPdownWhellPaintColor);
        numberPickerZTE.setOnValueChangedListener(new NumberPickerZTE.OnValueChangeListener() { // from class: com.zte.mifavor.widget.TimePickerZTE.2
            @Override // com.zte.mifavor.widget.NumberPickerZTE.OnValueChangeListener
            public void a(NumberPickerZTE numberPickerZTE2, int i3, int i4) {
                TimePickerZTE.this.p();
                if (!TimePickerZTE.this.h() && ((i3 == 11 && i4 == 12) || (i3 == 12 && i4 == 11))) {
                    TimePickerZTE.this.mIsAm ^= TimePickerZTE.DEFAULT_ENABLED_STATE;
                    TimePickerZTE.this.n();
                }
                TimePickerZTE.this.k();
            }
        });
        EditText editText = (EditText) numberPickerZTE.findViewById(R.id.numberpicker_input);
        this.mHourSpinnerInput = editText;
        editText.setImeOptions(5);
        NumberPickerZTE numberPickerZTE2 = (NumberPickerZTE) findViewById(R.id.divider);
        this.mDividerSpinner = numberPickerZTE2;
        numberPickerZTE2.setMinValue(0);
        numberPickerZTE2.setMaxValue(0);
        numberPickerZTE2.setDisplayedValues(new String[]{":"});
        numberPickerZTE2.setInputSize(this.mInputSize);
        numberPickerZTE2.getInputText().setFocusable(false);
        numberPickerZTE2.setEnabled(false);
        numberPickerZTE2.j0(this.mUPdownWhellPaintColor, this.mSelectorWhellPaintColor);
        NumberPickerZTE numberPickerZTE3 = (NumberPickerZTE) findViewById(R.id.minute);
        this.mMinuteSpinner = numberPickerZTE3;
        numberPickerZTE3.setInputSize(this.mInputSize);
        numberPickerZTE3.setSelectorSize(this.mSelectorSize);
        numberPickerZTE3.k0(this.mEvenWhellPaintColor, this.mSelectorWhellPaintColor, this.mUPdownWhellPaintColor);
        numberPickerZTE3.setMinValue(0);
        numberPickerZTE3.setMaxValue(59);
        numberPickerZTE3.setOnLongPressUpdateInterval(100L);
        numberPickerZTE3.setFormatter(NumberPickerZTE.getTwoDigitFormatter());
        numberPickerZTE3.setOnValueChangedListener(new NumberPickerZTE.OnValueChangeListener() { // from class: com.zte.mifavor.widget.TimePickerZTE.3
            @Override // com.zte.mifavor.widget.NumberPickerZTE.OnValueChangeListener
            public void a(NumberPickerZTE numberPickerZTE4, int i3, int i4) {
                TimePickerZTE.this.p();
                TimePickerZTE.this.k();
            }
        });
        EditText editText2 = (EditText) numberPickerZTE3.findViewById(R.id.numberpicker_input);
        this.mMinuteSpinnerInput = editText2;
        editText2.setImeOptions(5);
        String[] strArr = {context.getString(R.string.am), context.getString(R.string.pm)};
        this.mAmPmStrings = strArr;
        View findViewById = findViewById(R.id.amPm);
        if (findViewById instanceof Button) {
            this.mAmPmSpinner = null;
            this.mAmPmSpinnerInput = null;
            Button button = (Button) findViewById;
            this.mAmPmButton = button;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.TimePickerZTE.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    view.requestFocus();
                    TimePickerZTE.this.mIsAm ^= TimePickerZTE.DEFAULT_ENABLED_STATE;
                    TimePickerZTE.this.n();
                    TimePickerZTE.this.k();
                }
            });
        } else {
            this.mAmPmButton = null;
            NumberPickerZTE numberPickerZTE4 = (NumberPickerZTE) findViewById;
            this.mAmPmSpinner = numberPickerZTE4;
            numberPickerZTE4.setInputSize(this.mInputSize);
            numberPickerZTE4.setSelectorSize(this.mSelectorSize);
            numberPickerZTE4.j0(this.mUPdownWhellPaintColor, this.mSelectorWhellPaintColor);
            numberPickerZTE4.setMinValue(0);
            numberPickerZTE4.setMaxValue(1);
            numberPickerZTE4.setDisplayedValues(strArr);
            numberPickerZTE4.setOnValueChangedListener(new NumberPickerZTE.OnValueChangeListener() { // from class: com.zte.mifavor.widget.TimePickerZTE.5
                @Override // com.zte.mifavor.widget.NumberPickerZTE.OnValueChangeListener
                public void a(NumberPickerZTE numberPickerZTE5, int i3, int i4) {
                    TimePickerZTE.this.p();
                    numberPickerZTE5.requestFocus();
                    TimePickerZTE.this.mIsAm ^= TimePickerZTE.DEFAULT_ENABLED_STATE;
                    TimePickerZTE.this.n();
                    TimePickerZTE.this.k();
                }
            });
            EditText editText3 = (EditText) numberPickerZTE4.findViewById(R.id.numberpicker_input);
            this.mAmPmSpinnerInput = editText3;
            editText3.setImeOptions(6);
        }
        if (j()) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.timePickerLayout);
            viewGroup.removeView(findViewById);
            viewGroup.addView(findViewById, 0);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) findViewById.getLayoutParams();
            int marginStart = marginLayoutParams.getMarginStart();
            int marginEnd = marginLayoutParams.getMarginEnd();
            if (marginStart != marginEnd) {
                marginLayoutParams.setMarginStart(marginEnd);
                marginLayoutParams.setMarginEnd(marginStart);
            }
        }
        getHourFormatData();
        o();
        q();
        n();
        setOnTimeChangedListener(NO_OP_CHANGE_LISTENER);
        setHourMFV(this.mTempCalendar.get(11));
        setMinuteMFV(this.mTempCalendar.get(12));
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }
}
