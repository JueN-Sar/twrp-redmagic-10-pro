package com.google.android.material.navigation;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.activity.BackEventCompat;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeableDelegate;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements MaterialBackHandler {
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private final DrawerLayout.DrawerListener backDrawerListener;
    private final MaterialBackOrchestrator backOrchestrator;
    private boolean bottomInsetScrimEnabled;

    @Px
    private int drawerLayoutCornerSize;
    private final boolean drawerLayoutCornerSizeBackAnimationEnabled;

    @Px
    private final int drawerLayoutCornerSizeBackAnimationMax;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;

    @NonNull
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final NavigationMenuPresenter presenter;
    private final ShapeableDelegate shapeableDelegate;
    private final MaterialSideContainerBackHelper sideContainerBackHelper;
    private final int[] tmpLocation;
    private boolean topInsetScrimEnabled;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int DEF_STYLE_RES = com.google.android.material.R.style.Widget_Design_NavigationView;

    public interface OnNavigationItemSelectedListener {
        boolean a(MenuItem menuItem);
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R.attr.navigationViewStyle);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    private ColorStateList l(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList a2 = AppCompatResources.a(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = a2.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{a2.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    private Drawable m(TintTypedArray tintTypedArray) {
        return n(tintTypedArray, MaterialResources.b(getContext(), tintTypedArray, com.google.android.material.R.styleable.NavigationView_itemShapeFillColor));
    }

    private Drawable n(TintTypedArray tintTypedArray, ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.b(getContext(), tintTypedArray.n(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.n(com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        materialShapeDrawable.a0(colorStateList);
        return new InsetDrawable((Drawable) materialShapeDrawable, tintTypedArray.f(com.google.android.material.R.styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.f(com.google.android.material.R.styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.f(com.google.android.material.R.styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.f(com.google.android.material.R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    private boolean o(TintTypedArray tintTypedArray) {
        return tintTypedArray.s(com.google.android.material.R.styleable.NavigationView_itemShapeAppearance) || tintTypedArray.s(com.google.android.material.R.styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void t(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (!this.drawerLayoutCornerSizeBackAnimationEnabled || this.drawerLayoutCornerSize == 0) {
            return;
        }
        this.drawerLayoutCornerSize = 0;
        v(getWidth(), getHeight());
    }

    private void v(int i2, int i3) {
        if ((getParent() instanceof DrawerLayout) && (getLayoutParams() instanceof DrawerLayout.LayoutParams)) {
            if ((this.drawerLayoutCornerSize > 0 || this.drawerLayoutCornerSizeBackAnimationEnabled) && (getBackground() instanceof MaterialShapeDrawable)) {
                boolean z = GravityCompat.b(((DrawerLayout.LayoutParams) getLayoutParams()).f3626a, ViewCompat.v(this)) == 3;
                MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
                ShapeAppearanceModel.Builder o2 = materialShapeDrawable.getShapeAppearanceModel().v().o(this.drawerLayoutCornerSize);
                if (z) {
                    o2.E(0.0f);
                    o2.v(0.0f);
                } else {
                    o2.I(0.0f);
                    o2.z(0.0f);
                }
                ShapeAppearanceModel m2 = o2.m();
                materialShapeDrawable.setShapeAppearanceModel(m2);
                this.shapeableDelegate.g(this, m2);
                this.shapeableDelegate.f(this, new RectF(0.0f, 0.0f, i2, i3));
                this.shapeableDelegate.i(this, true);
            }
        }
    }

    private Pair w() {
        ViewParent parent = getParent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((parent instanceof DrawerLayout) && (layoutParams instanceof DrawerLayout.LayoutParams)) {
            return new Pair((DrawerLayout) parent, (DrawerLayout.LayoutParams) layoutParams);
        }
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    private void x() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.material.navigation.NavigationView.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                NavigationView navigationView = NavigationView.this;
                navigationView.getLocationOnScreen(navigationView.tmpLocation);
                boolean z = true;
                boolean z2 = NavigationView.this.tmpLocation[1] == 0;
                NavigationView.this.presenter.z(z2);
                NavigationView navigationView2 = NavigationView.this;
                navigationView2.setDrawTopInsetForeground(z2 && navigationView2.s());
                NavigationView.this.setDrawLeftInsetForeground(NavigationView.this.tmpLocation[0] == 0 || NavigationView.this.tmpLocation[0] + NavigationView.this.getWidth() == 0);
                Activity a2 = ContextUtils.a(NavigationView.this.getContext());
                if (a2 != null) {
                    Rect a3 = WindowUtils.a(a2);
                    boolean z3 = a3.height() - NavigationView.this.getHeight() == NavigationView.this.tmpLocation[1];
                    boolean z4 = Color.alpha(a2.getWindow().getNavigationBarColor()) != 0;
                    NavigationView navigationView3 = NavigationView.this;
                    navigationView3.setDrawBottomInsetForeground(z3 && z4 && navigationView3.r());
                    if (a3.width() != NavigationView.this.tmpLocation[0] && a3.width() - NavigationView.this.getWidth() != NavigationView.this.tmpLocation[0]) {
                        z = false;
                    }
                    NavigationView.this.setDrawRightInsetForeground(z);
                }
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void a() {
        w();
        this.sideContainerBackHelper.f();
        u();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void c(BackEventCompat backEventCompat) {
        w();
        this.sideContainerBackHelper.j(backEventCompat);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void d(BackEventCompat backEventCompat) {
        this.sideContainerBackHelper.k(backEventCompat, ((DrawerLayout.LayoutParams) w().second).f3626a);
        if (this.drawerLayoutCornerSizeBackAnimationEnabled) {
            this.drawerLayoutCornerSize = AnimationUtils.c(0, this.drawerLayoutCornerSizeBackAnimationMax, this.sideContainerBackHelper.a(backEventCompat.a()));
            v(getWidth(), getHeight());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.shapeableDelegate.e(canvas, new CanvasCompat.CanvasOperation() { // from class: com.google.android.material.navigation.b
            @Override // com.google.android.material.canvas.CanvasCompat.CanvasOperation
            public final void a(Canvas canvas2) {
                NavigationView.this.t(canvas2);
            }
        });
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void e() {
        Pair w = w();
        DrawerLayout drawerLayout = (DrawerLayout) w.first;
        BackEventCompat c2 = this.sideContainerBackHelper.c();
        if (c2 == null || Build.VERSION.SDK_INT < 34) {
            drawerLayout.f(this);
            return;
        }
        this.sideContainerBackHelper.h(c2, ((DrawerLayout.LayoutParams) w.second).f3626a, DrawerLayoutUtils.b(drawerLayout, this), DrawerLayoutUtils.c(drawerLayout));
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    protected void f(WindowInsetsCompat windowInsetsCompat) {
        this.presenter.i(windowInsetsCompat);
    }

    @VisibleForTesting
    MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    @Nullable
    public MenuItem getCheckedItem() {
        return this.presenter.j();
    }

    @Px
    public int getDividerInsetEnd() {
        return this.presenter.k();
    }

    @Px
    public int getDividerInsetStart() {
        return this.presenter.l();
    }

    public int getHeaderCount() {
        return this.presenter.m();
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.presenter.n();
    }

    @Dimension
    public int getItemHorizontalPadding() {
        return this.presenter.o();
    }

    @Dimension
    public int getItemIconPadding() {
        return this.presenter.p();
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.presenter.s();
    }

    public int getItemMaxLines() {
        return this.presenter.q();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.presenter.r();
    }

    @Px
    public int getItemVerticalPadding() {
        return this.presenter.t();
    }

    @NonNull
    public Menu getMenu() {
        return this.menu;
    }

    @Px
    public int getSubheaderInsetEnd() {
        return this.presenter.v();
    }

    @Px
    public int getSubheaderInsetStart() {
        return this.presenter.w();
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.e(this);
        ViewParent parent = getParent();
        if ((parent instanceof DrawerLayout) && this.backOrchestrator.b()) {
            DrawerLayout drawerLayout = (DrawerLayout) parent;
            drawerLayout.L(this.backDrawerListener);
            drawerLayout.a(this.backDrawerListener);
            if (drawerLayout.A(this)) {
                this.backOrchestrator.e();
            }
        }
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            ((DrawerLayout) parent).L(this.backDrawerListener);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), this.maxWidth), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME);
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.menu.T(savedState.f14858i);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f14858i = bundle;
        this.menu.V(bundle);
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        v(i2, i3);
    }

    public View p(int i2) {
        return this.presenter.y(i2);
    }

    public void q(int i2) {
        this.presenter.U(true);
        getMenuInflater().inflate(i2, this.menu);
        this.presenter.U(false);
        this.presenter.updateMenuView(false);
    }

    public boolean r() {
        return this.bottomInsetScrimEnabled;
    }

    public boolean s() {
        return this.topInsetScrimEnabled;
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.bottomInsetScrimEnabled = z;
    }

    public void setCheckedItem(@IdRes int i2) {
        MenuItem findItem = this.menu.findItem(i2);
        if (findItem != null) {
            this.presenter.A((MenuItemImpl) findItem);
        }
    }

    public void setDividerInsetEnd(@Px int i2) {
        this.presenter.B(i2);
    }

    public void setDividerInsetStart(@Px int i2) {
        this.presenter.C(i2);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.d(this, f2);
    }

    @RestrictTo
    @VisibleForTesting
    public void setForceCompatClippingEnabled(boolean z) {
        this.shapeableDelegate.h(this, z);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.presenter.E(drawable);
    }

    public void setItemBackgroundResource(@DrawableRes int i2) {
        setItemBackground(ContextCompat.e(getContext(), i2));
    }

    public void setItemHorizontalPadding(@Dimension int i2) {
        this.presenter.G(i2);
    }

    public void setItemHorizontalPaddingResource(@DimenRes int i2) {
        this.presenter.G(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconPadding(@Dimension int i2) {
        this.presenter.H(i2);
    }

    public void setItemIconPaddingResource(int i2) {
        this.presenter.H(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconSize(@Dimension int i2) {
        this.presenter.I(i2);
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.presenter.J(colorStateList);
    }

    public void setItemMaxLines(int i2) {
        this.presenter.K(i2);
    }

    public void setItemTextAppearance(@StyleRes int i2) {
        this.presenter.L(i2);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.presenter.M(z);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.presenter.N(colorStateList);
    }

    public void setItemVerticalPadding(@Px int i2) {
        this.presenter.O(i2);
    }

    public void setItemVerticalPaddingResource(@DimenRes int i2) {
        this.presenter.O(getResources().getDimensionPixelSize(i2));
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i2) {
        super.setOverScrollMode(i2);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.P(i2);
        }
    }

    public void setSubheaderInsetEnd(@Px int i2) {
        this.presenter.R(i2);
    }

    public void setSubheaderInsetStart(@Px int i2) {
        this.presenter.S(i2);
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.topInsetScrimEnabled = z;
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.navigation.NavigationView.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
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
        public Bundle f14858i;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f14858i = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.f14858i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NavigationView(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 591
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckedItem(@NonNull MenuItem menuItem) {
        MenuItem findItem = this.menu.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.presenter.A((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
