package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.preference.internal.AbstractMultiSelectListPreference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class PreferenceFragmentCompat extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceManager.OnDisplayPreferenceDialogListener, PreferenceManager.OnNavigateToScreenListener, DialogPreference.TargetFragment {
    private PreferenceManager i0;
    RecyclerView j0;
    private boolean k0;
    private boolean l0;
    private Context m0;
    private int n0 = R.layout.preference_list_fragment;
    private final DividerDecoration o0 = new DividerDecoration();
    private Handler p0 = new Handler() { // from class: androidx.preference.PreferenceFragmentCompat.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragmentCompat.this.a2();
        }
    };
    private final Runnable q0 = new Runnable() { // from class: androidx.preference.PreferenceFragmentCompat.2
        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = PreferenceFragmentCompat.this.j0;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    };
    private Runnable r0;

    /* renamed from: androidx.preference.PreferenceFragmentCompat$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Preference f4710c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ String f4711h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ PreferenceFragmentCompat f4712i;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.Adapter adapter = this.f4712i.j0.getAdapter();
            if (!(adapter instanceof PreferenceGroup.PreferencePositionCallback)) {
                if (adapter != 0) {
                    throw new IllegalStateException("Adapter must implement PreferencePositionCallback");
                }
                return;
            }
            Preference preference = this.f4710c;
            int b2 = preference != null ? ((PreferenceGroup.PreferencePositionCallback) adapter).b(preference) : ((PreferenceGroup.PreferencePositionCallback) adapter).i(this.f4711h);
            if (b2 != -1) {
                this.f4712i.j0.l1(b2);
            } else {
                adapter.I(new ScrollToPreferenceObserver(adapter, this.f4712i.j0, this.f4710c, this.f4711h));
            }
        }
    }

    private class DividerDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f4713a;

        /* renamed from: b, reason: collision with root package name */
        private int f4714b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4715c = true;

        DividerDecoration() {
        }

        private boolean f(View view, RecyclerView recyclerView) {
            RecyclerView.ViewHolder h0 = recyclerView.h0(view);
            boolean z = false;
            if (!(h0 instanceof PreferenceViewHolder) || !((PreferenceViewHolder) h0).P()) {
                return false;
            }
            boolean z2 = this.f4715c;
            int indexOfChild = recyclerView.indexOfChild(view);
            if (indexOfChild >= recyclerView.getChildCount() - 1) {
                return z2;
            }
            RecyclerView.ViewHolder h02 = recyclerView.h0(recyclerView.getChildAt(indexOfChild + 1));
            if ((h02 instanceof PreferenceViewHolder) && ((PreferenceViewHolder) h02).O()) {
                z = true;
            }
            return z;
        }

        public void c(boolean z) {
            this.f4715c = z;
        }

        public void d(Drawable drawable) {
            if (drawable != null) {
                this.f4714b = drawable.getIntrinsicHeight();
            } else {
                this.f4714b = 0;
            }
            this.f4713a = drawable;
            PreferenceFragmentCompat.this.j0.v0();
        }

        public void e(int i2) {
            this.f4714b = i2;
            PreferenceFragmentCompat.this.j0.v0();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (f(view, recyclerView)) {
                rect.bottom = this.f4714b;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            if (this.f4713a == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                if (f(childAt, recyclerView)) {
                    int y = ((int) childAt.getY()) + childAt.getHeight();
                    this.f4713a.setBounds(0, y, width, this.f4714b + y);
                    this.f4713a.draw(canvas);
                }
            }
        }
    }

    public interface OnPreferenceDisplayDialogCallback {
        boolean a(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference);
    }

    public interface OnPreferenceStartFragmentCallback {
        boolean a(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference);
    }

    public interface OnPreferenceStartScreenCallback {
        boolean a(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen);
    }

    private static class ScrollToPreferenceObserver extends RecyclerView.AdapterDataObserver {

        /* renamed from: a, reason: collision with root package name */
        private final RecyclerView.Adapter f4717a;

        /* renamed from: b, reason: collision with root package name */
        private final RecyclerView f4718b;

        /* renamed from: c, reason: collision with root package name */
        private final Preference f4719c;

        /* renamed from: d, reason: collision with root package name */
        private final String f4720d;

        public ScrollToPreferenceObserver(RecyclerView.Adapter adapter, RecyclerView recyclerView, Preference preference, String str) {
            this.f4717a = adapter;
            this.f4718b = recyclerView;
            this.f4719c = preference;
            this.f4720d = str;
        }

        private void g() {
            this.f4717a.K(this);
            Preference preference = this.f4719c;
            int b2 = preference != null ? ((PreferenceGroup.PreferencePositionCallback) this.f4717a).b(preference) : ((PreferenceGroup.PreferencePositionCallback) this.f4717a).i(this.f4720d);
            if (b2 != -1) {
                this.f4718b.l1(b2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void a() {
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void b(int i2, int i3) {
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void c(int i2, int i3, Object obj) {
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void d(int i2, int i3) {
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void e(int i2, int i3, int i4) {
            g();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void f(int i2, int i3) {
            g();
        }
    }

    private void m2() {
        PreferenceScreen d2 = d2();
        if (d2 != null) {
            d2.U();
        }
        j2();
    }

    @Override // androidx.fragment.app.Fragment
    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray obtainStyledAttributes = this.m0.obtainStyledAttributes(null, R.styleable.PreferenceFragmentCompat, R.attr.preferenceFragmentCompatStyle, 0);
        this.n0 = obtainStyledAttributes.getResourceId(R.styleable.PreferenceFragmentCompat_android_layout, this.n0);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.PreferenceFragmentCompat_android_divider);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PreferenceFragmentCompat_android_dividerHeight, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.PreferenceFragmentCompat_allowDividerAfterLastItem, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(this.m0);
        View inflate = cloneInContext.inflate(this.n0, viewGroup, false);
        View findViewById = inflate.findViewById(android.R.id.list_container);
        if (!(findViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) findViewById;
        RecyclerView i2 = i2(cloneInContext, viewGroup2, bundle);
        if (i2 == null) {
            throw new RuntimeException("Could not create RecyclerView");
        }
        this.j0 = i2;
        i2.h(this.o0);
        k2(drawable);
        if (dimensionPixelSize != -1) {
            l2(dimensionPixelSize);
        }
        this.o0.c(z);
        if (this.j0.getParent() == null) {
            viewGroup2.addView(this.j0);
        }
        this.p0.post(this.q0);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void K0() {
        this.p0.removeCallbacks(this.q0);
        this.p0.removeMessages(1);
        if (this.k0) {
            m2();
        }
        this.j0 = null;
        super.K0();
    }

    @Override // androidx.fragment.app.Fragment
    public void X0(Bundle bundle) {
        super.X0(bundle);
        PreferenceScreen d2 = d2();
        if (d2 != null) {
            Bundle bundle2 = new Bundle();
            d2.j0(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void Y0() {
        super.Y0();
        this.i0.l(this);
        this.i0.j(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void Z0() {
        super.Z0();
        this.i0.l(null);
        this.i0.j(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void a1(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen d2;
        super.a1(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle("android:preferences")) != null && (d2 = d2()) != null) {
            d2.i0(bundle2);
        }
        if (this.k0) {
            a2();
            Runnable runnable = this.r0;
            if (runnable != null) {
                runnable.run();
                this.r0 = null;
            }
        }
        this.l0 = true;
    }

    void a2() {
        PreferenceScreen d2 = d2();
        if (d2 != null) {
            c2().setAdapter(f2(d2));
            d2.Q();
        }
        e2();
    }

    public Fragment b2() {
        return null;
    }

    public final RecyclerView c2() {
        return this.j0;
    }

    public PreferenceScreen d2() {
        return this.i0.h();
    }

    @Override // androidx.preference.PreferenceManager.OnNavigateToScreenListener
    public void e(PreferenceScreen preferenceScreen) {
        if (!((b2() instanceof OnPreferenceStartScreenCallback) && ((OnPreferenceStartScreenCallback) b2()).a(this, preferenceScreen)) && (t() instanceof OnPreferenceStartScreenCallback)) {
            ((OnPreferenceStartScreenCallback) t()).a(this, preferenceScreen);
        }
    }

    protected void e2() {
    }

    @Override // androidx.preference.DialogPreference.TargetFragment
    public Preference f(CharSequence charSequence) {
        PreferenceManager preferenceManager = this.i0;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.a(charSequence);
    }

    protected RecyclerView.Adapter f2(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    public RecyclerView.LayoutManager g2() {
        return new LinearLayoutManager(t());
    }

    public abstract void h2(Bundle bundle, String str);

    public RecyclerView i2(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (this.m0.getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(g2());
        recyclerView2.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(recyclerView2));
        return recyclerView2;
    }

    @Override // androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void j(Preference preference) {
        DialogFragment z2;
        boolean a2 = b2() instanceof OnPreferenceDisplayDialogCallback ? ((OnPreferenceDisplayDialogCallback) b2()).a(this, preference) : false;
        if (!a2 && (t() instanceof OnPreferenceDisplayDialogCallback)) {
            a2 = ((OnPreferenceDisplayDialogCallback) t()).a(this, preference);
        }
        if (!a2 && H().l0("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                z2 = EditTextPreferenceDialogFragmentCompat.z2(preference.u());
            } else if (preference instanceof ListPreference) {
                z2 = ListPreferenceDialogFragmentCompat.z2(preference.u());
            } else {
                if (!(preference instanceof AbstractMultiSelectListPreference)) {
                    throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
                }
                z2 = MultiSelectListPreferenceDialogFragmentCompat.z2(preference.u());
            }
            z2.T1(this, 0);
            z2.q2(H(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }

    protected void j2() {
    }

    @Override // androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean k(Preference preference) {
        if (preference.p() == null) {
            return false;
        }
        boolean a2 = b2() instanceof OnPreferenceStartFragmentCallback ? ((OnPreferenceStartFragmentCallback) b2()).a(this, preference) : false;
        return (a2 || !(t() instanceof OnPreferenceStartFragmentCallback)) ? a2 : ((OnPreferenceStartFragmentCallback) t()).a(this, preference);
    }

    public void k2(Drawable drawable) {
        this.o0.d(drawable);
    }

    public void l2(int i2) {
        this.o0.e(i2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        t().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        int i2 = typedValue.resourceId;
        if (i2 == 0) {
            i2 = R.style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(t(), i2);
        this.m0 = contextThemeWrapper;
        PreferenceManager preferenceManager = new PreferenceManager(contextThemeWrapper);
        this.i0 = preferenceManager;
        preferenceManager.k(this);
        h2(bundle, x() != null ? x().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }
}
