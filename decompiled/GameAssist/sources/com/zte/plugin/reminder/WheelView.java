package com.zte.plugin.reminder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import com.google.android.gms.common.api.Api;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.reminder.R;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes2.dex */
public class WheelView extends View {
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 4;
    private static final int SELECTOR_MIDDLE_ITEM_INDEX = 3;
    private static final int SELECTOR_WHEEL_ITEM_COUNT = 7;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    private final int EDGE_ALPHA;
    private final int ENHANCED_ALPHA;
    private final int MIDDLE_ALPHA;
    private final int mAdjustDrawPos;
    private final Scroller mAdjustScroller;
    private int mCurrentAlpha;
    private float mCurrentLocationX;
    private float mCurrentLocationY;
    private int mCurrentScrollOffset;
    private String[] mDisplayedValues;
    private Scroller mFlingScroller;
    private Formatter mFormatter;
    private int mInitialScrollOffset;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private Paint mMaskPaint;
    private int mMaxValue;
    private int mMaximumFlingVelocity;
    private int mMiddleBottom;
    private int mMiddleTop;
    private float mMiddleY;
    private Bitmap mMiddleZoneBg;
    private int mMinValue;
    private int mMinimumFlingVelocity;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private int mPreviousScrollerY;
    private int mScrollState;
    private Paint mSelectedWheelPaint;
    private int mSelectorElementHeight;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private final int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private int mSelectorTextGapHeightNotWrap;
    private Rect mTextBound;
    private Paint mTextPaint;
    private Rect mTextShowRect;
    private int mTextSize;
    private float[] mTextsLocation;
    private float[] mTextsScaleX;
    private float[] mTextsSize;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private boolean mWrapSelectorWheel;

    public interface Formatter {
        String a(int i2);
    }

    public interface OnScrollListener {

        public @interface ScrollState {
        }

        void a(WheelView wheelView, int i2);
    }

    public interface OnValueChangeListener {
        void a(WheelView wheelView, int i2, int i3);
    }

    protected static class TwoDigitFormatter implements Formatter {

        /* renamed from: b, reason: collision with root package name */
        char f18080b;

        /* renamed from: c, reason: collision with root package name */
        java.util.Formatter f18081c;

        /* renamed from: a, reason: collision with root package name */
        final StringBuilder f18079a = new StringBuilder();

        /* renamed from: d, reason: collision with root package name */
        final Object[] f18082d = new Object[1];

        TwoDigitFormatter() {
            d(Locale.getDefault());
        }

        private java.util.Formatter b(Locale locale) {
            return new java.util.Formatter(this.f18079a, locale);
        }

        private static char c(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        private void d(Locale locale) {
            this.f18081c = b(locale);
            this.f18080b = c(locale);
        }

        @Override // com.zte.plugin.reminder.WheelView.Formatter
        public String a(int i2) {
            Locale locale = Locale.getDefault();
            if (this.f18080b != c(locale)) {
                d(locale);
            }
            this.f18082d[0] = Integer.valueOf(i2);
            StringBuilder sb = this.f18079a;
            sb.delete(0, sb.length());
            this.f18081c.format("%02d", this.f18082d);
            return this.f18081c.toString();
        }
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSelectedWheelPaint = new Paint();
        this.ENHANCED_ALPHA = 225;
        this.EDGE_ALPHA = 0;
        this.MIDDLE_ALPHA = 150;
        this.mTextBound = new Rect();
        this.mTextShowRect = new Rect();
        this.mMaskPaint = new Paint();
        this.mSelectorIndices = new int[7];
        this.mSelectorIndexToStringCache = new SparseArray<>();
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.mTextsScaleX = new float[7];
        this.mTextsSize = new float[7];
        this.mTextsLocation = new float[7];
        this.mFlingScroller = new Scroller(getContext(), null, true);
        this.mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(8.0f));
        Paint paint = new Paint();
        this.mTextPaint = paint;
        paint.setColor(-16776961);
        int color = getContext().getResources().getColor(R.color.game_reminder_wheelview_text_color);
        this.mMaskPaint.setAntiAlias(true);
        this.mMaskPaint.setDither(true);
        this.mMaskPaint.setStyle(Paint.Style.FILL);
        this.mMiddleZoneBg = ((BitmapDrawable) context.getDrawable(R.drawable.game_reminder_wheelview_middle_zone_bg)).getBitmap();
        this.mSelectedWheelPaint.setColor(color);
        this.mSelectedWheelPaint.setAntiAlias(true);
        this.mSelectedWheelPaint.setTextAlign(Paint.Align.LEFT);
        if (ZteFeature.isTabletProduct()) {
            this.mTextSize = getResources().getDimensionPixelSize(R.dimen.game_reminder_wheel_tablet_text_size);
        } else {
            this.mTextSize = getResources().getDimensionPixelSize(R.dimen.game_reminder_wheel_text_size);
        }
        this.mAdjustDrawPos = getContext().getResources().getDimensionPixelOffset(R.dimen.nubia_wheel_adjust_pos);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 4;
    }

