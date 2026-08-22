package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.Editable;
import android.text.PrecomputedText;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class TextViewCompat {

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static int a(TextView textView) {
            return textView.getBreakStrategy();
        }

        @DoNotInline
        static ColorStateList b(TextView textView) {
            return textView.getCompoundDrawableTintList();
        }

        @DoNotInline
        static PorterDuff.Mode c(TextView textView) {
            return textView.getCompoundDrawableTintMode();
        }

        @DoNotInline
        static int d(TextView textView) {
            return textView.getHyphenationFrequency();
        }

        @DoNotInline
        static void e(TextView textView, int i2) {
            textView.setBreakStrategy(i2);
        }

        @DoNotInline
        static void f(TextView textView, ColorStateList colorStateList) {
            textView.setCompoundDrawableTintList(colorStateList);
        }

        @DoNotInline
        static void g(TextView textView, PorterDuff.Mode mode) {
            textView.setCompoundDrawableTintMode(mode);
        }

        @DoNotInline
        static void h(TextView textView, int i2) {
            textView.setHyphenationFrequency(i2);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static DecimalFormatSymbols a(Locale locale) {
            return DecimalFormatSymbols.getInstance(locale);
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static int a(TextView textView) {
            return textView.getAutoSizeMaxTextSize();
        }

        @DoNotInline
        static int b(TextView textView) {
            return textView.getAutoSizeMinTextSize();
        }

        @DoNotInline
        static int c(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        @DoNotInline
        static int[] d(TextView textView) {
            return textView.getAutoSizeTextAvailableSizes();
        }

        @DoNotInline
        static int e(TextView textView) {
            return textView.getAutoSizeTextType();
        }

        @DoNotInline
        static void f(TextView textView, int i2, int i3, int i4, int i5) {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }

        @DoNotInline
        static void g(TextView textView, int[] iArr, int i2) {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        @DoNotInline
        static void h(TextView textView, int i2) {
            textView.setAutoSizeTextTypeWithDefaults(i2);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static CharSequence a(PrecomputedText precomputedText) {
            return precomputedText;
        }

        @DoNotInline
        static String[] b(DecimalFormatSymbols decimalFormatSymbols) {
            return decimalFormatSymbols.getDigitStrings();
        }

        @DoNotInline
        static PrecomputedText.Params c(TextView textView) {
            return textView.getTextMetricsParams();
        }

        @DoNotInline
        static void d(TextView textView, int i2) {
            textView.setFirstBaselineToTopHeight(i2);
        }
    }

    @RequiresApi
    static class Api34Impl {
        @DoNotInline
        public static void a(@NonNull TextView textView, int i2, @FloatRange float f2) {
            textView.setLineHeight(i2, f2);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface AutoSizeTextType {
    }

    @RequiresApi
    private static class OreoCallback implements ActionMode.Callback {

        /* renamed from: a, reason: collision with root package name */
        private final ActionMode.Callback f3552a;

        /* renamed from: b, reason: collision with root package name */
        private final TextView f3553b;

        /* renamed from: c, reason: collision with root package name */
        private Class f3554c;

        /* renamed from: d, reason: collision with root package name */
        private Method f3555d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f3556e;

        /* renamed from: f, reason: collision with root package name */
        private boolean f3557f;

        private Intent a() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }

        private Intent b(ResolveInfo resolveInfo, TextView textView) {
            Intent putExtra = a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !e(textView));
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            return putExtra.setClassName(activityInfo.packageName, activityInfo.name);
        }

        private List c(Context context, PackageManager packageManager) {
            ArrayList arrayList = new ArrayList();
            if (!(context instanceof Activity)) {
                return arrayList;
            }
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(a(), 0)) {
                if (f(resolveInfo, context)) {
                    arrayList.add(resolveInfo);
                }
            }
            return arrayList;
        }

        private boolean e(TextView textView) {
            return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
        }

        private boolean f(ResolveInfo resolveInfo, Context context) {
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!activityInfo.exported) {
                return false;
            }
            String str = activityInfo.permission;
            return str == null || context.checkSelfPermission(str) == 0;
        }

        private void g(Menu menu) {
            Context context = this.f3553b.getContext();
            PackageManager packageManager = context.getPackageManager();
            if (!this.f3557f) {
                this.f3557f = true;
                try {
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.f3554c = cls;
                    this.f3555d = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                    this.f3556e = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    this.f3554c = null;
                    this.f3555d = null;
                    this.f3556e = false;
                }
            }
            try {
                Method declaredMethod = (this.f3556e && this.f3554c.isInstance(menu)) ? this.f3555d : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
                for (int size = menu.size() - 1; size >= 0; size--) {
                    MenuItem item = menu.getItem(size);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        declaredMethod.invoke(menu, Integer.valueOf(size));
                    }
                }
                List c2 = c(context, packageManager);
                for (int i2 = 0; i2 < c2.size(); i2++) {
                    ResolveInfo resolveInfo = (ResolveInfo) c2.get(i2);
                    menu.add(0, 0, i2 + 100, resolveInfo.loadLabel(packageManager)).setIntent(b(resolveInfo, this.f3553b)).setShowAsAction(1);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }

        ActionMode.Callback d() {
            return this.f3552a;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.f3552a.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.f3552a.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.f3552a.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            g(menu);
            return this.f3552a.onPrepareActionMode(actionMode, menu);
        }
    }

    public static Drawable[] a(TextView textView) {
        return textView.getCompoundDrawablesRelative();
    }

    public static int b(TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    public static int c(TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    public static int d(TextView textView) {
        return textView.getMaxLines();
    }

    private static int e(TextDirectionHeuristic textDirectionHeuristic) {
        TextDirectionHeuristic textDirectionHeuristic2;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        if (textDirectionHeuristic == textDirectionHeuristic3 || textDirectionHeuristic == (textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == textDirectionHeuristic2) {
            return 6;
        }
        return textDirectionHeuristic == textDirectionHeuristic3 ? 7 : 1;
    }

    public static PrecomputedTextCompat.Params f(TextView textView) {
        return new PrecomputedTextCompat.Params(Api28Impl.c(textView));
    }

    public static void g(TextView textView, int i2, int i3, int i4, int i5) {
        Api26Impl.f(textView, i2, i3, i4, i5);
    }

    public static void h(TextView textView, ColorStateList colorStateList) {
        Preconditions.h(textView);
        Api23Impl.f(textView, colorStateList);
    }

    public static void i(TextView textView, PorterDuff.Mode mode) {
        Preconditions.h(textView);
        Api23Impl.g(textView, mode);
    }

    public static void j(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    public static void k(TextView textView, int i2) {
        Preconditions.e(i2);
        Api28Impl.d(textView, i2);
    }

    public static void l(TextView textView, int i2) {
        Preconditions.e(i2);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2 - i3);
        }
    }

    public static void m(TextView textView, int i2) {
        Preconditions.e(i2);
        if (i2 != textView.getPaint().getFontMetricsInt(null)) {
            textView.setLineSpacing(i2 - r0, 1.0f);
        }
    }

    public static void n(TextView textView, int i2, float f2) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api34Impl.a(textView, i2, f2);
        } else {
            m(textView, Math.round(TypedValue.applyDimension(i2, f2, textView.getResources().getDisplayMetrics())));
        }
    }

    public static void o(TextView textView, PrecomputedTextCompat precomputedTextCompat) {
        textView.setText(Api28Impl.a(precomputedTextCompat.b()));
    }

    public static void p(TextView textView, int i2) {
        textView.setTextAppearance(i2);
    }

    public static void q(TextView textView, PrecomputedTextCompat.Params params) {
        textView.setTextDirection(e(params.d()));
        textView.getPaint().set(params.e());
        Api23Impl.e(textView, params.b());
        Api23Impl.h(textView, params.c());
    }

    public static ActionMode.Callback r(ActionMode.Callback callback) {
        return callback instanceof OreoCallback ? ((OreoCallback) callback).d() : callback;
    }

    public static ActionMode.Callback s(TextView textView, ActionMode.Callback callback) {
        return callback;
    }
}
