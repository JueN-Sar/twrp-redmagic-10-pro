package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import com.google.android.material.internal.MaterialCheckable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo
@UiThread
/* loaded from: classes.dex */
public class CheckableGroup<T extends MaterialCheckable<T>> {

    /* renamed from: a, reason: collision with root package name */
    private final Map f14684a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Set f14685b = new HashSet();

    /* renamed from: c, reason: collision with root package name */
    private OnCheckedStateChangeListener f14686c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f14687d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f14688e;

    public interface OnCheckedStateChangeListener {
        void a(Set set);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g(MaterialCheckable materialCheckable) {
        int id = materialCheckable.getId();
        if (this.f14685b.contains(Integer.valueOf(id))) {
            return false;
        }
        MaterialCheckable materialCheckable2 = (MaterialCheckable) this.f14684a.get(Integer.valueOf(k()));
        if (materialCheckable2 != null) {
            r(materialCheckable2, false);
        }
        boolean add = this.f14685b.add(Integer.valueOf(id));
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        OnCheckedStateChangeListener onCheckedStateChangeListener = this.f14686c;
        if (onCheckedStateChangeListener != null) {
            onCheckedStateChangeListener.a(i());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean r(MaterialCheckable materialCheckable, boolean z) {
        int id = materialCheckable.getId();
        if (!this.f14685b.contains(Integer.valueOf(id))) {
            return false;
        }
        if (z && this.f14685b.size() == 1 && this.f14685b.contains(Integer.valueOf(id))) {
            materialCheckable.setChecked(true);
            return false;
        }
        boolean remove = this.f14685b.remove(Integer.valueOf(id));
        if (materialCheckable.isChecked()) {
            materialCheckable.setChecked(false);
        }
        return remove;
    }

    public void e(MaterialCheckable materialCheckable) {
        this.f14684a.put(Integer.valueOf(materialCheckable.getId()), materialCheckable);
        if (materialCheckable.isChecked()) {
            g(materialCheckable);
        }
        materialCheckable.setInternalOnCheckedChangeListener(new MaterialCheckable.OnCheckedChangeListener<T>() { // from class: com.google.android.material.internal.CheckableGroup.1
            @Override // com.google.android.material.internal.MaterialCheckable.OnCheckedChangeListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(MaterialCheckable materialCheckable2, boolean z) {
                if (!z) {
                    CheckableGroup checkableGroup = CheckableGroup.this;
                    if (!checkableGroup.r(materialCheckable2, checkableGroup.f14688e)) {
                        return;
                    }
                } else if (!CheckableGroup.this.g(materialCheckable2)) {
                    return;
                }
                CheckableGroup.this.m();
            }
        });
    }

    public void f(int i2) {
        MaterialCheckable materialCheckable = (MaterialCheckable) this.f14684a.get(Integer.valueOf(i2));
        if (materialCheckable != null && g(materialCheckable)) {
            m();
        }
    }

    public void h() {
        boolean z = !this.f14685b.isEmpty();
        Iterator it = this.f14684a.values().iterator();
        while (it.hasNext()) {
            r((MaterialCheckable) it.next(), false);
        }
        if (z) {
            m();
        }
    }

    public Set i() {
        return new HashSet(this.f14685b);
    }

    public List j(ViewGroup viewGroup) {
        Set i2 = i();
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof MaterialCheckable) && i2.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    public int k() {
        if (!this.f14687d || this.f14685b.isEmpty()) {
            return -1;
        }
        return ((Integer) this.f14685b.iterator().next()).intValue();
    }

    public boolean l() {
        return this.f14687d;
    }

    public void n(MaterialCheckable materialCheckable) {
        materialCheckable.setInternalOnCheckedChangeListener(null);
        this.f14684a.remove(Integer.valueOf(materialCheckable.getId()));
        this.f14685b.remove(Integer.valueOf(materialCheckable.getId()));
    }

    public void o(OnCheckedStateChangeListener onCheckedStateChangeListener) {
        this.f14686c = onCheckedStateChangeListener;
    }

    public void p(boolean z) {
        this.f14688e = z;
    }

    public void q(boolean z) {
        if (this.f14687d != z) {
            this.f14687d = z;
            h();
        }
    }
}
