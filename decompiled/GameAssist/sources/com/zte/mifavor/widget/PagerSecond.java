package com.zte.mifavor.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.zte.mifavor.utils.DisplayModeManager;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.Field;

/* loaded from: classes2.dex */
public class PagerSecond extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private static final int[] ATTRS = {R.attr.textSize, R.attr.textColor};
    private static final int RESET_INTERPOLATOR = 1;
    private static final int SMOOTH_SCROLL = 2;
    private static final String TAG = "PagerSecond";
    private final int COUNT2;
    private final int COUNT3;
    private final int COUNT4;
    private int COUNT_1;
    private int COUNT_2;
    private int COUNT_3;
    private int COUNT_4;
    private int COUNT_5;
    private int COUNT_6;
    private int COUNT_7;
    private boolean DBG;
    private float FACTOR_1;
    private float FACTOR_2;
    private float FACTOR_3;
    private float FACTOR_4;
    private float FACTOR_5;
    private float FACTOR_7;
    private final int POS1;
    private final int POS2;
    private int TabWidthMax;
    private int currentPosition;
    private float currentPositionOffset;
    private int currentTabBoldPos;
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    public ViewPager.OnPageChangeListener delegatePageListener;
    private int dividerColor;
    private int dividerPadding;
    private Paint dividerPaint;
    private int dividerWidth;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    private int indicatorHeight;
    private Field interpolator;
    private int lastScrollX;
    private boolean mAtStartSide;
    private Context mContext;
    private int mCurrentTabRect;
    private boolean mCutOffTab;
    private int mFocusTabStyle;
    private Handler mHandler;
    private int mIndicatorColor;
    private Interpolator mInterpolatorInstance;
    private boolean mIsMainTab;
    private int mNormalTabStyle;
    private int mSubtabFocusedColor;
    private int mTabFocusedColor;
    private ColorStateList mTabTextColor;
    private float mTabTextSize;
    private boolean mTextAllCaps;
    private boolean mWithMenu;
    private final PageListener pageListener;
    private ViewPager pager;
    private View reallyTab;
    private Paint rectPaint;
    private int scrollOffset;
    private boolean shouldExpand;
    private Scroller srcollerInstance;
    private int tabBackgroundResId;
    private int tabCount;
    private int tabPadding;
    private int tabShadowHeight;
    private LinearLayout tabsContainer;
    private int textPadding;
    private int underlineHeight;

    public interface IconTabProvider {
        int a(int i2);
    }

    public class MyTab extends TextViewZTE {
        public MyTab(Context context) {
            super(context);
        }

        @Override // android.view.View
        public boolean isFocused() {
            if (TextUtils.TruncateAt.MARQUEE.equals(getEllipsize())) {
                return true;
            }
            return super.isFocused();
        }

        @Override // android.widget.TextView, android.view.View
        protected void onMeasure(int i2, int i3) {
            if (PagerSecond.this.DBG) {
                Log.d(PagerSecond.TAG, "--tab:" + View.MeasureSpec.toString(i2));
            }
            if (PagerSecond.this.mCutOffTab) {
                super.onMeasure(i2, i3);
                return;
            }
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (mode == 0) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(PagerSecond.this.TabWidthMax, Integer.MIN_VALUE), i3);
            } else {
                if (mode != Integer.MIN_VALUE) {
                    super.onMeasure(i2, i3);
                    return;
                }
                if (size > PagerSecond.this.TabWidthMax) {
                    i2 = View.MeasureSpec.makeMeasureSpec(PagerSecond.this.TabWidthMax, Integer.MIN_VALUE);
                }
                super.onMeasure(i2, i3);
            }
        }
    }

    private class PageListener implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void d(int i2, float f2, int i3) {
            PagerSecond.this.currentPosition = i2;
            PagerSecond pagerSecond = PagerSecond.this;
            pagerSecond.currentTabBoldPos = pagerSecond.currentPosition;
            PagerSecond.this.currentPositionOffset = f2;
            PagerSecond.this.P(i2, (int) (r0.tabsContainer.getChildAt(i2).getWidth() * f2));
            PagerSecond.this.invalidate();
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSecond.this.delegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.d(i2, f2, i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void f(int i2) {
            if (i2 == 0) {
                PagerSecond pagerSecond = PagerSecond.this;
                pagerSecond.P(pagerSecond.pager.getCurrentItem(), 0);
            }
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSecond.this.delegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.f(i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void g(int i2) {
            PagerSecond.this.mCurrentTabRect = i2;
            PagerSecond.this.currentTabBoldPos = i2;
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSecond.this.delegatePageListener;
            if (onPageChangeListener != null) {
                onPageChangeListener.g(i2);
            }
            PagerSecond.this.R();
        }

        private PageListener() {
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.zte.mifavor.widget.PagerSecond.SavedState.1
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
        int f17717c;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f17717c);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f17717c = parcel.readInt();
        }
    }

    public class TabContainer extends LinearLayout {
        public TabContainer(Context context) {
            super(context);
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            if (PagerSecond.this.DBG) {
                Log.d(PagerSecond.TAG, "----container:" + View.MeasureSpec.toString(i2));
            }
            if (PagerSecond.this.mCutOffTab) {
                for (int i4 = 0; i4 < getChildCount(); i4++) {
                    View childAt = getChildAt(i4);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                    childAt.setLayoutParams(layoutParams);
                }
                super.onMeasure(i2, i3);
                return;
            }
            int childCount = getChildCount();
            float f2 = childCount <= PagerSecond.this.COUNT_1 ? PagerSecond.this.FACTOR_1 : childCount == PagerSecond.this.COUNT_2 ? PagerSecond.this.FACTOR_2 : childCount == PagerSecond.this.COUNT_3 ? PagerSecond.this.FACTOR_3 : childCount == PagerSecond.this.COUNT_4 ? PagerSecond.this.FACTOR_4 : (childCount == PagerSecond.this.COUNT_5 || childCount == PagerSecond.this.COUNT_6) ? PagerSecond.this.FACTOR_5 : childCount >= PagerSecond.this.COUNT_7 ? PagerSecond.this.FACTOR_7 : PagerSecond.this.FACTOR_1;
            int measuredWidth = PagerSecond.this.getMeasuredWidth();
            if (getChildCount() < 1 || measuredWidth <= 0) {
                super.onMeasure(i2, i3);
                return;
            }
            PagerSecond.this.TabWidthMax = (int) ((f2 * measuredWidth) / getChildCount());
            super.onMeasure(i2, i3);
            if (PagerSecond.this.DBG) {
                Log.d(PagerSecond.TAG, "<<<parent=" + getMeasuredWidth() + "," + PagerSecond.this.getMeasuredWidth() + ",TabMaxLimit=" + PagerSecond.this.TabWidthMax);
            }
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                View childAt2 = getChildAt(i7);
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(PagerSecond.this.TabWidthMax, Integer.MIN_VALUE), i3);
                i5 += childAt2.getMeasuredWidth();
                childAt2.getMeasuredWidth();
                int unused = PagerSecond.this.TabWidthMax;
                if (childAt2.getMeasuredWidth() > i6) {
                    i6 = childAt2.getMeasuredWidth();
                }
                if (PagerSecond.this.DBG) {
                    Log.d(PagerSecond.TAG, "measure " + childAt2.getId() + ":" + childAt2.getMeasuredWidth());
                }
            }
            int i8 = measuredWidth - i5;
            if (PagerSecond.this.DBG) {
                Log.d(PagerSecond.TAG, "excessSpace=" + i8 + ",maxWidth=" + i6);
            }
            if (!PagerSecond.this.mAtStartSide && i8 > 0) {
                if (measuredWidth / getChildCount() >= i6) {
                    if (PagerSecond.this.DBG) {
                        Log.d(PagerSecond.TAG, "avg tab:" + (measuredWidth / getChildCount()));
                    }
                    for (int i9 = 0; i9 < getChildCount(); i9++) {
                        getChildAt(i9).measure(View.MeasureSpec.makeMeasureSpec(measuredWidth / getChildCount(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                    }
                } else {
                    int childCount2 = i8 / getChildCount();
                    if (PagerSecond.this.DBG) {
                        Log.d(PagerSecond.TAG, "avg space:" + childCount2);
                    }
                    for (int i10 = 0; i10 < getChildCount(); i10++) {
                        View childAt3 = getChildAt(i10);
                        childAt3.measure(View.MeasureSpec.makeMeasureSpec(childAt3.getMeasuredWidth() + childCount2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), i3);
                    }
                }
            }
            for (int i11 = 0; i11 < getChildCount(); i11++) {
                View childAt4 = getChildAt(i11);
                if (PagerSecond.this.DBG) {
                    Log.d(PagerSecond.TAG, "final " + childAt4.getId() + ":" + childAt4.getMeasuredWidth());
                }
            }
        }
    }

    public PagerSecond(Context context) {
        this(context, null);
    }

    private void J(int i2, int i3) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i3);
        K(i2, imageButton);
    }

    private void K(final int i2, View view) {
        view.setId(i2);
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.widget.PagerSecond.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Log.d(PagerSecond.TAG, "click tab, position = " + i2);
                try {
                    PagerSecond.this.interpolator.setAccessible(true);
                    PagerSecond.this.interpolator.set(PagerSecond.this.srcollerInstance, AnimationUtils.loadInterpolator(PagerSecond.this.mContext, com.zte.extres.R.interpolator.tab_move));
                } catch (Exception e2) {
                    Log.d(PagerSecond.TAG, "addTab -onClick : replace interpolator error: " + e2);
                }
                PagerSecond.this.pager.setCurrentItem(i2);
                PagerSecond.this.mHandler.sendMessageDelayed(PagerSecond.this.mHandler.obtainMessage(1), 600L);
            }
        });
        if (this.mIsMainTab || this.mAtStartSide) {
            int i3 = this.tabPadding;
            int i4 = this.textPadding;
            view.setPadding(i3 + i4, 0, i3 + i4, 0);
        } else {
            int i5 = this.tabPadding;
            int i6 = this.textPadding;
            view.setPadding(i5 - i6, 0, i5 - i6, 0);
        }
        setShouldExpand(true);
        if (view instanceof TextView) {
            if (i2 == 0) {
                if (this.mIsMainTab) {
                    ((TextView) view).setTextAppearance(com.zte.extres.R.style.mfvc_tab_focused_font);
                } else {
                    ((TextView) view).setTextAppearance(com.zte.extres.R.style.mfvc_subtab_focused_font);
                }
            } else if (this.mIsMainTab) {
                ((TextView) view).setTextAppearance(com.zte.extres.R.style.mfvc_tab_normal_font);
            } else {
                ((TextView) view).setTextAppearance(com.zte.extres.R.style.mfvc_subtab_normal_font);
            }
        }
        this.tabsContainer.addView(view, i2, this.shouldExpand ? this.expandedTabLayoutParams : this.defaultTabLayoutParams);
    }

    private void L(int i2, CharSequence charSequence) {
        MyTab myTab = new MyTab(getContext());
        myTab.setText(charSequence);
        myTab.setGravity(17);
        myTab.setSingleLine();
        myTab.setEllipsize(TextUtils.TruncateAt.END);
        myTab.setAllCaps(N());
        K(i2, myTab);
    }

    private static boolean M(Context context, AttributeSet attributeSet) {
        boolean z;
        boolean z2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.zte.extres.R.styleable.PagerSlidingTabStrip, 0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(com.zte.extres.R.styleable.PagerSlidingTabStrip_android_layout_height, 0);
        if (obtainStyledAttributes.hasValue(com.zte.extres.R.styleable.PagerSlidingTabStrip_mfvIsMainTab)) {
            z2 = obtainStyledAttributes.getBoolean(com.zte.extres.R.styleable.PagerSlidingTabStrip_mfvIsMainTab, true);
            z = true;
        } else {
            z = false;
            z2 = true;
        }
        obtainStyledAttributes.recycle();
        if (z) {
            return z2;
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_appbar_height);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(com.zte.extres.R.dimen.mfvc_subtab_height);
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{130, 38});
        boolean z3 = !obtainStyledAttributes2.getBoolean(0, true) || obtainStyledAttributes2.getBoolean(1, false) || dimensionPixelOffset > (dimensionPixelSize + dimensionPixelSize2) / 2;
        obtainStyledAttributes2.recycle();
        Log.d(TAG, "isMainTab = " + z3);
        return z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void P(int r8, int r9) {
        /*
            r7 = this;
            int r9 = r7.tabCount
            if (r9 <= 0) goto L72
            android.widget.LinearLayout r9 = r7.tabsContainer
            if (r9 == 0) goto L72
            android.view.View r8 = r9.getChildAt(r8)
            if (r8 != 0) goto L10
            goto L72
        L10:
            androidx.viewpager.widget.ViewPager r8 = r7.pager
            int r8 = r8.getCurrentItem()
            android.widget.LinearLayout r9 = r7.tabsContainer
            int r9 = r9.getWidth()
            android.widget.LinearLayout r0 = r7.tabsContainer
            android.view.View r0 = r0.getChildAt(r8)
            int r0 = r0.getLeft()
            android.widget.LinearLayout r1 = r7.tabsContainer
            android.view.View r1 = r1.getChildAt(r8)
            int r1 = r1.getWidth()
            int r2 = r7.tabCount
            r3 = 1
            r4 = 0
            r5 = 2
            if (r2 == r5) goto L48
            r6 = 3
            if (r2 == r6) goto L4e
            r6 = 4
            if (r2 == r6) goto L46
            int r1 = r1 / r5
            int r0 = r0 + r1
            int r1 = r7.getWidth()
            int r1 = r1 / r5
        L44:
            int r0 = r0 - r1
            goto L58
        L46:
            if (r8 != r3) goto L4a
        L48:
            r0 = r4
            goto L58
        L4a:
            if (r8 != r5) goto L48
            r0 = r9
            goto L58
        L4e:
            if (r8 != r3) goto L48
            int r1 = r1 / r5
            int r0 = r0 + r1
            int r1 = r7.getWidth()
            int r1 = r1 / r5
            goto L44
        L58:
            if (r8 != 0) goto L5c
            r9 = r4
            goto L63
        L5c:
            int r1 = r7.tabCount
            int r1 = r1 - r3
            if (r8 != r1) goto L62
            goto L63
        L62:
            r9 = r0
        L63:
            int r8 = r7.lastScrollX
            if (r9 == r8) goto L72
            r7.lastScrollX = r9
            android.os.Handler r7 = r7.mHandler
            android.os.Message r8 = r7.obtainMessage(r5, r9, r4)
            r7.sendMessage(r8)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.mifavor.widget.PagerSecond.P(int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        if (this.DBG) {
            Log.d(TAG, "update Tab Styles in.");
        }
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            View childAt = this.tabsContainer.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextViewZTE textViewZTE = (TextViewZTE) childAt;
                if (this.currentTabBoldPos == i2) {
                    if (this.DBG) {
                        Log.d(TAG, "update Tab Styles. currentTabBoldPos=" + this.currentTabBoldPos + ", i=" + i2 + ", mIsMainTab=" + this.mIsMainTab + ", mTabFocusedColor=" + this.mTabFocusedColor + ", mTabFocusedColor=" + this.mTabFocusedColor + ", mFocusTabStyle=" + this.mFocusTabStyle);
                    }
                    if (!this.mIsMainTab) {
                        int i3 = this.mSubtabFocusedColor;
                        if (i3 == 0) {
                            i3 = com.zte.extres.R.style.mfvc_subtab_focused_font;
                        }
                        textViewZTE.setTextAppearance(i3);
                    } else if (this.mFocusTabStyle != 0) {
                        Log.d(TAG, "update Tab Styles. tab.setTextAppearance(mFocusTabStyle). mFocusTabStyle=" + this.mFocusTabStyle);
                        textViewZTE.setTextAppearance(this.mFocusTabStyle);
                    } else {
                        Log.d(TAG, "update Tab Styles. tab.setTextAppearance(R.style.mfvc_tab_focused_font).");
                        int i4 = this.mTabFocusedColor;
                        if (i4 == 0) {
                            i4 = com.zte.extres.R.style.mfvc_tab_focused_font;
                        }
                        textViewZTE.setTextAppearance(i4);
                    }
                    textViewZTE.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                    textViewZTE.setMarqueeRepeatLimit(-1);
                } else {
                    if (this.DBG) {
                        Log.d(TAG, "update Tab Styles. currentTabBoldPos=" + this.currentTabBoldPos + ", i=" + i2 + ", mIsMainTab=" + this.mIsMainTab + ", mNormalTabStyle=" + this.mNormalTabStyle);
                    }
                    if (!this.mIsMainTab) {
                        textViewZTE.setTextAppearance(com.zte.extres.R.style.mfvc_subtab_normal_font);
                    } else if (this.mNormalTabStyle != 0) {
                        Log.d(TAG, "update Tab Styles. tab.setTextAppearance(mNormalTabStyle). mNormalTabStyle=" + this.mNormalTabStyle);
                        textViewZTE.setTextAppearance(this.mNormalTabStyle);
                    } else {
                        Log.d(TAG, "update Tab Styles. tab.setTextAppearance(R.style.mfvc_tab_normal_font).");
                        textViewZTE.setTextAppearance(com.zte.extres.R.style.mfvc_tab_normal_font);
                    }
                    textViewZTE.setEllipsize(TextUtils.TruncateAt.END);
                }
                textViewZTE.setTextFontScale(5);
                textViewZTE.setTextSize(0, this.mTabTextSize);
                textViewZTE.setAllCaps(this.mTextAllCaps);
            }
        }
        if (this.DBG) {
            Log.d(TAG, "update Tab Styles out.");
        }
    }

    private int getScreenWidth() {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } catch (Exception e2) {
            Log.e(TAG, "get Screen Width error, e = ", e2);
            return -1;
        }
    }

    public boolean N() {
        return this.mTextAllCaps;
    }

    public void O() {
        this.tabsContainer.removeAllViews();
        this.tabCount = this.pager.getAdapter().e();
        for (int i2 = 0; i2 < this.tabCount; i2++) {
            if (this.pager.getAdapter() instanceof IconTabProvider) {
                J(i2, ((IconTabProvider) this.pager.getAdapter()).a(i2));
            } else {
                L(i2, this.pager.getAdapter().g(i2));
            }
        }
        R();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.zte.mifavor.widget.PagerSecond.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PagerSecond.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                PagerSecond pagerSecond = PagerSecond.this;
                pagerSecond.currentPosition = pagerSecond.pager.getCurrentItem();
                PagerSecond pagerSecond2 = PagerSecond.this;
                pagerSecond2.currentTabBoldPos = pagerSecond2.currentPosition;
                PagerSecond pagerSecond3 = PagerSecond.this;
                pagerSecond3.P(pagerSecond3.currentPosition, 0);
            }
        });
    }

    public void Q() {
        this.mAtStartSide = true;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void d(int i2, float f2, int i3) {
        this.pageListener.d(i2, f2, i3);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void f(int i2) {
        this.pageListener.f(i2);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void g(int i2) {
        this.pageListener.g(i2);
    }

    public boolean getAtStartSide() {
        return this.mAtStartSide;
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public int getDividerPadding() {
        return this.dividerPadding;
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public int getIndicatorHeight() {
        return this.indicatorHeight;
    }

    public int getScrollOffset() {
        return this.scrollOffset;
    }

    public boolean getShouldExpand() {
        return this.shouldExpand;
    }

    public int getTabBackground() {
        return this.tabBackgroundResId;
    }

    public int getTabPaddingLeftRight() {
        return this.tabPadding;
    }

    public ColorStateList getTextColor() {
        return this.mTabTextColor;
    }

    public float getTextSize() {
        return this.mTabTextSize;
    }

    public int getUnderlineHeight() {
        return this.underlineHeight;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (isInEditMode() || this.tabCount == 0) {
            return;
        }
        int height = getHeight();
        this.rectPaint.setColor(this.mIndicatorColor);
        if (!isEnabled()) {
            this.rectPaint.setAlpha(66);
        }
        View childAt = this.tabsContainer.getChildAt(this.currentPosition);
        float left = childAt.getLeft();
        float right = childAt.getRight();
        if (this.currentPositionOffset > 0.0f && (i2 = this.currentPosition) < this.tabCount - 1) {
            View childAt2 = this.tabsContainer.getChildAt(i2 + 1);
            float left2 = childAt2.getLeft();
            float right2 = childAt2.getRight();
            float f2 = this.currentPositionOffset;
            left = (left2 * f2) + ((1.0f - f2) * left);
            right = (right2 * f2) + ((1.0f - f2) * right);
        }
        if (this.mAtStartSide) {
            int i3 = this.tabPadding;
            float f3 = left + i3;
            int i4 = height - this.indicatorHeight;
            int i5 = this.tabShadowHeight;
            canvas.drawRect(f3, i4 - i5, right - i3, height - i5, this.rectPaint);
        } else {
            View childAt3 = this.tabsContainer.getChildAt(this.mCurrentTabRect);
            this.reallyTab = childAt3;
            if (childAt3 instanceof TextView) {
                TextViewZTE textViewZTE = (TextViewZTE) childAt3;
                float measureText = textViewZTE.getPaint().measureText(textViewZTE.getText().toString());
                if (measureText < this.reallyTab.getWidth()) {
                    float width = (this.reallyTab.getWidth() - measureText) / 2.0f;
                    int i6 = this.textPadding;
                    float f4 = width - i6;
                    int i7 = this.tabPadding;
                    if (f4 < i7) {
                        if (this.mIsMainTab) {
                            float f5 = left + i7;
                            int i8 = height - this.indicatorHeight;
                            int i9 = this.tabShadowHeight;
                            canvas.drawRect(f5, i8 - i9, right - i7, height - i9, this.rectPaint);
                        } else {
                            float f6 = (left + i7) - (i6 * 2);
                            int i10 = height - this.indicatorHeight;
                            int i11 = this.tabShadowHeight;
                            canvas.drawRect(f6, i10 - i11, (right - i7) + (i6 * 2), height - i11, this.rectPaint);
                        }
                    } else if (this.mIsMainTab) {
                        float f7 = (left + width) - i6;
                        int i12 = height - this.indicatorHeight;
                        int i13 = this.tabShadowHeight;
                        canvas.drawRect(f7, i12 - i13, (right - width) + i6, height - i13, this.rectPaint);
                    } else {
                        float f8 = ((left + width) - i6) - (i6 * 2);
                        int i14 = height - this.indicatorHeight;
                        int i15 = this.tabShadowHeight;
                        canvas.drawRect(f8, i14 - i15, (right - width) + i6 + (i6 * 2), height - i15, this.rectPaint);
                    }
                } else if (this.mIsMainTab) {
                    int i16 = this.tabPadding;
                    float f9 = left + i16;
                    int i17 = height - this.indicatorHeight;
                    int i18 = this.tabShadowHeight;
                    canvas.drawRect(f9, i17 - i18, right - i16, height - i18, this.rectPaint);
                } else {
                    int i19 = this.tabPadding;
                    int i20 = this.textPadding;
                    float f10 = (left + i19) - (i20 * 2);
                    int i21 = height - this.indicatorHeight;
                    int i22 = this.tabShadowHeight;
                    canvas.drawRect(f10, i21 - i22, (right - i19) + (i20 * 2), height - i22, this.rectPaint);
                }
            }
        }
        this.dividerPaint.setColor(this.dividerColor);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (!DisplayModeManager.d(getContext())) {
            super.onMeasure(i2, i3);
            return;
        }
        if (this.DBG) {
            Log.d(TAG, "------pagerSecond:" + View.MeasureSpec.toString(i2));
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        boolean z = DisplayModeManager.b(getContext()) == 2;
        boolean z2 = getResources().getConfiguration().orientation == 2;
        int screenWidth = getScreenWidth();
        if (this.DBG) {
            Log.d(TAG, "[bigA,land,menu]:" + z + "," + z2 + "," + this.mWithMenu);
        }
        if (z && z2) {
            if (!this.mWithMenu) {
                this.mCutOffTab = false;
                if (this.tabCount % 2 == 1 && size > screenWidth / 2) {
                    this.mCutOffTab = true;
                    setPaddingRelative(0, getPaddingTop(), screenWidth / (this.tabCount + 1), getPaddingBottom());
                }
            } else if (screenWidth < 0 || size <= (i4 = screenWidth / 2)) {
                super.onMeasure(i2, i3);
                return;
            } else if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                size = i4;
            }
            i2 = View.MeasureSpec.makeMeasureSpec(size, mode);
            if (this.DBG) {
                Log.d(TAG, "onMeasure--> " + View.MeasureSpec.toString(i2));
            }
        } else {
            setPaddingRelative(0, getPaddingTop(), 0, getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        Log.d(TAG, "onMeasure: width = " + getWidth() + ", height = " + getHeight());
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.currentPosition = savedState.f17717c;
        requestLayout();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f17717c = this.currentPosition;
        return savedState;
    }

    public void setCurrentTab(int i2) {
        this.currentPosition = i2;
        this.currentTabBoldPos = i2;
        P(i2, 0);
    }

    public void setDBG(boolean z) {
        this.DBG = z;
    }

    public void setDividerColor(int i2) {
        this.dividerColor = i2;
        invalidate();
    }

    public void setDividerColorResource(int i2) {
        this.dividerColor = getResources().getColor(i2);
        invalidate();
    }

    public void setDividerPadding(int i2) {
        this.dividerPadding = i2;
        invalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i2 = 0; i2 < this.tabsContainer.getChildCount(); i2++) {
            this.tabsContainer.getChildAt(i2).setEnabled(z);
        }
        this.pager.setEnabled(z);
    }

    public void setIndicatorColor(int i2) {
        this.mIndicatorColor = i2;
        invalidate();
    }

    public void setIndicatorColorResource(int i2) {
        this.mIndicatorColor = getResources().getColor(i2);
        invalidate();
    }

    public void setIndicatorHeight(int i2) {
        this.indicatorHeight = i2;
        invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.delegatePageListener = onPageChangeListener;
    }

    public void setPagerSecondPadding(int i2) {
    }

    public void setPagerSecondPaddingByCount(int i2) {
    }

    public void setScrollOffset(int i2) {
        this.scrollOffset = i2;
        invalidate();
    }

    public void setShouldExpand(boolean z) {
        this.shouldExpand = z;
        requestLayout();
    }

    public void setSubtabFocusedColor(int i2) {
        if (this.DBG) {
            Log.d(TAG, "set Subtab Focused Color in. subtabFocusedColor=" + i2);
        }
        this.mSubtabFocusedColor = i2;
        R();
    }

    public void setTabBackground(int i2) {
        this.tabBackgroundResId = i2;
    }

    public void setTabFocusedColor(int i2) {
        if (this.DBG) {
            Log.d(TAG, "set Tab Focused Color in. tabFocusedColor=" + i2);
        }
        this.mTabFocusedColor = i2;
        R();
    }

    public void setTabPaddingLeftRight(int i2) {
        this.tabPadding = i2;
        R();
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        R();
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (this.DBG) {
            Log.d(TAG, "set Text Color in. textColor=" + colorStateList);
        }
        this.mTabTextColor = colorStateList;
        R();
    }

    public void setTextColorResource(int i2) {
        this.mTabTextColor = getResources().getColorStateList(i2, null);
        R();
    }

    public void setTextSize(float f2) {
        this.mTabTextSize = TypedValue.applyDimension(2, f2, getContext().getResources().getDisplayMetrics());
        R();
    }

    public void setUnderlineHeight(int i2) {
        this.underlineHeight = i2;
        invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.pager = viewPager;
        viewPager.getContext().getTheme().applyStyle(com.zte.extres.R.style.ViewPagerForPagerSecondStyle, true);
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.pageListener);
        try {
            Class<?> cls = viewPager.getClass();
            Field field = null;
            while (field == null && cls != null) {
                try {
                    field = cls.getDeclaredField("mScroller");
                } catch (NoSuchFieldException unused) {
                    if (this.DBG) {
                        Log.d(TAG, "no mScroller for " + cls.getName());
                    }
                    cls = cls.getSuperclass();
                }
            }
            if (field != null) {
                field.setAccessible(true);
                Scroller scroller = (Scroller) field.get(viewPager);
                this.srcollerInstance = scroller;
                Field declaredField = scroller.getClass().getDeclaredField("mInterpolator");
                this.interpolator = declaredField;
                declaredField.setAccessible(true);
                this.mInterpolatorInstance = (Interpolator) this.interpolator.get(this.srcollerInstance);
            }
        } catch (Exception e2) {
            Log.d(TAG, "setViewPager: get interpolator error: " + e2);
        }
        O();
    }

    public void setWithMenu(boolean z) {
        this.mWithMenu = z;
    }

    public PagerSecond(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, M(context, attributeSet) ? com.zte.extres.R.attr.mfvTabStyle : com.zte.extres.R.attr.mfvSubTabStyle);
    }

    public PagerSecond(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, M(context, attributeSet) ? com.zte.extres.R.style.PagerSecondStyle : com.zte.extres.R.style.PagerSecondStyle_Sub);
    }

    public PagerSecond(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2);
        this.DBG = false;
        this.pageListener = new PageListener();
        this.currentPosition = 0;
        this.currentPositionOffset = 0.0f;
        this.currentTabBoldPos = 0;
        this.mCurrentTabRect = 0;
        this.dividerColor = 436207616;
        this.shouldExpand = false;
        this.mTextAllCaps = false;
        this.scrollOffset = 52;
        this.indicatorHeight = 2;
        this.underlineHeight = 4;
        this.dividerPadding = 12;
        this.tabPadding = 14;
        this.textPadding = 2;
        this.dividerWidth = 1;
        this.mFocusTabStyle = 0;
        this.mNormalTabStyle = 0;
        this.lastScrollX = 0;
        this.reallyTab = null;
        this.mTabFocusedColor = 0;
        this.mSubtabFocusedColor = 0;
        this.FACTOR_1 = 1.26f;
        this.FACTOR_2 = 1.66f;
        this.FACTOR_3 = 1.35f;
        this.FACTOR_4 = 1.67f;
        this.FACTOR_5 = 1.61f;
        this.FACTOR_7 = 1.7f;
        this.COUNT_1 = 1;
        this.COUNT_2 = 2;
        this.COUNT_3 = 3;
        this.COUNT_4 = 4;
        this.COUNT_5 = 5;
        this.COUNT_6 = 6;
        this.COUNT_7 = 7;
        this.mHandler = new Handler() { // from class: com.zte.mifavor.widget.PagerSecond.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i4 = message.what;
                if (i4 != 1) {
                    if (i4 != 2) {
                        return;
                    }
                    PagerSecond.this.smoothScrollTo(message.arg1, 0);
                    return;
                }
                try {
                    PagerSecond.this.interpolator.setAccessible(true);
                    PagerSecond.this.interpolator.set(PagerSecond.this.srcollerInstance, PagerSecond.this.mInterpolatorInstance);
                } catch (Exception e2) {
                    Log.d(PagerSecond.TAG, "mHandler RESET_INTERPOLATOR: replace interpolator error: " + e2);
                }
            }
        };
        this.COUNT2 = 2;
        this.COUNT3 = 3;
        this.COUNT4 = 4;
        this.POS1 = 1;
        this.POS2 = 2;
        this.mContext = context;
        setFillViewport(true);
        setWillNotDraw(false);
        setHorizontalFadingEdgeEnabled(true);
        setOnTouchListener(new View.OnTouchListener(this) { // from class: com.zte.mifavor.widget.PagerSecond.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        TabContainer tabContainer = new TabContainer(context);
        this.tabsContainer = tabContainer;
        tabContainer.setOrientation(0);
        this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.tabsContainer);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.scrollOffset = (int) TypedValue.applyDimension(1, this.scrollOffset, displayMetrics);
        this.indicatorHeight = (int) getResources().getDimension(com.zte.extres.R.dimen.mfvc_line_normal_height);
        this.underlineHeight = (int) TypedValue.applyDimension(1, this.underlineHeight, displayMetrics);
        this.dividerPadding = (int) TypedValue.applyDimension(1, this.dividerPadding, displayMetrics);
        this.tabPadding = (int) TypedValue.applyDimension(1, this.tabPadding, displayMetrics);
        this.textPadding = (int) TypedValue.applyDimension(1, this.textPadding, displayMetrics);
        this.dividerWidth = (int) TypedValue.applyDimension(1, this.dividerWidth, displayMetrics);
        this.tabShadowHeight = (int) TypedValue.applyDimension(1, this.tabShadowHeight, displayMetrics);
        this.mTabTextColor = getResources().getColorStateList(com.zte.extres.R.color.tab_text_color);
        this.mIndicatorColor = getResources().getColor(com.zte.extres.R.color.mfv_common_actb_ab);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.zte.extres.R.styleable.PagerSlidingTabStrip, i2, i3);
        this.mIsMainTab = obtainStyledAttributes.getBoolean(com.zte.extres.R.styleable.PagerSlidingTabStrip_mfvIsMainTab, true);
        this.mTabTextSize = obtainStyledAttributes.getDimension(com.zte.extres.R.styleable.PagerSlidingTabStrip_android_textSize, this.mTabTextSize);
        if (obtainStyledAttributes.hasValue(com.zte.extres.R.styleable.PagerSlidingTabStrip_android_textColor)) {
            this.mTabTextColor = obtainStyledAttributes.getColorStateList(com.zte.extres.R.styleable.PagerSlidingTabStrip_android_textColor);
        }
        this.mIndicatorColor = obtainStyledAttributes.getColor(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.mIndicatorColor);
        this.dividerColor = obtainStyledAttributes.getColor(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.dividerColor);
        this.indicatorHeight = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.indicatorHeight);
        this.underlineHeight = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.underlineHeight);
        this.dividerPadding = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.dividerPadding);
        this.tabPadding = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.tabPadding);
        this.tabShadowHeight = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsTabShadowHeight, this.tabShadowHeight);
        int i4 = com.zte.extres.R.drawable.item_background_borderless_material;
        this.tabBackgroundResId = i4;
        this.tabBackgroundResId = obtainStyledAttributes.getResourceId(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsTabBackground, i4);
        this.shouldExpand = obtainStyledAttributes.getBoolean(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.shouldExpand);
        this.scrollOffset = obtainStyledAttributes.getDimensionPixelSize(com.zte.extres.R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.scrollOffset);
        this.mTextAllCaps = obtainStyledAttributes.getBoolean(com.zte.extres.R.styleable.PagerSlidingTabStrip_android_textAllCaps, this.mTextAllCaps);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.rectPaint = paint;
        paint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.dividerPaint = paint2;
        paint2.setAntiAlias(true);
        this.dividerPaint.setStrokeWidth(this.dividerWidth);
        this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
        this.expandedTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (Utils.f17815b) {
            setForceDarkAllowed(false);
        }
    }
}
