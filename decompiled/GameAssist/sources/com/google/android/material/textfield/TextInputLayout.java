package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEFAULT_PLACEHOLDER_FADE_DURATION = 87;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_TextInputLayout;
    private static final int[][] EDIT_TEXT_BACKGROUND_RIPPLE_STATE = {new int[]{android.R.attr.state_pressed}, new int[0]};
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 167;
    private static final String LOG_TAG = "TextInputLayout";
    private static final int NO_WIDTH = -1;
    private static final int PLACEHOLDER_START_DELAY = 67;
    private ValueAnimator animator;
    private boolean areCornerRadiiRtl;

    @Nullable
    private MaterialShapeDrawable boxBackground;
    private boolean boxBackgroundApplied;

    @ColorInt
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;

    @ColorInt
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;

    @Nullable
    private MaterialShapeDrawable boxUnderlineDefault;

    @Nullable
    private MaterialShapeDrawable boxUnderlineFocused;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;

    @Nullable
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;

    @Nullable
    private ColorStateList counterTextColor;

    @Nullable
    private TextView counterView;

    @Nullable
    private ColorStateList cursorColor;

    @Nullable
    private ColorStateList cursorErrorColor;

    @ColorInt
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;

    @ColorInt
    private int defaultStrokeColor;

    @ColorInt
    private int disabledColor;

    @ColorInt
    private int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;

    @Nullable
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;

    @NonNull
    private final EndCompoundLayout endLayout;
    private boolean expandedHintEnabled;
    private StateListDrawable filledDropDownMenuBackground;

    @ColorInt
    private int focusedFilledBackgroundColor;

    @ColorInt
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean globalLayoutListenerAdded;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;

    @ColorInt
    private int hoveredFilledBackgroundColor;

    @ColorInt
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;

    @NonNull
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;

    @NonNull
    private LengthCounter lengthCounter;
    private int maxEms;
    private int maxWidth;
    private int minEms;
    private int minWidth;
    private Drawable originalEditTextEndDrawable;
    int originalEditTextMinimumHeight;
    private CharSequence originalHint;
    private MaterialShapeDrawable outlinedDropDownMenuBackground;
    private boolean placeholderEnabled;

    @Nullable
    private Fade placeholderFadeIn;

    @Nullable
    private Fade placeholderFadeOut;
    private CharSequence placeholderText;
    private int placeholderTextAppearance;

    @Nullable
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    private boolean restoringSavedState;

    @NonNull
    private ShapeAppearanceModel shapeAppearanceModel;

    @Nullable
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;

    @NonNull
    private final StartCompoundLayout startLayout;
    private ColorStateList strokeErrorColor;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {

        /* renamed from: d, reason: collision with root package name */
        private final TextInputLayout f15447d;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.f15447d = textInputLayout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void g(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.g(view, accessibilityNodeInfoCompat);
            EditText editText = this.f15447d.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            CharSequence hint = this.f15447d.getHint();
            CharSequence error = this.f15447d.getError();
            CharSequence placeholderText = this.f15447d.getPlaceholderText();
            int counterMaxLength = this.f15447d.getCounterMaxLength();
            CharSequence counterOverflowDescription = this.f15447d.getCounterOverflowDescription();
            boolean isEmpty = TextUtils.isEmpty(text);
            boolean z = !isEmpty;
            boolean z2 = true;
            boolean z3 = !TextUtils.isEmpty(hint);
            boolean z4 = !this.f15447d.O();
            boolean z5 = !TextUtils.isEmpty(error);
            if (!z5 && TextUtils.isEmpty(counterOverflowDescription)) {
                z2 = false;
            }
            String charSequence = z3 ? hint.toString() : "";
            this.f15447d.startLayout.A(accessibilityNodeInfoCompat);
            if (z) {
                accessibilityNodeInfoCompat.K0(text);
            } else if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.K0(charSequence);
                if (z4 && placeholderText != null) {
                    accessibilityNodeInfoCompat.K0(charSequence + ", " + ((Object) placeholderText));
                }
            } else if (placeholderText != null) {
                accessibilityNodeInfoCompat.K0(placeholderText);
            }
            if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.s0(charSequence);
                accessibilityNodeInfoCompat.G0(isEmpty);
            }
            if (text == null || text.length() != counterMaxLength) {
                counterMaxLength = -1;
            }
            accessibilityNodeInfoCompat.v0(counterMaxLength);
            if (z2) {
                if (!z5) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.o0(error);
            }
            View t = this.f15447d.indicatorViewController.t();
            if (t != null) {
                accessibilityNodeInfoCompat.t0(t);
            }
            this.f15447d.endLayout.m().o(view, accessibilityNodeInfoCompat);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void h(View view, AccessibilityEvent accessibilityEvent) {
            super.h(view, accessibilityEvent);
            this.f15447d.endLayout.m().p(view, accessibilityEvent);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface EndIconMode {
    }

    public interface LengthCounter {
        int a(Editable editable);
    }

    public interface OnEditTextAttachedListener {
        void a(TextInputLayout textInputLayout);
    }

    public interface OnEndIconChangedListener {
        void a(TextInputLayout textInputLayout, int i2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.textfield.TextInputLayout.SavedState.1
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
        CharSequence f15448i;

        /* renamed from: j, reason: collision with root package name */
        boolean f15449j;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + ((Object) this.f15448i) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            TextUtils.writeToParcel(this.f15448i, parcel, i2);
            parcel.writeInt(this.f15449j ? 1 : 0);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f15448i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f15449j = parcel.readInt() == 1;
        }
    }

    public TextInputLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textInputStyle);
    }

    private boolean A() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void B() {
        Iterator<OnEditTextAttachedListener> it = this.editTextAttachedListeners.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    private void C(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable;
        if (this.boxUnderlineFocused == null || (materialShapeDrawable = this.boxUnderlineDefault) == null) {
            return;
        }
        materialShapeDrawable.draw(canvas);
        if (this.editText.isFocused()) {
            Rect bounds = this.boxUnderlineFocused.getBounds();
            Rect bounds2 = this.boxUnderlineDefault.getBounds();
            float F = this.collapsingTextHelper.F();
            int centerX = bounds2.centerX();
            bounds.left = AnimationUtils.c(centerX, bounds2.left, F);
            bounds.right = AnimationUtils.c(centerX, bounds2.right, F);
            this.boxUnderlineFocused.draw(canvas);
        }
    }

    private void D(Canvas canvas) {
        if (this.hintEnabled) {
            this.collapsingTextHelper.l(canvas);
        }
    }

    private void E(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z && this.hintAnimationEnabled) {
            animateToExpansionFraction(0.0f);
        } else {
            this.collapsingTextHelper.y0(0.0f);
        }
        if (A() && ((CutoutDrawable) this.boxBackground).t0()) {
            x();
        }
        this.hintExpanded = true;
        K();
        this.startLayout.l(true);
        this.endLayout.H(true);
    }

    private MaterialShapeDrawable F(boolean z) {
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mtrl_shape_corner_size_small_component);
        float f2 = z ? dimensionPixelOffset : 0.0f;
        EditText editText = this.editText;
        float popupElevation = editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText).getPopupElevation() : getResources().getDimensionPixelOffset(R.dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ShapeAppearanceModel m2 = ShapeAppearanceModel.a().E(f2).I(f2).v(dimensionPixelOffset).z(dimensionPixelOffset).m();
        EditText editText2 = this.editText;
        MaterialShapeDrawable m3 = MaterialShapeDrawable.m(getContext(), popupElevation, editText2 instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView) editText2).getDropDownBackgroundTintList() : null);
        m3.setShapeAppearanceModel(m2);
        m3.c0(0, dimensionPixelOffset2, 0, dimensionPixelOffset2);
        return m3;
    }

    private static Drawable G(MaterialShapeDrawable materialShapeDrawable, int i2, int i3, int[][] iArr) {
        return new RippleDrawable(new ColorStateList(iArr, new int[]{MaterialColors.l(i3, i2, 0.1f), i2}), materialShapeDrawable, materialShapeDrawable);
    }

    private int H(int i2, boolean z) {
        return i2 + ((z || getPrefixText() == null) ? (!z || getSuffixText() == null) ? this.editText.getCompoundPaddingLeft() : this.endLayout.y() : this.startLayout.c());
    }

    private int I(int i2, boolean z) {
        return i2 - ((z || getSuffixText() == null) ? (!z || getPrefixText() == null) ? this.editText.getCompoundPaddingRight() : this.startLayout.c() : this.endLayout.y());
    }

    private static Drawable J(Context context, MaterialShapeDrawable materialShapeDrawable, int i2, int[][] iArr) {
        int c2 = MaterialColors.c(context, R.attr.colorSurface, LOG_TAG);
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        int l2 = MaterialColors.l(i2, c2, 0.1f);
        materialShapeDrawable2.a0(new ColorStateList(iArr, new int[]{l2, 0}));
        materialShapeDrawable2.setTint(c2);
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{l2, c2});
        MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
        materialShapeDrawable3.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, materialShapeDrawable2, materialShapeDrawable3), materialShapeDrawable});
    }

    private void K() {
        TextView textView = this.placeholderTextView;
        if (textView == null || !this.placeholderEnabled) {
            return;
        }
        textView.setText((CharSequence) null);
        TransitionManager.a(this.inputFrame, this.placeholderFadeOut);
        this.placeholderTextView.setVisibility(4);
    }

    private boolean P() {
        return c0() || (this.counterView != null && this.counterOverflowed);
    }

    private boolean R() {
        return this.boxBackgroundMode == 1 && this.editText.getMinLines() <= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int S(Editable editable) {
        if (editable != null) {
            return editable.length();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void T() {
        this.editText.requestLayout();
    }

    private void U() {
        o();
        q0();
        z0();
        g0();
        k();
        if (this.boxBackgroundMode != 0) {
            s0();
        }
        a0();
    }

    private void V() {
        if (A()) {
            RectF rectF = this.tmpRectF;
            this.collapsingTextHelper.o(rectF, this.editText.getWidth(), this.editText.getGravity());
            if (rectF.width() <= 0.0f || rectF.height() <= 0.0f) {
                return;
            }
            n(rectF);
            rectF.offset(-getPaddingLeft(), ((-getPaddingTop()) - (rectF.height() / 2.0f)) + this.boxStrokeWidthPx);
            ((CutoutDrawable) this.boxBackground).w0(rectF);
        }
    }

    private void W() {
        if (!A() || this.hintExpanded) {
            return;
        }
        x();
        V();
    }

    private static void X(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                X((ViewGroup) childAt, z);
            }
        }
    }

    private void Z() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void a0() {
        EditText editText = this.editText;
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.boxBackgroundMode;
                if (i2 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                } else if (i2 == 1) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateFilledDropDownMenuBackground());
                }
            }
        }
    }

    private boolean d0() {
        return (this.endLayout.G() || ((this.endLayout.A() && L()) || this.endLayout.w() != null)) && this.endLayout.getMeasuredWidth() > 0;
    }

    private boolean e0() {
        return (getStartIconDrawable() != null || (getPrefixText() != null && getPrefixTextView().getVisibility() == 0)) && this.startLayout.getMeasuredWidth() > 0;
    }

    private void f0() {
        if (this.placeholderTextView == null || !this.placeholderEnabled || TextUtils.isEmpty(this.placeholderText)) {
            return;
        }
        this.placeholderTextView.setText(this.placeholderText);
        TransitionManager.a(this.inputFrame, this.placeholderFadeIn);
        this.placeholderTextView.setVisibility(0);
        this.placeholderTextView.bringToFront();
        announceForAccessibility(this.placeholderText);
    }

    private void g0() {
        if (this.boxBackgroundMode == 1) {
            if (MaterialResources.k(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (MaterialResources.j(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
    }

    @Nullable
    private Drawable getEditTextBoxBackground() {
        EditText editText = this.editText;
        if (!(editText instanceof AutoCompleteTextView) || EditTextUtils.a(editText)) {
            return this.boxBackground;
        }
        int d2 = MaterialColors.d(this.editText, R.attr.colorControlHighlight);
        int i2 = this.boxBackgroundMode;
        if (i2 == 2) {
            return J(getContext(), this.boxBackground, d2, EDIT_TEXT_BACKGROUND_RIPPLE_STATE);
        }
        if (i2 == 1) {
            return G(this.boxBackground, this.boxBackgroundColor, d2, EDIT_TEXT_BACKGROUND_RIPPLE_STATE);
        }
        return null;
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if (this.filledDropDownMenuBackground == null) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            this.filledDropDownMenuBackground = stateListDrawable;
            stateListDrawable.addState(new int[]{android.R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
            this.filledDropDownMenuBackground.addState(new int[0], F(false));
        }
        return this.filledDropDownMenuBackground;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.outlinedDropDownMenuBackground == null) {
            this.outlinedDropDownMenuBackground = F(true);
        }
        return this.outlinedDropDownMenuBackground;
    }

    private void h0(Rect rect) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderlineDefault;
        if (materialShapeDrawable != null) {
            int i2 = rect.bottom;
            materialShapeDrawable.setBounds(rect.left, i2 - this.boxStrokeWidthDefaultPx, rect.right, i2);
        }
        MaterialShapeDrawable materialShapeDrawable2 = this.boxUnderlineFocused;
        if (materialShapeDrawable2 != null) {
            int i3 = rect.bottom;
            materialShapeDrawable2.setBounds(rect.left, i3 - this.boxStrokeWidthFocusedPx, rect.right, i3);
        }
    }

    private void i0() {
        if (this.counterView != null) {
            EditText editText = this.editText;
            j0(editText == null ? null : editText.getText());
        }
    }

    private void j() {
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            this.inputFrame.addView(textView);
            this.placeholderTextView.setVisibility(0);
        }
    }

    private void k() {
        if (this.editText == null || this.boxBackgroundMode != 1) {
            return;
        }
        if (MaterialResources.k(getContext())) {
            EditText editText = this.editText;
            ViewCompat.y0(editText, ViewCompat.z(editText), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_top), ViewCompat.y(this.editText), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_2_0_padding_bottom));
        } else if (MaterialResources.j(getContext())) {
            EditText editText2 = this.editText;
            ViewCompat.y0(editText2, ViewCompat.z(editText2), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_top), ViewCompat.y(this.editText), getResources().getDimensionPixelSize(R.dimen.material_filled_edittext_font_1_3_padding_bottom));
        }
    }

    private static void k0(Context context, TextView textView, int i2, int i3, boolean z) {
        textView.setContentDescription(context.getString(z ? R.string.character_counter_overflowed_content_description : R.string.character_counter_content_description, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    private void l() {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null) {
            return;
        }
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.getShapeAppearanceModel();
        ShapeAppearanceModel shapeAppearanceModel2 = this.shapeAppearanceModel;
        if (shapeAppearanceModel != shapeAppearanceModel2) {
            this.boxBackground.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        if (v()) {
            this.boxBackground.j0(this.boxStrokeWidthPx, this.boxStrokeColor);
        }
        int p2 = p();
        this.boxBackgroundColor = p2;
        this.boxBackground.a0(ColorStateList.valueOf(p2));
        m();
        q0();
    }

    private void l0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            b0(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (!this.counterOverflowed || (colorStateList = this.counterOverflowTextColor) == null) {
                return;
            }
            this.counterView.setTextColor(colorStateList);
        }
    }

    private void m() {
        if (this.boxUnderlineDefault == null || this.boxUnderlineFocused == null) {
            return;
        }
        if (w()) {
            this.boxUnderlineDefault.a0(this.editText.isFocused() ? ColorStateList.valueOf(this.defaultStrokeColor) : ColorStateList.valueOf(this.boxStrokeColor));
            this.boxUnderlineFocused.a0(ColorStateList.valueOf(this.boxStrokeColor));
        }
        invalidate();
    }

    private void m0() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.cursorColor;
        if (colorStateList2 == null) {
            colorStateList2 = MaterialColors.h(getContext(), R.attr.colorControlActivated);
        }
        EditText editText = this.editText;
        if (editText == null || editText.getTextCursorDrawable() == null) {
            return;
        }
        Drawable mutate = DrawableCompat.r(this.editText.getTextCursorDrawable()).mutate();
        if (P() && (colorStateList = this.cursorErrorColor) != null) {
            colorStateList2 = colorStateList;
        }
        DrawableCompat.o(mutate, colorStateList2);
    }

    private void n(RectF rectF) {
        float f2 = rectF.left;
        int i2 = this.boxLabelCutoutPaddingPx;
        rectF.left = f2 - i2;
        rectF.right += i2;
    }

    private void o() {
        int i2 = this.boxBackgroundMode;
        if (i2 == 0) {
            this.boxBackground = null;
            this.boxUnderlineDefault = null;
            this.boxUnderlineFocused = null;
            return;
        }
        if (i2 == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderlineDefault = new MaterialShapeDrawable();
            this.boxUnderlineFocused = new MaterialShapeDrawable();
        } else {
            if (i2 != 2) {
                throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
            if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = CutoutDrawable.r0(this.shapeAppearanceModel);
            }
            this.boxUnderlineDefault = null;
            this.boxUnderlineFocused = null;
        }
    }

    private int p() {
        return this.boxBackgroundMode == 1 ? MaterialColors.k(MaterialColors.e(this, R.attr.colorSurface, 0), this.boxBackgroundColor) : this.boxBackgroundColor;
    }

    private void p0() {
        ViewCompat.m0(this.editText, getEditTextBoxBackground());
    }

    private Rect q(Rect rect) {
        if (this.editText == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.tmpBoundsRect;
        boolean p2 = ViewUtils.p(this);
        rect2.bottom = rect.bottom;
        int i2 = this.boxBackgroundMode;
        if (i2 == 1) {
            rect2.left = H(rect.left, p2);
            rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
            rect2.right = I(rect.right, p2);
            return rect2;
        }
        if (i2 != 2) {
            rect2.left = H(rect.left, p2);
            rect2.top = getPaddingTop();
            rect2.right = I(rect.right, p2);
            return rect2;
        }
        rect2.left = rect.left + this.editText.getPaddingLeft();
        rect2.top = rect.top - u();
        rect2.right = rect.right - this.editText.getPaddingRight();
        return rect2;
    }

    private int r(Rect rect, Rect rect2, float f2) {
        return R() ? (int) (rect2.top + f2) : rect.bottom - this.editText.getCompoundPaddingBottom();
    }

    private boolean r0() {
        int max;
        if (this.editText == null || this.editText.getMeasuredHeight() >= (max = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight()))) {
            return false;
        }
        this.editText.setMinimumHeight(max);
        return true;
    }

    private int s(Rect rect, float f2) {
        return R() ? (int) (rect.centerY() - (f2 / 2.0f)) : rect.top + this.editText.getCompoundPaddingTop();
    }

    private void s0() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int u = u();
            if (u != layoutParams.topMargin) {
                layoutParams.topMargin = u;
                this.inputFrame.requestLayout();
            }
        }
    }

    private void setEditText(EditText editText) {
        if (this.editText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if (getEndIconMode() != 3 && !(editText instanceof TextInputEditText)) {
            Log.i(LOG_TAG, "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.editText = editText;
        int i2 = this.minEms;
        if (i2 != -1) {
            setMinEms(i2);
        } else {
            setMinWidth(this.minWidth);
        }
        int i3 = this.maxEms;
        if (i3 != -1) {
            setMaxEms(i3);
        } else {
            setMaxWidth(this.maxWidth);
        }
        this.boxBackgroundApplied = false;
        U();
        setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        this.collapsingTextHelper.N0(this.editText.getTypeface());
        this.collapsingTextHelper.v0(this.editText.getTextSize());
        this.collapsingTextHelper.q0(this.editText.getLetterSpacing());
        int gravity = this.editText.getGravity();
        this.collapsingTextHelper.j0((gravity & (-113)) | 48);
        this.collapsingTextHelper.u0(gravity);
        this.originalEditTextMinimumHeight = ViewCompat.w(editText);
        this.editText.addTextChangedListener(new TextWatcher(editText) { // from class: com.google.android.material.textfield.TextInputLayout.1

            /* renamed from: c, reason: collision with root package name */
            int f15442c;

            /* renamed from: h, reason: collision with root package name */
            final /* synthetic */ EditText f15443h;

            {
                this.f15443h = editText;
                this.f15442c = editText.getLineCount();
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                TextInputLayout.this.t0(!r0.restoringSavedState);
                TextInputLayout textInputLayout = TextInputLayout.this;
                if (textInputLayout.counterEnabled) {
                    textInputLayout.j0(editable);
                }
                if (TextInputLayout.this.placeholderEnabled) {
                    TextInputLayout.this.x0(editable);
                }
                int lineCount = this.f15443h.getLineCount();
                int i4 = this.f15442c;
                if (lineCount != i4) {
                    if (lineCount < i4) {
                        int w = ViewCompat.w(this.f15443h);
                        int i5 = TextInputLayout.this.originalEditTextMinimumHeight;
                        if (w != i5) {
                            this.f15443h.setMinimumHeight(i5);
                        }
                    }
                    this.f15442c = lineCount;
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i4, int i5, int i6) {
            }
        });
        if (this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
        }
        if (this.hintEnabled) {
            if (TextUtils.isEmpty(this.hint)) {
                CharSequence hint = this.editText.getHint();
                this.originalHint = hint;
                setHint(hint);
                this.editText.setHint((CharSequence) null);
            }
            this.isProvidingHint = true;
        }
        m0();
        if (this.counterView != null) {
            j0(this.editText.getText());
        }
        o0();
        this.indicatorViewController.f();
        this.startLayout.bringToFront();
        this.endLayout.bringToFront();
        B();
        this.endLayout.x0();
        if (!isEnabled()) {
            editText.setEnabled(false);
        }
        u0(false, true);
    }

    private void setHintInternal(CharSequence charSequence) {
        if (TextUtils.equals(charSequence, this.hint)) {
            return;
        }
        this.hint = charSequence;
        this.collapsingTextHelper.K0(charSequence);
        if (this.hintExpanded) {
            return;
        }
        V();
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if (this.placeholderEnabled == z) {
            return;
        }
        if (z) {
            j();
        } else {
            Z();
            this.placeholderTextView = null;
        }
        this.placeholderEnabled = z;
    }

    private Rect t(Rect rect) {
        if (this.editText == null) {
            throw new IllegalStateException();
        }
        Rect rect2 = this.tmpBoundsRect;
        float C = this.collapsingTextHelper.C();
        rect2.left = rect.left + this.editText.getCompoundPaddingLeft();
        rect2.top = s(rect, C);
        rect2.right = rect.right - this.editText.getCompoundPaddingRight();
        rect2.bottom = r(rect, rect2, C);
        return rect2;
    }

    private int u() {
        float r2;
        if (!this.hintEnabled) {
            return 0;
        }
        int i2 = this.boxBackgroundMode;
        if (i2 == 0) {
            r2 = this.collapsingTextHelper.r();
        } else {
            if (i2 != 2) {
                return 0;
            }
            r2 = this.collapsingTextHelper.r() / 2.0f;
        }
        return (int) r2;
    }

    private void u0(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText = this.editText;
        boolean z3 = false;
        boolean z4 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.editText;
        if (editText2 != null && editText2.hasFocus()) {
            z3 = true;
        }
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.d0(colorStateList2);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.defaultHintTextColor;
            this.collapsingTextHelper.d0(ColorStateList.valueOf(colorStateList3 != null ? colorStateList3.getColorForState(new int[]{-16842910}, this.disabledColor) : this.disabledColor));
        } else if (c0()) {
            this.collapsingTextHelper.d0(this.indicatorViewController.r());
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.d0(textView.getTextColors());
        } else if (z3 && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.i0(colorStateList);
        }
        if (z4 || !this.expandedHintEnabled || (isEnabled() && z3)) {
            if (z2 || this.hintExpanded) {
                y(z);
                return;
            }
            return;
        }
        if (z2 || !this.hintExpanded) {
            E(z);
        }
    }

    private boolean v() {
        return this.boxBackgroundMode == 2 && w();
    }

    private void v0() {
        EditText editText;
        if (this.placeholderTextView == null || (editText = this.editText) == null) {
            return;
        }
        this.placeholderTextView.setGravity(editText.getGravity());
        this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
    }

    private boolean w() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    private void w0() {
        EditText editText = this.editText;
        x0(editText == null ? null : editText.getText());
    }

    private void x() {
        if (A()) {
            ((CutoutDrawable) this.boxBackground).u0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0(Editable editable) {
        if (this.lengthCounter.a(editable) != 0 || this.hintExpanded) {
            K();
        } else {
            f0();
        }
    }

    private void y(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (z && this.hintAnimationEnabled) {
            animateToExpansionFraction(1.0f);
        } else {
            this.collapsingTextHelper.y0(1.0f);
        }
        this.hintExpanded = false;
        if (A()) {
            V();
        }
        w0();
        this.startLayout.l(false);
        this.endLayout.H(false);
    }

    private void y0(boolean z, boolean z2) {
        int defaultColor = this.strokeErrorColor.getDefaultColor();
        int colorForState = this.strokeErrorColor.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.strokeErrorColor.getColorForState(new int[]{android.R.attr.state_activated, android.R.attr.state_enabled}, defaultColor);
        if (z) {
            this.boxStrokeColor = colorForState2;
        } else if (z2) {
            this.boxStrokeColor = colorForState;
        } else {
            this.boxStrokeColor = defaultColor;
        }
    }

    private Fade z() {
        Fade fade = new Fade();
        fade.h0(MotionUtils.f(getContext(), R.attr.motionDurationShort2, DEFAULT_PLACEHOLDER_FADE_DURATION));
        fade.j0(MotionUtils.g(getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.f13814a));
        return fade;
    }

    public boolean L() {
        return this.endLayout.F();
    }

    public boolean M() {
        return this.indicatorViewController.C();
    }

    public boolean N() {
        return this.indicatorViewController.D();
    }

    final boolean O() {
        return this.hintExpanded;
    }

    public boolean Q() {
        return this.isProvidingHint;
    }

    public void Y() {
        this.startLayout.m();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof EditText)) {
            super.addView(view, i2, layoutParams);
            return;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
        layoutParams2.gravity = (layoutParams2.gravity & (-113)) | 16;
        this.inputFrame.addView(view, layoutParams2);
        this.inputFrame.setLayoutParams(layoutParams);
        s0();
        setEditText((EditText) view);
    }

    @VisibleForTesting
    void animateToExpansionFraction(float f2) {
        if (this.collapsingTextHelper.F() == f2) {
            return;
        }
        if (this.animator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.animator = valueAnimator;
            valueAnimator.setInterpolator(MotionUtils.g(getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.f13815b));
            this.animator.setDuration(MotionUtils.f(getContext(), R.attr.motionDurationMedium4, LABEL_SCALE_ANIMATION_DURATION));
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.textfield.TextInputLayout.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TextInputLayout.this.collapsingTextHelper.y0(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
        }
        this.animator.setFloatValues(this.collapsingTextHelper.F(), f2);
        this.animator.start();
    }

    void b0(TextView textView, int i2) {
        try {
            TextViewCompat.p(textView, i2);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        TextViewCompat.p(textView, R.style.TextAppearance_AppCompat_Caption);
        textView.setTextColor(ContextCompat.c(getContext(), R.color.design_error));
    }

    boolean c0() {
        return this.indicatorViewController.l();
    }

    @VisibleForTesting
    boolean cutoutIsOpen() {
        return A() && ((CutoutDrawable) this.boxBackground).t0();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i2) {
        EditText editText = this.editText;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i2);
            return;
        }
        if (this.originalHint != null) {
            boolean z = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence hint = editText.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i2);
                return;
            } finally {
                this.editText.setHint(hint);
                this.isProvidingHint = z;
            }
        }
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i2);
        onProvideAutofillVirtualStructure(viewStructure, i2);
        viewStructure.setChildCount(this.inputFrame.getChildCount());
        for (int i3 = 0; i3 < this.inputFrame.getChildCount(); i3++) {
            View childAt = this.inputFrame.getChildAt(i3);
            ViewStructure newChild = viewStructure.newChild(i3);
            childAt.dispatchProvideAutofillStructure(newChild, i2);
            if (childAt == this.editText) {
                newChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        D(canvas);
        C(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.inDrawableStateChanged) {
            return;
        }
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        boolean I0 = collapsingTextHelper != null ? collapsingTextHelper.I0(drawableState) : false;
        if (this.editText != null) {
            t0(ViewCompat.N(this) && isEnabled());
        }
        o0();
        z0();
        if (I0) {
            invalidate();
        }
        this.inDrawableStateChanged = false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public int getBaseline() {
        EditText editText = this.editText;
        return editText != null ? editText.getBaseline() + getPaddingTop() + u() : super.getBaseline();
    }

    @NonNull
    MaterialShapeDrawable getBoxBackground() {
        int i2 = this.boxBackgroundMode;
        if (i2 == 1 || i2 == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.boxCollapsedPaddingTopPx;
    }

    public float getBoxCornerRadiusBottomEnd() {
        return ViewUtils.p(this) ? this.shapeAppearanceModel.j().a(this.tmpRectF) : this.shapeAppearanceModel.l().a(this.tmpRectF);
    }

    public float getBoxCornerRadiusBottomStart() {
        return ViewUtils.p(this) ? this.shapeAppearanceModel.l().a(this.tmpRectF) : this.shapeAppearanceModel.j().a(this.tmpRectF);
    }

    public float getBoxCornerRadiusTopEnd() {
        return ViewUtils.p(this) ? this.shapeAppearanceModel.r().a(this.tmpRectF) : this.shapeAppearanceModel.t().a(this.tmpRectF);
    }

    public float getBoxCornerRadiusTopStart() {
        return ViewUtils.p(this) ? this.shapeAppearanceModel.t().a(this.tmpRectF) : this.shapeAppearanceModel.r().a(this.tmpRectF);
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    @Nullable
    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    @Nullable
    CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (this.counterEnabled && this.counterOverflowed && (textView = this.counterView) != null) {
            return textView.getContentDescription();
        }
        return null;
    }

    @Nullable
    public ColorStateList getCounterOverflowTextColor() {
        return this.counterOverflowTextColor;
    }

    @Nullable
    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    @Nullable
    @RequiresApi
    public ColorStateList getCursorColor() {
        return this.cursorColor;
    }

    @Nullable
    @RequiresApi
    public ColorStateList getCursorErrorColor() {
        return this.cursorErrorColor;
    }

    @Nullable
    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    @Nullable
    public EditText getEditText() {
        return this.editText;
    }

    @Nullable
    public CharSequence getEndIconContentDescription() {
        return this.endLayout.l();
    }

    @Nullable
    public Drawable getEndIconDrawable() {
        return this.endLayout.n();
    }

    public int getEndIconMinSize() {
        return this.endLayout.o();
    }

    public int getEndIconMode() {
        return this.endLayout.p();
    }

    @NonNull
    public ImageView.ScaleType getEndIconScaleType() {
        return this.endLayout.q();
    }

    @NonNull
    CheckableImageButton getEndIconView() {
        return this.endLayout.r();
    }

    @Nullable
    public CharSequence getError() {
        if (this.indicatorViewController.C()) {
            return this.indicatorViewController.p();
        }
        return null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.indicatorViewController.n();
    }

    @Nullable
    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.o();
    }

    @ColorInt
    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.q();
    }

    @Nullable
    public Drawable getErrorIconDrawable() {
        return this.endLayout.s();
    }

    @Nullable
    public CharSequence getHelperText() {
        if (this.indicatorViewController.D()) {
            return this.indicatorViewController.s();
        }
        return null;
    }

    @ColorInt
    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.u();
    }

    @Nullable
    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    @VisibleForTesting
    final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.r();
    }

    @VisibleForTesting
    final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.w();
    }

    @Nullable
    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    @NonNull
    public LengthCounter getLengthCounter() {
        return this.lengthCounter;
    }

    public int getMaxEms() {
        return this.maxEms;
    }

    @Px
    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getMinEms() {
        return this.minEms;
    }

    @Px
    public int getMinWidth() {
        return this.minWidth;
    }

    @Nullable
    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endLayout.u();
    }

    @Nullable
    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endLayout.v();
    }

    @Nullable
    public CharSequence getPlaceholderText() {
        if (this.placeholderEnabled) {
            return this.placeholderText;
        }
        return null;
    }

    @StyleRes
    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    @Nullable
    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    @Nullable
    public CharSequence getPrefixText() {
        return this.startLayout.a();
    }

    @Nullable
    public ColorStateList getPrefixTextColor() {
        return this.startLayout.b();
    }

    @NonNull
    public TextView getPrefixTextView() {
        return this.startLayout.d();
    }

    @NonNull
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    @Nullable
    public CharSequence getStartIconContentDescription() {
        return this.startLayout.e();
    }

    @Nullable
    public Drawable getStartIconDrawable() {
        return this.startLayout.f();
    }

    public int getStartIconMinSize() {
        return this.startLayout.g();
    }

    @NonNull
    public ImageView.ScaleType getStartIconScaleType() {
        return this.startLayout.h();
    }

    @Nullable
    public CharSequence getSuffixText() {
        return this.endLayout.w();
    }

    @Nullable
    public ColorStateList getSuffixTextColor() {
        return this.endLayout.x();
    }

    @NonNull
    public TextView getSuffixTextView() {
        return this.endLayout.z();
    }

    @Nullable
    public Typeface getTypeface() {
        return this.typeface;
    }

    public void i(OnEditTextAttachedListener onEditTextAttachedListener) {
        this.editTextAttachedListeners.add(onEditTextAttachedListener);
        if (this.editText != null) {
            onEditTextAttachedListener.a(this);
        }
    }

    @VisibleForTesting
    final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.w();
    }

    void j0(Editable editable) {
        int a2 = this.lengthCounter.a(editable);
        boolean z = this.counterOverflowed;
        int i2 = this.counterMaxLength;
        if (i2 == -1) {
            this.counterView.setText(String.valueOf(a2));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            this.counterOverflowed = a2 > i2;
            k0(getContext(), this.counterView, a2, this.counterMaxLength, this.counterOverflowed);
            if (z != this.counterOverflowed) {
                l0();
            }
            this.counterView.setText(BidiFormatter.c().j(getContext().getString(R.string.character_counter_pattern, Integer.valueOf(a2), Integer.valueOf(this.counterMaxLength))));
        }
        if (this.editText == null || z == this.counterOverflowed) {
            return;
        }
        t0(false);
        z0();
        o0();
    }

    boolean n0() {
        boolean z;
        if (this.editText == null) {
            return false;
        }
        boolean z2 = true;
        if (e0()) {
            int measuredWidth = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if (this.startDummyDrawable == null || this.startDummyDrawableWidth != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.startDummyDrawable = colorDrawable;
                this.startDummyDrawableWidth = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] a2 = TextViewCompat.a(this.editText);
            Drawable drawable = a2[0];
            Drawable drawable2 = this.startDummyDrawable;
            if (drawable != drawable2) {
                TextViewCompat.j(this.editText, drawable2, a2[1], a2[2], a2[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.startDummyDrawable != null) {
                Drawable[] a3 = TextViewCompat.a(this.editText);
                TextViewCompat.j(this.editText, null, a3[1], a3[2], a3[3]);
                this.startDummyDrawable = null;
                z = true;
            }
            z = false;
        }
        if (d0()) {
            int measuredWidth2 = this.endLayout.z().getMeasuredWidth() - this.editText.getPaddingRight();
            CheckableImageButton k2 = this.endLayout.k();
            if (k2 != null) {
                measuredWidth2 = measuredWidth2 + k2.getMeasuredWidth() + MarginLayoutParamsCompat.b((ViewGroup.MarginLayoutParams) k2.getLayoutParams());
            }
            Drawable[] a4 = TextViewCompat.a(this.editText);
            Drawable drawable3 = this.endDummyDrawable;
            if (drawable3 == null || this.endDummyDrawableWidth == measuredWidth2) {
                if (drawable3 == null) {
                    ColorDrawable colorDrawable2 = new ColorDrawable();
                    this.endDummyDrawable = colorDrawable2;
                    this.endDummyDrawableWidth = measuredWidth2;
                    colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
                }
                Drawable drawable4 = a4[2];
                Drawable drawable5 = this.endDummyDrawable;
                if (drawable4 != drawable5) {
                    this.originalEditTextEndDrawable = drawable4;
                    TextViewCompat.j(this.editText, a4[0], a4[1], drawable5, a4[3]);
                } else {
                    z2 = z;
                }
            } else {
                this.endDummyDrawableWidth = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                TextViewCompat.j(this.editText, a4[0], a4[1], this.endDummyDrawable, a4[3]);
            }
        } else {
            if (this.endDummyDrawable == null) {
                return z;
            }
            Drawable[] a5 = TextViewCompat.a(this.editText);
            if (a5[2] == this.endDummyDrawable) {
                TextViewCompat.j(this.editText, a5[0], a5[1], this.originalEditTextEndDrawable, a5[3]);
            } else {
                z2 = z;
            }
            this.endDummyDrawable = null;
        }
        return z2;
    }

    void o0() {
        Drawable background;
        TextView textView;
        EditText editText = this.editText;
        if (editText == null || this.boxBackgroundMode != 0 || (background = editText.getBackground()) == null) {
            return;
        }
        if (DrawableUtils.a(background)) {
            background = background.mutate();
        }
        if (c0()) {
            background.setColorFilter(AppCompatDrawableManager.e(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            background.setColorFilter(AppCompatDrawableManager.e(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
        } else {
            DrawableCompat.c(background);
            this.editText.refreshDrawableState();
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.Y(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.endLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.globalLayoutListenerAdded = false;
        boolean r0 = r0();
        boolean n0 = n0();
        if (r0 || n0) {
            this.editText.post(new Runnable() { // from class: com.google.android.material.textfield.n
                @Override // java.lang.Runnable
                public final void run() {
                    TextInputLayout.this.T();
                }
            });
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        EditText editText = this.editText;
        if (editText != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.a(this, editText, rect);
            h0(rect);
            if (this.hintEnabled) {
                this.collapsingTextHelper.v0(this.editText.getTextSize());
                int gravity = this.editText.getGravity();
                this.collapsingTextHelper.j0((gravity & (-113)) | 48);
                this.collapsingTextHelper.u0(gravity);
                this.collapsingTextHelper.f0(q(rect));
                this.collapsingTextHelper.p0(t(rect));
                this.collapsingTextHelper.a0();
                if (!A() || this.hintExpanded) {
                    return;
                }
                V();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (!this.globalLayoutListenerAdded) {
            this.endLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.globalLayoutListenerAdded = true;
        }
        v0();
        this.endLayout.x0();
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        setError(savedState.f15448i);
        if (savedState.f15449j) {
            post(new Runnable() { // from class: com.google.android.material.textfield.TextInputLayout.2
                @Override // java.lang.Runnable
                public void run() {
                    TextInputLayout.this.endLayout.h();
                }
            });
        }
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        boolean z = i2 == 1;
        if (z != this.areCornerRadiiRtl) {
            float a2 = this.shapeAppearanceModel.r().a(this.tmpRectF);
            float a3 = this.shapeAppearanceModel.t().a(this.tmpRectF);
            ShapeAppearanceModel m2 = ShapeAppearanceModel.a().D(this.shapeAppearanceModel.s()).H(this.shapeAppearanceModel.q()).u(this.shapeAppearanceModel.k()).y(this.shapeAppearanceModel.i()).E(a3).I(a2).v(this.shapeAppearanceModel.l().a(this.tmpRectF)).z(this.shapeAppearanceModel.j().a(this.tmpRectF)).m();
            this.areCornerRadiiRtl = z;
            setShapeAppearanceModel(m2);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (c0()) {
            savedState.f15448i = getError();
        }
        savedState.f15449j = this.endLayout.E();
        return savedState;
    }

    void q0() {
        EditText editText = this.editText;
        if (editText == null || this.boxBackground == null) {
            return;
        }
        if ((this.boxBackgroundApplied || editText.getBackground() == null) && this.boxBackgroundMode != 0) {
            p0();
            this.boxBackgroundApplied = true;
        }
    }

    public void setBoxBackgroundColor(@ColorInt int i2) {
        if (this.boxBackgroundColor != i2) {
            this.boxBackgroundColor = i2;
            this.defaultFilledBackgroundColor = i2;
            this.focusedFilledBackgroundColor = i2;
            this.hoveredFilledBackgroundColor = i2;
            l();
        }
    }

    public void setBoxBackgroundColorResource(@ColorRes int i2) {
        setBoxBackgroundColor(ContextCompat.c(getContext(), i2));
    }

    public void setBoxBackgroundColorStateList(@NonNull ColorStateList colorStateList) {
        int defaultColor = colorStateList.getDefaultColor();
        this.defaultFilledBackgroundColor = defaultColor;
        this.boxBackgroundColor = defaultColor;
        this.disabledFilledBackgroundColor = colorStateList.getColorForState(new int[]{-16842910}, -1);
        this.focusedFilledBackgroundColor = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        this.hoveredFilledBackgroundColor = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
        l();
    }

    public void setBoxBackgroundMode(int i2) {
        if (i2 == this.boxBackgroundMode) {
            return;
        }
        this.boxBackgroundMode = i2;
        if (this.editText != null) {
            U();
        }
    }

    public void setBoxCollapsedPaddingTop(int i2) {
        this.boxCollapsedPaddingTopPx = i2;
    }

    public void setBoxCornerFamily(int i2) {
        this.shapeAppearanceModel = this.shapeAppearanceModel.v().C(i2, this.shapeAppearanceModel.r()).G(i2, this.shapeAppearanceModel.t()).t(i2, this.shapeAppearanceModel.j()).x(i2, this.shapeAppearanceModel.l()).m();
        l();
    }

    public void setBoxStrokeColor(@ColorInt int i2) {
        if (this.focusedStrokeColor != i2) {
            this.focusedStrokeColor = i2;
            z0();
        }
    }

    public void setBoxStrokeColorStateList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.isStateful()) {
            this.defaultStrokeColor = colorStateList.getDefaultColor();
            this.disabledColor = colorStateList.getColorForState(new int[]{-16842910}, -1);
            this.hoveredStrokeColor = colorStateList.getColorForState(new int[]{android.R.attr.state_hovered, android.R.attr.state_enabled}, -1);
            this.focusedStrokeColor = colorStateList.getColorForState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, -1);
        } else if (this.focusedStrokeColor != colorStateList.getDefaultColor()) {
            this.focusedStrokeColor = colorStateList.getDefaultColor();
        }
        z0();
    }

    public void setBoxStrokeErrorColor(@Nullable ColorStateList colorStateList) {
        if (this.strokeErrorColor != colorStateList) {
            this.strokeErrorColor = colorStateList;
            z0();
        }
    }

    public void setBoxStrokeWidth(int i2) {
        this.boxStrokeWidthDefaultPx = i2;
        z0();
    }

    public void setBoxStrokeWidthFocused(int i2) {
        this.boxStrokeWidthFocusedPx = i2;
        z0();
    }

    public void setBoxStrokeWidthFocusedResource(@DimenRes int i2) {
        setBoxStrokeWidthFocused(getResources().getDimensionPixelSize(i2));
    }

    public void setBoxStrokeWidthResource(@DimenRes int i2) {
        setBoxStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public void setCounterEnabled(boolean z) {
        if (this.counterEnabled != z) {
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
                this.counterView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_counter);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.counterView.setTypeface(typeface);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.e(this.counterView, 2);
                MarginLayoutParamsCompat.d((ViewGroup.MarginLayoutParams) this.counterView.getLayoutParams(), getResources().getDimensionPixelOffset(R.dimen.mtrl_textinput_counter_margin_start));
                l0();
                i0();
            } else {
                this.indicatorViewController.E(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public void setCounterMaxLength(int i2) {
        if (this.counterMaxLength != i2) {
            if (i2 > 0) {
                this.counterMaxLength = i2;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                i0();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int i2) {
        if (this.counterOverflowTextAppearance != i2) {
            this.counterOverflowTextAppearance = i2;
            l0();
        }
    }

    public void setCounterOverflowTextColor(@Nullable ColorStateList colorStateList) {
        if (this.counterOverflowTextColor != colorStateList) {
            this.counterOverflowTextColor = colorStateList;
            l0();
        }
    }

    public void setCounterTextAppearance(int i2) {
        if (this.counterTextAppearance != i2) {
            this.counterTextAppearance = i2;
            l0();
        }
    }

    public void setCounterTextColor(@Nullable ColorStateList colorStateList) {
        if (this.counterTextColor != colorStateList) {
            this.counterTextColor = colorStateList;
            l0();
        }
    }

    @RequiresApi
    public void setCursorColor(@Nullable ColorStateList colorStateList) {
        if (this.cursorColor != colorStateList) {
            this.cursorColor = colorStateList;
            m0();
        }
    }

    @RequiresApi
    public void setCursorErrorColor(@Nullable ColorStateList colorStateList) {
        if (this.cursorErrorColor != colorStateList) {
            this.cursorErrorColor = colorStateList;
            if (P()) {
                m0();
            }
        }
    }

    public void setDefaultHintTextColor(@Nullable ColorStateList colorStateList) {
        this.defaultHintTextColor = colorStateList;
        this.focusedTextColor = colorStateList;
        if (this.editText != null) {
            t0(false);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        X(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.endLayout.N(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.endLayout.O(z);
    }

    public void setEndIconContentDescription(@StringRes int i2) {
        this.endLayout.P(i2);
    }

    public void setEndIconDrawable(@DrawableRes int i2) {
        this.endLayout.R(i2);
    }

    public void setEndIconMinSize(@IntRange int i2) {
        this.endLayout.T(i2);
    }

    public void setEndIconMode(int i2) {
        this.endLayout.U(i2);
    }

    public void setEndIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.endLayout.V(onClickListener);
    }

    public void setEndIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.endLayout.W(onLongClickListener);
    }

    public void setEndIconScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.endLayout.X(scaleType);
    }

    public void setEndIconTintList(@Nullable ColorStateList colorStateList) {
        this.endLayout.Y(colorStateList);
    }

    public void setEndIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.endLayout.Z(mode);
    }

    public void setEndIconVisible(boolean z) {
        this.endLayout.a0(z);
    }

    public void setError(@Nullable CharSequence charSequence) {
        if (!this.indicatorViewController.C()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            } else {
                setErrorEnabled(true);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.indicatorViewController.x();
        } else {
            this.indicatorViewController.S(charSequence);
        }
    }

    public void setErrorAccessibilityLiveRegion(int i2) {
        this.indicatorViewController.G(i2);
    }

    public void setErrorContentDescription(@Nullable CharSequence charSequence) {
        this.indicatorViewController.H(charSequence);
    }

    public void setErrorEnabled(boolean z) {
        this.indicatorViewController.I(z);
    }

    public void setErrorIconDrawable(@DrawableRes int i2) {
        this.endLayout.b0(i2);
    }

    public void setErrorIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.endLayout.d0(onClickListener);
    }

    public void setErrorIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.endLayout.e0(onLongClickListener);
    }

    public void setErrorIconTintList(@Nullable ColorStateList colorStateList) {
        this.endLayout.f0(colorStateList);
    }

    public void setErrorIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.endLayout.g0(mode);
    }

    public void setErrorTextAppearance(@StyleRes int i2) {
        this.indicatorViewController.J(i2);
    }

    public void setErrorTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.K(colorStateList);
    }

    public void setExpandedHintEnabled(boolean z) {
        if (this.expandedHintEnabled != z) {
            this.expandedHintEnabled = z;
            t0(false);
        }
    }

    public void setHelperText(@Nullable CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            if (N()) {
                setHelperTextEnabled(false);
            }
        } else {
            if (!N()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.T(charSequence);
        }
    }

    public void setHelperTextColor(@Nullable ColorStateList colorStateList) {
        this.indicatorViewController.N(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.indicatorViewController.M(z);
    }

    public void setHelperTextTextAppearance(@StyleRes int i2) {
        this.indicatorViewController.L(i2);
    }

    public void setHint(@Nullable CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.hintAnimationEnabled = z;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.hintEnabled) {
            this.hintEnabled = z;
            if (z) {
                CharSequence hint = this.editText.getHint();
                if (!TextUtils.isEmpty(hint)) {
                    if (TextUtils.isEmpty(this.hint)) {
                        setHint(hint);
                    }
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            } else {
                this.isProvidingHint = false;
                if (!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                setHintInternal(null);
            }
            if (this.editText != null) {
                s0();
            }
        }
    }

    public void setHintTextAppearance(@StyleRes int i2) {
        this.collapsingTextHelper.g0(i2);
        this.focusedTextColor = this.collapsingTextHelper.p();
        if (this.editText != null) {
            t0(false);
            s0();
        }
    }

    public void setHintTextColor(@Nullable ColorStateList colorStateList) {
        if (this.focusedTextColor != colorStateList) {
            if (this.defaultHintTextColor == null) {
                this.collapsingTextHelper.i0(colorStateList);
            }
            this.focusedTextColor = colorStateList;
            if (this.editText != null) {
                t0(false);
            }
        }
    }

    public void setLengthCounter(@NonNull LengthCounter lengthCounter) {
        this.lengthCounter = lengthCounter;
    }

    public void setMaxEms(int i2) {
        this.maxEms = i2;
        EditText editText = this.editText;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxEms(i2);
    }

    public void setMaxWidth(@Px int i2) {
        this.maxWidth = i2;
        EditText editText = this.editText;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMaxWidth(i2);
    }

    public void setMaxWidthResource(@DimenRes int i2) {
        setMaxWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    public void setMinEms(int i2) {
        this.minEms = i2;
        EditText editText = this.editText;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinEms(i2);
    }

    public void setMinWidth(@Px int i2) {
        this.minWidth = i2;
        EditText editText = this.editText;
        if (editText == null || i2 == -1) {
            return;
        }
        editText.setMinWidth(i2);
    }

    public void setMinWidthResource(@DimenRes int i2) {
        setMinWidth(getContext().getResources().getDimensionPixelSize(i2));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@StringRes int i2) {
        this.endLayout.i0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i2) {
        this.endLayout.k0(i2);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        this.endLayout.m0(z);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.endLayout.n0(colorStateList);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode mode) {
        this.endLayout.o0(mode);
    }

    public void setPlaceholderText(@Nullable CharSequence charSequence) {
        if (this.placeholderTextView == null) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
            this.placeholderTextView = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_placeholder);
            ViewCompat.s0(this.placeholderTextView, 2);
            Fade z = z();
            this.placeholderFadeIn = z;
            z.n0(67L);
            this.placeholderFadeOut = z();
            setPlaceholderTextAppearance(this.placeholderTextAppearance);
            setPlaceholderTextColor(this.placeholderTextColor);
        }
        if (TextUtils.isEmpty(charSequence)) {
            setPlaceholderTextEnabled(false);
        } else {
            if (!this.placeholderEnabled) {
                setPlaceholderTextEnabled(true);
            }
            this.placeholderText = charSequence;
        }
        w0();
    }

    public void setPlaceholderTextAppearance(@StyleRes int i2) {
        this.placeholderTextAppearance = i2;
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            TextViewCompat.p(textView, i2);
        }
    }

    public void setPlaceholderTextColor(@Nullable ColorStateList colorStateList) {
        if (this.placeholderTextColor != colorStateList) {
            this.placeholderTextColor = colorStateList;
            TextView textView = this.placeholderTextView;
            if (textView == null || colorStateList == null) {
                return;
            }
            textView.setTextColor(colorStateList);
        }
    }

    public void setPrefixText(@Nullable CharSequence charSequence) {
        this.startLayout.n(charSequence);
    }

    public void setPrefixTextAppearance(@StyleRes int i2) {
        this.startLayout.o(i2);
    }

    public void setPrefixTextColor(@NonNull ColorStateList colorStateList) {
        this.startLayout.p(colorStateList);
    }

    public void setShapeAppearanceModel(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null || materialShapeDrawable.getShapeAppearanceModel() == shapeAppearanceModel) {
            return;
        }
        this.shapeAppearanceModel = shapeAppearanceModel;
        l();
    }

    public void setStartIconCheckable(boolean z) {
        this.startLayout.q(z);
    }

    public void setStartIconContentDescription(@StringRes int i2) {
        setStartIconContentDescription(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setStartIconDrawable(@DrawableRes int i2) {
        setStartIconDrawable(i2 != 0 ? AppCompatResources.b(getContext(), i2) : null);
    }

    public void setStartIconMinSize(@IntRange int i2) {
        this.startLayout.t(i2);
    }

    public void setStartIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.startLayout.u(onClickListener);
    }

    public void setStartIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.startLayout.v(onLongClickListener);
    }

    public void setStartIconScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.startLayout.w(scaleType);
    }

    public void setStartIconTintList(@Nullable ColorStateList colorStateList) {
        this.startLayout.x(colorStateList);
    }

    public void setStartIconTintMode(@Nullable PorterDuff.Mode mode) {
        this.startLayout.y(mode);
    }

    public void setStartIconVisible(boolean z) {
        this.startLayout.z(z);
    }

    public void setSuffixText(@Nullable CharSequence charSequence) {
        this.endLayout.p0(charSequence);
    }

    public void setSuffixTextAppearance(@StyleRes int i2) {
        this.endLayout.q0(i2);
    }

    public void setSuffixTextColor(@NonNull ColorStateList colorStateList) {
        this.endLayout.r0(colorStateList);
    }

    public void setTextInputAccessibilityDelegate(@Nullable AccessibilityDelegate accessibilityDelegate) {
        EditText editText = this.editText;
        if (editText != null) {
            ViewCompat.i0(editText, accessibilityDelegate);
        }
    }

    public void setTypeface(@Nullable Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            this.collapsingTextHelper.N0(typeface);
            this.indicatorViewController.P(typeface);
            TextView textView = this.counterView;
            if (textView != null) {
                textView.setTypeface(typeface);
            }
        }
    }

    void t0(boolean z) {
        u0(z, false);
    }

    void z0() {
        TextView textView;
        EditText editText;
        EditText editText2;
        if (this.boxBackground == null || this.boxBackgroundMode == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = isFocused() || ((editText2 = this.editText) != null && editText2.hasFocus());
        if (isHovered() || ((editText = this.editText) != null && editText.isHovered())) {
            z = true;
        }
        if (!isEnabled()) {
            this.boxStrokeColor = this.disabledColor;
        } else if (c0()) {
            if (this.strokeErrorColor != null) {
                y0(z2, z);
            } else {
                this.boxStrokeColor = getErrorCurrentTextColors();
            }
        } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
            if (z2) {
                this.boxStrokeColor = this.focusedStrokeColor;
            } else if (z) {
                this.boxStrokeColor = this.hoveredStrokeColor;
            } else {
                this.boxStrokeColor = this.defaultStrokeColor;
            }
        } else if (this.strokeErrorColor != null) {
            y0(z2, z);
        } else {
            this.boxStrokeColor = textView.getCurrentTextColor();
        }
        m0();
        this.endLayout.I();
        Y();
        if (this.boxBackgroundMode == 2) {
            int i2 = this.boxStrokeWidthPx;
            if (z2 && isEnabled()) {
                this.boxStrokeWidthPx = this.boxStrokeWidthFocusedPx;
            } else {
                this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
            }
            if (this.boxStrokeWidthPx != i2) {
                W();
            }
        }
        if (this.boxBackgroundMode == 1) {
            if (!isEnabled()) {
                this.boxBackgroundColor = this.disabledFilledBackgroundColor;
            } else if (z && !z2) {
                this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
            } else if (z2) {
                this.boxBackgroundColor = this.focusedFilledBackgroundColor;
            } else {
                this.boxBackgroundColor = this.defaultFilledBackgroundColor;
            }
        }
        l();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextInputLayout(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 880
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setEndIconContentDescription(@Nullable CharSequence charSequence) {
        this.endLayout.Q(charSequence);
    }

    public void setEndIconDrawable(@Nullable Drawable drawable) {
        this.endLayout.S(drawable);
    }

    public void setErrorIconDrawable(@Nullable Drawable drawable) {
        this.endLayout.c0(drawable);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.endLayout.j0(charSequence);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.endLayout.l0(drawable);
    }

    public void setStartIconContentDescription(@Nullable CharSequence charSequence) {
        this.startLayout.r(charSequence);
    }

    public void setStartIconDrawable(@Nullable Drawable drawable) {
        this.startLayout.s(drawable);
    }

    public void setHint(@StringRes int i2) {
        setHint(i2 != 0 ? getResources().getText(i2) : null);
    }
}
