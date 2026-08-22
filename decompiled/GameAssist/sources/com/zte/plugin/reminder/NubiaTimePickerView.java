package com.zte.plugin.reminder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.reminder.R;
import com.zte.plugin.reminder.WheelView;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class NubiaTimePickerView extends FrameLayout {
    private static final int DEFAULT_END_YEAR = 2037;
    private static final int DEFAULT_START_YEAR = 1970;
    private static final int MAX_MONTH = 12;
    private static final int MONTH_APR = 4;
    private static final int MONTH_FEB = 2;
    private static final int MONTH_JUN = 6;
    private static final int MONTH_NOV = 11;
    private static final int MONTH_SEP = 9;
    private String DAY;
    private String MONTH;
    private String[] WEEKDAYS_SHORT;
    private int mDay;
    private int mHour;
    private WheelView mHourView;
    private Bitmap mMiddleZoneBg;
    private int mMinute;
    private WheelView mMinuteView;
    private int mMonth;
    private String mMonthDay;
    private WheelView mMonthDayView;
    private OnTimeChangeListener mOnTimeChangeListener;
    private Paint mPaint;
    private int mWeekDay;
    private int mYear;

    public interface OnTimeChangeListener {
        void a();
    }

    public NubiaTimePickerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.WEEKDAYS_SHORT = new String[7];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i2, int i3) {
        String str = this.mMonthDayView.getDisplayedValues()[i2];
        String str2 = this.mMonthDayView.getDisplayedValues()[i3];
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        int indexOf = str.indexOf(this.MONTH);
        int indexOf2 = str.indexOf(this.DAY);
        if (indexOf < 2 || indexOf2 < 2) {
            return;
        }
        int parseInt = Integer.parseInt(str.substring(0, indexOf));
        int parseInt2 = Integer.parseInt(str.substring(indexOf2 - 2, indexOf2));
        int indexOf3 = str2.indexOf(this.MONTH);
        int indexOf4 = str2.indexOf(this.DAY);
        if (indexOf3 < 2 || indexOf4 < 2) {
            return;
        }
        int parseInt3 = Integer.parseInt(str2.substring(0, indexOf3));
        int parseInt4 = Integer.parseInt(str2.substring(indexOf4 - 2, indexOf4));
        if (parseInt == 12 && parseInt2 == 31 && parseInt3 == 1 && parseInt4 == 1) {
            this.mYear++;
        }
        if (parseInt == 1 && parseInt2 == 1 && parseInt3 == 12 && parseInt4 == 31) {
            this.mYear--;
        }
    }

    private boolean k(int i2) {
        return (i2 % 4 == 0 && i2 % 100 != 0) || i2 % 400 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        OnTimeChangeListener onTimeChangeListener = this.mOnTimeChangeListener;
        if (onTimeChangeListener != null) {
            onTimeChangeListener.a();
        }
    }

    private void m(int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        String str = n(calendar.get(2) + 1) + "/" + n(calendar.get(5));
        String[] o2 = o(i2);
        String[] strArr = new String[30];
        int i5 = 0;
        while (true) {
            if (i5 >= o2.length) {
                break;
            }
            if (str.equals(o2[i5])) {
                for (int i6 = 0; i6 < 30; i6++) {
                    strArr[i6] = o2[i5 % o2.length];
                    i5++;
                }
            } else {
                i5++;
            }
        }
        s(strArr);
        r(i3, i4);
        l();
    }

    private String n(int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i2 < 10 ? "0" : "");
        sb.append(i2);
        return sb.toString();
    }

    private String[] o(int i2) {
        ArrayList arrayList = new ArrayList();
        boolean k2 = k(i2);
        int i3 = 1;
        while (i3 <= 12) {
            int i4 = i3 == 2 ? k2 ? 29 : 28 : (i3 == 4 || i3 == 6 || i3 == 9 || i3 == 11) ? 30 : 31;
            for (int i5 = 1; i5 <= i4; i5++) {
                StringBuilder sb = new StringBuilder();
                sb.append(i3 < 10 ? "0" + i3 : Integer.valueOf(i3));
                sb.append("/");
                sb.append(i5 < 10 ? "0" + i5 : Integer.valueOf(i5));
                arrayList.add(sb.toString());
            }
            i3++;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private void r(int i2, int i3) {
        if (i2 != 0 || i3 >= 30) {
            this.mMonthDayView.setValue(0);
            setCurrentDate(this.mMonthDayView.getDisplayedValues()[0]);
        } else {
            this.mMonthDayView.setValue(1);
            setCurrentDate(this.mMonthDayView.getDisplayedValues()[1]);
        }
    }

    private void s(String[] strArr) {
        this.mMonthDayView.setDisplayedValues(null);
        this.mMonthDayView.setMinValue(0);
        this.mMonthDayView.setMaxValue(strArr.length - 1);
        this.mMonthDayView.setWrapSelectorWheel(false);
        this.mMonthDayView.setDisplayedValues(strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentDate(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mMonthDay = str;
        int indexOf = str.indexOf("/");
        if (indexOf < 2) {
            return;
        }
        this.mMonth = Integer.parseInt(this.mMonthDay.substring(0, indexOf));
        String str2 = this.mMonthDay;
        int parseInt = Integer.parseInt(str2.substring(indexOf + 1, str2.length()));
        this.mDay = parseInt;
        if (this.mMonth < 1 || parseInt < 1) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mYear, this.mMonth - 1, this.mDay);
        setCurrentWeek(calendar.get(7));
    }

    public Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mYear, this.mMonth, this.mDay, this.mHour, this.mMinute);
        return calendar;
    }

    public long getChoosedDateTimeLong() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mYear, this.mMonth - 1, this.mDay, this.mHour, this.mMinute, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public String getChoosedDateTimeString() {
        return this.mYear + "/" + this.mMonthDay + " " + this.WEEKDAYS_SHORT[this.mWeekDay - 1] + " " + n(this.mHour) + ":" + n(this.mMinute);
    }

    public final int getCurrentHour() {
        return this.mHourView.getValue();
    }

    public final int getCurrentMinute() {
        return this.mMinuteView.getValue();
    }

    public int getCurrentYear() {
        return this.mYear;
    }

    public int getMonthDay() {
        WheelView wheelView = this.mMonthDayView;
        if (wheelView != null) {
            return wheelView.getValue();
        }
        return 0;
    }

    public WheelView getMonthDayView() {
        return this.mMonthDayView;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        WheelView wheelView = this.mMinuteView;
        if (wheelView == null || this.mMiddleZoneBg == null) {
            return;
        }
        canvas.drawBitmap(this.mMiddleZoneBg, new Rect(0, 0, this.mMiddleZoneBg.getWidth(), this.mMiddleZoneBg.getHeight()), new Rect(0, wheelView.getMiddleTop(), getRight(), this.mMinuteView.getMiddleBottom() - 12), this.mPaint);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SaveState saveState = (SaveState) parcelable;
        super.onRestoreInstanceState(saveState.getSuperState());
        setCurrentHour(Integer.valueOf(saveState.f18077c));
        setCurrentMinute(Integer.valueOf(saveState.f18078h));
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return new SaveState(super.onSaveInstanceState(), getCurrentHour(), getCurrentMinute());
    }

    public void p() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + 1800000);
        int i2 = calendar.get(1);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        setCurrentYear(i2);
        setCurrentHour(Integer.valueOf(i3));
        setCurrentMinute(Integer.valueOf(i4));
        m(i2, i3, i4);
    }

    public void q(Calendar calendar) {
        int i2 = calendar.get(1);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        setCurrentYear(i2);
        setCurrentHour(Integer.valueOf(i3));
        setCurrentMinute(Integer.valueOf(i4));
        m(i2, i3, i4);
    }

    public void setCurrentHour(Integer num) {
        if (num == null && num.intValue() == getCurrentHour()) {
            return;
        }
        this.mHourView.setValue(num.intValue());
        this.mHour = num.intValue();
    }

    public void setCurrentMinute(Integer num) {
        if (num == null && num.intValue() == getCurrentMinute()) {
            return;
        }
        this.mMinuteView.setValue(num.intValue());
        this.mMinute = num.intValue();
    }

    public void setCurrentWeek(int i2) {
        this.mWeekDay = i2;
    }

    public void setCurrentYear(int i2) {
        this.mYear = i2;
    }

    public void setMonthDay(int i2) {
        WheelView wheelView = this.mMonthDayView;
        if (wheelView != null) {
            wheelView.setValue(i2);
        }
        setCurrentDate(this.mMonthDayView.getDisplayedValues()[i2]);
    }

    public final void setOnTimeChangedListener(OnTimeChangeListener onTimeChangeListener) {
        this.mOnTimeChangeListener = onTimeChangeListener;
    }

    protected static class SaveState extends View.BaseSavedState {
        public static final Parcelable.Creator<SaveState> CREATOR = new Parcelable.Creator<SaveState>() { // from class: com.zte.plugin.reminder.NubiaTimePickerView.SaveState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SaveState createFromParcel(Parcel parcel) {
                return new SaveState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SaveState[] newArray(int i2) {
                return new SaveState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        final int f18077c;

        /* renamed from: h, reason: collision with root package name */
        final int f18078h;

        SaveState(Parcel parcel) {
            super(parcel);
            this.f18077c = parcel.readInt();
            this.f18078h = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f18077c);
            parcel.writeInt(this.f18078h);
        }

        SaveState(Parcelable parcelable, int i2, int i3) {
            super(parcelable);
            this.f18077c = i2;
            this.f18078h = i3;
        }
    }

    public NubiaTimePickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.WEEKDAYS_SHORT = new String[7];
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mMiddleZoneBg = ((BitmapDrawable) context.getDrawable(R.drawable.game_reminder_wheelview_middle_zone_bg)).getBitmap();
        InflaterHelper.g(R.layout.game_reminder_time_picker, this, true);
        setBackgroundColor(0);
        WheelView wheelView = (WheelView) findViewById(R.id.month_day_spinner);
        this.mMonthDayView = wheelView;
        wheelView.setMinValue(1);
        this.mMonthDayView.setWrapSelectorWheel(false);
        this.mMonthDayView.setOnValueChangedListener(new WheelView.OnValueChangeListener() { // from class: com.zte.plugin.reminder.NubiaTimePickerView.1
            @Override // com.zte.plugin.reminder.WheelView.OnValueChangeListener
            public void a(WheelView wheelView2, int i2, int i3) {
                NubiaTimePickerView.this.j(i2, i3);
                if (NubiaTimePickerView.this.mYear > NubiaTimePickerView.DEFAULT_END_YEAR) {
                    NubiaTimePickerView.this.mYear = NubiaTimePickerView.DEFAULT_END_YEAR;
                }
                if (NubiaTimePickerView.this.mYear < NubiaTimePickerView.DEFAULT_START_YEAR) {
                    NubiaTimePickerView.this.mYear = NubiaTimePickerView.DEFAULT_START_YEAR;
                }
                NubiaTimePickerView nubiaTimePickerView = NubiaTimePickerView.this;
                nubiaTimePickerView.setCurrentDate(nubiaTimePickerView.mMonthDayView.getDisplayedValues()[i3]);
                NubiaTimePickerView.this.l();
            }
        });
        WheelView wheelView2 = (WheelView) findViewById(R.id.hour_spinner);
        this.mHourView = wheelView2;
        wheelView2.setMinValue(0);
        this.mHourView.setMaxValue(23);
        this.mHourView.setFormatter(WheelView.getTwoDigitFormatter());
        this.mHourView.setOnValueChangedListener(new WheelView.OnValueChangeListener() { // from class: com.zte.plugin.reminder.NubiaTimePickerView.2
            @Override // com.zte.plugin.reminder.WheelView.OnValueChangeListener
            public void a(WheelView wheelView3, int i2, int i3) {
                NubiaTimePickerView.this.mHourView.setValue(i3);
                NubiaTimePickerView.this.mHour = i3;
                NubiaTimePickerView.this.l();
            }
        });
        WheelView wheelView3 = (WheelView) findViewById(R.id.minute_spinner);
        this.mMinuteView = wheelView3;
        wheelView3.setMinValue(0);
        this.mMinuteView.setMaxValue(59);
        this.mMinuteView.setFormatter(WheelView.getTwoDigitFormatter());
        this.mMinuteView.setOnValueChangedListener(new WheelView.OnValueChangeListener() { // from class: com.zte.plugin.reminder.NubiaTimePickerView.3
            @Override // com.zte.plugin.reminder.WheelView.OnValueChangeListener
            public void a(WheelView wheelView4, int i2, int i3) {
                NubiaTimePickerView.this.mMinute = i3;
                NubiaTimePickerView.this.l();
            }
        });
        this.MONTH = context.getResources().getString(R.string.nubia_date_month);
        this.DAY = context.getResources().getString(R.string.nubia_date_day);
        String[] stringArray = getContext().getResources().getStringArray(R.array.nubia_weeks_short);
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            this.WEEKDAYS_SHORT[i2] = stringArray[i2];
        }
    }
}
