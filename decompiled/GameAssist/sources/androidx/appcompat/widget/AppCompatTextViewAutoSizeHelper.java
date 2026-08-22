package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.api.Api;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
class AppCompatTextViewAutoSizeHelper {

    /* renamed from: l, reason: collision with root package name */
    private static final RectF f852l = new RectF();

    /* renamed from: m, reason: collision with root package name */
    private static ConcurrentHashMap f853m = new ConcurrentHashMap();

    /* renamed from: h, reason: collision with root package name */
    private TextPaint f861h;

    /* renamed from: i, reason: collision with root package name */
    private final TextView f862i;

    /* renamed from: j, reason: collision with root package name */
    private final Context f863j;

    /* renamed from: a, reason: collision with root package name */
    private int f854a = 0;

    /* renamed from: b, reason: collision with root package name */
    private boolean f855b = false;

    /* renamed from: c, reason: collision with root package name */
    private float f856c = -1.0f;

    /* renamed from: d, reason: collision with root package name */
    private float f857d = -1.0f;

    /* renamed from: e, reason: collision with root package name */
    private float f858e = -1.0f;

    /* renamed from: f, reason: collision with root package name */
    private int[] f859f = new int[0];

    /* renamed from: g, reason: collision with root package name */
    private boolean f860g = false;

    /* renamed from: k, reason: collision with root package name */
    private final Impl f864k = new Impl29();

