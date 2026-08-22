package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public class PreferenceManager {

    /* renamed from: a, reason: collision with root package name */
    private Context f4741a;

    /* renamed from: c, reason: collision with root package name */
    private SharedPreferences f4743c;

    /* renamed from: d, reason: collision with root package name */
    private PreferenceDataStore f4744d;

    /* renamed from: e, reason: collision with root package name */
    private SharedPreferences.Editor f4745e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f4746f;

    /* renamed from: g, reason: collision with root package name */
    private String f4747g;

    /* renamed from: h, reason: collision with root package name */
    private int f4748h;

    /* renamed from: j, reason: collision with root package name */
    private PreferenceScreen f4750j;

    /* renamed from: k, reason: collision with root package name */
    private PreferenceComparisonCallback f4751k;

    /* renamed from: l, reason: collision with root package name */
    private OnPreferenceTreeClickListener f4752l;

    /* renamed from: m, reason: collision with root package name */
    private OnDisplayPreferenceDialogListener f4753m;

    /* renamed from: n, reason: collision with root package name */
    private OnNavigateToScreenListener f4754n;

    /* renamed from: b, reason: collision with root package name */
    private long f4742b = 0;

    /* renamed from: i, reason: collision with root package name */
    private int f4749i = 0;

    public interface OnDisplayPreferenceDialogListener {
        void j(Preference preference);
    }

    public interface OnNavigateToScreenListener {
        void e(PreferenceScreen preferenceScreen);
    }

    public interface OnPreferenceTreeClickListener {
        boolean k(Preference preference);
    }

    public static abstract class PreferenceComparisonCallback {
        public abstract boolean a(Preference preference, Preference preference2);

        public abstract boolean b(Preference preference, Preference preference2);
    }

    public static class SimplePreferenceComparisonCallback extends PreferenceComparisonCallback {
        @Override // androidx.preference.PreferenceManager.PreferenceComparisonCallback
        public boolean a(Preference preference, Preference preference2) {
            if (preference.getClass() != preference2.getClass()) {
                return false;
            }
            if ((preference == preference2 && preference.z0()) || !TextUtils.equals(preference.G(), preference2.G()) || !TextUtils.equals(preference.F(), preference2.F())) {
                return false;
            }
            Drawable r2 = preference.r();
            Drawable r3 = preference2.r();
            if ((r2 != r3 && (r2 == null || !r2.equals(r3))) || preference.J() != preference2.J() || preference.L() != preference2.L()) {
                return false;
            }
            if (!(preference instanceof TwoStatePreference) || ((TwoStatePreference) preference).A0() == ((TwoStatePreference) preference2).A0()) {
                return !(preference instanceof DropDownPreference) || preference == preference2;
            }
            return false;
        }

        @Override // androidx.preference.PreferenceManager.PreferenceComparisonCallback
        public boolean b(Preference preference, Preference preference2) {
            return preference.s() == preference2.s();
        }
    }

    public PreferenceManager(Context context) {
        this.f4741a = context;
        m(b(context));
    }

    private static String b(Context context) {
        return context.getPackageName() + "_preferences";
    }

    public Preference a(CharSequence charSequence) {
        PreferenceScreen preferenceScreen = this.f4750j;
        if (preferenceScreen == null) {
            return null;
        }
        return preferenceScreen.A0(charSequence);
    }

    SharedPreferences.Editor c() {
        if (this.f4744d != null) {
            return null;
        }
        if (!this.f4746f) {
            return i().edit();
        }
        if (this.f4745e == null) {
            this.f4745e = i().edit();
        }
        return this.f4745e;
    }

    public OnNavigateToScreenListener d() {
        return this.f4754n;
    }

    public OnPreferenceTreeClickListener e() {
        return this.f4752l;
    }

    public PreferenceComparisonCallback f() {
        return this.f4751k;
    }

    public PreferenceDataStore g() {
        return this.f4744d;
    }

    public PreferenceScreen h() {
        return this.f4750j;
    }

    public SharedPreferences i() {
        if (g() != null) {
            return null;
        }
        if (this.f4743c == null) {
            this.f4743c = (this.f4749i != 1 ? this.f4741a : ContextCompat.b(this.f4741a)).getSharedPreferences(this.f4747g, this.f4748h);
        }
        return this.f4743c;
    }

    public void j(OnDisplayPreferenceDialogListener onDisplayPreferenceDialogListener) {
        this.f4753m = onDisplayPreferenceDialogListener;
    }

    public void k(OnNavigateToScreenListener onNavigateToScreenListener) {
        this.f4754n = onNavigateToScreenListener;
    }

    public void l(OnPreferenceTreeClickListener onPreferenceTreeClickListener) {
        this.f4752l = onPreferenceTreeClickListener;
    }

    public void m(String str) {
        this.f4747g = str;
        this.f4743c = null;
    }

    boolean n() {
        return !this.f4746f;
    }

    public void o(Preference preference) {
        OnDisplayPreferenceDialogListener onDisplayPreferenceDialogListener = this.f4753m;
        if (onDisplayPreferenceDialogListener != null) {
            onDisplayPreferenceDialogListener.j(preference);
        }
    }
}
