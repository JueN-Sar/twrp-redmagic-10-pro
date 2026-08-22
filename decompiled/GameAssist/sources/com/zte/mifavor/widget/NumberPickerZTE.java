package com.zte.mifavor.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.google.android.gms.common.api.Api;
import com.zte.extres.R;
import com.zte.mifavor.utils.UIUtils;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import libcore.icu.LocaleData;

/* loaded from: classes2.dex */
public class NumberPickerZTE extends LinearLayout {
    public static final boolean DEBUG = false;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300;
    private static final int ITEM_CNT_LANDSCAPE = 3;
    private static final int ITEM_CNT_PORTRAIT = 5;
    private static final int ITEM_CNT_PORTRAIT_OUTSCREEN = 3;
    private static final int MAX_HEIGHT_LANDSCAPE = 120;
    private static final int MAX_HEIGHT_LANDSCAPE_OUTSCREEN = 98;
    private static final int MAX_HEIGHT_PORTRAIT = 192;
    private static final int MAX_HEIGHT_PORTRAIT_OUTSCREEN = 168;
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 8;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final String TAG = "NumberPickerZTE";
    private static final float TOP_AND_BOTTOM_FADING_EDGE_STRENGTH = 0.0f;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDERS_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDER_HEIGHT = 2;
    private int INPUT_DISABLE_FLAG;
    private int displayYear;
    private AccessibilityNodeProviderImpl mAccessibilityNodeProvider;
    private final Scroller mAdjustScroller;
    private BeginSoftInputOnLongPressCommand mBeginSoftInputOnLongPressCommand;
    private int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private final boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private final ImageButton mDecrementButton;
    private boolean mDecrementVirtualButtonPressed;
    private String[] mDisplayedValues;
    private Typeface mFinalMfvInputTypeface;
    private final Scroller mFlingScroller;
    private Formatter mFormatter;
    private final boolean mHasSelectorWheel;
    private final ImageButton mIncrementButton;
    private boolean mIncrementVirtualButtonPressed;
    private boolean mIngonreMoveEvents;
    private int mInitialScrollOffset;
    private float mInputSize;
    private final EditText mInputText;
    private int mInputTextColor;
    boolean mIsMonkey;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private int mLastHoveredChildVirtualViewId;
    private long mLongPressUpdateInterval;
    private int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private Typeface mMfvInputTypeface;
    private final int mMinHeight;
    private int mMinValue;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private Typeface mOriginInputTypeface;
    private final PressedStateHelper mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private float mSelectedSize;
    private final Drawable mSelectionDivider;
    private final int mSelectionDividerHeight;
    private int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private int mSelectorEvenWhellPaintColor;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private int[] mSelectorIndices;
    private int mSelectorMiddleItemIndex;
    private int mSelectorOddWhellPaintColor;
    private int mSelectorTextGapHeight;
    private int mSelectorWheelItemCount;
    private final Paint mSelectorWheelPaint;
    private SetSelectionCommand mSetSelectionCommand;
    private boolean mShowSoftInputOnTap;
    private final int mSolidColor;
    private final int mTextSize;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private final Drawable mVirtualButtonPressedDrawable;
    private boolean mWrapSelectorWheel;
    private int mdividerWith;
    private static final int DEFAULT_LAYOUT_RESOURCE_ID = R.layout.number_picker;
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    private static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 4160, 4161, 4162, 4163, 4164, 4165, 4166, 4167, 4168, 4169};

    class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {

        /* renamed from: a, reason: collision with root package name */
        private final Rect f17693a = new Rect();

        /* renamed from: b, reason: collision with root package name */
        private final int[] f17694b = new int[2];

        /* renamed from: c, reason: collision with root package name */
        private int f17695c = Integer.MIN_VALUE;

        AccessibilityNodeProviderImpl() {
        }

        private AccessibilityNodeInfo a(int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(NumberPickerZTE.class.getName());
            obtain.setPackageName(((LinearLayout) NumberPickerZTE.this).mContext.getPackageName());
            obtain.setSource(NumberPickerZTE.this);
            if (g()) {
                obtain.addChild(NumberPickerZTE.this, 3);
            }
            obtain.addChild(NumberPickerZTE.this, 2);
            if (h()) {
                obtain.addChild(NumberPickerZTE.this, 1);
            }
            obtain.setParent((View) NumberPickerZTE.this.getParentForAccessibility());
            obtain.setEnabled(NumberPickerZTE.this.isEnabled());
            obtain.setScrollable(true);
            float f2 = NumberPickerZTE.this.getContext().getResources().getCompatibilityInfo().applicationScale;
            Rect rect = this.f17693a;
            rect.set(i2, i3, i4, i5);
            if (f2 != 1.0f) {
                rect.left = (int) ((rect.left * f2) + 0.5f);
                rect.top = (int) ((rect.top * f2) + 0.5f);
                rect.right = (int) ((rect.right * f2) + 0.5f);
                rect.bottom = (int) ((rect.bottom * f2) + 0.5f);
            }
            obtain.setBoundsInParent(rect);
            obtain.setVisibleToUser(NumberPickerZTE.this.isVisibleToUser());
            int[] iArr = this.f17694b;
            NumberPickerZTE.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            if (f2 != 1.0f) {
                rect.left = (int) ((rect.left * f2) + 0.5f);
                rect.top = (int) ((rect.top * f2) + 0.5f);
                rect.right = (int) ((rect.right * f2) + 0.5f);
                rect.bottom = (int) ((rect.bottom * f2) + 0.5f);
            }
            obtain.setBoundsInScreen(rect);
            if (this.f17695c != -1) {
                obtain.addAction(64);
            }
            if (this.f17695c == -1) {
                obtain.addAction(128);
            }
            if (NumberPickerZTE.this.isEnabled()) {
                if (NumberPickerZTE.this.getWrapSelectorWheel() || NumberPickerZTE.this.getValue() < NumberPickerZTE.this.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (NumberPickerZTE.this.getWrapSelectorWheel() || NumberPickerZTE.this.getValue() > NumberPickerZTE.this.getMinValue()) {
                    obtain.addAction(8192);
                }
            }
            return obtain;
        }

        private AccessibilityNodeInfo b(int i2, String str, int i3, int i4, int i5, int i6) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(((LinearLayout) NumberPickerZTE.this).mContext.getPackageName());
            obtain.setSource(NumberPickerZTE.this, i2);
            obtain.setParent(NumberPickerZTE.this);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(NumberPickerZTE.this.isEnabled());
            Rect rect = this.f17693a;
            rect.set(i3, i4, i5, i6);
            obtain.setVisibleToUser(NumberPickerZTE.this.isVisibleToUser(rect));
            obtain.setBoundsInParent(rect);
            int[] iArr = this.f17694b;
            NumberPickerZTE.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.f17695c != i2) {
                obtain.addAction(64);
            }
            if (this.f17695c == i2) {
                obtain.addAction(128);
            }
            if (NumberPickerZTE.this.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo c(int i2, int i3, int i4, int i5) {
            AccessibilityNodeInfo createAccessibilityNodeInfo = NumberPickerZTE.this.mInputText.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(NumberPickerZTE.this, 2);
            if (this.f17695c != 2) {
                createAccessibilityNodeInfo.addAction(64);
            }
            if (this.f17695c == 2) {
                createAccessibilityNodeInfo.addAction(128);
            }
            Rect rect = this.f17693a;
            rect.set(i2, i3, i4, i5);
            createAccessibilityNodeInfo.setVisibleToUser(NumberPickerZTE.this.isVisibleToUser(rect));
            createAccessibilityNodeInfo.setBoundsInParent(rect);
            int[] iArr = this.f17694b;
            NumberPickerZTE.this.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            createAccessibilityNodeInfo.setBoundsInScreen(rect);
            return createAccessibilityNodeInfo;
        }

        private void d(String str, int i2, List list) {
            if (i2 == 1) {
                String f2 = f();
                if (TextUtils.isEmpty(f2) || !f2.toString().toLowerCase(Locale.getDefault()).contains(str)) {
                    return;
                }
                list.add(createAccessibilityNodeInfo(1));
                return;
            }
            if (i2 != 2) {
                if (i2 != 3) {
                    return;
                }
                String e2 = e();
                if (TextUtils.isEmpty(e2) || !e2.toString().toLowerCase(Locale.getDefault()).contains(str)) {
                    return;
                }
                list.add(createAccessibilityNodeInfo(3));
                return;
            }
            Editable text = NumberPickerZTE.this.mInputText.getText();
            if (!TextUtils.isEmpty(text) && text.toString().toLowerCase(Locale.getDefault()).contains(str)) {
                list.add(createAccessibilityNodeInfo(2));
                return;
            }
            Editable text2 = NumberPickerZTE.this.mInputText.getText();
            if (TextUtils.isEmpty(text2) || !text2.toString().toLowerCase(Locale.getDefault()).contains(str)) {
                return;
            }
            list.add(createAccessibilityNodeInfo(2));
        }

        private String e() {
            int i2 = NumberPickerZTE.this.mValue - 1;
            if (NumberPickerZTE.this.mWrapSelectorWheel) {
                i2 = NumberPickerZTE.this.P(i2);
            }
            if (i2 >= NumberPickerZTE.this.mMinValue) {
                return NumberPickerZTE.this.mDisplayedValues == null ? NumberPickerZTE.this.M(i2) : NumberPickerZTE.this.mDisplayedValues[i2 - NumberPickerZTE.this.mMinValue];
            }
            return null;
        }

        private String f() {
            int i2 = NumberPickerZTE.this.mValue + 1;
            if (NumberPickerZTE.this.mWrapSelectorWheel) {
                i2 = NumberPickerZTE.this.P(i2);
            }
            if (i2 <= NumberPickerZTE.this.mMaxValue) {
                return NumberPickerZTE.this.mDisplayedValues == null ? NumberPickerZTE.this.M(i2) : NumberPickerZTE.this.mDisplayedValues[i2 - NumberPickerZTE.this.mMinValue];
            }
            return null;
        }

        private boolean g() {
            return NumberPickerZTE.this.getWrapSelectorWheel() || NumberPickerZTE.this.getValue() > NumberPickerZTE.this.getMinValue();
        }

        private boolean h() {
            return NumberPickerZTE.this.getWrapSelectorWheel() || NumberPickerZTE.this.getValue() < NumberPickerZTE.this.getMaxValue();
        }

        private void i(int i2, int i3, String str) {
            if (((AccessibilityManager) ((LinearLayout) NumberPickerZTE.this).mContext.getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(((LinearLayout) NumberPickerZTE.this).mContext.getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(NumberPickerZTE.this.isEnabled());
                obtain.setSource(NumberPickerZTE.this, i2);
                NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
                numberPickerZTE.requestSendAccessibilityEvent(numberPickerZTE, obtain);
            }
        }

        private void j(int i2) {
            if (((AccessibilityManager) ((LinearLayout) NumberPickerZTE.this).mContext.getSystemService("accessibility")).isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                NumberPickerZTE.this.mInputText.onInitializeAccessibilityEvent(obtain);
                NumberPickerZTE.this.mInputText.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(NumberPickerZTE.this, 2);
                NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
                numberPickerZTE.requestSendAccessibilityEvent(numberPickerZTE, obtain);
            }
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            return i2 != -1 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? super.createAccessibilityNodeInfo(i2) : b(3, e(), NumberPickerZTE.this.getScrollX(), NumberPickerZTE.this.getScrollY(), NumberPickerZTE.this.getScrollX() + (NumberPickerZTE.this.getRight() - NumberPickerZTE.this.getLeft()), NumberPickerZTE.this.mTopSelectionDividerTop + NumberPickerZTE.this.mSelectionDividerHeight) : c(NumberPickerZTE.this.getScrollX(), NumberPickerZTE.this.mTopSelectionDividerTop + NumberPickerZTE.this.mSelectionDividerHeight, NumberPickerZTE.this.getScrollX() + (NumberPickerZTE.this.getRight() - NumberPickerZTE.this.getLeft()), NumberPickerZTE.this.mBottomSelectionDividerBottom - NumberPickerZTE.this.mSelectionDividerHeight) : b(1, f(), NumberPickerZTE.this.getScrollX(), NumberPickerZTE.this.mBottomSelectionDividerBottom - NumberPickerZTE.this.mSelectionDividerHeight, NumberPickerZTE.this.getScrollX() + (NumberPickerZTE.this.getRight() - NumberPickerZTE.this.getLeft()), NumberPickerZTE.this.getScrollY() + (NumberPickerZTE.this.getBottom() - NumberPickerZTE.this.getTop())) : a(NumberPickerZTE.this.getScrollX(), NumberPickerZTE.this.getScrollY(), NumberPickerZTE.this.getScrollX() + (NumberPickerZTE.this.getRight() - NumberPickerZTE.this.getLeft()), NumberPickerZTE.this.getScrollY() + (NumberPickerZTE.this.getBottom() - NumberPickerZTE.this.getTop()));
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List findAccessibilityNodeInfosByText(String str, int i2) {
            if (TextUtils.isEmpty(str)) {
                return Collections.emptyList();
            }
            String lowerCase = str.toLowerCase(Locale.getDefault());
            ArrayList arrayList = new ArrayList();
            if (i2 == -1) {
                d(lowerCase, 3, arrayList);
                d(lowerCase, 2, arrayList);
                d(lowerCase, 1, arrayList);
                return arrayList;
            }
            if (i2 != 1 && i2 != 2 && i2 != 3) {
                return super.findAccessibilityNodeInfosByText(str, i2);
            }
            d(lowerCase, i2, arrayList);
            return arrayList;
        }

        public void k(int i2, int i3) {
            if (i2 == 1) {
                if (h()) {
                    i(i2, i3, f());
                }
            } else if (i2 == 2) {
                j(i3);
            } else if (i2 == 3 && g()) {
                i(i2, i3, e());
            }
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int i2, int i3, Bundle bundle) {
            if (i2 != -1) {
                if (i2 == 1) {
                    if (i3 == 16) {
                        if (!NumberPickerZTE.this.isEnabled()) {
                            return false;
                        }
                        NumberPickerZTE.this.H(true);
                        k(i2, 1);
                        return true;
                    }
                    if (i3 == 64) {
                        if (this.f17695c == i2) {
                            return false;
                        }
                        this.f17695c = i2;
                        k(i2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS);
                        NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
                        numberPickerZTE.invalidate(0, numberPickerZTE.mBottomSelectionDividerBottom, NumberPickerZTE.this.getRight(), NumberPickerZTE.this.getBottom());
                        return true;
                    }
                    if (i3 != 128 || this.f17695c != i2) {
                        return false;
                    }
                    this.f17695c = Integer.MIN_VALUE;
                    k(i2, 65536);
                    NumberPickerZTE numberPickerZTE2 = NumberPickerZTE.this;
                    numberPickerZTE2.invalidate(0, numberPickerZTE2.mBottomSelectionDividerBottom, NumberPickerZTE.this.getRight(), NumberPickerZTE.this.getBottom());
                    return true;
                }
                if (i2 == 2) {
                    if (i3 == 1) {
                        if (!NumberPickerZTE.this.isEnabled() || NumberPickerZTE.this.mInputText.isFocused()) {
                            return false;
                        }
                        return NumberPickerZTE.this.mInputText.requestFocus();
                    }
                    if (i3 == 2) {
                        if (!NumberPickerZTE.this.isEnabled() || !NumberPickerZTE.this.mInputText.isFocused()) {
                            return false;
                        }
                        NumberPickerZTE.this.mInputText.clearFocus();
                        return true;
                    }
                    if (i3 == 16) {
                        if (!NumberPickerZTE.this.isEnabled()) {
                            return false;
                        }
                        NumberPickerZTE.this.n0();
                        return true;
                    }
                    if (i3 == 64) {
                        if (this.f17695c == i2) {
                            return false;
                        }
                        this.f17695c = i2;
                        k(i2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS);
                        NumberPickerZTE.this.mInputText.invalidate();
                        return true;
                    }
                    if (i3 != 128) {
                        return NumberPickerZTE.this.mInputText.performAccessibilityAction(i3, bundle);
                    }
                    if (this.f17695c != i2) {
                        return false;
                    }
                    this.f17695c = Integer.MIN_VALUE;
                    k(i2, 65536);
                    NumberPickerZTE.this.mInputText.invalidate();
                    return true;
                }
                if (i2 == 3) {
                    if (i3 == 16) {
                        if (!NumberPickerZTE.this.isEnabled()) {
                            return false;
                        }
                        NumberPickerZTE.this.H(i2 == 1);
                        k(i2, 1);
                        return true;
                    }
                    if (i3 == 64) {
                        if (this.f17695c == i2) {
                            return false;
                        }
                        this.f17695c = i2;
                        k(i2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FORCE_DRAW_BAR_BACKGROUNDS);
                        NumberPickerZTE numberPickerZTE3 = NumberPickerZTE.this;
                        numberPickerZTE3.invalidate(0, 0, numberPickerZTE3.getRight(), NumberPickerZTE.this.mTopSelectionDividerTop);
                        return true;
                    }
                    if (i3 != 128 || this.f17695c != i2) {
                        return false;
                    }
                    this.f17695c = Integer.MIN_VALUE;
                    k(i2, 65536);
                    NumberPickerZTE numberPickerZTE4 = NumberPickerZTE.this;
                    numberPickerZTE4.invalidate(0, 0, numberPickerZTE4.getRight(), NumberPickerZTE.this.mTopSelectionDividerTop);
                    return true;
                }
            } else {
                if (i3 == 64) {
                    if (this.f17695c == i2) {
                        return false;
                    }
                    this.f17695c = i2;
                    NumberPickerZTE.this.requestAccessibilityFocus();
                    return true;
                }
                if (i3 == 128) {
                    if (this.f17695c != i2) {
                        return false;
                    }
                    this.f17695c = Integer.MIN_VALUE;
                    NumberPickerZTE.this.clearAccessibilityFocus();
                    return true;
                }
                if (i3 == 4096) {
                    if (!NumberPickerZTE.this.isEnabled() || (!NumberPickerZTE.this.getWrapSelectorWheel() && NumberPickerZTE.this.getValue() >= NumberPickerZTE.this.getMaxValue())) {
                        return false;
                    }
                    NumberPickerZTE.this.H(true);
                    return true;
                }
                if (i3 == 8192) {
                    if (!NumberPickerZTE.this.isEnabled() || (!NumberPickerZTE.this.getWrapSelectorWheel() && NumberPickerZTE.this.getValue() <= NumberPickerZTE.this.getMinValue())) {
                        return false;
                    }
                    NumberPickerZTE.this.H(false);
                    return true;
                }
            }
            return super.performAction(i2, i3, bundle);
        }
    }

    class BeginSoftInputOnLongPressCommand implements Runnable {
        BeginSoftInputOnLongPressCommand() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPickerZTE.this.n0();
            NumberPickerZTE.this.mIngonreMoveEvents = true;
        }
    }

    class ChangeCurrentByOneFromLongPressCommand implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private boolean f17698c;

        ChangeCurrentByOneFromLongPressCommand() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(boolean z) {
            this.f17698c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPickerZTE.this.H(this.f17698c);
            NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
            numberPickerZTE.postDelayed(this, numberPickerZTE.mLongPressUpdateInterval);
        }
    }

    public static class CustomEditText extends EditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView
        public void onEditorAction(int i2) {
            super.onEditorAction(i2);
            if (i2 == 6) {
                clearFocus();
            }
        }
    }

    public interface Formatter {
        String a(int i2);
    }

    class InputTextFilter extends NumberKeyListener {
        InputTextFilter() {
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (NumberPickerZTE.this.mSetSelectionCommand != null) {
                NumberPickerZTE.this.mSetSelectionCommand.a();
            }
            if (NumberPickerZTE.this.mDisplayedValues == null) {
                CharSequence filter = super.filter(charSequence, i2, i3, spanned, i4, i5);
                if (filter == null) {
                    filter = charSequence.subSequence(i2, i3);
                }
                String str = String.valueOf(spanned.subSequence(0, i4)) + ((Object) filter) + ((Object) spanned.subSequence(i5, spanned.length()));
                return "".equals(str) ? str : (NumberPickerZTE.this.O(str) > NumberPickerZTE.this.mMaxValue || str.length() > String.valueOf(NumberPickerZTE.this.mMaxValue).length()) ? "" : filter;
            }
            String valueOf = String.valueOf(charSequence.subSequence(i2, i3));
            if (TextUtils.isEmpty(valueOf)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i4)) + ((Object) valueOf) + ((Object) spanned.subSequence(i5, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase(Locale.getDefault());
            for (String str3 : NumberPickerZTE.this.mDisplayedValues) {
                if (str3.toLowerCase(Locale.getDefault()).startsWith(lowerCase)) {
                    NumberPickerZTE.this.e0(str2.length(), str3.length());
                    return str3.subSequence(i4, str3.length());
                }
            }
            return "";
        }

        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return NumberPickerZTE.DIGIT_CHARACTERS;
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }
    }

    public interface OnScrollListener {
        void a(NumberPickerZTE numberPickerZTE, int i2);
    }

    public interface OnValueChangeListener {
        void a(NumberPickerZTE numberPickerZTE, int i2, int i3);
    }

    class PressedStateHelper implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private int f17701c;

        /* renamed from: h, reason: collision with root package name */
        private int f17702h;

        PressedStateHelper() {
        }

        public void a(int i2) {
            c();
            this.f17702h = 1;
            this.f17701c = i2;
            NumberPickerZTE.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void b(int i2) {
            c();
            this.f17702h = 2;
            this.f17701c = i2;
            NumberPickerZTE.this.post(this);
        }

        public void c() {
            this.f17702h = 0;
            this.f17701c = 0;
            NumberPickerZTE.this.removeCallbacks(this);
            if (NumberPickerZTE.this.mIncrementVirtualButtonPressed) {
                NumberPickerZTE.this.mIncrementVirtualButtonPressed = false;
                NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
                numberPickerZTE.invalidate(0, numberPickerZTE.mBottomSelectionDividerBottom, NumberPickerZTE.this.getRight(), NumberPickerZTE.this.getBottom());
            }
            NumberPickerZTE.this.mDecrementVirtualButtonPressed = false;
            if (NumberPickerZTE.this.mDecrementVirtualButtonPressed) {
                NumberPickerZTE numberPickerZTE2 = NumberPickerZTE.this;
                numberPickerZTE2.invalidate(0, 0, numberPickerZTE2.getRight(), NumberPickerZTE.this.mTopSelectionDividerTop);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f17702h;
            if (i2 == 1) {
                int i3 = this.f17701c;
                if (i3 == 1) {
                    NumberPickerZTE.this.mIncrementVirtualButtonPressed = true;
                    NumberPickerZTE numberPickerZTE = NumberPickerZTE.this;
                    numberPickerZTE.invalidate(0, numberPickerZTE.mBottomSelectionDividerBottom, NumberPickerZTE.this.getRight(), NumberPickerZTE.this.getBottom());
                    return;
                } else {
                    if (i3 != 2) {
                        return;
                    }
                    NumberPickerZTE.this.mDecrementVirtualButtonPressed = true;
                    NumberPickerZTE numberPickerZTE2 = NumberPickerZTE.this;
                    numberPickerZTE2.invalidate(0, 0, numberPickerZTE2.getRight(), NumberPickerZTE.this.mTopSelectionDividerTop);
                    return;
                }
            }
            if (i2 != 2) {
                return;
            }
            int i4 = this.f17701c;
            if (i4 == 1) {
                if (!NumberPickerZTE.this.mIncrementVirtualButtonPressed) {
                    NumberPickerZTE.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                }
                NumberPickerZTE.this.mIncrementVirtualButtonPressed = !r0.mIncrementVirtualButtonPressed;
                NumberPickerZTE numberPickerZTE3 = NumberPickerZTE.this;
                numberPickerZTE3.invalidate(0, numberPickerZTE3.mBottomSelectionDividerBottom, NumberPickerZTE.this.getRight(), NumberPickerZTE.this.getBottom());
                return;
            }
            if (i4 != 2) {
                return;
            }
            if (!NumberPickerZTE.this.mDecrementVirtualButtonPressed) {
                NumberPickerZTE.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
            }
            NumberPickerZTE.this.mDecrementVirtualButtonPressed = !r0.mDecrementVirtualButtonPressed;
            NumberPickerZTE numberPickerZTE4 = NumberPickerZTE.this;
            numberPickerZTE4.invalidate(0, 0, numberPickerZTE4.getRight(), NumberPickerZTE.this.mTopSelectionDividerTop);
        }
    }

    private static class SetSelectionCommand implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private final EditText f17704c;

        /* renamed from: h, reason: collision with root package name */
        private int f17705h;

        /* renamed from: i, reason: collision with root package name */
        private int f17706i;

        /* renamed from: j, reason: collision with root package name */
        private boolean f17707j;

        public SetSelectionCommand(EditText editText) {
            this.f17704c = editText;
        }

        public void a() {
            if (this.f17707j) {
                this.f17704c.removeCallbacks(this);
                this.f17707j = false;
            }
        }

        public void b(int i2, int i3) {
            this.f17705h = i2;
            this.f17706i = i3;
            if (this.f17707j) {
                return;
            }
            this.f17704c.post(this);
            this.f17707j = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f17707j = false;
            if (this.f17704c.length() >= this.f17705h) {
                int length = this.f17704c.length();
                int i2 = this.f17706i;
                if (length >= i2) {
                    this.f17704c.setSelection(this.f17705h, i2);
                }
            }
        }
    }

    private static class TwoDigitFormatter implements Formatter {

        /* renamed from: a, reason: collision with root package name */
        final StringBuilder f17708a = new StringBuilder();

        /* renamed from: b, reason: collision with root package name */
        final Object[] f17709b = new Object[1];

        /* renamed from: c, reason: collision with root package name */
        char f17710c;

        /* renamed from: d, reason: collision with root package name */
        java.util.Formatter f17711d;

        TwoDigitFormatter() {
            d(Locale.getDefault());
        }

        private java.util.Formatter b(Locale locale) {
            return new java.util.Formatter(this.f17708a, locale);
        }

        private static char c(Locale locale) {
            return LocaleData.get(locale).zeroDigit;
        }

        private void d(Locale locale) {
            this.f17711d = b(locale);
            this.f17710c = c(locale);
        }

        @Override // com.zte.mifavor.widget.NumberPickerZTE.Formatter
        public String a(int i2) {
            Locale locale = Locale.getDefault();
            if (this.f17710c != c(locale)) {
                d(locale);
            }
            this.f17709b[0] = Integer.valueOf(i2);
            StringBuilder sb = this.f17708a;
            sb.delete(0, sb.length());
            this.f17711d.format("%02d", this.f17709b);
            return this.f17711d.toString();
        }
    }

    public NumberPickerZTE(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H(boolean z) {
        if (!this.mHasSelectorWheel) {
            if (z) {
                m0(this.mValue + 1, true);
                return;
            } else {
                m0(this.mValue - 1, true);
                return;
            }
        }
        this.mInputText.setVisibility(4);
        if (!Y(this.mFlingScroller)) {
            Y(this.mAdjustScroller);
        }
        this.mPreviousScrollerY = 0;
        if (z) {
            this.mFlingScroller.startScroll(0, 0, 0, -this.mSelectorElementHeight, 300);
        } else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight, 300);
        }
        invalidate();
    }

    private void I(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.mMinValue) {
            i2 = this.mMaxValue;
        }
        iArr[0] = i2;
        J(i2);
    }

    private void J(int i2) {
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
            str = strArr != null ? strArr[i2 - i3] : M(i2);
        }
        sparseArray.put(i2, str);
    }

    private boolean K() {
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
        this.mAdjustScroller.startScroll(0, 0, 0, i2, SELECTOR_ADJUSTMENT_DURATION_MILLIS);
        invalidate();
        return true;
    }

    private void L(int i2) {
        this.mPreviousScrollerY = 0;
        if (i2 > 0) {
            this.mFlingScroller.fling(0, 0, 0, i2, 0, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        } else {
            this.mFlingScroller.fling(0, Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, i2, 0, 0, 0, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String M(int i2) {
        Formatter formatter = this.mFormatter;
        return formatter != null ? formatter.a(i2) : N(i2);
    }

    private static String N(int i2) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int O(String str) {
        try {
            if (this.mDisplayedValues == null) {
                return Integer.parseInt(str);
            }
            for (int i2 = 0; i2 < this.mDisplayedValues.length; i2++) {
                str = str.toLowerCase(Locale.getDefault());
                if (this.mDisplayedValues[i2].toLowerCase(Locale.getDefault()).startsWith(str)) {
                    return this.mMinValue + i2;
                }
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return this.mMinValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int P(int i2) {
        int i3 = this.mMaxValue;
        if (i2 > i3) {
            int i4 = this.mMinValue;
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        int i5 = this.mMinValue;
        return i2 < i5 ? (i3 - ((i5 - i2) % (i3 - i5))) + 1 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager == null || !inputMethodManager.isActive(this.mInputText)) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        if (this.mHasSelectorWheel) {
            this.mInputText.setVisibility(4);
        }
    }

    private void R(int[] iArr) {
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
        J(i4);
    }

    private void S() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) / 2);
    }

    private void T() {
        V();
        int[] iArr = this.mSelectorIndices;
        int bottom = (int) ((((getBottom() - getTop()) - (iArr.length * this.mTextSize)) / iArr.length) + 0.5f);
        this.mSelectorTextGapHeight = bottom;
        this.mSelectorElementHeight = this.mTextSize + bottom;
        int baseline = (this.mInputText.getBaseline() + this.mInputText.getTop()) - (this.mSelectorElementHeight * this.mSelectorMiddleItemIndex);
        this.mInitialScrollOffset = baseline;
        this.mCurrentScrollOffset = baseline;
        p0();
    }

    private void U() {
        V();
        int[] iArr = this.mSelectorIndices;
        int bottom = (int) ((((getBottom() - getTop()) - (iArr.length * this.mTextSize)) / iArr.length) + 0.5f);
        this.mSelectorTextGapHeight = bottom;
        this.mSelectorElementHeight = this.mTextSize + bottom;
        int baseline = (this.mInputText.getBaseline() + this.mInputText.getTop()) - (this.mSelectorElementHeight * this.mSelectorMiddleItemIndex);
        this.mInitialScrollOffset = baseline;
        this.mCurrentScrollOffset = baseline;
    }

    private void V() {
        this.mSelectorIndexToStringCache.clear();
        int[] iArr = this.mSelectorIndices;
        int value = getValue();
        for (int i2 = 0; i2 < this.mSelectorIndices.length; i2++) {
            int i3 = (i2 - this.mSelectorMiddleItemIndex) + value;
            if (this.mWrapSelectorWheel) {
                i3 = P(i3);
            }
            iArr[i2] = i3;
            J(i3);
        }
    }

    private int X(int i2, int i3) {
        if (i3 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i3, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        if (mode == 1073741824) {
            return i2;
        }
        throw new IllegalArgumentException("Unknown measure mode: " + mode);
    }

    private boolean Y(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i2 = this.mInitialScrollOffset - ((this.mCurrentScrollOffset + finalY) % this.mSelectorElementHeight);
        if (i2 == 0) {
            return false;
        }
        int abs = Math.abs(i2);
        int i3 = this.mSelectorElementHeight;
        if (abs > i3 / 2) {
            i2 = i2 > 0 ? i2 - i3 : i2 + i3;
        }
        scrollBy(0, finalY + i2);
        return true;
    }

    private void Z(int i2, int i3) {
        OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.a(this, i2, this.mValue);
        }
    }

    private void a0(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.a(this, i2);
        }
    }

    private void b0(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            K();
            p0();
            a0(0);
        } else if (this.mScrollState != 1) {
            p0();
        }
    }

    private void c0() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand == null) {
            this.mBeginSoftInputOnLongPressCommand = new BeginSoftInputOnLongPressCommand();
        } else {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        postDelayed(this.mBeginSoftInputOnLongPressCommand, ViewConfiguration.getLongPressTimeout());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(boolean z, long j2) {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mChangeCurrentByOneFromLongPressCommand.b(z);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0(int i2, int i3) {
        if (this.mSetSelectionCommand == null) {
            this.mSetSelectionCommand = new SetSelectionCommand(this.mInputText);
        }
        this.mSetSelectionCommand.b(i2, i3);
    }

    private void f0() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        SetSelectionCommand setSelectionCommand = this.mSetSelectionCommand;
        if (setSelectionCommand != null) {
            setSelectionCommand.a();
        }
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        this.mPressedStateHelper.c();
    }

    private void g0() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            removeCallbacks(beginSoftInputOnLongPressCommand);
        }
    }

    public static final Formatter getTwoDigitFormatter() {
        return sTwoDigitFormatter;
    }

    private void h0() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
    }

    private int i0(int i2, int i3, int i4) {
        return i2 != -1 ? LinearLayout.resolveSizeAndState(Math.max(i2, i3), i4, 0) : i3;
    }

    private void l0(int i2, int i3) {
        this.mSelectorWheelItemCount = i2;
        this.mSelectorMiddleItemIndex = i2 / 2;
        this.mSelectorIndices = new int[i2];
        V();
        int applyDimension = (int) TypedValue.applyDimension(1, i3, getResources().getDisplayMetrics());
        this.mMaxHeight = applyDimension;
        int i4 = this.mMinHeight;
        if (i4 != -1 && applyDimension != -1 && i4 > applyDimension) {
            throw new IllegalArgumentException("minHeight > maxHeight");
        }
    }

    private void m0(int i2, boolean z) {
        if (this.mValue == i2) {
            return;
        }
        int P = this.mWrapSelectorWheel ? P(i2) : Math.min(Math.max(i2, this.mMinValue), this.mMaxValue);
        int i3 = this.mValue;
        this.mValue = P;
        if (this.mScrollState != 2) {
            p0();
        }
        if (z) {
            Z(i3, P);
        }
        V();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n0() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (this.mHasSelectorWheel) {
                this.mInputText.setVisibility(0);
            }
            this.mInputText.requestFocus();
            inputMethodManager.showSoftInput(this.mInputText, 0);
        }
    }

    private void o0() {
        int i2;
        if (this.mComputeMaxWidth) {
            String[] strArr = this.mDisplayedValues;
            int i3 = 0;
            if (strArr == null) {
                float f2 = TOP_AND_BOTTOM_FADING_EDGE_STRENGTH;
                for (int i4 = 0; i4 <= 9; i4++) {
                    float measureText = this.mSelectorWheelPaint.measureText(N(i4));
                    if (measureText > f2) {
                        f2 = measureText;
                    }
                }
                for (int i5 = this.mMaxValue; i5 > 0; i5 /= 10) {
                    i3++;
                }
                i2 = (int) (i3 * f2);
            } else {
                int length = strArr.length;
                int i6 = 0;
                while (i3 < length) {
                    float measureText2 = this.mSelectorWheelPaint.measureText(this.mDisplayedValues[i3]);
                    if (measureText2 > i6) {
                        i6 = (int) measureText2;
                    }
                    i3++;
                }
                i2 = i6;
            }
            int paddingLeft = i2 + this.mInputText.getPaddingLeft() + this.mInputText.getPaddingRight();
            if (this.mMaxWidth != paddingLeft) {
                int i7 = this.mMinWidth;
                if (paddingLeft > i7) {
                    this.mMaxWidth = paddingLeft;
                } else {
                    this.mMaxWidth = i7;
                }
                invalidate();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean p0() {
        /*
            r7 = this;
            boolean r0 = com.zte.mifavor.widget.Utils.w()
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            int r0 = r7.INPUT_DISABLE_FLAG
            r2 = 1
            if (r0 != r2) goto L1b
            int r0 = r7.mValue
            int r3 = r7.displayYear
            if (r0 <= r3) goto L1b
            android.widget.EditText r7 = r7.mInputText
            java.lang.String r0 = ""
            r7.setText(r0)
            return r2
        L1b:
            java.lang.String[] r0 = r7.mDisplayedValues
            if (r0 == 0) goto L30
            int r3 = r7.mValue
            int r4 = r7.mMinValue
            int r5 = r3 - r4
            if (r5 < 0) goto L30
            int r5 = r3 - r4
            int r6 = r0.length
            if (r5 >= r6) goto L30
            int r3 = r3 - r4
            r0 = r0[r3]
            goto L36
        L30:
            int r0 = r7.mValue
            java.lang.String r0 = r7.M(r0)
        L36:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L95
            android.widget.EditText r3 = r7.mInputText
            android.text.Editable r3 = r3.getText()
            java.lang.String r4 = r3.toString()
            boolean r4 = r0.equals(r4)
            if (r4 != 0) goto L95
            android.widget.EditText r4 = r7.mInputText
            r4.setText(r0)
            com.zte.mifavor.widget.NumberPickerZTE$AccessibilityNodeProviderImpl r4 = r7.mAccessibilityNodeProvider
            if (r4 == 0) goto L94
            android.content.Context r4 = r7.mContext
            java.lang.String r5 = "accessibility"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.view.accessibility.AccessibilityManager r4 = (android.view.accessibility.AccessibilityManager) r4
            boolean r4 = r4.isEnabled()
            if (r4 == 0) goto L94
            boolean r4 = r7.mIsMonkey
            if (r4 != 0) goto L94
            r4 = 16
            android.view.accessibility.AccessibilityEvent r4 = android.view.accessibility.AccessibilityEvent.obtain(r4)
            android.widget.EditText r5 = r7.mInputText
            r5.onInitializeAccessibilityEvent(r4)
            android.widget.EditText r5 = r7.mInputText
            r5.onPopulateAccessibilityEvent(r4)
            r4.setFromIndex(r1)
            int r1 = r3.length()
            r4.setRemovedCount(r1)
            int r0 = r0.length()
            r4.setAddedCount(r0)
            r4.setBeforeText(r3)
            r0 = 2
            r4.setSource(r7, r0)
            r7.requestSendAccessibilityEvent(r7, r4)
        L94:
            return r2
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.NumberPickerZTE.p0():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q0(View view) {
        String valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            p0();
        } else {
            m0(O(valueOf), true);
        }
    }

    private void setSelectorWheelItemCount(Configuration configuration) {
        boolean j2 = UIUtils.j(((LinearLayout) this).mContext);
        int i2 = 3;
        if (configuration.orientation == 2 || configuration.toString().contains("split-screen-primary")) {
            l0(3, j2 ? MAX_HEIGHT_LANDSCAPE_OUTSCREEN : MAX_HEIGHT_LANDSCAPE);
        } else {
            boolean h2 = UIUtils.h(((LinearLayout) this).mContext);
            int i3 = j2 ? MAX_HEIGHT_PORTRAIT_OUTSCREEN : MAX_HEIGHT_PORTRAIT;
            if (!j2 && !h2) {
                i2 = 5;
            }
            l0(i2, i3);
            Log.d(TAG, "setSelectorWheelItemCount bIsMulWindow=" + h2 + ", count=" + i2);
        }
        U();
    }

    public boolean W(int i2) {
        return this.INPUT_DISABLE_FLAG == 1 && i2 > this.displayYear;
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
            b0(scroller);
        } else {
            invalidate();
        }
    }

    @Override // android.view.View
    protected int computeVerticalScrollExtent() {
        return getHeight();
    }

    @Override // android.view.View
    protected int computeVerticalScrollOffset() {
        return this.mCurrentScrollOffset;
    }

    @Override // android.view.View
    protected int computeVerticalScrollRange() {
        return ((this.mMaxValue - this.mMinValue) + 1) * this.mSelectorElementHeight;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.mHasSelectorWheel) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (!((AccessibilityManager) ((LinearLayout) this).mContext.getSystemService("accessibility")).isEnabled()) {
            return false;
        }
        int y = (int) motionEvent.getY();
        int i2 = y < this.mTopSelectionDividerTop ? 3 : y > this.mBottomSelectionDividerBottom ? 1 : 2;
        int actionMasked = motionEvent.getActionMasked();
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider();
        if (actionMasked == 7) {
            int i3 = this.mLastHoveredChildVirtualViewId;
            if (i3 == i2 || i3 == -1) {
                return false;
            }
            accessibilityNodeProviderImpl.k(i3, 256);
            accessibilityNodeProviderImpl.k(i2, 128);
            this.mLastHoveredChildVirtualViewId = i2;
            accessibilityNodeProviderImpl.performAction(i2, 64, null);
            return false;
        }
        if (actionMasked == 9) {
            accessibilityNodeProviderImpl.k(i2, 128);
            this.mLastHoveredChildVirtualViewId = i2;
            accessibilityNodeProviderImpl.performAction(i2, 64, null);
            return false;
        }
        if (actionMasked != 10) {
            return false;
        }
        accessibilityNodeProviderImpl.k(i2, 256);
        this.mLastHoveredChildVirtualViewId = -1;
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004c, code lost:
    
        requestFocus();
        r5.mLastHandledDownDpadKeyCode = r0;
        f0();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005a, code lost:
    
        if (r5.mFlingScroller.isFinished() == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
    
        if (r0 != 20) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005e, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
    
        H(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0060, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
    
        return true;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getKeyCode()
            r1 = 19
            r2 = 20
            if (r0 == r1) goto L19
            if (r0 == r2) goto L19
            r1 = 23
            if (r0 == r1) goto L15
            r1 = 66
            if (r0 == r1) goto L15
            goto L65
        L15:
            r5.f0()
            goto L65
        L19:
            boolean r1 = r5.mHasSelectorWheel
            if (r1 != 0) goto L1e
            goto L65
        L1e:
            int r1 = r6.getAction()
            r3 = 1
            if (r1 == 0) goto L30
            if (r1 == r3) goto L28
            goto L65
        L28:
            int r1 = r5.mLastHandledDownDpadKeyCode
            if (r1 != r0) goto L65
            r6 = -1
            r5.mLastHandledDownDpadKeyCode = r6
            return r3
        L30:
            boolean r1 = r5.mWrapSelectorWheel
            if (r1 != 0) goto L42
            if (r0 != r2) goto L37
            goto L42
        L37:
            int r1 = r5.getValue()
            int r4 = r5.getMinValue()
            if (r1 <= r4) goto L65
            goto L4c
        L42:
            int r1 = r5.getValue()
            int r4 = r5.getMaxValue()
            if (r1 >= r4) goto L65
        L4c:
            r5.requestFocus()
            r5.mLastHandledDownDpadKeyCode = r0
            r5.f0()
            android.widget.Scroller r6 = r5.mFlingScroller
            boolean r6 = r6.isFinished()
            if (r6 == 0) goto L64
            if (r0 != r2) goto L60
            r6 = r3
            goto L61
        L60:
            r6 = 0
        L61:
            r5.H(r6)
        L64:
            return r3
        L65:
            boolean r5 = super.dispatchKeyEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.NumberPickerZTE.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            f0();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            f0();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mSelectionDivider;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (!this.mHasSelectorWheel) {
            return super.getAccessibilityNodeProvider();
        }
        if (this.mAccessibilityNodeProvider == null) {
            this.mAccessibilityNodeProvider = new AccessibilityNodeProviderImpl();
        }
        return this.mAccessibilityNodeProvider;
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return TOP_AND_BOTTOM_FADING_EDGE_STRENGTH;
    }

    public CharSequence getDisplayedValueForCurrentSelection() {
        return this.mSelectorIndexToStringCache.get(getValue());
    }

    public String[] getDisplayedValues() {
        String[] strArr = this.mDisplayedValues;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        return null;
    }

    public EditText getInputText() {
        return this.mInputText;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.mSolidColor;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return TOP_AND_BOTTOM_FADING_EDGE_STRENGTH;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public int getYearValue() {
        int i2 = this.mValue;
        if (i2 == this.mMaxValue && this.INPUT_DISABLE_FLAG == 1) {
            return 0;
        }
        return i2;
    }

    public void j0(int i2, int i3) {
        this.mSelectorOddWhellPaintColor = i2;
        this.mInputTextColor = i3;
        this.mSelectorWheelPaint.setColor(i2);
        this.mInputText.setTextColor(i3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mSelectionDivider;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void k0(int i2, int i3, int i4) {
        this.mSelectorEvenWhellPaintColor = i2;
        this.mInputTextColor = i3;
        this.mSelectorOddWhellPaintColor = i4;
        this.mInputText.setTextColor(i3);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setSelectorWheelItemCount(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f0();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.mHasSelectorWheel) {
            super.onDraw(canvas);
            return;
        }
        float right = (getRight() - getLeft()) / 2.0f;
        float f2 = this.mCurrentScrollOffset;
        Drawable drawable = this.mVirtualButtonPressedDrawable;
        if (drawable != null && this.mScrollState == 0) {
            if (this.mDecrementVirtualButtonPressed) {
                drawable.setState(LinearLayout.PRESSED_STATE_SET);
                this.mVirtualButtonPressedDrawable.setBounds(0, 0, getRight(), this.mTopSelectionDividerTop);
                this.mVirtualButtonPressedDrawable.draw(canvas);
            }
            if (this.mIncrementVirtualButtonPressed) {
                this.mVirtualButtonPressedDrawable.setState(LinearLayout.PRESSED_STATE_SET);
                this.mVirtualButtonPressedDrawable.setBounds(0, this.mBottomSelectionDividerBottom, getRight(), getBottom());
                this.mVirtualButtonPressedDrawable.draw(canvas);
            }
        }
        int[] iArr = this.mSelectorIndices;
        if (this.INPUT_DISABLE_FLAG == 0) {
            int i2 = 0;
            while (i2 < iArr.length) {
                String str = this.mSelectorIndexToStringCache.get(iArr[i2]);
                if (i2 != this.mSelectorMiddleItemIndex) {
                    if (Utils.f17814a) {
                        this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                    }
                    this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                    if (this.mSelectorWheelItemCount == 3) {
                        this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                    } else if (i2 % 2 == 0) {
                        this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    } else {
                        this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                    }
                    canvas.drawText(str, right, i2 > 1 ? f2 : f2 - 5.0f, this.mSelectorWheelPaint);
                } else if (this.mInputText.getVisibility() != 0) {
                    if (Utils.f17814a) {
                        this.mSelectorWheelPaint.setTypeface(this.mFinalMfvInputTypeface);
                    }
                    this.mSelectorWheelPaint.setTextSize(this.mInputSize);
                    this.mSelectorWheelPaint.setColor(this.mInputTextColor);
                    canvas.drawText(str, right, f2, this.mSelectorWheelPaint);
                }
                f2 += this.mSelectorElementHeight;
                i2++;
            }
        } else {
            int i3 = this.mValue;
            int i4 = this.displayYear;
            if (i3 > i4) {
                boolean z = Utils.f17814a;
                if (z) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                if (this.mSelectorWheelItemCount == 5) {
                    String valueOf = String.valueOf(this.displayYear - 1);
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(valueOf, right, f2, this.mSelectorWheelPaint);
                    f2 += this.mSelectorElementHeight;
                }
                String valueOf2 = String.valueOf(this.displayYear);
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText(valueOf2, right, f2, this.mSelectorWheelPaint);
                float f3 = f2 + this.mSelectorElementHeight;
                if (this.mInputText.getVisibility() != 0) {
                    if (z) {
                        this.mSelectorWheelPaint.setTypeface(this.mFinalMfvInputTypeface);
                    }
                    this.mSelectorWheelPaint.setTextSize(this.mInputSize);
                    this.mSelectorWheelPaint.setColor(this.mInputTextColor);
                    canvas.drawText("--", right, f3, this.mSelectorWheelPaint);
                }
                float f4 = f3 + this.mSelectorElementHeight;
                if (z) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText(String.valueOf(this.mMinValue), right, f4, this.mSelectorWheelPaint);
                if (this.mSelectorWheelItemCount == 5) {
                    String valueOf3 = String.valueOf(this.mMinValue + 1);
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(valueOf3, right, f4 + this.mSelectorElementHeight, this.mSelectorWheelPaint);
                }
            } else if (i3 == i4) {
                boolean z2 = Utils.f17814a;
                if (z2) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                if (this.mSelectorWheelItemCount == 5) {
                    String valueOf4 = String.valueOf(this.displayYear - 2);
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(valueOf4, right, f2, this.mSelectorWheelPaint);
                    f2 += this.mSelectorElementHeight;
                }
                String valueOf5 = String.valueOf(this.displayYear - 1);
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText(valueOf5, right, f2, this.mSelectorWheelPaint);
                float f5 = f2 + this.mSelectorElementHeight;
                if (this.mInputText.getVisibility() != 0) {
                    String valueOf6 = String.valueOf(this.displayYear);
                    this.mSelectorWheelPaint.setTypeface(this.mFinalMfvInputTypeface);
                    this.mSelectorWheelPaint.setTextSize(this.mInputSize);
                    this.mSelectorWheelPaint.setColor(this.mInputTextColor);
                    canvas.drawText(valueOf6, right, f5, this.mSelectorWheelPaint);
                }
                float f6 = f5 + this.mSelectorElementHeight;
                if (z2) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText("--", right, f6, this.mSelectorWheelPaint);
                if (this.mSelectorWheelItemCount == 5) {
                    String valueOf7 = String.valueOf(this.mMinValue);
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(valueOf7, right, f6 + this.mSelectorElementHeight, this.mSelectorWheelPaint);
                }
            } else if (this.mMinValue == i3) {
                boolean z3 = Utils.f17814a;
                if (z3) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                if (this.mSelectorWheelItemCount == 5) {
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(String.valueOf(this.displayYear), right, f2, this.mSelectorWheelPaint);
                    f2 += this.mSelectorElementHeight;
                }
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText("--", right, f2, this.mSelectorWheelPaint);
                float f7 = f2 + this.mSelectorElementHeight;
                String valueOf8 = String.valueOf(this.mValue);
                if (this.mInputText.getVisibility() != 0) {
                    if (z3) {
                        this.mSelectorWheelPaint.setTypeface(this.mFinalMfvInputTypeface);
                    }
                    this.mSelectorWheelPaint.setTextSize(this.mInputSize);
                    this.mSelectorWheelPaint.setColor(this.mInputTextColor);
                    canvas.drawText(valueOf8, right, f7, this.mSelectorWheelPaint);
                }
                float f8 = f7 + this.mSelectorElementHeight;
                String valueOf9 = String.valueOf(this.mValue + 1);
                if (z3) {
                    this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                }
                this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                canvas.drawText(valueOf9, right, f8, this.mSelectorWheelPaint);
                if (this.mSelectorWheelItemCount == 5) {
                    String valueOf10 = String.valueOf(this.mValue + 2);
                    this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                    canvas.drawText(valueOf10, right, f8 + this.mSelectorElementHeight, this.mSelectorWheelPaint);
                }
            } else {
                int i5 = 0;
                while (i5 < iArr.length) {
                    String str2 = this.mSelectorIndexToStringCache.get(iArr[i5]);
                    if (i5 != this.mSelectorMiddleItemIndex) {
                        if (Utils.f17814a) {
                            this.mSelectorWheelPaint.setTypeface(this.mOriginInputTypeface);
                        }
                        this.mSelectorWheelPaint.setTextSize(this.mSelectedSize);
                        if (this.mSelectorWheelItemCount == 3) {
                            this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                        } else if (i5 % 2 == 0) {
                            this.mSelectorWheelPaint.setColor(this.mSelectorEvenWhellPaintColor);
                        } else {
                            this.mSelectorWheelPaint.setColor(this.mSelectorOddWhellPaintColor);
                        }
                        if (str2.compareTo(String.valueOf(this.displayYear)) > 0) {
                            str2 = "--";
                        }
                        canvas.drawText(str2, right, i5 > 1 ? f2 : f2 - 5.0f, this.mSelectorWheelPaint);
                    } else if (this.mInputText.getVisibility() != 0) {
                        if (Utils.f17814a) {
                            this.mSelectorWheelPaint.setTypeface(this.mFinalMfvInputTypeface);
                        }
                        this.mSelectorWheelPaint.setTextSize(this.mInputSize);
                        this.mSelectorWheelPaint.setColor(this.mInputTextColor);
                        canvas.drawText(str2, right, f2, this.mSelectorWheelPaint);
                    }
                    f2 += this.mSelectorElementHeight;
                    i5++;
                }
            }
        }
        Drawable drawable2 = this.mSelectionDivider;
        if (drawable2 != null) {
            int i6 = this.mTopSelectionDividerTop;
            drawable2.setBounds(0, i6, getRight() + this.mdividerWith, this.mSelectionDividerHeight + i6);
            this.mSelectionDivider.draw(canvas);
            int i7 = this.mBottomSelectionDividerBottom;
            this.mSelectionDivider.setBounds(0, i7 - this.mSelectionDividerHeight, getRight() + this.mdividerWith, i7);
            this.mSelectionDivider.draw(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NumberPickerZTE.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.mMinValue + this.mValue) * this.mSelectorElementHeight);
        accessibilityEvent.setMaxScrollY((this.mMaxValue - this.mMinValue) * this.mSelectorElementHeight);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mIsMonkey || !this.mHasSelectorWheel || !isEnabled() || motionEvent.getActionMasked() != 0) {
            return false;
        }
        f0();
        Q();
        this.mInputText.setVisibility(4);
        float y = motionEvent.getY();
        this.mLastDownEventY = y;
        this.mLastDownOrMoveEventY = y;
        this.mLastDownEventTime = motionEvent.getEventTime();
        this.mIngonreMoveEvents = false;
        this.mShowSoftInputOnTap = false;
        float f2 = this.mLastDownEventY;
        if (f2 < this.mTopSelectionDividerTop) {
            if (this.mScrollState == 0) {
                this.mPressedStateHelper.a(2);
            }
        } else if (f2 > this.mBottomSelectionDividerBottom && this.mScrollState == 0) {
            this.mPressedStateHelper.a(1);
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        if (!this.mFlingScroller.isFinished()) {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
            a0(0);
        } else if (this.mAdjustScroller.isFinished()) {
            float f3 = this.mLastDownEventY;
            if (f3 < this.mTopSelectionDividerTop) {
                d0(false, ViewConfiguration.getLongPressTimeout());
            } else if (f3 > this.mBottomSelectionDividerBottom) {
                d0(true, ViewConfiguration.getLongPressTimeout());
            } else {
                this.mShowSoftInputOnTap = true;
                c0();
            }
        } else {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!this.mHasSelectorWheel) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.mInputText.getMeasuredWidth();
        int measuredHeight2 = this.mInputText.getMeasuredHeight();
        int i6 = (measuredWidth - measuredWidth2) / 2;
        int i7 = (measuredHeight - measuredHeight2) / 2;
        this.mInputText.layout(i6, i7, measuredWidth2 + i6, measuredHeight2 + i7);
        if (z) {
            T();
            S();
            this.mSelectionDividersDistance = this.mSelectorElementHeight;
            int height = getHeight();
            int i8 = this.mSelectionDividersDistance;
            int i9 = this.mSelectionDividerHeight;
            int i10 = ((height - i8) / 2) - i9;
            this.mTopSelectionDividerTop = i10;
            this.mBottomSelectionDividerBottom = i10 + (i9 * 2) + i8;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (!this.mHasSelectorWheel) {
            super.onMeasure(i2, i3);
        } else {
            super.onMeasure(X(i2, this.mMaxWidth), X(i3, this.mMaxHeight));
            setMeasuredDimension(i0(this.mMinWidth, getMeasuredWidth(), i2), i0(this.mMinHeight, getMeasuredHeight(), i3));
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mIsMonkey || !isEnabled() || !this.mHasSelectorWheel) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            g0();
            h0();
            this.mPressedStateHelper.c();
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
            int yVelocity = (int) velocityTracker.getYVelocity();
            if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                L(yVelocity);
                a0(2);
            } else {
                int y = (int) motionEvent.getY();
                int abs = (int) Math.abs(y - this.mLastDownEventY);
                long eventTime = motionEvent.getEventTime() - this.mLastDownEventTime;
                if (abs > this.mTouchSlop || eventTime >= ViewConfiguration.getTapTimeout()) {
                    K();
                } else if (this.mShowSoftInputOnTap) {
                    this.mShowSoftInputOnTap = false;
                    n0();
                } else {
                    int i2 = (y / this.mSelectorElementHeight) - this.mSelectorMiddleItemIndex;
                    if (i2 > 0) {
                        H(true);
                        this.mPressedStateHelper.b(1);
                    } else if (i2 < 0) {
                        H(false);
                        this.mPressedStateHelper.b(2);
                    }
                }
                a0(0);
            }
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        } else if (actionMasked == 2 && !this.mIngonreMoveEvents) {
            float y2 = motionEvent.getY();
            if (this.mScrollState == 1) {
                scrollBy(0, (int) (y2 - this.mLastDownOrMoveEventY));
                invalidate();
            } else if (((int) Math.abs(y2 - this.mLastDownEventY)) > this.mTouchSlop) {
                f0();
                a0(1);
            }
            this.mLastDownOrMoveEventY = y2;
        }
        return true;
    }

    @Override // android.view.View
    public boolean performClick() {
        if (!this.mHasSelectorWheel) {
            return super.performClick();
        }
        if (super.performClick()) {
            return true;
        }
        n0();
        return true;
    }

    @Override // android.view.View
    public boolean performLongClick() {
        if (!this.mHasSelectorWheel) {
            return super.performLongClick();
        }
        if (!super.performLongClick()) {
            n0();
            this.mIngonreMoveEvents = true;
        }
        return true;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        int i4;
        int[] iArr = this.mSelectorIndices;
        int i5 = this.mCurrentScrollOffset;
        boolean z = this.mWrapSelectorWheel;
        if (!z && i3 > 0 && iArr[this.mSelectorMiddleItemIndex] <= this.mMinValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        if (!z && i3 < 0 && iArr[this.mSelectorMiddleItemIndex] >= this.mMaxValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
            return;
        }
        this.mCurrentScrollOffset = i3 + i5;
        while (true) {
            int i6 = this.mCurrentScrollOffset;
            if (i6 - this.mInitialScrollOffset <= this.mSelectorTextGapHeight) {
                break;
            }
            this.mCurrentScrollOffset = i6 - this.mSelectorElementHeight;
            I(iArr);
            m0(iArr[this.mSelectorMiddleItemIndex], true);
            if (!this.mWrapSelectorWheel && iArr[this.mSelectorMiddleItemIndex] <= this.mMinValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
        while (true) {
            i4 = this.mCurrentScrollOffset;
            if (i4 - this.mInitialScrollOffset >= (-this.mSelectorTextGapHeight)) {
                break;
            }
            this.mCurrentScrollOffset = i4 + this.mSelectorElementHeight;
            R(iArr);
            m0(iArr[this.mSelectorMiddleItemIndex], true);
            if (!this.mWrapSelectorWheel && iArr[this.mSelectorMiddleItemIndex] >= this.mMaxValue) {
                this.mCurrentScrollOffset = this.mInitialScrollOffset;
            }
        }
        if (i5 != i4) {
            onScrollChanged(0, i4, 0, i5);
        }
    }

    public void setDisableInput(int i2) {
        this.INPUT_DISABLE_FLAG = 1;
        this.mInputText.setVisibility(4);
        this.displayYear = i2;
        int i3 = i2 + 1;
        this.mValue = i3;
        invalidate();
        setMaxValue(i3);
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues == strArr) {
            return;
        }
        this.mDisplayedValues = strArr;
        if (strArr != null) {
            this.mInputText.setRawInputType(524289);
        } else {
            this.mInputText.setRawInputType(2);
        }
        p0();
        V();
        o0();
    }

    public void setDividerWidth(int i2) {
        this.mdividerWith = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.mHasSelectorWheel) {
            this.mIncrementButton.setEnabled(z);
        }
        if (!this.mHasSelectorWheel) {
            this.mDecrementButton.setEnabled(z);
        }
        this.mInputText.setEnabled(z);
    }

    public void setFormatter(Formatter formatter) {
        if (formatter == this.mFormatter) {
            return;
        }
        this.mFormatter = formatter;
        V();
        p0();
    }

    public void setInputSize(int i2) {
        this.mInputText.setTextSize(1, i2);
        this.mInputSize = this.mInputText.getTextSize();
    }

    public void setInputTextColor(int i2) {
        this.mInputTextColor = i2;
        this.mInputText.setTextColor(i2);
    }

    public void setMaxValue(int i2) {
        if (this.mMaxValue == i2) {
            return;
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        this.mMaxValue = i2;
        if (i2 < this.mValue) {
            this.mValue = i2;
        }
        setWrapSelectorWheel(i2 - this.mMinValue > this.mSelectorIndices.length);
        V();
        p0();
        o0();
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
        V();
        p0();
        o0();
        invalidate();
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.mLongPressUpdateInterval = j2;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setSelectorSize(int i2) {
        EditText editText = new EditText(((LinearLayout) this).mContext);
        editText.setTextSize(1, i2);
        this.mSelectedSize = editText.getTextSize();
        this.mSelectorWheelPaint.setTextSize(editText.getTextSize());
    }

    public void setValue(int i2) {
        if (i2 == 0 && this.INPUT_DISABLE_FLAG == 1) {
            m0(this.mMaxValue, false);
        } else {
            m0(i2, false);
        }
    }

    public void setWrapSelectorWheel(boolean z) {
        boolean z2 = this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length;
        if ((!z || z2) && z != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = z;
        }
    }

    public NumberPickerZTE(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSelectorIndexToStringCache = new SparseArray<>();
        this.mSelectorWheelItemCount = 5;
        this.mSelectorMiddleItemIndex = 5 / 2;
        this.mSelectorIndices = new int[5];
        this.INPUT_DISABLE_FLAG = 0;
        this.mSelectorEvenWhellPaintColor = 1107296256;
        this.mSelectorOddWhellPaintColor = -1979711488;
        this.mInputTextColor = 36563;
        this.mdividerWith = 0;
        this.mLongPressUpdateInterval = DEFAULT_LONG_PRESS_UPDATE_INTERVAL;
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        this.mMfvInputTypeface = null;
        this.mIsMonkey = false;
        int i3 = R.layout.number_picker;
        boolean z = Utils.f17814a;
        if (z) {
            this.mMfvInputTypeface = Typeface.create("sans-serif-medium", 0);
        }
        this.mHasSelectorWheel = true;
        this.mInputTextColor = getResources().getColor(R.color.mfv_common_date_time_txt_fc);
        this.mSelectorEvenWhellPaintColor = getResources().getColor(R.color.mfv_common_tf_txt_watermark);
        this.mSelectorOddWhellPaintColor = getResources().getColor(R.color.mfv_odd_whell_paint_color);
        this.mSolidColor = 0;
        Drawable drawable = ((LinearLayout) this).mContext.getResources().getDrawable(R.drawable.number_picker_divider_zte);
        this.mSelectionDivider = drawable;
        drawable.setTintMode(PorterDuff.Mode.SRC);
        drawable.setTint(((LinearLayout) this).mContext.getResources().getColor(R.color.mfv_common_divl));
        this.mSelectionDividerHeight = 0;
        this.mSelectionDividersDistance = (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics());
        this.mMinHeight = -1;
        this.mMaxHeight = (int) TypedValue.applyDimension(1, 192.0f, getResources().getDisplayMetrics());
        this.mMinWidth = (int) TypedValue.applyDimension(1, 80.0f, getResources().getDisplayMetrics());
        this.mMaxWidth = -1;
        this.mComputeMaxWidth = true;
        this.mVirtualButtonPressedDrawable = null;
        this.mPressedStateHelper = new PressedStateHelper();
        setWillNotDraw(!true);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(i3, (ViewGroup) this, true);
        new View.OnClickListener() { // from class: com.zte.mifavor.widget.NumberPickerZTE.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NumberPickerZTE.this.Q();
                NumberPickerZTE.this.mInputText.clearFocus();
                if (view.getId() == R.id.increment) {
                    NumberPickerZTE.this.H(true);
                } else {
                    NumberPickerZTE.this.H(false);
                }
            }
        };
        new View.OnLongClickListener() { // from class: com.zte.mifavor.widget.NumberPickerZTE.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                NumberPickerZTE.this.Q();
                NumberPickerZTE.this.mInputText.clearFocus();
                if (view.getId() == R.id.increment) {
                    NumberPickerZTE.this.d0(true, 0L);
                } else {
                    NumberPickerZTE.this.d0(false, 0L);
                }
                return true;
            }
        };
        this.mIncrementButton = null;
        this.mDecrementButton = null;
        EditText editText = (EditText) findViewById(R.id.numberpicker_input);
        this.mInputText = editText;
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.zte.mifavor.widget.NumberPickerZTE.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z2) {
                if (z2) {
                    return;
                }
                NumberPickerZTE.this.mInputText.setSelection(0, 0);
                NumberPickerZTE.this.q0(view);
            }
        });
        editText.setSelectAllOnFocus(true);
        editText.setFilters(new InputFilter[]{new InputTextFilter()});
        editText.setAccessibilityLiveRegion(1);
        editText.setRawInputType(2);
        editText.setImeOptions(6);
        editText.setTextColor(this.mInputTextColor);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
        int textSize = (int) editText.getTextSize();
        this.mTextSize = textSize;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        if (z) {
            paint.setTypeface(editText.getTypeface());
        }
        paint.setColor(editText.getTextColors().getColorForState(LinearLayout.ENABLED_STATE_SET, R.color.white));
        this.mSelectorWheelPaint = paint;
        setInputSize(20);
        setSelectorSize(16);
        this.mFlingScroller = new Scroller(getContext(), null, true);
        this.mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        if (z) {
            Typeface typeface = editText.getTypeface();
            this.mOriginInputTypeface = typeface;
            this.mOriginInputTypeface = Typeface.create(typeface, Utils.f(context) + 400, false);
            this.mFinalMfvInputTypeface = Typeface.create(this.mMfvInputTypeface, Utils.f(context) + 500, false);
            editText.setTypeface(this.mMfvInputTypeface);
        }
        p0();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        if (getFocusable() == 16) {
            setFocusable(1);
            setFocusableInTouchMode(true);
        }
        setSelectorWheelItemCount(getContext().getResources().getConfiguration());
        this.mIsMonkey = Utils.w();
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
