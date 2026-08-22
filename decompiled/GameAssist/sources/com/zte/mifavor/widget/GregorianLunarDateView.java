package com.zte.mifavor.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zte.extres.R;
import com.zte.mifavor.widget.NumberPickerZTE;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

@SuppressLint({"WrongConstant"})
/* loaded from: classes2.dex */
public class GregorianLunarDateView extends LinearLayout {
    private static final int DAY_SPAN_GREGORIAN = 31;
    private static final int DAY_SPAN_LUNAR = 30;
    private static final int DAY_START = 1;
    private static final int DAY_START_GREGORIAN = 1;
    private static final int DAY_START_LUNAR = 1;
    private static final int DAY_STOP = 30;
    private static final int DAY_STOP_GREGORIAN = 31;
    private static final int DAY_STOP_LUNAR = 30;
    private static final int MONTH_SPAN_GREGORIAN = 12;
    private static final int MONTH_SPAN_LUNAR_LEAP = 13;
    private static final int MONTH_SPAN_LUNAR_NORMAL = 12;
    private static final int MONTH_START = 1;
    private static final int MONTH_START_GREGORIAN = 1;
    private static final int MONTH_START_LUNAR = 1;
    private static final int MONTH_START_LUNAR_LEAP = 1;
    private static final int MONTH_START_LUNAR_NORMAL = 1;
    private static final int MONTH_START_LUNAR_NOYEAR = 1;
    private static final int MONTH_STOP_GREGORIAN = 12;
    private static final int MONTH_STOP_LUNAR_LEAP = 13;
    private static final int MONTH_STOP_LUNAR_NORMAL = 12;
    private static final int MONTH_STOP_LUNAR_NOYEAR = 24;
    private static final String TAG = "GregorianLunarDateView";
    private static final int YEAR_START = 1900;
    private static final int YEAR_STOP = 2100;
    private static boolean force;
    private String[] currDisplayMonthsLunar;
    private String[] displayDaysGregorian;
    private String[] displayDaysLunar;
    private String[] displayMonthsGregorian;
    private String[] displayMonthsLunar;
    private String[] displayMonthsLunarNoYear;
    private String[] displayYearsGregorian;
    private String[] displayYearsLunar;
    private int mActiveTextSize;
    private View mBottoSwitchLine;
    private View mContentView;
    private Calendar mCurrentDate;
    private int mDateTextSize;
    private int mEvenWhellPaintColor;
    private int mInactiveTextSize;
    private boolean mIsGregorian;
    private boolean mIsNeedCustomSubTitle;
    private final RelativeLayout mLunarChoice;
    private OnDateChangedListener mOnDateChangedListener;
    private String mPrefixSubTitle;
    private int mSelectorWhellPaintColor;
    private final LinearLayout mSpinners;
    private boolean mStateMachineEvent;
    private SwitchZTE mSwitch;
    private View mTopSwitchLine;
    private int mTransparentPaintColor;
    private int mUPdownWhellPaintColor;
    private int max_year;
    private int min_year;
    private NumberPickerZTE picker_day;
    private EditText picker_day_input;
    private NumberPickerZTE picker_month;
    private EditText picker_month_input;
    private NumberPickerZTE picker_year;
    private EditText picker_year_input;

    public static class CalendarData {

        /* renamed from: a, reason: collision with root package name */
        public boolean f17638a;

        /* renamed from: b, reason: collision with root package name */
        public int f17639b;

        /* renamed from: c, reason: collision with root package name */
        public int f17640c;

        /* renamed from: d, reason: collision with root package name */
        public int f17641d;

        /* renamed from: e, reason: collision with root package name */
        public ChineseCalendar f17642e;

        public CalendarData(int i2, int i3, int i4, boolean z) {
            this.f17639b = i2;
            this.f17640c = i3;
            this.f17641d = i4;
            this.f17638a = z;
            b();
        }

        private void b() {
            if (this.f17638a) {
                this.f17642e = new ChineseCalendar(this.f17639b, this.f17640c - 1, this.f17641d);
            } else {
                int i2 = this.f17639b;
                this.f17642e = new ChineseCalendar(true, i2, Util.c(this.f17640c, i2), this.f17641d);
            }
        }

        public Calendar a() {
            return this.f17642e;
        }
    }

