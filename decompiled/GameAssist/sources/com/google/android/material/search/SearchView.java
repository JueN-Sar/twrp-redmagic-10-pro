package com.google.android.material.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.shape.MaterialShapeUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class SearchView extends FrameLayout implements CoordinatorLayout.AttachedBehavior, MaterialBackHandler {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SearchView;
    private static final long TALKBACK_FOCUS_CHANGE_DELAY_MS = 100;
    private boolean animatedMenuItems;
    private boolean animatedNavigationIcon;
    private boolean autoShowKeyboard;
    private final boolean backHandlingEnabled;

    @NonNull
    private final MaterialBackOrchestrator backOrchestrator;

    @ColorInt
    private final int backgroundColor;
    final View backgroundView;
    private Map<View, Integer> childImportantForAccessibilityMap;
    final ImageButton clearButton;
    final TouchObserverFrameLayout contentContainer;

    @NonNull
    private TransitionState currentTransitionState;
    final View divider;
    final Toolbar dummyToolbar;
    final EditText editText;
    private final ElevationOverlayProvider elevationOverlayProvider;
    final FrameLayout headerContainer;
    private final boolean layoutInflated;
    final ClippableRoundedCornerLayout rootView;
    final View scrim;

    @Nullable
    private SearchBar searchBar;
    final TextView searchPrefix;
    private final SearchViewAnimationHelper searchViewAnimationHelper;
    private int softInputMode;
    final View statusBarSpacer;
    private boolean statusBarSpacerEnabledOverride;
    final MaterialToolbar toolbar;
    final FrameLayout toolbarContainer;
    private final Set<TransitionListener> transitionListeners;
    private boolean useWindowInsetsController;

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior() {
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        /* renamed from: J, reason: merged with bridge method [inline-methods] */
        public boolean m(CoordinatorLayout coordinatorLayout, SearchView searchView, View view) {
            if (searchView.y() || !(view instanceof SearchBar)) {
                return false;
            }
            searchView.setupWithSearchBar((SearchBar) view);
            return false;
        }

        public Behavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.search.SearchView.SavedState.1
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
        String f15013i;

        /* renamed from: j, reason: collision with root package name */
        int f15014j;

        public SavedState(Parcel parcel) {
            this(parcel, null);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.f15013i);
            parcel.writeInt(this.f15014j);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f15013i = parcel.readString();
            this.f15014j = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public interface TransitionListener {
        void a(SearchView searchView, TransitionState transitionState, TransitionState transitionState2);
    }

    public enum TransitionState {
        HIDING,
        HIDDEN,
        SHOWING,
        SHOWN
    }

    public SearchView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSearchViewStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A() {
        if (this.editText.requestFocus()) {
            this.editText.sendAccessibilityEvent(8);
        }
        ViewUtils.w(this.editText, this.useWindowInsetsController);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(View view) {
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(View view) {
        r();
        K();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean D(View view, MotionEvent motionEvent) {
        if (!t()) {
            return false;
        }
        q();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat E(ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3, View view, WindowInsetsCompat windowInsetsCompat) {
        marginLayoutParams.leftMargin = i2 + windowInsetsCompat.j();
        marginLayoutParams.rightMargin = i3 + windowInsetsCompat.k();
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean F(View view, MotionEvent motionEvent) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat G(View view, WindowInsetsCompat windowInsetsCompat) {
        int l2 = windowInsetsCompat.l();
        setUpStatusBarSpacer(l2);
        if (!this.statusBarSpacerEnabledOverride) {
            setStatusBarSpacerEnabledInternal(l2 > 0);
        }
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat H(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
        boolean p2 = ViewUtils.p(this.toolbar);
        this.toolbar.setPadding((p2 ? relativePadding.f14803c : relativePadding.f14801a) + windowInsetsCompat.j(), relativePadding.f14802b, (p2 ? relativePadding.f14801a : relativePadding.f14803c) + windowInsetsCompat.k(), relativePadding.f14804d);
        return windowInsetsCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void I(View view) {
        W();
    }

    private void L(TransitionState transitionState, boolean z) {
        if (this.currentTransitionState.equals(transitionState)) {
            return;
        }
        if (z) {
            if (transitionState == TransitionState.SHOWN) {
                setModalForAccessibility(true);
            } else if (transitionState == TransitionState.HIDDEN) {
                setModalForAccessibility(false);
            }
        }
        TransitionState transitionState2 = this.currentTransitionState;
        this.currentTransitionState = transitionState;
        Iterator it = new LinkedHashSet(this.transitionListeners).iterator();
        while (it.hasNext()) {
            ((TransitionListener) it.next()).a(this, transitionState2, transitionState);
        }
        Y(transitionState);
    }

    private void M(boolean z, boolean z2) {
        if (z2) {
            this.toolbar.setNavigationIcon((Drawable) null);
            return;
        }
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.search.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchView.this.B(view);
            }
        });
        if (z) {
            DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(getContext());
            drawerArrowDrawable.c(MaterialColors.d(this, R.attr.colorOnSurface));
            this.toolbar.setNavigationIcon(drawerArrowDrawable);
        }
    }

    private void N() {
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
    }

    private void O() {
        this.clearButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.search.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchView.this.C(view);
            }
        });
        this.editText.addTextChangedListener(new TextWatcher() { // from class: com.google.android.material.search.SearchView.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                SearchView.this.clearButton.setVisibility(charSequence.length() > 0 ? 0 : 8);
            }
        });
    }

    private void P() {
        this.contentContainer.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.search.k
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean D;
                D = SearchView.this.D(view, motionEvent);
                return D;
            }
        });
    }

    private void Q() {
        final ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.divider.getLayoutParams();
        final int i2 = marginLayoutParams.leftMargin;
        final int i3 = marginLayoutParams.rightMargin;
        ViewCompat.x0(this.divider, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.search.c
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat E;
                E = SearchView.E(marginLayoutParams, i2, i3, view, windowInsetsCompat);
                return E;
            }
        });
    }

    private void R(int i2, String str, String str2) {
        if (i2 != -1) {
            TextViewCompat.p(this.editText, i2);
        }
        this.editText.setText(str);
        this.editText.setHint(str2);
    }

    private void S() {
        V();
        Q();
        U();
    }

    private void T() {
        this.rootView.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.material.search.j
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean F;
                F = SearchView.F(view, motionEvent);
                return F;
            }
        });
    }

    private void U() {
        setUpStatusBarSpacer(getStatusBarHeight());
        ViewCompat.x0(this.statusBarSpacer, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.search.g
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat G;
                G = SearchView.this.G(view, windowInsetsCompat);
                return G;
            }
        });
    }

    private void V() {
        ViewUtils.g(this.toolbar, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.search.f
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                WindowInsetsCompat H;
                H = SearchView.this.H(view, windowInsetsCompat, relativePadding);
                return H;
            }
        });
    }

    private void X(ViewGroup viewGroup, boolean z) {
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != this) {
                if (childAt.findViewById(this.rootView.getId()) != null) {
                    X((ViewGroup) childAt, z);
                } else if (z) {
                    this.childImportantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    ViewCompat.s0(childAt, 4);
                } else {
                    Map<View, Integer> map = this.childImportantForAccessibilityMap;
                    if (map != null && map.containsKey(childAt)) {
                        ViewCompat.s0(childAt, this.childImportantForAccessibilityMap.get(childAt).intValue());
                    }
                }
            }
        }
    }

    private void Y(TransitionState transitionState) {
        if (this.searchBar == null || !this.backHandlingEnabled) {
            return;
        }
        if (transitionState.equals(TransitionState.SHOWN)) {
            this.backOrchestrator.c();
        } else if (transitionState.equals(TransitionState.HIDDEN)) {
            this.backOrchestrator.f();
        }
    }

    private void Z() {
        MaterialToolbar materialToolbar = this.toolbar;
        if (materialToolbar == null || x(materialToolbar)) {
            return;
        }
        int defaultNavigationIconResource = getDefaultNavigationIconResource();
        if (this.searchBar == null) {
            this.toolbar.setNavigationIcon(defaultNavigationIconResource);
            return;
        }
        Drawable r2 = DrawableCompat.r(AppCompatResources.b(getContext(), defaultNavigationIconResource).mutate());
        if (this.toolbar.getNavigationIconTint() != null) {
            DrawableCompat.n(r2, this.toolbar.getNavigationIconTint().intValue());
        }
        this.toolbar.setNavigationIcon(new FadeThroughDrawable(this.searchBar.getNavigationIcon(), r2));
        a0();
    }

    private void a0() {
        ImageButton e2 = ToolbarUtils.e(this.toolbar);
        if (e2 == null) {
            return;
        }
        int i2 = this.rootView.getVisibility() == 0 ? 1 : 0;
        Drawable q2 = DrawableCompat.q(e2.getDrawable());
        if (q2 instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable) q2).e(i2);
        }
        if (q2 instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable) q2).a(i2);
        }
    }

    @Nullable
    private Window getActivityWindow() {
        Activity a2 = ContextUtils.a(getContext());
        if (a2 == null) {
            return null;
        }
        return a2.getWindow();
    }

    private float getOverlayElevation() {
        SearchBar searchBar = this.searchBar;
        return searchBar != null ? searchBar.getCompatElevation() : getResources().getDimension(R.dimen.m3_searchview_elevation);
    }

    @Px
    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void setStatusBarSpacerEnabledInternal(boolean z) {
        this.statusBarSpacer.setVisibility(z ? 0 : 8);
    }

    private void setUpBackgroundViewElevationOverlay(float f2) {
        ElevationOverlayProvider elevationOverlayProvider = this.elevationOverlayProvider;
        if (elevationOverlayProvider == null || this.backgroundView == null) {
            return;
        }
        this.backgroundView.setBackgroundColor(elevationOverlayProvider.c(this.backgroundColor, f2));
    }

    private void setUpHeaderLayout(int i2) {
        if (i2 != -1) {
            p(LayoutInflater.from(getContext()).inflate(i2, (ViewGroup) this.headerContainer, false));
        }
    }

    private void setUpStatusBarSpacer(@Px int i2) {
        if (this.statusBarSpacer.getLayoutParams().height != i2) {
            this.statusBarSpacer.getLayoutParams().height = i2;
            this.statusBarSpacer.requestLayout();
        }
    }

    private boolean v() {
        return this.currentTransitionState.equals(TransitionState.HIDDEN) || this.currentTransitionState.equals(TransitionState.HIDING);
    }

    private boolean x(Toolbar toolbar) {
        return DrawableCompat.q(toolbar.getNavigationIcon()) instanceof DrawerArrowDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z() {
        this.editText.clearFocus();
        SearchBar searchBar = this.searchBar;
        if (searchBar != null) {
            searchBar.requestFocus();
        }
        ViewUtils.o(this.editText, this.useWindowInsetsController);
    }

    public void J() {
        this.editText.postDelayed(new Runnable() { // from class: com.google.android.material.search.e
            @Override // java.lang.Runnable
            public final void run() {
                SearchView.this.A();
            }
        }, TALKBACK_FOCUS_CHANGE_DELAY_MS);
    }

    void K() {
        if (this.autoShowKeyboard) {
            J();
        }
    }

    public void W() {
        if (this.currentTransitionState.equals(TransitionState.SHOWN) || this.currentTransitionState.equals(TransitionState.SHOWING)) {
            return;
        }
        this.searchViewAnimationHelper.Z();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void a() {
        if (v() || this.searchBar == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        this.searchViewAnimationHelper.o();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (this.layoutInflated) {
            this.contentContainer.addView(view, i2, layoutParams);
        } else {
            super.addView(view, i2, layoutParams);
        }
    }

    public void b0() {
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.softInputMode = activityWindow.getAttributes().softInputMode;
        }
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void c(BackEventCompat backEventCompat) {
        if (v() || this.searchBar == null) {
            return;
        }
        this.searchViewAnimationHelper.a0(backEventCompat);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void d(BackEventCompat backEventCompat) {
        if (v() || this.searchBar == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        this.searchViewAnimationHelper.f0(backEventCompat);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void e() {
        if (v()) {
            return;
        }
        BackEventCompat S = this.searchViewAnimationHelper.S();
        if (Build.VERSION.SDK_INT < 34 || this.searchBar == null || S == null) {
            s();
        } else {
            this.searchViewAnimationHelper.p();
        }
    }

    @VisibleForTesting
    MaterialMainContainerBackHelper getBackHelper() {
        return this.searchViewAnimationHelper.r();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    @NonNull
    public CoordinatorLayout.Behavior<SearchView> getBehavior() {
        return new Behavior();
    }

    @NonNull
    public TransitionState getCurrentTransitionState() {
        return this.currentTransitionState;
    }

    @DrawableRes
    @RestrictTo
    protected int getDefaultNavigationIconResource() {
        return R.drawable.ic_arrow_back_black_24;
    }

    @NonNull
    public EditText getEditText() {
        return this.editText;
    }

    @Nullable
    public CharSequence getHint() {
        return this.editText.getHint();
    }

    @NonNull
    public TextView getSearchPrefix() {
        return this.searchPrefix;
    }

    @Nullable
    public CharSequence getSearchPrefixText() {
        return this.searchPrefix.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.softInputMode;
    }

    @NonNull
    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.editText.getText();
    }

    @NonNull
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.e(this);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        b0();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        setText(savedState.f15013i);
        setVisible(savedState.f15014j == 0);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Editable text = getText();
        savedState.f15013i = text == null ? null : text.toString();
        savedState.f15014j = this.rootView.getVisibility();
        return savedState;
    }

    public void p(View view) {
        this.headerContainer.addView(view);
        this.headerContainer.setVisibility(0);
    }

    public void q() {
        this.editText.post(new Runnable() { // from class: com.google.android.material.search.m
            @Override // java.lang.Runnable
            public final void run() {
                SearchView.this.z();
            }
        });
    }

    public void r() {
        this.editText.setText("");
    }

    public void s() {
        if (this.currentTransitionState.equals(TransitionState.HIDDEN) || this.currentTransitionState.equals(TransitionState.HIDING)) {
            return;
        }
        this.searchViewAnimationHelper.M();
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.animatedNavigationIcon = z;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.autoShowKeyboard = z;
    }

    @Override // android.view.View
    @RequiresApi
    public void setElevation(float f2) {
        super.setElevation(f2);
        setUpBackgroundViewElevationOverlay(f2);
    }

    public void setHint(@Nullable CharSequence charSequence) {
        this.editText.setHint(charSequence);
    }

    public void setMenuItemsAnimated(boolean z) {
        this.animatedMenuItems = z;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z) {
            this.childImportantForAccessibilityMap = new HashMap(viewGroup.getChildCount());
        }
        X(viewGroup, z);
        if (z) {
            return;
        }
        this.childImportantForAccessibilityMap = null;
    }

    public void setOnMenuItemClickListener(@Nullable Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void setSearchPrefixText(@Nullable CharSequence charSequence) {
        this.searchPrefix.setText(charSequence);
        this.searchPrefix.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    @RestrictTo
    public void setStatusBarSpacerEnabled(boolean z) {
        this.statusBarSpacerEnabledOverride = true;
        setStatusBarSpacerEnabledInternal(z);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(@Nullable CharSequence charSequence) {
        this.editText.setText(charSequence);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.toolbar.setTouchscreenBlocksFocus(z);
    }

    void setTransitionState(@NonNull TransitionState transitionState) {
        L(transitionState, true);
    }

    @RestrictTo
    public void setUseWindowInsetsController(boolean z) {
        this.useWindowInsetsController = z;
    }

    public void setVisible(boolean z) {
        boolean z2 = this.rootView.getVisibility() == 0;
        this.rootView.setVisibility(z ? 0 : 8);
        a0();
        L(z ? TransitionState.SHOWN : TransitionState.HIDDEN, z2 != z);
    }

    public void setupWithSearchBar(@Nullable SearchBar searchBar) {
        this.searchBar = searchBar;
        this.searchViewAnimationHelper.X(searchBar);
        if (searchBar != null) {
            searchBar.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.search.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SearchView.this.I(view);
                }
            });
            if (Build.VERSION.SDK_INT >= 34) {
                try {
                    searchBar.setHandwritingDelegatorCallback(new Runnable() { // from class: com.google.android.material.search.i
                        @Override // java.lang.Runnable
                        public final void run() {
                            SearchView.this.W();
                        }
                    });
                    this.editText.setIsHandwritingDelegate(true);
                } catch (LinkageError unused) {
                }
            }
        }
        Z();
        N();
        Y(getCurrentTransitionState());
    }

    boolean t() {
        return this.softInputMode == 48;
    }

    public boolean u() {
        return this.animatedNavigationIcon;
    }

    public boolean w() {
        return this.animatedMenuItems;
    }

    public boolean y() {
        return this.searchBar != null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SearchView(@androidx.annotation.NonNull android.content.Context r9, @androidx.annotation.Nullable android.util.AttributeSet r10, int r11) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setHint(@StringRes int i2) {
        this.editText.setHint(i2);
    }

    public void setText(@StringRes int i2) {
        this.editText.setText(i2);
    }
}
