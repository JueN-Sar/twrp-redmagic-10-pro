package com.google.android.material.tabs;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeUtils;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@ViewPager.DecorView
/* loaded from: classes.dex */
public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;

    @Dimension
    static final int DEFAULT_GAP_TEXT_ICON = 8;

    @Dimension
    private static final int DEFAULT_HEIGHT = 48;

    @Dimension
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;

    @Dimension
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final String LOG_TAG = "TabLayout";
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int SELECTED_INDICATOR_HEIGHT_DEFAULT = -1;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;

    @Dimension
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;

    @Nullable
    private BaseOnTabSelectedListener currentVpSelectedListener;
    private final int defaultTabTextAppearance;
    int indicatorPosition;
    boolean inlineLabel;
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;

    @Nullable
    private PagerAdapter pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;

    @Nullable
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;

    @Nullable
    private Tab selectedTab;
    private int selectedTabTextAppearance;
    float selectedTabTextSize;
    private boolean setupViewPagerImplicitly;

    @NonNull
    final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    int tabIndicatorAnimationMode;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorHeight;
    private TabIndicatorInterpolator tabIndicatorInterpolator;
    private final TimeInterpolator tabIndicatorTimeInterpolator;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;

    @NonNull
    Drawable tabSelectedIndicator;
    private int tabSelectedIndicatorColor;
    private final int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final Pools.Pool<TabView> tabViewPool;
    private final ArrayList<Tab> tabs;
    boolean unboundedRipple;

    @Nullable
    ViewPager viewPager;
    private int viewPagerScrollState;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_TabLayout;
    private static final Pools.Pool<Tab> tabPool = new Pools.SynchronizedPool(16);

    private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {

        /* renamed from: c, reason: collision with root package name */
        private boolean f15340c;

        AdapterChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnAdapterChangeListener
        public void a(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.M(pagerAdapter2, this.f15340c);
            }
        }

        void b(boolean z) {
            this.f15340c = z;
        }
    }

    @Deprecated
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void a(Tab tab);

        void b(Tab tab);

        void c(Tab tab);
    }

    public @interface LabelVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    private class PagerAdapterObserver extends DataSetObserver {
        PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.F();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.F();
        }
    }

    class SlidingTabIndicator extends LinearLayout {
        ValueAnimator indicatorAnimator;
        private int layoutDirection;

        SlidingTabIndicator(Context context) {
            super(context);
            this.layoutDirection = -1;
            setWillNotDraw(false);
        }

        private void e() {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.indicatorPosition == -1) {
                tabLayout.indicatorPosition = tabLayout.getSelectedTabPosition();
            }
            f(TabLayout.this.indicatorPosition);
        }

        private void f(int i2) {
            if (TabLayout.this.viewPagerScrollState == 0 || (TabLayout.this.getTabSelectedIndicator().getBounds().left == -1 && TabLayout.this.getTabSelectedIndicator().getBounds().right == -1)) {
                View childAt = getChildAt(i2);
                TabIndicatorInterpolator tabIndicatorInterpolator = TabLayout.this.tabIndicatorInterpolator;
                TabLayout tabLayout = TabLayout.this;
                tabIndicatorInterpolator.c(tabLayout, childAt, tabLayout.tabSelectedIndicator);
                TabLayout.this.indicatorPosition = i2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void g() {
            f(TabLayout.this.getSelectedTabPosition());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(View view, View view2, float f2) {
            if (view == null || view.getWidth() <= 0) {
                Drawable drawable = TabLayout.this.tabSelectedIndicator;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.tabSelectedIndicator.getBounds().bottom);
            } else {
                TabIndicatorInterpolator tabIndicatorInterpolator = TabLayout.this.tabIndicatorInterpolator;
                TabLayout tabLayout = TabLayout.this;
                tabIndicatorInterpolator.d(tabLayout, view, view2, f2, tabLayout.tabSelectedIndicator);
            }
            ViewCompat.Z(this);
        }

        private void k(boolean z, int i2, int i3) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.indicatorPosition == i2) {
                return;
            }
            final View childAt = getChildAt(tabLayout.getSelectedTabPosition());
            final View childAt2 = getChildAt(i2);
            if (childAt2 == null) {
                g();
                return;
            }
            TabLayout.this.indicatorPosition = i2;
            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.SlidingTabIndicator.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SlidingTabIndicator.this.j(childAt, childAt2, valueAnimator.getAnimatedFraction());
                }
            };
            if (!z) {
                this.indicatorAnimator.removeAllUpdateListeners();
                this.indicatorAnimator.addUpdateListener(animatorUpdateListener);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.indicatorAnimator = valueAnimator;
            valueAnimator.setInterpolator(TabLayout.this.tabIndicatorTimeInterpolator);
            valueAnimator.setDuration(i3);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(animatorUpdateListener);
            valueAnimator.start();
        }

        void c(int i2, int i3) {
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning() && TabLayout.this.indicatorPosition != i2) {
                this.indicatorAnimator.cancel();
            }
            k(true, i2, i3);
        }

        boolean d() {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            int height;
            int height2 = TabLayout.this.tabSelectedIndicator.getBounds().height();
            if (height2 < 0) {
                height2 = TabLayout.this.tabSelectedIndicator.getIntrinsicHeight();
            }
            int i2 = TabLayout.this.tabIndicatorGravity;
            if (i2 == 0) {
                height = getHeight() - height2;
                height2 = getHeight();
            } else if (i2 != 1) {
                height = 0;
                if (i2 != 2) {
                    height2 = i2 != 3 ? 0 : getHeight();
                }
            } else {
                height = (getHeight() - height2) / 2;
                height2 = (getHeight() + height2) / 2;
            }
            if (TabLayout.this.tabSelectedIndicator.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
                TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, height, bounds.right, height2);
                TabLayout.this.tabSelectedIndicator.draw(canvas);
            }
            super.draw(canvas);
        }

        void h(int i2, float f2) {
            TabLayout.this.indicatorPosition = Math.round(i2 + f2);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            j(getChildAt(i2), getChildAt(i2 + 1), f2);
        }

        void i(int i2) {
            Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
            TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, 0, bounds.right, i2);
            requestLayout();
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            super.onLayout(z, i2, i3, i4, i5);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                e();
            } else {
                k(false, TabLayout.this.getSelectedTabPosition(), -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i2, int i3) {
            super.onMeasure(i2, i3);
            if (View.MeasureSpec.getMode(i2) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                int childCount = getChildCount();
                int i4 = 0;
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    if (childAt.getVisibility() == 0) {
                        i4 = Math.max(i4, childAt.getMeasuredWidth());
                    }
                }
                if (i4 <= 0) {
                    return;
                }
                if (i4 * childCount <= getMeasuredWidth() - (((int) ViewUtils.h(getContext(), 16)) * 2)) {
                    boolean z = false;
                    for (int i6 = 0; i6 < childCount; i6++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i6).getLayoutParams();
                        if (layoutParams.width != i4 || layoutParams.weight != 0.0f) {
                            layoutParams.width = i4;
                            layoutParams.weight = 0.0f;
                            z = true;
                        }
                    }
                    if (!z) {
                        return;
                    }
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.tabGravity = 0;
                    tabLayout2.U(false);
                }
                super.onMeasure(i2, i3);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i2) {
            super.onRtlPropertiesChanged(i2);
        }
    }

    public static class Tab {

        /* renamed from: a, reason: collision with root package name */
        private Object f15346a;

        /* renamed from: b, reason: collision with root package name */
        private Drawable f15347b;

        /* renamed from: c, reason: collision with root package name */
        private CharSequence f15348c;

        /* renamed from: d, reason: collision with root package name */
        private CharSequence f15349d;

        /* renamed from: f, reason: collision with root package name */
        private View f15351f;

        /* renamed from: h, reason: collision with root package name */
        public TabLayout f15353h;

        /* renamed from: i, reason: collision with root package name */
        public TabView f15354i;

        /* renamed from: e, reason: collision with root package name */
        private int f15350e = -1;

        /* renamed from: g, reason: collision with root package name */
        private int f15352g = 1;

        /* renamed from: j, reason: collision with root package name */
        private int f15355j = -1;

        public View e() {
            return this.f15351f;
        }

        public Drawable f() {
            return this.f15347b;
        }

        public int g() {
            return this.f15350e;
        }

        public int h() {
            return this.f15352g;
        }

        public CharSequence i() {
            return this.f15348c;
        }

        public boolean j() {
            TabLayout tabLayout = this.f15353h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            return selectedTabPosition != -1 && selectedTabPosition == this.f15350e;
        }

        void k() {
            this.f15353h = null;
            this.f15354i = null;
            this.f15346a = null;
            this.f15347b = null;
            this.f15355j = -1;
            this.f15348c = null;
            this.f15349d = null;
            this.f15350e = -1;
            this.f15351f = null;
        }

        public void l() {
            TabLayout tabLayout = this.f15353h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.K(this);
        }

        public Tab m(CharSequence charSequence) {
            this.f15349d = charSequence;
            s();
            return this;
        }

        public Tab n(int i2) {
            return o(LayoutInflater.from(this.f15354i.getContext()).inflate(i2, (ViewGroup) this.f15354i, false));
        }

        public Tab o(View view) {
            this.f15351f = view;
            s();
            return this;
        }

        public Tab p(Drawable drawable) {
            this.f15347b = drawable;
            TabLayout tabLayout = this.f15353h;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                tabLayout.U(true);
            }
            s();
            if (BadgeUtils.f13948a && this.f15354i.k() && this.f15354i.badgeDrawable.isVisible()) {
                this.f15354i.invalidate();
            }
            return this;
        }

        void q(int i2) {
            this.f15350e = i2;
        }

        public Tab r(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f15349d) && !TextUtils.isEmpty(charSequence)) {
                this.f15354i.setContentDescription(charSequence);
            }
            this.f15348c = charSequence;
            s();
            return this;
        }

        void s() {
            TabView tabView = this.f15354i;
            if (tabView != null) {
                tabView.s();
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface TabIndicatorAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface TabIndicatorGravity {
    }

    public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /* renamed from: c, reason: collision with root package name */
        private final WeakReference f15356c;

        /* renamed from: h, reason: collision with root package name */
        private int f15357h;

        /* renamed from: i, reason: collision with root package name */
        private int f15358i;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.f15356c = new WeakReference(tabLayout);
        }

        void a() {
            this.f15358i = 0;
            this.f15357h = 0;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void d(int i2, float f2, int i3) {
            TabLayout tabLayout = (TabLayout) this.f15356c.get();
            if (tabLayout != null) {
                int i4 = this.f15358i;
                tabLayout.P(i2, f2, i4 != 2 || this.f15357h == 1, (i4 == 2 && this.f15357h == 0) ? false : true, false);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void f(int i2) {
            this.f15357h = this.f15358i;
            this.f15358i = i2;
            TabLayout tabLayout = (TabLayout) this.f15356c.get();
            if (tabLayout != null) {
                tabLayout.V(this.f15358i);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void g(int i2) {
            TabLayout tabLayout = (TabLayout) this.f15356c.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i2 || i2 >= tabLayout.getTabCount()) {
                return;
            }
            int i3 = this.f15358i;
            tabLayout.L(tabLayout.B(i2), i3 == 0 || (i3 == 2 && this.f15357h == 0));
        }
    }

    public final class TabView extends LinearLayout {

        @Nullable
        private View badgeAnchorView;

        @Nullable
        private BadgeDrawable badgeDrawable;

        @Nullable
        private Drawable baseBackgroundDrawable;

        @Nullable
        private ImageView customIconView;

        @Nullable
        private TextView customTextView;

        @Nullable
        private View customView;
        private int defaultMaxLines;
        private ImageView iconView;
        private Tab tab;
        private TextView textView;

        public TabView(Context context) {
            super(context);
            this.defaultMaxLines = 2;
            t(context);
            ViewCompat.y0(this, TabLayout.this.tabPaddingStart, TabLayout.this.tabPaddingTop, TabLayout.this.tabPaddingEnd, TabLayout.this.tabPaddingBottom);
            setGravity(17);
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            setClickable(true);
            ViewCompat.z0(this, PointerIconCompat.b(getContext(), 1002));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawBackground(Canvas canvas) {
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.baseBackgroundDrawable.draw(canvas);
            }
        }

        private void f(final View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    if (view.getVisibility() == 0) {
                        TabView.this.r(view);
                    }
                }
            });
        }

        private float g(Layout layout, int i2, float f2) {
            return layout.getLineWidth(i2) * (f2 / layout.getPaint().getTextSize());
        }

        @Nullable
        private BadgeDrawable getBadge() {
            return this.badgeDrawable;
        }

        @NonNull
        private BadgeDrawable getOrCreateBadge() {
            if (this.badgeDrawable == null) {
                this.badgeDrawable = BadgeDrawable.d(getContext());
            }
            q();
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private void h(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        private FrameLayout i() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        private FrameLayout j(View view) {
            if ((view == this.iconView || view == this.textView) && BadgeUtils.f13948a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean k() {
            return this.badgeDrawable != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void l() {
            FrameLayout frameLayout;
            if (BadgeUtils.f13948a) {
                frameLayout = i();
                addView(frameLayout, 0);
            } else {
                frameLayout = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_icon, (ViewGroup) frameLayout, false);
            this.iconView = imageView;
            frameLayout.addView(imageView, 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void m() {
            FrameLayout frameLayout;
            if (BadgeUtils.f13948a) {
                frameLayout = i();
                addView(frameLayout);
            } else {
                frameLayout = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.design_layout_tab_text, (ViewGroup) frameLayout, false);
            this.textView = textView;
            frameLayout.addView(textView);
        }

        private void o(View view) {
            if (k() && view != null) {
                h(false);
                BadgeUtils.c(this.badgeDrawable, view, j(view));
                this.badgeAnchorView = view;
            }
        }

        private void p() {
            if (k()) {
                h(true);
                View view = this.badgeAnchorView;
                if (view != null) {
                    BadgeUtils.f(this.badgeDrawable, view);
                    this.badgeAnchorView = null;
                }
            }
        }

        private void q() {
            Tab tab;
            Tab tab2;
            if (k()) {
                if (this.customView != null) {
                    p();
                    return;
                }
                if (this.iconView != null && (tab2 = this.tab) != null && tab2.f() != null) {
                    View view = this.badgeAnchorView;
                    ImageView imageView = this.iconView;
                    if (view == imageView) {
                        r(imageView);
                        return;
                    } else {
                        p();
                        o(this.iconView);
                        return;
                    }
                }
                if (this.textView == null || (tab = this.tab) == null || tab.h() != 1) {
                    p();
                    return;
                }
                View view2 = this.badgeAnchorView;
                TextView textView = this.textView;
                if (view2 == textView) {
                    r(textView);
                } else {
                    p();
                    o(this.textView);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void r(View view) {
            if (k() && view == this.badgeAnchorView) {
                BadgeUtils.g(this.badgeDrawable, view, j(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [android.graphics.drawable.RippleDrawable] */
        public void t(Context context) {
            int i2 = TabLayout.this.tabBackgroundResId;
            if (i2 != 0) {
                Drawable b2 = AppCompatResources.b(context, i2);
                this.baseBackgroundDrawable = b2;
                if (b2 != null && b2.isStateful()) {
                    this.baseBackgroundDrawable.setState(getDrawableState());
                }
            } else {
                this.baseBackgroundDrawable = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            if (TabLayout.this.tabRippleColorStateList != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList a2 = RippleUtils.a(TabLayout.this.tabRippleColorStateList);
                boolean z = TabLayout.this.unboundedRipple;
                if (z) {
                    gradientDrawable = null;
                }
                gradientDrawable = new RippleDrawable(a2, gradientDrawable, z ? null : gradientDrawable2);
            }
            ViewCompat.m0(this, gradientDrawable);
            TabLayout.this.invalidate();
        }

        private void w(TextView textView, ImageView imageView, boolean z) {
            boolean z2;
            Tab tab = this.tab;
            Drawable mutate = (tab == null || tab.f() == null) ? null : DrawableCompat.r(this.tab.f()).mutate();
            if (mutate != null) {
                DrawableCompat.o(mutate, TabLayout.this.tabIconTint);
                PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                if (mode != null) {
                    DrawableCompat.p(mutate, mode);
                }
            }
            Tab tab2 = this.tab;
            CharSequence i2 = tab2 != null ? tab2.i() : null;
            if (imageView != null) {
                if (mutate != null) {
                    imageView.setImageDrawable(mutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z3 = !TextUtils.isEmpty(i2);
            if (textView != null) {
                z2 = z3 && this.tab.f15352g == 1;
                textView.setText(z3 ? i2 : null);
                textView.setVisibility(z2 ? 0 : 8);
                if (z3) {
                    setVisibility(0);
                }
            } else {
                z2 = false;
            }
            if (z && imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int h2 = (z2 && imageView.getVisibility() == 0) ? (int) ViewUtils.h(getContext(), 8) : 0;
                if (TabLayout.this.inlineLabel) {
                    if (h2 != MarginLayoutParamsCompat.a(marginLayoutParams)) {
                        MarginLayoutParamsCompat.c(marginLayoutParams, h2);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (h2 != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = h2;
                    MarginLayoutParamsCompat.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            Tab tab3 = this.tab;
            CharSequence charSequence = tab3 != null ? tab3.f15349d : null;
            if (!z3) {
                i2 = charSequence;
            }
            TooltipCompat.a(this, i2);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null && drawable.isStateful() && this.baseBackgroundDrawable.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        int getContentHeight() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            for (int i4 = 0; i4 < 3; i4++) {
                View view = viewArr[i4];
                if (view != null && view.getVisibility() == 0) {
                    i3 = z ? Math.min(i3, view.getTop()) : view.getTop();
                    i2 = z ? Math.max(i2, view.getBottom()) : view.getBottom();
                    z = true;
                }
            }
            return i2 - i3;
        }

        int getContentWidth() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            for (int i4 = 0; i4 < 3; i4++) {
                View view = viewArr[i4];
                if (view != null && view.getVisibility() == 0) {
                    i3 = z ? Math.min(i3, view.getLeft()) : view.getLeft();
                    i2 = z ? Math.max(i2, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return i2 - i3;
        }

        @Nullable
        public Tab getTab() {
            return this.tab;
        }

        void n() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            AccessibilityNodeInfoCompat O0 = AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                O0.l0(this.badgeDrawable.i());
            }
            O0.k0(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.a(0, 1, this.tab.g(), 1, false, isSelected()));
            if (isSelected()) {
                O0.i0(false);
                O0.a0(AccessibilityNodeInfoCompat.AccessibilityActionCompat.f3486i);
            }
            O0.C0(getResources().getString(R.string.item_view_role_description));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i2, int i3) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i2 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, Integer.MIN_VALUE);
            }
            super.onMeasure(i2, i3);
            if (this.textView != null) {
                float f2 = TabLayout.this.tabTextSize;
                int i4 = this.defaultMaxLines;
                ImageView imageView = this.iconView;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.textView;
                    if (textView != null && textView.getLineCount() > 1) {
                        f2 = TabLayout.this.tabTextMultiLineSize;
                    }
                } else {
                    i4 = 1;
                }
                float textSize = this.textView.getTextSize();
                int lineCount = this.textView.getLineCount();
                int d2 = TextViewCompat.d(this.textView);
                if (f2 != textSize || (d2 >= 0 && i4 != d2)) {
                    if (TabLayout.this.mode != 1 || f2 <= textSize || lineCount != 1 || ((layout = this.textView.getLayout()) != null && g(layout, 0, f2) <= (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        this.textView.setTextSize(0, f2);
                        this.textView.setMaxLines(i4);
                        super.onMeasure(i2, i3);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean performClick = super.performClick();
            if (this.tab == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            this.tab.l();
            return true;
        }

        final void s() {
            v();
            Tab tab = this.tab;
            setSelected(tab != null && tab.j());
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            isSelected();
            super.setSelected(z);
            TextView textView = this.textView;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.iconView;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.customView;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(@Nullable Tab tab) {
            if (tab != this.tab) {
                this.tab = tab;
                s();
            }
        }

        final void u() {
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            TextView textView = this.customTextView;
            if (textView == null && this.customIconView == null) {
                w(this.textView, this.iconView, true);
            } else {
                w(textView, this.customIconView, false);
            }
        }

        final void v() {
            ViewParent parent;
            Tab tab = this.tab;
            View e2 = tab != null ? tab.e() : null;
            if (e2 != null) {
                ViewParent parent2 = e2.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(e2);
                    }
                    View view = this.customView;
                    if (view != null && (parent = view.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.customView);
                    }
                    addView(e2);
                }
                this.customView = e2;
                TextView textView = this.textView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.iconView.setImageDrawable(null);
                }
                TextView textView2 = (TextView) e2.findViewById(android.R.id.text1);
                this.customTextView = textView2;
                if (textView2 != null) {
                    this.defaultMaxLines = TextViewCompat.d(textView2);
                }
                this.customIconView = (ImageView) e2.findViewById(android.R.id.icon);
            } else {
                View view2 = this.customView;
                if (view2 != null) {
                    removeView(view2);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            if (this.customView == null) {
                if (this.iconView == null) {
                    l();
                }
                if (this.textView == null) {
                    m();
                    this.defaultMaxLines = TextViewCompat.d(this.textView);
                }
                TextViewCompat.p(this.textView, TabLayout.this.defaultTabTextAppearance);
                if (!isSelected() || TabLayout.this.selectedTabTextAppearance == -1) {
                    TextViewCompat.p(this.textView, TabLayout.this.tabTextAppearance);
                } else {
                    TextViewCompat.p(this.textView, TabLayout.this.selectedTabTextAppearance);
                }
                ColorStateList colorStateList = TabLayout.this.tabTextColors;
                if (colorStateList != null) {
                    this.textView.setTextColor(colorStateList);
                }
                w(this.textView, this.iconView, true);
                q();
                f(this.iconView);
                f(this.textView);
            } else {
                TextView textView3 = this.customTextView;
                if (textView3 != null || this.customIconView != null) {
                    w(textView3, this.customIconView, false);
                }
            }
            if (tab == null || TextUtils.isEmpty(tab.f15349d)) {
                return;
            }
            setContentDescription(tab.f15349d);
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {

        /* renamed from: a, reason: collision with root package name */
        private final ViewPager f15361a;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.f15361a = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void a(Tab tab) {
            this.f15361a.setCurrentItem(tab.g());
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void b(Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void c(Tab tab) {
        }
    }

    public TabLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.tabStyle);
    }

    private void A() {
        if (this.scrollAnimator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            valueAnimator.setInterpolator(this.tabIndicatorTimeInterpolator);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TabLayout.this.scrollTo(((Integer) valueAnimator2.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    private boolean C() {
        return getTabMode() == 0 || getTabMode() == 2;
    }

    private void J(int i2) {
        TabView tabView = (TabView) this.slidingTabIndicator.getChildAt(i2);
        this.slidingTabIndicator.removeViewAt(i2);
        if (tabView != null) {
            tabView.n();
            this.tabViewPool.release(tabView);
        }
        requestLayout();
    }

    private void R(ViewPager viewPager, boolean z, boolean z2) {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.J(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.adapterChangeListener;
            if (adapterChangeListener != null) {
                this.viewPager.I(adapterChangeListener);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.currentVpSelectedListener;
        if (baseOnTabSelectedListener != null) {
            I(baseOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.pageChangeListener.a();
            viewPager.c(this.pageChangeListener);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.currentVpSelectedListener = viewPagerOnTabSelectedListener;
            g(viewPagerOnTabSelectedListener);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                M(adapter, z);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            this.adapterChangeListener.b(z);
            viewPager.b(this.adapterChangeListener);
            N(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            M(null, false);
        }
        this.setupViewPagerImplicitly = z2;
    }

    private void S() {
        int size = this.tabs.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.tabs.get(i2).s();
        }
    }

    private void T(LinearLayout.LayoutParams layoutParams) {
        if (this.mode == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    @Dimension
    private int getDefaultHeight() {
        int size = this.tabs.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            Tab tab = this.tabs.get(i2);
            if (tab == null || tab.f() == null || TextUtils.isEmpty(tab.i())) {
                i2++;
            } else if (!this.inlineLabel) {
                return DEFAULT_HEIGHT_WITH_TEXT_ICON;
            }
        }
        return DEFAULT_HEIGHT;
    }

    private int getTabMinWidth() {
        int i2 = this.requestedTabMinWidth;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.mode;
        if (i3 == 0 || i3 == 2) {
            return this.scrollableTabMinWidth;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void l(TabItem tabItem) {
        Tab E = E();
        CharSequence charSequence = tabItem.text;
        if (charSequence != null) {
            E.r(charSequence);
        }
        Drawable drawable = tabItem.icon;
        if (drawable != null) {
            E.p(drawable);
        }
        int i2 = tabItem.customLayout;
        if (i2 != 0) {
            E.n(i2);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            E.m(tabItem.getContentDescription());
        }
        i(E);
    }

    private void m(Tab tab) {
        TabView tabView = tab.f15354i;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.slidingTabIndicator.addView(tabView, tab.g(), u());
    }

    private void n(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        l((TabItem) view);
    }

    private void o(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !ViewCompat.N(this) || this.slidingTabIndicator.d()) {
            N(i2, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int r2 = r(i2, 0.0f);
        if (scrollX != r2) {
            A();
            this.scrollAnimator.setIntValues(scrollX, r2);
            this.scrollAnimator.start();
        }
        this.slidingTabIndicator.c(i2, this.tabIndicatorAnimationDuration);
    }

    private void p(int i2) {
        if (i2 == 0) {
            Log.w(LOG_TAG, "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i2 == 1) {
            this.slidingTabIndicator.setGravity(1);
            return;
        } else if (i2 != 2) {
            return;
        }
        this.slidingTabIndicator.setGravity(8388611);
    }

    private void q() {
        int i2 = this.mode;
        ViewCompat.y0(this.slidingTabIndicator, (i2 == 0 || i2 == 2) ? Math.max(0, this.contentInsetStart - this.tabPaddingStart) : 0, 0, 0, 0);
        int i3 = this.mode;
        if (i3 == 0) {
            p(this.tabGravity);
        } else if (i3 == 1 || i3 == 2) {
            if (this.tabGravity == 2) {
                Log.w(LOG_TAG, "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.slidingTabIndicator.setGravity(1);
        }
        U(true);
    }

    private int r(int i2, float f2) {
        View childAt;
        int i3 = this.mode;
        if ((i3 != 0 && i3 != 2) || (childAt = this.slidingTabIndicator.getChildAt(i2)) == null) {
            return 0;
        }
        int i4 = i2 + 1;
        View childAt2 = i4 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i4) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return ViewCompat.v(this) == 0 ? left + i5 : left - i5;
    }

    private void s(Tab tab, int i2) {
        tab.q(i2);
        this.tabs.add(i2, tab);
        int size = this.tabs.size();
        int i3 = -1;
        for (int i4 = i2 + 1; i4 < size; i4++) {
            if (this.tabs.get(i4).g() == this.indicatorPosition) {
                i3 = i4;
            }
            this.tabs.get(i4).q(i4);
        }
        this.indicatorPosition = i3;
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.slidingTabIndicator.getChildAt(i3);
                if ((i3 != i2 || childAt.isSelected()) && (i3 == i2 || !childAt.isSelected())) {
                    childAt.setSelected(i3 == i2);
                    childAt.setActivated(i3 == i2);
                } else {
                    childAt.setSelected(i3 == i2);
                    childAt.setActivated(i3 == i2);
                    if (childAt instanceof TabView) {
                        ((TabView) childAt).v();
                    }
                }
                i3++;
            }
        }
    }

    private static ColorStateList t(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams u() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        T(layoutParams);
        return layoutParams;
    }

    private TabView w(Tab tab) {
        Pools.Pool<TabView> pool = this.tabViewPool;
        TabView tabView = pool != null ? (TabView) pool.acquire() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        tabView.setTab(tab);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab.f15349d)) {
            tabView.setContentDescription(tab.f15348c);
        } else {
            tabView.setContentDescription(tab.f15349d);
        }
        return tabView;
    }

    private void x(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).c(tab);
        }
    }

    private void y(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).a(tab);
        }
    }

    private void z(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).b(tab);
        }
    }

    public Tab B(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i2);
    }

    public boolean D() {
        return this.tabIndicatorFullWidth;
    }

    public Tab E() {
        Tab v = v();
        v.f15353h = this;
        v.f15354i = w(v);
        if (v.f15355j != -1) {
            v.f15354i.setId(v.f15355j);
        }
        return v;
    }

    void F() {
        int currentItem;
        H();
        PagerAdapter pagerAdapter = this.pagerAdapter;
        if (pagerAdapter != null) {
            int e2 = pagerAdapter.e();
            for (int i2 = 0; i2 < e2; i2++) {
                k(E().r(this.pagerAdapter.g(i2)), false);
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null || e2 <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            K(B(currentItem));
        }
    }

    protected boolean G(Tab tab) {
        return tabPool.release(tab);
    }

    public void H() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            J(childCount);
        }
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.k();
            G(next);
        }
        this.selectedTab = null;
    }

    public void I(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        this.selectedListeners.remove(baseOnTabSelectedListener);
    }

    public void K(Tab tab) {
        L(tab, true);
    }

    public void L(Tab tab, boolean z) {
        Tab tab2 = this.selectedTab;
        if (tab2 == tab) {
            if (tab2 != null) {
                x(tab);
                o(tab.g());
                return;
            }
            return;
        }
        int g2 = tab != null ? tab.g() : -1;
        if (z) {
            if ((tab2 == null || tab2.g() == -1) && g2 != -1) {
                N(g2, 0.0f, true);
            } else {
                o(g2);
            }
            if (g2 != -1) {
                setSelectedTabView(g2);
            }
        }
        this.selectedTab = tab;
        if (tab2 != null && tab2.f15353h != null) {
            z(tab2);
        }
        if (tab != null) {
            y(tab);
        }
    }

    void M(PagerAdapter pagerAdapter, boolean z) {
        DataSetObserver dataSetObserver;
        PagerAdapter pagerAdapter2 = this.pagerAdapter;
        if (pagerAdapter2 != null && (dataSetObserver = this.pagerAdapterObserver) != null) {
            pagerAdapter2.u(dataSetObserver);
        }
        this.pagerAdapter = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter.m(this.pagerAdapterObserver);
        }
        F();
    }

    public void N(int i2, float f2, boolean z) {
        O(i2, f2, z, true);
    }

    public void O(int i2, float f2, boolean z, boolean z2) {
        P(i2, f2, z, z2, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x006e, code lost:
    
        if (r10 == false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void P(int r6, float r7, boolean r8, boolean r9, boolean r10) {
        /*
            r5 = this;
            float r0 = (float) r6
            float r0 = r0 + r7
            int r0 = java.lang.Math.round(r0)
            if (r0 < 0) goto L7b
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r1 = r5.slidingTabIndicator
            int r1 = r1.getChildCount()
            if (r0 < r1) goto L12
            goto L7b
        L12:
            if (r9 == 0) goto L19
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r9 = r5.slidingTabIndicator
            r9.h(r6, r7)
        L19:
            android.animation.ValueAnimator r9 = r5.scrollAnimator
            if (r9 == 0) goto L28
            boolean r9 = r9.isRunning()
            if (r9 == 0) goto L28
            android.animation.ValueAnimator r9 = r5.scrollAnimator
            r9.cancel()
        L28:
            int r7 = r5.r(r6, r7)
            int r9 = r5.getScrollX()
            int r1 = r5.getSelectedTabPosition()
            r2 = 0
            r3 = 1
            if (r6 >= r1) goto L3a
            if (r7 >= r9) goto L48
        L3a:
            int r1 = r5.getSelectedTabPosition()
            if (r6 <= r1) goto L42
            if (r7 <= r9) goto L48
        L42:
            int r1 = r5.getSelectedTabPosition()
            if (r6 != r1) goto L4a
        L48:
            r1 = r3
            goto L4b
        L4a:
            r1 = r2
        L4b:
            int r4 = androidx.core.view.ViewCompat.v(r5)
            if (r4 != r3) goto L68
            int r1 = r5.getSelectedTabPosition()
            if (r6 >= r1) goto L59
            if (r7 <= r9) goto L70
        L59:
            int r1 = r5.getSelectedTabPosition()
            if (r6 <= r1) goto L61
            if (r7 >= r9) goto L70
        L61:
            int r9 = r5.getSelectedTabPosition()
            if (r6 != r9) goto L6a
            goto L70
        L68:
            if (r1 != 0) goto L70
        L6a:
            int r9 = r5.viewPagerScrollState
            if (r9 == r3) goto L70
            if (r10 == 0) goto L76
        L70:
            if (r6 >= 0) goto L73
            r7 = r2
        L73:
            r5.scrollTo(r7, r2)
        L76:
            if (r8 == 0) goto L7b
            r5.setSelectedTabView(r0)
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.P(int, float, boolean, boolean, boolean):void");
    }

    public void Q(ViewPager viewPager, boolean z) {
        R(viewPager, z, false);
    }

    void U(boolean z) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            View childAt = this.slidingTabIndicator.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            T((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    void V(int i2) {
        this.viewPagerScrollState = i2;
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        n(view);
    }

    public void g(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        if (this.selectedListeners.contains(baseOnTabSelectedListener)) {
            return;
        }
        this.selectedListeners.add(baseOnTabSelectedListener);
    }

    public int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.g();
        }
        return -1;
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    @Nullable
    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    @Nullable
    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    @NonNull
    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    @Nullable
    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public void h(OnTabSelectedListener onTabSelectedListener) {
        g(onTabSelectedListener);
    }

    public void i(Tab tab) {
        k(tab, this.tabs.isEmpty());
    }

    public void j(Tab tab, int i2, boolean z) {
        if (tab.f15353h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        s(tab, i2);
        m(tab);
        if (z) {
            tab.l();
        }
    }

    public void k(Tab tab, boolean z) {
        j(tab, this.tabs.size(), z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.e(this);
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                R((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
            View childAt = this.slidingTabIndicator.getChildAt(i2);
            if (childAt instanceof TabView) {
                ((TabView) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.O0(accessibilityNodeInfo).j0(AccessibilityNodeInfoCompat.CollectionInfoCompat.b(1, getTabCount(), false, 1));
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return C() && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int round = Math.round(ViewUtils.h(getContext(), getDefaultHeight()));
        int mode = View.MeasureSpec.getMode(i3);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                i3 = View.MeasureSpec.makeMeasureSpec(round + getPaddingTop() + getPaddingBottom(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
            }
        } else if (getChildCount() == 1 && View.MeasureSpec.getSize(i3) >= round) {
            getChildAt(0).setMinimumHeight(round);
        }
        int size = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) != 0) {
            int i4 = this.requestedTabMaxWidth;
            if (i4 <= 0) {
                i4 = (int) (size - ViewUtils.h(getContext(), TAB_MIN_WIDTH_MARGIN));
            }
            this.tabMaxWidth = i4;
        }
        super.onMeasure(i2, i3);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i5 = this.mode;
            if (i5 != 0) {
                if (i5 == 1) {
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        return;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), HorizontalScrollView.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
                }
                if (i5 != 2) {
                    return;
                }
            }
            if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), HorizontalScrollView.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || C()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.inlineLabel != z) {
            this.inlineLabel = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).u();
                }
            }
            q();
        }
    }

    public void setInlineLabelResource(@BoolRes int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable OnTabSelectedListener onTabSelectedListener) {
        setOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        A();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(@Nullable Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable mutate = DrawableCompat.r(drawable).mutate();
        this.tabSelectedIndicator = mutate;
        DrawableUtils.n(mutate, this.tabSelectedIndicatorColor);
        int i2 = this.tabIndicatorHeight;
        if (i2 == -1) {
            i2 = this.tabSelectedIndicator.getIntrinsicHeight();
        }
        this.slidingTabIndicator.i(i2);
    }

    public void setSelectedTabIndicatorColor(@ColorInt int i2) {
        this.tabSelectedIndicatorColor = i2;
        DrawableUtils.n(this.tabSelectedIndicator, i2);
        U(false);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.tabIndicatorGravity != i2) {
            this.tabIndicatorGravity = i2;
            ViewCompat.Z(this.slidingTabIndicator);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.tabIndicatorHeight = i2;
        this.slidingTabIndicator.i(i2);
    }

    public void setTabGravity(int i2) {
        if (this.tabGravity != i2) {
            this.tabGravity = i2;
            q();
        }
    }

    public void setTabIconTint(@Nullable ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            S();
        }
    }

    public void setTabIconTintResource(@ColorRes int i2) {
        setTabIconTint(AppCompatResources.a(getContext(), i2));
    }

    public void setTabIndicatorAnimationMode(int i2) {
        this.tabIndicatorAnimationMode = i2;
        if (i2 == 0) {
            this.tabIndicatorInterpolator = new TabIndicatorInterpolator();
            return;
        }
        if (i2 == 1) {
            this.tabIndicatorInterpolator = new ElasticTabIndicatorInterpolator();
        } else {
            if (i2 == 2) {
                this.tabIndicatorInterpolator = new FadeTabIndicatorInterpolator();
                return;
            }
            throw new IllegalArgumentException(i2 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.tabIndicatorFullWidth = z;
        this.slidingTabIndicator.g();
        ViewCompat.Z(this.slidingTabIndicator);
    }

    public void setTabMode(int i2) {
        if (i2 != this.mode) {
            this.mode = i2;
            q();
        }
    }

    public void setTabRippleColor(@Nullable ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).t(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(@ColorRes int i2) {
        setTabRippleColor(AppCompatResources.a(getContext(), i2));
    }

    public void setTabTextColors(@Nullable ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            S();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(@Nullable PagerAdapter pagerAdapter) {
        M(pagerAdapter, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.unboundedRipple != z) {
            this.unboundedRipple = z;
            for (int i2 = 0; i2 < this.slidingTabIndicator.getChildCount(); i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).t(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(@BoolRes int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        Q(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    protected Tab v() {
        Tab tab = (Tab) tabPool.acquire();
        return tab == null ? new Tab() : tab;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TabLayout(@androidx.annotation.NonNull android.content.Context r10, @androidx.annotation.Nullable android.util.AttributeSet r11, int r12) {
        /*
            Method dump skipped, instructions count: 546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        n(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void setOnTabSelectedListener(@Nullable BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.selectedListener;
        if (baseOnTabSelectedListener2 != null) {
            I(baseOnTabSelectedListener2);
        }
        this.selectedListener = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null) {
            g(baseOnTabSelectedListener);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        n(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        n(view);
    }

    public void setSelectedTabIndicator(@DrawableRes int i2) {
        if (i2 != 0) {
            setSelectedTabIndicator(AppCompatResources.b(getContext(), i2));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
