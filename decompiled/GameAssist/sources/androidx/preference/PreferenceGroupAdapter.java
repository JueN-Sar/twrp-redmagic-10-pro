package androidx.preference;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
public class PreferenceGroupAdapter extends RecyclerView.Adapter<PreferenceViewHolder> implements Preference.OnPreferenceChangeInternalListener, PreferenceGroup.PreferencePositionCallback {

    /* renamed from: c, reason: collision with root package name */
    private PreferenceGroup f4723c;

    /* renamed from: d, reason: collision with root package name */
    private List f4724d;

    /* renamed from: e, reason: collision with root package name */
    private List f4725e;

    /* renamed from: f, reason: collision with root package name */
    private List f4726f;

    /* renamed from: g, reason: collision with root package name */
    private PreferenceLayout f4727g;

    /* renamed from: h, reason: collision with root package name */
    private Handler f4728h;

    /* renamed from: i, reason: collision with root package name */
    private CollapsiblePreferenceGroupController f4729i;

    /* renamed from: j, reason: collision with root package name */
    private Runnable f4730j;

    private static class PreferenceLayout {

        /* renamed from: a, reason: collision with root package name */
        int f4736a;

        /* renamed from: b, reason: collision with root package name */
        int f4737b;

        /* renamed from: c, reason: collision with root package name */
        String f4738c;

        PreferenceLayout() {
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PreferenceLayout)) {
                return false;
            }
            PreferenceLayout preferenceLayout = (PreferenceLayout) obj;
            return this.f4736a == preferenceLayout.f4736a && this.f4737b == preferenceLayout.f4737b && TextUtils.equals(this.f4738c, preferenceLayout.f4738c);
        }

        public int hashCode() {
            return ((((527 + this.f4736a) * 31) + this.f4737b) * 31) + this.f4738c.hashCode();
        }

        PreferenceLayout(PreferenceLayout preferenceLayout) {
            this.f4736a = preferenceLayout.f4736a;
            this.f4737b = preferenceLayout.f4737b;
            this.f4738c = preferenceLayout.f4738c;
        }
    }

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this(preferenceGroup, new Handler());
    }

    private void L(Preference preference) {
        PreferenceLayout M = M(preference, null);
        if (this.f4726f.contains(M)) {
            return;
        }
        this.f4726f.add(M);
    }

    private PreferenceLayout M(Preference preference, PreferenceLayout preferenceLayout) {
        if (preferenceLayout == null) {
            preferenceLayout = new PreferenceLayout();
        }
        preferenceLayout.f4738c = preference.getClass().getName();
        preferenceLayout.f4736a = preference.v();
        preferenceLayout.f4737b = preference.H();
        return preferenceLayout;
    }

    private void N(List list, PreferenceGroup preferenceGroup) {
        preferenceGroup.H0();
        int E0 = preferenceGroup.E0();
        for (int i2 = 0; i2 < E0; i2++) {
            Preference D0 = preferenceGroup.D0(i2);
            list.add(D0);
            L(D0);
            if (D0 instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) D0;
                if (preferenceGroup2.F0()) {
                    N(list, preferenceGroup2);
                }
            }
            D0.o0(this);
        }
    }

    @VisibleForTesting
    static PreferenceGroupAdapter createInstanceWithCustomHandler(PreferenceGroup preferenceGroup, Handler handler) {
        return new PreferenceGroupAdapter(preferenceGroup, handler);
    }

    public Preference O(int i2) {
        if (i2 < 0 || i2 >= m()) {
            return null;
        }
        return (Preference) this.f4724d.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public void A(PreferenceViewHolder preferenceViewHolder, int i2) {
        O(i2).R(preferenceViewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public PreferenceViewHolder C(ViewGroup viewGroup, int i2) {
        PreferenceLayout preferenceLayout = (PreferenceLayout) this.f4726f.get(i2);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes((AttributeSet) null, R.styleable.BackgroundStyle);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = ContextCompat.e(viewGroup.getContext(), android.R.drawable.list_selector_background);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(preferenceLayout.f4736a, viewGroup, false);
        if (inflate.getBackground() == null) {
            ViewCompat.m0(inflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(android.R.id.widget_frame);
        if (viewGroup2 != null) {
            int i3 = preferenceLayout.f4737b;
            if (i3 != 0) {
                from.inflate(i3, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new PreferenceViewHolder(inflate);
    }

    void R() {
        Iterator it = this.f4725e.iterator();
        while (it.hasNext()) {
            ((Preference) it.next()).o0(null);
        }
        ArrayList arrayList = new ArrayList(this.f4725e.size());
        N(arrayList, this.f4723c);
        final List c2 = this.f4729i.c(this.f4723c);
        final List list = this.f4724d;
        this.f4724d = c2;
        this.f4725e = arrayList;
        PreferenceManager E = this.f4723c.E();
        if (E == null || E.f() == null) {
            r();
        } else {
            final PreferenceManager.PreferenceComparisonCallback f2 = E.f();
            DiffUtil.a(new DiffUtil.Callback() { // from class: androidx.preference.PreferenceGroupAdapter.2
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean a(int i2, int i3) {
                    return f2.a((Preference) list.get(i2), (Preference) c2.get(i3));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean b(int i2, int i3) {
                    return f2.b((Preference) list.get(i2), (Preference) c2.get(i3));
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int d() {
                    return c2.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int e() {
                    return list.size();
                }
            }).e(this);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((Preference) it2.next()).d();
        }
    }

    @Override // androidx.preference.PreferenceGroup.PreferencePositionCallback
    public int b(Preference preference) {
        int size = this.f4724d.size();
        for (int i2 = 0; i2 < size; i2++) {
            Preference preference2 = (Preference) this.f4724d.get(i2);
            if (preference2 != null && preference2.equals(preference)) {
                return i2;
            }
        }
        return -1;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public void c(Preference preference) {
        int indexOf = this.f4724d.indexOf(preference);
        if (indexOf != -1) {
            t(indexOf, preference);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeInternalListener
    public void e(Preference preference) {
        this.f4728h.removeCallbacks(this.f4730j);
        this.f4728h.post(this.f4730j);
    }

    @Override // androidx.preference.PreferenceGroup.PreferencePositionCallback
    public int i(String str) {
        int size = this.f4724d.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (TextUtils.equals(str, ((Preference) this.f4724d.get(i2)).u())) {
                return i2;
            }
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f4724d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long n(int i2) {
        if (q()) {
            return O(i2).s();
        }
        return -1L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        PreferenceLayout M = M(O(i2), this.f4727g);
        this.f4727g = M;
        int indexOf = this.f4726f.indexOf(M);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.f4726f.size();
        this.f4726f.add(new PreferenceLayout(this.f4727g));
        return size;
    }

    private PreferenceGroupAdapter(PreferenceGroup preferenceGroup, Handler handler) {
        this.f4727g = new PreferenceLayout();
        this.f4730j = new Runnable() { // from class: androidx.preference.PreferenceGroupAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                PreferenceGroupAdapter.this.R();
            }
        };
        this.f4723c = preferenceGroup;
        this.f4728h = handler;
        this.f4729i = new CollapsiblePreferenceGroupController(preferenceGroup, this);
        this.f4723c.o0(this);
        this.f4724d = new ArrayList();
        this.f4725e = new ArrayList();
        this.f4726f = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.f4723c;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            J(((PreferenceScreen) preferenceGroup2).I0());
        } else {
            J(true);
        }
        R();
    }
}