    public interface OnDateChangedListener {
        void a(GregorianLunarDateView gregorianLunarDateView, int i2, int i3, int i4);
    }

    public GregorianLunarDateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void A() {
        this.mSpinners.removeAllViews();
        char[] dateFormatOrder = DateFormat.getDateFormatOrder(getContext());
        int length = dateFormatOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c2 = dateFormatOrder[i2];
            if (c2 == 'M') {
                this.mSpinners.addView(this.picker_month);
                C(this.picker_month, length, i2);
            } else if (c2 == 'd') {
                this.mSpinners.addView(this.picker_day);
                C(this.picker_day, length, i2);
            } else {
                if (c2 != 'y') {
                    throw new IllegalArgumentException(Arrays.toString(dateFormatOrder));
                }
                this.mSpinners.addView(this.picker_year);
                C(this.picker_year, length, i2);
            }
        }
    }

    private void B(ChineseCalendar chineseCalendar, boolean z) {
        setDisplayData(z);
        v(chineseCalendar, z);
        u(chineseCalendar, z);
        t(chineseCalendar, z);
    }

    private void C(NumberPickerZTE numberPickerZTE, int i2, int i3) {
        ((TextView) numberPickerZTE.findViewById(R.id.numberpicker_input)).setImeOptions(i3 < i2 + (-1) ? 5 : 6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(NumberPickerZTE numberPickerZTE, int i2, int i3, int i4, String[] strArr) {
        if (strArr == null) {
            throw new IllegalArgumentException("newDisplayedVales should not be null.");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("newDisplayedVales's length should not be 0.");
        }
        int i5 = (i4 - i3) + 1;
        if (strArr.length < i5) {
            throw new IllegalArgumentException("newDisplayedVales's length should not be less than newSpan.");
        }
        int maxValue = (numberPickerZTE.getMaxValue() - numberPickerZTE.getMinValue()) + 1;
        numberPickerZTE.setMinValue(i3);
        if (i5 > maxValue) {
            numberPickerZTE.setDisplayedValues(strArr);
            numberPickerZTE.setMaxValue(i4);
        } else {
            numberPickerZTE.setMaxValue(i4);
            numberPickerZTE.setDisplayedValues(strArr);
        }
        numberPickerZTE.setValue(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (inputMethodManager.isActive(this.picker_year_input)) {
                this.picker_year_input.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.picker_month_input)) {
                this.picker_month_input.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this.picker_day_input)) {
                this.picker_day_input.clearFocus();
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    private boolean J() {
        return Character.isDigit(this.displayMonthsGregorian[0].charAt(0));
    }

    private Calendar p(Calendar calendar, int i2, int i3, boolean z) {
        int i4 = calendar.get(1);
        if (!z) {
            return Math.abs(i4 - i2) < Math.abs(i4 - i3) ? new ChineseCalendar(true, i2, 1, 1) : new ChineseCalendar(true, i3, 12, Util.l(i3, 12));
        }
        if (i4 < i2) {
            calendar.set(1, i2);
            calendar.set(2, 1);
            calendar.set(5, 1);
        }
        if (i4 <= i3) {
            return calendar;
        }
        calendar.set(1, i3);
        calendar.set(2, 11);
        calendar.set(5, Util.k(i3, 12));
        return calendar;
    }

    private boolean q(Calendar calendar, int i2, int i3, boolean z) {
        int i4 = z ? calendar.get(1) : new ChineseCalendar(calendar).get(ChineseCalendar.CHINESE_YEAR);
        return i2 <= i4 && i4 <= i3;
    }

    private void setDisplayData(boolean z) {
        int i2 = 0;
        if (!z) {
            this.displayYearsLunar = new String[(this.max_year - this.min_year) + 1];
            int i3 = 0;
            while (true) {
                int i4 = this.max_year;
                int i5 = this.min_year;
                if (i3 >= (i4 - i5) + 1) {
                    break;
                }
                this.displayYearsLunar[i3] = String.valueOf(i5 + i3);
                i3++;
            }
            this.displayMonthsLunar = new String[12];
            int i6 = 0;
            while (i6 < 12) {
                int i7 = i6 + 1;
                this.displayMonthsLunar[i6] = Util.g(i7);
                i6 = i7;
            }
            this.displayDaysLunar = new String[30];
            while (i2 < 30) {
                int i8 = i2 + 1;
                this.displayDaysLunar[i2] = Util.f(i8);
                i2 = i8;
            }
            return;
        }
        this.displayYearsGregorian = new String[(this.max_year - this.min_year) + 1];
        int i9 = 0;
        while (true) {
            int i10 = this.max_year;
            int i11 = this.min_year;
            if (i9 >= (i10 - i11) + 1) {
                break;
            }
            this.displayYearsGregorian[i9] = String.valueOf(i11 + i9);
            i9++;
        }
        this.displayMonthsGregorian = new DateFormatSymbols().getShortMonths();
        if (J()) {
            this.displayMonthsGregorian = new String[12];
            int i12 = 0;
            while (i12 < 12) {
                int i13 = i12 + 1;
                this.displayMonthsGregorian[i12] = String.valueOf(i13);
                i12 = i13;
            }
        }
        this.displayDaysGregorian = new String[31];
        while (i2 < 31) {
            int i14 = i2 + 1;
            this.displayDaysGregorian[i2] = String.valueOf(i14);
            i2 = i14;
        }
    }

    public static void setForce(boolean z) {
        force = z;
    }

    private void t(ChineseCalendar chineseCalendar, boolean z) {
        if (z) {
            int k2 = Util.k(chineseCalendar.get(1), chineseCalendar.get(2) + 1);
            E(this.picker_day, chineseCalendar.get(5), 1, k2, this.displayDaysGregorian);
        } else {
            int l2 = Util.l(chineseCalendar.get(ChineseCalendar.CHINESE_YEAR), chineseCalendar.get(ChineseCalendar.CHINESE_MONTH));
            E(this.picker_day, chineseCalendar.get(ChineseCalendar.CHINESE_DATE), 1, l2, this.displayDaysLunar);
        }
    }

    private void u(ChineseCalendar chineseCalendar, boolean z) {
        int a2;
        String[] e2;
        int i2 = 12;
        if (z) {
            a2 = chineseCalendar.get(2) + 1;
            e2 = this.displayMonthsGregorian;
        } else {
            int i3 = Util.i(chineseCalendar.get(ChineseCalendar.CHINESE_YEAR));
            if (i3 == 0) {
                a2 = chineseCalendar.get(ChineseCalendar.CHINESE_MONTH);
                e2 = this.displayMonthsLunar;
            } else {
                a2 = Util.a(chineseCalendar.get(ChineseCalendar.CHINESE_MONTH), i3);
                e2 = Util.e(i3);
                i2 = 13;
            }
        }
        E(this.picker_month, a2, 1, i2, e2);
    }

    private void v(ChineseCalendar chineseCalendar, boolean z) {
        if (z) {
            E(this.picker_year, chineseCalendar.get(1), this.min_year, this.max_year, this.displayYearsGregorian);
        } else {
            E(this.picker_year, chineseCalendar.get(ChineseCalendar.CHINESE_YEAR), this.min_year, this.max_year, this.displayYearsLunar);
        }
        this.picker_year.setWrapSelectorWheel(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        OnDateChangedListener onDateChangedListener = this.mOnDateChangedListener;
        if (onDateChangedListener != null) {
            if (this.mIsGregorian) {
                onDateChangedListener.a(this, this.picker_year.getValue(), this.picker_month.getValue() - 1, this.picker_day.getValue());
                return;
            }
            NumberPickerZTE numberPickerZTE = this.picker_year;
            if (numberPickerZTE == null || numberPickerZTE.getYearValue() == 0) {
                return;
            }
            this.mOnDateChangedListener.a(this, getYear(), getMonth() - 1, getDayOfMonth());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i2, int i3, int i4, int i5, boolean z) {
        int value = this.picker_day.getValue();
        int j2 = Util.j(i2, i4, z);
        int j3 = Util.j(i3, i5, z);
        if (j2 == j3) {
            return;
        }
        E(this.picker_day, value <= j3 ? value : j3, 1, j3, z ? this.displayDaysGregorian : this.displayDaysLunar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i2, int i3, boolean z) {
        int value = this.picker_month.getValue();
        int value2 = this.picker_day.getValue();
        if (z) {
            int j2 = Util.j(i2, value, true);
            int j3 = Util.j(i3, value, true);
            if (j2 == j3) {
                return;
            }
            E(this.picker_day, value2 <= j3 ? value2 : j3, 1, j3, this.displayDaysGregorian);
            return;
        }
        int i4 = Util.i(i3);
        int i5 = Util.i(i2);
        if (i4 == i5) {
            int b2 = Util.b(value, i5);
            int b3 = Util.b(value, i4);
            int l2 = Util.l(i2, b2);
            int l3 = Util.l(i3, b3);
            if (l2 == l3) {
                return;
            }
            E(this.picker_day, value2 <= l3 ? value2 : l3, 1, l3, this.displayDaysLunar);
            return;
        }
        this.currDisplayMonthsLunar = Util.e(i4);
        int a2 = Util.a(Math.abs(Util.b(value, i5)), i4);
        E(this.picker_month, a2, 1, i4 == 0 ? 12 : 13, this.currDisplayMonthsLunar);
        int j4 = Util.j(i2, value, false);
        int j5 = Util.j(i3, a2, false);
        if (j4 == j5) {
            return;
        }
        E(this.picker_day, value2 <= j5 ? value2 : j5, 1, j5, this.displayDaysLunar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
    
        if (r0 < java.lang.Math.abs(r10)) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void z(int r10, int r11) {
        /*
            r9 = this;
            com.zte.mifavor.widget.NumberPickerZTE r0 = r9.picker_month
            int r0 = r0.getValue()
            com.zte.mifavor.widget.NumberPickerZTE r1 = r9.picker_day
            int r1 = r1.getValue()
            com.zte.mifavor.widget.NumberPickerZTE r2 = r9.picker_year
            boolean r2 = r2.W(r11)
            if (r2 == 0) goto L5c
            int r2 = com.zte.mifavor.widget.Util.i(r10)
            if (r2 != 0) goto L20
        L1a:
            int r2 = r0 * 2
            int r2 = r2 + (-1)
        L1e:
            r5 = r2
            goto L39
        L20:
            int r3 = java.lang.Math.abs(r2)
            if (r0 > r3) goto L27
            goto L1a
        L27:
            int r2 = java.lang.Math.abs(r2)
            int r2 = r2 + 1
            if (r2 != r0) goto L34
            int r2 = r0 * 2
            int r2 = r2 + (-2)
            goto L1e
        L34:
            int r2 = r0 * 2
            int r2 = r2 + (-3)
            goto L1e
        L39:
            com.zte.mifavor.widget.NumberPickerZTE r4 = r9.picker_month
            r7 = 24
            java.lang.String[] r8 = r9.displayMonthsLunarNoYear
            r6 = 1
            r3 = r9
            r3.E(r4, r5, r6, r7, r8)
            com.zte.mifavor.widget.NumberPickerZTE r3 = r9.picker_day
            r6 = 30
            java.lang.String[] r7 = r9.displayDaysLunar
            r5 = 1
            r2 = r9
            r4 = r1
            r2.E(r3, r4, r5, r6, r7)
            int r2 = com.zte.extres.R.id.week
            android.view.View r2 = r9.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            r3 = 4
            r2.setVisibility(r3)
        L5c:
            com.zte.mifavor.widget.NumberPickerZTE r2 = r9.picker_year
            boolean r10 = r2.W(r10)
            if (r10 == 0) goto Lc8
            int r10 = com.zte.mifavor.widget.Util.i(r11)
            if (r10 != 0) goto L7c
            int r10 = r0 % 2
            if (r10 != 0) goto L71
            int r0 = r0 / 2
            goto L75
        L71:
            int r0 = r0 / 2
            int r0 = r0 + 1
        L75:
            java.lang.String[] r10 = r9.displayMonthsLunar
            r2 = 12
        L79:
            r8 = r10
            r7 = r2
            goto L9e
        L7c:
            int r2 = r0 % 2
            if (r2 != 0) goto L8c
            int r0 = r0 / 2
            int r2 = java.lang.Math.abs(r10)
            if (r0 >= r2) goto L89
            goto L97
        L89:
            int r0 = r0 + 1
            goto L97
        L8c:
            int r0 = r0 / 2
            int r2 = java.lang.Math.abs(r10)
            if (r0 >= r2) goto L95
            goto L89
        L95:
            int r0 = r0 + 2
        L97:
            java.lang.String[] r10 = com.zte.mifavor.widget.Util.e(r10)
            r2 = 13
            goto L79
        L9e:
            r6 = 1
            com.zte.mifavor.widget.NumberPickerZTE r4 = r9.picker_month
            r3 = r9
            r5 = r0
            r3.E(r4, r5, r6, r7, r8)
            r10 = 0
            int r6 = com.zte.mifavor.widget.Util.j(r11, r0, r10)
            r11 = 30
            if (r11 == r6) goto Lbd
            if (r1 > r6) goto Lb3
            r4 = r1
            goto Lb4
        Lb3:
            r4 = r6
        Lb4:
            com.zte.mifavor.widget.NumberPickerZTE r3 = r9.picker_day
            r5 = 1
            java.lang.String[] r7 = r9.displayDaysLunar
            r2 = r9
            r2.E(r3, r4, r5, r6, r7)
        Lbd:
            int r11 = com.zte.extres.R.id.week
            android.view.View r9 = r9.findViewById(r11)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r9.setVisibility(r10)
        Lc8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.GregorianLunarDateView.z(int, int):void");
    }

    public void D(NumberPickerZTE numberPickerZTE, int i2) {
        if (numberPickerZTE.getVisibility() == i2) {
            return;
        }
        if (i2 == 8 || i2 == 0 || i2 == 4) {
            numberPickerZTE.setVisibility(i2);
        }
    }

    public void F(int i2, int i3) {
        TextView textView = (TextView) findViewById(R.id.week);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, i2, layoutParams.rightMargin, i3);
        textView.setLayoutParams(layoutParams);
    }

    public void H(boolean z) {
        ChineseCalendar chineseCalendar = (ChineseCalendar) getCalendarData().a();
        if (!q(chineseCalendar, this.min_year, this.max_year, z)) {
            chineseCalendar = (ChineseCalendar) p(chineseCalendar, this.min_year, this.max_year, z);
        }
        this.mIsGregorian = z;
        this.mStateMachineEvent = true;
        this.mSwitch.setChecked(!z);
        this.mStateMachineEvent = false;
        s(chineseCalendar, z);
        I(chineseCalendar);
    }

    public void I(Calendar calendar) {
        String str;
        TextView textView = (TextView) findViewById(R.id.week);
        if (textView.getVisibility() == 0) {
            String formatDateTime = DateUtils.formatDateTime(getContext(), calendar.getTimeInMillis(), 2);
            int i2 = calendar.get(1);
            int i3 = calendar.get(2);
            int i4 = calendar.get(5);
            if (this.mIsNeedCustomSubTitle) {
                return;
            }
            SwitchZTE switchZTE = this.mSwitch;
            if (switchZTE == null || !switchZTE.isChecked()) {
                str = this.mPrefixSubTitle + i2 + getContext().getResources().getStringArray(R.array.year)[0] + (i3 + 1) + getContext().getResources().getStringArray(R.array.month)[0] + i4 + getContext().getResources().getStringArray(R.array.day)[0] + " " + formatDateTime;
            } else {
                str = this.mPrefixSubTitle + new ChineseCalendar(i2, i3, i4).r();
            }
            textView.setText(str);
        }
    }

    public CalendarData getCalendarData() {
        return new CalendarData(this.picker_year.getValue(), this.picker_month.getValue(), this.picker_day.getValue(), this.mIsGregorian);
    }

    public int getDayOfMonth() {
        return this.mCurrentDate.get(5);
    }

    public boolean getIsGregorian() {
        return this.mIsGregorian;
    }

    public int getMonth() {
        return this.mCurrentDate.get(2);
    }

    public View getNumberPickerDay() {
        return this.picker_day;
    }

    public View getNumberPickerMonth() {
        return this.picker_month;
    }

    public View getNumberPickerYear() {
        return this.picker_year;
    }

    public int getPickerDayOfMonth() {
        return this.picker_day.getValue();
    }

    public int getPickerMonth() {
        return this.picker_month.getValue();
    }

    public int getPickerYear() {
        return this.picker_year.getValue();
    }

    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    public void r(int i2, int i3, int i4, OnDateChangedListener onDateChangedListener) {
        this.mOnDateChangedListener = onDateChangedListener;
    }

    public void s(Calendar calendar, boolean z) {
        if (!q(calendar, this.min_year, this.max_year, z)) {
            calendar = p(calendar, this.min_year, this.max_year, z);
        }
        this.mIsGregorian = z;
        ChineseCalendar chineseCalendar = calendar instanceof ChineseCalendar ? (ChineseCalendar) calendar : new ChineseCalendar(calendar);
        B(chineseCalendar, this.mIsGregorian);
        if (!this.mIsGregorian) {
            this.mCurrentDate = chineseCalendar.e(this.picker_year.getValue(), Util.c(this.picker_month.getValue(), this.picker_year.getValue()), this.picker_day.getValue());
            return;
        }
        this.mCurrentDate.set(1, this.picker_year.getValue());
        this.mCurrentDate.set(2, this.picker_month.getValue() - 1);
        this.mCurrentDate.set(5, this.picker_day.getValue());
    }

    public void setColor(int i2) {
        this.picker_day.j0(this.mUPdownWhellPaintColor, i2);
        this.picker_month.j0(this.mUPdownWhellPaintColor, i2);
        this.picker_year.j0(this.mUPdownWhellPaintColor, i2);
    }

    public void setDisplayDayLunar(int i2) {
        this.displayDaysLunar = new String[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            this.displayDaysLunar[i3] = Util.f(i4);
            i3 = i4;
        }
    }

    public void setDisplayMonthsAndDaysLunarNoYear(Calendar calendar) {
        int i2;
        this.displayMonthsLunarNoYear = new String[MONTH_STOP_LUNAR_NOYEAR];
        int i3 = 0;
        while (i3 < MONTH_STOP_LUNAR_NOYEAR) {
            int i4 = i3 + 1;
            this.displayMonthsLunarNoYear[i3] = Util.h(i4);
            i3 = i4;
        }
        if (!q(calendar, this.min_year, this.max_year, false)) {
            calendar = p(calendar, this.min_year, this.max_year, false);
        }
        ChineseCalendar chineseCalendar = new ChineseCalendar(calendar);
        int i5 = Util.i(chineseCalendar.get(ChineseCalendar.CHINESE_YEAR));
        if (i5 == 0) {
            i2 = (chineseCalendar.get(ChineseCalendar.CHINESE_MONTH) * 2) - 1;
        } else {
            int a2 = Util.a(chineseCalendar.get(ChineseCalendar.CHINESE_MONTH), i5);
            i2 = a2 <= Math.abs(i5) ? (a2 * 2) - 1 : a2 == Math.abs(i5) + 1 ? (a2 * 2) - 2 : (a2 * 2) - 3;
        }
        E(this.picker_month, i2, 1, MONTH_STOP_LUNAR_NOYEAR, this.displayMonthsLunarNoYear);
        E(this.picker_day, chineseCalendar.get(ChineseCalendar.CHINESE_DATE), 1, 30, this.displayDaysLunar);
        ((TextView) findViewById(R.id.week)).setVisibility(4);
    }

    public void setDisplayMonthsLunar(int i2) {
        this.displayMonthsLunar = new String[i2];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            this.displayMonthsLunar[i3] = Util.g(i4);
            i3 = i4;
        }
    }

    public void setForceUpdate(boolean z) {
        setForce(z);
    }

    public void setGregorian(boolean z) {
        if (this.mIsGregorian != z && Locale.getDefault().getLanguage().equals("zh")) {
            H(z);
        }
    }

    public void setMaxYear(int i2) {
        this.max_year = i2;
    }

    public void setMinYear(int i2) {
        this.min_year = i2;
    }

    public void setNeedCustomSubTitle(boolean z) {
        this.mIsNeedCustomSubTitle = z;
        Log.d(TAG, "set Need Custom SubTitle custom" + z);
    }

    public void setNumberPickerDayVisibility(int i2) {
        D(this.picker_day, i2);
    }

    public void setNumberPickerMonthVisibility(int i2) {
        D(this.picker_month, i2);
    }

    public void setNumberPickerYearVisibility(int i2) {
        D(this.picker_year, i2);
    }

    public void setPrefixSubtitle(String str) {
        this.mPrefixSubTitle = str + " ";
        setWeekVisibility(true);
    }

    public void setSubTitle(String str) {
        TextView textView = (TextView) findViewById(R.id.week);
        if (textView == null || textView.getVisibility() != 0) {
            return;
        }
        textView.setText(str);
    }

    public void setSwitchShown(boolean z) {
        if (Locale.getDefault().getLanguage().equals("zh")) {
            this.mLunarChoice.setVisibility(z ? 0 : 8);
            this.mTopSwitchLine.setVisibility(z ? 0 : 8);
            this.mBottoSwitchLine.setVisibility(z ? 0 : 8);
        } else {
            this.mLunarChoice.setVisibility(8);
            this.mTopSwitchLine.setVisibility(8);
            this.mBottoSwitchLine.setVisibility(8);
        }
    }

    public void setWeekVisibility(boolean z) {
        ((TextView) findViewById(R.id.week)).setVisibility(z ? 0 : 8);
        I(this.mCurrentDate);
    }

    public GregorianLunarDateView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.min_year = YEAR_START;
        this.max_year = YEAR_STOP;
        this.mSwitch = null;
        this.mTopSwitchLine = null;
        this.mBottoSwitchLine = null;
        this.mIsGregorian = true;
        this.mStateMachineEvent = false;
        this.mSelectorWhellPaintColor = 36563;
        this.mUPdownWhellPaintColor = -1979711488;
        this.mEvenWhellPaintColor = 1107296256;
        this.mTransparentPaintColor = 16777215;
        this.mActiveTextSize = 20;
        this.mInactiveTextSize = 16;
        this.mDateTextSize = 12;
        this.mIsNeedCustomSubTitle = false;
        this.mPrefixSubTitle = "";
        this.mContentView = LinearLayout.inflate(context, R.layout.view_gregorian_lunar_date, this);
        NumberPickerZTE.OnValueChangeListener onValueChangeListener = new NumberPickerZTE.OnValueChangeListener() { // from class: com.zte.mifavor.widget.GregorianLunarDateView.1
            @Override // com.zte.mifavor.widget.NumberPickerZTE.OnValueChangeListener
            public void a(NumberPickerZTE numberPickerZTE, int i3, int i4) {
                GregorianLunarDateView.this.G();
                if (numberPickerZTE == GregorianLunarDateView.this.picker_year) {
                    if (GregorianLunarDateView.this.mIsGregorian || !(GregorianLunarDateView.this.picker_year.W(i3) || GregorianLunarDateView.this.picker_year.W(i4))) {
                        GregorianLunarDateView gregorianLunarDateView = GregorianLunarDateView.this;
                        gregorianLunarDateView.y(i3, i4, gregorianLunarDateView.mIsGregorian);
                    } else {
                        GregorianLunarDateView.this.z(i3, i4);
                    }
                } else if (numberPickerZTE == GregorianLunarDateView.this.picker_month) {
                    if (GregorianLunarDateView.this.mIsGregorian || GregorianLunarDateView.this.picker_year.getYearValue() != 0) {
                        int value = GregorianLunarDateView.this.picker_year.getValue();
                        GregorianLunarDateView gregorianLunarDateView2 = GregorianLunarDateView.this;
                        gregorianLunarDateView2.x(value, value, i3, i4, gregorianLunarDateView2.mIsGregorian);
                    } else {
                        GregorianLunarDateView gregorianLunarDateView3 = GregorianLunarDateView.this;
                        gregorianLunarDateView3.E(gregorianLunarDateView3.picker_day, GregorianLunarDateView.this.picker_day.getValue(), 1, 30, GregorianLunarDateView.this.displayDaysLunar);
                    }
                } else if (numberPickerZTE != GregorianLunarDateView.this.picker_day) {
                    throw new IllegalArgumentException();
                }
                if (GregorianLunarDateView.this.mIsGregorian) {
                    GregorianLunarDateView.this.mCurrentDate.set(1, GregorianLunarDateView.this.picker_year.getValue());
                    GregorianLunarDateView.this.mCurrentDate.set(2, GregorianLunarDateView.this.picker_month.getValue() - 1);
                    GregorianLunarDateView.this.mCurrentDate.set(5, GregorianLunarDateView.this.picker_day.getValue());
                } else if (GregorianLunarDateView.this.picker_year.getYearValue() != 0) {
                    ChineseCalendar chineseCalendar = new ChineseCalendar(GregorianLunarDateView.this.mCurrentDate);
                    int c2 = Util.c(GregorianLunarDateView.this.picker_month.getValue(), GregorianLunarDateView.this.picker_year.getValue());
                    GregorianLunarDateView gregorianLunarDateView4 = GregorianLunarDateView.this;
                    gregorianLunarDateView4.mCurrentDate = chineseCalendar.e(gregorianLunarDateView4.picker_year.getValue(), c2, GregorianLunarDateView.this.picker_day.getValue());
                }
                GregorianLunarDateView gregorianLunarDateView5 = GregorianLunarDateView.this;
                gregorianLunarDateView5.I(gregorianLunarDateView5.mCurrentDate);
                GregorianLunarDateView.this.w();
            }
        };
        Calendar calendar = Calendar.getInstance();
        this.mCurrentDate = calendar;
        calendar.setTimeInMillis(System.currentTimeMillis());
        r(this.mCurrentDate.get(1), this.mCurrentDate.get(2), this.mCurrentDate.get(5), null);
        Settings.System.getFloat(getContext().getContentResolver(), "font_scale", new Configuration().fontScale);
        NumberPickerZTE numberPickerZTE = (NumberPickerZTE) this.mContentView.findViewById(R.id.picker_year);
        this.picker_year = numberPickerZTE;
        numberPickerZTE.setInputSize(this.mActiveTextSize);
        this.picker_year.setSelectorSize(this.mInactiveTextSize);
        this.picker_year.setOnValueChangedListener(onValueChangeListener);
        this.picker_year_input = (EditText) this.picker_year.findViewById(R.id.numberpicker_input);
        NumberPickerZTE numberPickerZTE2 = (NumberPickerZTE) this.mContentView.findViewById(R.id.picker_month);
        this.picker_month = numberPickerZTE2;
        numberPickerZTE2.setInputSize(this.mActiveTextSize);
        this.picker_month.setSelectorSize(this.mInactiveTextSize);
        this.picker_month.setOnValueChangedListener(onValueChangeListener);
        this.picker_month_input = (EditText) this.picker_month.findViewById(R.id.numberpicker_input);
        NumberPickerZTE numberPickerZTE3 = (NumberPickerZTE) this.mContentView.findViewById(R.id.picker_day);
        this.picker_day = numberPickerZTE3;
        numberPickerZTE3.setInputSize(this.mActiveTextSize);
        this.picker_day.setSelectorSize(this.mInactiveTextSize);
        this.picker_day.setOnValueChangedListener(onValueChangeListener);
        this.picker_day_input = (EditText) this.picker_day.findViewById(R.id.numberpicker_input);
        this.mSpinners = (LinearLayout) findViewById(R.id.pickers);
        A();
        this.mSwitch = (SwitchZTE) findViewById(R.id.switch1);
        this.mTopSwitchLine = findViewById(R.id.topswitch_line);
        this.mBottoSwitchLine = findViewById(R.id.bottomswitch_line);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lunar_choice);
        this.mLunarChoice = relativeLayout;
        if (Locale.getDefault().getLanguage().equals("zh")) {
            this.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.zte.mifavor.widget.GregorianLunarDateView.2
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (GregorianLunarDateView.this.mStateMachineEvent) {
                        return;
                    }
                    GregorianLunarDateView.this.mLunarChoice.setVisibility(0);
                    GregorianLunarDateView.this.setGregorian(!z);
                }
            });
            this.mSwitch.setChecked(false);
        } else {
            relativeLayout.setVisibility(8);
            this.mTopSwitchLine.setVisibility(8);
            this.mBottoSwitchLine.setVisibility(8);
            setGregorian(true);
        }
        this.mSelectorWhellPaintColor = context.getResources().getColor(R.color.mfv_common_date_time_txt_fc);
        this.mUPdownWhellPaintColor = getResources().getColor(R.color.mfv_common_pop_secondary_txt);
        this.mEvenWhellPaintColor = getResources().getColor(R.color.mfv_common_tf_txt_watermark);
        setColor(this.mSelectorWhellPaintColor);
    }
}
