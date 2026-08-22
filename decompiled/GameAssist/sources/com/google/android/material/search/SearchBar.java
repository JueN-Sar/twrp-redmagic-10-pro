package com.google.android.material.search;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes.dex */
public class SearchBar extends Toolbar {
    private static final int DEFAULT_SCROLL_FLAGS = 53;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SearchBar;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";

    @Nullable
    private final AccessibilityManager accessibilityManager;
    private MaterialShapeDrawable backgroundShape;

    @Nullable
    private View centerView;
    private final boolean defaultMarginsEnabled;
    private final Drawable defaultNavigationIcon;
    private boolean defaultScrollFlagsEnabled;
    private final boolean forceDefaultNavigationOnClickListener;
    private final boolean layoutInflated;
    private int menuResId;

    @Nullable
    private Integer navigationIconTint;

    @Nullable
    private Drawable originalNavigationIconBackground;
    private final SearchBarAnimationHelper searchBarAnimationHelper;
    private final TextView textView;
    private final boolean tintNavigationIcon;
    private final AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    public static abstract class OnLoadAnimationCallback {
        public void a() {
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.search.SearchBar.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: i, reason: collision with root package name */
        String f14992i;

        public SavedState(Parcel parcel) {
            this(parcel, null);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f14992i);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f14992i = parcel.readString();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public SearchBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSearchBarStyle);
    }

    private int X(int i2, int i3) {
        return i2 == 0 ? i3 : i2;
    }

