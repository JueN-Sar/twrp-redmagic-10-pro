package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.view.TintableBackgroundView;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import androidx.core.widget.TintableCompoundDrawablesView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.IntFunction;

/* loaded from: classes.dex */
public class AppCompatTextView extends TextView implements TintableBackgroundView, TintableCompoundDrawablesView, AutoSizeableTextView, EmojiCompatConfigurationView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;

    @NonNull
    private AppCompatEmojiTextHelper mEmojiTextViewHelper;
    private boolean mIsSetTypefaceProcessing;

    @Nullable
    private Future<PrecomputedTextCompat> mPrecomputedTextFuture;

    @Nullable
    private SuperCaller mSuperCaller;
    private final AppCompatTextClassifierHelper mTextClassifierHelper;
    private final AppCompatTextHelper mTextHelper;

    @RequiresApi
    @RestrictTo
    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<AppCompatTextView> {

        /* renamed from: a, reason: collision with root package name */
        private boolean f839a;

        /* renamed from: b, reason: collision with root package name */
        private int f840b;

        /* renamed from: c, reason: collision with root package name */
        private int f841c;

        /* renamed from: d, reason: collision with root package name */
        private int f842d;

        /* renamed from: e, reason: collision with root package name */
        private int f843e;

        /* renamed from: f, reason: collision with root package name */
        private int f844f;

        /* renamed from: g, reason: collision with root package name */
        private int f845g;

        /* renamed from: h, reason: collision with root package name */
        private int f846h;

        /* renamed from: i, reason: collision with root package name */
        private int f847i;

        @Override // android.view.inspector.InspectionCompanion
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void readProperties(AppCompatTextView appCompatTextView, PropertyReader propertyReader) {
            if (!this.f839a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readInt(this.f840b, appCompatTextView.getAutoSizeMaxTextSize());
            propertyReader.readInt(this.f841c, appCompatTextView.getAutoSizeMinTextSize());
            propertyReader.readInt(this.f842d, appCompatTextView.getAutoSizeStepGranularity());
            propertyReader.readIntEnum(this.f843e, appCompatTextView.getAutoSizeTextType());
            propertyReader.readObject(this.f844f, appCompatTextView.getBackgroundTintList());
            propertyReader.readObject(this.f845g, appCompatTextView.getBackgroundTintMode());
            propertyReader.readObject(this.f846h, appCompatTextView.getCompoundDrawableTintList());
            propertyReader.readObject(this.f847i, appCompatTextView.getCompoundDrawableTintMode());
        }

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.f840b = propertyMapper.mapInt("autoSizeMaxTextSize", R.attr.autoSizeMaxTextSize);
            this.f841c = propertyMapper.mapInt("autoSizeMinTextSize", R.attr.autoSizeMinTextSize);
            this.f842d = propertyMapper.mapInt("autoSizeStepGranularity", R.attr.autoSizeStepGranularity);
            this.f843e = propertyMapper.mapIntEnum("autoSizeTextType", R.attr.autoSizeTextType, new IntFunction<String>() { // from class: androidx.appcompat.widget.AppCompatTextView.InspectionCompanion.1
                @Override // java.util.function.IntFunction
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public String apply(int i2) {
                    return i2 != 0 ? i2 != 1 ? String.valueOf(i2) : "uniform" : "none";
                }
            });
            this.f844f = propertyMapper.mapObject("backgroundTint", R.attr.backgroundTint);
            this.f845g = propertyMapper.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
            this.f846h = propertyMapper.mapObject("drawableTint", R.attr.drawableTint);
            this.f847i = propertyMapper.mapObject("drawableTintMode", R.attr.drawableTintMode);
            this.f839a = true;
        }
    }

    private interface SuperCaller {
        void a(int[] iArr, int i2);

        void b(int i2);

        int c();

        int d();

        void e(int i2, float f2);

        int[] f();

        TextClassifier g();

        int h();

        void i(TextClassifier textClassifier);

        void j(int i2, int i3, int i4, int i5);

        void k(int i2);

        int l();

        void m(int i2);
    }

    @RequiresApi
    class SuperCallerApi26 implements SuperCaller {
        SuperCallerApi26() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void a(int[] iArr, int i2) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void b(int i2) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int c() {
            return AppCompatTextView.super.getAutoSizeTextType();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int d() {
            return AppCompatTextView.super.getAutoSizeMinTextSize();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void e(int i2, float f2) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int[] f() {
            return AppCompatTextView.super.getAutoSizeTextAvailableSizes();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public TextClassifier g() {
            return AppCompatTextView.super.getTextClassifier();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int h() {
            return AppCompatTextView.super.getAutoSizeMaxTextSize();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void i(TextClassifier textClassifier) {
            AppCompatTextView.super.setTextClassifier(textClassifier);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void j(int i2, int i3, int i4, int i5) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void k(int i2) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public int l() {
            return AppCompatTextView.super.getAutoSizeStepGranularity();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void m(int i2) {
            AppCompatTextView.super.setAutoSizeTextTypeWithDefaults(i2);
        }
    }

    @RequiresApi
    class SuperCallerApi28 extends SuperCallerApi26 {
        SuperCallerApi28() {
            super();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void b(int i2) {
            AppCompatTextView.super.setLastBaselineToBottomHeight(i2);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void k(int i2) {
            AppCompatTextView.super.setFirstBaselineToTopHeight(i2);
        }
    }

    @RequiresApi
    class SuperCallerApi34 extends SuperCallerApi28 {
        SuperCallerApi34() {
            super();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void e(int i2, float f2) {
            AppCompatTextView.super.setLineHeight(i2, f2);
        }
    }

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    @NonNull
    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.mEmojiTextViewHelper == null) {
            this.mEmojiTextViewHelper = new AppCompatEmojiTextHelper(this);
        }
        return this.mEmojiTextViewHelper;
    }

    private void q() {
        Future<PrecomputedTextCompat> future = this.mPrecomputedTextFuture;
        if (future != null) {
            try {
                this.mPrecomputedTextFuture = null;
                TextViewCompat.o(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.b();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    @RestrictTo
    public int getAutoSizeMaxTextSize() {
        if (ViewUtils.f1095a) {
            return getSuperCaller().h();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.e();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo
    public int getAutoSizeMinTextSize() {
        if (ViewUtils.f1095a) {
            return getSuperCaller().d();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.f();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo
    public int getAutoSizeStepGranularity() {
        if (ViewUtils.f1095a) {
            return getSuperCaller().l();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.g();
        }
        return -1;
    }

    @Override // android.widget.TextView
    @RestrictTo
    public int[] getAutoSizeTextAvailableSizes() {
        if (ViewUtils.f1095a) {
            return getSuperCaller().f();
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        return appCompatTextHelper != null ? appCompatTextHelper.h() : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    @RestrictTo
    public int getAutoSizeTextType() {
        if (ViewUtils.f1095a) {
            return getSuperCaller().c() == 1 ? 1 : 0;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            return appCompatTextHelper.i();
        }
        return 0;
    }

    @Override // android.widget.TextView
    @Nullable
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.r(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return TextViewCompat.b(this);
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return TextViewCompat.c(this);
    }

    @RequiresApi
    @UiThread
    SuperCaller getSuperCaller() {
        if (this.mSuperCaller == null) {
            if (Build.VERSION.SDK_INT >= 34) {
                this.mSuperCaller = new SuperCallerApi34();
            } else {
                this.mSuperCaller = new SuperCallerApi28();
            }
        }
        return this.mSuperCaller;
    }

    @Nullable
    @RestrictTo
    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.c();
        }
        return null;
    }

    @Nullable
    @RestrictTo
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.d();
        }
        return null;
    }

    @Nullable
    @RestrictTo
    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.j();
    }

    @Nullable
    @RestrictTo
    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.k();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        q();
        return super.getText();
    }

    @Override // android.widget.TextView
    @NonNull
    @RequiresApi
    public TextClassifier getTextClassifier() {
        return getSuperCaller().g();
    }

    @NonNull
    public PrecomputedTextCompat.Params getTextMetricsParamsCompat() {
        return TextViewCompat.f(this);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.mTextHelper.r(this, onCreateInputConnection, editorInfo);
        return AppCompatHintHelper.a(onCreateInputConnection, editorInfo, this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.o(z, i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        q();
        super.onMeasure(i2, i3);
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper == null || ViewUtils.f1095a || !appCompatTextHelper.l()) {
            return;
        }
        this.mTextHelper.c();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().d(z);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        if (ViewUtils.f1095a) {
            getSuperCaller().j(i2, i3, i4, i5);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.t(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (ViewUtils.f1095a) {
            getSuperCaller().a(iArr, i2);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.u(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    @RestrictTo
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (ViewUtils.f1095a) {
            getSuperCaller().m(i2);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.v(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i2) {
        super.setBackgroundResource(i2);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.g(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(@Nullable ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.s(this, callback));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().e(z);
    }

    @Override // android.widget.TextView
    public void setFilters(@NonNull InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(@IntRange @Px int i2) {
        getSuperCaller().k(i2);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(@IntRange @Px int i2) {
        getSuperCaller().b(i2);
    }

    @Override // android.widget.TextView
    public void setLineHeight(@IntRange @Px int i2) {
        TextViewCompat.m(this, i2);
    }

    public void setPrecomputedText(@NonNull PrecomputedTextCompat precomputedTextCompat) {
        TextViewCompat.o(this, precomputedTextCompat);
    }

    @RestrictTo
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.i(colorStateList);
        }
    }

    @RestrictTo
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.j(mode);
        }
    }

    @RestrictTo
    public void setSupportCompoundDrawablesTintList(@Nullable ColorStateList colorStateList) {
        this.mTextHelper.w(colorStateList);
        this.mTextHelper.b();
    }

    @RestrictTo
    public void setSupportCompoundDrawablesTintMode(@Nullable PorterDuff.Mode mode) {
        this.mTextHelper.x(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.q(context, i2);
        }
    }

    @Override // android.widget.TextView
    @RequiresApi
    public void setTextClassifier(@Nullable TextClassifier textClassifier) {
        getSuperCaller().i(textClassifier);
    }

    public void setTextFuture(@Nullable Future<PrecomputedTextCompat> future) {
        this.mPrecomputedTextFuture = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(@NonNull PrecomputedTextCompat.Params params) {
        TextViewCompat.q(this, params);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        if (ViewUtils.f1095a) {
            super.setTextSize(i2, f2);
            return;
        }
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.A(i2, f2);
        }
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i2) {
        if (this.mIsSetTypefaceProcessing) {
            return;
        }
        Typeface a2 = (typeface == null || i2 <= 0) ? null : TypefaceCompat.a(getContext(), typeface, i2);
        this.mIsSetTypefaceProcessing = true;
        if (a2 != null) {
            typeface = a2;
        }
        try {
            super.setTypeface(typeface, i2);
        } finally {
            this.mIsSetTypefaceProcessing = false;
        }
    }

    public AppCompatTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.textViewStyle);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i2, float f2) {
        if (Build.VERSION.SDK_INT >= 34) {
            getSuperCaller().e(i2, f2);
        } else {
            TextViewCompat.n(this, i2, f2);
        }
    }

    public AppCompatTextView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(TintContextWrapper.b(context), attributeSet, i2);
        this.mIsSetTypefaceProcessing = false;
        this.mSuperCaller = null;
        ThemeUtils.a(this, getContext());
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper = appCompatBackgroundHelper;
        appCompatBackgroundHelper.e(attributeSet, i2);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.m(attributeSet, i2);
        appCompatTextHelper.b();
        this.mTextClassifierHelper = new AppCompatTextClassifierHelper(this);
        getEmojiTextViewHelper().c(attributeSet, i2);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i2 != 0 ? AppCompatResources.b(context, i2) : null, i3 != 0 ? AppCompatResources.b(context, i3) : null, i4 != 0 ? AppCompatResources.b(context, i4) : null, i5 != 0 ? AppCompatResources.b(context, i5) : null);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i2 != 0 ? AppCompatResources.b(context, i2) : null, i3 != 0 ? AppCompatResources.b(context, i3) : null, i4 != 0 ? AppCompatResources.b(context, i4) : null, i5 != 0 ? AppCompatResources.b(context, i5) : null);
        AppCompatTextHelper appCompatTextHelper = this.mTextHelper;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.p();
        }
    }
}
