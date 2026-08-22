package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.utils.UIUtils;
import com.zte.mifavor.widget.NumberPickerZTE;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DatePickerZTE extends FrameLayout {
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final boolean DEFAULT_CALENDAR_VIEW_SHOWN = true;
    private static final boolean DEFAULT_ENABLED_STATE = true;
    private static final int DEFAULT_END_YEAR = 2100;
    private static final boolean DEFAULT_SPINNERS_SHOWN = true;
    private static final int DEFAULT_START_YEAR = 1900;
    private static final String TAG = "DatePickerZTE";
    private OnDateChangedListener mAutoFillChangeListener;
    private Calendar mCurrentDate;
    private Locale mCurrentLocale;
    private final DateFormat mDateFormat;
    private final NumberPickerZTE mDaySpinner;
    private final EditText mDaySpinnerInput;
    private int mEvenWhellPaintColor;
    private int mInputSize;
    private boolean mIsEnabled;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private final NumberPickerZTE mMonthSpinner;
    private final EditText mMonthSpinnerInput;
    private int mNumberOfMonths;
    private OnDateChangedListener mOnDateChangedListener;
    private int mSelectorSize;
    private int mSelectorWhellPaintColor;
    private String[] mShortMonths;
    private final LinearLayout mSpinners;
    private Calendar mTempDate;
    private int mUPdownWhellPaintColor;
    private final NumberPickerZTE mYearSpinner;
    private final EditText mYearSpinnerInput;

    public interface OnDateChangedListener {
        void c(DatePickerZTE datePickerZTE, int i2, int i3, int i4);
    }

    public DatePickerZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.datePickerStyle);
    }

    private Calendar j(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        sendAccessibilityEvent(4);
        OnDateChangedListener onDateChangedListener = this.mOnDateChangedListener;
        if (onDateChangedListener != null) {
            onDateChangedListener.c(this, getYear(), getMonth(), getDayOfMonth());
        }
    }

    private boolean m(String str, Calendar calendar) {
        try {
            calendar.setTime(this.mDateFormat.parse(str));
            return true;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void n() {
        this.mSpinners.removeAllViews();
        char[] dateFormatOrder = android.text.format.DateFormat.getDateFormatOrder(getContext());
        int length = dateFormatOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = dateFormatOrder[i2];
            if (c2 == 'M') {
                this.mSpinners.addView(this.mMonthSpinner);
                q(this.mMonthSpinner, length, i2);
            } else if (c2 == 'd') {
                this.mSpinners.addView(this.mDaySpinner);
                q(this.mDaySpinner, length, i2);
            } else {
                if (c2 != 'y') {
                    throw new IllegalArgumentException(Arrays.toString(dateFormatOrder));
                }
                this.mSpinners.addView(this.mYearSpinner);
                q(this.mYearSpinner, length, i2);
            }
        }
    }

    private void o() {
        r(this.mDaySpinner, R.id.increment, R.string.date_picker_increment_day_button);
        r(this.mDaySpinner, R.id.decrement, R.string.date_picker_decrement_day_button);
        r(this.mMonthSpinner, R.id.increment, R.string.date_picker_increment_month_button);
        r(this.mMonthSpinner, R.id.decrement, R.string.date_picker_decrement_month_button);
        r(this.mYearSpinner, R.id.increment, R.string.date_picker_increment_year_button);
        r(this.mYearSpinner, R.id.decrement, R.string.date_picker_decrement_year_button);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i2, int i3, int i4) {
        this.mCurrentDate.set(i2, i3, i4);
        if (this.mCurrentDate.before(this.mMinDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
        } else if (this.mCurrentDate.after(this.mMaxDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
        }
    }

    private void q(NumberPickerZTE numberPickerZTE, int i2, int i3) {
        ((TextView) numberPickerZTE.findViewById(R.id.numberpicker_input)).setImeOptions(i3 < i2 + (-1) ? 5 : 6);
    }

    private void r(View view, int i2, int i3) {
        View findViewById = view.findViewById(i2);
        if (findViewById != null) {
            findViewById.setContentDescription(((FrameLayout) this).mContext.getString(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (inputMethodManager.isActive(this.mYearSpinnerInput)) {
                this.mYearSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.mMonthSpinnerInput)) {
                this.mMonthSpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.mDaySpinnerInput)) {
                this.mDaySpinnerInput.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    private void setCurrentLocale(Locale locale) {
        if (locale.equals(this.mCurrentLocale)) {
            return;
        }
        this.mCurrentLocale = locale;
        this.mTempDate = j(this.mTempDate, locale);
        this.mMinDate = j(this.mMinDate, locale);
        this.mMaxDate = j(this.mMaxDate, locale);
        this.mCurrentDate = j(this.mCurrentDate, locale);
        this.mNumberOfMonths = this.mTempDate.getActualMaximum(2) + 1;
        this.mShortMonths = new DateFormatSymbols().getShortMonths();
        if (u()) {
            this.mShortMonths = new String[this.mNumberOfMonths];
            int i2 = 0;
            while (i2 < this.mNumberOfMonths) {
                int i3 = i2 + 1;
                this.mShortMonths[i2] = String.format(Locale.getDefault(), "%d", Integer.valueOf(i3));
                i2 = i3;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.mCurrentDate.equals(this.mMinDate)) {
            this.mDaySpinner.setMinValue(this.mCurrentDate.get(5));
            this.mDaySpinner.setMaxValue(this.mCurrentDate.getActualMaximum(5));
            this.mDaySpinner.setWrapSelectorWheel(false);
            this.mMonthSpinner.setDisplayedValues(null);
            this.mMonthSpinner.setMinValue(this.mCurrentDate.get(2));
            this.mMonthSpinner.setMaxValue(this.mCurrentDate.getActualMaximum(2));
            this.mMonthSpinner.setWrapSelectorWheel(false);
        } else if (this.mCurrentDate.equals(this.mMaxDate)) {
            this.mDaySpinner.setMinValue(this.mCurrentDate.getActualMinimum(5));
            this.mDaySpinner.setMaxValue(this.mCurrentDate.get(5));
            this.mDaySpinner.setWrapSelectorWheel(false);
            this.mMonthSpinner.setDisplayedValues(null);
            this.mMonthSpinner.setMinValue(this.mCurrentDate.getActualMinimum(2));
            this.mMonthSpinner.setMaxValue(this.mCurrentDate.get(2));
            this.mMonthSpinner.setWrapSelectorWheel(false);
        } else {
            this.mDaySpinner.setMinValue(1);
            this.mDaySpinner.setMaxValue(this.mCurrentDate.getActualMaximum(5));
            this.mDaySpinner.setWrapSelectorWheel(true);
            this.mMonthSpinner.setDisplayedValues(null);
            this.mMonthSpinner.setMinValue(0);
            this.mMonthSpinner.setMaxValue(11);
            this.mMonthSpinner.setWrapSelectorWheel(true);
        }
        this.mMonthSpinner.setDisplayedValues((String[]) Arrays.copyOfRange(this.mShortMonths, this.mMonthSpinner.getMinValue(), this.mMonthSpinner.getMaxValue() + 1));
        this.mYearSpinner.setMinValue(this.mMinDate.get(1));
        this.mYearSpinner.setMaxValue(this.mMaxDate.get(1));
        this.mYearSpinner.setWrapSelectorWheel(false);
        this.mYearSpinner.setValue(this.mCurrentDate.get(1));
        this.mMonthSpinner.setValue(this.mCurrentDate.get(2));
        this.mDaySpinner.setValue(this.mCurrentDate.get(5));
        if (u()) {
            this.mMonthSpinnerInput.setRawInputType(2);
        }
    }

    private boolean u() {
        return Character.isDigit(this.mShortMonths[0].charAt(0));
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public int getDayOfMonth() {
        return this.mCurrentDate.get(5);
    }

    public int getMonth() {
        return this.mCurrentDate.get(2);
    }

    public boolean getSpinnersShown() {
        return this.mSpinners.isShown();
    }

    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void k(int i2, int i3, int i4, OnDateChangedListener onDateChangedListener) {
        p(i2, i3, i4);
        t();
        this.mOnDateChangedListener = onDateChangedListener;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DatePickerZTE.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DatePickerZTE.class.getName());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(((FrameLayout) this).mContext, this.mCurrentDate.getTimeInMillis(), 20));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        p(savedState.d(), savedState.b(), savedState.a());
        t();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getYear(), getMonth(), getDayOfMonth());
    }

    public void setColor(int i2) {
        this.mDaySpinner.j0(this.mUPdownWhellPaintColor, i2);
        this.mMonthSpinner.j0(this.mUPdownWhellPaintColor, i2);
        this.mYearSpinner.j0(this.mUPdownWhellPaintColor, i2);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.mIsEnabled == z) {
            return;
        }
        super.setEnabled(z);
        this.mDaySpinner.setEnabled(z);
        this.mMonthSpinner.setEnabled(z);
        this.mYearSpinner.setEnabled(z);
        this.mIsEnabled = z;
    }

    public void setInputSize(int i2) {
        this.mDaySpinner.setInputSize(i2);
        this.mMonthSpinner.setInputSize(i2);
        this.mYearSpinner.setInputSize(i2);
    }

    public void setMaxDate(long j2) {
        this.mTempDate.setTimeInMillis(j2);
        if (this.mTempDate.get(1) != this.mMaxDate.get(1) || this.mTempDate.get(6) == this.mMaxDate.get(6)) {
            this.mMaxDate.setTimeInMillis(j2);
            if (this.mCurrentDate.after(this.mMaxDate)) {
                this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
            }
            t();
        }
    }

    public void setMinDate(long j2) {
        this.mTempDate.setTimeInMillis(j2);
        if (this.mTempDate.get(1) != this.mMinDate.get(1) || this.mTempDate.get(6) == this.mMinDate.get(6)) {
            this.mMinDate.setTimeInMillis(j2);
            if (this.mCurrentDate.before(this.mMinDate)) {
                this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
            }
            t();
        }
    }

    public void setSelectorSize(int i2) {
        this.mDaySpinner.setSelectorSize(i2);
        this.mMonthSpinner.setSelectorSize(i2);
        this.mYearSpinner.setSelectorSize(i2);
    }

    public void setSpinnersShown(boolean z) {
        this.mSpinners.setVisibility(z ? 0 : 8);
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.zte.mifavor.widget.DatePickerZTE.SavedState.1
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
        private final int f17609c;

        /* renamed from: h, reason: collision with root package name */
        private final int f17610h;

        /* renamed from: i, reason: collision with root package name */
        private final int f17611i;

        public int a() {
            return this.f17611i;
        }

        public int b() {
            return this.f17610h;
        }

        public int d() {
            return this.f17609c;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f17609c);
            parcel.writeInt(this.f17610h);
            parcel.writeInt(this.f17611i);
        }

        private SavedState(Parcelable parcelable, int i2, int i3, int i4) {
            super(parcelable);
            this.f17609c = i2;
            this.f17610h = i3;
            this.f17611i = i4;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f17609c = parcel.readInt();
            this.f17610h = parcel.readInt();
            this.f17611i = parcel.readInt();
        }
    }

    public DatePickerZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.mIsEnabled = true;
        this.mSelectorWhellPaintColor = 36563;
        this.mUPdownWhellPaintColor = -1979711488;
        this.mEvenWhellPaintColor = 1107296256;
        this.mInputSize = 20;
        this.mSelectorSize = 16;
        setCurrentLocale(Locale.getDefault());
        if (UIUtils.j(context)) {
            this.mInputSize = (int) (context.getResources().getDimensionPixelSize(R.dimen.mfv_common_input_text_size_outsrcreen) / context.getResources().getDisplayMetrics().scaledDensity);
            this.mSelectorSize = (int) (context.getResources().getDimensionPixelSize(R.dimen.mfv_common_selector_text_size_outsrcreen) / context.getResources().getDisplayMetrics().scaledDensity);
            Log.d(TAG, "DatePickerZTE dp mInputSize=" + this.mInputSize + ", mSelectorSize=" + this.mSelectorSize);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DatePicker, i2, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.DatePicker_spinnersShown, true);
        int i3 = obtainStyledAttributes.getInt(R.styleable.DatePicker_startYear, DEFAULT_START_YEAR);
        int i4 = obtainStyledAttributes.getInt(R.styleable.DatePicker_endYear, DEFAULT_END_YEAR);
        String string = obtainStyledAttributes.getString(R.styleable.DatePicker_minDate);
        String string2 = obtainStyledAttributes.getString(R.styleable.DatePicker_maxDate);
        obtainStyledAttributes.recycle();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.date_picker_zte, (ViewGroup) this, true);
        NumberPickerZTE.OnValueChangeListener onValueChangeListener = new NumberPickerZTE.OnValueChangeListener() { // from class: com.zte.mifavor.widget.DatePickerZTE.1
            @Override // com.zte.mifavor.widget.NumberPickerZTE.OnValueChangeListener
            public void a(NumberPickerZTE numberPickerZTE, int i5, int i6) {
                DatePickerZTE.this.s();
                DatePickerZTE.this.mTempDate.setTimeInMillis(DatePickerZTE.this.mCurrentDate.getTimeInMillis());
                if (numberPickerZTE == DatePickerZTE.this.mDaySpinner) {
                    DatePickerZTE.this.mTempDate.add(5, i6 - i5);
                } else if (numberPickerZTE == DatePickerZTE.this.mMonthSpinner) {
                    DatePickerZTE.this.mTempDate.add(2, i6 - i5);
                } else {
                    if (numberPickerZTE != DatePickerZTE.this.mYearSpinner) {
                        throw new IllegalArgumentException();
                    }
                    DatePickerZTE.this.mTempDate.set(1, i6);
                }
                DatePickerZTE datePickerZTE = DatePickerZTE.this;
                datePickerZTE.p(datePickerZTE.mTempDate.get(1), DatePickerZTE.this.mTempDate.get(2), DatePickerZTE.this.mTempDate.get(5));
                DatePickerZTE.this.t();
                DatePickerZTE.this.l();
            }
        };
        this.mSelectorWhellPaintColor = getResources().getColor(R.color.mfv_common_date_time_txt_fc);
        this.mUPdownWhellPaintColor = getResources().getColor(R.color.mfv_common_pop_secondary_txt);
        this.mEvenWhellPaintColor = getResources().getColor(R.color.mfv_common_tf_txt_watermark);
        this.mSpinners = (LinearLayout) findViewById(R.id.pickers);
        NumberPickerZTE numberPickerZTE = (NumberPickerZTE) findViewById(R.id.day);
        this.mDaySpinner = numberPickerZTE;
        numberPickerZTE.setInputSize(this.mInputSize);
        numberPickerZTE.setSelectorSize(this.mSelectorSize);
        numberPickerZTE.k0(this.mEvenWhellPaintColor, this.mSelectorWhellPaintColor, this.mUPdownWhellPaintColor);
        numberPickerZTE.setOnLongPressUpdateInterval(100L);
        numberPickerZTE.setOnValueChangedListener(onValueChangeListener);
        this.mDaySpinnerInput = (EditText) numberPickerZTE.findViewById(R.id.numberpicker_input);
        NumberPickerZTE numberPickerZTE2 = (NumberPickerZTE) findViewById(R.id.month);
        this.mMonthSpinner = numberPickerZTE2;
        numberPickerZTE2.setInputSize(this.mInputSize);
        numberPickerZTE2.setSelectorSize(this.mSelectorSize);
        numberPickerZTE2.k0(this.mEvenWhellPaintColor, this.mSelectorWhellPaintColor, this.mUPdownWhellPaintColor);
        numberPickerZTE2.setMinValue(0);
        numberPickerZTE2.setMaxValue(this.mNumberOfMonths - 1);
        numberPickerZTE2.setDisplayedValues(this.mShortMonths);
        numberPickerZTE2.setOnLongPressUpdateInterval(200L);
        numberPickerZTE2.setOnValueChangedListener(onValueChangeListener);
        this.mMonthSpinnerInput = (EditText) numberPickerZTE2.findViewById(R.id.numberpicker_input);
        NumberPickerZTE numberPickerZTE3 = (NumberPickerZTE) findViewById(R.id.year);
        this.mYearSpinner = numberPickerZTE3;
        numberPickerZTE3.setInputSize(this.mInputSize);
        numberPickerZTE3.setSelectorSize(this.mSelectorSize);
        numberPickerZTE3.k0(this.mEvenWhellPaintColor, this.mSelectorWhellPaintColor, this.mUPdownWhellPaintColor);
        numberPickerZTE3.setOnLongPressUpdateInterval(100L);
        numberPickerZTE3.setOnValueChangedListener(onValueChangeListener);
        this.mYearSpinnerInput = (EditText) numberPickerZTE3.findViewById(R.id.numberpicker_input);
        if (z) {
            setSpinnersShown(z);
        } else {
            setSpinnersShown(true);
        }
        this.mTempDate.clear();
        if (TextUtils.isEmpty(string)) {
            this.mTempDate.set(i3, 0, 1);
        } else if (!m(string, this.mTempDate)) {
            this.mTempDate.set(i3, 0, 1);
        }
        setMinDate(this.mTempDate.getTimeInMillis());
        this.mTempDate.clear();
        if (TextUtils.isEmpty(string2)) {
            this.mTempDate.set(i4, 11, 31);
        } else if (!m(string2, this.mTempDate)) {
            this.mTempDate.set(i4, 11, 31);
        }
        setMaxDate(this.mTempDate.getTimeInMillis());
        this.mCurrentDate.setTimeInMillis(System.currentTimeMillis());
        k(this.mCurrentDate.get(1), this.mCurrentDate.get(2), this.mCurrentDate.get(5), null);
        n();
        o();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }
}
