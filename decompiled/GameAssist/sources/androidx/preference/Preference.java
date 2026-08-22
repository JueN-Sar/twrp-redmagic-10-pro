package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.PreferenceManager;
import com.google.android.gms.common.api.Api;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class Preference implements Comparable<Preference> {
    private Object A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private int K;
    private int L;
    private OnPreferenceChangeInternalListener M;
    private List N;
    private PreferenceGroup O;
    private boolean P;
    private boolean Q;
    private final View.OnClickListener R;

    /* renamed from: c, reason: collision with root package name */
    private Context f4663c;

    /* renamed from: h, reason: collision with root package name */
    private PreferenceManager f4664h;

    /* renamed from: i, reason: collision with root package name */
    private PreferenceDataStore f4665i;

    /* renamed from: j, reason: collision with root package name */
    private long f4666j;

    /* renamed from: k, reason: collision with root package name */
    private OnPreferenceChangeListener f4667k;

    /* renamed from: l, reason: collision with root package name */
    private OnPreferenceClickListener f4668l;

    /* renamed from: m, reason: collision with root package name */
    private int f4669m;

    /* renamed from: n, reason: collision with root package name */
    private int f4670n;

    /* renamed from: o, reason: collision with root package name */
    private CharSequence f4671o;

    /* renamed from: p, reason: collision with root package name */
    private CharSequence f4672p;

    /* renamed from: q, reason: collision with root package name */
    private int f4673q;

    /* renamed from: r, reason: collision with root package name */
    private Drawable f4674r;

    /* renamed from: s, reason: collision with root package name */
    private String f4675s;
    private Intent t;
    private String u;
    private Bundle v;
    private boolean w;
    private boolean x;
    private boolean y;
    private String z;

    public static class BaseSavedState extends AbsSavedState {
        public static final Parcelable.Creator<BaseSavedState> CREATOR = new Parcelable.Creator<BaseSavedState>() { // from class: androidx.preference.Preference.BaseSavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public BaseSavedState createFromParcel(Parcel parcel) {
                return new BaseSavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public BaseSavedState[] newArray(int i2) {
                return new BaseSavedState[i2];
            }
        };

        public BaseSavedState(Parcel parcel) {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    interface OnPreferenceChangeInternalListener {
        void c(Preference preference);

        void e(Preference preference);
    }

    public interface OnPreferenceChangeListener {
        boolean a(Preference preference, Object obj);
    }

    public interface OnPreferenceClickListener {
        boolean a(Preference preference);
    }

    public Preference(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f4669m = Api.BaseClientBuilder.API_PRIORITY_OTHER;
        this.f4670n = 0;
        this.w = true;
        this.x = true;
        this.y = true;
        this.B = true;
        this.C = true;
        this.D = true;
        this.E = true;
        this.F = true;
        this.H = true;
        this.J = true;
        this.K = R.layout.preference;
        this.R = new View.OnClickListener() { // from class: androidx.preference.Preference.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Preference.this.b0(view);
            }
        };
        this.f4663c = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Preference, i2, i3);
        this.f4673q = TypedArrayUtils.n(obtainStyledAttributes, R.styleable.Preference_icon, R.styleable.Preference_android_icon, 0);
        this.f4675s = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.Preference_key, R.styleable.Preference_android_key);
        this.f4671o = TypedArrayUtils.p(obtainStyledAttributes, R.styleable.Preference_title, R.styleable.Preference_android_title);
        this.f4672p = TypedArrayUtils.p(obtainStyledAttributes, R.styleable.Preference_summary, R.styleable.Preference_android_summary);
        this.f4669m = TypedArrayUtils.d(obtainStyledAttributes, R.styleable.Preference_order, R.styleable.Preference_android_order, Api.BaseClientBuilder.API_PRIORITY_OTHER);
        this.u = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.Preference_fragment, R.styleable.Preference_android_fragment);
        this.K = TypedArrayUtils.n(obtainStyledAttributes, R.styleable.Preference_layout, R.styleable.Preference_android_layout, R.layout.preference);
        this.L = TypedArrayUtils.n(obtainStyledAttributes, R.styleable.Preference_widgetLayout, R.styleable.Preference_android_widgetLayout, 0);
        this.w = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_enabled, R.styleable.Preference_android_enabled, true);
        this.x = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_selectable, R.styleable.Preference_android_selectable, true);
        this.y = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_persistent, R.styleable.Preference_android_persistent, true);
        this.z = TypedArrayUtils.o(obtainStyledAttributes, R.styleable.Preference_dependency, R.styleable.Preference_android_dependency);
        int i4 = R.styleable.Preference_allowDividerAbove;
        this.E = TypedArrayUtils.b(obtainStyledAttributes, i4, i4, this.x);
        int i5 = R.styleable.Preference_allowDividerBelow;
        this.F = TypedArrayUtils.b(obtainStyledAttributes, i5, i5, this.x);
        if (obtainStyledAttributes.hasValue(R.styleable.Preference_defaultValue)) {
            this.A = V(obtainStyledAttributes, R.styleable.Preference_defaultValue);
        } else if (obtainStyledAttributes.hasValue(R.styleable.Preference_android_defaultValue)) {
            this.A = V(obtainStyledAttributes, R.styleable.Preference_android_defaultValue);
        }
        this.J = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_shouldDisableView, R.styleable.Preference_android_shouldDisableView, true);
        boolean hasValue = obtainStyledAttributes.hasValue(R.styleable.Preference_singleLineTitle);
        this.G = hasValue;
        if (hasValue) {
            this.H = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_singleLineTitle, R.styleable.Preference_android_singleLineTitle, true);
        }
        this.I = TypedArrayUtils.b(obtainStyledAttributes, R.styleable.Preference_iconSpaceReserved, R.styleable.Preference_android_iconSpaceReserved, false);
        int i6 = R.styleable.Preference_isPreferenceVisible;
        this.D = TypedArrayUtils.b(obtainStyledAttributes, i6, i6, true);
        obtainStyledAttributes.recycle();
    }

    private void g0() {
        if (TextUtils.isEmpty(this.z)) {
            return;
        }
        Preference j2 = j(this.z);
        if (j2 != null) {
            j2.h0(this);
            return;
        }
        throw new IllegalStateException("Dependency \"" + this.z + "\" not found for preference \"" + this.f4675s + "\" (title: \"" + ((Object) this.f4671o) + "\"");
    }

    private void h0(Preference preference) {
        if (this.N == null) {
            this.N = new ArrayList();
        }
        this.N.add(preference);
        preference.T(this, u0());
    }

    private void k0(View view, boolean z) {
        view.setEnabled(z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                k0(viewGroup.getChildAt(childCount), z);
            }
        }
    }

    private void w0(SharedPreferences.Editor editor) {
        if (this.f4664h.n()) {
            editor.apply();
        }
    }

    private void x0() {
        Preference j2;
        String str = this.z;
        if (str == null || (j2 = j(str)) == null) {
            return;
        }
        j2.y0(this);
    }

    private void y0(Preference preference) {
        List list = this.N;
        if (list != null) {
            list.remove(preference);
        }
    }

    protected int A(int i2) {
        if (!v0()) {
            return i2;
        }
        PreferenceDataStore D = D();
        return D != null ? D.b(this.f4675s, i2) : this.f4664h.i().getInt(this.f4675s, i2);
    }

    protected String B(String str) {
        if (!v0()) {
            return str;
        }
        PreferenceDataStore D = D();
        return D != null ? D.c(this.f4675s, str) : this.f4664h.i().getString(this.f4675s, str);
    }

    public Set C(Set set) {
        if (!v0()) {
            return set;
        }
        PreferenceDataStore D = D();
        return D != null ? D.d(this.f4675s, set) : this.f4664h.i().getStringSet(this.f4675s, set);
    }

    public PreferenceDataStore D() {
        PreferenceDataStore preferenceDataStore = this.f4665i;
        if (preferenceDataStore != null) {
            return preferenceDataStore;
        }
        PreferenceManager preferenceManager = this.f4664h;
        if (preferenceManager != null) {
            return preferenceManager.g();
        }
        return null;
    }

    public PreferenceManager E() {
        return this.f4664h;
    }

    public CharSequence F() {
        return this.f4672p;
    }

    public CharSequence G() {
        return this.f4671o;
    }

    public final int H() {
        return this.L;
    }

    public boolean I() {
        return !TextUtils.isEmpty(this.f4675s);
    }

    public boolean J() {
        return this.w && this.B && this.C;
    }

    public boolean K() {
        return this.y;
    }

    public boolean L() {
        return this.x;
    }

    public final boolean M() {
        return this.D;
    }

    protected void N() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.M;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.c(this);
        }
    }

    public void O(boolean z) {
        List list = this.N;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Preference) list.get(i2)).T(this, z);
        }
    }

    protected void P() {
        OnPreferenceChangeInternalListener onPreferenceChangeInternalListener = this.M;
        if (onPreferenceChangeInternalListener != null) {
            onPreferenceChangeInternalListener.e(this);
        }
    }

    public void Q() {
        g0();
    }

    public void R(PreferenceViewHolder preferenceViewHolder) {
        preferenceViewHolder.f5252a.setOnClickListener(this.R);
        preferenceViewHolder.f5252a.setId(this.f4670n);
        TextView textView = (TextView) preferenceViewHolder.N(android.R.id.title);
        if (textView != null) {
            CharSequence G = G();
            if (TextUtils.isEmpty(G)) {
                textView.setVisibility(8);
            } else {
                textView.setText(G);
                textView.setVisibility(0);
                if (this.G) {
                    textView.setSingleLine(this.H);
                }
            }
        }
        TextView textView2 = (TextView) preferenceViewHolder.N(android.R.id.summary);
        if (textView2 != null) {
            CharSequence F = F();
            if (TextUtils.isEmpty(F)) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(F);
                textView2.setVisibility(0);
            }
        }
        ImageView imageView = (ImageView) preferenceViewHolder.N(android.R.id.icon);
        if (imageView != null) {
            if (this.f4673q != 0 || this.f4674r != null) {
                if (this.f4674r == null) {
                    this.f4674r = ContextCompat.e(l(), this.f4673q);
                }
                Drawable drawable = this.f4674r;
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
            if (this.f4674r != null) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(this.I ? 4 : 8);
            }
        }
        View N = preferenceViewHolder.N(R.id.icon_frame);
        if (N == null) {
            N = preferenceViewHolder.N(android.R.id.icon_frame);
        }
        if (N != null) {
            if (this.f4674r != null) {
                N.setVisibility(0);
            } else {
                N.setVisibility(this.I ? 4 : 8);
            }
        }
        if (this.J) {
            k0(preferenceViewHolder.f5252a, J());
        } else {
            k0(preferenceViewHolder.f5252a, true);
        }
        boolean L = L();
        preferenceViewHolder.f5252a.setFocusable(L);
        preferenceViewHolder.f5252a.setClickable(L);
        preferenceViewHolder.Q(this.E);
        preferenceViewHolder.R(this.F);
    }

    protected void S() {
    }

    public void T(Preference preference, boolean z) {
        if (this.B == z) {
            this.B = !z;
            O(u0());
            N();
        }
    }

    public void U() {
        x0();
        this.P = true;
    }

    protected Object V(TypedArray typedArray, int i2) {
        return null;
    }

    public void W(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void X(Preference preference, boolean z) {
        if (this.C == z) {
            this.C = !z;
            O(u0());
            N();
        }
    }

    protected void Y(Parcelable parcelable) {
        this.Q = true;
        if (parcelable != AbsSavedState.EMPTY_STATE && parcelable != null) {
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        }
    }

    protected Parcelable Z() {
        this.Q = true;
        return AbsSavedState.EMPTY_STATE;
    }

    public void a0() {
        PreferenceManager.OnPreferenceTreeClickListener e2;
        if (J()) {
            S();
            OnPreferenceClickListener onPreferenceClickListener = this.f4668l;
            if (onPreferenceClickListener == null || !onPreferenceClickListener.a(this)) {
                PreferenceManager E = E();
                if ((E == null || (e2 = E.e()) == null || !e2.k(this)) && this.t != null) {
                    l().startActivity(this.t);
                }
            }
        }
    }

    protected void b0(View view) {
        a0();
    }

    public boolean c(Object obj) {
        OnPreferenceChangeListener onPreferenceChangeListener = this.f4667k;
        return onPreferenceChangeListener == null || onPreferenceChangeListener.a(this, obj);
    }

    protected boolean c0(boolean z) {
        if (!v0()) {
            return false;
        }
        if (z == z(!z)) {
            return true;
        }
        PreferenceDataStore D = D();
        if (D != null) {
            D.e(this.f4675s, z);
        } else {
            SharedPreferences.Editor c2 = this.f4664h.c();
            c2.putBoolean(this.f4675s, z);
            w0(c2);
        }
        return true;
    }

    public final void d() {
        this.P = false;
    }

    protected boolean d0(int i2) {
        if (!v0()) {
            return false;
        }
        if (i2 == A(~i2)) {
            return true;
        }
        PreferenceDataStore D = D();
        if (D != null) {
            D.f(this.f4675s, i2);
        } else {
            SharedPreferences.Editor c2 = this.f4664h.c();
            c2.putInt(this.f4675s, i2);
            w0(c2);
        }
        return true;
    }

    @Override // java.lang.Comparable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(Preference preference) {
        int i2 = this.f4669m;
        int i3 = preference.f4669m;
        if (i2 != i3) {
            return i2 - i3;
        }
        CharSequence charSequence = this.f4671o;
        CharSequence charSequence2 = preference.f4671o;
        if (charSequence == charSequence2) {
            return 0;
        }
        if (charSequence == null) {
            return 1;
        }
        if (charSequence2 == null) {
            return -1;
        }
        return charSequence.toString().compareToIgnoreCase(preference.f4671o.toString());
    }

    protected boolean e0(String str) {
        if (!v0()) {
            return false;
        }
        if (TextUtils.equals(str, B(null))) {
            return true;
        }
        PreferenceDataStore D = D();
        if (D != null) {
            D.g(this.f4675s, str);
        } else {
            SharedPreferences.Editor c2 = this.f4664h.c();
            c2.putString(this.f4675s, str);
            w0(c2);
        }
        return true;
    }

    void f(Bundle bundle) {
        Parcelable parcelable;
        if (!I() || (parcelable = bundle.getParcelable(this.f4675s)) == null) {
            return;
        }
        this.Q = false;
        Y(parcelable);
        if (!this.Q) {
            throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
        }
    }

    public boolean f0(Set set) {
        if (!v0()) {
            return false;
        }
        if (set.equals(C(null))) {
            return true;
        }
        PreferenceDataStore D = D();
        if (D != null) {
            D.h(this.f4675s, set);
        } else {
            SharedPreferences.Editor c2 = this.f4664h.c();
            c2.putStringSet(this.f4675s, set);
            w0(c2);
        }
        return true;
    }

    void h(Bundle bundle) {
        if (I()) {
            this.Q = false;
            Parcelable Z = Z();
            if (!this.Q) {
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            }
            if (Z != null) {
                bundle.putParcelable(this.f4675s, Z);
            }
        }
    }

    public void i0(Bundle bundle) {
        f(bundle);
    }

    protected Preference j(String str) {
        PreferenceManager preferenceManager;
        if (TextUtils.isEmpty(str) || (preferenceManager = this.f4664h) == null) {
            return null;
        }
        return preferenceManager.a(str);
    }

    public void j0(Bundle bundle) {
        h(bundle);
    }

    public Context l() {
        return this.f4663c;
    }

    public void l0(int i2) {
        m0(ContextCompat.e(this.f4663c, i2));
        this.f4673q = i2;
    }

    public void m0(Drawable drawable) {
        if ((drawable != null || this.f4674r == null) && (drawable == null || this.f4674r == drawable)) {
            return;
        }
        this.f4674r = drawable;
        this.f4673q = 0;
        N();
    }

    public Bundle n() {
        if (this.v == null) {
            this.v = new Bundle();
        }
        return this.v;
    }

    public void n0(int i2) {
        this.K = i2;
    }

    StringBuilder o() {
        StringBuilder sb = new StringBuilder();
        CharSequence G = G();
        if (!TextUtils.isEmpty(G)) {
            sb.append(G);
            sb.append(' ');
        }
        CharSequence F = F();
        if (!TextUtils.isEmpty(F)) {
            sb.append(F);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb;
    }

    final void o0(OnPreferenceChangeInternalListener onPreferenceChangeInternalListener) {
        this.M = onPreferenceChangeInternalListener;
    }

    public String p() {
        return this.u;
    }

    public void p0(OnPreferenceClickListener onPreferenceClickListener) {
        this.f4668l = onPreferenceClickListener;
    }

    public void q0(int i2) {
        if (i2 != this.f4669m) {
            this.f4669m = i2;
            P();
        }
    }

    public Drawable r() {
        int i2;
        if (this.f4674r == null && (i2 = this.f4673q) != 0) {
            this.f4674r = ContextCompat.e(this.f4663c, i2);
        }
        return this.f4674r;
    }

    public void r0(CharSequence charSequence) {
        if ((charSequence != null || this.f4672p == null) && (charSequence == null || charSequence.equals(this.f4672p))) {
            return;
        }
        this.f4672p = charSequence;
        N();
    }

    long s() {
        return this.f4666j;
    }

    public void s0(int i2) {
        t0(this.f4663c.getString(i2));
    }

    public Intent t() {
        return this.t;
    }

    public void t0(CharSequence charSequence) {
        if ((charSequence != null || this.f4671o == null) && (charSequence == null || charSequence.equals(this.f4671o))) {
            return;
        }
        this.f4671o = charSequence;
        N();
    }

    public String toString() {
        return o().toString();
    }

    public String u() {
        return this.f4675s;
    }

    public boolean u0() {
        return !J();
    }

    public final int v() {
        return this.K;
    }

    protected boolean v0() {
        return this.f4664h != null && K() && I();
    }

    public OnPreferenceChangeListener w() {
        return this.f4667k;
    }

    public OnPreferenceClickListener x() {
        return this.f4668l;
    }

    public PreferenceGroup y() {
        return this.O;
    }

    protected boolean z(boolean z) {
        if (!v0()) {
            return z;
        }
        PreferenceDataStore D = D();
        return D != null ? D.a(this.f4675s, z) : this.f4664h.i().getBoolean(this.f4675s, z);
    }

    public final boolean z0() {
        return this.P;
    }

    public Preference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public Preference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.a(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
    }

    public Preference(Context context) {
        this(context, null);
    }
}