    private void a(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.mMinValue) {
            i2 = this.mMaxValue;
        }
        iArr[0] = i2;
        b(i2);
    }

    private void b(int i2) {
        String str;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        if (sparseArray.get(i2) != null) {
            return;
        }
        int i3 = this.mMinValue;
        if (i2 < i3 || i2 > this.mMaxValue) {
            str = "";
        } else {
            String[] strArr = this.mDisplayedValues;
            str = strArr != null ? strArr[i2 - i3] : e(i2);
        }
        sparseArray.put(i2, str);
    }

    private boolean c() {
        int i2 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if (i2 == 0) {
            return false;
        }
        this.mPreviousScrollerY = 0;
        int abs = Math.abs(i2);
        int i3 = this.mSelectorElementHeight;
        if (abs > i3 / 2) {
            if (i2 > 0) {
                i3 = -i3;
            }
            i2 += i3;
        }
        this.mAdjustScroller.startScroll(0, 0, 0, i2, 300);
        invalidate();
        return true;
    }

    private void d(int i2) {
        this.mPreviousScrollerY = 0;
        if (i2 > 0) {
            this.mFlingScroller.fling(0, 0, 0, i2, 0, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        } else {
            this.mFlingScroller.fling(0, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, i2, 0, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        }
        invalidate();
    }

    private String e(int i2) {
        Formatter formatter = this.mFormatter;
        return formatter != null ? formatter.a(i2) : Integer.toString(i2);
    }

    private int f(int i2) {
        int i3 = this.mMaxValue;
        if (i2 > i3) {
            int i4 = this.mMinValue;
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        int i5 = this.mMinValue;
        return i2 < i5 ? (i3 - ((i5 - i2) % (i3 - i5))) + 1 : i2;
    }

    private void g(int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length - 1) {
            int i3 = i2 + 1;
            iArr[i2] = iArr[i3];
            i2 = i3;
        }
        int i4 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i4 > this.mMaxValue) {
            i4 = this.mMinValue;
        }
        iArr[iArr.length - 1] = i4;
        b(i4);
    }

    public static final Formatter getTwoDigitFormatter() {
        return sTwoDigitFormatter;
    }

    private void h() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) / 2);
    }

    private void i() {
        j();
        int[] iArr = this.mSelectorIndices;
        float bottom = (getBottom() - getTop()) - (iArr.length * this.mTextSize);
        float length = iArr.length;
        this.mSelectorTextGapHeight = (int) ((bottom / length) + 0.5f);
        this.mSelectorTextGapHeightNotWrap = (int) ((((getBottom() - getTop()) - (this.mTextSize * 5)) / 5.0f) + 0.5f);
        this.mSelectorElementHeight = this.mTextSize + this.mSelectorTextGapHeight;
        int bottom2 = (int) ((getBottom() - getTop()) / (length * 2.0f));
        this.mInitialScrollOffset = bottom2;
        this.mCurrentScrollOffset = bottom2;
        this.mMiddleTop = ((getBottom() - getTop()) / 2) - this.mSelectorElementHeight;
        this.mMiddleBottom = ((getBottom() - getTop()) / 2) + this.mSelectorElementHeight;
        getDrawTextAttri();
        this.mMiddleY = (this.mMiddleTop + this.mMiddleBottom) / 2;
    }

    private void j() {
        this.mSelectorIndexToStringCache.clear();
        int[] iArr = this.mSelectorIndices;
        int value = getValue();
        for (int i2 = 0; i2 < this.mSelectorIndices.length; i2++) {
            int i3 = (i2 - 3) + value;
            if (this.mWrapSelectorWheel) {
                i3 = f(i3);
            }
            iArr[i2] = i3;
            b(i3);
        }
    }

    private void k(int i2, int i3) {
        OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.a(this, i2, this.mValue);
        }
    }

    private void l(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.a(this, i2);
        }
    }

    private void m(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            c();
            l(0);
        }
    }

    private void n(int i2, boolean z) {
        if (this.mValue == i2) {
            return;
        }
        int f2 = this.mWrapSelectorWheel ? f(i2) : Math.min(Math.max(i2, this.mMinValue), this.mMaxValue);
        int i3 = this.mValue;
        this.mValue = f2;
        if (z) {
            k(i3, f2);
        }
        j();
        invalidate();
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mFlingScroller;
        if (scroller.isFinished()) {
            scroller = this.mAdjustScroller;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.mPreviousScrollerY == 0) {
            this.mPreviousScrollerY = scroller.getStartY();
        }
        scrollBy(0, currY - this.mPreviousScrollerY);
        this.mPreviousScrollerY = currY;
        if (scroller.isFinished()) {
            m(scroller);
        } else {
            invalidate();
        }
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public void getDrawTextAttri() {
        double d2 = 1.5707963267948966d;
        float f2 = (float) (1.5707963267948966d - 1.5707964f);
        int i2 = this.mSelectorElementHeight;
        float f3 = i2 * 6;
        float f4 = i2 * 3;
        int[] iArr = this.mSelectorIndices;
        float f5 = this.mCurrentScrollOffset;
        int i3 = 0;
        while (i3 < iArr.length) {
            double d3 = ((3.1415927f * (f5 - this.mInitialScrollOffset)) / f3) + f2;
            this.mTextsSize[i3] = (float) (this.mTextSize * Math.sin(d3));
            float[] fArr = this.mTextsSize;
            if (fArr[i3] < 0.0f) {
                fArr[i3] = 0.0f;
            }
            this.mTextsLocation[i3] = (float) ((((this.mInitialScrollOffset + f4) + (Math.sin(d3 - d2) * f4)) + (this.mTextsSize[i3] / 2.0f)) - this.mAdjustDrawPos);
            this.mTextsScaleX[i3] = ((float) ((Math.sin(d3) * 0.2d) + 0.8d)) * (this.mTextSize / this.mTextsSize[i3]);
            f5 += this.mSelectorElementHeight;
            i3++;
            d2 = 1.5707963267948966d;
        }
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMiddleBottom() {
        return this.mMiddleBottom;
    }

    public int getMiddleTop() {
        return this.mMiddleTop;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getValue() {
        return this.mValue;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int[] iArr = this.mSelectorIndices;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            this.mSelectedWheelPaint.setTextSize(this.mTextSize);
            float f2 = this.mTextsLocation[i2];
            this.mCurrentLocationY = f2;
            int abs = 150 - ((int) ((Math.abs(f2 - this.mMiddleY) * 300.0f) / getHeight()));
            this.mCurrentAlpha = abs;
            this.mSelectedWheelPaint.setAlpha(abs);
            String str = this.mSelectorIndexToStringCache.get(i3);
            this.mSelectedWheelPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
            canvas.save();
            canvas.scale(1.0f, 1.0f / this.mTextsScaleX[i2]);
            float right = ((getRight() - getLeft()) / 2) - (this.mTextBound.width() / 2);
            this.mCurrentLocationX = right;
            float f3 = this.mTextsLocation[i2];
            float f4 = this.mTextsScaleX[i2];
            float f5 = f3 * f4;
            this.mCurrentLocationY = f5;
            Rect rect = this.mTextBound;
            int i4 = (int) (rect.left + right);
            int i5 = (int) ((rect.top * f4) + f5);
            int i6 = (int) (rect.right + right);
            int i7 = (int) ((rect.bottom * f4) + f5);
            if (i5 > i7) {
                i5 = i7;
                i7 = i5;
            }
            Rect rect2 = this.mTextShowRect;
            rect2.left = i4;
            rect2.top = i5;
            rect2.right = i6;
            rect2.bottom = i7;
            canvas.drawText(str, right, f5, this.mSelectedWheelPaint);
            float f6 = this.mTextShowRect.bottom;
            int i8 = this.mMiddleTop;
            float f7 = this.mTextsScaleX[i2];
            if (f6 > i8 * f7 && r4.top < this.mMiddleBottom * f7) {
                canvas.clipRect(0.0f, (i8 * f7) + 1.0f, getWidth(), this.mMiddleBottom * this.mTextsScaleX[i2]);
                if (this.mMiddleZoneBg != null) {
                    canvas.drawBitmap(this.mMiddleZoneBg, new Rect(0, 0, this.mMiddleZoneBg.getWidth(), this.mMiddleZoneBg.getHeight()), this.mTextShowRect, this.mMaskPaint);
                }
                this.mSelectedWheelPaint.setAlpha(225);
                this.mSelectedWheelPaint.setTextSize(this.mTextSize + 3);
                canvas.drawText(TextUtils.ellipsize(str, new TextPaint(this.mSelectedWheelPaint), getWidth() - 15, TextUtils.TruncateAt.END).toString(), this.mCurrentLocationX, this.mCurrentLocationY, this.mSelectedWheelPaint);
            }
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            i();
            h();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(getContext().getResources().getDimensionPixelSize(R.dimen.game_reminder_wheel_hight), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            float y = motionEvent.getY();
            this.mLastDownEventY = y;
            this.mLastDownOrMoveEventY = y;
            this.mLastDownEventTime = motionEvent.getEventTime();
            getParent().requestDisallowInterceptTouchEvent(true);
            if (!this.mFlingScroller.isFinished()) {
                this.mFlingScroller.forceFinished(true);
                this.mAdjustScroller.forceFinished(true);
                l(0);
            } else if (!this.mAdjustScroller.isFinished()) {
                this.mFlingScroller.forceFinished(true);
                this.mAdjustScroller.forceFinished(true);
            }
        } else if (action == 1) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
            int yVelocity = (int) velocityTracker.getYVelocity();
            if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                d(yVelocity);
            } else {
                c();
                l(0);
            }
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        } else if (action == 2) {
            float y2 = motionEvent.getY();
            if (this.mScrollState == 1) {
                scrollBy(0, (int) (y2 - this.mLastDownOrMoveEventY));
                invalidate();
            } else if (((int) Math.abs(y2 - this.mLastDownEventY)) > this.mTouchSlop) {
                l(1);
            }
            this.mLastDownOrMoveEventY = y2;
        }
        return true;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        int i4;
        int[] iArr = this.mSelectorIndices;
        boolean z = this.mWrapSelectorWheel;
        if (!z && i3 > 0 && iArr[3] <= this.mMinValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        if (!z && i3 < 0 && iArr[3] >= this.mMaxValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        if (!z && ((i4 = iArr[3]) <= this.mMinValue || i4 >= this.mMaxValue)) {
            this.mSelectorTextGapHeight = this.mSelectorTextGapHeightNotWrap;
        }
        this.mCurrentScrollOffset += i3;
        while (true) {
            int i5 = this.mCurrentScrollOffset;
            if (i5 - this.mInitialScrollOffset <= this.mSelectorTextGapHeight) {
                break;
            }
            this.mCurrentScrollOffset = i5 - this.mSelectorElementHeight;
            a(iArr);
            n(iArr[3], true);
            if (!this.mWrapSelectorWheel && iArr[3] <= this.mMinValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
        while (true) {
            int i6 = this.mCurrentScrollOffset;
            if (i6 - this.mInitialScrollOffset >= (-this.mSelectorTextGapHeight)) {
                getDrawTextAttri();
                return;
            }
            this.mCurrentScrollOffset = i6 + this.mSelectorElementHeight;
            g(iArr);
            n(iArr[3], true);
            if (!this.mWrapSelectorWheel && iArr[3] >= this.mMaxValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues == strArr) {
            return;
        }
        this.mDisplayedValues = strArr;
        j();
    }

    public void setFormatter(Formatter formatter) {
        if (formatter == this.mFormatter) {
            return;
        }
        this.mFormatter = formatter;
        j();
    }

    public void setMaxValue(int i2) {
        if (this.mMaxValue == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        this.mMaxValue = i2;
        if (i2 < this.mValue) {
            this.mValue = i2;
        }
        setWrapSelectorWheel(i2 - this.mMinValue > this.mSelectorIndices.length);
        j();
        invalidate();
    }

    public void setMinValue(int i2) {
        if (this.mMinValue == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        this.mMinValue = i2;
        if (i2 > this.mValue) {
            this.mValue = i2;
        }
        setWrapSelectorWheel(this.mMaxValue - i2 > this.mSelectorIndices.length);
        j();
        invalidate();
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setValue(int i2) {
        n(i2, false);
    }

    public void setWrapSelectorWheel(boolean z) {
        boolean z2 = this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length;
        if ((!z || z2) && z != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = z;
        }
    }
}
