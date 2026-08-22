package androidx.preference;

import android.app.DialogFragment;
import android.app.Fragment;
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
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class PreferenceFragment extends Fragment implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceManager.OnDisplayPreferenceDialogListener, PreferenceManager.OnNavigateToScreenListener, DialogPreference.TargetFragment {

    /* renamed from: c, reason: collision with root package name */
    private PreferenceManager f4685c;

    /* renamed from: h, reason: collision with root package name */
    RecyclerView f4686h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4687i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f4688j;

    /* renamed from: k, reason: collision with root package name */
    private Context f4689k;

    /* renamed from: l, reason: collision with root package name */
    private int f4690l = R.layout.preference_list_fragment;

    /* renamed from: m, reason: collision with root package name */
    private final DividerDecoration f4691m = new DividerDecoration();

    /* renamed from: n, reason: collision with root package name */
    private final Handler f4692n = new Handler() { // from class: androidx.preference.PreferenceFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            PreferenceFragment.this.a();
        }
    };

    /* renamed from: o, reason: collision with root package name */
    private final Runnable f4693o = new Runnable() { // from class: androidx.preference.PreferenceFragment.2
        @Override // java.lang.Runnable
        public void run() {
            RecyclerView recyclerView = PreferenceFragment.this.f4686h;
            recyclerView.focusableViewAvailable(recyclerView);
        }
    };

    /* renamed from: p, reason: collision with root package name */
    private Runnable f4694p;

    /* renamed from: androidx.preference.PreferenceFragment$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Preference f4697c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ String f4698h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ PreferenceFragment f4699i;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.Adapter adapter = this.f4699i.f4686h.getAdapter();
            if (!(adapter instanceof PreferenceGroup.PreferencePositionCallback)) {
                if (adapter != 0) {
                    throw new IllegalStateException("Adapter must implement PreferencePositionCallback");
                }
                return;
            }
            Preference preference = this.f4697c;
            int b2 = preference != null ? ((PreferenceGroup.PreferencePositionCallback) adapter).b(preference) : ((PreferenceGroup.PreferencePositionCallback) adapter).i(this.f4698h);
            if (b2 != -1) {
                this.f4699i.f4686h.l1(b2);
            } else {
                adapter.I(new ScrollToPreferenceObserver(adapter, this.f4699i.f4686h, this.f4697c, this.f4698h));
            }
        }
    }

    private class DividerDecoration extends RecyclerView.ItemDecoration {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f4700a;

        /* renamed from: b, reason: collision with root package name */
        private int f4701b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4702c = true;

        DividerDecoration() {
        }

        private boolean f(View view, RecyclerView recyclerView) {
            RecyclerView.ViewHolder h0 = recyclerView.h0(view);
            boolean z = false;
            if (!(h0 instanceof PreferenceViewHolder) || !((PreferenceViewHolder) h0).P()) {
                return false;
            }
            boolean z2 = this.f4702c;
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
            this.f4702c = z;
        }

        public void d(Drawable drawable) {
            if (drawable != null) {
                this.f4701b = drawable.getIntrinsicHeight();
            } else {
                this.f4701b = 0;
            }
            this.f4700a = drawable;
            PreferenceFragment.this.f4686h.v0();
        }

        public void e(int i2) {
            this.f4701b = i2;
            PreferenceFragment.this.f4686h.v0();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (f(view, recyclerView)) {
                rect.bottom = this.f4701b;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            if (this.f4700a == null) {
                return;
            }
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                if (f(childAt, recyclerView)) {
                    int y = ((int) childAt.getY()) + childAt.getHeight();
                    this.f4700a.setBounds(0, y, width, this.f4701b + y);
                    this.f4700a.draw(canvas);
                }
            }
        }
    }

    public interface OnPreferenceDisplayDialogCallback {
        boolean a(PreferenceFragment preferenceFragment, Preference preference);
    }

    public interface OnPreferenceStartFragmentCallback {
        boolean a(PreferenceFragment preferenceFragment, Preference preference);
    }

    public interface OnPreferenceStartScreenCallback {
        boolean a(PreferenceFragment preferenceFragment, PreferenceScreen preferenceScreen);
    }

    private static class ScrollToPreferenceObserver extends RecyclerView.AdapterDataObserver {

        /* renamed from: a, reason: collision with root package name */
        private final RecyclerView.Adapter f4704a;

        /* renamed from: b, reason: collision with root package name */
        private final RecyclerView f4705b;

        /* renamed from: c, reason: collision with root package name */
        private final Preference f4706c;

        /* renamed from: d, reason: collision with root package name */
        private final String f4707d;

        ScrollToPreferenceObserver(RecyclerView.Adapter adapter, RecyclerView recyclerView, Preference preference, String str) {
            this.f4704a = adapter;
            this.f4705b = recyclerView;
            this.f4706c = preference;
            this.f4707d = str;
        }

        private void g() {
            this.f4704a.K(this);
            Preference preference = this.f4706c;
            int b2 = preference != null ? ((PreferenceGroup.PreferencePositionCallback) this.f4704a).b(preference) : ((PreferenceGroup.PreferencePositionCallback) this.f4704a).i(this.f4707d);
            if (b2 != -1) {
                this.f4705b.l1(b2);
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

    private void q() {
        PreferenceScreen d2 = d();
        if (d2 != null) {
            d2.U();
        }
        n();
    }

    void a() {
        PreferenceScreen d2 = d();
        if (d2 != null) {
            c().setAdapter(h(d2));
            d2.Q();
        }
        g();
    }

    public Fragment b() {
        return null;
    }

    public final RecyclerView c() {
        return this.f4686h;
    }

    public PreferenceScreen d() {
        return this.f4685c.h();
    }

    @Override // androidx.preference.PreferenceManager.OnNavigateToScreenListener
    public void e(PreferenceScreen preferenceScreen) {
        if (!((b() instanceof OnPreferenceStartScreenCallback) && ((OnPreferenceStartScreenCallback) b()).a(this, preferenceScreen)) && (getActivity() instanceof OnPreferenceStartScreenCallback)) {
            ((OnPreferenceStartScreenCallback) getActivity()).a(this, preferenceScreen);
        }
    }

    @Override // androidx.preference.DialogPreference.TargetFragment
    public Preference f(CharSequence charSequence) {
        PreferenceManager preferenceManager = this.f4685c;
        if (preferenceManager == null) {
            return null;
        }
        return preferenceManager.a(charSequence);
    }

    protected void g() {
    }

    protected RecyclerView.Adapter h(PreferenceScreen preferenceScreen) {
        return new PreferenceGroupAdapter(preferenceScreen);
    }

    public RecyclerView.LayoutManager i() {
        return new LinearLayoutManager(getActivity());
    }

    @Override // androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void j(Preference preference) {
        DialogFragment i2;
        boolean a2 = b() instanceof OnPreferenceDisplayDialogCallback ? ((OnPreferenceDisplayDialogCallback) b()).a(this, preference) : false;
        if (!a2 && (getActivity() instanceof OnPreferenceDisplayDialogCallback)) {
            a2 = ((OnPreferenceDisplayDialogCallback) getActivity()).a(this, preference);
        }
        if (!a2 && getFragmentManager().findFragmentByTag("androidx.preference.PreferenceFragment.DIALOG") == null) {
            if (preference instanceof EditTextPreference) {
                i2 = EditTextPreferenceDialogFragment.i(preference.u());
            } else if (preference instanceof ListPreference) {
                i2 = ListPreferenceDialogFragment.i(preference.u());
            } else {
                if (!(preference instanceof MultiSelectListPreference)) {
                    throw new IllegalArgumentException("Tried to display dialog for unknown preference type. Did you forget to override onDisplayPreferenceDialog()?");
                }
                i2 = MultiSelectListPreferenceDialogFragment.i(preference.u());
            }
            i2.setTargetFragment(this, 0);
            i2.show(getFragmentManager(), "androidx.preference.PreferenceFragment.DIALOG");
        }
    }

    @Override // androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean k(Preference preference) {
        if (preference.p() == null) {
            return false;
        }
        boolean a2 = b() instanceof OnPreferenceStartFragmentCallback ? ((OnPreferenceStartFragmentCallback) b()).a(this, preference) : false;
        return (a2 || !(getActivity() instanceof OnPreferenceStartFragmentCallback)) ? a2 : ((OnPreferenceStartFragmentCallback) getActivity()).a(this, preference);
    }

    public abstract void l(Bundle bundle, String str);

    public RecyclerView m(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RecyclerView recyclerView;
        if (this.f4689k.getPackageManager().hasSystemFeature("android.hardware.type.automotive") && (recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view)) != null) {
            return recyclerView;
        }
        RecyclerView recyclerView2 = (RecyclerView) layoutInflater.inflate(R.layout.preference_recyclerview, viewGroup, false);
        recyclerView2.setLayoutManager(i());
        recyclerView2.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(recyclerView2));
        return recyclerView2;
    }

    protected void n() {
    }

    public void o(Drawable drawable) {
        this.f4691m.d(drawable);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(R.attr.preferenceTheme, typedValue, true);
        int i2 = typedValue.resourceId;
        if (i2 == 0) {
            i2 = R.style.PreferenceThemeOverlay;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getActivity(), i2);
        this.f4689k = contextThemeWrapper;
        PreferenceManager preferenceManager = new PreferenceManager(contextThemeWrapper);
        this.f4685c = preferenceManager;
        preferenceManager.k(this);
        l(bundle, getArguments() != null ? getArguments().getString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT") : null);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context context = this.f4689k;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.PreferenceFragment, TypedArrayUtils.a(context, R.attr.preferenceFragmentStyle, android.R.attr.preferenceFragmentStyle), 0);
        this.f4690l = obtainStyledAttributes.getResourceId(R.styleable.PreferenceFragment_android_layout, this.f4690l);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.PreferenceFragment_android_divider);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PreferenceFragment_android_dividerHeight, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.PreferenceFragment_allowDividerAfterLastItem, true);
        obtainStyledAttributes.recycle();
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(this.f4689k);
        View inflate = cloneInContext.inflate(this.f4690l, viewGroup, false);
        View findViewById = inflate.findViewById(android.R.id.list_container);
        if (!(findViewById instanceof ViewGroup)) {
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class");
        }
        ViewGroup viewGroup2 = (ViewGroup) findViewById;
        RecyclerView m2 = m(cloneInContext, viewGroup2, bundle);
        if (m2 == null) {
            throw new RuntimeException("Could not create RecyclerView");
        }
        this.f4686h = m2;
        m2.h(this.f4691m);
        o(drawable);
        if (dimensionPixelSize != -1) {
            p(dimensionPixelSize);
        }
        this.f4691m.c(z);
        if (this.f4686h.getParent() == null) {
            viewGroup2.addView(this.f4686h);
        }
        this.f4692n.post(this.f4693o);
        return inflate;
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        this.f4692n.removeCallbacks(this.f4693o);
        this.f4692n.removeMessages(1);
        if (this.f4687i) {
            q();
        }
        this.f4686h = null;
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PreferenceScreen d2 = d();
        if (d2 != null) {
            Bundle bundle2 = new Bundle();
            d2.j0(bundle2);
            bundle.putBundle("android:preferences", bundle2);
        }
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.f4685c.l(this);
        this.f4685c.j(this);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.f4685c.l(null);
        this.f4685c.j(null);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Bundle bundle2;
        PreferenceScreen d2;
        super.onViewCreated(view, bundle);
        if (bundle != null && (bundle2 = bundle.getBundle("android:preferences")) != null && (d2 = d()) != null) {
            d2.i0(bundle2);
        }
        if (this.f4687i) {
            a();
            Runnable runnable = this.f4694p;
            if (runnable != null) {
                runnable.run();
                this.f4694p = null;
            }
        }
        this.f4688j = true;
    }

    public void p(int i2) {
        this.f4691m.e(i2);
    }
}