    @RequiresApi
    private static final class Api23Impl {
        @NonNull
        @DoNotInline
        static StaticLayout a(@NonNull CharSequence charSequence, @NonNull Layout.Alignment alignment, int i2, int i3, @NonNull TextView textView, @NonNull TextPaint textPaint, @NonNull Impl impl) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, i2);
            StaticLayout.Builder hyphenationFrequency = obtain.setAlignment(alignment).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency());
            if (i3 == -1) {
                i3 = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            }
            hyphenationFrequency.setMaxLines(i3);
            try {
                impl.a(obtain, textView);
            } catch (ClassCastException unused) {
                Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
            }
            return obtain.build();
        }
    }

    private static class Impl {
        Impl() {
        }

        void a(StaticLayout.Builder builder, TextView textView) {
        }

        boolean b(TextView textView) {
            return ((Boolean) AppCompatTextViewAutoSizeHelper.k(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    @RequiresApi
    private static class Impl23 extends Impl {
        Impl23() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) AppCompatTextViewAutoSizeHelper.k(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    @RequiresApi
    private static class Impl29 extends Impl23 {
        Impl29() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl23, androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection(textView.getTextDirectionHeuristic());
        }

        @Override // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper.Impl
        boolean b(TextView textView) {
            return textView.isHorizontallyScrollable();
        }
    }

    AppCompatTextViewAutoSizeHelper(TextView textView) {
        this.f862i = textView;
        this.f863j = textView.getContext();
    }

    private int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    private void c() {
        this.f854a = 0;
        this.f857d = -1.0f;
        this.f858e = -1.0f;
        this.f856c = -1.0f;
        this.f859f = new int[0];
        this.f855b = false;
    }

    private int d(RectF rectF) {
        int length = this.f859f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = 1;
        int i3 = length - 1;
        int i4 = 0;
        while (i2 <= i3) {
            int i5 = (i2 + i3) / 2;
            if (v(this.f859f[i5], rectF)) {
                int i6 = i5 + 1;
                i4 = i2;
                i2 = i6;
            } else {
                i4 = i5 - 1;
                i3 = i4;
            }
        }
        return this.f859f[i4];
    }

    private static Method j(String str) {
        try {
            Method method = (Method) f853m.get(str);
            if (method == null && (method = TextView.class.getDeclaredMethod(str, null)) != null) {
                method.setAccessible(true);
                f853m.put(str, method);
            }
            return method;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    static Object k(Object obj, String str, Object obj2) {
        try {
            return j(str).invoke(obj, null);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return obj2;
        }
    }

    private void q(float f2) {
        if (f2 != this.f862i.getPaint().getTextSize()) {
            this.f862i.getPaint().setTextSize(f2);
            boolean isInLayout = this.f862i.isInLayout();
            if (this.f862i.getLayout() != null) {
                this.f855b = false;
                try {
                    Method j2 = j("nullLayouts");
                    if (j2 != null) {
                        j2.invoke(this.f862i, null);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (isInLayout) {
                    this.f862i.forceLayout();
                } else {
                    this.f862i.requestLayout();
                }
                this.f862i.invalidate();
            }
        }
    }

    private boolean s() {
        if (w() && this.f854a == 1) {
            if (!this.f860g || this.f859f.length == 0) {
                int floor = ((int) Math.floor((this.f858e - this.f857d) / this.f856c)) + 1;
                int[] iArr = new int[floor];
                for (int i2 = 0; i2 < floor; i2++) {
                    iArr[i2] = Math.round(this.f857d + (i2 * this.f856c));
                }
                this.f859f = b(iArr);
            }
            this.f855b = true;
        } else {
            this.f855b = false;
        }
        return this.f855b;
    }

    private void t(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = typedArray.getDimensionPixelSize(i2, -1);
            }
            this.f859f = b(iArr);
            u();
        }
    }

    private boolean u() {
        boolean z = this.f859f.length > 0;
        this.f860g = z;
        if (z) {
            this.f854a = 1;
            this.f857d = r0[0];
            this.f858e = r0[r1 - 1];
            this.f856c = -1.0f;
        }
        return z;
    }

    private boolean v(int i2, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f862i.getText();
        TransformationMethod transformationMethod = this.f862i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f862i)) != null) {
            text = transformation;
        }
        int maxLines = this.f862i.getMaxLines();
        initTempTextPaint(i2);
        StaticLayout createLayout = createLayout(text, (Layout.Alignment) k(this.f862i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (createLayout.getLineCount() <= maxLines && createLayout.getLineEnd(createLayout.getLineCount() - 1) == text.length())) && ((float) createLayout.getHeight()) <= rectF.bottom;
    }

    private boolean w() {
        return !(this.f862i instanceof AppCompatEditText);
    }

    private void x(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
        this.f854a = 1;
        this.f857d = f2;
        this.f858e = f3;
        this.f856c = f4;
        this.f860g = false;
    }

    void a() {
        if (l()) {
            if (this.f855b) {
                if (this.f862i.getMeasuredHeight() <= 0 || this.f862i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f864k.b(this.f862i) ? WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_IS_ROUNDED_CORNERS_OVERLAY : (this.f862i.getMeasuredWidth() - this.f862i.getTotalPaddingLeft()) - this.f862i.getTotalPaddingRight();
                int height = (this.f862i.getHeight() - this.f862i.getCompoundPaddingBottom()) - this.f862i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f852l;
                synchronized (rectF) {
                    try {
                        rectF.setEmpty();
                        rectF.right = measuredWidth;
                        rectF.bottom = height;
                        float d2 = d(rectF);
                        if (d2 != this.f862i.getTextSize()) {
                            r(0, d2);
                        }
                    } finally {
                    }
                }
            }
            this.f855b = true;
        }
    }

    @NonNull
    @VisibleForTesting
    StaticLayout createLayout(@NonNull CharSequence charSequence, @NonNull Layout.Alignment alignment, int i2, int i3) {
        return Api23Impl.a(charSequence, alignment, i2, i3, this.f862i, this.f861h, this.f864k);
    }

    int e() {
        return Math.round(this.f858e);
    }

    int f() {
        return Math.round(this.f857d);
    }

    int g() {
        return Math.round(this.f856c);
    }

    int[] h() {
        return this.f859f;
    }

    int i() {
        return this.f854a;
    }

    @VisibleForTesting
    void initTempTextPaint(int i2) {
        TextPaint textPaint = this.f861h;
        if (textPaint == null) {
            this.f861h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f861h.set(this.f862i.getPaint());
        this.f861h.setTextSize(i2);
    }

    boolean l() {
        return w() && this.f854a != 0;
    }

    void m(AttributeSet attributeSet, int i2) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.f863j.obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i2, 0);
        TextView textView = this.f862i;
        ViewCompat.g0(textView, textView.getContext(), R.styleable.AppCompatTextView, attributeSet, obtainStyledAttributes, i2, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeTextType)) {
            this.f854a = obtainStyledAttributes.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity) ? obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize) ? obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize) ? obtainStyledAttributes.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes) && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            t(obtainTypedArray);
            obtainTypedArray.recycle();
        }
        obtainStyledAttributes.recycle();
        if (!w()) {
            this.f854a = 0;
            return;
        }
        if (this.f854a == 1) {
            if (!this.f860g) {
                DisplayMetrics displayMetrics = this.f863j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                x(dimension2, dimension3, dimension);
            }
            s();
        }
    }

    void n(int i2, int i3, int i4, int i5) {
        if (w()) {
            DisplayMetrics displayMetrics = this.f863j.getResources().getDisplayMetrics();
            x(TypedValue.applyDimension(i5, i2, displayMetrics), TypedValue.applyDimension(i5, i3, displayMetrics), TypedValue.applyDimension(i5, i4, displayMetrics));
            if (s()) {
                a();
            }
        }
    }

    void o(int[] iArr, int i2) {
        if (w()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArr2 = new int[length];
                if (i2 == 0) {
                    iArr2 = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f863j.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArr2[i3] = Math.round(TypedValue.applyDimension(i2, iArr[i3], displayMetrics));
                    }
                }
                this.f859f = b(iArr2);
                if (!u()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f860g = false;
            }
            if (s()) {
                a();
            }
        }
    }

    void p(int i2) {
        if (w()) {
            if (i2 == 0) {
                c();
                return;
            }
            if (i2 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i2);
            }
            DisplayMetrics displayMetrics = this.f863j.getResources().getDisplayMetrics();
            x(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (s()) {
                a();
            }
        }
    }

    void r(int i2, float f2) {
        Context context = this.f863j;
        q(TypedValue.applyDimension(i2, f2, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }
}