    private void Y(ShapeAppearanceModel shapeAppearanceModel, int i2, float f2, float f3, int i3) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        this.backgroundShape = materialShapeDrawable;
        materialShapeDrawable.P(getContext());
        this.backgroundShape.Z(f2);
        if (f3 >= 0.0f) {
            this.backgroundShape.j0(f3, i3);
        }
        int d2 = MaterialColors.d(this, R.attr.colorControlHighlight);
        this.backgroundShape.a0(ColorStateList.valueOf(i2));
        ColorStateList valueOf = ColorStateList.valueOf(d2);
        MaterialShapeDrawable materialShapeDrawable2 = this.backgroundShape;
        ViewCompat.m0(this, new RippleDrawable(valueOf, materialShapeDrawable2, materialShapeDrawable2));
    }

    private void Z() {
        setNavigationIcon(getNavigationIcon() == null ? this.defaultNavigationIcon : getNavigationIcon());
        setNavigationIconDecorative(true);
    }

    private void a0(int i2, String str, String str2) {
        if (i2 != -1) {
            TextViewCompat.p(this.textView, i2);
        }
        setText(str);
        setHint(str2);
        if (getNavigationIcon() == null) {
            MarginLayoutParamsCompat.d((ViewGroup.MarginLayoutParams) this.textView.getLayoutParams(), getResources().getDimensionPixelSize(R.dimen.m3_searchbar_text_margin_start_no_navigation_icon));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b0(boolean z) {
        setFocusableInTouchMode(z);
    }

    private void c0() {
        View view = this.centerView;
        if (view == null) {
            return;
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
        int i2 = measuredWidth2 + measuredWidth;
        int measuredHeight = this.centerView.getMeasuredHeight();
        int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
        d0(this.centerView, measuredWidth2, measuredHeight2, i2, measuredHeight2 + measuredHeight);
    }

    private void d0(View view, int i2, int i3, int i4, int i5) {
        if (ViewCompat.v(this) == 1) {
            view.layout(getMeasuredWidth() - i4, i3, getMeasuredWidth() - i2, i5);
        } else {
            view.layout(i2, i3, i4, i5);
        }
    }

    private Drawable e0(Drawable drawable) {
        int d2;
        if (!this.tintNavigationIcon || drawable == null) {
            return drawable;
        }
        Integer num = this.navigationIconTint;
        if (num != null) {
            d2 = num.intValue();
        } else {
            d2 = MaterialColors.d(this, drawable == this.defaultNavigationIcon ? R.attr.colorOnSurfaceVariant : R.attr.colorOnSurface);
        }
        Drawable r2 = DrawableCompat.r(drawable.mutate());
        DrawableCompat.n(r2, d2);
        return r2;
    }

    private void f0(int i2, int i3) {
        View view = this.centerView;
        if (view != null) {
            view.measure(i2, i3);
        }
    }

    private void g0() {
        if (this.defaultMarginsEnabled && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.m3_searchbar_margin_horizontal);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultMarginVerticalResource());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.leftMargin = X(marginLayoutParams.leftMargin, dimensionPixelSize);
            marginLayoutParams.topMargin = X(marginLayoutParams.topMargin, dimensionPixelSize2);
            marginLayoutParams.rightMargin = X(marginLayoutParams.rightMargin, dimensionPixelSize);
            marginLayoutParams.bottomMargin = X(marginLayoutParams.bottomMargin, dimensionPixelSize2);
        }
    }

    private void h0() {
        if (Build.VERSION.SDK_INT < 34) {
            return;
        }
        boolean z = getLayoutDirection() == 1;
        ImageButton e2 = ToolbarUtils.e(this);
        int width = (e2 == null || !e2.isClickable()) ? 0 : z ? getWidth() - e2.getLeft() : e2.getRight();
        ActionMenuView b2 = ToolbarUtils.b(this);
        int right = b2 != null ? z ? b2.getRight() : getWidth() - b2.getLeft() : 0;
        float f2 = -(z ? right : width);
        if (!z) {
            width = right;
        }
        setHandwritingBoundsOffsets(f2, 0.0f, -width, 0.0f);
    }

    private void i0() {
        if (getLayoutParams() instanceof AppBarLayout.LayoutParams) {
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) getLayoutParams();
            if (this.defaultScrollFlagsEnabled) {
                if (layoutParams.c() == 0) {
                    layoutParams.g(DEFAULT_SCROLL_FLAGS);
                }
            } else if (layoutParams.c() == DEFAULT_SCROLL_FLAGS) {
                layoutParams.g(0);
            }
        }
    }

    private void j0() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager != null) {
            if (accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled()) {
                setFocusableInTouchMode(true);
            }
            addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.search.SearchBar.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                    AccessibilityManagerCompat.a(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                    AccessibilityManagerCompat.b(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }
            });
        }
    }

    private void l0(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue(NAMESPACE_APP, "title") != null) {
            throw new UnsupportedOperationException("SearchBar does not support title. Use hint or text instead.");
        }
        if (attributeSet.getAttributeValue(NAMESPACE_APP, "subtitle") != null) {
            throw new UnsupportedOperationException("SearchBar does not support subtitle. Use hint or text instead.");
        }
    }

    private void setNavigationIconDecorative(boolean z) {
        ImageButton e2 = ToolbarUtils.e(this);
        if (e2 == null) {
            return;
        }
        e2.setClickable(!z);
        e2.setFocusable(!z);
        Drawable background = e2.getBackground();
        if (background != null) {
            this.originalNavigationIconBackground = background;
        }
        e2.setBackgroundDrawable(z ? null : this.originalNavigationIconBackground);
        h0();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (this.layoutInflated && this.centerView == null && !(view instanceof ActionMenuView)) {
            this.centerView = view;
            view.setAlpha(0.0f);
        }
        super.addView(view, i2, layoutParams);
    }

    @Nullable
    public View getCenterView() {
        return this.centerView;
    }

    float getCompatElevation() {
        MaterialShapeDrawable materialShapeDrawable = this.backgroundShape;
        return materialShapeDrawable != null ? materialShapeDrawable.w() : ViewCompat.r(this);
    }

    public float getCornerSize() {
        return this.backgroundShape.I();
    }

    @DimenRes
    @RestrictTo
    protected int getDefaultMarginVerticalResource() {
        return R.dimen.m3_searchbar_margin_vertical;
    }

    @DrawableRes
    @RestrictTo
    protected int getDefaultNavigationIconResource() {
        return R.drawable.ic_search_black_24;
    }

    @Nullable
    public CharSequence getHint() {
        return this.textView.getHint();
    }

    int getMenuResId() {
        return this.menuResId;
    }

    @ColorInt
    public int getStrokeColor() {
        return this.backgroundShape.E().getDefaultColor();
    }

    @Dimension
    public float getStrokeWidth() {
        return this.backgroundShape.G();
    }

    @NonNull
    public CharSequence getText() {
        return this.textView.getText();
    }

    @NonNull
    public TextView getTextView() {
        return this.textView;
    }

    public void k0() {
        this.searchBarAnimationHelper.g(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.f(this, this.backgroundShape);
        g0();
        i0();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        accessibilityNodeInfo.setEditable(isEnabled());
        CharSequence text = getText();
        boolean isEmpty = TextUtils.isEmpty(text);
        accessibilityNodeInfo.setHintText(getHint());
        accessibilityNodeInfo.setShowingHintText(isEmpty);
        if (isEmpty) {
            text = getHint();
        }
        accessibilityNodeInfo.setText(text);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        c0();
        h0();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        f0(i2, i3);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        setText(savedState.f14992i);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        CharSequence text = getText();
        savedState.f14992i = text == null ? null : text.toString();
        return savedState;
    }

    public void setCenterView(@Nullable View view) {
        View view2 = this.centerView;
        if (view2 != null) {
            removeView(view2);
            this.centerView = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.defaultScrollFlagsEnabled = z;
        i0();
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeDrawable materialShapeDrawable = this.backgroundShape;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.Z(f2);
        }
    }

    public void setHint(@Nullable CharSequence charSequence) {
        this.textView.setHint(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(@Nullable Drawable drawable) {
        super.setNavigationIcon(e0(drawable));
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (this.forceDefaultNavigationOnClickListener) {
            return;
        }
        super.setNavigationOnClickListener(onClickListener);
        setNavigationIconDecorative(onClickListener == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.searchBarAnimationHelper.f(z);
    }

    public void setStrokeColor(@ColorInt int i2) {
        if (getStrokeColor() != i2) {
            this.backgroundShape.l0(ColorStateList.valueOf(i2));
        }
    }

    public void setStrokeWidth(@Dimension float f2) {
        if (getStrokeWidth() != f2) {
            this.backgroundShape.m0(f2);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    public void setText(@Nullable CharSequence charSequence) {
        this.textView.setText(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void z(int i2) {
        Menu menu = getMenu();
        boolean z = menu instanceof MenuBuilder;
        if (z) {
            ((MenuBuilder) menu).i0();
        }
        super.z(i2);
        this.menuResId = i2;
        if (z) {
            ((MenuBuilder) menu).h0();
        }
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

        /* renamed from: n, reason: collision with root package name */
        private boolean f14993n;

        public ScrollingViewBehavior() {
            this.f14993n = false;
        }

        private void Z(AppBarLayout appBarLayout) {
            appBarLayout.setBackgroundColor(0);
            appBarLayout.setTargetElevation(0.0f);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        protected boolean U() {
            return true;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean m(CoordinatorLayout coordinatorLayout, View view, View view2) {
            boolean m2 = super.m(coordinatorLayout, view, view2);
            if (!this.f14993n && (view2 instanceof AppBarLayout)) {
                this.f14993n = true;
                Z((AppBarLayout) view2);
            }
            return m2;
        }

        public ScrollingViewBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f14993n = false;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SearchBar(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            int r6 = com.google.android.material.search.SearchBar.DEF_STYLE_RES
            android.content.Context r11 = com.google.android.material.theme.overlay.MaterialThemeOverlay.c(r11, r12, r13, r6)
            r10.<init>(r11, r12, r13)
            r11 = -1
            r10.menuResId = r11
            com.google.android.material.search.a r0 = new com.google.android.material.search.a
            r0.<init>()
            r10.touchExplorationStateChangeListener = r0
            android.content.Context r7 = r10.getContext()
            r10.l0(r12)
            int r0 = r10.getDefaultNavigationIconResource()
            android.graphics.drawable.Drawable r0 = androidx.appcompat.content.res.AppCompatResources.b(r7, r0)
            r10.defaultNavigationIcon = r0
            com.google.android.material.search.SearchBarAnimationHelper r0 = new com.google.android.material.search.SearchBarAnimationHelper
            r0.<init>()
            r10.searchBarAnimationHelper = r0
            int[] r2 = com.google.android.material.R.styleable.SearchBar
            r8 = 0
            int[] r5 = new int[r8]
            r0 = r7
            r1 = r12
            r3 = r13
            r4 = r6
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.i(r0, r1, r2, r3, r4, r5)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r12 = com.google.android.material.shape.ShapeAppearanceModel.e(r7, r12, r13, r6)
            com.google.android.material.shape.ShapeAppearanceModel r2 = r12.m()
            int r12 = com.google.android.material.R.styleable.SearchBar_backgroundTint
            int r3 = r0.getColor(r12, r8)
            int r12 = com.google.android.material.R.styleable.SearchBar_elevation
            r13 = 0
            float r4 = r0.getDimension(r12, r13)
            int r12 = com.google.android.material.R.styleable.SearchBar_defaultMarginsEnabled
            r13 = 1
            boolean r12 = r0.getBoolean(r12, r13)
            r10.defaultMarginsEnabled = r12
            int r12 = com.google.android.material.R.styleable.SearchBar_defaultScrollFlagsEnabled
            boolean r12 = r0.getBoolean(r12, r13)
            r10.defaultScrollFlagsEnabled = r12
            int r12 = com.google.android.material.R.styleable.SearchBar_hideNavigationIcon
            boolean r12 = r0.getBoolean(r12, r8)
            int r1 = com.google.android.material.R.styleable.SearchBar_forceDefaultNavigationOnClickListener
            boolean r1 = r0.getBoolean(r1, r8)
            r10.forceDefaultNavigationOnClickListener = r1
            int r1 = com.google.android.material.R.styleable.SearchBar_tintNavigationIcon
            boolean r1 = r0.getBoolean(r1, r13)
            r10.tintNavigationIcon = r1
            int r1 = com.google.android.material.R.styleable.SearchBar_navigationIconTint
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L88
            int r1 = com.google.android.material.R.styleable.SearchBar_navigationIconTint
            int r1 = r0.getColor(r1, r11)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r10.navigationIconTint = r1
        L88:
            int r1 = com.google.android.material.R.styleable.SearchBar_android_textAppearance
            int r11 = r0.getResourceId(r1, r11)
            int r1 = com.google.android.material.R.styleable.SearchBar_android_text
            java.lang.String r1 = r0.getString(r1)
            int r5 = com.google.android.material.R.styleable.SearchBar_android_hint
            java.lang.String r5 = r0.getString(r5)
            int r6 = com.google.android.material.R.styleable.SearchBar_strokeWidth
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r6 = r0.getDimension(r6, r9)
            int r9 = com.google.android.material.R.styleable.SearchBar_strokeColor
            int r8 = r0.getColor(r9, r8)
            r0.recycle()
            if (r12 != 0) goto Lb0
            r10.Z()
        Lb0:
            r10.setClickable(r13)
            r10.setFocusable(r13)
            android.view.LayoutInflater r12 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_search_bar
            r12.inflate(r0, r10)
            r10.layoutInflated = r13
            int r12 = com.google.android.material.R.id.open_search_bar_text_view
            android.view.View r12 = r10.findViewById(r12)
            android.widget.TextView r12 = (android.widget.TextView) r12
            r10.textView = r12
            androidx.core.view.ViewCompat.q0(r10, r4)
            r10.a0(r11, r1, r5)
            r1 = r10
            r5 = r6
            r6 = r8
            r1.Y(r2, r3, r4, r5, r6)
            android.content.Context r11 = r10.getContext()
            java.lang.String r12 = "accessibility"
            java.lang.Object r11 = r11.getSystemService(r12)
            android.view.accessibility.AccessibilityManager r11 = (android.view.accessibility.AccessibilityManager) r11
            r10.accessibilityManager = r11
            r10.j0()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setHint(@StringRes int i2) {
        this.textView.setHint(i2);
    }

    public void setText(@StringRes int i2) {
        this.textView.setText(i2);
    }
}
